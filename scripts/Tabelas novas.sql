if object_id('Configuracao_Instalador_Integrador') is not NULL
  drop table [dbo].[Configuracao_Instalador_Integrador]
go

if object_id('Configuracao_Monitorador_Integradores') is not NULL
  drop table [dbo].[Configuracao_Monitorador_Integradores]
go

if object_id('Configuracao_Instalador_Integrador_Nuvem') is not NULL
  drop table [dbo].[Configuracao_Instalador_Integrador_Nuvem]
go


SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

/*
update   [dbo].[Sistema_Integrado]
   set   sg_sistema_integrado_sisint = 'WinThor'
   where sg_sistema_integrado_sisint = 'WINTHOR'

update   [dbo].[Sistema_Integrado]
   set   sg_sistema_integrado_sisint = 'PCronos'
   where sg_sistema_integrado_sisint = 'PCRONOS'


INSERT INTO [dbo].[Sistema_Integrado]
           ([sg_sistema_integrado_sisint]
           ,[nm_sistema_integrado_sisint]
           ,[dt_cadastro_sisint]
           ,[dt_desativacao_sisint]
           ,[dt_alteracao_sisint]
           ,[user_id_ususis])
     VALUES
           ('SAP'
           ,'SAP'
           ,'2017-08-02 10:31:00.000'
           ,null
           ,'2017-08-02 10:31:00.000'
           ,1)
*/
           

create table [dbo].[Configuracao_Instalador_Integrador_Nuvem](
	[id_config_instalador_integrador_nuvem_ciintnuv] [int] IDENTITY(1,1) NOT NULL,
	[id_sistema_integrado_sisint] [int] NOT NULL,
	tipo_sist_operacional_ciintnuv varchar(30) NOT NULL,
	sist_operacional_32_ou_64_bit_ciintnuv varchar(6) NOT NULL,
	espaco_livre_disco_ciintnuv varchar(10) NOT NULL,
	memoria_ram_livre_ciintnuv varchar(10) NOT NULL,
	versao_jre_ciintnuv varchar(15) NOT NULL,
	tipo_jre_ciintnuv varchar(10) NOT NULL,
	versao_integrador_ciintnuv varchar(10) NOT NULL,
	disco_integrador_ciintnuv char NOT NULL,
	dir_programfiles_ciintnuv varchar(30) NOT NULL,
	endereco_ip_publico_servidor_ciintnuv varchar(30) NOT NULL,
	porta_ip_aberta_ciintnuv varchar(15) NOT NULL,
	frequencia_processamento_ciintnuv varchar(10) NOT NULL,

	apelido_contato_ti_nuvem_ciintnuv varchar(15) NOT NULL,
	email_contato_ti_nuvem_ciintnuv varchar(30) NOT NULL,
	skype_contato_ti_nuvem_ciintnuv varchar(30) NOT NULL,
	telefone_contato_ti_Nuvem_ciintnuv varchar(30) NOT NULL,

	apelido_contato_ti_secundario_nuvem_ciintnuv varchar(15) NULL,
	email_contato_ti_secundario_nuvem_ciintnuv varchar(30) NULL,
	skype_contato_ti_secundario_nuvem_ciintnuv varchar(30) NULL,
	telefone_contato_ti_secundario_Nuvem_ciintnuv varchar(30) NULL,

	[dt_cadastro_ciintnuv] [datetime] NULL,
	[dt_desativacao_ciintnuv] [datetime] NULL,
	[dt_alteracao_ciintnuv] [datetime] NULL,
	[user_id_ususis] [int] NOT NULL,
CONSTRAINT [PK_CIINTNUV] PRIMARY KEY CLUSTERED 
(
	[id_config_instalador_integrador_nuvem_ciintnuv] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY],
 CONSTRAINT [UK_CIINTNUV_SISINT] UNIQUE NONCLUSTERED 
(
	[id_sistema_integrado_sisint] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]


ALTER TABLE [dbo].[Configuracao_Instalador_Integrador_Nuvem]  WITH CHECK ADD  CONSTRAINT [FK_CIINTNUV_SISINT] FOREIGN KEY([id_sistema_integrado_sisint])
REFERENCES [dbo].[Sistema_Integrado] ([id_sistema_integrado_sisint])
GO
ALTER TABLE [dbo].[Configuracao_Instalador_Integrador_Nuvem] CHECK CONSTRAINT [FK_CIINTNUV_SISINT]
GO


ALTER TABLE [dbo].[Configuracao_Instalador_Integrador_Nuvem]  WITH CHECK ADD  CONSTRAINT [FK_CIINTNUV_USUSIS] FOREIGN KEY([user_id_ususis])
REFERENCES [dbo].[Usuario_Sistema] ([user_id])
GO
ALTER TABLE [dbo].[Configuracao_Instalador_Integrador_Nuvem] CHECK CONSTRAINT [FK_CIINTNUV_USUSIS]
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Cada fornecedor no servidor nuvem tem outro padrão de integração.' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Configuracao_Instalador_Integrador_Nuvem', @level2type=N'COLUMN',@level2name=N'id_sistema_integrado_sisint'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Serve para o conteúdo dos emails automáticos pelo Monitorador.' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Configuracao_Instalador_Integrador_Nuvem', @level2type=N'COLUMN',@level2name=N'apelido_contato_ti_nuvem_ciintnuv'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Serve para o conteúdo dos emails automáticos pelo Monitorador.' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Configuracao_Instalador_Integrador_Nuvem', @level2type=N'COLUMN',@level2name=N'apelido_contato_ti_secundario_nuvem_ciintnuv'
GO

create table [dbo].[Configuracao_Monitorador_Integradores](
	[id_config_monitorador_integradores_conmonint] [int] IDENTITY(1,1) NOT NULL,
	[id_fornecedor_fornec] [int] NOT NULL,
	id_vendedor_responsavel_integracao_ususis int NULL,
	sn_em_producao_conmonint bit NOT NULL,

    apelido_contato_ti_conmonint varchar(15) NOT NULL,
	email_contato_ti_conmonint varchar(30) NOT NULL,
	skype_contato_ti_conmonint varchar(30) NOT NULL,
	telefone_contato_ti_conmonint varchar(30) NOT NULL,
	funcao_contato_ti_conmonint varchar(30) NULL,

	apelido_contato_ti_secundario_conmonint varchar(15) NULL,
	email_contato_ti_secundario_conmonint varchar(30) NULL,
	skype_contato_ti_secundario_conmonint  varchar(30) NULL,
	telefone_contato_ti_secundario_conmonint varchar(30) NOT NULL,
	funcao_contato_ti_secundario_conmonint varchar(30) NULL,

	aplicativo_desktop_remoto_conmonint varchar(30) NOT NULL,
	id_aplicativo_desktop_remoto_conmonint varchar(30) NOT NULL,
	[dt_cadastro_conmonint] [datetime] NULL,
	[dt_desativacao_conmonint] [datetime] NULL,
	[dt_alteracao_conmonint] [datetime] NULL,
	[user_id_ususis] [int] NOT NULL,
CONSTRAINT [PK_CONMONINT] PRIMARY KEY CLUSTERED 
(
	[id_config_monitorador_integradores_conmonint] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY],
 CONSTRAINT [UK_CONMONINT_FORNEC] UNIQUE NONCLUSTERED 
(
	[id_fornecedor_fornec] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]


ALTER TABLE [dbo].[Configuracao_Monitorador_Integradores]  WITH CHECK ADD  CONSTRAINT [FK_CONMONINT_FORNEC] FOREIGN KEY([id_fornecedor_fornec])
REFERENCES [dbo].[Fornecedor] ([id_fornecedor_fornec])
GO
ALTER TABLE [dbo].[Configuracao_Monitorador_Integradores] CHECK CONSTRAINT [FK_CONMONINT_FORNEC]
GO


ALTER TABLE [dbo].[Configuracao_Monitorador_Integradores]  WITH CHECK ADD  CONSTRAINT [FK_CONMONINT_USUSIS] FOREIGN KEY([user_id_ususis])
REFERENCES [dbo].[Usuario_Sistema] ([user_id])
GO
ALTER TABLE [dbo].[Configuracao_Monitorador_Integradores] CHECK CONSTRAINT [FK_CONMONINT_USUSIS]
GO


ALTER TABLE [dbo].[Configuracao_Monitorador_Integradores]  WITH CHECK ADD  CONSTRAINT [FK_CONMONINT_RESP_USUSIS] FOREIGN KEY([id_vendedor_responsavel_integracao_ususis])
REFERENCES [dbo].[Usuario_Sistema] ([user_id])
GO
ALTER TABLE [dbo].[Configuracao_Monitorador_Integradores] CHECK CONSTRAINT [FK_CONMONINT_RESP_USUSIS]
GO



EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'A sigla do sistema do fornecedor pode ser obtido pelo Padrao_Integracao do Fornecedor.' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Configuracao_Monitorador_Integradores', @level2type=N'COLUMN',@level2name=N'id_fornecedor_fornec'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Vendedor responsável para a manutenção dos De-Para´s, etc.' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Configuracao_Monitorador_Integradores', @level2type=N'COLUMN',@level2name=N'id_vendedor_responsavel_integracao_ususis'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Em produção ou não no lado do fornecedor (no servidor próprio dele).' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Configuracao_Monitorador_Integradores', @level2type=N'COLUMN',@level2name=N'sn_em_producao_conmonint'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Serve para o conteúdo dos emails automáticos.' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Configuracao_Monitorador_Integradores', @level2type=N'COLUMN',@level2name=N'apelido_contato_ti_conmonint'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Serve para o conteúdo dos emails automáticos.' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Configuracao_Monitorador_Integradores', @level2type=N'COLUMN',@level2name=N'apelido_contato_ti_secundario_conmonint'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Na maioria dos fornecedores o ID do AnyDesk nunca muda.' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Configuracao_Monitorador_Integradores', @level2type=N'COLUMN',@level2name=N'id_aplicativo_desktop_remoto_conmonint'
GO



create table [dbo].[Configuracao_Instalador_Integrador](
	[id_config_instalador_integrador_coninsint] [int] IDENTITY(1,1) NOT NULL,
	[id_fornecedor_fornec] [int] NOT NULL,
	[id_sistema_integrado_sisint] [int] NOT NULL,
	[id_config_instalador_integrador_nuvem_ciintnuv] [int] NULL,
	nr_sequencia_instancia_nuvem_coninsint int NULL,
	usuario_webservice_coninsint varchar(30) NOT NULL,
	tipo_sist_operacional_coninsint varchar(30) NOT NULL,
	sist_operacional_32_ou_64_bit_coninsint varchar(6) NOT NULL,
	espaco_livre_disco_coninsint varchar(10) NOT NULL,
	memoria_ram_livre_coninsint varchar(10) NOT NULL,
	versao_jre_coninsint varchar(15) NOT NULL,
	tipo_jre_coninsint varchar(10) NOT NULL,
	versao_integrador_coninsint varchar(10) NOT NULL,
	disco_integrador_coninsint char NOT NULL,
	dir_programfiles_coninsint varchar(30) NOT NULL,
	endereco_ip_publico_servidor_coninsint varchar(30) NOT NULL,
	porta_ip_aberta_coninsint varchar(15) NOT NULL,
	frequencia_processamento_coninsint varchar(10) NOT NULL,
	sn_debug_ativado_coninsint bit NOT NULL,
	qtd_dias_arquivos_xml_guardados_coninsint int NOT NULL,
	[dt_cadastro_coninsint] [datetime] NULL,
	[dt_desativacao_coninsint] [datetime] NULL,
	[dt_alteracao_coninsint] [datetime] NULL,
	[user_id_ususis] [int] NOT NULL,
CONSTRAINT [PK_CONINSINT] PRIMARY KEY CLUSTERED 
(
	[id_config_instalador_integrador_coninsint] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY],
 CONSTRAINT [UK_CONINSINT_FORNEC] UNIQUE NONCLUSTERED 
(
	[id_fornecedor_fornec] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO


CREATE UNIQUE INDEX UK_CONINSINT_SEQ_NUVEM 
ON dbo.Configuracao_Instalador_Integrador(
	[id_config_instalador_integrador_nuvem_ciintnuv] ASC,
	nr_sequencia_instancia_nuvem_coninsint ASC
)
WHERE id_config_instalador_integrador_nuvem_ciintnuv is not null
	  AND nr_sequencia_instancia_nuvem_coninsint is not null
GO


ALTER TABLE [dbo].[Configuracao_Instalador_Integrador]  WITH CHECK ADD  CONSTRAINT [FK_CONINSINT_SISINT] FOREIGN KEY([id_sistema_integrado_sisint])
REFERENCES [dbo].[Sistema_Integrado] ([id_sistema_integrado_sisint])
GO
ALTER TABLE [dbo].[Configuracao_Instalador_Integrador] CHECK CONSTRAINT [FK_CONINSINT_SISINT]
GO


ALTER TABLE [dbo].[Configuracao_Instalador_Integrador]  WITH CHECK ADD  CONSTRAINT [FK_CONINSINT_USUSIS] FOREIGN KEY([user_id_ususis])
REFERENCES [dbo].[Usuario_Sistema] ([user_id])
GO
ALTER TABLE [dbo].[Configuracao_Instalador_Integrador] CHECK CONSTRAINT [FK_CONINSINT_USUSIS]
GO


ALTER TABLE [dbo].[Configuracao_Instalador_Integrador]  WITH CHECK ADD  CONSTRAINT [FK_CONINSINT_FORNEC] FOREIGN KEY([id_fornecedor_fornec])
REFERENCES [dbo].[Fornecedor] ([id_fornecedor_fornec])
GO
ALTER TABLE [dbo].[Configuracao_Instalador_Integrador] CHECK CONSTRAINT [FK_CONINSINT_FORNEC]
GO


ALTER TABLE [dbo].[Configuracao_Instalador_Integrador]  WITH CHECK ADD  CONSTRAINT [FK_CONINSINT_CIINTNUV] FOREIGN KEY([id_config_instalador_integrador_nuvem_ciintnuv])
REFERENCES [dbo].[Configuracao_Instalador_Integrador_Nuvem] ([id_config_instalador_integrador_nuvem_ciintnuv])
GO
ALTER TABLE [dbo].[Configuracao_Instalador_Integrador] CHECK CONSTRAINT [FK_CONINSINT_CIINTNUV]
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Na hora da instalação do lado do fornecedor ainda não existe um padrão de integração em produção.' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Configuracao_Instalador_Integrador', @level2type=N'COLUMN',@level2name=N'id_sistema_integrado_sisint'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Debug no lado do fornecedor (no servidor local dele + debug remoto).' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Configuracao_Instalador_Integrador', @level2type=N'COLUMN',@level2name=N'sn_debug_ativado_coninsint'
GO
		

