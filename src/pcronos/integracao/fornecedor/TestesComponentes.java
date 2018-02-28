package pcronos.integracao.fornecedor;

import java.util.Date;
import java.util.Properties;
import java.util.Locale;
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
import java.net.URI;
import javax.ws.rs.core.MediaType;
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
import pcronos.integracao.Criptografia;

import org.firebirdsql.jdbc.FBDriver   ;
import java.sql.DriverManager          ;
import java.sql.Connection             ; 
import java.sql.SQLException           ;

public class TestesComponentes {

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
		  
	  public static void main(String[] args) throws Exception {
		java.sql.Connection conn = null;
		java.sql.Statement stat = null;
		java.sql.ResultSet rSet = null;

		try
        {
			System.out.println("cript = " + Criptografia.encrypt("a"));
			System.out.println("decript = " + Criptografia.decrypt("ÅÖTÆ"));
			
			
         // BigDecimal bigDec = new BigDecimal(0);
			BigDecimal bigDec = new BigDecimal("0.00");
			if (bigDec.compareTo(BigDecimal.ZERO) == 0)
				System.out.println("bigDec == 0");
			else
				System.out.println("bigDec != 0, bigDec = " + bigDec.toString());

			
			testNullParam(null, "abc");
			testNullParam(null, "abc", true);
		  
          if (1 == 1) return;

          File dir = new File("C:/temp/a/b/c/log");
          dir.mkdirs();

	      if (1 == 1) return;

		  System.out.println("Teste jar -cp .jar class");
		  if (1 == 1) return;
			 
	      String a = "abc";
	      String b = "xyz";
	
	      String c = a;
	
	      c += b; 
	
	      System.out.println("Teste Scheduler - " + new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new Date()));
	
	
	
	   // Float vlMinimoPedido = null;
	   // System.out.println(   vlMinimoPedido == null ? "" : Float.toString(vlMinimoPedido)   );
	
	   // vlMinimoPedido = 1.0f;
	   // System.out.println(   vlMinimoPedido == null ? "" : Float.toString(vlMinimoPedido)   );
	
	
	      Locale locale = new Locale("pt", "BR");
	      NumberFormat nf = NumberFormat.getInstance(locale);
	      nf.setGroupingUsed(false);
	      nf.setMaximumFractionDigits(2);
	      nf.setMinimumFractionDigits(2);
	
	      BigDecimal vlMinimoPedido = null;
	   // System.out.println( "nf.format(vlMinimoPedido) = " + (vlMinimoPedido == null ? "null" : nf.format(vlMinimoPedido)) );
	
	      vlMinimoPedido = new BigDecimal(0);
	   // System.out.println( "vlMinimoPedido.setScale(2, BigDecimal.ROUND_HALF_UP) = " + vlMinimoPedido.setScale(2, BigDecimal.ROUND_HALF_UP));
	   // System.out.println( "nf.format(vlMinimoPedido) = " + (vlMinimoPedido == null ? "null" : nf.format(vlMinimoPedido)) );
	
	
	      vlMinimoPedido = new BigDecimal(0.1);
	   // System.out.println( "vlMinimoPedido.setScale(2, BigDecimal.ROUND_HALF_UP) = " + vlMinimoPedido.setScale(2, BigDecimal.ROUND_HALF_UP));
	   // System.out.println( "nf.format(vlMinimoPedido) = " + (vlMinimoPedido == null ? "null" : nf.format(vlMinimoPedido)) );
	
	
	      Integer numRegiaoWinThor = null;
	   // System.out.println( "Integer.toString(((int)numRegiaoWinThor)) = " + Integer.toString(((int)numRegiaoWinThor)));

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
