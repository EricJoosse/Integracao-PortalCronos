package pcronos.integracao.fornecedor;

import java.io.IOException;

import mslinks.ShellLink;

public class AdicionadorFornecedorNuvem 
{

	public static void main(String[] args) throws IOException, Exception 
	{
		  String nmFornecedor = args[0];
		  TarefaWindows tarefaWindows = new TarefaWindows(nmFornecedor);
		  tarefaWindows.gravarEmArquivoXML();
		  
		  // Criar atalho no menu de Windows:
		  String caminhoMaisNomeArquivo = "C:/Arquivos de Programas PC/Integração Fornecedor - Portal Cronos/conf/Integração APS - Portal Cronos." + nmFornecedor + ".properties";
		  String nomeAtalho = "C:/ProgramData/Microsoft/Windows/Start Menu/Programs/Portal Cronos/Integração APS/Configurações " + nmFornecedor + ".lnk";		  
       	  ShellLink.createLink(caminhoMaisNomeArquivo, nomeAtalho);
		  
	}

}
