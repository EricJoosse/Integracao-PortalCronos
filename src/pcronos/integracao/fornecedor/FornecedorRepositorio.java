package pcronos.integracao.fornecedor;

public class FornecedorRepositorio {

	public FornecedorRepositorio() {
	}

	// O método getIdFornecedorByCnpj(String cnpj) nunca fica chamado se siglaSistema == "PCronos", 
	// então não precisa retornar um objeto Integer (nullable). 
	// observação: se um dia precisar trocar "int" por "Integer", tem que tomar providencias para  
	// continuar funcionando com valor -1, veja https://docs.oracle.com/javase/tutorial/java/nutsandbolts/datatypes.html
	int getIdFornecedorByCnpj(String cnpj) throws Exception {
		if (cnpj.equals("02870737000190"))
			return 13;
		else if (cnpj.equals("00680755000265"))
			return 947;
		else if (cnpj.equals("07182763000140"))
			return 30;
		else if (cnpj.equals("04666316000178"))
			return 21;
		else if (cnpj.equals("07041307000180"))
			return 170;
		else if (cnpj.equals("24150377000195"))
			return 60;
		else if (cnpj.equals("07534303000133"))
			return 33;
		else if (cnpj.equals("11799763000160"))
			return 51;
		else if (cnpj.equals("24407389000233"))
			return 171;
		else if (cnpj.equals("03042263000151"))
			return 14;
		else if (cnpj.equals("11222333444455"))
			throw new Exception("Erro! O CNPJ da empresa fornecedora não foi informado no arquivo C:/Arquivos de Programas PC/Integração Fornecedor - Portal Cronos/" + IntegracaoFornecedorCompleta.NOME_ARQUIVO_PROPERTIES + "!");
		else {
			throw new Exception("Erro! O CNPJ da empresa fornecedora " + cnpj + " no arquivo C:/Arquivos de Programas PC/Integração Fornecedor - Portal Cronos/" + IntegracaoFornecedorCompleta.NOME_ARQUIVO_PROPERTIES + " está errado!");
		}
	}
	
	
	Fornecedor getFornecedor(Integer idFornecedor) throws Exception {
		Fornecedor f = new Fornecedor();
		f.IdFornecedor = idFornecedor;

		if (idFornecedor == 2016) {
			f.NomeFantasiaEmpresa = "Teste Windows Server 2016";
			f.SiglaSistemaFornecedor = "WinThor";
			f.EmailResponsavelTI = "";
			f.EmailResponsavelTIAlternativo = "";
			f.ApelidoResponsavelTI = "";
			f.ApelidoResponsavelTIAlternativo = "";
			f.AplicativoDesktopRemoto = "";
			f.usuarioWebservice = "";
			f.versaoJRE = "";
			f.versaoIntegrador = "";
			f.tipoSO = "Windows Server 2016";
			f.dirProgramFiles = "Program Files";
		}			   
		else if (idFornecedor == null) {
			f.NomeFantasiaEmpresa = "Monitoramento";
			f.SiglaSistemaFornecedor = "PCronos";
			f.EmailResponsavelTI = "";
			f.EmailResponsavelTIAlternativo = "";
			f.ApelidoResponsavelTI = "";
			f.ApelidoResponsavelTIAlternativo = "";
			f.AplicativoDesktopRemoto = "mstsc";
			f.versaoJRE = "jre1.8.0_92";
			f.versaoIntegrador = "2.8.2";
			f.tipoSO = "Windows Server 2008 R2 SP1";
			f.dirProgramFiles = "Program Files";
		}			   
		else if (idFornecedor == 13) {
			f.NomeFantasiaEmpresa = "Formaggio";
			f.SiglaSistemaFornecedor = "APS";
			f.EmailResponsavelTI = "projetosti@formaggio.com.br";  
			f.EmailResponsavelTIAlternativo = "ti@formaggio.com.br??????"; 
			f.ApelidoResponsavelTI = "Geymison";
			f.ApelidoResponsavelTIAlternativo = "";
			f.FuncaoResponsavelTI = "Coordenador de Projetos de TI";
			f.FuncaoResponsavelTIAlternativo = "";
			f.AplicativoDesktopRemoto = "Team Viewer";
			f.usuarioWebservice = "ws-formaggio";
			f.versaoJRE = "jre1.8.0_92";
			f.versaoIntegrador = "2.6";
			f.tipoSO = "Windows Server 2012 R2";
			f.dirProgramFiles = "Program Files";
		}			   
		else if (idFornecedor == 947) {
			f.NomeFantasiaEmpresa = "JR Distribuição";
			f.SiglaSistemaFornecedor = "WinThor";
			f.EmailResponsavelTI = "jrembalagem.ti@gmail.com"; 
			f.EmailResponsavelTIAlternativo = "";
			f.ApelidoResponsavelTI = "Ivan";
			f.ApelidoResponsavelTIAlternativo = "";
			f.AplicativoDesktopRemoto = "AnyDesk";
			f.IdAplicativoDesktopRemoto = "734228115";
			f.usuarioWebservice = "ws-jrembalagem";
			f.versaoJRE = "jre1.8.0_92";
			f.versaoIntegrador = "2.8.1";
			f.tipoSO = "Windows 10 Pro";
			f.SO32ou64bit = "64bit";
			f.dirProgramFiles = "Program Files";
		}			   
		else if (idFornecedor == 30) {
			f.NomeFantasiaEmpresa = "Prolac";
			f.SiglaSistemaFornecedor = "WinThor";
			f.EmailResponsavelTI = "mscomprolac@gmail.com"; 
			f.EmailResponsavelTIAlternativo = "marcelo@casadoqueijo.net.br";
			f.ApelidoResponsavelTI = "Marcos";
			f.ApelidoResponsavelTIAlternativo = "Marcelo";
			f.AplicativoDesktopRemoto = "AnyDesk";
			f.IdAplicativoDesktopRemoto = "425769045";
			f.usuarioWebservice = "ws-prolac";
			f.versaoJRE = "jre1.8.0_191";
			f.versaoIntegrador = "2.8.2";
			f.tipoSO = "Windows Server 2008 R2 SP1";
			f.dirProgramFiles = "Program Files (x86)";
		}			   
		else if (idFornecedor == 21) {
			f.NomeFantasiaEmpresa = "Marítimos";
			f.SiglaSistemaFornecedor = "WinThor";
			f.EmailResponsavelTI = "felipe.lolaia@maritimospescados.com.br"; 
			f.EmailResponsavelTIAlternativo = "";
			f.ApelidoResponsavelTI = "Felipe";
			f.ApelidoResponsavelTIAlternativo = "";
			f.AplicativoDesktopRemoto = "Team Viewer";
			f.usuarioWebservice = "ws-mpescados";
			f.versaoJRE = "jre1.8.0_92";
			f.versaoIntegrador = "1.2.3";
			f.tipoSO = "?????????????????????";
			f.dirProgramFiles = "Program Files";
		}			   
		else if (idFornecedor == 170) {
			f.NomeFantasiaEmpresa = "SOST";
			f.SiglaSistemaFornecedor = "WinThor";
			f.EmailResponsavelTI = "informatica@sost.com.br OU cleijonatassilva@sost.com.br"; 
			f.EmailResponsavelTIAlternativo = "informatica@sost.com.br OU carlossena@sost.com.br";
			f.ApelidoResponsavelTI = "Cleijonatas";
			f.ApelidoResponsavelTIAlternativo = "Sena";
			f.AplicativoDesktopRemoto = "AnyDesk";
			f.usuarioWebservice = "ws-sost";
			f.versaoJRE = "jre1.8.0_92";
			f.versaoIntegrador = "2.3.1";
			f.tipoSO = "Windows Server 2008 R2 SP1";
			f.dirProgramFiles = "Program Files";
		}			   
		else if (idFornecedor == 60) {
			f.NomeFantasiaEmpresa = "Karne Keijo";
			f.SiglaSistemaFornecedor = "SAP";
			f.EmailResponsavelTI = "timons@kk.com.br";
			f.EmailResponsavelTIAlternativo = "";
			f.ApelidoResponsavelTI = "Timon";
			f.ApelidoResponsavelTIAlternativo = "";
			f.AplicativoDesktopRemoto = "Team Viewer";
			f.usuarioWebservice = "ws-karnekeijo";
			f.versaoJRE = "jre1.8.0_92";
			f.versaoIntegrador = "2.4.1";
			f.tipoSO = "Windows Server 2008 R2 SP1";
			f.dirProgramFiles = "Program Files";
		}			   
		else if (idFornecedor == 33) {
			f.NomeFantasiaEmpresa = "Comal";
			f.SiglaSistemaFornecedor = "WinThor";
			f.EmailResponsavelTI = "ti@comalcomercio.com.br";
			f.EmailResponsavelTIAlternativo = "";
			f.ApelidoResponsavelTI = "Rildson"; 
							// Tem vontade para ajudar, porém é muito incompetente 
							// e muito burro, tem que explicar tudo detalhadamente
			f.ApelidoResponsavelTIAlternativo = "";
			f.AplicativoDesktopRemoto = "AnyDesk";
			f.usuarioWebservice = "ws-comal";
			f.versaoJRE = "jre1.8.0_92";
			f.versaoIntegrador = "2.8.2";
			f.tipoSO = "Windows Server 2016"; // Windows Server 2016 Standard
			f.dirProgramFiles = "Program Files";
		}			   
		else if (idFornecedor == 51) {
			f.NomeFantasiaEmpresa = "Master Commerce";
			f.SiglaSistemaFornecedor = "WinThor";
			f.EmailResponsavelTI = "";
			f.EmailResponsavelTIAlternativo = "";
			f.ApelidoResponsavelTI = "";
			f.ApelidoResponsavelTIAlternativo = "";
			f.AplicativoDesktopRemoto = "Team Viewer";
			f.usuarioWebservice = "ws-mcommerce";
			f.versaoJRE = "jre1.8.0_92";
			f.versaoIntegrador = "???????";
			f.tipoSO = "?????????????????????";
			f.dirProgramFiles = "Program Files";
		}			   
		else if (idFornecedor == 171) {
			f.NomeFantasiaEmpresa = "Propão";
			f.SiglaSistemaFornecedor = "WinThor";
			f.EmailResponsavelTI = "ti@propao.com.br";
			f.EmailResponsavelTIAlternativo = "";
			f.ApelidoResponsavelTI = "Elthon"; 
							// Desenrolado, responsável para o cadastro de De-Para´s
			f.ApelidoResponsavelTIAlternativo = "";
			f.AplicativoDesktopRemoto = "AnyDesk";
			f.usuarioWebservice = "ws-propao";
			f.versaoJRE = "jre1.8.0_92";
			f.versaoIntegrador = "2.6.1";
			f.tipoSO = "Windows Server 2008 R2 SP1"; // Na verdade "Windows Server 2008 R2 Enterprise" sem nenhum service pack.........
			f.dirProgramFiles = "Program Files";
		}			   
		else if (idFornecedor == 14) {
			f.NomeFantasiaEmpresa = "Padeirão";
			f.SiglaSistemaFornecedor = "WinThor";
			f.EmailResponsavelTI = "tiagoautran@padeirao.com";
			f.EmailResponsavelTIAlternativo = "";
			f.ApelidoResponsavelTI = "Tiago"; 
			f.ApelidoResponsavelTIAlternativo = "";
			f.AplicativoDesktopRemoto = "AnyDesk";
			f.usuarioWebservice = "ws-padeirao";
			f.versaoJRE = "jre1.8.0_111";
			f.versaoIntegrador = "2.8.2";
			f.tipoSO = "Windows Server 2012 R2"; // Windows Server 2012 R2 Standard (sem nenhum service pack)
			f.dirProgramFiles = "Program Files";
		}			   
		else { 
			throw new Exception("Erro: idFornecedor " + idFornecedor.toString() + " não existe");
		}
		
		return f;
	}
}
