package pcronos.integracao.fornecedor;

import java.util.Date;
import java.util.Properties;
import java.util.Locale;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.text.NumberFormat;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.PrintStream;
import java.net.URI;
import javax.ws.rs.core.MediaType;

import com.microsoft.sqlserver.jdbc.SQLServerDriver;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.multipart.FormDataMultiPart;
import com.sun.jersey.multipart.file.FileDataBodyPart;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.w3c.dom.CharacterData;
import org.xml.sax.InputSource;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.OutputKeys;
import oracle.jdbc.driver.OracleDriver ; // http://www.java2s.com/Code/Jar/j/Downloadjdbcoraclejar.htm
import pcronos.integracao.EmailAutomatico;
import pcronos.integracao.fornecedor.dto.ViradaFornecedorParaProducaoDTO;

import org.firebirdsql.jdbc.FBDriver   ;
import java.sql.DriverManager          ;
import java.sql.Connection             ; 
import java.sql.SQLException           ;

public class TestadorSnippets {

	  public static String       instanciaBancoDeDados;

	  static 
	  {
        try {
	      Properties config = new Properties();
	      config.load(new FileInputStream("conf/Integração Fornecedor - Portal Cronos.properties"));
	      instanciaBancoDeDados             = config.getProperty("InstanciaBancoDeDados");
        }
        catch (Exception ex) {  }

	  
	  }
	  
	  
	  public static void testNullParam(Document doc, String txt) {
		    if (doc == null)
		    	System.out.println("doc == null, txt = " + txt);
		  }
		  
	  public static void testNullParam(File fi, String txt, boolean toLog) {
		    if (fi == null)
		    	System.out.println("file == null, txt = " + txt);
		  }
		  
	  private static void readProduto(int qtdProdutosComEstoque) 
	  {
  	       ++qtdProdutosComEstoque;
	  }

	  private static int readProdutoNovo(int qtdProdutosComEstoque) 
	  {
  	       ++qtdProdutosComEstoque;
	    // qtdProdutosComEstoque++;

  	       return qtdProdutosComEstoque;
	  }	  
	  
	  private static void testarParamIntegerInt() throws Exception 
	  {
	   		 // FornecedorRepositorio fRep = new FornecedorRepositorio();
 		    int iForn = 60;
			FornecedorRepositorio fRep = new FornecedorRepositorio();
 		    Fornecedor f = fRep.getFornecedor(iForn);
			System.out.println("fRep.getFornecedor(60) = " + f.NomeFantasiaEmpresa);
	  }

	  private static void testarGetFornecedor() throws Exception 
	  {
	   		 // FornecedorRepositorio fRep = new FornecedorRepositorio();
 		    int iForn = 60;
			FornecedorRepositorio fRep = new FornecedorRepositorio();
 		    Fornecedor f = fRep.getFornecedor(iForn);
			System.out.println("fRep.getFornecedor(60) = " + f.NomeFantasiaEmpresa);
			
			try {
				System.out.println("getFornecedor(13).NomeFantasiaEmpresa = " + fRep.getFornecedor(13).NomeFantasiaEmpresa); 
					// -> Formaggio
				System.out.println("getFornecedor(33).NomeFantasiaEmpresa = " + fRep.getFornecedor(33).NomeFantasiaEmpresa); 
					// -> Comal
				System.out.println("getFornecedor(null).NomeFantasiaEmpresa = " + fRep.getFornecedor(null).NomeFantasiaEmpresa); 
					// -> Monitoramento
				System.out.println("getFornecedor(9999).NomeFantasiaEmpresa = " + fRep.getFornecedor(9999).NomeFantasiaEmpresa);
					// -> new Exception("Erro: idFornecedor " + idFornecedor.toString() + " não existe") // não existe
			} 
			catch (Exception e) {
				e.printStackTrace();
			} 		  
	  }
	  

	  private static void testarGetIdFornecedorByCnpj() 
	  {
			FornecedorRepositorio fRep = new FornecedorRepositorio();
			
			try {
				System.out.println("getIdFornecedorByCnpj(00680755000265) = " + fRep.getIdFornecedorByCnpj("00680755000265")); 
				// -> 947
			} 
			catch (Exception e) {
				e.printStackTrace();
			} 		  
			try {
				System.out.println("getIdFornecedorByCnpj(24150377000195) = " + fRep.getIdFornecedorByCnpj("24150377000195")); 
				// -> 60
			} 
			catch (Exception e) {
				e.printStackTrace();
			} 		  
			try {
				System.out.println("getIdFornecedorByCnpj(11222333444455) = " + fRep.getIdFornecedorByCnpj("11222333444455")); 
				// -> Exception que não foi informado
			} 
			catch (Exception e) {
				e.printStackTrace();
			} 		  
			try {
				System.out.println("fRep.getIdFornecedorByCnpj(null) = " + fRep.getIdFornecedorByCnpj(null)); 
				// -> Exception que não foi informado
			} 
			catch (Exception e) {
				e.printStackTrace();
			} 		  
			try {
				System.out.println("getIdFornecedorByCnpj('') = " + fRep.getIdFornecedorByCnpj("")); 
				// -> Exception que está errado
			} 
			catch (Exception e) {
				e.printStackTrace();
			} 		  
			try {
				System.out.println("getIdFornecedorByCnpj(680755000265) = " + fRep.getIdFornecedorByCnpj("680755000265")); 
				// -> Exception que está errado
			} 
			catch (Exception e) {
				e.printStackTrace();
			} 		  
	  }
	  

	  private static void testarMainArgs(String[] args2)
	  {
			int idFornecedor = Integer.parseInt(args2[0]);		 
	        System.out.println("TestadorSnippets.java: idFornecedor = " + Integer.toString(idFornecedor));		  
	  }
	  
	  private static void testarIncrementOperator()
	  {
	    	int qtdProdutosComEstoqueNovo = 0;
	    	qtdProdutosComEstoqueNovo = readProdutoNovo(qtdProdutosComEstoqueNovo);
	        System.out.println("QtdProdutosComEstoqueNovo = " + Integer.toString(qtdProdutosComEstoqueNovo));	        
	        
	    	int qtdProdutosComEstoque = 0;
	        readProduto(qtdProdutosComEstoque);
	        System.out.println("QtdProdutosComEstoque = " + Integer.toString(qtdProdutosComEstoque));		  
	  }	  
	  
	  private static void testarIncrementOperatorString()
	  {
	      String a = "abc";
	      String b = "xyz";
	
	      String c = a;
	
	      c += b; 		  
	  }
	  
	  private static void testarGetQtdProdutosComEstoqueDeArquivoLog() throws Exception
	  {
		    LocalDateTime horaInicio = LocalDateTime.now();
  	    	Integer qtdProdutosComEstoque2 = null;
          	File dirLogRemoto = new File(Constants.DIR_LOG_REMOTO); 
          	for (final File file : dirLogRemoto.listFiles()) 
          	{
          	     if (       file.getName().startsWith("ws-jrembalagem" + ".") 
          	    		 && file.getName().endsWith("." + horaInicio.getYear() + "." + String.format("%02d", horaInicio.getMonthValue()) + ".log") 
          	    		 && file.getName().indexOf("." + "Homologacao" + ".")  == -1 
          	    		 && file.getName().indexOf("." + "Apresentacao" + ".") == -1 
          	    		 && file.getName().indexOf("." + "Teste" + ".")        == -1 
          	    		 && file.getName().indexOf("." + "Debug" + ".") > 0) 
          	     {
          	    	BufferedReader br = new BufferedReader(new FileReader(dirLogRemoto + "/" + file.getName()));
          	    	try 
          	    	{
          	    	    String linha = br.readLine();
          	    	    String prefix = "Cotacao " + "252-0164" + ": QtdProdutosComEstoque = ";

          	    	    while (linha != null && !linha.startsWith(prefix, 21))
          	    	    {
          	    	        linha = br.readLine();
          	    	    }

           	    	    if (linha != null && linha.startsWith(prefix, 21))
     	    	    	    qtdProdutosComEstoque2 = Integer.parseInt(linha.substring(21).replace(prefix, ""));
          	    	} 
          	    	finally 
          	    	{
          	    	    br.close();
          	    	}
          	    }
        	}
          	// Adicionar no .log: Cotacao 163-0235: QtdProdutosComEstoque = 354
   		    System.out.println("");
          	if (qtdProdutosComEstoque2 == null)
    	    	  System.out.println("qtdProdutosComEstoque2 não encontrado");
          	else
  	    	      System.out.println("qtdProdutosComEstoque2 = " + qtdProdutosComEstoque2.toString());		  
	  }
	  	  
	  private static void testarDayOfWeek()
	  {
		    LocalDateTime horaInicio = LocalDateTime.now();
			horaInicio = LocalDateTime.now();
     	    if (horaInicio.getDayOfWeek() == DayOfWeek.SATURDAY)
				System.out.println("Hoje é sábado");
     	    else if (horaInicio.getDayOfWeek() == DayOfWeek.SUNDAY)
				System.out.println("Hoje é domingo");
     	    else 
				System.out.println("Hoje é dia útil");		  
	  }
	  	  
	  private static void testarDateFormatComParenteses()
	  {
   	    DateTimeFormatter formatterParenteses = DateTimeFormatter.ofPattern("'('dd/MM/yyyy')' HH:mm");
  	    System.out.println(LocalDateTime.now().format(formatterParenteses));		  
	  }
	  
	  private static void diversosTestesBigDecimal()
	  {
 	      // BigDecimal bigDec = new BigDecimal(0);
		  BigDecimal bigDec = new BigDecimal("0.00");
		  if (bigDec.compareTo(BigDecimal.ZERO) == 0)
		  	 System.out.println("bigDec == 0");
		  else
		  	 System.out.println("bigDec != 0, bigDec = " + bigDec.toString());		  

		  
	      Locale locale = new Locale("pt", "BR");
	      NumberFormat nf = NumberFormat.getInstance(locale);
	      nf.setGroupingUsed(false);
	      nf.setMaximumFractionDigits(2);
	      nf.setMinimumFractionDigits(2);
	
	      BigDecimal vlMinimoPedido = null;
	      System.out.println( "nf.format(vlMinimoPedido) = " + (vlMinimoPedido == null ? "null" : nf.format(vlMinimoPedido)) );
	
	      vlMinimoPedido = new BigDecimal(0);
	      System.out.println( "vlMinimoPedido.setScale(2, BigDecimal.ROUND_HALF_UP) = " + vlMinimoPedido.setScale(2, BigDecimal.ROUND_HALF_UP));
	      System.out.println( "nf.format(vlMinimoPedido) = " + (vlMinimoPedido == null ? "null" : nf.format(vlMinimoPedido)) );
	
	
	      vlMinimoPedido = new BigDecimal(0.1);
	      System.out.println( "vlMinimoPedido.setScale(2, BigDecimal.ROUND_HALF_UP) = " + vlMinimoPedido.setScale(2, BigDecimal.ROUND_HALF_UP));
	      System.out.println( "nf.format(vlMinimoPedido) = " + (vlMinimoPedido == null ? "null" : nf.format(vlMinimoPedido)) );
	
	  }

	  private static void testar_mkdirs()
	  {
          File dir = new File("C:/temp/a/b/c/log");
          dir.mkdirs();		  
	  }
	  
	  private static void testarFBDriver() throws Exception
	  {
		java.sql.Connection conn = null;
		java.sql.Statement stat = null;
		java.sql.ResultSet rSet = null;

		FBDriver fbDriver = new FBDriver();
         DriverManager.registerDriver(fbDriver); // Antigamente: Class.forName("org.firebirdsql.jdbc.FBDriver"); 
         String connectionString = "jdbc:firebirdsql://" + "52.10.223.6" + ":" + "3050" + "/" + instanciaBancoDeDados;
         System.out.println("connectionString = " + connectionString);
  	     conn = DriverManager.getConnection(connectionString, "SYSDBA", "masterkey") ;	    
  	     stat = conn.createStatement() ;
  	     String sqlString = "select cpfcgc "
                + "  from cliente    "
                ;
  	     rSet = stat.executeQuery( sqlString ) ;
  	     while (rSet.next()) 
  	     {
  	    	System.out.println("cpfcgc = " + rSet.getString(1));
  	     }		  
	  }
	  	  
	  private static void testarNullParam()
	  {
			testNullParam(null, "abc");
			testNullParam(null, "abc", true);		  
	  }
	  
	  private static void testarFloats()
	  {
		   Float vlMinimoPedido = null;
		   System.out.println(   vlMinimoPedido == null ? "" : Float.toString(vlMinimoPedido)   );
		
		   vlMinimoPedido = 1.0f;
		   System.out.println(   vlMinimoPedido == null ? "" : Float.toString(vlMinimoPedido)   );
	  }
	  
	  private static void testarNullInteger()
	  {
	      Integer numRegiaoWinThor = null;
	      System.out.println( "Integer.toString(((int)numRegiaoWinThor)) = " + Integer.toString((numRegiaoWinThor)));
	  }
	  
	  private static void testarSimpleDateFormat()
	  {
	      System.out.println("Teste Scheduler - " + new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new Date()));
	  }
	  
	  private static void testar_jar_cp()
	  {
 		  System.out.println("Teste jar -cp .jar class");
	  }
	  
	  private static void testarForloop() {
		int qtdFornecedores = 11;

		for (int j=0; j < (qtdFornecedores); j++) {
		    System.out.println("j = " + j);
		}
	  }
	  
	  private static void comparacaoVersoes(String versao) {
	      if (versao.compareTo("2.7.0") >= 0 || versao.compareTo("2.7") >= 0)
	    	 System.out.println("Versão " + versao + " >= 2.7.0");
	      else
	    	 System.out.println("Versão " + versao + " >= 2.7.0 não");
	  }
	  
	  private static void testarComparacaoVersoes() {
		  comparacaoVersoes("1.2.3");
		  comparacaoVersoes("2");
		  comparacaoVersoes("2.6");
		  comparacaoVersoes("2.6.9");
		  comparacaoVersoes("2.7");
		  comparacaoVersoes("2.7.0");
		  comparacaoVersoes("2.7.1");
	      comparacaoVersoes("2.8");
	      comparacaoVersoes("2.8.4");
	      comparacaoVersoes("3");
	  }
	  
	  private static void testarOffset() {
		  String linha = "01/03/2019 15:10:21: Cotacao 252-0164: QtdProdutosComEstoque = 23";
		  String prefix = "Cotacao 252-0164: QtdProdutosComEstoque = ";
	      Integer qtdProdutosComEstoque = Integer.parseInt(linha.substring(21).replace(prefix, ""));
    	  System.out.println("qtdProdutosComEstoque = " + qtdProdutosComEstoque.toString());
	  }
	  
	  private static void testarSp_historicoErrosIntegracaoRadical() throws SQLException {
    	  // Executar stored procedure para solucionar timeouts provisoriamente:

		  // Antes ee depois rodar os seguintes selects: 
		  // select count(*) from [dbo].[Log_Erro_Integracao] where id_fornecedor_fornec = 947 
		  // select count(*) from [dbo].[Log_Erro_Integracao_Historico] where id_fornecedor_fornec = 947

	      SQLServerDriver sqlsrvDriver = new SQLServerDriver() ; 
	      DriverManager.registerDriver( sqlsrvDriver ) ; 	
	      //connectionUrl = "jdbc:sqlserver://localhost:1433;" +  
	    	//         "databaseName=AdventureWorks;user=UserName;password=*****";  
	      String connectionString = "jdbc:sqlserver://" + IntegracaoFornecedorCompleta.enderecoIpServidorBancoDeDados + ":" + IntegracaoFornecedorCompleta.portaServidorBancoDeDados + ";databaseName=" + IntegracaoFornecedorCompleta.instanciaBancoDeDados; 
	      java.sql.Connection conn = DriverManager.getConnection(connectionString, IntegracaoFornecedorCompleta.usernameBancoDeDados, IntegracaoFornecedorCompleta.senhaBancoDeDados ) ;	    

    	  System.out.println("IntegracaoFornecedorCompleta.enderecoIpServidorBancoDeDados = " + IntegracaoFornecedorCompleta.enderecoIpServidorBancoDeDados);
    	  System.out.println("IntegracaoFornecedorCompleta.portaServidorBancoDeDados = " + IntegracaoFornecedorCompleta.portaServidorBancoDeDados);
    	  System.out.println("IntegracaoFornecedorCompleta.instanciaBancoDeDados = " + IntegracaoFornecedorCompleta.instanciaBancoDeDados);
    	  System.out.println("IntegracaoFornecedorCompleta.usernameBancoDeDados = " + IntegracaoFornecedorCompleta.usernameBancoDeDados);
    	  System.out.println("IntegracaoFornecedorCompleta.senhaBancoDeDados = " + IntegracaoFornecedorCompleta.senhaBancoDeDados);

    	  java.sql.CallableStatement stmtTimeout = conn.prepareCall("{call dbo.historicoErrosIntegracaoRadical(?)}");
    	  stmtTimeout.setInt(1, 947);
    	  stmtTimeout.executeUpdate();
		  
	  }

	  private static void testarProcuraTimeouts() throws IOException {
        	 File dirLogRemoto = new File(Constants.DIR_LOG_REMOTO); 
  			 LocalDateTime horaInicioSelect = LocalDateTime.now();
           	 
  	    	 arquivoloop: for (final File file : dirLogRemoto.listFiles()) 
        	 {
  	     	     System.out.println("file.getName() = " + file.getName());

     	     	 LocalDateTime datahoraArquivoLog = LocalDateTime.ofInstant(Instant.ofEpochMilli(file.lastModified()), ZoneId.systemDefault()); 

				 if (    file.getName().startsWith("ws-jrembalagem" + ".") 
        	    	  && file.getName().endsWith(".log") 
        	    	  && file.getName().indexOf("." + "Homologacao" + ".")  == -1 
        	    	  && file.getName().indexOf("." + "Apresentacao" + ".") == -1 
        	    	  && file.getName().indexOf("." + "Teste" + ".")        == -1 
        	    	  && file.getName().indexOf("." + "Erro" + ".") > 0 
        	    	  && datahoraArquivoLog.isAfter(horaInicioSelect.minusMinutes(15)) 
        	    	)
        	     {
	 	  	        BufferedReader br = new BufferedReader(new FileReader(dirLogRemoto + "/" + file.getName()));
        	    	try 
        	    	{
        	    	    String linha = br.readLine();

        	    	    while (linha != null)
        	    	    {        	 	 	  	    
        	 	 	  	    if (linha.indexOf("InnerException") >= 0 && linha.indexOf("Execution Timeout Expired") > 0)
        	    	    	{
		           	    	    br.close();
		           	    	    System.out.println("timeout encontrado com sucesso!");
		           	    	    continue arquivoloop;
        	    	    		
        	    	    	}
        	    	    	else
        	    	           linha = br.readLine();
        	    	    }
        	    	    
        	    	} 
        	    	finally 
        	    	{
        	    	    br.close();
        	    	}
        	     } 
        	 } // for		  
	  }
	  
	  private static void testarMontagemTemplateEmail() throws Exception 
	  {
		String tipoDtoOuNmFornecedor = "INI";
     	ViradaFornecedorParaProducaoDTO dtoVirada = new ViradaFornecedorParaProducaoDTO();
     	dtoVirada.IdFornecedor = 33;
     	dtoVirada.IdFornecedorString = Integer.toString(dtoVirada.IdFornecedor);			            	      	
	    FornecedorRepositorio fRep = new FornecedorRepositorio();
 		Fornecedor f = fRep.getFornecedor(dtoVirada.IdFornecedor);
 		LocalDateTime horaInicio = LocalDateTime.now();
 		
 		
 		String assunto = "Integração " + f.NomeFantasiaEmpresa + " colocada em produção! - TESTE COM TEMPLATE";
 		String body = Utils.getTemplateEmail("Email de tipo Virada fornecedor para produção.txt")
     			           .replaceAll("[IdFornecedorString]", dtoVirada.IdFornecedorString);
   	    System.out.println(body);
        EmailAutomatico.enviar(IntegracaoFornecedorCompleta.remetenteEmailAutomatico, IntegracaoFornecedorCompleta.destinoEmailAutomatico, IntegracaoFornecedorCompleta.ccEmailAutomatico, assunto, null, body, IntegracaoFornecedorCompleta.provedorEmailAutomatico, IntegracaoFornecedorCompleta.portaEmailAutomatico, IntegracaoFornecedorCompleta.usuarioEmailAutomatico, IntegracaoFornecedorCompleta.senhaCriptografadaEmailAutomatico, IntegracaoFornecedorCompleta.diretorioArquivosXmlSemBarraNoFinal, horaInicio, IntegracaoFornecedorCompleta.diretorioArquivosXml, tipoDtoOuNmFornecedor, (tipoDtoOuNmFornecedor.equals("INI") ? null : "ERRADO!!!"));

         
 		assunto = "Integração " + f.NomeFantasiaEmpresa + " colocada em produção! - TESTE SEM TEMPLATE";
	    body = "Ao setor Desenvolvimento do Portal Cronos," + "\r\n";
	    body += " " + "\r\n";
     	body += "A integração do fornecedor com id_fornecedor = " + dtoVirada.IdFornecedorString + " foi colocada em produção!\r\n\r\n";
	    body += " " + "\r\n";
     	body += "1. Provisoriamente (enquanto que o seguinte ainda não foi automatizado):" + "\r\n"; 
	    body += "   Favor excluir o \"OR\" deste id_fornecedor no arquivo \"/scripts/sp_monitorarIntegracaoFornecedores.sql\" no projeto Eclipse e executar o script na base de produção. ";
     	body += "Dica: procura \"" + dtoVirada.IdFornecedorString + "\" nesta sp." + "\r\n";
	    body += " " + "\r\n";
     	body += "2. Provisoriamente (enquanto que ainda não existe uma tabela nova dbo.Fornecedor_Integrado, e enquanto que o seguinte ainda não foi automatizado):" + "\r\n"; 
	    body += "   favor alterar a chave \"Em produção\" de \"Não	\" para \"Sim\" no arquivo Tabela Fornecedores_Versões.txt" + "\r\n\r\n";
	    body += " " + "\r\n"
	    			+ "Atc," + "\r\n"
	    			+ "O email automático do Portal Cronos " + "\r\n"
	    			+  "\r\n\r\n\r\n\r\n";		
        EmailAutomatico.enviar(IntegracaoFornecedorCompleta.remetenteEmailAutomatico, IntegracaoFornecedorCompleta.destinoEmailAutomatico, IntegracaoFornecedorCompleta.ccEmailAutomatico, assunto, null, body, IntegracaoFornecedorCompleta.provedorEmailAutomatico, IntegracaoFornecedorCompleta.portaEmailAutomatico, IntegracaoFornecedorCompleta.usuarioEmailAutomatico, IntegracaoFornecedorCompleta.senhaCriptografadaEmailAutomatico, IntegracaoFornecedorCompleta.diretorioArquivosXmlSemBarraNoFinal, horaInicio, IntegracaoFornecedorCompleta.diretorioArquivosXml, tipoDtoOuNmFornecedor, (tipoDtoOuNmFornecedor.equals("INI") ? null : "ERRADO!!!"));
	}
	  
	  public static void main(String[] args) throws Exception {

		try
        {			
		 // testarGetIdFornecedorByCnpj();
		 // testarGetFornecedor();
	     // testarMainArgs(args);
	     // testarIncrementOperator();
		 // testarIncrementOperatorString();
	     // testarGetQtdProdutosComEstoqueDeArquivoLog();
		 // testarDayOfWeek();
		 // testarDateFormatComParenteses();
		 // testarParamIntegerInt();
		 // diversosTestesBigDecimal();
		 // testarNullParam();
		 // testar_mkdirs();
		 // testarFBDriver();
		 // testarFloats();
		 // testarNullInteger();
		 // testarSimpleDateFormat();
		 // testar_jar_cp();
		 // testarForloop();
		 // testarComparacaoVersoes();
		 //	testarOffset();
         // testarSp_historicoErrosIntegracaoRadical();
         // testarProcuraTimeouts();
			testarMontagemTemplateEmail();
         

         // throw new Exception("try");
        }
	    catch (java.lang.Exception ex) { 
         // throw new Exception("catch");
	    	ex.printStackTrace();
        }
        finally { 
        	System.out.println("finally executed");
        }
    }

}
