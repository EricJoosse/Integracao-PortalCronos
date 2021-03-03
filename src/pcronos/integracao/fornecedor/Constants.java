package pcronos.integracao.fornecedor;

public class Constants {

	   public static final String DIR_ARQUIVOS_PROPERTIES = "conf/";
	   public static final String NOME_TEMPLATE_CLOUD_PROPERTIES = "TemplateNuvemAPS.properties";
	   public static final String NOME_ARQUIVO_PROPERTIES = "Integração Fornecedor - Portal Cronos.properties";
       // Using an absolute path (one that starts WITH '/') means that the current 
       // package is ignored.

       //  Relative paths (those WITHOUT a leading '/') mean that the resource 
	   //  will be searched relative to the directory which represents the package 
	   //  the class is in.
   	   //  Quer dizer: in a stand-alone environment (production env. por exemplo, sem Eclipse).  
       //  O comando "Run" in Eclipse searches however relative to the project root, and not relative to /src, 
       //  and DOS also searches however relative to the current directory DOS is in.
       //  No ambiente de produção o Agendador de Tarefas de Windows executa um arquivo .bat 
       //  então também searches relative to the current directory DOS is in, which happens to coincide 
       //  with the directory the jar-file is in.	
	   
	   public static final String DIR_TEMPLATES_EMAIL = "tpl/";
	   
	   public static final String DIR_LOG_REMOTO = "C:/ProgramData/PortalCronos/Logs/Remoto/Integracao";
	   // O seguinte funciona também em dir = new File(xxx): "C:\\ProgramData\\PortalCronos\\Logs\\Remoto\\Integracao"
	   
	   public static final String ESPACO_LIVRE = "espaço livre";
	   public static final String SERVAPPCRONOS = "AppSrvProd2012B";
	   public static final String SERVBANCOCRONOS = "BDSrvProd2012";
	   public static final String SERVBANCOCRONOSCONTINGENCIA = "SrvBancoConting";
	   public static final String SERVTESTE = "ServTeste";

}
