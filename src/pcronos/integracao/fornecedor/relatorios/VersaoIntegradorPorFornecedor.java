package pcronos.integracao.fornecedor.relatorios;

import java.util.Map.Entry;

import pcronos.integracao.fornecedor.Fornecedor;
import pcronos.integracao.fornecedor.FornecedorRepositorio;

public class VersaoIntegradorPorFornecedor {

	public static void main(String[] args)	{
        for (Entry<Integer, Fornecedor> entry : FornecedorRepositorio.hashMap.entrySet()) {
		     // String key = entry.getKey().toString();
		        Object value = entry.getValue();
		     // System.out.println("key = " + key);
		     // System.out.println("NomeFantasiaEmpresa = " + ((Fornecedor)value).NomeFantasiaEmpresa);
		     // System.out.println("versaoIntegrador = " + ((Fornecedor)value).versaoIntegrador);
		        System.out.println(String.format("%-16s", ((Fornecedor)value).NomeFantasiaEmpresa) + " " + String.format("%-10s", ((Fornecedor)value).versaoIntegrador));
	    }
	}
	    


}
