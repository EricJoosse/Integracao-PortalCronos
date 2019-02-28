SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


if object_id('monitorarIntegracaoFornecedores') is not null Drop procedure monitorarIntegracaoFornecedores
go

create procedure [dbo].[monitorarIntegracaoFornecedores] (
    @incremental_15min bit = 1
) as

 
-- exec dbo.monitorarIntegracaoFornecedores @incremental_15min = 0
-- exec dbo.monitorarIntegracaoFornecedores @incremental_15min = 1

SET TRANSACTION ISOLATION LEVEL SNAPSHOT

set nocount on

declare @fornecedor_id  int
      , @nm_fornecedor  varchar(50)
      , @vRet           varchar(max)
      , @ini            datetime
      , @fim            datetime
      , @qtd            int

-- Cotações com @ini < dt_hr_inicio_vigencia_cot < @fim estão selecionadas

-- Variáveis iguais para todos os fornecedores:
-- Frequência das ofertas de 15 minutos + 5 minutos (tempo máximo das ofertas automáticas) = 20 min.
-- Mais tarde alterado de 20 min para 25 min para evitar "false alarms" que acontecem de vez em quanto. 
-- ANTIGO: Overlap de 30 segundos dos intervalos para nunca perder nada.

-- Não alterar o intervalo para infinito para evitar que o monitoramento envia email de uma cotação de ontem 
-- que não foi enviada por causa do limite de 3 emails por dia por fornecedor!!!!

-- Para testar as condições complicadas nos selects, descomentar: 
--set @ini = Dateadd("ss", -30, Dateadd("mi", -9900000, getDate()))

--set @ini = Dateadd("ss", -30, Dateadd("mi", -35, getDate()))

if (Datepart(day, getDate()) = 2 and Datepart(month, getDate()) = 1 and Datepart(year, getDate()) = 2020 and Datepart(hour, getDate()) = 7)
  begin
    -- Na hora de 02/01/2020 de 07:00 até 08:00 horas: 
    set @ini = Dateadd("hour", -44, getDate()) -- 1 feriado integral + meio expediente = 7 + 24 + 8 + 5
  end
  
else if (Datepart(day, getDate()) = 6 and Datepart(month, getDate()) = 3 and Datepart(year, getDate()) = 2019 and Datepart(hour, getDate()) = 12)
  begin
    -- Carnaval: 
    set @ini = Dateadd("hour", -116, getDate()) -- 4 feriados integrais + meio expediente = 12 + 96 + 8
  end

  else if (Datepart(day, getDate()) = 22 and Datepart(month, getDate()) = 4 and Datepart(year, getDate()) = 2019 and Datepart(hour, getDate()) = 7)
  begin
    -- Páscoa: 
    set @ini = Dateadd("hour", -87, getDate()) -- 7 + 48 + 24 + 8 
  end
  
  else if (Datepart(day, getDate()) = 2 and Datepart(month, getDate()) = 5 and Datepart(year, getDate()) = 2019 and Datepart(hour, getDate()) = 7)
  begin
    -- Dia do Trabalhador: 
    set @ini = Dateadd("hour", -39, getDate()) -- 7 + 24 + 8 
  end
  
  else if (Datepart(day, getDate()) = 18 and Datepart(month, getDate()) = 11 and Datepart(year, getDate()) = 2019 and Datepart(hour, getDate()) = 7)
  begin
    -- : 
    set @ini = Dateadd("hour", -87, getDate()) -- 7 + 48 + 24 + 8 
  end
  
  else if (Datepart(day, getDate()) = 26 and Datepart(month, getDate()) = 12 and Datepart(year, getDate()) = 2019 and Datepart(hour, getDate()) = 7)
  begin
    -- Natal: 
    set @ini = Dateadd("hour", -39, getDate()) -- 7 + 24 + 8 
  end
  
else if (Datepart(weekday, getDate()) = 2 and Datepart(hour, getDate()) = 7)
  begin
    -- Na hora de todas as outras Segunda-feiras de 07:00 até 08:00 horas: 
    set @ini = Dateadd("hour", -63, getDate()) -- 7 + 48 + 8 
  end
  
else if (Datepart(weekday, getDate()) in (3, 4, 5, 6) and Datepart(hour, getDate()) = 7)
  begin
    -- Na hora de todas as outras terça-feiras até sexta-feiras de 07:00 até 08:00 horas: 
    set @ini = Dateadd("hour", -15, getDate()) -- 7 + 8 
  end
  
else
  begin
    -- Intervalo de 3 horas pois o 	web service das ofertas no site do Portal Cronos 
    -- está parado 2 horas durante horários de pico todas as terça-feiras de 10:40 até 12:40,  
    -- e também o monitoramento das ofertas está parado quase 2 horas durante os horários de almoço:
    set @ini = Dateadd("mi", -180, getDate())
  end

set @fim = Dateadd("mi", -25, getDate()) -- Com -20 min acontece "false alarms" de vez em quanto -> alterado de -20 para -25
                                         -- Se acontecer false alarms ainda por ter escapado dos intervalos, aumentar -25 para -35


declare c cursor for 
  select  f.id_fornecedor_fornec, pe.nm_pessoa
    from  dbo.Fornecedor f
          join dbo.Pessoa pe on pe.id_pessoa = f.id_pessoa
          join dbo.Padrao_Sistema_Integrado psi  on psi.id_padrao_sistema_int_psisint = f.id_padrao_sistema_int_psisint
	                                        and psi.dt_desativacao_psisint is null
    where f.id_padrao_sistema_int_psisint is not null
      and (                          f.id_fornecedor_fornec <>  21) -- Marítimos
    --and (@incremental_15min = 0 OR f.id_fornecedor_fornec <>  13) -- Formaggio
    --and (@incremental_15min = 0 OR f.id_fornecedor_fornec <> 947) -- JR Distribuição
    --and (                          f.id_fornecedor_fornec <>  30) -- Prolac
    --and (@incremental_15min = 0 OR f.id_fornecedor_fornec <>  60) -- Karne Keijo
      and (                          f.id_fornecedor_fornec <> 170) -- SOST (BA)
    --and (@incremental_15min = 0 OR f.id_fornecedor_fornec <> 385) -- Walmart
    --and (@incremental_15min = 0 OR f.id_fornecedor_fornec <>  33) -- Comal
    --and (@incremental_15min = 0 OR f.id_fornecedor_fornec <> 171) -- Propão
    --and (@incremental_15min = 0 OR f.id_fornecedor_fornec <>  14) -- Padeirão

-- 1. DESCOMENTAR OS "and"´S DOS f.id_fornecedor_fornec´S ACIMA APENAS PARA SUPRIMIR QUANTIDADES GRANDES DE EMAILS TEMPORARIAMENTE, 
--    NO CASO QUE JÁ SABEMOS QUE FORNECEDOR X NÃO ESTÁ FUNCIONANDO MAIS POR ENQUANTO; 
-- 
-- 2. REMOVER "@incremental_15min = 0 OR " SE TEMPORARIAMENTE NEM QUER VER O FORNECEDOR MAIS NO SQL SERVER MANAGEMENT STUDIO.



open c
fetch c into @fornecedor_id, @nm_fornecedor

while @@fetch_status = 0 
  begin

  -- No seguinte ainda tem um bug que esta sp envia o mesmo email 2, 3 ou no máximo 4 vezes repetidamente:

  -- ADICIONAR UM "OR" ABAIXO APENAS PARA FORNECEDORES COM PADRÕES DE INTEGRAÇÃO JÁ CADASTRADAS 
  -- NA BASE DE DADOS DE PRODUÇÃO DO PORTAL CRONOS, 
  -- PORÉM AINDA NÃO COLOCADAS EM PRODUÇÃO NO LADO DO SERVIDOR DO FORNECEDOR: 

  if @fornecedor_id = 0   -- Para facilitar a manutenção deste if 
  or @fornecedor_id = 385 -- Walmart  ainda não está em produção 
    begin
      if (
         --  ((select  count(distinct left(convert(varchar, dt_envio_cotacao_icotfor, 120),13))
         --      from  dbo.Integracao_Cotacao_Fornecedor
         --     where  id_fornecedor_fornec = @fornecedor_id) in (1,2)
         --  )
         not exists (select  top (1) 1
                       from  dbo.Integracao_Cotacao_Fornecedor
                      where  dt_envio_cotacao_icotfor < Dateadd(minute, -25, getdate())
                    )
         and exists (select  top (1) 1
                       from  dbo.Integracao_Cotacao_Fornecedor
                      where  dt_envio_cotacao_icotfor >= Dateadd(minute, -25, getdate())
                    )
         -- and ((select  max(left(convert(varchar, dt_envio_cotacao_icotfor, 120),13))
         --         from  dbo.Integracao_Cotacao_Fornecedor
         --        where  id_fornecedor_fornec = @fornecedor_id) = left(convert(varchar, getdate(), 120),13)
         --     )
      )
      begin
    select cast(@fornecedor_id as varchar)
         , 'INI'
         , null
         , null
         , null
         , @ini
         , @fim
         , null
         , null
         , null
      end
    end
  else
    begin

--  print '@fornecedor_id = ' + cast(@fornecedor_id as varchar)

    --Drop table #cotacoesParadas
    create table #cotacoesParadas (nm_fornecedor varchar(max),  nm_comprador varchar(max),  cd_cotacao_cot varchar(max), Erro varchar(max), QtdMeusProdutos int, QtdTentativas int, DtCadastro datetime, DtFim datetime, id_cotacao_cot int)

    Insert into #cotacoesParadas exec dbo.getCotacoesFornecedorIntegracao @ids_status='4', @idFornecedor = @fornecedor_id, @sort = 'DtHrFimVigencia', @incremental_15min = @incremental_15min, @ini = @ini, @fim = @fim

--    set @vRet = @vRet + 'Fornecedor ' + ... + ': Cotação ' + ... + ' não ofertada automaticamente'
--'
--.....enviar email

  select @qtd = count(*)
    from #cotacoesParadas

  --print @qtd

    if @qtd = 0
    begin
      if @incremental_15min = 1
      begin
    select @fornecedor_id as idFornecedor
         , ''
         , ''
         , null
         , null
         , null
         , @ini
         , @fim
         , null
         , null
         , null
      end
      else
      begin
    select @fornecedor_id as idFornecedor
         , @nm_fornecedor
         , ''
         , null
         , null
         , null
         , null
         , null
         , null
         , null
      end
    end
    else
    begin
      if @incremental_15min = 1
      begin
    select @fornecedor_id as idFornecedor
         , nm_fornecedor
         , 'Cotação ' + cd_cotacao_cot + ' da compradora ' + nm_comprador + ' não ofertada automaticamente' as Ocorrência
         , QtdMeusProdutos
         , QtdTentativas
         , Erro 
         , @ini as iniIntervalo
         , @fim as fimIntervalo
         , DtCadastro
         , DtFim
         , cd_cotacao_cot
      from #cotacoesParadas
      end
      else
      begin
    select @fornecedor_id as idFornecedor
         , nm_fornecedor
         , id_cotacao_cot
         , 'Cotação ' + cd_cotacao_cot + ' da compradora ' + nm_comprador + ' não ofertada automaticamente' as Ocorrência
         , QtdMeusProdutos
         , isnull(cast(QtdTentativas as varchar),'') as QtdTentativas
         , Erro 
         , @ini as iniIntervalo
         , @fim as fimIntervalo
         , DtCadastro
         , DtFim
      from #cotacoesParadas
      end
    end

    Drop table #cotacoesParadas

    end -- else

    fetch c into @fornecedor_id, @nm_fornecedor
  end -- while

close c
deallocate c


