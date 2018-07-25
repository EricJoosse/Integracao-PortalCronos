package pcronos.integracao.fornecedor;


public class Desinstalador {

	public static void main(String[] args) {
		try {	
			
			// siglaSistema == "PCronos" no caso de instalação do serviço de monitoramento automático no servidor de aplicação do Portal Cronos:
    		if (!IntegracaoFornecedorCompleta.siglaSistema.equals("PCronos")) {
			    FornecedorRepositorio fRep = new FornecedorRepositorio();
             // int idFornecedor = Integer.parseInt(args[0]);
		   	    int idFornecedor = fRep.getIdFornecedorByCnpj(IntegracaoFornecedorCompleta.cnpjFornecedor);
			
	            ManualManutencao m = new ManualManutencao(fRep.getFornecedor(idFornecedor));
	            m.removerPCronosDoMenuWindows();
			}
    		
		}
//		catch (java.lang.ArrayIndexOutOfBoundsException aioex) {
//			System.out.println("Parâmetro \"idFornecedor\" não informado na chamada de pcronos.integracao.fornecedor.Desinstalador");
//		}
//		catch (NumberFormatException nfex) {
//			System.out.println("Parâmetro \"idFornecedor\" inválido na chamada de pcronos.integracao.fornecedor.Desinstalador");
//		}
		catch (Exception ex) {
			System.out.println("Erro: " + ex.getMessage() + "\r\n" + "Não foi possível desinstalar o serviço!");
		}
    }

}
