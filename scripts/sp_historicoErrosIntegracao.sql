USE [PCronos_Producao]
GO


if object_id('historicoErrosIntegracao') is not NULL
  drop procedure [dbo].[historicoErrosIntegracao]
go


SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create procedure [dbo].[historicoErrosIntegracao]
as

/*
select count(*) from [dbo].[Log_Erro_Integracao]
select count(*) from [dbo].[Log_Erro_Integracao_Historico]
exec [dbo].[historicoErrosIntegracao]
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
  where (      id_fornecedor_fornec is not null
		   and (   cd_cotacao_logeint is null
		      --OR id_fornecedor_fornec = 947
		        OR replace(cd_cotacao_logeint, ' (2)','') in 
							      (select     c.cd_cotacao_cot
									  FROM  dbo.Historico_Status_Cotacao as HIST 
									        INNER JOIN (SELECT H.id_cotacao_cot, MAX(H.dt_status_cotacao_htstcot) as dt_status_cotacao_htstcot 
									                      FROM dbo.Historico_Status_Cotacao H GROUP by id_cotacao_cot) as TEMP_TABLE 
									                   ON HIST.dt_status_cotacao_htstcot = TEMP_TABLE.dt_status_cotacao_htstcot AND HIST.id_cotacao_cot = TEMP_TABLE.id_cotacao_cot 
									        INNER JOIN      dbo.Cotacao            as c    on c.id_cotacao_cot = HIST.id_cotacao_cot
									   where HIST.id_status_cotacao_stcot in (5,7,9)
									     and c.dt_hr_fim_vigencia_cot < DATEADD("DAY", -23, getdate())
								  )
		       ) 
       )
       OR
       (     id_comprador_compr is not null
         and cd_requisicao_logeint is not null
         and cd_requisicao_logeint in (select req.cd_requisicao_reqei collate Latin1_General_CI_AS
                                          from dbo.Requisicao_Entrada_Integra req
										        inner join dbo.Historico_Status_Requisicao hsr on hsr.id_requisicao_entr_int_reqei = req.id_requisicao_entr_int_reqei
										                                                      AND hsr.id_hist_status_requisicao_htstreq = (SELECT TOP 1 h2.id_hist_status_requisicao_htstreq
																										                                     FROM Historico_Status_Requisicao h2
																										                                    WHERE h2.id_requisicao_entr_int_reqei = hsr.id_requisicao_entr_int_reqei
																										                                    ORDER BY h2.dt_status_requisicao_htstreq DESC) 
										                                                      AND hsr.id_status_requisicao_streq in (4, 5)
                                         where req.dt_cadastro_reqei < DATEADD("DAY", -30, getdate())
                                       )
       )
       OR
       (     id_comprador_compr is not null
         and id_ordem_compra_ordcom is not null
         and id_ordem_compra_ordcom in (select oc.id_ordem_compra_ordcom
                                           from dbo.Ordem_Compra oc
                                           where oc.dt_cadastro_ordcom < DATEADD("DAY", -30, getdate())
                                        )
       )

       
delete from [dbo].[Log_Erro_Integracao]
 where (       id_fornecedor_fornec is not null
		   and (   cd_cotacao_logeint is null
		      --OR id_fornecedor_fornec = 947
		        OR replace(cd_cotacao_logeint, ' (2)','') in 
						      (select     c.cd_cotacao_cot
								  FROM  dbo.Historico_Status_Cotacao as HIST 
								        INNER JOIN (SELECT H.id_cotacao_cot, MAX(H.dt_status_cotacao_htstcot) as dt_status_cotacao_htstcot 
								                      FROM dbo.Historico_Status_Cotacao H GROUP by id_cotacao_cot) as TEMP_TABLE 
								                   ON HIST.dt_status_cotacao_htstcot = TEMP_TABLE.dt_status_cotacao_htstcot AND HIST.id_cotacao_cot = TEMP_TABLE.id_cotacao_cot 
								        INNER JOIN      dbo.Cotacao            as c    on c.id_cotacao_cot = HIST.id_cotacao_cot
								   where HIST.id_status_cotacao_stcot in (5,7,9)
								     and c.dt_hr_fim_vigencia_cot < DATEADD("DAY", -23, getdate())
							  )
		       )
       )
       OR
       (     id_comprador_compr is not null
         and cd_requisicao_logeint is not null
         and cd_requisicao_logeint in (select req.cd_requisicao_reqei collate Latin1_General_CI_AS
                                          from dbo.Requisicao_Entrada_Integra req
										        inner join dbo.Historico_Status_Requisicao hsr on hsr.id_requisicao_entr_int_reqei = req.id_requisicao_entr_int_reqei
										                                                      AND hsr.id_hist_status_requisicao_htstreq = (SELECT TOP 1 h2.id_hist_status_requisicao_htstreq
																										                                     FROM Historico_Status_Requisicao h2
																										                                    WHERE h2.id_requisicao_entr_int_reqei = hsr.id_requisicao_entr_int_reqei
																										                                    ORDER BY h2.dt_status_requisicao_htstreq DESC) 
										                                                      AND hsr.id_status_requisicao_streq in (4, 5)
                                         where req.dt_cadastro_reqei < DATEADD("DAY", -30, getdate())
                                       )
       )
       OR
       (     id_comprador_compr is not null
         and id_ordem_compra_ordcom is not null
         and id_ordem_compra_ordcom in (select oc.id_ordem_compra_ordcom
                                           from dbo.Ordem_Compra oc
                                           where oc.dt_cadastro_ordcom < DATEADD("DAY", -30, getdate())
                                        )
       )
