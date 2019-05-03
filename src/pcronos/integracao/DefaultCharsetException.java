package pcronos.integracao;

public class DefaultCharsetException extends Exception {
	public DefaultCharsetException(String charset) {
        super("Erro interno! O Monitorador já foi adaptado para charsets default de Windows/Linux diferente de Cp1252, e provavelmente já funciona, porém o envio dos emails automáticos ainda não foi testado com o charset " + charset + " como charset default de Windows/Linux.");
	}

}
