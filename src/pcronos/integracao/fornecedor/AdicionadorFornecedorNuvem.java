package pcronos.integracao.fornecedor;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;

import mslinks.ShellLink;
import pcronos.integracao.DefaultCharsetException;
import pcronos.integracao.EmailAutomatico;


public class AdicionadorFornecedorNuvem 
{
	private static boolean toDebugar = false;

	private static void editarArquivoConfig(String caminhoMaisNomeArquivo, String nmFornecedor) throws IOException, DefaultCharsetException
	{
		if (!Utils.getDefaultCharsetJVM().equals("windows-1252"))
		{
			throw new DefaultCharsetException(Utils.getDefaultCharsetJVM());
		}
		
     	String conteudoArquivo = new String(Files.readAllBytes(Paths.get(caminhoMaisNomeArquivo)), Charset.forName("windows-1252"));
     	
     	if (toDebugar) System.out.println("nmFornecedor = " + nmFornecedor);
     	if (toDebugar) System.out.println("conteudoArquivo.length() = " + conteudoArquivo.length());

     	conteudoArquivo = conteudoArquivo.replace("NomeEmpresa", nmFornecedor).replace("ws-empresa", "ws-" + nmFornecedor.toLowerCase());
     	
     	if (toDebugar) System.out.println("conteudoArquivo.length() = " + conteudoArquivo.length());
     	
     	int tamanhoArqCortado = conteudoArquivo.indexOf("######### Configurações E-mail automático");
     	
     	if (toDebugar) System.out.println("tamanhoArqCortado = " + tamanhoArqCortado);
     	
     	conteudoArquivo = conteudoArquivo.substring(0, tamanhoArqCortado);

     	if (toDebugar) System.out.println("conteudoArquivo = " + conteudoArquivo);

     	BufferedWriter bWriter = new BufferedWriter(new FileWriter(caminhoMaisNomeArquivo, false));
        bWriter.write(conteudoArquivo);
        bWriter.flush();
        bWriter.close();
	}

	
	public static void main(String[] args) throws IOException, Exception 
	{
		String nmFornecedor = args[0];

	    LocalDateTime horaInicio = LocalDateTime.now();
   	    IntegracaoFornecedorCompleta.Inicializar(Constants.DIR_ARQUIVOS_PROPERTIES + Constants.NOME_TEMPLATE_CLOUD_PROPERTIES);
		String assunto = "Fornecedor novo " + nmFornecedor + " adicionado na integração APS Cloud / PCronos!";
		String body = "Favor adicionar este fornecedor (por enquanto manualmente) no Monitorador.";
        EmailAutomatico.enviar(IntegracaoFornecedorCompleta.remetenteEmailAutomatico, IntegracaoFornecedorCompleta.destinoEmailAutomatico, IntegracaoFornecedorCompleta.ccEmailAutomatico, assunto, null, body, IntegracaoFornecedorCompleta.provedorEmailAutomatico, IntegracaoFornecedorCompleta.portaEmailAutomatico, IntegracaoFornecedorCompleta.usuarioEmailAutomatico, IntegracaoFornecedorCompleta.senhaCriptografadaEmailAutomatico, IntegracaoFornecedorCompleta.diretorioArquivosXmlSemBarraNoFinal, horaInicio, IntegracaoFornecedorCompleta.diretorioArquivosXml, "INI", null);

        TarefaWindows tarefaWindows = new TarefaWindows(nmFornecedor);
		tarefaWindows.gravarEmArquivoXML();
		  
		String caminhoMaisNomeArquivo = "C:/Arquivos de Programas PC/Integração Fornecedor - Portal Cronos/conf/Integração APS - Portal Cronos." + nmFornecedor + ".properties";
       	editarArquivoConfig(caminhoMaisNomeArquivo, nmFornecedor);

	    // Criar atalho no menu de Windows:
		String nomeAtalho = "C:/ProgramData/Microsoft/Windows/Start Menu/Programs/Portal Cronos/Integrador APS Cloud/Configurações " + nmFornecedor + ".lnk";		  
       	ShellLink.createLink(caminhoMaisNomeArquivo, nomeAtalho);
       	  
		  
	}

}
