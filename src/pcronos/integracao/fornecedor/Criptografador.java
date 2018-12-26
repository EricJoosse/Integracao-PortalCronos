package pcronos.integracao.fornecedor;

import pcronos.integracao.Criptografia;

public class Criptografador {

	public static void main(String[] args) throws Exception {
		String strToCript = "aaa"; 
		String strCriptografado = Criptografia.encrypt(strToCript);
		
		System.out.println("O criptografado de \"" + strToCript + "\" = \"" + strCriptografado + "\"");
		System.out.println();
		System.out.println("O decriptografado de \"" + strCriptografado + "\" = \"" + Criptografia.decrypt(strCriptografado, true) + "\"");

	}

}
