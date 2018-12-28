package pcronos.integracao.fornecedor;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
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



public class Utils {

	public static TransformerFactory transformerFactory;

	
	public static boolean isNullOrBlank(String s)
	{
	  return (s == null || s.trim().equals(""));
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

}
