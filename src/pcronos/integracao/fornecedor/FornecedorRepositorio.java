package pcronos.integracao.fornecedor;

import java.util.HashMap;
import java.util.Map.Entry;

public class FornecedorRepositorio {

	public static HashMap<Integer, Fornecedor> hashMap;

    static {
		hashMap = new HashMap<Integer, Fornecedor>(); 
		Fornecedor f1 = new Fornecedor();
		Fornecedor f2 = new Fornecedor();
		Fornecedor f3 = new Fornecedor();
		Fornecedor f4 = new Fornecedor();
		Fornecedor f5 = new Fornecedor();
		Fornecedor f6 = new Fornecedor();
		Fornecedor f7 = new Fornecedor();
		Fornecedor f8 = new Fornecedor();
		Fornecedor f9 = new Fornecedor();
		Fornecedor f10 = new Fornecedor();
		Fornecedor f11 = new Fornecedor();
    	
		f1.IdFornecedor = 2016;
		f1.NomeFantasiaEmpresa = "Teste Windows Server 2016";
		f1.versaoIntegrador = "";
		f1.SiglaSistemaFornecedor = "WinThor";
		f1.EmailResponsavelTI = "";
		f1.EmailResponsavelTIAlternativo = "";
		f1.ApelidoResponsavelTI = "";
		f1.ApelidoResponsavelTIAlternativo = "";
		f1.AplicativoDesktopRemoto = "";
		f1.usuarioWebservice = "";
		f1.versaoJRE = "";
		f1.tipoSO = "Windows Server 2016";
		f1.dirProgramFiles = "Program Files";
		hashMap.put(2016, f1);

		f2.IdFornecedor = null;
		f2.NomeFantasiaEmpresa = "Monitoramento";
		f2.versaoIntegrador = "2.8.4";
		f2.SiglaSistemaFornecedor = "PCronos";
		f2.EmailResponsavelTI = "";
		f2.EmailResponsavelTIAlternativo = "";
		f2.ApelidoResponsavelTI = "";
		f2.ApelidoResponsavelTIAlternativo = "";
		f2.AplicativoDesktopRemoto = "mstsc";
		f2.versaoJRE = "jre1.8.0_92";
		f2.tipoSO = "Windows Server 2008 R2 SP1";
		f2.dirProgramFiles = "Program Files";
		hashMap.put(null, f2);

		f3.IdFornecedor = 13;
		f3.NomeFantasiaEmpresa = "Formaggio";
		f3.versaoIntegrador = "2.6";
		f3.SiglaSistemaFornecedor = "APS";
		f3.EmailResponsavelTI = "projetosti@formaggio.com.br";  
		f3.EmailResponsavelTIAlternativo = "ti@formaggio.com.br??????"; 
		f3.SkypeResponsavelTI = "live:projetosti_1 = Geymison Lima - TI Formaggio";
		f3.SkypeResponsavelTIAlternativo = "Braytner Vasconcelos";
		f3.ApelidoResponsavelTI = "Geymison";
		f3.ApelidoResponsavelTIAlternativo = "";
		f3.FuncaoResponsavelTI = "Coordenador de Projetos de TI";
		f3.FuncaoResponsavelTIAlternativo = "";
		f3.AplicativoDesktopRemoto = "Team Viewer";
		f3.usuarioWebservice = "ws-formaggio";
		f3.versaoJRE = "jre1.8.0_92";
		f3.tipoSO = "Windows Server 2012 R2";
		f3.dirProgramFiles = "Program Files";
		f3.cnpjFornecedor = "02870737000190";
		hashMap.put(13, f3);

		f4.IdFornecedor = 947;
		f4.NomeFantasiaEmpresa = "JR Distribuição";
		f4.versaoIntegrador = "2.8.1";
		f4.SiglaSistemaFornecedor = "WinThor";
		f4.EmailResponsavelTI = "jrembalagem.ti@gmail.com"; 
		f4.EmailResponsavelTIAlternativo = "";
		f4.SkypeResponsavelTI = "ivan barros";
		f4.ApelidoResponsavelTI = "Ivan";
		f4.ApelidoResponsavelTIAlternativo = "";
		f4.AplicativoDesktopRemoto = "AnyDesk";
		f4.IdAplicativoDesktopRemoto = "734228115";
		f4.usuarioWebservice = "ws-jrembalagem";
		f4.versaoJRE = "jre1.8.0_92";
		f4.tipoSO = "Windows 10 Pro";
		f4.SO32ou64bit = "64bit";
		f4.dirProgramFiles = "Program Files";
		f4.cnpjFornecedor = "00680755000265";
		hashMap.put(947, f4);

		f5.IdFornecedor = 30;
		f5.NomeFantasiaEmpresa = "Prolac";
		f5.versaoIntegrador = "2.8.2";
		f5.SiglaSistemaFornecedor = "WinThor";
		f5.EmailResponsavelTI = "mscomprolac@gmail.com"; 
		f5.EmailResponsavelTIAlternativo = "marcelo@casadoqueijo.net.br";
		f5.SkypeResponsavelTI = "marcos.scognamiglio";
		f5.SkypeResponsavelTIAlternativo = "Marcelo Bezerra";
		f5.ApelidoResponsavelTI = "Marcos";
		f5.ApelidoResponsavelTIAlternativo = "Marcelo";
		f5.AplicativoDesktopRemoto = "AnyDesk";
		f5.IdAplicativoDesktopRemoto = "425769045";
		f5.usuarioWebservice = "ws-prolac";
		f5.versaoJRE = "jre1.8.0_191";
		f5.tipoSO = "Windows Server 2008 R2 SP1";
		f5.dirProgramFiles = "Program Files (x86)";
		f5.cnpjFornecedor = "07182763000140";
		hashMap.put(30, f5);

		f6.IdFornecedor = 21;
		f6.NomeFantasiaEmpresa = "Marítimos";
		f6.versaoIntegrador = "1.2.3";
		f6.SiglaSistemaFornecedor = "WinThor";
		f6.EmailResponsavelTI = "felipe.lolaia@maritimospescados.com.br"; 
		f6.EmailResponsavelTIAlternativo = "";
		f6.SkypeResponsavelTI = "Felipe Lolaia";
		f6.ApelidoResponsavelTI = "Felipe";
		f6.ApelidoResponsavelTIAlternativo = "";
		f6.AplicativoDesktopRemoto = "Team Viewer";
		f6.usuarioWebservice = "ws-mpescados";
		f6.versaoJRE = "jre1.8.0_92";
		f6.tipoSO = "?????????????????????";
		f6.dirProgramFiles = "Program Files";
		f6.cnpjFornecedor = "04666316000178";
		hashMap.put(21, f6);

		f7.IdFornecedor = 170;
		f7.NomeFantasiaEmpresa = "SOST";
		f7.versaoIntegrador = "2.3.1";
		f7.SiglaSistemaFornecedor = "WinThor";
		f7.EmailResponsavelTI = "informatica@sost.com.br OU cleijonatassilva@sost.com.br"; 
		f7.EmailResponsavelTIAlternativo = "informatica@sost.com.br OU carlossena@sost.com.br";
		f7.SkypeResponsavelTI = "Cleijonatas S Silva";
		f7.SkypeResponsavelTIAlternativo = "Sena Junior";
		f7.ApelidoResponsavelTI = "Cleijonatas";
		f7.ApelidoResponsavelTIAlternativo = "Sena";
		f7.AplicativoDesktopRemoto = "AnyDesk";
		f7.usuarioWebservice = "ws-sost";
		f7.versaoJRE = "jre1.8.0_92";
		f7.tipoSO = "Windows Server 2008 R2 SP1";
		f7.dirProgramFiles = "Program Files";
		f7.cnpjFornecedor = "07041307000180";
		hashMap.put(170, f7);

		f8.IdFornecedor = 60;
		f8.NomeFantasiaEmpresa = "Karne Keijo";
		f8.versaoIntegrador = "2.4.1";
		f8.SiglaSistemaFornecedor = "SAP";
		f8.EmailResponsavelTI = "timons@kk.com.br";
		f8.EmailResponsavelTIAlternativo = "";
		f8.SkypeResponsavelTI = "Timon Dourado";
		f8.ApelidoResponsavelTI = "Timon";
		f8.ApelidoResponsavelTIAlternativo = "";
		f8.AplicativoDesktopRemoto = "Team Viewer";
		f8.usuarioWebservice = "ws-karnekeijo";
		f8.versaoJRE = "jre1.8.0_92";
		f8.tipoSO = "Windows Server 2008 R2 SP1";
		f8.dirProgramFiles = "Program Files";
		f8.cnpjFornecedor = "24150377000195";
		hashMap.put(60, f8);

		f9.IdFornecedor = 33;
		f9.NomeFantasiaEmpresa = "Comal";
		f9.versaoIntegrador = "2.8.2";
		f9.SiglaSistemaFornecedor = "WinThor";
		f9.EmailResponsavelTI = "ti@comalcomercio.com.br";
		f9.EmailResponsavelTIAlternativo = "";
		f9.SkypeResponsavelTI = "Rildson Carlos";
		f9.ApelidoResponsavelTI = "Rildson"; 
						// Tem vontade para ajudar, porém é muito incompetente 
						// e muito burro, tem que explicar tudo detalhadamente
		f9.ApelidoResponsavelTIAlternativo = "";
		f9.AplicativoDesktopRemoto = "AnyDesk";
		f9.usuarioWebservice = "ws-comal";
		f9.versaoJRE = "jre1.8.0_92";
		f9.tipoSO = "Windows Server 2016"; // Windows Server 2016 Standard
		f9.dirProgramFiles = "Program Files";
		f9.cnpjFornecedor = "07534303000133";
		hashMap.put(33, f9);

		f10.IdFornecedor = 171;
		f10.NomeFantasiaEmpresa = "Propão";
		f10.versaoIntegrador = "2.6.1";
		f10.SiglaSistemaFornecedor = "WinThor";
		f10.EmailResponsavelTI = "ti@propao.com.br";
		f10.EmailResponsavelTIAlternativo = "";
		f10.SkypeResponsavelTI = "Apoio.propao@hotmail.com";
		f10.ApelidoResponsavelTI = "Elthon"; 
						// Desenrolado, responsável para o cadastro de De-Para´s
		f10.ApelidoResponsavelTIAlternativo = "";
		f10.AplicativoDesktopRemoto = "AnyDesk";
		f10.usuarioWebservice = "ws-propao";
		f10.versaoJRE = "jre1.8.0_92";
		f10.tipoSO = "Windows Server 2008 R2 SP1"; // Na verdade "Windows Server 2008 R2 Enterprise" sem nenhum service pack.........
		f10.dirProgramFiles = "Program Files";
		f10.cnpjFornecedor = "24407389000233";
		hashMap.put(171, f10);

		f11.IdFornecedor = 14;
		f11.NomeFantasiaEmpresa = "Padeirão";
		f11.versaoIntegrador = "2.8.3";
		f11.SiglaSistemaFornecedor = "WinThor";
		f11.EmailResponsavelTI = "tiagoautran@padeirao.com";
		f11.EmailResponsavelTIAlternativo = "";
		f11.SkypeResponsavelTI = "tiagoautran";
		f11.ApelidoResponsavelTI = "Tiago"; 
		f11.ApelidoResponsavelTIAlternativo = "";
		f11.AplicativoDesktopRemoto = "AnyDesk";
		f11.usuarioWebservice = "ws-padeirao";
		f11.versaoJRE = "jre1.8.0_111";
		f11.tipoSO = "Windows Server 2012 R2"; // Windows Server 2012 R2 Standard (sem nenhum service pack)
		f11.dirProgramFiles = "Program Files";
		f11.cnpjFornecedor = "03042263000151";
		hashMap.put(14, f11);
		
    }
	
	public FornecedorRepositorio() {
	}
	

	// O método getIdFornecedorByCnpj(String cnpj) nunca fica chamado se siglaSistema == "PCronos", 
	// então não precisa retornar um objeto Integer (nullable). 
	// Observação: se um dia precisar trocar "int" por "Integer", tem que tomar providencias para  
	// continuar funcionando com valor -1, veja https://docs.oracle.com/javase/tutorial/java/nutsandbolts/datatypes.html
	int getIdFornecedorByCnpj(String cnpj) throws Exception 
	{
		if (cnpj == null || cnpj.equals("11222333444455"))
			throw new Exception("Erro! O CNPJ da empresa fornecedora não foi informado no arquivo C:/Arquivos de Programas PC/Integração Fornecedor - Portal Cronos/" + IntegracaoFornecedorCompleta.NOME_ARQUIVO_PROPERTIES + "!");
		else 
		{
			Fornecedor f = null;
			
	        for (Entry<Integer, Fornecedor> entry : hashMap.entrySet()) {
		        Object value = entry.getValue();
		        
		        if ( !Utils.isNullOrBlank(((Fornecedor)value).cnpjFornecedor) && ((Fornecedor)value).cnpjFornecedor.equals(cnpj)) 
		        {
		        	f = ((Fornecedor)value);
		        }
		    }

			if (f == null)
				throw new Exception("Erro! O CNPJ da empresa fornecedora \"" + cnpj + "\" no arquivo C:/Arquivos de Programas PC/Integração Fornecedor - Portal Cronos/" + IntegracaoFornecedorCompleta.NOME_ARQUIVO_PROPERTIES + " está errado!");
			else
	            return f.IdFornecedor;
		}
	}

	
	
	Fornecedor getFornecedor(Integer idFornecedor) throws Exception {		
		Fornecedor f = hashMap.get(idFornecedor);
		
		if (f == null)
			throw new Exception("Erro: idFornecedor " + idFornecedor.toString() + " não existe");

		return f;
	}

}
