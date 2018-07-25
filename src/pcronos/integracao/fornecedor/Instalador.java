package pcronos.integracao.fornecedor;



public class Instalador {

	public static void main(String[] args) {
		try {
			int idFornecedor = Integer.parseInt(args[0]);		 
			
			// idFornecedor == -1 no caso de instalação do serviço de monitoramento automático no servidor de aplicação do Portal Cronos:
			if (idFornecedor != -1) {
				FornecedorRepositorio fRep = new FornecedorRepositorio();
		        ManualManutencao m = new ManualManutencao(fRep.getFornecedor(idFornecedor));
		        m.gravarEmArquivoNoMenuWindows();
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
