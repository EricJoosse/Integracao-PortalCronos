package pcronos.integracao.fornecedor;

public class FornecedorRepositorio {

	public FornecedorRepositorio() {
	}

	Fornecedor getFornecedor(Integer idFornecedor) {
		Fornecedor f = new Fornecedor();
		f.IdFornecedor = idFornecedor;

		if (idFornecedor == 13) {
			f.EmailResponsavelTI = "projetosti@formaggio.com.br";  
			f.EmailResponsavelTIAlternativo = "ti@formaggio.com.br"; 
			f.ApelidoResponsavelTI = "Geymison";
			f.ApelidoResponsavelTIAlternativo = "Braytner";
			f.FuncaoResponsavelTI = "Coordenador de Projetos de TI";
			f.FuncaoResponsavelTIAlternativo = "Coordenador Geral de TI";
			f.SiglaSistemaFornecedor = "APS";
			f.AplicativoDesktopRemoto = "Team Viewer";
			f.NomeFantasiaEmpresa = "Formaggio";
		}			   
		else if (idFornecedor == 947) {
			f.EmailResponsavelTI = "jrembalagem.ti@gmail.com"; 
			f.EmailResponsavelTIAlternativo = "";
			f.ApelidoResponsavelTI = "Ivan";
			f.ApelidoResponsavelTIAlternativo = "";
			f.SiglaSistemaFornecedor = "WinThor";
			f.AplicativoDesktopRemoto = "AnyDesk";
			f.NomeFantasiaEmpresa = "JR Embalagem";
		}			   
		else if (idFornecedor == 30) {
			f.EmailResponsavelTI = "marcelo@casadoqueijo.net.br"; 
			f.EmailResponsavelTIAlternativo = "";
			f.ApelidoResponsavelTI = "Marcelo";
			f.ApelidoResponsavelTIAlternativo = "";
			f.SiglaSistemaFornecedor = "WinThor";
			f.AplicativoDesktopRemoto = "AnyDesk";
			f.NomeFantasiaEmpresa = "Prolac";
		}			   
		else if (idFornecedor == 21) {
			f.EmailResponsavelTI = "felipe.lolaia@maritimospescados.com.br"; 
			f.EmailResponsavelTIAlternativo = "";
			f.ApelidoResponsavelTI = "Felipe";
			f.ApelidoResponsavelTIAlternativo = "";
			f.SiglaSistemaFornecedor = "WinThor";
			f.AplicativoDesktopRemoto = "Team Viewer";
			f.NomeFantasiaEmpresa = "Marítimos";
		}			   
		else if (idFornecedor == 170) {
			f.EmailResponsavelTI = "informatica@sost.com.br OU cleijonatassilva@sost.com.br"; 
			f.EmailResponsavelTIAlternativo = "informatica@sost.com.br OU carlossena@sost.com.br";
			f.ApelidoResponsavelTI = "Cleijonatas";
			f.ApelidoResponsavelTIAlternativo = "Sena";
			f.SiglaSistemaFornecedor = "WinThor";
			f.AplicativoDesktopRemoto = "AnyDesk";
			f.NomeFantasiaEmpresa = "SOST";
		}			   
		else if (idFornecedor == 60) {
			f.EmailResponsavelTI = "timons@kk.com.br";
			f.EmailResponsavelTIAlternativo = "";
			f.ApelidoResponsavelTI = "Timon";
			f.ApelidoResponsavelTIAlternativo = "";
			f.SiglaSistemaFornecedor = "SAP";
			f.AplicativoDesktopRemoto = "Team Viewer";
			f.NomeFantasiaEmpresa = "Karne Keijo";
		}			   
		else if (idFornecedor == 33) {
			f.EmailResponsavelTI = "";
			f.EmailResponsavelTIAlternativo = "";
			f.ApelidoResponsavelTI = "";
			f.ApelidoResponsavelTIAlternativo = "";
			f.SiglaSistemaFornecedor = "WinThor";
			f.AplicativoDesktopRemoto = "Team Viewer";
			f.NomeFantasiaEmpresa = "Comal";
		}			   
		else if (idFornecedor == 51) {
			f.EmailResponsavelTI = "";
			f.EmailResponsavelTIAlternativo = "";
			f.ApelidoResponsavelTI = "";
			f.ApelidoResponsavelTIAlternativo = "";
			f.SiglaSistemaFornecedor = "WinThor";
			f.AplicativoDesktopRemoto = "Team Viewer";
			f.NomeFantasiaEmpresa = "Master Commerce";
		}			   
		else if (idFornecedor == null) {
			f.EmailResponsavelTI = "";
			f.EmailResponsavelTIAlternativo = "";
			f.ApelidoResponsavelTI = "";
			f.ApelidoResponsavelTIAlternativo = "";
			f.SiglaSistemaFornecedor = "PCronos";
			f.AplicativoDesktopRemoto = "mstsc";
			f.NomeFantasiaEmpresa = "Monitoramento";
		}			   
		return f;
	}
}
