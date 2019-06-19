package pcronos.integracao.fornecedor;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import mslinks.ShellLink;
import pcronos.integracao.DefaultCharsetException;


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
     	if (toDebugar) System.out.println("conteudoArquivo = " + conteudoArquivo);
     	
     	BufferedWriter bWriter = new BufferedWriter(new FileWriter(caminhoMaisNomeArquivo, false));
        bWriter.write(conteudoArquivo);
        bWriter.flush();
        bWriter.close();
	}

	
	public static void main(String[] args) throws IOException, Exception 
	{
		String nmFornecedor = args[0];
		TarefaWindows tarefaWindows = new TarefaWindows(nmFornecedor);
		tarefaWindows.gravarEmArquivoXML();
		  
		String caminhoMaisNomeArquivo = "C:/Arquivos de Programas PC/Integração Fornecedor - Portal Cronos/conf/Integração APS - Portal Cronos." + nmFornecedor + ".properties";
       	editarArquivoConfig(caminhoMaisNomeArquivo, nmFornecedor);

	    // Criar atalho no menu de Windows:
		String nomeAtalho = "C:/ProgramData/Microsoft/Windows/Start Menu/Programs/Portal Cronos/Integrador APS Cloud/Configurações " + nmFornecedor + ".lnk";		  
       	ShellLink.createLink(caminhoMaisNomeArquivo, nomeAtalho);
       	  
		  
	}

}
