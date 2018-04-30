package pcronos.integracao;

import java.io.UnsupportedEncodingException;
//import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import java.util.regex.Pattern;

public class Criptografia {

	public static String encrypt(String strClearText) throws Exception
	{
		String key = "lKj@#%$&BDg84952"; // 128 bit key
	//	String key = "Bar12345Bar12345"; // 128 bit key
		return doubleEncryption_Blowfish_Base64(strClearText, key, "UTF-8");
	 // return encrypt(strClearText, key);
	}

	
	public static String decrypt(String strEncrypted, boolean toDebugar) throws Exception
	{
		String key = "lKj@#%$&BDg84952"; // Mesmo 128 bit key
		return doubleDecryption_Base64_Blowfish(strEncrypted, key, "UTF-8", toDebugar);
	 // return decrypt(strEncrypted, key);
	}
	

	// O seguinte dá erro no caso da senha "usercronos01": 

//    public static String encrypt(String strClearText, String strKey) throws Exception
//	{
//		String strData="";
//		
//		try {
//			SecretKeySpec skeyspec=new SecretKeySpec(strKey.getBytes(),"Blowfish");
//			Cipher cipher=Cipher.getInstance("Blowfish");
//			cipher.init(Cipher.ENCRYPT_MODE, skeyspec);
//			byte[] encrypted=cipher.doFinal(strClearText.getBytes());
//			strData=new String(encrypted);
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new Exception(e);
//		}
//		return strData;
//	}
	

    
	// O seguinte dá erro no caso da senha "usercronos01": 

//    public static String decrypt(String strEncrypted, String strKey) throws Exception
//	{
//		String strData="";
//		
//		try {
//			SecretKeySpec skeyspec=new SecretKeySpec(strKey.getBytes(),"Blowfish");
//			Cipher cipher=Cipher.getInstance("Blowfish");
//			cipher.init(Cipher.DECRYPT_MODE, skeyspec);
//			byte[] decrypted=cipher.doFinal(strEncrypted.getBytes());
//			strData=new String(decrypted);
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new Exception(e);
//		}
//		return strData;
//	}
	
    
    public static String byteArrayToHex(byte[] bytes) throws UnsupportedEncodingException 
    {
        return Hex.encodeHexString(bytes);
    }

    
    public static byte[] hexToByteArray(String hex) throws DecoderException 
    {
        return Hex.decodeHex(hex.toCharArray());
    }

    
    public static String doubleEncryption_Blowfish_Base64(String to_encrypt, String key, String charEncoding) throws Exception
    {
        //charEncoding="UTF-8";
        Cipher cipher;
         try {
            byte[] encodedBytes =to_encrypt.getBytes(charEncoding);       
           
            if(encodedBytes.length % 8 != 0){ //not a multiple of 8
                System.out.println("encodedBytes is not padded properly in 8 bits");
                //create a new array with a size which is a multiple of 8
                byte[] padded = new byte[encodedBytes.length + 8 - (encodedBytes.length % 8)];
                //copy the old array into it
                System.arraycopy(encodedBytes, 0, padded, 0, encodedBytes.length);
                encodedBytes = padded;
            }else{
                System.out.println("encodedBytes is padded properly in 8 bits");
            }
           
            byte[] keyBytes=key.getBytes(charEncoding);
            SecretKeySpec secretkey = new SecretKeySpec(keyBytes, "Blowfish");                  
            cipher = Cipher.getInstance("Blowfish/ECB/NoPadding");
            cipher.init(Cipher.ENCRYPT_MODE, secretkey);      
            byte[] encryptedBlowFish = cipher.doFinal(encodedBytes);
            byte[] encryptedFinalBase64 = Base64.encodeBase64(encryptedBlowFish);    
            return new String(encryptedFinalBase64);
         // return new String(byteArrayToHex(encryptedFinalBase64));
         } catch (Exception e) {
              e.printStackTrace();              
  			throw new Exception(e);
           // return null;
         }
    }

    
    public static String doubleDecryption_Base64_Blowfish(String to_decrypt, String key, String charSet, boolean toDebugar) throws Exception
    {          
        // charSet="UTF-8";
         Cipher cipher;
          try {
              byte[] decodedBytes = Base64.decodeBase64(to_decrypt.getBytes(charSet));
           // byte[] decodedBytes = Base64.decodeBase64(hexToByteArray(to_decrypt.getBytes(charSet)));
             if(decodedBytes.length % 8 != 0){ //not a multiple of 8
                 if (toDebugar) System.out.println("decodedBytes is not padded properly in 8 bits");
                 //create a new array with a size which is a multiple of 8
                 byte[] padded = new byte[decodedBytes.length + 8 - (decodedBytes.length % 8)];
                 //copy the old array into it
                 System.arraycopy(decodedBytes, 0, padded, 0, decodedBytes.length);
                 decodedBytes = padded;
             }else{
            	 if (toDebugar) System.out.println("decodedBytes is padded properly in 8 bits");
             }
            
             byte[] keyBytes=key.getBytes(charSet);
             SecretKeySpec secretkey = new SecretKeySpec(keyBytes, "Blowfish");
             cipher = Cipher.getInstance("Blowfish/ECB/NoPadding");
             cipher.init(Cipher.DECRYPT_MODE, secretkey);      
             byte[] decryptedFinal = cipher.doFinal(decodedBytes);
             return (new String(decryptedFinal)).replaceAll("[^a-zA-Z0-9]*", ""); 
          } catch (Exception e) {
              e.printStackTrace();
    			throw new Exception(e);
    	           // return null;
          }
     }       
}