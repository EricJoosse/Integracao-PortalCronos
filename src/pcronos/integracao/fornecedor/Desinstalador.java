package pcronos.integracao.fornecedor;


public class Desinstalador {

	public static void main(String[] args) {
		try {
			int idFornecedor = Integer.parseInt(args[0]);		 
			FornecedorRepositorio fRep = new FornecedorRepositorio();
	        ManualManutencao m = new ManualManutencao(fRep.getFornecedor(idFornecedor));
	        m.removerPCronosDoMenuWindows();
		}
		catch (java.lang.ArrayIndexOutOfBoundsException aioex) {
			System.out.println("Parâmetro \"idFornecedor\" não informado na chamada de pcronos.integracao.fornecedor.Desinstalador");
		}
		catch (NumberFormatException nfex) {
			System.out.println("Parâmetro \"idFornecedor\" inválido na chamada de pcronos.integracao.fornecedor.Desinstalador");
		}
		catch (Exception ex) {
			System.out.println("Erro: " + ex.getMessage());
		}
    }

}
