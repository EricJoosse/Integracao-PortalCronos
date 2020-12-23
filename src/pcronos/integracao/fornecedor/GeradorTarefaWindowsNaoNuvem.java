package pcronos.integracao.fornecedor;


// Esta classe está usada em UpgradeNaoNuvem3.1.0.bat

public class GeradorTarefaWindowsNaoNuvem {

	public static void main(String[] args) throws Exception 
	{
		int idFornecedor = Integer.parseInt(args[0]);		 
		
        try 
        {
			// Primeiro testar se o idFornecedor digitado existe:
 		    FornecedorRepositorio fRep = new FornecedorRepositorio();
		    Fornecedor f = fRep.getFornecedor(idFornecedor);
	
	        TarefaWindows tarefaWindows = new TarefaWindows(false, null, idFornecedor);
			tarefaWindows.gravarEmArquivoXML();
		}
		catch (java.lang.ArrayIndexOutOfBoundsException aioex) 
		{
			String msg = "Parâmetro \"idFornecedor\" não informado na chamada de pcronos.integracao.fornecedor.GeradorTarefaWindowsNaoNuvem";
			System.out.println(msg);
			throw new Exception(msg);
		}
		catch (NumberFormatException nfex) 
		{
			String msg = "Parâmetro \"idFornecedor\" inválido na chamada de pcronos.integracao.fornecedor.GeradorTarefaWindowsNaoNuvem";
			System.out.println(msg);
			throw new Exception(msg);
		}
		catch (Exception ex) 
		{
			String msg = "Erro: " + ex.getMessage() + "\r\n" + "Não foi possível fazer o upgrade para 3.1.0!";
			System.out.println(msg);
			throw new Exception(msg);
		}
    }

}
