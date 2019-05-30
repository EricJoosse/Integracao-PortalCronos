package pcronos.integracao.fornecedor;

import mslinks.ShellLink;

public class Instalador {

	public static void main(String[] args) {
		try {
			int idFornecedor = Integer.parseInt(args[0]);		 
			
			FornecedorRepositorio fRep = new FornecedorRepositorio();
			Fornecedor f = fRep.getFornecedor(idFornecedor);

			// idFornecedor == -1 no caso de instalação do serviço de monitoramento automático no servidor de aplicação do Portal Cronos:
			if (idFornecedor != -1) 
			{
		        ManualManutencao m = new ManualManutencao(f);
		        m.gravarEmArquivoNoMenuWindows();

				if (f.IsServicoNuvem) 
				{
					String caminhoMaisNomeArquivo = "C:/Arquivos de Programas PC/AdicionarFornecedorNuvem.bat";
					String nomeAtalho = "C:/ProgramData/Microsoft/Windows/Start Menu/Programs/Portal Cronos/Integração " + f.SiglaSistemaFornecedor + "/Adicionar Cliente novo.lnk";
			       	ShellLink.createLink(caminhoMaisNomeArquivo, nomeAtalho);
				}
			}

			
			  
		}
		catch (java.lang.ArrayIndexOutOfBoundsException aioex) {
			System.out.println("Parâmetro \"idFornecedor\" não informado na chamada de pcronos.integracao.fornecedor.Instalador");
		}
		catch (NumberFormatException nfex) {
			System.out.println("Parâmetro \"idFornecedor\" inválido na chamada de pcronos.integracao.fornecedor.Instalador");
		}
		catch (Exception ex) {
			System.out.println("Erro: " + ex.getMessage() + "\r\n" + "Não foi possível instalar o serviço 100 % !");
		}
    }

}
