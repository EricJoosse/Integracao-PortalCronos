package pcronos.integracao.fornecedor;

import pcronos.integracao.Criptografia;

public class Descriptografador {

	public static void main(String[] args) throws Exception {
		String strToDecrypt = "ST+o/6W2VnE="; 
		String strDescriptografado = Criptografia.decrypt(strToDecrypt, true);
		
		System.out.println("A senha descriptografada de \"" + strToDecrypt + "\" = \"" + strDescriptografado + "\"");
		System.out.println();
		System.out.println("A senha re-criptografada de \"" + strDescriptografado + "\" = \"" + Criptografia.encrypt(strDescriptografado) + "\"");

	}

}
