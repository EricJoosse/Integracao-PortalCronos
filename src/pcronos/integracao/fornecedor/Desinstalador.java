package pcronos.integracao.fornecedor;

import java.util.Properties;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.io.StringWriter;


public class Desinstalador {

	  static 
	  {
		  Inicializar();
	  }

	  public static        String siglaSistema;
	  public static        String cnpjFornecedor;
	  public static        String erroStaticConstructor;

	  
	  private static void Inicializar()
	  {
		    try {
		        erroStaticConstructor = null;

		        if (!IntegracaoFornecedorCompleta.IsSistemaFornecedorNuvem)
		        {
			        Properties config = new Properties();
			        config.load(new FileInputStream(Constants.DIR_ARQUIVOS_PROPERTIES + Constants.NOME_ARQUIVO_PROPERTIES));
	
			        siglaSistema   = config.getProperty("SiglaSistema");
			        cnpjFornecedor = config.getProperty("CnpjFornecedor");
		        }
		        else
			        siglaSistema   = "APS";

	      } 
	      catch (Exception ex) {
	        try
	        {
	          erroStaticConstructor = "Erro imprevisto! " + Utils.printStackTraceToString(ex);
	        }
	        catch (Exception ex2)
	        {
	          throw new ExceptionInInitializerError(ex2);
	        }
	      }
	  }

	  
	  public static void main(String[] args) {
		try {	
			
			// siglaSistema == "PCronos" no caso de instalação do serviço de monitoramento automático 
			// no servidor de aplicação do Portal Cronos:
    		if (!siglaSistema.equals("PCronos")) {
			    FornecedorRepositorio fRep = new FornecedorRepositorio();

			 // int idFornecedor = Integer.parseInt(args[0]);			    
			    int idFornecedor = 0;
			    if (IntegracaoFornecedorCompleta.IsSistemaFornecedorNuvem)
   		   	       idFornecedor = 1995;
			    else
			   	   idFornecedor = fRep.getIdFornecedorByCnpj(cnpjFornecedor);
			
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
