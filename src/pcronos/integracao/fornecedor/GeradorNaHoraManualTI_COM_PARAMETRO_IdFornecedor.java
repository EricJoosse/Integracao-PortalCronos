package pcronos.integracao.fornecedor;



public class GeradorNaHoraManualTI_COM_PARAMETRO_IdFornecedor {

	public static void main(String[] args) {
		try {
			int idFornecedor = Integer.parseInt(args[0]);		 
			FornecedorRepositorio fRep = new FornecedorRepositorio();
	        ManualManutencao m = new ManualManutencao(fRep.getFornecedor(idFornecedor));
	        m.gravarEmArquivoSoltoNoRaizDoProjeto();
	        System.out.println("Manual gerado com sucesso no raiz do projeto Eclipse: " + m.nomeArquivo);
		}
		catch (java.lang.ArrayIndexOutOfBoundsException aioex) {
			System.out.println("Parâmetro \"idFornecedor\" não informado na chamada de pcronos.integracao.fornecedor.Instalador");
		}
		catch (NumberFormatException nfex) {
			System.out.println("Parâmetro \"idFornecedor\" inválido na chamada de pcronos.integracao.fornecedor.Instalador");
		}
		catch (Exception ex) {
			System.out.println("Erro: " + ex.getMessage());
		}
    }

}
