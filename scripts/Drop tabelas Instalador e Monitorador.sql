if object_id('Configuracao_Instalador_Integrador') is not NULL
  drop table [dbo].[Configuracao_Instalador_Integrador];
--go

if object_id('Configuracao_Monitorador_Integradores') is not NULL
  drop table [dbo].[Configuracao_Monitorador_Integradores];
--go

if object_id('Configuracao_Instalador_Integrador_Nuvem') is not NULL
  drop table [dbo].[Configuracao_Instalador_Integrador_Nuvem];
--go

if object_id('Configuracao_Monitorador_Integradores_Nuvem') is not NULL
  drop table [dbo].[Configuracao_Monitorador_Integradores_Nuvem];
--go

if object_id('Contato_TI_Integrador') is not NULL
  drop table [dbo].[Contato_TI_Integrador];
--go

if object_id('Contato_TI_Integrador_Nuvem') is not NULL
  drop table [dbo].[Contato_TI_Integrador_Nuvem];
--go
