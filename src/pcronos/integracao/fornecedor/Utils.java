package pcronos.integracao.fornecedor;

import java.io.Reader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.xml.transform.TransformerFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import pcronos.integracao.DefaultCharsetException;
import pcronos.integracao.EmailAutomatico;

import org.hibernate.Session;
import org.hibernate.jdbc.Work;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.ibatis.jdbc.ScriptRunner;


public class Utils {

	public static TransformerFactory transformerFactory;

	
	public static boolean isNullOrBlank(String s)
	{
	  return (s == null || s.trim().equals(""));
	}

	public static String replaceNull(String input) 
	{
		  return input == null ? "" : input;
	}
	
	public static String replaceNull(Integer input) 
	{
		  return input == null ? "" : input.toString();
	}
	
	public static String rpad(String txt, int tamanho)
	{
		return String.format("%-" + Integer.toString(tamanho) + "s", txt);
	}
	
	public static String transformarXmlParaLegivel(Document xmlIlegivel) throws TransformerException
	{
        transformerFactory = TransformerFactory.newInstance();
	    Transformer transformerIlegivel = transformerFactory.newTransformer();
	    transformerIlegivel.setOutputProperty(OutputKeys.ENCODING, "iso-8859-1");
		transformerIlegivel.setOutputProperty(OutputKeys.INDENT, "yes");
        transformerIlegivel.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        final DOMSource sourceIlegivel = new DOMSource(xmlIlegivel);
        final StreamResult resultLegivel = new StreamResult(new StringWriter());
        transformerIlegivel.transform(sourceIlegivel, resultLegivel);
        return resultLegivel.getWriter().toString();

	}

	public static String transformarXmlParaLegivel(Element elementIlegivel) throws TransformerException
	{
        transformerFactory = TransformerFactory.newInstance();
	    Transformer transformerIlegivel = transformerFactory.newTransformer();
	    transformerIlegivel.setOutputProperty(OutputKeys.ENCODING, "iso-8859-1");
		transformerIlegivel.setOutputProperty(OutputKeys.INDENT, "yes");
        transformerIlegivel.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        final DOMSource sourceIlegivel = new DOMSource(elementIlegivel);
        final StreamResult resultLegivel = new StreamResult(new StringWriter());
        transformerIlegivel.transform(sourceIlegivel, resultLegivel);
        return resultLegivel.getWriter().toString();

	}

	public static String transformarXmlParaLegivel(String stringIlegivel) throws TransformerException, ParserConfigurationException, IOException, SAXException
	{
		if (isNullOrBlank(stringIlegivel))
			return new String("");
		
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        DocumentBuilder docBuilder = factory.newDocumentBuilder();
        InputSource is = new InputSource();
        is.setCharacterStream(new StringReader(stringIlegivel));
        Document xmlIlegivel = docBuilder.parse(is);

        transformerFactory = TransformerFactory.newInstance();
	    Transformer transformerIlegivel = transformerFactory.newTransformer();
	    transformerIlegivel.setOutputProperty(OutputKeys.ENCODING, "iso-8859-1");
		transformerIlegivel.setOutputProperty(OutputKeys.INDENT, "yes");
        transformerIlegivel.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        final DOMSource sourceIlegivel = new DOMSource(xmlIlegivel);
        final StreamResult resultLegivel = new StreamResult(new StringWriter());
        transformerIlegivel.transform(sourceIlegivel, resultLegivel);
        return resultLegivel.getWriter().toString();

	}

	public static String printStackTraceToString(Exception ex)
	{
	
	   // Writer writer = new StringWriter();
	      StringWriter sWriter = new StringWriter();
	      ex.printStackTrace(new PrintWriter(sWriter));
	      return sWriter.toString();
	}
 
	public static String getDefaultCharsetJVM() {
		return Charset.defaultCharset().displayName();
	}

	public static String getTemplateEmail(String tipoEmail) throws IOException, DefaultCharsetException
	{
		if (!getDefaultCharsetJVM().equals("windows-1252"))
		{
			throw new DefaultCharsetException(getDefaultCharsetJVM());
		}
		
      	String caminhoTemplate = Constants.DIR_TEMPLATES_EMAIL + tipoEmail;
      	 
     	return new String(Files.readAllBytes(Paths.get(caminhoTemplate)), Charset.forName("windows-1252"));

       	
      	 // Alternativo: 
     // ByteBuffer bb = ByteBuffer.wrap(Files.readAllBytes(Paths.get(caminhoTemplate)));
     // CharBuffer cb = Charset.forName("windows-1252").decode(bb); // StandardCharsets.UTF_8 
     // return cb.toString();
        
      	
      	
      	// Usar o seguinte no caso que vai dar erro OutOfMemory um dia:
//    	BufferedReader br = new BufferedReader(new FileReader(caminhoTemplate));
//    	String seq = "";
//    	for (String line; (line = br.readLine()) != null; )
//    	    seq += line;	    	
//      return seq;

//      	String body = "";
//	    	BufferedReader br = new BufferedReader(new FileReader(caminhoTemplate));
//	    	try 
//	    	{
//	    	    String linha = br.readLine();
//
//	    	    while (linha != null)
//	    	    {
//    	           linha = br.readLine();
//    	           body += linha;
//	    	    }
//	    	    
//	    	} 
//	    	finally 
//	    	{
//	    	    br.close();
//	    	}
//	    	return body;
	
	}
	
	public static String getIsAmbienteNuvem() throws Exception {
		boolean toDebugar = false;
		
	    String conteudoArquivo = new String(Files.readAllBytes(Paths.get("bin/IsAmbienteNuvem.bat")), Charset.forName("windows-1252"));
	    if (toDebugar) System.out.println("conteudoArquivo = " + conteudoArquivo);

	    conteudoArquivo = conteudoArquivo.replace("\r\n", "");
	    if (toDebugar) System.out.println("conteudoArquivo = " + conteudoArquivo);
	    
	    conteudoArquivo = conteudoArquivo.replace("SET IsAmbienteNuvem=", "");
	    if (toDebugar) System.out.println("conteudoArquivo = " + conteudoArquivo);

	    if (!conteudoArquivo.equals("0") && !conteudoArquivo.equals("1"))
	       throw new Exception("Conteúdo inválido no arquivo bin/IsAmbienteNuvem.bat!");
	  
	    return conteudoArquivo;
    }
			
	public static byte calcularMinutoAgendamento(boolean isAmbienteNuvem, Integer idFornecedorNaoNuvem) throws Exception {
		// Gerar minuto inteiro apenas de 0 até 14 pois "15" é a mesma coisa como "0"

		
		byte minIni = 0;  // 00 a partir da versão 3.1.0 
		                   // 04 em todos os fornecedores até a versão 3.0.0
						   // 19 na versão 3.0.0

		
		// Solução temporária e rápida feita na versão 3.0.0:
		
		//if (nmFornecedor.equals("Marizpan") || nmFornecedor.toLowerCase().equals("varig"))
		//minIni = minIni + 0;
		//else if (nmFornecedor.equals("Atacamax") || nmFornecedor.toLowerCase().equals("vasp"))
		//minIni = minIni + 7;
		//else 
		//minIni = minIni + 12;
		
		
		if (isAmbienteNuvem)
		{
			// A partir da versão 3.1.0 incrementar 1 minuto, pois muitas vezes o processamento demora menos de 1 minuto:
			File dirConfig = new File(Constants.DIR_ARQUIVOS_PROPERTIES); 
			int qtdFornecedores = 0;
			
			for (final File file : dirConfig.listFiles()) 
			{
				if (     file.getName().startsWith("Integração APS - Portal Cronos.") 
					  && file.getName().endsWith(".properties") 
					  && file.getName().toLowerCase().indexOf("copy")  == -1 
					  && file.getName().toLowerCase().indexOf("cópia") == -1 
					  && file.getName().toLowerCase().indexOf("copia") == -1 
					  && file.getName().toLowerCase().indexOf("backup")  == -1 
					  && file.getName().toLowerCase().indexOf("bck") == -1 
					  && file.getName().toLowerCase().indexOf("template") == -1 
				   ) 
				{
				   qtdFornecedores += 1;
				}
			}			

			// Separar os fornecedores por intervalos de 1 minuto:
			byte intervaloMinutos = 1;
			minIni = (byte)Math.floorMod((qtdFornecedores * intervaloMinutos), 15);
		} // if (isAmbienteNuvem)
		else 
		{
			// A partir da versão 3.1.0 usar intervalos de 1 minuto, pois muitas vezes o processamento demora menos de 1 minuto:
			
			if (idFornecedorNaoNuvem == null)
				throw new Exception("Erro interno! idFornecedor == null na chamada de calcularMinutoAgendamento()!");		
			else if (idFornecedorNaoNuvem == -1)
			{
			    minIni = 4; // Solicitado por Leão para espalhar os processamentos dos fornecedores 
			                // mais tarde, depois da otimização de performance dos web services e do monitoramento.
			}
			else
			{
				// A seguinte fórmula é uma forma para gerar o minuto semi-aleatoriamente 
				// de forma que o mesmo pode ser reproduzido por relatórios e Unidades de Teste:
				minIni = (byte)Math.floorMod(idFornecedorNaoNuvem, 15);
			 // minIni = (byte)(idFornecedor % 15); // Não funciona com números negativos
			}
		}

		
		if (minIni < 0 || minIni > 14)
			throw new Exception("Erro interno! O intervalo do minuto do agendamento só pode ser um número inteiro de 0 atá 14!");		

		return minIni;
	}

	public static String displayFilesize(long fileSize) {
        if (fileSize <= 0) {
            return "0";
        } else {
            String[] fileUnit = new String[] {"B","KB","MB","GB","TB"};
            int group = (int)(Math.log10(fileSize) / Math.log10(1024));
            return new DecimalFormat("#,##0.#").format(fileSize / Math.pow(1024, group)) + " " + fileUnit[group];
        }
    }
	
	public static String getNomeServidor() {
        try {
			return InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException e) {
			return "Desconhecido";
		}
	}
	
	public static void executarScriptEmConexaoHibernate(Session session, String nomeScript) {
		
		// O seguinte é uma forma para executar qualquer comando JDBC usando a conexão JDBC 
		// por baixo da conexão Hibernate, sem usar as Entidades Hibernate:
		session.doWork(new Work() {
		     
		    @Override
		    public void execute(Connection conn) throws SQLException {
		    	try
		    	{
		    		// A documentação do ibatis e do mybatis não diz com quais bases de dados o ScriptRunner funciona (Oracle, Sybase, etc)
		    		// porém foi testado que pelo menos o script "Criar tabelas Instalador e Monitorador.sql" funciona com SQL Server
		    		// usando mybatis-3.4.5:
			        ScriptRunner sr = new ScriptRunner(conn);
			        Reader reader = new BufferedReader(new FileReader("C:\\PCronos\\Integração Fornecedor - Portal Cronos\\scripts\\" + nomeScript));
			        sr.setSendFullScript(true);
			        sr.runScript(reader);
		    	}
		    	catch (Exception ex)
		    	{
		    		System.out.println(ex.getMessage());
		    	}
		    }
		});
	}
}

