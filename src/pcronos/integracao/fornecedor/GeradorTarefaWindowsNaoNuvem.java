package pcronos.integracao.fornecedor;



public class GeradorTarefaWindowsNaoNuvem {

	public static void main(String[] args) {
		int idFornecedor = Integer.parseInt(args[0]);		 
		
        try 
        {
			// Primeiro testar se o idFornecedor digitado existe:
			FornecedorRepositorio fRep = new FornecedorRepositorio();
	        Fornecedor f =  fRep.getFornecedor(idFornecedor);
	
	        TarefaWindows tarefaWindows = new TarefaWindows(false, null, idFornecedor);
			tarefaWindows.gravarEmArquivoXML();
		}
		catch (java.lang.ArrayIndexOutOfBoundsException aioex) {
			System.out.println("Parâmetro \"idFornecedor\" não informado na chamada de pcronos.integracao.fornecedor.GeradorTarefaWindowsNaoNuvem");
		}
		catch (NumberFormatException nfex) {
			System.out.println("Parâmetro \"idFornecedor\" inválido na chamada de pcronos.integracao.fornecedor.GeradorTarefaWindowsNaoNuvem");
		}
		catch (Exception ex) {
			System.out.println("Erro: " + ex.getMessage());
		}
    }

}
