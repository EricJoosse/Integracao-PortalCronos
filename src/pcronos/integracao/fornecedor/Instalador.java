package pcronos.integracao.fornecedor;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import mslinks.ShellLink;
import pcronos.integracao.DefaultCharsetException;

public class Instalador {

	private static void gravarIsAmbienteNuvem(int isAmbienteNuvem) {
      try
   	  {
	        BufferedWriter bWriter = new BufferedWriter(new FileWriter("bin/IsAmbienteNuvem.bat", true));
	        bWriter.append(Integer.toString(isAmbienteNuvem));
	        bWriter.newLine();
	     // bWriter.newLine();
	        bWriter.flush();
	        bWriter.close();
   	  }
   	  catch (IOException ioe)
   	  {
   	    System.out.println(Integer.toString(isAmbienteNuvem));
   	  }
	}
	
	
	private static void editarArquivoConfig(String caminhoMaisNomeArquivo, String siglaSistema, String cnpjFornecedor) throws IOException, DefaultCharsetException
	{
		if (!Utils.getDefaultCharsetJVM().equals("windows-1252"))
		{
			throw new DefaultCharsetException(Utils.getDefaultCharsetJVM());
		}
		
     	String conteudoArquivo = new String(Files.readAllBytes(Paths.get(caminhoMaisNomeArquivo)), Charset.forName("windows-1252"));
     	
     	conteudoArquivo = conteudoArquivo.replace("SiglaSistema                      = XXX", "SiglaSistema                      = " + siglaSistema);
     	
     	if (cnpjFornecedor != null)
     		conteudoArquivo = conteudoArquivo.replace("CnpjFornecedor                    = 11222333444455", "CnpjFornecedor                    = " + cnpjFornecedor);
     	
     	BufferedWriter bWriter = new BufferedWriter(new FileWriter(caminhoMaisNomeArquivo, false));
        bWriter.write(conteudoArquivo);
        bWriter.flush();
        bWriter.close();
	}

	
	public static void main(String[] args) throws Exception 
	{
		try {
			int idFornecedor = Integer.parseInt(args[0]);		 
			
			FornecedorRepositorio fRep = new FornecedorRepositorio();
			
			Fornecedor f = null;
			
			if (idFornecedor == -1)
   			    f = fRep.getFornecedor(null);
			else
				f = fRep.getFornecedor(idFornecedor);

			// Observações: 
			// 1. idFornecedor == -1 no caso de instalação do serviço de monitoramento automático no servidor de aplicação do Portal Cronos:
			// 2. if (idFornecedor == null) não tratar aqui, pois neste caso entra no ArrayIndexOutOfBoundsException 
			if (idFornecedor != -1) 
			{
				if (f.IsServicoNuvem) 
				{
					gravarIsAmbienteNuvem(1); // Primeiro para evitar instalação amíguo de tipo nuvem ou não-nuvem no caso de instalação abortada por causa de qq erro no instalador

			        ManualManutencao m = new ManualManutencao(f, null);
			        m.gravarEmArquivoNoMenuWindows();
					
					String caminhoMaisNomeArquivo = "C:/Arquivos de Programas PC/AdicionarFornecedorNuvem.bat";
					String nomeAtalho = "C:/ProgramData/Microsoft/Windows/Start Menu/Programs/Portal Cronos/Integrador " + f.SiglaSistemaFornecedor + " Cloud/Adicionar Instância.lnk";
					ShellLink slAdicionar = ShellLink.createLink(caminhoMaisNomeArquivo)
												  // .setWorkingDir("..")
													 .setIconLocation("C:/Arquivos de Programas PC/Integração Fornecedor - Portal Cronos/res/AdicionarInstancia.ico");
					slAdicionar.saveTo(nomeAtalho);

					caminhoMaisNomeArquivo = "C:/Arquivos de Programas PC/RemoverFornecedorNuvem.bat";
					nomeAtalho = "C:/ProgramData/Microsoft/Windows/Start Menu/Programs/Portal Cronos/Integrador " + f.SiglaSistemaFornecedor + " Cloud/Remover Instância.lnk";
					ShellLink slRemover = ShellLink.createLink(caminhoMaisNomeArquivo)
												// .setWorkingDir("..")
												   .setIconLocation("C:/Arquivos de Programas PC/Integração Fornecedor - Portal Cronos/res/RemoverInstancia.ico");
					slRemover.saveTo(nomeAtalho);
				}
				else if (!f.IsServicoNuvem)
				{
					gravarIsAmbienteNuvem(0); // Primeiro para evitar instalação amíguo de tipo nuvem ou não-nuvem no caso de instalação abortada por causa de qq erro no instalador

					String siglaSistema = args[1];

					// Primeiro para evitar instalação amíguo de tipo nuvem ou não-nuvem no caso de instalação abortada por causa de qq erro no instalador:
			       	editarArquivoConfig("C:/Arquivos de Programas PC/Integração Fornecedor - Portal Cronos/" + Constants.DIR_ARQUIVOS_PROPERTIES + Constants.NOME_ARQUIVO_PROPERTIES
	       			           , siglaSistema
	       			           , f.cnpjFornecedor);

			       	
					if (!f.SiglaSistemaFornecedor.equals(siglaSistema))
						throw new Exception("A sigla digitada do sistema não bate com a sigla cadastrada no site do Portal Cronos!");
					
					ManualManutencao m = new ManualManutencao(f, siglaSistema);
			        m.gravarEmArquivoNoMenuWindows();

			       	TarefaWindows tarefaWindows = new TarefaWindows(false, null, idFornecedor);
					tarefaWindows.gravarEmArquivoXML();
				}
			}
			else if (idFornecedor == -1)
			{
				// idFornecedor == -1 no caso de instalação do serviço de monitoramento automático no servidor de aplicação do Portal Cronos
				gravarIsAmbienteNuvem(0);

				String siglaSistema = args[1];
		       	editarArquivoConfig("C:/Arquivos de Programas PC/Integração Fornecedor - Portal Cronos/" + Constants.DIR_ARQUIVOS_PROPERTIES + Constants.NOME_ARQUIVO_PROPERTIES
		       			           , siglaSistema
		       			           , null);

		        TarefaWindows tarefaWindows = new TarefaWindows(false, null, idFornecedor);
				tarefaWindows.gravarEmArquivoXML();
			}
			  
		}
		catch (java.lang.ArrayIndexOutOfBoundsException aioex) 
		{
			String msg = "Parâmetro \"idFornecedor\" não informado na chamada de pcronos.integracao.fornecedor.Instalador";
			System.out.println(msg);
			throw new Exception(msg);
		}
		catch (NumberFormatException nfex) 
		{
			String msg = "Parâmetro \"idFornecedor\" inválido na chamada de pcronos.integracao.fornecedor.Instalador";
			System.out.println(msg);
			throw new Exception(msg);
		}
		catch (Exception ex) 
		{
			String msg = "Erro: " + ex.getMessage() + "\r\n" + "Não foi possível instalar o Integrador 100 % !";
			System.out.println(msg);
			throw new Exception(msg);
		}
    }

}
