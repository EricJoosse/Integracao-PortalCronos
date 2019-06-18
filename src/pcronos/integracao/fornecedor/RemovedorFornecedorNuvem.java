package pcronos.integracao.fornecedor;

import java.io.File;
import java.io.IOException;

import mslinks.ShellLink;

public class RemovedorFornecedorNuvem 
{

	public static void main(String[] args) throws IOException, Exception 
	{
		  String nmFornecedor = args[0];
		  
		  // Remover atalho no menu de Windows:
		  String nomeAtalho = "C:/ProgramData/Microsoft/Windows/Start Menu/Programs/Portal Cronos/Integrador APS Cloud/Configurações " + nmFornecedor + ".lnk";		  
     	  File fileAtalho = new File(nomeAtalho);
     	  fileAtalho.delete();		  
	}

}
