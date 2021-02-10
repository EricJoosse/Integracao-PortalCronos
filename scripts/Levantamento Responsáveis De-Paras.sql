select  pf.user_id_ususis
     ,  u.cd_usuario_sistema_ususis
	 ,  u.id_perfil_acesso_perace
     ,  pf.dt_alteracao_prdfor
	 ,  pa.ds_perfil_acesso_perace
	 ,  pe.ds_email_pessoa
  from  dbo.Produto_Fornecedor pf
        inner join dbo.Usuario_Sistema u on u.user_id = pf.user_id_ususis and u.dt_desativacao_ususis is null
		inner join dbo.Perfil_Acesso pa on pa.id_perfil_acesso_perace = u.id_perfil_acesso_perace
		inner join dbo.Pessoa pe on pe.id_pessoa = u.id_pessoa and pe.dt_desativacao_pessoa is null
  where pf.id_fornecedor_fornec = 33
    and pf.dt_desativacao_prdfor is null
order by pf.dt_alteracao_prdfor desc

/*
select  count(*)
     ,  u.cd_usuario_sistema_ususis
  from  dbo.Produto_Fornecedor pf
        inner join dbo.Usuario_Sistema u on u.user_id = pf.user_id_ususis and u.dt_desativacao_ususis is null
		inner join dbo.Perfil_Acesso pa on pa.id_perfil_acesso_perace = u.id_perfil_acesso_perace
		inner join dbo.Pessoa pe on pe.id_pessoa = u.id_pessoa and pe.dt_desativacao_pessoa is null
  where pf.id_fornecedor_fornec = 33
    and pf.dt_desativacao_prdfor is null
group by u.cd_usuario_sistema_ususis
order by count(*) desc


33   Comal            
1    Atacamax   
170  SOST        
171  Propão     
1995 Marizpan   
13   Formaggio			paula@formaggio.com.br	167
14   Padeirão            
947  JR Distribuição          
21   Marítimos         
60   Karne Keijo       
30   Prolac     
23	 Ingá Vinhos		 

*/
