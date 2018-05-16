package pcronos.integracao.fornecedor;

import java.util.jar.JarException;

import javax.lang.model.type.IntersectionType;

public class Instalador {

	public static void main(String[] args) {
		try {
			int idFornecedor = Integer.parseInt(args[0]);		 
			FornecedorRepositorio fRep = new FornecedorRepositorio();
	        ManualManutencao m = new ManualManutencao(fRep.getFornecedor(idFornecedor));
	        m.gravarEmArquivo();
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
