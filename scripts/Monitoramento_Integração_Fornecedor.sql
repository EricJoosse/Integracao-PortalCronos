exec dbo.monitorarIntegracaoFornecedores @incremental_15min = 0
exec dbo.monitorarIntegracaoFornecedores @incremental_15min = 1


Formaggio 		13
JR Embalagem	947	
Prolac 			30
Marítimos		21
Karne Keijo		60
Comal 			33
SOST (BA)		170
Walmart        	385
Propão			171
Padeirão		14
Marizpan	    1995
Atacamax	    1

/*
select id_fornecedor_fornec, *
  from [dbo].[Usuario_Sistema]  
  where cd_usuario_sistema_ususis = 'ericcoringa'

 update [dbo].[Usuario_Sistema]  
    set id_fornecedor_fornec = 13
  where cd_usuario_sistema_ususis = 'leaof2mobile'




update [dbo].[Usuario_Sistema]  
    set id_fornecedor_fornec = 1995
      , id_perfil_acesso_perace = 14
      , id_central_compra_ctrcom = null
      , id_comprador_compr = null
  where cd_usuario_sistema_ususis = 'ericcoringa'

 -- Voltar:
update [dbo].[Usuario_Sistema]  
    set id_fornecedor_fornec = null
      , id_perfil_acesso_perace = 1
      , id_central_compra_ctrcom = 1
      , id_comprador_compr = null
  where cd_usuario_sistema_ususis = 'ericcoringa'

*/

select c.id_comprador_compr
     , pe.nm_pessoa
  from dbo.Comprador c
       inner join dbo.Pessoa pe on pe.id_pessoa = c.id_pessoa
 where c.id_pessoa in (select  id_pessoa
                        from  dbo.Pessoa
                       where  nm_pessoa like '%Papacapim%')
-> 513

update [dbo].[Usuario_Sistema]  
    set id_fornecedor_fornec = null
      , id_perfil_acesso_perace = 4 -- Gerente Compras
      , id_central_compra_ctrcom = null
      , id_comprador_compr = 513
	  , dt_desativacao_ususis = null
  where cd_usuario_sistema_ususis = 'ericcoringa'
