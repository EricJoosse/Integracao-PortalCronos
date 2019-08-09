package pcronos.integracao.fornecedor.relatorios;

import java.util.Map.Entry;
import pcronos.integracao.fornecedor.Fornecedor;
import pcronos.integracao.fornecedor.FornecedorRepositorio;
import pcronos.integracao.fornecedor.Utils;

public class HorasIniJobsFornecedores {

	public static void main(String[] args) throws Exception {
		System.out.println(Utils.rpad("ID", 4) + " " + Utils.rpad("Fornecedor", 26) + " "
				+ Utils.rpad("Minuto Atual", 13) + " " + Utils.rpad("Minuto Futuro", 6));
		System.out.println(Utils.rpad("==", 4) + " " + Utils.rpad("==========", 26) + " "
				+ Utils.rpad("=============", 13) + " " + Utils.rpad("=============", 6));
		System.out.println();

		for (Entry<Integer, Fornecedor> entry : FornecedorRepositorio.hashMap.entrySet()) {
			// String key = entry.getKey().toString();
			Object value = entry.getValue();
			// System.out.println("key = " + key);
			// System.out.println("NomeFantasiaEmpresa = " +
			// ((Fornecedor)value).NomeFantasiaEmpresa);
			// System.out.println("versaoIntegrador = " +
			// ((Fornecedor)value).versaoIntegrador);

			Integer idFornecedor = ((Fornecedor) value).IdFornecedor;

			
			
			String minutoAgendamentoFuturo = "";
			try {
				// No caso do servidor de monitoramento:
				if (idFornecedor == null)
					minutoAgendamentoFuturo = Byte.toString(Utils.calcularMinutoAgendamento(-1));
				else
					minutoAgendamentoFuturo = Byte.toString(Utils.calcularMinutoAgendamento(idFornecedor));
			} catch (Exception ex) {
				minutoAgendamentoFuturo = ex.getMessage();
			}

			try
			{
				if (Integer.parseInt(minutoAgendamentoFuturo) < 10)
					minutoAgendamentoFuturo = "0" + minutoAgendamentoFuturo;
			}
			catch (Exception ex) { }

			
			
			FornecedorRepositorio fRep = new FornecedorRepositorio();
			Fornecedor f = fRep.getFornecedor(idFornecedor);
			
			String minutoAgendamentoAtual = "";
            if (f.versaoIntegrador.compareTo("3.1.0") >= 0 || f.versaoIntegrador.compareTo("3.1") >= 0)
				minutoAgendamentoAtual = minutoAgendamentoFuturo;
            else if (f.IsServicoNuvem)
            {
        		if (f.NomeFantasiaEmpresa.equals("Marizpan"))
        			minutoAgendamentoAtual = "04";
        		else if (f.NomeFantasiaEmpresa.equals("Atacamax"))
        			minutoAgendamentoAtual = "11";
        		else 
        			minutoAgendamentoAtual = "01";
            }
            else
				minutoAgendamentoAtual = "04";

            
			System.out.println(Utils.rpad(Utils.replaceNull(idFornecedor).toString(), 4) + " "
						+ Utils.rpad(Utils.replaceNull(((Fornecedor) value).NomeFantasiaEmpresa), 26) + " "
						+ Utils.rpad(Utils.replaceNull(minutoAgendamentoAtual), 13) + " "
						+ Utils.rpad(Utils.replaceNull(minutoAgendamentoFuturo), 6));
		}
	}

}
