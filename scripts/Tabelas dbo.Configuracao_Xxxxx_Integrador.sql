if object_id('Configuracao_Instalador_Integrador') is not NULL
  drop table [dbo].[Configuracao_Instalador_Integrador]
go

if object_id('Configuracao_Monitorador_Integradores') is not NULL
  drop table [dbo].[Configuracao_Monitorador_Integradores]
go


SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

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

           
.....subst conint em todas as colunas........

create table [dbo].[Configuracao_Monitorador_Integradores](
	[id_conf_monitorador_integradores_conmonint] [int] IDENTITY(1,1) NOT NULL,
	[id_fornecedor_fornec] [int] NOT NULL,
			Descricao da coluna: NomeFantasiaEmpresa, cnpjFornecedor, SiglaSistemaFornecedor (pelo Padrao_Integracao do Fornecedor)
	id_vendedor_responsavel_integracao_ususis int NULL,
	sn_em_producao_conint bit NOT NULL,
	apelido_email_responsavel_ti_conint varchar(15) NOT NULL,
	email_responsavel_ti_conint varchar(30) NOT NULL,
	skype_responsavel_ti_conint varchar(30) NOT NULL,
	telefone_responsavel_ti_conint varchar(30) NOT NULL,
	funcao_responsavel_ti_conint varchar(30) NOT NULL,
	apelido_email_responsavel_ti_alternativo_conint varchar(15) NOT NULL,
	email_responsavel_ti_alternativo_conint varchar(30) NOT NULL,
	skype_responsavel_ti_alternativo_conint  varchar(30) NOT NULL,
	funcao_responsavel_ti_alternativo_conint varchar(30) NOT NULL,
	apelido_email_responsavel_ti_nuvem_conint varchar(30) NULL,
	email_responsavel_ti_nuvem_conint varchar(30) NULL,
	skype_responsavel_ti_nuvem_conint varchar(30) NULL,
	telefone_responsavel_ti_Nuvem_conint varchar(30) NULL,
	aplicativo_desktop_remoto_conint varchar(30) NOT NULL,
	id_aplicativo_desktop_remoto_conint varchar(30) NOT NULL,

create table [dbo].[Configuracao_Instalador_Integrador](
	[id_conf_instalador_integrador_coninsint] [int] IDENTITY(1,1) NOT NULL,
	[id_fornecedor_fornec] [int] NOT NULL,
			Descricao da coluna: NomeFantasiaEmpresa, cnpjFornecedor
	[id_sistema_integrado_sisint] [int] NOT NULL,
			Descricao da coluna: SiglaSistemaFornecedor
	sn_servico_nuvem_conint bit NOT NULL,
	sequencia_instancia_nuvem_conint int NULL,
	usuario_webservice_conint varchar(30) NOT NULL,
	tipo_so_conint varchar(30) NOT NULL,
	so_32_ou_64_bit_conint varchar(6) NOT NULL,
	espaco_livre_disco_conint varchar(10) NOT NULL,
	memoria_ram_livre_conint varchar(10) NOT NULL,
	versao_jre_conint varchar(15) NOT NULL,
	tipo_jre_conint varchar(10) NOT NULL,
	versao_integrador_conint varchar(10) NOT NULL,
	sn_debug_ativado_conint bit NOT NULL,
	disco_integrador_conint char NOT NULL,
	dir_programfiles_conint varchar(30) NOT NULL,
	endereco_ip_publico_servidor_conint varchar(30) NOT NULL,
	porta_ip_aberta_conint varchar(15) NOT NULL,
	frequencia_processamento_conint varchar(10) NOT NULL,
	qtd_dias_arquivos_xml_guardados_conint int NOT NULL,
 CONSTRAINT [PK_CONCOM] PRIMARY KEY CLUSTERED 
(
	[id_contrato_comprador_concom] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY],
 CONSTRAINT [UK_CONCOM_COMPR_FORNEC_CONPAG_VALIDADE] UNIQUE NONCLUSTERED 
(
	[id_comprador_compr] ASC,
	[id_fornecedor_fornec] ASC,
	[id_condicao_pagamento_conpag] ASC,
	[dt_ini_validade_concom] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY],
 CONSTRAINT [UK_CONCOM_COMPR_FORNEC_CONTRATO] UNIQUE NONCLUSTERED 
(
	[id_comprador_compr] ASC,
	[id_fornecedor_fornec] ASC,
	[cd_contrato_concom] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

ALTER TABLE [dbo].[Contrato_Comprador]  WITH CHECK ADD  CONSTRAINT [FK_CONCOM_COMPR] FOREIGN KEY([id_comprador_compr])
REFERENCES [dbo].[Comprador] ([id_comprador_compr])
GO

ALTER TABLE [dbo].[Contrato_Comprador] CHECK CONSTRAINT [FK_CONCOM_COMPR]
GO

EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Identificador do contrato  do comprador.' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Contrato_Comprador', @level2type=N'COLUMN',@level2name=N'id_contrato_comprador_concom'
GO

EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Código do contrato do  comprador.' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Contrato_Comprador', @level2type=N'COLUMN',@level2name=N'cd_contrato_concom'
GO

EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Identificador do  comprador.' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Contrato_Comprador', @level2type=N'COLUMN',@level2name=N'id_comprador_compr'
GO

