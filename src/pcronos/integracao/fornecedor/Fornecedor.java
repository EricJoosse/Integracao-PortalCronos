package pcronos.integracao.fornecedor;

public class Fornecedor {

	public Fornecedor() {		
	}
	
	public Integer IdFornecedor;
	public String NomeFantasiaEmpresa;
	String SiglaSistemaFornecedor;
	public String EmailResponsavelTI;
	String EmailResponsavelTIAlternativo;
	public String SkypeResponsavelTI;
	String SkypeResponsavelTIAlternativo;
	public String ApelidoResponsavelTI;
	String ApelidoResponsavelTIAlternativo;
	String FuncaoResponsavelTI;
	String FuncaoResponsavelTIAlternativo;
	String TelefoneResponsavelTI;
	String AplicativoDesktopRemoto;
	String IdAplicativoDesktopRemoto; // !!!!!!!!!!!! ID do AnyDesk depende do usuário logado!!!!!!!!!!!!!!!!!!
	String usuarioWebservice;
	String versaoJRE;
	String tipoJRE;
	public String versaoIntegrador;
	String IsEmProducao;
	boolean IsDebugAtivado;
	String tipoSO;
	String SO32ou64bit;
	String dirProgramFiles;
	String DiscoIntegrador;
	String EspacoLivreDisco;
	String EnderecoIpPublicoServidor;
	String PortaIpAberta;
	String cnpjFornecedor;
	public String EmailResponsavelTI_Nuvem;
	public String SkypeResponsavelTI_Nuvem;
	public String ApelidoResponsavelTI_Nuvem;
	public String TelefoneResponsavelTI_Nuvem;
	String ResponsavelDeParasProdutos;
	String MemoriaRamLivre;
	public boolean IsServicoNuvem;
	public int SequenciaInstanciaNuvem;
	public String FrequenciaProcessamento;
	
	
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
