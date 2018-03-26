package pcronos.integracao.fornecedor;

public class FornecedorRepositorio {

	public FornecedorRepositorio() {
	}

	Fornecedor getFornecedor(int idFornecedor) {
		Fornecedor f = new Fornecedor();
		f.IdFornecedor = idFornecedor;

		if (idFornecedor == 13) {
			f.EmailResponsavelTI = "projetosti@formaggio.com.br";
			f.EmailResponsavelAlternativoTI = "ti@formaggio.com.br";
			f.ApelidoResponsavelTI = "Geymison";
			f.ApelidoResponsavelAlternativoTI = "Braytner";
			f.SiglaSistemaFornecedor = "APS";
			f.AplicativoDesktopRemoto = "Team Viewer";
			f.NomeFantasiaEmpresa = "Formaggio";
		}			   
		else if (idFornecedor == 947) {
			f.EmailResponsavelTI = "jrembalagem.ti@gmail.com";
			f.EmailResponsavelAlternativoTI = "";
			f.ApelidoResponsavelTI = "Ivan";
			f.ApelidoResponsavelAlternativoTI = "";
			f.SiglaSistemaFornecedor = "WinThor";
			f.AplicativoDesktopRemoto = "AnyDesk";
			f.NomeFantasiaEmpresa = "JR Embalagem";
		}			   
		else if (idFornecedor == 30) {
			f.EmailResponsavelTI = "marcelo@casadoqueijo.net.br";
			f.EmailResponsavelAlternativoTI = "";
			f.ApelidoResponsavelTI = "Marcelo";
			f.ApelidoResponsavelAlternativoTI = "";
			f.SiglaSistemaFornecedor = "WinThor";
			f.AplicativoDesktopRemoto = "AnyDesk";
			f.NomeFantasiaEmpresa = "Prolac";
		}			   
		else if (idFornecedor == 21) {
			f.EmailResponsavelTI = "felipe.lolaia@maritimospescados.com.br";
			f.EmailResponsavelAlternativoTI = "";
			f.ApelidoResponsavelTI = "Felipe";
			f.ApelidoResponsavelAlternativoTI = "";
			f.SiglaSistemaFornecedor = "WinThor";
			f.AplicativoDesktopRemoto = "Team Viewer";
			f.NomeFantasiaEmpresa = "Marítimos";
		}			   
		else if (idFornecedor == 170) {
			f.EmailResponsavelTI = "informatica@sost.com.br";
			f.EmailResponsavelAlternativoTI = "informatica@sost.com.br";
			f.ApelidoResponsavelTI = "Cleijonatas";
			f.ApelidoResponsavelAlternativoTI = "Sena";
			f.SiglaSistemaFornecedor = "WinThor";
			f.AplicativoDesktopRemoto = "AnyDesk";
			f.NomeFantasiaEmpresa = "SOST";
		}			   
		return f;
	}
}
