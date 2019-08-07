package pcronos.integracao.fornecedor.relatorios;

import java.util.Map.Entry;
import pcronos.integracao.fornecedor.Fornecedor;
import pcronos.integracao.fornecedor.FornecedorRepositorio;
import pcronos.integracao.fornecedor.Utils;

public class HorasIniJobsFornecedores {

	public static void main(String[] args)	{
        for (Entry<Integer, Fornecedor> entry : FornecedorRepositorio.hashMap.entrySet()) {
		     // String key = entry.getKey().toString();
		        Object value = entry.getValue();
		     // System.out.println("key = " + key);
		     // System.out.println("NomeFantasiaEmpresa = " + ((Fornecedor)value).NomeFantasiaEmpresa);
		     // System.out.println("versaoIntegrador = " + ((Fornecedor)value).versaoIntegrador);
		        
		        Integer idFornecedor = ((Fornecedor)value).IdFornecedor;
		        
		        String minAgendamento = "";
		        try 
		        {
		        	if (idFornecedor == null)
   		               minAgendamento = "???";
		        	else
			           minAgendamento = Byte.toString(Utils.calcularMinutoAgendamento(idFornecedor));
		        }
		        catch (Exception ex)
		        {
		        	minAgendamento = ex.getMessage();
		        }
		        
		        System.out.println(  		Utils.rpad(Utils.replaceNull(idFornecedor).toString(), 4) 
    								+ " " + Utils.rpad(Utils.replaceNull(((Fornecedor)value).NomeFantasiaEmpresa), 26)
    								+ " " + Utils.rpad(Utils.replaceNull(minAgendamento), 6)
		        		          );
	    }
	}
	    


}
