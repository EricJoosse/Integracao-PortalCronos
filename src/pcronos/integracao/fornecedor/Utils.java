package pcronos.integracao.fornecedor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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
	
}
