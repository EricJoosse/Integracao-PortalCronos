SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


if object_id('getCotacoesFornecedorIntegracao') is not null Drop procedure getCotacoesFornecedorIntegracao
go

create procedure [dbo].[getCotacoesFornecedorIntegracao] (
    @idFornecedor int
  , @incremental_15min bit
  , @ini            datetime
  , @fim            datetime
  , @sort varchar(200)
  , @asc bit = 1
  , @ids_status varchar(1024) = null
  , @txt varchar(50) = null
  , @take int = 11
  , @skip int = 0
  , @containsEncerrado bit = 0
) as

 
-- exec dbo.getCotacoesFornecedorIntegracao @ids_status='4,5,9', @idFornecedor  = 73, @txt = '066-0046', @sort = 'DtHrFimVigencia', @asc = 0
-- exec dbo.getCotacoesFornecedorIntegracao @ids_status='4,5,9', @idFornecedor  = 73, @sort = 'DtHrFimVigencia', @asc = 0


SET TRANSACTION ISOLATION LEVEL SNAPSHOT

declare @p_text varchar(50)
      , @ids_ufs varchar(1024)

set @ids_ufs = dbo.getUFsFornecedorConcatenadas(@idFornecedor)

if @txt is not null
  begin
    set @p_text = Lower(@txt)
  end

/*

Usar o seguinte na sp getCotacoesFornecedorMaster : 

if isnull(@ids_status, '') <> ''
  begin
    create table #ids_status ( Data int)
    insert into #ids_status
    select Data from split(@ids_status, ',')
  end


if isnull(@ids_ufs, '') <> ''
  begin
    create table #ids_ufs ( Data varchar(2))
    insert into #ids_ufs
    select Data from split(@ids_ufs, ',')
  end

--exec( 'select * from #ids_ufs' )
--return

*/


declare @sn_cotacao_tab_produto_fornec bit
    --, @sn_aviso_cotacao_ususis bit
    --, @sn_aviso_ordem_compra_ususis bit

select  @sn_cotacao_tab_produto_fornec = isnull(sn_cotacao_tab_produto_fornec, 0)
  from  dbo.Fornecedor
  where id_fornecedor_fornec = @idFornecedor


create table #CotacoesTemp ( id_cotacao_cot int primary key
                           , dt_hr_fim_vigencia_cot datetime
                           , id_status_cotacao_stcot int)

if (isnull(@p_text, '') <> '') OR  (@sort = 'NmComprador')
  begin
    alter table #CotacoesTemp add nm_comprador varchar(50)
  end

alter table #CotacoesTemp add cd_cotacao_cot varchar(15)
alter table #CotacoesTemp add ds_familia_produto_cot varchar(500)
alter table #CotacoesTemp add id_unidade_federativa_unifed varchar(2)
alter table #CotacoesTemp add ds_condicao_pagamento_conpag varchar(50)

if @sort = 'DsStatusCotacao'
  begin
    alter table #CotacoesTemp add ds_status_cotacao_stcot varchar(40)
  end

if @sort = 'VendidorResponsavel'
  begin
    alter table #CotacoesTemp add vendidor_responsavel varchar(50)
  end

if @sort = 'NrProduto'
  begin
    alter table #CotacoesTemp add produtos_cotacao int
  end

if @sort = 'NrOfertas'
  begin
    alter table #CotacoesTemp add produtos_ofertados int
  end


create table #Cotacoes (id_cotacao_cot int primary key, row int, id_status_cotacao_stcot int)

DECLARE @SQL VARCHAR(MAX)

SET @SQL = 'INSERT INTO #CotacoesTemp
SELECT  c.id_cotacao_cot
     ,  isnull(c.dt_hr_fim_vigencia_cot, '''')    as dt_hr_fim_vigencia_cot
     ,  HIST.id_status_cotacao_stcot '


if (isnull(@p_text, '') <> '') OR  (@sort = 'NmComprador')
  begin
    set @SQL = @SQL + '
     ,  comp.nm_pessoa                            as nm_comprador '
  end

set @SQL = @SQL + '
     ,  c.cd_cotacao_cot
     ,  c.ds_familia_produto_cot
     ,  c.id_unidade_federativa_unifed
     ,  cp.ds_condicao_pagamento_conpag '

if @sort = 'DsStatusCotacao'
  begin
    set @SQL = @SQL + '
     ,  s.ds_status_cotacao_stcot '
  end


if @sort = 'VendidorResponsavel'
  begin
    set @SQL = @SQL + '
     ,  ( select  top (1) ve.nm_pessoa
            from        dbo.Fornecedor_Cotacao as fc   
                        INNER JOIN      dbo.Usuario_Sistema    as u    on fc.id_user_resp_forcot = u.user_id
                                                                      and fc.id_cotacao_cot = c.id_cotacao_cot
                                                                      and fc.id_fornecedor_fornec = ' + cast(@idFornecedor as varchar) + '
                        INNER JOIN      dbo.Pessoa             as ve   on ve.id_pessoa = u.id_pessoa
        ) as vendidor_responsavel '
  end
 
 
if @sort = 'NrProduto'
  begin
    set @SQL = @SQL + '
     ,  (select COUNT (*) from Produto_Cotacao pc where pc.id_cotacao_cot = HIST.id_cotacao_cot) as produtos_cotacao '
  end
 
 
if @sort = 'NrOfertas'
  begin
    set @SQL = @SQL + '
     ,  (select COUNT (distinct ofc.id_produto_cotacao_prdcot) 
           from Fornecedor_Prod_Marca_Cotacao            ofc 
                INNER JOIN      dbo.Fornecedor_Cotacao    fc    on fc.id_cotacao_cot = c.id_cotacao_cot
                                                               and fc.id_fornecedor_fornec = ' + cast(@idFornecedor as varchar) + '
                                                               and ofc.id_fornecedor_cotacao_forcot = fc.id_fornecedor_cotacao_forcot 
          where ofc.id_produto_cotacao_prdcot in (select pc.id_produto_cotacao_prdcot 
                                                    from Produto_Cotacao pc 
                                                   where pc.id_cotacao_cot = HIST.id_cotacao_cot)
        ) as produtos_ofertados '
  end
 
 
set @SQL = @SQL + '
  FROM  dbo.Historico_Status_Cotacao as HIST 
        INNER JOIN (SELECT H.id_cotacao_cot, MAX(H.dt_status_cotacao_htstcot) as dt_status_cotacao_htstcot 
                      FROM dbo.Historico_Status_Cotacao H GROUP by id_cotacao_cot) as TEMP_TABLE 
                   ON HIST.dt_status_cotacao_htstcot = TEMP_TABLE.dt_status_cotacao_htstcot 
                  AND HIST.id_cotacao_cot = TEMP_TABLE.id_cotacao_cot 
                  AND HIST.id_status_cotacao_stcot  not in (1, 9) '


if isnull(@ids_status, '') <> ''
  begin
    set @SQL = @SQL + '
               AND HIST.id_status_cotacao_stcot in (' + @ids_status + ') '
  end

 
set @SQL = @SQL + '
        INNER JOIN      dbo.Cotacao            as c    on c.id_cotacao_cot = HIST.id_cotacao_cot '


-- 42 = Compesa
-- 2351 e 2352 = empresas fictítias da Compesa
if (@idFornecedor = 2351 OR @idFornecedor = 2352)
  begin
    set @SQL = @SQL + '
                                                      and c.id_comprador_compr = 42 '
  end


if (isnull(@ids_ufs, '') <> '')
  begin
    set @SQL = @SQL + '
                                       and ( 
                                                      ( 
                                                          (select count(*)
                                                             from dbo.Requisicao_Entrada_Integra rei
                                                                   where  rei.id_cotacao_cot = c.id_cotacao_cot
                                                                     and  rei.dt_cancelamento_reqei is null
                                                          ) > 0
                                                          and exists ( select  top (1) 1 
                                                                         from  dbo.Requisicao_Entrada_Integra rei2
                                                                        where  rei2.id_cotacao_cot = c.id_cotacao_cot
                                                                          and  rei2.id_unidade_federativa_unifed_fatura in (''' + replace(@ids_ufs, ',', ''',''') + ''') 
                                                                          and  rei2.dt_cancelamento_reqei is null
                                                                     ) 
                                                      ) 
                                                 OR 
                                                      (
                                                          (select count(*)
                                                             from dbo.Requisicao_Entrada_Integra rei3
                                                                   where  rei3.id_cotacao_cot = c.id_cotacao_cot
                                                                     and  rei3.dt_cancelamento_reqei is null
                                                          ) = 0
                                                          and c.id_unidade_federativa_unifed in (''' + replace(@ids_ufs, ',', ''',''') + ''') 
                                                      )
                                           ) '
  end
 

if (isnull(@p_text, '') <> '') OR  (@sort = 'NmComprador')
  begin
    set @SQL = @SQL + '
        INNER JOIN      dbo.Comprador          as co   on co.id_comprador_compr = c.id_comprador_compr
        INNER JOIN      dbo.pessoa             as comp on comp.id_pessoa = co.id_pessoa '
  end


set @SQL = @SQL + '
        INNER JOIN      dbo.Condicao_Pagamento as cp   on cp.id_condicao_pagamento_conpag = c.id_condicao_pagamento_conpag '

if @sort = 'DsStatusCotacao'
  begin
    set @SQL = @SQL + '             
INNER JOIN      dbo.Status_Cotacao     as s    on s.id_status_cotacao_stcot = HIST.id_status_cotacao_stcot '
  end


if @sn_cotacao_tab_produto_fornec = 1
begin
  set @SQL = @SQL + '
                WHERE (        exists ( SELECT top (1) 1
                                          FROM dbo.Produto_Fornecedor       pf
                                               inner join dbo.Produto_Marca pm  on pf.id_produto_marca_prdmca = pm.id_produto_marca_prdmca
                                               inner join Produto           p   on p.id_produto_prod = pm.id_produto_prod
                                               inner join Produto_Cotacao   pc  on pc.id_cotacao_cot = HIST.id_cotacao_cot 
                                                                               and p.id_produto_prod = pc.id_produto_prod 
                                         WHERE pf.id_fornecedor_fornec = ' + cast(@idFornecedor as varchar) + ' 
                                           AND pf.dt_desativacao_prdfor IS NULL
                                      )
                      ) '
  end
else
  begin
  set @SQL = @SQL + '
                WHERE (    not exists ( SELECT top (1) 1
                                          FROM Segmento_Fornecedor 
                                         WHERE id_fornecedor_fornec = ' + cast(@idFornecedor as varchar) + ' 
                                           AND dt_desativacao_segfor IS NULL
                                      )
                        OR     exists ( SELECT top (1) 1
                                          FROM Segmento_Fornecedor sf
                                               inner join Estrutura_Produto ep  on sf.id_estrutura_produto_estprd = ep.id_segmento_estrutura_estprd 
                                               inner join Produto           p   on p.id_estrutura_produto_estprd = ep.id_estrutura_produto_estprd
                                               inner join Produto_Cotacao   pc  on pc.id_cotacao_cot = HIST.id_cotacao_cot 
                                                                               and p.id_produto_prod = pc.id_produto_prod 
                                         WHERE sf.id_fornecedor_fornec = ' + cast(@idFornecedor as varchar) + '
                                           AND sf.dt_desativacao_segfor IS NULL
                                      )
                        OR     exists ( SELECT top (1) 1
                                          FROM dbo.Produto_Fornecedor       pf
                                               inner join dbo.Produto_Marca pm  on pf.id_produto_marca_prdmca = pm.id_produto_marca_prdmca
                                               inner join Produto           p   on p.id_produto_prod = pm.id_produto_prod
                                               inner join Produto_Cotacao   pc  on pc.id_cotacao_cot = HIST.id_cotacao_cot 
                                                                               and p.id_produto_prod = pc.id_produto_prod 
                                         WHERE pf.id_fornecedor_fornec = ' + cast(@idFornecedor as varchar) + ' 
                                           AND pf.dt_desativacao_prdfor IS NULL
                                      )
                      ) '
  end

 

if isnull(@p_text, '') <> ''
  begin
    set @SQL = @SQL + '
    AND (    Lower(comp.nm_pessoa) like ''%' + @p_text + '%'' 
          or Lower(c.cd_cotacao_cot) = ''' +  @p_text + '''
          or Lower(c.ds_familia_produto_cot) like ''%' + @p_text + '%'' 
          or Lower(c.ds_cotacao_cot) like ''%' + @p_text + '%'' 
          or Lower(cp.ds_condicao_pagamento_conpag) like ''%' + @p_text + '%'' 
          or Lower(( select  top (1) ve.nm_pessoa
                       from        dbo.Fornecedor_Cotacao as fc   
                              INNER JOIN      dbo.Usuario_Sistema    as u    on fc.id_user_resp_forcot = u.user_id
                                                                            and fc.id_cotacao_cot = c.id_cotacao_cot
                                                                            and fc.id_fornecedor_fornec = ' + cast(@idFornecedor as varchar) + '
                              INNER JOIN      dbo.Pessoa             as ve   on ve.id_pessoa = u.id_pessoa)) like ''%' + @p_text + '%'' 
       -- or c.dt_hr_fim_vigencia_cot = cast(@txt as datetime).....
        ) '
  end


--print @sql

exec (@sql)



-- UNION com cotações encerradas : 

SET @SQL = 'INSERT INTO #CotacoesTemp
SELECT  c.id_cotacao_cot
     ,  isnull(c.dt_hr_fim_vigencia_cot, '''')    as dt_hr_fim_vigencia_cot
     ,  HIST.id_status_cotacao_stcot '


if (isnull(@p_text, '') <> '') OR  (@sort = 'NmComprador')
  begin
    set @SQL = @SQL + '
     ,  comp.nm_pessoa                            as nm_comprador '
  end

set @SQL = @SQL + '
     ,  c.cd_cotacao_cot
     ,  c.ds_familia_produto_cot
     ,  c.id_unidade_federativa_unifed
     ,  cp.ds_condicao_pagamento_conpag '

if @sort = 'DsStatusCotacao'
  begin
    set @SQL = @SQL + '
     ,  s.ds_status_cotacao_stcot '
  end


if @sort = 'VendidorResponsavel'
  begin
    set @SQL = @SQL + '
     ,  ve.nm_pessoa                              as vendidor_responsavel '
  end
 
 
if @sort = 'NrProduto'
  begin
    set @SQL = @SQL + '
     ,  (select COUNT (*) from Produto_Cotacao pc where pc.id_cotacao_cot = HIST.id_cotacao_cot) as produtos_cotacao '
  end
 
 
if @sort = 'NrOfertas'
  begin
    set @SQL = @SQL + '
     ,  (select COUNT (distinct ofc.id_produto_cotacao_prdcot) 
           from Fornecedor_Prod_Marca_Cotacao ofc 
          where ofc.id_fornecedor_cotacao_forcot = fc.id_fornecedor_cotacao_forcot 
            and ofc.id_produto_cotacao_prdcot in (select pc.id_produto_cotacao_prdcot 
                                                    from Produto_Cotacao pc 
                                                   where pc.id_cotacao_cot = HIST.id_cotacao_cot)
        ) as produtos_ofertados '
  end
 
 
set @SQL = @SQL + '
  from  dbo.Historico_Status_Cotacao as HIST 
        INNER JOIN (SELECT H.id_cotacao_cot, MAX(H.dt_status_cotacao_htstcot) as dt_status_cotacao_htstcot 
                      FROM dbo.Historico_Status_Cotacao H GROUP by id_cotacao_cot) as TEMP_TABLE 
                   ON HIST.dt_status_cotacao_htstcot = TEMP_TABLE.dt_status_cotacao_htstcot 
                  AND HIST.id_cotacao_cot = TEMP_TABLE.id_cotacao_cot 
                  and ' + cast(@containsEncerrado as varchar) + ' = 1
                  and HIST.id_status_cotacao_stcot = 9
        INNER JOIN      dbo.Cotacao            as c  on c.id_cotacao_cot = HIST.id_cotacao_cot '



-- 42 = Compesa
-- 2351 e 2352 = empresas fictítias da Compesa
if (@idFornecedor = 2351 OR @idFornecedor = 2352)
  begin
    set @SQL = @SQL + '
                                                    and c.id_comprador_compr = 42 '
  end


if (isnull(@ids_ufs, '') <> '')
  begin
    set @SQL = @SQL + '
                                       and ( 
                                                      ( 
                                                          (select count(*)
                                                             from dbo.Requisicao_Entrada_Integra rei
                                                                   where  rei.id_cotacao_cot = c.id_cotacao_cot
                                                                     and  rei.dt_cancelamento_reqei is null
                                                          ) > 0
                                                          and exists ( select  top (1) 1 
                                                                         from  dbo.Requisicao_Entrada_Integra rei2
                                                                        where  rei2.id_cotacao_cot = c.id_cotacao_cot
                                                                          and  rei2.id_unidade_federativa_unifed_fatura in (''' + replace(@ids_ufs, ',', ''',''') + ''') 
                                                                          and  rei2.dt_cancelamento_reqei is null
                                                                     ) 
                                                      ) 
                                                 OR 
                                                      (
                                                          (select count(*)
                                                             from dbo.Requisicao_Entrada_Integra rei3
                                                                   where  rei3.id_cotacao_cot = c.id_cotacao_cot
                                                                     and  rei3.dt_cancelamento_reqei is null
                                                          ) = 0
                                                          and c.id_unidade_federativa_unifed in (''' + replace(@ids_ufs, ',', ''',''') + ''') 
                                                      )
                                           ) '
  end




if (isnull(@p_text, '') <> '') OR  (@sort = 'NmComprador')
  begin
    set @SQL = @SQL + '
        INNER JOIN      dbo.Comprador          as co   on co.id_comprador_compr = c.id_comprador_compr
        INNER JOIN      dbo.pessoa             as comp on comp.id_pessoa = co.id_pessoa '
  end


set @SQL = @SQL + '
        INNER JOIN      dbo.Condicao_Pagamento as cp   on cp.id_condicao_pagamento_conpag = c.id_condicao_pagamento_conpag
        INNER JOIN      dbo.Fornecedor_Cotacao as fc   on fc.id_cotacao_cot = c.id_cotacao_cot
                                                      and fc.id_fornecedor_fornec = ' + cast(@idFornecedor as varchar) + ' '

if (isnull(@p_text, '') <> '') OR  (@sort = 'VendidorResponsavel')
  begin
    set @SQL = @SQL + '
        INNER JOIN      dbo.Usuario_Sistema    as u    on fc.id_user_resp_forcot = u.user_id
        INNER JOIN      dbo.Pessoa             as ve   on ve.id_pessoa = u.id_pessoa '
  end


if @sort = 'DsStatusCotacao'
  begin
    set @SQL = @SQL + '
             INNER JOIN      dbo.Status_Cotacao     as s    on s.id_status_cotacao_stcot = HIST.id_status_cotacao_stcot '
  end




if isnull(@p_text, '') <> ''
  begin
    set @SQL = @SQL + '
 where (     Lower(comp.nm_pessoa) like ''%' + @p_text + '%'' 
          or Lower(c.cd_cotacao_cot) = ''' +  @p_text + '''
          or Lower(c.ds_familia_produto_cot) like ''%' + @p_text + '%'' 
          or Lower(c.ds_cotacao_cot) like ''%' + @p_text + '%'' 
          or Lower(cp.ds_condicao_pagamento_conpag) like ''%' + @p_text + '%'' 
          or Lower(ve.nm_pessoa) like ''%' + @p_text + '%'' 
        ) '
  end


--print @sql
--return

exec (@sql)

--IF @@ROWCOUNT = 0
--   PRINT 'Warning: No rows found';
--ELSE
--   PRINT 'rows encontrado';


SET @SQL = 'INSERT INTO #Cotacoes
select  IView.id_cotacao_cot
     ,  ROW_NUMBER()  OVER (ORDER BY ' + ( case when @sort = 'DtHrFimVigencia'      then 'IView.dt_hr_fim_vigencia_cot'
                                                when @sort = 'NmComprador'          then 'IView.nm_comprador'
                                                when @sort = 'IdUnidadeFederativa'  then 'IView.id_unidade_federativa_unifed'
                                                when @sort = 'CdCotacao'            then 'IView.cd_cotacao_cot'
                                                when @sort = 'DsFamilias'           then 'IView.ds_familia_produto_cot'
                                                when @sort = 'DsStatusCotacao'      then 'IView.ds_status_cotacao_stcot'
                                                when @sort = 'DsCondicaoPagamento'  then 'IView.ds_condicao_pagamento_conpag'
                                                when @sort = 'VendidorResponsavel'  then 'IView.vendidor_responsavel'
                                                when @sort = 'NrProduto'            then 'IView.produtos_cotacao'
                                                when @sort = 'NrOfertas'            then 'IView.produtos_ofertados'
                                                else                                     'IView.dt_hr_fim_vigencia_cot'
                                       end) + ' ' + (case when @asc = 1 then 'ASC' else 'DESC' end) + '
                           ) row 
     ,  IView.id_status_cotacao_stcot 
   from #CotacoesTemp as IView '

--print @sql

exec (@sql)



--SELECT count(*) total from #Cotacoes
--DELETE FROM #Cotacoes WHERE row not between (@skip + 1) and (@take + @skip)


if @incremental_15min = 1
begin

-- Problemas a serem recebidos no email: 

SELECT  forn.nm_pessoa  as nm_fornecedor,
        comp.nm_pessoa  as nm_comprador,
        c.cd_cotacao_cot,
        isnull(lei.ds_ocorrencia_logeint, '') as Erro,
        (select count(*)
                  from dbo.Produto_Fornecedor pf
                       join dbo.Produto_Marca pm on pf.id_produto_marca_prdmca = pm.id_produto_marca_prdmca
                       join Produto_Cotacao   pc  on pc.id_cotacao_cot = ct.id_cotacao_cot
                                                 and pm.id_produto_prod = pc.id_produto_prod 
                 where pf.id_fornecedor_fornec = @idFornecedor
                   and pf.cd_produto_fornecedor_prdfor is not null
                   and pf.dt_desativacao_prdfor IS NULL
               ) as QtdMeusProdutos,
       (SELECT  isnull(icf.qt_envio_cotacao_icotfor,0)
          FROM  dbo.Integracao_Cotacao_Fornecedor icf
          where icf.id_cotacao_cot = ct.id_cotacao_cot
          and   icf.id_fornecedor_fornec = @idFornecedor
               ) as QtdTentativas,
        c.dt_cadastro_cot,
        c.dt_hr_fim_vigencia_cot,
        c.id_cotacao_cot
  FROM                  #Cotacoes              as ct   
        INNER JOIN      dbo.Cotacao            as c    on c.id_cotacao_cot = ct.id_cotacao_cot
                                                      and ct.id_status_cotacao_stcot = 4
                                                    --and c.sn_cond_pagto_obrigatoria_cot = 0
                                                      and c.dt_hr_inicio_vigencia_cot < @fim
                                                      and c.dt_hr_inicio_vigencia_cot > @ini
    and (select COUNT (distinct ofc.id_produto_cotacao_prdcot) 
           from Fornecedor_Prod_Marca_Cotacao            ofc 
                INNER JOIN      dbo.Fornecedor_Cotacao    fc    on fc.id_cotacao_cot = c.id_cotacao_cot
                                                               and fc.id_fornecedor_fornec = @idFornecedor
                                                               and ofc.id_fornecedor_cotacao_forcot = fc.id_fornecedor_cotacao_forcot 
                INNER JOIN      dbo.Usuario_Sistema       ws    on ws.id_perfil_acesso_perace = 12 -- Web service
                                                               and ofc.user_id_ususis = ws.user_id
          where ofc.id_produto_cotacao_prdcot in (select pc.id_produto_cotacao_prdcot 
                                                    from Produto_Cotacao pc 
                                                   where pc.id_cotacao_cot = ct.id_cotacao_cot)
        ) = 0
    -- No caso que não tiver NADA na tabela de log, a causa pode ser que a cotação inteira não foi ofertada 
    -- porque não tem estoque de nenhum dos meus produtos,
    -- neste caso somente tem suspeito de problema se tiver mais de 3 produtos,
    -- então apenas interessa: 1. Tem mais  de 3 meus produtos e não foi ofertada e não tem nenhum registro na tabela de log; 
    --                         2. Tem mais  de 3 meus produtos e não foi ofertada e tem pelo menos 1 registro de interesse na tabela de log; 
    --                         3. Tem menos de 3 meus produtos e não foi ofertada e tem pelo menos 1 registro de interesse na tabela de log:

    -- Não interessa: 1. Tem mais  de 3 meus produtos e não foi ofertada e tem apenas registros na tabela de log que não são de interesse;  
    --                2. Tem menos de 3 meus produtos e não foi ofertada e não tem nenhum registro na tabela de log;
    --                3. Tem menos de 3 meus produtos e não foi ofertada e tem apenas registros na tabela de log que não são de interesse:
    and (
          (    (select count(*) 
                  from dbo.Produto_Fornecedor pf
                       join dbo.Produto_Marca pm on pf.id_produto_marca_prdmca = pm.id_produto_marca_prdmca
                       join Produto_Cotacao   pc  on pc.id_cotacao_cot = ct.id_cotacao_cot
                                                 and pm.id_produto_prod = pc.id_produto_prod 
                 where pf.id_fornecedor_fornec = @idFornecedor
                   and pf.cd_produto_fornecedor_prdfor is not null
                   and pf.dt_desativacao_prdfor IS NULL
                 ) > 3
            and not exists (select 1 
                          from dbo.Log_Erro_Integracao
        	         where replace(cd_cotacao_logeint, ' (2)','')   = c.cd_cotacao_cot
		           and id_fornecedor_fornec = @idFornecedor
        	       )
          ) 
        or
          (    (select count(*) 
                  from dbo.Produto_Fornecedor pf
                       join dbo.Produto_Marca pm on pf.id_produto_marca_prdmca = pm.id_produto_marca_prdmca
                       join Produto_Cotacao   pc  on pc.id_cotacao_cot = ct.id_cotacao_cot
                                                 and pm.id_produto_prod = pc.id_produto_prod 
                 where pf.id_fornecedor_fornec = @idFornecedor
                   and pf.cd_produto_fornecedor_prdfor is not null
                   and pf.dt_desativacao_prdfor IS NULL
                 ) > 3
            and exists (select 1 
                          from dbo.Log_Erro_Integracao
        	         where replace(cd_cotacao_logeint, ' (2)','')   = c.cd_cotacao_cot
		           and id_fornecedor_fornec = @idFornecedor
                           and isnull(ds_ocorrencia_logeint, '') not like '%outra oferta ativa%'
                           and isnull(ds_ocorrencia_logeint, '') not like '%está fora dos padrões do mercado.'
                           and isnull(ds_ocorrencia_logeint, '') not like 'A Condição de Pagamento % informada no XML das ofertas não pode ser diferente da condição % definida obrigatoriamente na cotação%'
                           and isnull(ds_ocorrencia_logeint, '') not like 'O prazo de entrega, informado no sistema % do fornecedor, somado com a data de término da cotação%'
                           and isnull(ds_ocorrencia_logeint, '') not like 'Cotação % não ofertada! O CNPJ % da empresa compradora não foi encontrado no sistema%'
                           and isnull(ds_ocorrencia_logeint, '') not like 'O Código de Produto % do Fornecedor, informado na tela De-Para de Produtos no Portal Cronos, não existe no sistema%'
                           and isnull(ds_ocorrencia_logeint, '') not like 'Cotação % não ofertada! A empresa compradora % está bloqueada no sistema%'
                           and isnull(ds_ocorrencia_logeint, '') not like 'Cotação % não ofertada! A empresa compradora % foi desativada no sistema%'
        	       )
          ) 
        or
          (    (select count(*) 
                  from dbo.Produto_Fornecedor pf
                       join dbo.Produto_Marca pm on pf.id_produto_marca_prdmca = pm.id_produto_marca_prdmca
                       join Produto_Cotacao   pc  on pc.id_cotacao_cot = ct.id_cotacao_cot
                                                 and pm.id_produto_prod = pc.id_produto_prod 
                 where pf.id_fornecedor_fornec = @idFornecedor
                   and pf.cd_produto_fornecedor_prdfor is not null
                   and pf.dt_desativacao_prdfor IS NULL
                 ) <= 3
            and exists (select 1 
                          from dbo.Log_Erro_Integracao
        	         where replace(cd_cotacao_logeint, ' (2)','')   = c.cd_cotacao_cot
		           and id_fornecedor_fornec = @idFornecedor
                           and isnull(ds_ocorrencia_logeint, '') not like '%outra oferta ativa%'
                           and isnull(ds_ocorrencia_logeint, '') not like '%está fora dos padrões do mercado.'
                           and isnull(ds_ocorrencia_logeint, '') not like 'A Condição de Pagamento % informada no XML das ofertas não pode ser diferente da condição % definida obrigatoriamente na cotação%'
                           and isnull(ds_ocorrencia_logeint, '') not like 'O prazo de entrega, informado no sistema % do fornecedor, somado com a data de término da cotação%'
                           and isnull(ds_ocorrencia_logeint, '') not like 'Cotação % não ofertada! O CNPJ % da empresa compradora não foi encontrado no sistema%'
                           and isnull(ds_ocorrencia_logeint, '') not like 'O Código de Produto % do Fornecedor, informado na tela De-Para de Produtos no Portal Cronos, não existe no sistema%'
                           and isnull(ds_ocorrencia_logeint, '') not like 'Cotação % não ofertada! A empresa compradora % está bloqueada no sistema%'
                           and isnull(ds_ocorrencia_logeint, '') not like 'Cotação % não ofertada! A empresa compradora % foi desativada no sistema%'
        	       )
        ) 
      )
        INNER JOIN      dbo.Fornecedor         as fo   on fo.id_fornecedor_fornec = @idFornecedor
        INNER JOIN      dbo.pessoa             as forn on forn.id_pessoa = fo.id_pessoa
        INNER JOIN      dbo.Comprador          as co   on co.id_comprador_compr = c.id_comprador_compr
        INNER JOIN      dbo.pessoa             as comp on comp.id_pessoa = co.id_pessoa 
        LEFT JOIN      dbo.Log_Erro_Integracao as lei  on replace(lei.cd_cotacao_logeint, ' (2)','')   = c.cd_cotacao_cot
	                                               and lei.id_fornecedor_fornec = @idFornecedor
                                                       and isnull(lei.ds_ocorrencia_logeint, '') not like '%outra oferta ativa%'
                                                       and isnull(lei.ds_ocorrencia_logeint, '') not like '%está fora dos padrões do mercado.'
                                                       and isnull(lei.ds_ocorrencia_logeint, '') not like 'A Condição de Pagamento % informada no XML das ofertas não pode ser diferente da condição % definida obrigatoriamente na cotação%'
                                                       and isnull(lei.ds_ocorrencia_logeint, '') not like 'O prazo de entrega, informado no sistema % do fornecedor, somado com a data de término da cotação%'
                                                       and isnull(lei.ds_ocorrencia_logeint, '') not like 'Cotação % não ofertada! O CNPJ % da empresa compradora não foi encontrado no sistema%'
                                                       and isnull(lei.ds_ocorrencia_logeint, '') not like 'O Código de Produto % do Fornecedor, informado na tela De-Para de Produtos no Portal Cronos, não existe no sistema%'
                                                       and isnull(lei.ds_ocorrencia_logeint, '') not like 'Cotação % não ofertada! A empresa compradora % está bloqueada no sistema%'
                                                       and isnull(lei.ds_ocorrencia_logeint, '') not like 'Cotação % não ofertada! A empresa compradora % foi desativada no sistema%'
end
else
begin

-- Problemas a serem listados no SQL Management Studio:

SELECT  forn.nm_pessoa  as nm_fornecedor,
        comp.nm_pessoa  as nm_comprador,
        c.cd_cotacao_cot,
        isnull(lei.ds_ocorrencia_logeint, '') as Erro,
        (select count(*)
                  from dbo.Produto_Fornecedor pf
                       join dbo.Produto_Marca pm on pf.id_produto_marca_prdmca = pm.id_produto_marca_prdmca
                       join Produto_Cotacao   pc  on pc.id_cotacao_cot = ct.id_cotacao_cot
                                                 and pm.id_produto_prod = pc.id_produto_prod 
                 where pf.id_fornecedor_fornec = @idFornecedor
                   and pf.cd_produto_fornecedor_prdfor is not null
                   and pf.dt_desativacao_prdfor IS NULL
               ) as QtdMeusProdutos,
       (SELECT  isnull(icf.qt_envio_cotacao_icotfor,0)
          FROM  dbo.Integracao_Cotacao_Fornecedor icf
          where icf.id_cotacao_cot = ct.id_cotacao_cot
          and   icf.id_fornecedor_fornec = @idFornecedor
               ) as QtdTentativas,
        c.dt_cadastro_cot,
        c.dt_hr_fim_vigencia_cot,
        c.id_cotacao_cot
  FROM                  #Cotacoes              as ct   
        INNER JOIN      dbo.Cotacao            as c    on c.id_cotacao_cot = ct.id_cotacao_cot
                                                      and ct.id_status_cotacao_stcot = 4
                                                    --and c.sn_cond_pagto_obrigatoria_cot = 0
                                                      and c.dt_hr_inicio_vigencia_cot < @fim
    and (select COUNT (distinct ofc.id_produto_cotacao_prdcot) 
           from Fornecedor_Prod_Marca_Cotacao            ofc 
                INNER JOIN      dbo.Fornecedor_Cotacao    fc    on fc.id_cotacao_cot = c.id_cotacao_cot
                                                               and fc.id_fornecedor_fornec = @idFornecedor
                                                               and ofc.id_fornecedor_cotacao_forcot = fc.id_fornecedor_cotacao_forcot 
                INNER JOIN      dbo.Usuario_Sistema       ws    on ws.id_perfil_acesso_perace = 12 -- Web service
                                                               and ofc.user_id_ususis = ws.user_id
          where ofc.id_produto_cotacao_prdcot in (select pc.id_produto_cotacao_prdcot 
                                                    from Produto_Cotacao pc 
                                                   where pc.id_cotacao_cot = ct.id_cotacao_cot)
        ) = 0
    and not exists (select 1 
                      from dbo.Log_Erro_Integracao
     	             where replace(cd_cotacao_logeint, ' (2)','')   = c.cd_cotacao_cot
		       and id_fornecedor_fornec = @idFornecedor
                       and isnull(ds_ocorrencia_logeint, '') like '%outra oferta ativa%'
        	   )
    and not exists (select 1 
                      from dbo.Log_Erro_Integracao
     	             where replace(cd_cotacao_logeint, ' (2)','')   = c.cd_cotacao_cot
		       and id_fornecedor_fornec = @idFornecedor
                       and isnull(ds_ocorrencia_logeint, '') like 'Cotação % não ofertada! A empresa compradora % foi desativada no sistema%'
        	   )
    -- e não existem apenas mensagens desinteressantes com preços fora dos padrões do mercado sem existir outros tipos de mensagens:
    and (
          ( 
		   (select count(*) 
                      from dbo.Log_Erro_Integracao
     	             where replace(cd_cotacao_logeint, ' (2)','')   = c.cd_cotacao_cot
		       and id_fornecedor_fornec = @idFornecedor
                       and isnull(ds_ocorrencia_logeint, '') like '%está fora dos padrões do mercado.'
                   ) > 0
               and (select count(*) 
                      from dbo.Log_Erro_Integracao
     	             where replace(cd_cotacao_logeint, ' (2)','')   = c.cd_cotacao_cot
		       and id_fornecedor_fornec = @idFornecedor
                       and isnull(ds_ocorrencia_logeint, '') not like '%está fora dos padrões do mercado.'
                   ) > 0
          )
        or
          (
		   (select count(*) 
                      from dbo.Log_Erro_Integracao
     	             where replace(cd_cotacao_logeint, ' (2)','')   = c.cd_cotacao_cot
		       and id_fornecedor_fornec = @idFornecedor
                       and isnull(ds_ocorrencia_logeint, '') like '%está fora dos padrões do mercado.'
                   ) = 0
          )
       )
    and exists (select 1 
                  from dbo.Produto_Fornecedor pf
                       join dbo.Produto_Marca pm on pf.id_produto_marca_prdmca = pm.id_produto_marca_prdmca
                       join Produto_Cotacao   pc  on pc.id_cotacao_cot = ct.id_cotacao_cot
                                                 and pm.id_produto_prod = pc.id_produto_prod 
                 where pf.id_fornecedor_fornec = @idFornecedor
                   and pf.cd_produto_fornecedor_prdfor is not null
                   and pf.dt_desativacao_prdfor IS NULL
               )
        INNER JOIN      dbo.Fornecedor         as fo   on fo.id_fornecedor_fornec = @idFornecedor
        INNER JOIN      dbo.pessoa             as forn on forn.id_pessoa = fo.id_pessoa
        INNER JOIN      dbo.Comprador          as co   on co.id_comprador_compr = c.id_comprador_compr
        INNER JOIN      dbo.pessoa             as comp on comp.id_pessoa = co.id_pessoa 
        LEFT JOIN       dbo.Log_Erro_Integracao as lei on replace(lei.cd_cotacao_logeint, ' (2)','')   = c.cd_cotacao_cot
	                                               and lei.id_fornecedor_fornec = @idFornecedor
                                                       and isnull(lei.ds_ocorrencia_logeint, '') not like '%outra oferta ativa%'
                                                       and isnull(lei.ds_ocorrencia_logeint, '') not like '%está fora dos padrões do mercado.'
                                                       and isnull(lei.ds_ocorrencia_logeint, '') not like 'Cotação % não ofertada! A empresa compradora % foi desativada no sistema%'
                                                       -- Empresas bloqueadas são de interesse pois de vez em quando a fornecedora se esqueça desbloquear uma compradora indevidamente 
  order by c.dt_cadastro_cot desc
end

--print convert(varchar,@ini,113)
--print convert(varchar,@fim,113)

drop table #CotacoesTemp
drop table #Cotacoes

/*

Usar o seguinte na sp getCotacoesFornecedorMaster : 

if isnull(@ids_status, '') <> ''
  begin
    drop table #ids_status
  end

if isnull(@ids_ufs, '') <> ''
  begin
    drop table #ids_ufs
  end

*/


GO


