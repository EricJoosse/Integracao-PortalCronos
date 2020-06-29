select  id_sistema_integrado_sisint
  from  dbo.Sistema_Integrado
 where  upper(sg_sistema_integrado_sisint) = upper('APS', 'WinThor')

select ds_email_pessoa from dbo.Pessoa where ds_email_pessoa is not null

select  u.user_id, u.cd_usuario_sistema_ususis, u.id_pessoa
  from  dbo.Usuario_Sistema u
        inner join dbo.Pessoa p on p.id_pessoa = u.id_pessoa
 where  lower(p.ds_email_pessoa) = lower('eric.jo@bol.com.br')

-- Apenas executar na base de teste para limpar emails lixo:
update dbo.Usuario_Sistema set id_pessoa = 1 where user_id in (2274, 2354)

