package pcronos.integracao;

public class DefaultCharsetException extends Exception {
	public DefaultCharsetException(String charset) {
        super("Erro interno! O Monitorador ainda não suporta o charset " + charset + ".");
	}

}
