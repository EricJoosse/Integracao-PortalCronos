package pcronos.integracao.fornecedor;

import pcronos.integracao.Criptografia;

public class Criptografador {

	public static void main(String[] args) throws Exception {
		String strToCrypt = "aaa"; 
		String strCriptografado = Criptografia.encrypt(strToCrypt);
		
		System.out.println("A senha criptografada de \"" + strToCrypt + "\" = \"" + strCriptografado + "\"");
		System.out.println();
		System.out.println("A senha descriptografada de \"" + strCriptografado + "\" = \"" + Criptografia.decrypt(strCriptografado, true) + "\"");

	}

}
