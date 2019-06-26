package pcronos.integracao.fornecedor;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

import mslinks.ShellLink;
import pcronos.integracao.EmailAutomatico;

public class RemovedorFornecedorNuvem 
{

	public static void main(String[] args) throws IOException, Exception 
	{
		  String nmFornecedor = args[0];
		  
		  LocalDateTime horaInicio = LocalDateTime.now();
	   	  IntegracaoFornecedorCompleta.Inicializar(Constants.DIR_ARQUIVOS_PROPERTIES + Constants.NOME_TEMPLATE_CLOUD_PROPERTIES);
		  String assunto = "Fornecedor " + nmFornecedor + " removido da integração APS Cloud / PCronos!";
		  String body = "Favor remover, ou melhor comentar, este fornecedor (por enquanto manualmente) no Monitorador.";
	      EmailAutomatico.enviar(IntegracaoFornecedorCompleta.remetenteEmailAutomatico, IntegracaoFornecedorCompleta.destinoEmailAutomatico, IntegracaoFornecedorCompleta.ccEmailAutomatico, assunto, null, body, IntegracaoFornecedorCompleta.provedorEmailAutomatico, IntegracaoFornecedorCompleta.portaEmailAutomatico, IntegracaoFornecedorCompleta.usuarioEmailAutomatico, IntegracaoFornecedorCompleta.senhaCriptografadaEmailAutomatico, IntegracaoFornecedorCompleta.diretorioArquivosXmlSemBarraNoFinal, horaInicio, IntegracaoFornecedorCompleta.diretorioArquivosXml, "INI", null);

		  // Remover atalho no menu de Windows:
		  String nomeAtalho = "C:/ProgramData/Microsoft/Windows/Start Menu/Programs/Portal Cronos/Integrador APS Cloud/Configurações " + nmFornecedor + ".lnk";		  
     	  File fileAtalho = new File(nomeAtalho);
     	  fileAtalho.delete();		  
	}

}
