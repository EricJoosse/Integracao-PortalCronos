USE [PCronos_Producao]
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create procedure [dbo].[historicoErrosIntegracaoRadical] (
    @idFornecedor int
) as

/*
select count(*) from [dbo].[Log_Erro_Integracao]
select count(*) from [dbo].[Log_Erro_Integracao_Historico]
exec [dbo].[historicoErrosIntegracaoRadical] @idFornecedor = 947
select count(*) from [dbo].[Log_Erro_Integracao]
select count(*) from [dbo].[Log_Erro_Integracao_Historico]
*/

SET TRANSACTION ISOLATION LEVEL SNAPSHOT

INSERT INTO [dbo].[Log_Erro_Integracao_Historico]
           ([id_comprador_compr]
           ,[id_fornecedor_fornec]
           ,[id_requisicao_entr_int_reqei]
           ,[id_cotacao_cot]
           ,[id_ordem_compra_ordcom]
           ,[dt_hr_ocorrencia_logeihist]
           ,[ds_ocorrencia_logeihist]
           ,[dt_solucao_ocorrencia_logeihist]
           ,[sn_aviso_ocorrencia_logeihist]
           ,[cd_requisicao_logeihist]
           ,[cd_cotacao_logeihist]
           ,[cd_ordem_compra_logeihist]
           ,[cd_produto_logeihist]
           ,[ds_produto_logeihist]
           ,[ds_unid_produto_logeihist]
           ,[cd_produto_marca_logeihist]
           ,[cd_marca_logeihist]
           ,[qt_produto_logeihist]
           ,[vl_previsto_produto_logeihist]
           ,[ds_observacao_produto_logeihist]
           ,[sn_ignorar_vl_previsto_produto_logeihist]
           ,[tp_erro_ocorrencia_logeihist])
SELECT [id_comprador_compr]
      ,[id_fornecedor_fornec]
      ,[id_requisicao_entr_int_reqei]
      ,[id_cotacao_cot]
      ,[id_ordem_compra_ordcom]
      ,[dt_hr_ocorrencia_logeint]
      ,[ds_ocorrencia_logeint]
      ,[dt_solucao_ocorrencia_logeint]
      ,[sn_aviso_ocorrencia_logeint]
      ,[cd_requisicao_logeint]
      ,[cd_cotacao_logeint]
      ,[cd_ordem_compra_logeint]
      ,[cd_produto_logeint]
      ,[ds_produto_logeint]
      ,[ds_unid_produto_logeint]
      ,[cd_produto_marca_logeint]
      ,[cd_marca_logeint]
      ,[qt_produto_logeint]
      ,[vl_previsto_produto_logeint]
      ,[ds_observacao_produto_logeint]
      ,[sn_ignorar_vl_previsto_produto_logeint]
      ,[tp_erro_ocorrencia_logeint]
  FROM [dbo].[Log_Erro_Integracao]
 where  id_fornecedor_fornec = @idFornecedor

delete from [dbo].[Log_Erro_Integracao]
 where id_fornecedor_fornec = @idFornecedor
