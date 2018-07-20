package pcronos.integracao.fornecedor;

public class Fornecedor {

	public Fornecedor() {		
	}
	
	int IdFornecedor;
	String NomeFantasiaEmpresa;
	String SiglaSistemaFornecedor;
	String EmailResponsavelTI;
	String EmailResponsavelTIAlternativo;
	String ApelidoResponsavelTI;
	String ApelidoResponsavelTIAlternativo;
	String FuncaoResponsavelTI;
	String FuncaoResponsavelTIAlternativo;
	String AplicativoDesktopRemoto;
	String IdAplicativoDesktopRemoto;
	String usuarioWebservice;
	String versaoJRE;
	String tipoSO;
	String SO32ou64bit;
	String dirProgramFiles;
	
	
	public String getTipoBaseDeDados() throws Exception {
		if (this.SiglaSistemaFornecedor.equals("APS"))
			return "Firebird";
		else if (this.SiglaSistemaFornecedor.equals("WinThor"))
			return "Oracle";
		else if (this.SiglaSistemaFornecedor.equals("PCronos"))
			return "SQL Server";
		else if (this.SiglaSistemaFornecedor.equals("SAP"))
			return "SAP DB/SAP HANA (?)";
		else {
			throw new Exception("O tipo da base de dados não está cadastrado para fornecedor " + this.NomeFantasiaEmpresa);
		}
	}

}
