package pcronos.integracao;

//import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Criptografia {

	public static String encrypt(String strClearText) throws Exception
	{
		String key = "lKj@#%$&BDg84952"; // 128 bit key
	//	String key = "Bar12345Bar12345"; // 128 bit key
		return encrypt(strClearText, key);
	}

	
    public static String encrypt(String strClearText, String strKey) throws Exception
	{
		String strData="";
		
		try {
			SecretKeySpec skeyspec=new SecretKeySpec(strKey.getBytes(),"Blowfish");
			Cipher cipher=Cipher.getInstance("Blowfish");
			cipher.init(Cipher.ENCRYPT_MODE, skeyspec);
			byte[] encrypted=cipher.doFinal(strClearText.getBytes());
			strData=new String(encrypted);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
		return strData;
	}
	

	public static String decrypt(String strEncrypted) throws Exception
	{
		String key = "lKj@#%$&BDg84952"; // Mesmo 128 bit key
		return decrypt(strEncrypted, key);
	}
    
    public static String decrypt(String strEncrypted, String strKey) throws Exception
	{
		String strData="";
		
		try {
			SecretKeySpec skeyspec=new SecretKeySpec(strKey.getBytes(),"Blowfish");
			Cipher cipher=Cipher.getInstance("Blowfish");
			cipher.init(Cipher.DECRYPT_MODE, skeyspec);
			byte[] decrypted=cipher.doFinal(strEncrypted.getBytes());
			strData=new String(decrypted);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
		return strData;
	}
	
}