package pcronos.integracao.fornecedor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;
import java.util.Locale;
import java.util.List;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.text.NumberFormat;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.FileInputStream;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.net.URI;
import java.sql.DriverManager;
import java.sql.Connection; 
import java.sql.SQLException;
import javax.ws.rs.core.MediaType;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.OutputKeys;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.multipart.FormDataMultiPart;
import com.sun.jersey.multipart.file.FileDataBodyPart;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.w3c.dom.CharacterData;
import org.xml.sax.InputSource;
import oracle.jdbc.driver.OracleDriver ; // http://www.java2s.com/Code/Jar/j/Downloadjdbcoraclejar.htm
import org.firebirdsql.jdbc.FBDriver   ;

// Javadocs Jersey downloaded de http://repo1.maven.org/maven2/com/sun/jersey/
//                             e https://www.versioneye.com/java/com.sun.jersey.contribs:jersey-multipart/1.12       

//          Jaybird :            http://www.firebirdsql.org/en/jdbc-driver/
//                            ou https://www.versioneye.com/java/org.firebirdsql.jdbc:jaybird-jdk18/2.2.10

//          JDBC Oracle :        Não existe..........?????????????????????????

import java.io.FilePermission;
import java.security.AccessController;
 

final class IntegracaoFornecedorCompleta {

   public static final String NOME_ARQUIVO_PROPERTIES = "conf/Integração Fornecedor - Portal Cronos.properties";
                              // Using an absolute path (one that starts WITH '/') means that the current 
                              // package is ignored.

	                         //  Relative paths (those WITHOUT a leading '/') mean that the resource 
						     //  will be searched relative to the directory which represents the package 
						     //  the class is in.
                         	//  Quer dizer: in a stand-alone environment (production env. por exemplo, sem Eclipse).  
	                        //  O comando "Run" in Eclipse searches however relative to the project root, and not relative to /src, 
	                        //  and DOS also searches however relative to the current directory DOS is in.
	                        //  No ambiiente de produção o Agendador de Tarefas de Windows executa um arquivo .bat 
	                        //  então também searches relative to the current directory DOS is in, which happens to coincide 
	                        //  with the directory the jar-file is in.	
   
  public static final String NAO_OFERTADA_IMPACTO_SE_ALTERAR = "não ofertada";
  public static Locale       locale;
  public static NumberFormat nf;
  public static String       siglaSistema;
  public static boolean      toVerificarEstoque;
  public static String       criterioVerificacaoEstoque;
  public static boolean       toUsarValorMinimoSistemaFornecedor;
  public static String       username;
  public static String       senha;
  public static String       tipoBancoDeDados;
  public static String       usernameBancoDeDados;
  public static String       senhaBancoDeDados;
  public static String       enderecoIpServidorBancoDeDados;
  public static String       portaServidorBancoDeDados;
  public static String       instanciaBancoDeDados;
  public static String       cnpjFornecedor;
  public static String       nomeFantasiaFornecedor;
  public static String       tipoAmbiente;
  public static String       enderecoBaseWebService;
  public static String       diretorioArquivosXml;
  public static String       ObsOfertasPadraoSeNaoTemNoSistema;
  public static boolean      toDebugar;
  public static boolean      temErroGeralCotacao;
  public static String       erroStaticConstructor;
  public static String       nomeArquivoDebug;


  static 
  {
    try {
      Date hoje = new Date();
      nomeArquivoDebug = "Debug" + new SimpleDateFormat("yyyy.MM.dd - HH'h'mm ").format(hoje) + ".log";
 
      erroStaticConstructor = null;

      locale = new Locale("pt", "BR");
      nf = NumberFormat.getInstance(locale);

      Properties config = new Properties();
      config.load(new FileInputStream(NOME_ARQUIVO_PROPERTIES));

      siglaSistema                      = config.getProperty("SiglaSistema");
      toVerificarEstoque                = Boolean.parseBoolean(config.getProperty("VerificarEstoque"));
      criterioVerificacaoEstoque        = config.getProperty("CriterioVerificacaoEstoque");
      
      if (config.getProperty("UsarValorMinimoSistemaFornecedor") == null) // Se esta chave não existir no *.properties
          toUsarValorMinimoSistemaFornecedor = true;
      else 
          toUsarValorMinimoSistemaFornecedor = Boolean.parseBoolean(config.getProperty("UsarValorMinimoSistemaFornecedor"));

      username                          = config.getProperty("UsuarioWebService");
      senha                             = config.getProperty("SenhaWebService");
      tipoBancoDeDados                  = config.getProperty("TipoBancoDeDados");
      usernameBancoDeDados              = config.getProperty("UsuarioBancoDeDados");
      senhaBancoDeDados                 = config.getProperty("SenhaBancoDeDados");
      enderecoIpServidorBancoDeDados    = config.getProperty("EnderecoIpServidorBancoDeDados");
      portaServidorBancoDeDados         = config.getProperty("PortaServidorBancoDeDados");
      instanciaBancoDeDados             = config.getProperty("InstanciaBancoDeDados");
      cnpjFornecedor                    = config.getProperty("CnpjFornecedor");
      nomeFantasiaFornecedor            = config.getProperty("NomeFantasiaFornecedor");
      tipoAmbiente                      = config.getProperty("TipoAmbiente");
      toDebugar                         = Boolean.parseBoolean(config.getProperty("Debugar"));

      if (tipoAmbiente.equals("P"))
        enderecoBaseWebService          = config.getProperty("EnderecoBaseWebServiceProducao");
      else if (tipoAmbiente.equals("H"))
          enderecoBaseWebService          = config.getProperty("EnderecoBaseWebServiceHomologacao");
      else if (tipoAmbiente.equals("T"))
          enderecoBaseWebService          = config.getProperty("EnderecoBaseWebServiceTeste");

      diretorioArquivosXml              = config.getProperty("DiretorioArquivosXml");
      
      if (!Files.isDirectory(Paths.get(diretorioArquivosXml))) {
    	  String msgErroDiretorio = "Erro! O diretório " + diretorioArquivosXml + " não existe! Favor contatar o setor TI.";
    	  diretorioArquivosXml = "C:/";
    	  throw new Exception(msgErroDiretorio);
      }
      
      try {
  	    // O seguinte tem apenas efeito em Linux, e nenhum efeito em Windows, 
    	// pois com Windows >= XP não é possível alterar diretórios para read-only (apenas arquivos)
    	  
        File testDir = new File(diretorioArquivosXml);
        if (!testDir.canWrite()) 
        { 
           String msgErroDiretorio = "Erro! O diretório " + diretorioArquivosXml + " é protegido contra gravação de arquivos ! Favor contatar o setor TI.";
      	   diretorioArquivosXml = "C:/";
           throw new Exception(msgErroDiretorio);
        }

    	  // O seguinte tb não funciona com Windows : 
          // AccessController.checkPermission(new FilePermission(diretorioArquivosXml, "write"));
        
          // A única maneira para verificar os priviêgios necessários em Java 8 é : 
          File.createTempFile("check", null, testDir).delete();

          diretorioArquivosXml = diretorioArquivosXml + "/" ;
      }
      catch (SecurityException | IOException se_io_ex)
      {
   	     String msgErroDiretorio = "Erro! Não tem permissões suficientes para gravar arquivos no diretório " + diretorioArquivosXml + " ! Favor contatar o setor TI.";
   	     diretorioArquivosXml = "C:/";
      	 throw new Exception(msgErroDiretorio);
      }

      ObsOfertasPadraoSeNaoTemNoSistema = config.getProperty("ObsOfertasPadraoSeNaoTemNoSistema");

	  debugar("sun.boot.class.path            = " + java.lang.management.ManagementFactory.getRuntimeMXBean().getBootClassPath());
	  debugar("toVerificarEstoque             = " + toVerificarEstoque);
	  debugar("criterioVerificacaoEstoque     = " + criterioVerificacaoEstoque);
	  debugar("username                       = " + username);
	  debugar("senha                          = " + senha);
	  debugar("usernameBancoDeDados           = " + usernameBancoDeDados);
	  debugar("senhaBancoDeDados              = " + senhaBancoDeDados);
	  debugar("enderecoIpServidorBancoDeDados = " + enderecoIpServidorBancoDeDados);
	  debugar("portaServidorBancoDeDados      = " + portaServidorBancoDeDados);
	  debugar("instanciaBancoDeDados          = " + instanciaBancoDeDados);
	  debugar("cnpjFornecedor                 = " + cnpjFornecedor);
	  debugar("tipoAmbiente                   = " + tipoAmbiente);
	  debugar("toDebugar                      = " + toDebugar);
	  debugar("enderecoBaseWebService         = " + enderecoBaseWebService);
	  debugar("ObsOfertasPadraoSeNaoTemNoSistema = " + ObsOfertasPadraoSeNaoTemNoSistema);
         
      // throw new Exception("teste exception static constructor");
    } 
    catch (Exception ex) 
    {
      try
      {
        erroStaticConstructor = printStackTraceToString(ex); 
        logarErro(ex, true);
      }
      catch (Exception ex2)
      {
        throw new ExceptionInInitializerError(ex2);
      }
    }
  }


  private static void criarTabelasTeste() 
  {
	  try
	  {
    if (siglaSistema.equals("APS") && tipoBancoDeDados.equals("Firebird"))
    {
        FBDriver fbDriver = new FBDriver();
        DriverManager.registerDriver(fbDriver); 
        String connectionString = "jdbc:firebirdsql://" + enderecoIpServidorBancoDeDados + ":" + portaServidorBancoDeDados + "/" + instanciaBancoDeDados;
      	String sqlString = "create table produto (codproduto int, ativo int, ativopesq int)";
	    java.sql.Connection conn = DriverManager.getConnection(connectionString, usernameBancoDeDados, senhaBancoDeDados ) ;
	             // Por default AutoCommit = true
	    java.sql.Statement stat = conn.createStatement() ;
	    
	    try {
	      stat.executeUpdate(sqlString);
	    }
	    catch (Exception ex) { 
	    	ex.printStackTrace();
	    }

      	sqlString = "create table prodfilho (codproduto int, codprodfilho int)";

	    try {
		      stat.executeUpdate(sqlString);
        }
	    catch (Exception ex) { 
	    	ex.printStackTrace();
	    }

	    int qtdInsert = 0;
	    
	    try {
          sqlString = "insert into produto(codproduto, ativo, ativopesq) values (1, 2, 1)";
  	      qtdInsert = stat.executeUpdate(sqlString);
          System.out.println("qtdInsert = " + qtdInsert);
        }
	    catch (Exception ex) { 
	    	ex.printStackTrace();
	    }

	    try {
       	  sqlString = "insert into prodfilho(codproduto, codprodfilho) select 1, precoempresa.codprodfilho from precoempresa";
  	      qtdInsert = stat.executeUpdate(sqlString);
          System.out.println("qtdInsert = " + qtdInsert);
        }
	    catch (Exception ex) { 
	    	ex.printStackTrace();
	    }
  
    } // if (siglaSistema.equals("APS") && tipoBancoDeDados.equals("Firebird"))       			
	  }
      catch (Exception ex) { 
	    	ex.printStackTrace();
	  }
  }        			

  public static String getCharacterDataFromElement(Element e) 
  {
    Node child = e.getFirstChild();
    if (child instanceof CharacterData) {
      CharacterData cd = (CharacterData) child;
      return cd.getData();
    }
    return "";
  }

/*


<Cotacoes xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
<Cotacao>
 	<Dt_Gravacao>16/04/2015 14:31:35</Dt_Gravacao>
  	<Cd_Cotacao>001-0100</Cd_Cotacao>
  	<Cd_Comprador>01234567000145</Cd_Comprador>  
 	<Cd_Condicao_Pagamento>123</Cd_Condicao_Pagamento>
  	<Tp_Frete>CIF</Tp_Frete>
  	<Dt_Previsao_Entrega>21/04/2015</Dt_Previsao_Entrega>
  	<Dt_Inicio_Cotacao>16/04/2015 14:30:00</Dt_Inicio_Cotacao>
  	<Dt_Fim_Cotacao>20/04/2015 11:00:00</Dt_Fim_Cotacao>
  	<Dt_Cadastro>16/04/2015 14:29:46</Dt_Cadastro>
  	<Produtos>
    		<Produto>
      			<Cd_Produto_Fornecedor>045879</Cd_Produto_Fornecedor>
			<Cd_Produto>620</Cd_Produto>
      			<Cd_Unidade_Compra>UN</Cd_Unidade_Compra>
      			<Qt_Embalagem>12</Qt_ Embalagem>
      			<Qt_Produto>1000</Qt_Produto>
     		</Produto>
  	</Produtos>
</Cotacao>
</Cotacoes>



<?xml version="1.0" encoding="iso-8859-1"?>
<RecebimentoCotacao>
  <Cd_Cotacao>082-0239</Cd_Cotacao>
  <Cd_Comprador>06056930000143</Cd_Comprador>
  <Cd_Fornecedor>02870737000190</Cd_Fornecedor>
  <Dt_Recebimento>24/03/2016 00:05:44</Dt_Recebimento>
</RecebimentoCotacao>


<?xml version="1.0" encoding="iso-8859-1"?>
<OfertasCotacao>
  <Dt_Gravacao>24/03/2016 11:26:04</Dt_Gravacao>
  <Cd_Cotacao>082-0240</Cd_Cotacao>
  <Cd_Comprador>06056930000143</Cd_Comprador>
  <Cd_Fornecedor>02870737000190</Cd_Fornecedor>
  <Cd_Condicao_Pagamento_Fornecedor>4</Cd_Condicao_Pagamento_Fornecedor>
  <Tp_Frete_Fornecedor>CIF</Tp_Frete_Fornecedor>
  <Qt_Prz_Entrega>1</Qt_Prz_Entrega>
  <Vl_Minimo_Pedido>300,00</Vl_Minimo_Pedido>
  <Ds_Observacao_Fornecedor>Texto padrão do fornecedor para todas as cotações de todos os compradores.</Ds_Observacao_Fornecedor>
  <Produtos>
    <Produto>
      <Cd_Produto_Fornecedor>1852</Cd_Produto_Fornecedor>
      <Cd_Produto>428</Cd_Produto>
      <Qt_Embalagem>3</Qt_Embalagem>
      <Vl_Preco>33,4500</Vl_Preco>
      <Ds_Obs_Oferta_Fornecedor></Ds_Obs_Oferta_Fornecedor>
    </Produto>
  </Produtos>
  <Erros />
</OfertasCotacao>

*/

  private static void debugar(String txt) 
  {  
     if (toDebugar) 
     {
         try
    	 {
	        BufferedWriter bWriter = new BufferedWriter(new FileWriter(diretorioArquivosXml + nomeArquivoDebug, true));
	        bWriter.append(txt);
	        bWriter.newLine();
	        bWriter.newLine();
	        bWriter.flush();
	        bWriter.close();
    	 }
    	 catch (IOException ioe)
    	 {
    	   System.out.println(txt);
    	 }
     }
  }
  

  private static void logarErro( String erro )
  {
     Date hoje = new Date();
      
 	 try
 	 {
	        BufferedWriter bWriter = new BufferedWriter(new FileWriter(diretorioArquivosXml + "Erro - " + new SimpleDateFormat("yyyy.MM.dd - HH'h'mm.ss ").format(hoje) + ".log", true));
	        bWriter.append(erro);
	        bWriter.newLine();
	        bWriter.newLine();
	        bWriter.flush();
	        bWriter.close();
 	 }
 	 catch (IOException ioe)
 	 {
 	   System.out.println(erro);
 	 }  
  }
  
  private static void logarErro( Exception ex, boolean toConsoleTambem ) 
  {  
	  if (toConsoleTambem)
	  {
		  ex.printStackTrace();
	  }

	  Date hoje = new Date();
      File file = new File(diretorioArquivosXml + "Erro - " + new SimpleDateFormat("yyyy.MM.dd - HH'h'mm.ss ").format(hoje) + ".log");
      
      try
      {
    	  PrintStream ps = new PrintStream(file);
          ex.printStackTrace(ps);
          ps.close();
      }
      catch (FileNotFoundException fnfex)
      {
    	  // Deve ter tratado no static constructor
    	  throw new RuntimeException("Erro interno no método LogarErro() : arquivo não encontrado.");
      }
  }


  private static String printStackTraceToString(Exception ex)
  {

   // Writer writer = new StringWriter();
      StringWriter sWriter = new StringWriter();
      ex.printStackTrace(new PrintWriter(sWriter));
      return sWriter.toString();
  }


  private static void  enviarErroParaPortalCronos(Document docOfertas, Element elmErros, String cdProdutoFornecedor, String mensagemErro) 
  {
      temErroGeralCotacao = true; 

      Element elmErro = docOfertas.createElement("Erro");
      elmErros.appendChild(elmErro);

      Element elmCdProdutoFornecedorErro = docOfertas.createElement("Cd_Produto_Fornecedor");
      elmCdProdutoFornecedorErro.appendChild(docOfertas.createTextNode(cdProdutoFornecedor));
      elmErro.appendChild(elmCdProdutoFornecedorErro);

      debugar(mensagemErro);
      Element elmMensagem = docOfertas.createElement("Mensagem");
      elmMensagem.appendChild(docOfertas.createTextNode(mensagemErro));
      elmErro.appendChild(elmMensagem);

  }



  private static void readCotacao(NodeList cotacoes, int i, DocumentBuilder docBuilder) throws SQLException, ParserConfigurationException, TransformerException
  {
	java.sql.Connection conn = null;
	java.sql.Statement stat = null;
	java.sql.ResultSet rSet = null;
    Document docOfertas = null;
    Element elmErros = null;

    try
    {  
	    Element element = (Element)cotacoes.item(i);
	
	    NodeList nlist = element.getElementsByTagName("Cd_Cotacao");
	    Element elm = (Element) nlist.item(0);
	    String cdCotacao = getCharacterDataFromElement(elm);
	    debugar("cdCotacao: " + cdCotacao);
	
	    nlist = element.getElementsByTagName("Cd_Comprador");
	    elm = (Element) nlist.item(0);
	    String cdComprador = getCharacterDataFromElement(elm);
	 // Debugar("cdComprador: " + cdComprador);
	
	 	// Tag <Cd_Condicao_Pagamento>123</Cd_Condicao_Pagamento> pulado, pois não serve para nada 

	  	nlist = element.getElementsByTagName("Tp_Frete");
	    elm = (Element) nlist.item(0);
	    String tpFrete = getCharacterDataFromElement(elm);
	
	 // Tags pulados, pois não servem para nada :
	 //    <Dt_Previsao_Entrega>21/04/2015</Dt_Previsao_Entrega>
	 //    <Dt_Inicio_Cotacao>16/04/2015 14:30:00</Dt_Inicio_Cotacao>
	 //    <Dt_Fim_Cotacao>20/04/2015 11:00:00</Dt_Fim_Cotacao>
	 //    <Dt_Cadastro>16/04/2015 14:29:46</Dt_Cadastro>

	  	NodeList produtos = element.getElementsByTagName("Produto");
	
	
	//  =====================================================================================
	//
	//  Confirmação de Recebimento da Cotação
	//
	//  =====================================================================================
	
	    Document docRecebimentosCotacoes = docBuilder.newDocument();
	
	    Element elmRecebimentoCotacao = docRecebimentosCotacoes.createElement("RecebimentoCotacao");
	    docRecebimentosCotacoes.appendChild(elmRecebimentoCotacao);
	
	    Element elmCdCotacao = docRecebimentosCotacoes.createElement("Cd_Cotacao");
	    elmCdCotacao.appendChild(docRecebimentosCotacoes.createTextNode(cdCotacao));
	    elmRecebimentoCotacao.appendChild(elmCdCotacao);
	
	    Element elmCdComprador = docRecebimentosCotacoes.createElement("Cd_Comprador");
	    elmCdComprador.appendChild(docRecebimentosCotacoes.createTextNode(cdComprador));
	    elmRecebimentoCotacao.appendChild(elmCdComprador);
	
	    Element elmCdFornecedor = docRecebimentosCotacoes.createElement("Cd_Fornecedor");
	    elmCdFornecedor.appendChild(docRecebimentosCotacoes.createTextNode(cnpjFornecedor));
	    elmRecebimentoCotacao.appendChild(elmCdFornecedor);
	
	    Element elmDtRecebimento = docRecebimentosCotacoes.createElement("Dt_Recebimento");
	    Date hoje = new Date();
	    elmDtRecebimento.appendChild(docRecebimentosCotacoes.createTextNode(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(hoje)));
	    elmRecebimentoCotacao.appendChild(elmDtRecebimento);
	
	    TransformerFactory transformerFactory = TransformerFactory.newInstance();
	    Transformer transformer = transformerFactory.newTransformer();
	    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	 // transformer.setOutputProperty(OutputKeys.METHOD, "xml");
	    transformer.setOutputProperty(OutputKeys.ENCODING, "iso-8859-1");
	    transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
	
	    final DOMSource source = new DOMSource(docRecebimentosCotacoes);
	    String filenameConfirmReceb = diretorioArquivosXml + String.format("REC_%s_%s_%s.xml", cnpjFornecedor, cdCotacao, new SimpleDateFormat("yyyyMMdd'_'HHmmss").format(hoje));
    	Files.deleteIfExists(Paths.get(filenameConfirmReceb));
    	final StreamResult result = new StreamResult(new File(filenameConfirmReceb));
	
	 // Output to console for testing
	 // StreamResult result = new StreamResult(System.out);
	
	    transformer.transform(source, result);
	
	    upload_File(enderecoBaseWebService + "cotacao/EnviaArquivosRecebimentoCotacao", new File(filenameConfirmReceb), "form1", username, senha) ;
	    
	//  =====================================================================================
	//
	//  Ofertas
	//
	//  =====================================================================================
	
	    docOfertas = docBuilder.newDocument();
	
	    elmErros = docOfertas.createElement("Erros");
	    String mensagemErro;
	
	
	    if (erroStaticConstructor != null)
	    {
	      enviarErroParaPortalCronos(docOfertas, elmErros, "", "Erro imprevisto ! " + erroStaticConstructor);
	    }
	
	
	    if (    !criterioVerificacaoEstoque.equals("QtdEstoqueMaiorOuIgualQtdSolicitada")
	         && !criterioVerificacaoEstoque.equals("QtdEstoqueMaiorZero")
	       )
	    {
	      enviarErroParaPortalCronos(docOfertas, elmErros, "", "Erro : configuração \"CriterioVerificacaoEstoque\" inválida ! Opções permitidas: \"QtdEstoqueMaiorOuIgualQtdSolicitada\" ou \"QtdEstoqueMaiorZero\".");
	    }
	
	    Element elmOfertasCotacao = docOfertas.createElement("OfertasCotacao");
	    docOfertas.appendChild(elmOfertasCotacao);
	
	    Element elmDtGravacao = docOfertas.createElement("Dt_Gravacao");
	    hoje = new Date();
	    elmDtGravacao.appendChild(docOfertas.createTextNode(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(hoje)));
	    elmOfertasCotacao.appendChild(elmDtGravacao);
	
	    Element elmCdCotacao2 = docOfertas.createElement("Cd_Cotacao");
	    elmCdCotacao2.appendChild(docOfertas.createTextNode(cdCotacao));
	    elmOfertasCotacao.appendChild(elmCdCotacao2);
	
	    Element elmCdComprador2 = docOfertas.createElement("Cd_Comprador");
	    elmCdComprador2.appendChild(docOfertas.createTextNode(cdComprador));
	    elmOfertasCotacao.appendChild(elmCdComprador2);
	
	    Element elmCdFornecedor2 = docOfertas.createElement("Cd_Fornecedor");
	    elmCdFornecedor2.appendChild(docOfertas.createTextNode(cnpjFornecedor));
	    elmOfertasCotacao.appendChild(elmCdFornecedor2);
	
	    String connectionString = null;
	    
	    if (tipoBancoDeDados.equals("Oracle"))
	    {
	      OracleDriver orclDriver = new OracleDriver() ; 
	      DriverManager.registerDriver( orclDriver ) ; 	
	      connectionString = "jdbc:oracle:thin:@" + enderecoIpServidorBancoDeDados + ":" + portaServidorBancoDeDados + ":" + instanciaBancoDeDados; 
	    }
	    else if (tipoBancoDeDados.equals("Firebird"))
	    {
	         FBDriver fbDriver = new FBDriver();
	         DriverManager.registerDriver(fbDriver); // Antigamente: Class.forName("org.firebirdsql.jdbc.FBDriver"); 
	         connectionString = "jdbc:firebirdsql://" + enderecoIpServidorBancoDeDados + ":" + portaServidorBancoDeDados + "/" + instanciaBancoDeDados;
	    }
   
	    conn = DriverManager.getConnection(connectionString, usernameBancoDeDados, senhaBancoDeDados ) ;	    
	    stat = conn.createStatement() ;
	    String sqlString = null;
	    boolean toNaoVerificarDemaisErros = false;
	    boolean existeCompradora = true;
	    String dsComprador = "";		
	    
	    
        if (siglaSistema.equals("APS"))
        {
        	sqlString = "select cpfcgc   "
                      + "  from cliente  "
                      + " where replace(replace(replace(cpfcgc, '.',''), '/',''), '-','') = '" + cdComprador + "'"
                      ;
        }
        else if (siglaSistema.equals("WinThor"))
        {
		    sqlString = "select (PCCLIENT.CLIENTE || ' - ' || nvl(PCCLIENT.ESTCOB, '')) "
		              + "  from PCCLIENT        "
		              + " where replace(replace(replace(PCCLIENT.CGCENT, '.',''), '/',''), '-','') = '" + cdComprador + "'"
		              ;
        }
	    // Para executar o SELECT direto no banco de dados, se precisar :
	    debugar(sqlString);
	
	    rSet = stat.executeQuery( sqlString ) ;
	    
	    if (rSet == null || !rSet.next()) 
	    {
	    	toNaoVerificarDemaisErros = true;
	    	existeCompradora = false;
	        enviarErroParaPortalCronos(docOfertas, elmErros, "", "Cotação " + cdCotacao + " " + NAO_OFERTADA_IMPACTO_SE_ALTERAR + "! O CNPJ " + cdComprador + " da empresa compradora não foi encontrado no sistema " + siglaSistema + " do fornecedor " + nomeFantasiaFornecedor + ".");
	    }
	    else
	    {
		      if (siglaSistema.equals("WinThor")) 
		      {
			      dsComprador = ( (rSet.getObject(1) == null) ? "" : rSet.getString(1) ) ; 
		      }	    	
	    }

	    
	    
	    
	    rSet = null;
	    
	    if (existeCompradora)
	    {
			if (siglaSistema.equals("APS"))
	        {
	        	sqlString = null;
	        }
	        else if (siglaSistema.equals("WinThor"))
	        {
			    sqlString = "select PCCLIENT.CGCENT "
			              + "  from PCCLIENT        "
			              + " where replace(replace(replace(PCCLIENT.CGCENT, '.',''), '/',''), '-','') = '" + cdComprador + "'"
			              + "   and PCCLIENT.BLOQUEIO   <> 'S'  "
			              + "   and PCCLIENT.DTEXCLUSAO is null "
			              ;
	        }
	
	        if (sqlString != null)
	        {
			    // Para executar o SELECT direto no banco de dados, se precisar :
			    debugar(sqlString);
			
			    rSet = stat.executeQuery( sqlString ) ;
			
			    if (rSet == null || !rSet.next()) 
			    {
			       // Se o cliente for bloqueado ou excluido, não enviar NENHUMA mensagem de erro, e nao gerar nenhum arquivo XML, 
			       // porém apenas Debugar (para verificação na primeira instalação deste programa no fornecedor)
		  	       debugar("Cotação " + cdCotacao + " " + NAO_OFERTADA_IMPACTO_SE_ALTERAR + "! A empresa compradora " + (dsComprador != "" ? dsComprador : cdComprador) + " está bloqueada ou desativada no sistema " + siglaSistema + " do fornecedor " + nomeFantasiaFornecedor + ".");
			       return; // aborta a cotação  atual e continua com a próxima cotação
			    }
	        } // if (sqlString != null)
        } // if (existeCompradora)

	    
        
	    rSet = null;

	    if (siglaSistema.equals("APS"))
        {
        	sqlString = "select codtipopag "
                      + "  from cliente    "
                      + " where replace(replace(replace(cpfcgc, '.',''), '/',''), '-','') = '" + cdComprador + "'"
                      ;
                   // + "   and codstatus = 1" // 1 = BLOQUEADO, no APS isso quer dizer apenas bloqueado para pagto. a prazo, e NÃO bloqueado para pagto. a vista !
        }
        else if (siglaSistema.equals("WinThor"))
        {
		    sqlString = "select PCCLIENT.CODPLPAG "
		              + "  from PCCLIENT          "
		              + " where replace(replace(replace(PCCLIENT.CGCENT, '.',''), '/',''), '-','') = '" + cdComprador + "'"
		              + "   and PCCLIENT.BLOQUEIO   <> 'S'  "
		              + "   and PCCLIENT.DTEXCLUSAO is null "
		              ;
        }
	    // Para executar o SELECT direto no banco de dados, se precisar :
	    debugar(sqlString);
	
	    rSet = stat.executeQuery( sqlString ) ;
	
	    String cdCondicaoPagamento = "";
	
	    if (rSet != null && rSet.next()) 
	    {
	      cdCondicaoPagamento = ( (rSet.getObject(1) == null) ? "" 
	    		                                              : (siglaSistema.equals("APS") ? Integer.toString(rSet.getInt(1))
	    		                                            		                        : (siglaSistema.equals("WinThor") ? rSet.getString(1)
	    		                                            		                        		                          : ""
	    		                                            		                          )
	    		                                                )
	    		                ) ;  
	    } 
	
	    if (cdCondicaoPagamento.equals("") && !toNaoVerificarDemaisErros)
	    {
	      enviarErroParaPortalCronos(docOfertas, elmErros, "", "Cotação " + cdCotacao + " " + NAO_OFERTADA_IMPACTO_SE_ALTERAR + "! A Condição de Pagamento da empresa compradora " + (dsComprador != "" ? dsComprador : cdComprador) + " não foi informada no sistema " + siglaSistema + " do fornecedor " + nomeFantasiaFornecedor + ".");
	    }
	
	
	
	    Element elmCdCondicaoPagamento = docOfertas.createElement("Cd_Condicao_Pagamento_Fornecedor");
	    elmCdCondicaoPagamento.appendChild(docOfertas.createTextNode(cdCondicaoPagamento));
	    elmOfertasCotacao.appendChild(elmCdCondicaoPagamento);
	
	    Element elmTpFrete = docOfertas.createElement("Tp_Frete_Fornecedor");
	    elmTpFrete.appendChild(docOfertas.createTextNode(tpFrete));
	    elmOfertasCotacao.appendChild(elmTpFrete);
	 
	    String strQtPrzEntrega = "";
        if (siglaSistema.equals("APS"))
 	      strQtPrzEntrega = "";
        else if (siglaSistema.equals("WinThor"))
   	      strQtPrzEntrega = "";
	
	    Element elmQtPrzEntrega = docOfertas.createElement("Qt_Prz_Entrega");
	    elmQtPrzEntrega.appendChild(docOfertas.createTextNode(strQtPrzEntrega));
	    elmOfertasCotacao.appendChild(elmQtPrzEntrega);
	
	    BigDecimal vlMinimoPedido = null;
		
	    rSet = null;

	    if (        siglaSistema.equals("APS")
	    		|| (siglaSistema.equals("WinThor") && !toUsarValorMinimoSistemaFornecedor )
	       )
        {
        	vlMinimoPedido = null; // Versão anterior : new BigDecimal(300.00); 
        }
        else if (siglaSistema.equals("WinThor"))
        {
		    sqlString = "select PCPLPAG.VLMINPEDIDO "
		              + "  from PCCLIENT            "
		              + "     , PCPLPAG             "
		              + " where replace(replace(replace(PCCLIENT.CGCENT, '.',''), '/',''), '-','') = '" + cdComprador + "'"
		              + "   and PCCLIENT.CODPLPAG   =  PCPLPAG.CODPLPAG "
		              + "   and PCCLIENT.BLOQUEIO   <> 'S'              "
		              + "   and PCCLIENT.DTEXCLUSAO is null             "
		              ;
		    // Para executar o SELECT direto no banco de dados, se precisar :
		    debugar(sqlString);
		
		    rSet = stat.executeQuery( sqlString ) ;
		
		    if (rSet != null && rSet.next()) 
		    {
		      vlMinimoPedido = ( (rSet.getObject(1) == null) ? null : rSet.getBigDecimal(1).setScale(2, BigDecimal.ROUND_HALF_UP)  ) ;  
		    }

		    if (vlMinimoPedido == null && !toNaoVerificarDemaisErros)
		    {
		    	// O Valor Mínimo pode ser R$ 0,00 porém não pode ser em branco porque assim o sistema não sabe 
		    	// se for R$ 0,00 ou se é para usar o Valor Mínimo geral do fornecedor cadastrado no Portal Cronos :
		        enviarErroParaPortalCronos(docOfertas, elmErros, "", "Cotação " + cdCotacao + " " + NAO_OFERTADA_IMPACTO_SE_ALTERAR + "! O Valor Mínimo para Entrega para a empresa compradora " + (dsComprador != "" ? dsComprador : cdComprador) + " não foi informado no sistema " + siglaSistema + " do fornecedor " + nomeFantasiaFornecedor + ". Portanto não foi posssível verificar se o valor seria R$ 0,00 ou se o valor seria igual ao Valor Mínimo geral do fornecedor cadastrado no Portal Cronos.");
		    }
        }
	
        
	
	
	    nf.setGroupingUsed(false);
	    nf.setMaximumFractionDigits(2);
	    nf.setMinimumFractionDigits(2);  
	
	    Element elmVlMinimoPedido = docOfertas.createElement("Vl_Minimo_Pedido");
	    elmVlMinimoPedido.appendChild(docOfertas.createTextNode( (vlMinimoPedido == null ? "" : nf.format(vlMinimoPedido))  ));
	    elmOfertasCotacao.appendChild(elmVlMinimoPedido);
	
	    Element elmDsObservacaoFornecedor = docOfertas.createElement("Ds_Observacao_Fornecedor");
	    elmDsObservacaoFornecedor.appendChild(docOfertas.createTextNode(ObsOfertasPadraoSeNaoTemNoSistema));
	    elmOfertasCotacao.appendChild(elmDsObservacaoFornecedor);
	
	
	    rSet = null;

	    if (siglaSistema.equals("APS"))
        {
        	sqlString = "select replace(precousado,'ç','c') "
                      + "  from cliente "
                      + " where replace(replace(replace(cpfcgc, '.',''), '/',''), '-','') = '" + cdComprador + "'";
        }
        else if (siglaSistema.equals("WinThor"))
        {
		    // PVENDA1 NUMBER(16,6) preco referente tabela 1
		    // PVENDA2 NUMBER(16,6) preco referente tabela 2
		    // PVENDA3 NUMBER(16,6) preco referente tabela 3
		    // PVENDA4 NUMBER(16,6) preco referente tabela 4
		    // PVENDA5 NUMBER(16,6) preco referente tabela 5
		    // PVENDA6 NUMBER(16,6) preco referente tabela 6
		    // PVENDA7 NUMBER(16,6) preco referente tabela 7
		
		    // OBS: Compor o campo pvenda com a seguinte forma
		    // 1. Acessar a tabela PCPLPAG (TABELA DE PLANO DE PAGAMENTO) atraves do campo CODPLPAG na tabela PCLIENT (Tabela de Clientes)
		    // 2. Acessar os seguintes campos:
		    //    NUMPR NUMBER(6,2) = O intervalo fica entre 1 e 7. Serve para identificar o final do campo PVENDAx
		    //    VLMINPEDIDO NUMBER(12,2) = Valor minimo para o pedido.
		
		    sqlString = "select PCPLPAG.NUMPR "
		              + "  from PCCLIENT, PCPLPAG "
		              + " where replace(replace(replace(PCCLIENT.CGCENT, '.',''), '/',''), '-','') = '" + cdComprador + "'"
		              + "   and PCCLIENT.CODPLPAG   =  PCPLPAG.CODPLPAG "
		              + "   and PCCLIENT.BLOQUEIO   <> 'S' "
		              + "   and PCCLIENT.DTEXCLUSAO is null "
		              ;
        }
	
	    // Para executar o SELECT direto no banco de dados, se precisar :
	    debugar(sqlString);
	
	    rSet = stat.executeQuery( sqlString ) ;
		
	    String tipoPrecoComprador = null;
	
	    if (rSet != null && rSet.next()) 
	    {
  	      tipoPrecoComprador = ( (rSet.getObject(1) == null) ? null 
  	    		                                             : (siglaSistema.equals("APS") ? rSet.getString(1) 
  	    		                                            		                       : (siglaSistema.equals("WinThor") ? rSet.getBigDecimal(1).toString()
  	    		                                            		                    		                             : null
  	    		                                            		                         )
  	    		                                               )
  	    		               ) ; 
	    }
	
	    if (tipoPrecoComprador == null && !toNaoVerificarDemaisErros)
	    {
	      enviarErroParaPortalCronos(docOfertas, elmErros, "", "Cotação " + cdCotacao + " " + NAO_OFERTADA_IMPACTO_SE_ALTERAR + "! O tipo de preço para a empresa compradora " + (dsComprador != "" ? dsComprador : cdComprador) + " não foi informado no sistema " + siglaSistema + " do fornecedor " + nomeFantasiaFornecedor + ".");
	    }
	    else if (    siglaSistema.equals("WinThor")
                      && tipoPrecoComprador != null 
     	              && !(    tipoPrecoComprador.equals("1")
		                    || tipoPrecoComprador.equals("2")
		                    || tipoPrecoComprador.equals("3")
		                    || tipoPrecoComprador.equals("4")
		                    || tipoPrecoComprador.equals("5")
		                    || tipoPrecoComprador.equals("6")
		                    || tipoPrecoComprador.equals("7")
	                  )
	            )
	    {
          // Apenas verificar isso na primeira instalação en cada empresa fornecedora,
          // pois a causa do "erro" pode ser que uma versão nova do WinThor tem uma coluna nova, então não é erro, 
          // e tb pode ser algum erro de verdade : 
	      debugar("Possível erro: tipo de preço (" + tipoPrecoComprador + ") imprevisto no sistema " + siglaSistema + " do fornecedor " + nomeFantasiaFornecedor + ". Valores previstos : 1, 2, 3, 4, 5, 6, 7. Favor verificar.");
	    }
	    
	
	    Integer numRegiaoWinThor = null;
	    rSet = null;
		
	    if (siglaSistema.equals("WinThor"))
	    {
		    sqlString = "select PCPRACA.NUMREGIAO "
		              + "  from PCPRACA           "
		              + "     , PCCLIENT          "
		              + " where PCPRACA.CODPRACA = PCCLIENT.CODPRACA "
		              + "   and replace(replace(replace(PCCLIENT.CGCENT, '.',''), '/',''), '-','') = '" + cdComprador + "'"
		              + "   and PCCLIENT.BLOQUEIO   <> 'S'  "
		              + "   and PCCLIENT.DTEXCLUSAO is null "
		              ;
		
		    // Para executar o SELECT direto no banco de dados, se precisar :
		    debugar(sqlString);
		
		    rSet = stat.executeQuery( sqlString ) ;
		
		    if (rSet != null && rSet.next()) 
		    {
		      numRegiaoWinThor = ( (rSet.getObject(1) == null) ? null : rSet.getInt(1)  ) ;  
		    }

		    if (numRegiaoWinThor == null && !toNaoVerificarDemaisErros)
		    {
		      enviarErroParaPortalCronos(docOfertas, elmErros, "", "Cotação " + cdCotacao + " " + NAO_OFERTADA_IMPACTO_SE_ALTERAR + "! A região da empresa compradora " + (dsComprador != "" ? dsComprador : cdComprador) + " não foi informada no sistema " + siglaSistema + " do fornecedor " + nomeFantasiaFornecedor + ".");
		    }
	    }
	
	
	    Element elmProdutos = docOfertas.createElement("Produtos");
        elmOfertasCotacao.appendChild(elmProdutos); // Para pelo menos gerar o tag vazio "<Produtos/>" no caso que o arquivo XML for gravado 
	
	    if (!temErroGeralCotacao)
	    {	
	      for (int j = 0; j < produtos.getLength(); j++) 
	      {
	        readProduto(produtos, j, docOfertas, elmProdutos, elmErros, stat, rSet, tipoPrecoComprador, numRegiaoWinThor);
	      }
	    }
	
	    elmOfertasCotacao.appendChild(elmErros);  // Para pelo menos gerar o tag vazio "<Erros/>" no caso que o arquivo XML for gravado
	    
	    debugar("elmProdutos.hasChildNodes() = " + elmProdutos.hasChildNodes());
	    debugar("elmErros.hasChildNodes()    = " + elmErros.hasChildNodes());
	
	    // elmProdutos e elmErros nunca são null se chegar aqui :
	    if (    elmProdutos.hasChildNodes()
	         || elmErros.hasChildNodes() 
	       )
	    { 
	    	final DOMSource sourceOfertas = new DOMSource(docOfertas);
		    String filenameOfertas = diretorioArquivosXml + String.format("OFE_%s_%s_%s.xml", cnpjFornecedor, cdCotacao, new SimpleDateFormat("yyyyMMdd'_'HHmmss").format(hoje));
	    	Files.deleteIfExists(Paths.get(filenameOfertas));
		    final StreamResult resultOfertas = new StreamResult(new File(filenameOfertas));
		    transformer.transform(sourceOfertas, resultOfertas);
		
		    upload_File(enderecoBaseWebService + "FornecedorCotacao/EnviaArquivosOfertasCotacao", new File(filenameOfertas), "form1", username, senha) ;
		 // Funcionou : uploadOfertas_File(enderecoBaseWebService + "cotacao/EnviaArquivosRecebimentoCotacao", new File(diretorioArquivosXml + "TesteWebServiceConfirmRecebCotacoes.xml"), "form1", username, senha) ;
		 // uploadOfertas_BodyXML(enderecoBaseWebService + "cotacao/EnviaArquivosRecebimentoCotacao", (diretorioArquivosXml + "TesteWebServiceConfirmRecebCotacoes.xml"), username, senha) ;
	    }
    }
    catch (java.lang.Exception ex) { 
      logarErro(ex, false);
      enviarErroParaPortalCronos(docOfertas, elmErros, null, printStackTraceToString(ex));  
    }
    finally { 
      if (stat != null) {
        try {
          stat.close() ;  // Isso fecha o rSet automaticamente também
        }
        catch (java.sql.SQLException e) {
        }
        stat = null  ;
      }
      if (rSet != null)  rSet = null  ;
      
	  if (conn != null) { 
	      try { 
	        if ( !conn.isClosed() ) {
	        	conn.close() ;
	        }
	      }
	      catch ( java.lang.Throwable t ) {  
	      }

	      conn = null ; 
	  }
    } // finally
  }



  private static void readProduto(NodeList produtos, int i, Document docOfertas, Element elmProdutos, Element elmErros, java.sql.Statement stat, java.sql.ResultSet rSet, String tipoPrecoComprador, Integer numRegiaoWinThor) throws SQLException
  {
    String mensagemErro;

    Element element = (Element)produtos.item(i);

    NodeList nlist = element.getElementsByTagName("Cd_Produto_Fornecedor");
    Element elm = (Element) nlist.item(0);
    String cdProdutoFornecedor = getCharacterDataFromElement(elm);

    nlist = element.getElementsByTagName("Cd_Produto");
    elm = (Element) nlist.item(0);
    String cdProduto = getCharacterDataFromElement(elm);

    debugar("cdProdutoFornecedor: " + cdProdutoFornecedor + ", cdProduto: " + cdProduto);

    // Pulando <Cd_Unidade_Compra> do XML de /ObtemCotacoes

    nlist = element.getElementsByTagName("Qt_Embalagem");
    elm = (Element) nlist.item(0);
    String qtEmbalagem = getCharacterDataFromElement(elm);
 // Debugar("qtEmbalagem: " + qtEmbalagem);

    nlist = element.getElementsByTagName("Qt_Produto");
    elm = (Element) nlist.item(0);
    String qtSolicitada = getCharacterDataFromElement(elm);
 // Debugar("qtSolicitada: " + qtSolicitada);

    // =============================================================================
    //
    // Verificar se o código de produto existe :
    //
    // =============================================================================

    String sqlString = null;
    rSet = null;
    
    if (siglaSistema.equals("APS"))
    {
    	sqlString = "select 1 "
                  + "  from produto "
                  + "       join prodfilho on produto.codproduto = prodfilho.codproduto "
                  + " where prodfilho.codprodfilho = " + cdProdutoFornecedor
                  + " ";
                  // No APS é normal que o produto pode ficar desativado temporariamente, 
                  // então neste momento não usar a condição + "   and produto.ativo          = 2 ".
    }
    else if (siglaSistema.equals("WinThor"))
    {
	    // 3. Acessar a tabela PCTABPR (TABELA DE PRECOS) atravez do campo PCTABPR.CODPROD (CODIGO DO PRODUTO) e PCTABPR.NUMREGIAO (NUMERO DA REGIAO) COM O VALOR 1
	    // 4. Compor o preco unitario PVENDA + NUMPR.
	    sqlString = "select 1 "
	              + "  from PCPRODUT "
	              + " where PCPRODUT.CODPROD    = " + cdProdutoFornecedor
	              + "   and PCPRODUT.REVENDA    = 'S' "
	              + "   and PCPRODUT.DTEXCLUSAO is null  ";
    }
    
    // Para executar o SELECT direto no banco de dados, se precisar :
    if (i == 0) debugar(sqlString);

    rSet = stat.executeQuery( sqlString ) ;

    if (rSet == null || !rSet.next()) 
    {
      enviarErroParaPortalCronos(docOfertas, elmErros, cdProdutoFornecedor, "O Código de Produto " + cdProdutoFornecedor + " do Fornecedor, informado na tela De-Para de Produtos no Portal Cronos, não existe no sistema " + siglaSistema + " do fornecedor " + nomeFantasiaFornecedor + ".");
      return; // Obs.: o produto pode ser abortado via return, porém a cotação nunca, para poder enviar o erro para o Portal Cronos
    }

    // =============================================================================
    //
    // Ofertar :
    //
    // =============================================================================

    rSet = null;

    if (siglaSistema.equals("APS"))
    {
    	sqlString = "select precoempresa." + tipoPrecoComprador + " "
                  + "  from precoempresa "
                  + "       join prodfilho on prodfilho.codprodfilho = precoempresa.codprodfilho "
                  + "       join produto   on produto.codproduto     = prodfilho.codproduto ";
    }
    else if (siglaSistema.equals("WinThor"))
    {
	    // 3. Acessar a tabela PCTABPR (TABELA DE PRECOS) atravez do campo PCTABPR.CODPROD (CODIGO DO PRODUTO) e PCTABPR.NUMREGIAO (NUMERO DA REGIAO) COM O VALOR 1
	    // 4. Compor o preco unitario PVENDA + NUMPR.
    	// 5. A coluna PCPRODUT.CODSEC está NOT NULL, porém a coluna PSECAO.QTMAX está NULO, 
    	//    então podemos fazer uma inner join com PSECAO.
	    sqlString = "select (PCTABPR.PVENDA" + tipoPrecoComprador + " * ((nvl(PCSECAO.QTMAX, 0)/100) + 1)) " 
	              + "  from PCTABPR  "
	              + "     , PCPRODUT "
	              + "     , PCSECAO  "
	              ;
    }

    if (toVerificarEstoque)
    {
        if (siglaSistema.equals("APS"))
        {
        	sqlString += "       join estoqueempresa  on estoqueempresa.codprodfilho = precoempresa.codprodfilho ";

            if (criterioVerificacaoEstoque.equals("QtdEstoqueMaiorOuIgualQtdSolicitada"))
            	sqlString += "                        and estoqueempresa.estoque >= " + qtSolicitada;
            else if (criterioVerificacaoEstoque.equals("QtdEstoqueMaiorZero"))
            	sqlString += "                        and estoqueempresa.estoque > 0 ";
        }
        else if (siglaSistema.equals("WinThor"))
        {
           sqlString += "       , PCEST  ";
        }
    }

    if (siglaSistema.equals("APS"))
    {
    	sqlString += " where precoempresa.codprodfilho = " + cdProdutoFornecedor
                  +  "   and produto.ativo             = 2 "
                  +  "   and produto.ativopesq         = 1 ";
    }
    else if (siglaSistema.equals("WinThor"))
    {
	    sqlString += " where PCTABPR.CODPROD   = " + cdProdutoFornecedor
	              +  "   and PCTABPR.NUMREGIAO = " + Integer.toString((numRegiaoWinThor)) 
	              +  "   and PCPRODUT.CODPROD = PCTABPR.CODPROD "
	              +  "   and PCPRODUT.REVENDA = 'S' "
	              +  "   and PCPRODUT.DTEXCLUSAO is null "
	              +  "   and PCPRODUT.CODSEC = PCSECAO.CODSEC "
	           // +  "   and PCTABPR.CODPLPAG = " + cdCondicaoPagamento
	              ;
	
	    if (toVerificarEstoque)
	    {
	      sqlString += "   and PCEST.CODPROD   = PCTABPR.CODPROD "
	                +  "   and PCEST.CODFILIAL = 1               "
	                ;
	  
	      if (criterioVerificacaoEstoque.equals("QtdEstoqueMaiorOuIgualQtdSolicitada"))
	        sqlString += "   and (nvl(PCEST.QTESTGER,0) - nvl(PCEST.QTRESERV,0) - nvl(PCEST.QTPENDENTE,0) - nvl(PCEST.QTBLOQUEADA,0)) >= " + qtSolicitada;
	      else if (criterioVerificacaoEstoque.equals("QtdEstoqueMaiorZero"))
	        sqlString += "   and (nvl(PCEST.QTESTGER,0) - nvl(PCEST.QTRESERV,0) - nvl(PCEST.QTPENDENTE,0) - nvl(PCEST.QTBLOQUEADA,0)) > 0 ";
	   
	    } // if (toVerificarEstoque)
    }

    rSet = stat.executeQuery( sqlString ) ;

    BigDecimal preco = null;

    if (rSet != null && rSet.next()) 
    {
    	// Diferente de C# (Math.Round()), setScale() automaticamente não faz nada no caso do APS que só tem 
    	// 2 decimais na base de dados, e faz round para 4 no caso do WinTor automaticamente quando se aplica :
        preco = ( (rSet.getObject(1) == null) ? null : rSet.getBigDecimal(1).setScale(4, BigDecimal.ROUND_HALF_UP)  ) ;  
    }

    nf.setGroupingUsed(false);
    nf.setMaximumFractionDigits(4);
    nf.setMinimumFractionDigits(4);

    if (preco == null)
    {
    	if (toVerificarEstoque)
            debugar("Tipo de preço " + tipoPrecoComprador + ": preço ou estoque não encontrado para produto " + cdProdutoFornecedor + " no sistema " + siglaSistema + " do fornecedor " + nomeFantasiaFornecedor + ".");
    	else
            debugar("Tipo de preço " + tipoPrecoComprador + ": preço não encontrado para produto " + cdProdutoFornecedor + " no sistema " + siglaSistema + " do fornecedor " + nomeFantasiaFornecedor + ".");

    	debugar(sqlString);
    	// se não tiver preço ou estoque, não ofertar o produto e continuar com o próximo produto:
        return;
    }
    else if (i == 0) 
    	debugar(sqlString);
    else
    	debugar("Preço: " + nf.format(preco));
     

    Element elmProduto = docOfertas.createElement("Produto");
    elmProdutos.appendChild(elmProduto);

    Element elmCdProdutoFornecedor = docOfertas.createElement("Cd_Produto_Fornecedor");
    elmCdProdutoFornecedor.appendChild(docOfertas.createTextNode(cdProdutoFornecedor));
    elmProduto.appendChild(elmCdProdutoFornecedor);

    Element elmCdProduto = docOfertas.createElement("Cd_Produto");
    elmCdProduto.appendChild(docOfertas.createTextNode(cdProduto));
    elmProduto.appendChild(elmCdProduto);

    Element elmQtEmbalagem = docOfertas.createElement("Qt_Embalagem");
    elmQtEmbalagem.appendChild(docOfertas.createTextNode(qtEmbalagem));
    elmProduto.appendChild(elmQtEmbalagem);

    Element elmVlPreco = docOfertas.createElement("Vl_Preco");
    elmVlPreco.appendChild(docOfertas.createTextNode( (preco == null ? "" : nf.format(preco)) ));
    elmProduto.appendChild(elmVlPreco);

    String dsObservacaoProduto = "";

    Element elmDsObservacaoProduto = docOfertas.createElement("Ds_Obs_Oferta_Fornecedor");
    elmDsObservacaoProduto.appendChild(docOfertas.createTextNode(dsObservacaoProduto));
    elmProduto.appendChild(elmDsObservacaoProduto);
  }



  public static void downloadCotacoes(String url, String cnpjFornecedor, String username, String senha)   
  {
 
    try 
    {
		Client client = Client.create();
	
		WebResource webResource = client.resource(url);
	    client.addFilter(new HTTPBasicAuthFilter(username, senha));
	
		ClientResponse response = webResource.accept("application/xml")
	                                             .get(ClientResponse.class);
	
        String strXmlRecebido = response.getEntity(String.class);    	
		debugar("Output from " + url + " .... \n");
		debugar(strXmlRecebido);

		if (response.getStatus() != 200 && response.getStatus() != 202) {
	        client.destroy();
		   throw new Exception("\r\n  Erro! Favor verificar todas as configurações no  arquivo \"" 
	                          + NOME_ARQUIVO_PROPERTIES 
	                          + "\" ! \r\n    Erro: HTTP Status Code = " 
	                          + response.getStatus() 
	                          + " (" + response.getClientResponseStatus().getReasonPhrase() 
	                          + ") ao chamar o web service " 
	                          + url 
	                          + " : \r\n"
	                          + strXmlRecebido);
		}
	
        client.destroy();


        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);

        DocumentBuilder docBuilder = factory.newDocumentBuilder();

        InputSource is = new InputSource();
        is.setCharacterStream(new StringReader(strXmlRecebido));

        Document xmlCotacoes = docBuilder.parse(is);

        NodeList cotacoes = xmlCotacoes.getElementsByTagName("Cotacao");


        for (int i = 0; i < cotacoes.getLength(); i++) 
        { 
            temErroGeralCotacao = false;            
            readCotacao(cotacoes, i, docBuilder);
        }

    }
/*
    catch (ParserConfigurationException pce) 
    {
	pce.printStackTrace();
    } 
    catch (TransformerException tfe)  
    {
	tfe.printStackTrace();
    }
    catch (SQLException sqlex)
    {
	sqlex.printStackTrace();
    }
*/
    catch (Exception ex) 
    {
        logarErro(ex, false);
    }
  }



  public static void upload_File(String url, File f, String formName, String username, String senha) {

/* Formato retornado pelo web service :
 
  <xml version="1.0" encoding="UTF-8">
  <arquivos>
     <arquivo nome="Arquivo 1">
        <erros_impedindo_recebimento_xml>
            <erro>Erro 1</erro>
            <erro>Erro 2</erro>
        </erros_impedindo_recebimento_xml>
        <avisos_nao_impedindo_recebimento_xml>
            <aviso>Aviso 1</aviso>
            <aviso>Aviso 2</aviso>
        </avisos_nao_impedindo_recebimento_xml>
    <arquivo>
    <arquivo nome="Arquivo 2">
       <erros_impedindo_recebimento_xml>
            <erro>Erro 1</erro>
            <erro>Erro 2</erro>
        </erros_impedindo_recebimento_xml>
        <avisos_nao_impedindo_recebimento_xml>
            <aviso>Aviso 1</aviso>
            <aviso>Aviso 2</aviso>
        </avisos_nao_impedindo_recebimento_xml>
    <arquivo>
  </arquivos>
</xml>
  
 */
	  
	    ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);
        client.addFilter(new HTTPBasicAuthFilter(username, senha));
 
        WebResource resource = client.resource(url);
 
        FormDataMultiPart multiPart = new FormDataMultiPart();
        if (f != null) 
        {
            multiPart.bodyPart(new FileDataBodyPart("file", f,
                    MediaType.APPLICATION_OCTET_STREAM_TYPE));
        }
 
        ClientResponse clientResp = resource.type(MediaType.MULTIPART_FORM_DATA_TYPE)
        		                                  .post(ClientResponse.class, multiPart);
        
        debugar("HTTP Status Code = " + clientResp.getClientResponseStatus().getStatusCode() + " (" + clientResp.getClientResponseStatus().getReasonPhrase() + ")");

        // Neste caso JAXB seria mais trabalhoso do que SAX e DOM,
    	// então não usar um Entity :
        String xmlResposta = clientResp.getEntity(String.class);
        debugar("HTTP Response = " + xmlResposta) ;
        
        if (    clientResp.getClientResponseStatus().getStatusCode() != 200 
             && clientResp.getClientResponseStatus().getStatusCode() != 202)
        {
            client.destroy();
            // Não fazer throw new RuntimeException, porém continuar com a próxima cotação :
        	logarErro("Erro! HTTP Status Code = " + clientResp.getClientResponseStatus().getStatusCode() + " (" + clientResp.getClientResponseStatus().getReasonPhrase() + ")");
            logarErro("HTTP Response = " + xmlResposta) ;
        }
        else
        {
        }
        	
        client.destroy();
  }



 

  public static void uploadOfertas_BodyXML(String url, String nomeArquivo, String username, String senha) 
  {
    Client client = Client.create();
    WebResource webResource = client.resource(url);
    client.addFilter(new HTTPBasicAuthFilter(username, senha));


    // Copiado de : http://www.coderanch.com/t/551977/Web-Services/java/Jersey-RESTful-web-service-post

    String response = webResource.type(MediaType.APPLICATION_XML)
                                 .accept(MediaType.TEXT_PLAIN)
                                 .entity(new File(nomeArquivo))
                                 .post(String.class);
    debugar("response : " + response);

   }

    public static void main(String[] args)
    {    
   // criarTabelasTeste();

	  LocalDateTime horaInicio = LocalDateTime.now();

      downloadCotacoes(enderecoBaseWebService + "cotacao/ObtemCotacoesGET?cdFornecedor=" + cnpjFornecedor + "&dataInicio=", cnpjFornecedor, username, senha);

  	  nf.setMinimumIntegerDigits(2);
  	  nf.setMaximumFractionDigits(0);
	  LocalDateTime horaFim = LocalDateTime.now();
	  long HorasExecucao = Duration.between(horaInicio, horaFim).toHours(); // inclui os dias em horas
	  long MinutosExecucao = Duration.between(horaInicio, horaFim).toMinutes() % 60;
	  long SegundosExecucao = Duration.between(horaInicio, horaFim).getSeconds() % 60;
	  String tempoExecucao = nf.format(HorasExecucao) + ":" + nf.format(MinutosExecucao) + ":" + nf.format(SegundosExecucao);
	  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
	  
	  try
	  {
	      BufferedWriter bWriter = new BufferedWriter(new FileWriter(diretorioArquivosXml + "TemposExecução.log", true));
	      bWriter.append(horaInicio.format(formatter) + " - Tempo de Execução: " + tempoExecucao);
	      bWriter.newLine();
	      bWriter.flush();
	      bWriter.close();
	  }
  	  catch (IOException ioe)
	  {
  		  ioe.printStackTrace();
	  }
    }

}
