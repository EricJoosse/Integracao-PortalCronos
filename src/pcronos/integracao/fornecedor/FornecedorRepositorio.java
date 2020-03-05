package pcronos.integracao.fornecedor;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import pcronos.integracao.fornecedor.entidades.ConfigMonitoradorIntegradores;

import com.microsoft.sqlserver.jdbc.SQLServerException;

import org.hibernate.SessionFactory;
import org.hibernate.HibernateException;

//import javax.persistence.RollbackException;
//import javax.validation.ConstraintViolationException;
//import javax.validation.ConstraintViolation;

//import org.hibernate.validator.constraints.InvalidValue; 
//import org.hibernate.validator.constraints.InvalidStateException;
//import org.hibernate.validator.InvalidValue;          // hibernate.validator.3.1.0.GA 
//import org.hibernate.validator.InvalidStateException; // hibernate.validator.3.1.0.GA

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public class FornecedorRepositorio {

	public static HashMap<Integer, Fornecedor> hashMap;
	private static SessionFactory factory; 

    static {
		hashMap = new HashMap<Integer, Fornecedor>(); 
		final int qtdFornecedores = 14;
		Fornecedor[] f = new Fornecedor[qtdFornecedores];

		for (int j=0; j < (qtdFornecedores); j++) {
			f[j] = new Fornecedor();			
		}
		
		int i = 0;
		
		f[i].IdFornecedor = 2016;
		f[i].NomeFantasiaEmpresa = "Teste Win Server 2016";
		f[i].versaoIntegrador = "";
		f[i].SiglaSistemaFornecedor = "WinThor";
		f[i].IsServicoNuvem = false;
		f[i].IsEmProducao = "Não";
		f[i].EmailResponsavelTI = "";
		f[i].EmailResponsavelTIAlternativo = "";
		f[i].PrenomeResponsavelTI = "";
		f[i].PrenomeResponsavelTIAlternativo = "";
		f[i].AplicativoDesktopRemoto = "";
		f[i].usuarioWebservice = "";
		f[i].versaoJRE = "";
		f[i].tipoSO = "Windows Server 2016";
		f[i].dirProgramFiles = "Program Files";
		f[i].QtdDiasArquivosXmlGuardados = 100;
		f[i].FrequenciaProcessamento = "15 min";

		i++;
		f[i].IdFornecedor = null;
		f[i].NomeFantasiaEmpresa = "Monitoramento";
		f[i].versaoIntegrador = "3.4.1";
		f[i].SiglaSistemaFornecedor = "PCronos";
		f[i].IsServicoNuvem = false;
		f[i].IsEmProducao = "Sim";
		f[i].IsDebugAtivado = false;
		f[i].EmailResponsavelTI = "";
		f[i].EmailResponsavelTIAlternativo = "";
		f[i].PrenomeResponsavelTI = "";
		f[i].PrenomeResponsavelTIAlternativo = "";
		f[i].AplicativoDesktopRemoto = "mstsc";
		f[i].versaoJRE = "jre1.8.0_92";
		f[i].tipoJRE = "(.. bit)";
		f[i].tipoSO = "Windows Server 2008 R2 SP1";
		f[i].dirProgramFiles = "Program Files";
		f[i].DiscoIntegrador = "C";
		f[i].EspacoLivreDisco = "90 GB";
		f[i].QtdDiasArquivosXmlGuardados = 32;
		f[i].MemoriaRamLivre = "1.4 - 1.9 GB";
		f[i].EnderecoIpPublicoServidor = "Não se aplica";
		f[i].PortaIpAberta = "Não se aplica";
		f[i].FrequenciaProcessamento = "15 min";

		i++;
		f[i].IdFornecedor = -2;
		f[i].NomeFantasiaEmpresa = "Monitoramento Banco";
		f[i].versaoIntegrador = "3.4.1";
		f[i].SiglaSistemaFornecedor = "PCronos";
		f[i].IsServicoNuvem = false;
		f[i].IsEmProducao = "Sim";
		f[i].IsDebugAtivado = false;
		f[i].EmailResponsavelTI = "";
		f[i].EmailResponsavelTIAlternativo = "";
		f[i].PrenomeResponsavelTI = "";
		f[i].PrenomeResponsavelTIAlternativo = "";
		f[i].AplicativoDesktopRemoto = "mstsc";
		f[i].versaoJRE = "jre1.8.0_92";
		f[i].tipoJRE = "(.. bit)";
		f[i].tipoSO = "Windows Server 2012 R2";
		f[i].dirProgramFiles = "Program Files";
		f[i].DiscoIntegrador = "C";
		f[i].EspacoLivreDisco = "43 GB";
		f[i].QtdDiasArquivosXmlGuardados = 32;
		f[i].MemoriaRamLivre = "1.2 GB";
		f[i].EnderecoIpPublicoServidor = "Não se aplica";
		f[i].PortaIpAberta = "Não se aplica";
		f[i].FrequenciaProcessamento = "5 min";

		i++;
		f[i].IdFornecedor = 13;
		f[i].NomeFantasiaEmpresa = "Formaggio";
		f[i].versaoIntegrador = "2.6";
		f[i].SiglaSistemaFornecedor = "APS";
		f[i].IsServicoNuvem = false;
		f[i].IsEmProducao = "Sim";
		f[i].IsDebugAtivado = true;
		f[i].EmailResponsavelTI = "projetosti@formaggio.com.br";  
		f[i].EmailResponsavelTIAlternativo = "ti@formaggio.com.br??????"; 
		f[i].SkypeResponsavelTI = "live:projetosti_1 = Geymison Lima - TI Formaggio";
		f[i].SkypeResponsavelTIAlternativo = "Braytner Vasconcelos";
		f[i].PrenomeResponsavelTI = "Geymison";
		f[i].PrenomeResponsavelTIAlternativo = "";
		f[i].FuncaoResponsavelTI = "Coordenador de Projetos de TI";
		f[i].FuncaoResponsavelTIAlternativo = "";
		f[i].TelefoneResponsavelTI = "";
		f[i].AplicativoDesktopRemoto = "Team Viewer";
		f[i].usuarioWebservice = "ws-formaggio";
		f[i].versaoJRE = "jre1.8.0_92";
		f[i].tipoJRE = "(.. bit)";
		f[i].tipoSO = "Windows Server 2012 R2";
		f[i].dirProgramFiles = "Program Files";
		f[i].cnpjFornecedor = "02870737000190";
		f[i].DiscoIntegrador = "C";
		f[i].EspacoLivreDisco = "9 GB";
		f[i].QtdDiasArquivosXmlGuardados = 100;
		f[i].MemoriaRamLivre = "";
		f[i].EnderecoIpPublicoServidor = "187.113.120.97";
		f[i].PortaIpAberta = "80";
		f[i].FrequenciaProcessamento = "15 min";

		i++;
		f[i].IdFornecedor = 947;
		f[i].NomeFantasiaEmpresa = "JR Distribuição";
		f[i].versaoIntegrador = "2.8.1";
		f[i].SiglaSistemaFornecedor = "WinThor";
		f[i].IsServicoNuvem = false;
		f[i].IsEmProducao = "Sim";
		f[i].IsDebugAtivado = true;
		f[i].EmailResponsavelTI = "jrembalagem.ti@gmail.com"; 
		f[i].EmailResponsavelTIAlternativo = "";
		f[i].SkypeResponsavelTI = "ivan barros";
		f[i].PrenomeResponsavelTI = "Ivan";
		f[i].PrenomeResponsavelTIAlternativo = "";
		f[i].TelefoneResponsavelTI = "";
		f[i].AplicativoDesktopRemoto = "AnyDesk";
		f[i].IdAplicativoDesktopRemoto = "bljj9pg@ad ou 734228115";
		f[i].IdAplicativoDesktopRemoto = "734228115";
		f[i].usuarioWebservice = "ws-jrembalagem";
		f[i].versaoJRE = "jre1.8.0_92";
		f[i].tipoJRE = "(.. bit)";
		f[i].tipoSO = "Windows 10 Pro";
		f[i].SO32ou64bit = "64bit";
		f[i].dirProgramFiles = "Program Files";
		f[i].cnpjFornecedor = "00680755000265";
		f[i].DiscoIntegrador = "C";
		f[i].EspacoLivreDisco = "902 GB";
		f[i].QtdDiasArquivosXmlGuardados = 100;
		f[i].MemoriaRamLivre = "";
		f[i].EnderecoIpPublicoServidor = "jrolinda.dyndns.org";
		f[i].PortaIpAberta = "Não precisa";
		f[i].FrequenciaProcessamento = "15 min";

		i++;
		f[i].IdFornecedor = 30;
		f[i].NomeFantasiaEmpresa = "Prolac";
		f[i].versaoIntegrador = "3.4.0";
		f[i].SiglaSistemaFornecedor = "WinThor";
		f[i].IsServicoNuvem = false;
		f[i].IsEmProducao = "Sim";
		f[i].IsDebugAtivado = true;
		f[i].EmailResponsavelTI = "mscomprolac@gmail.com"; 
		f[i].EmailResponsavelTIAlternativo = "marcelo@casadoqueijo.net.br";
		f[i].SkypeResponsavelTI = "marcos.scognamiglio";
		f[i].SkypeResponsavelTIAlternativo = "Marcelo Bezerra";
		f[i].PrenomeResponsavelTI = "Marcos";
		f[i].PrenomeResponsavelTIAlternativo = "Marcelo";
		f[i].TelefoneResponsavelTI = "";
		f[i].AplicativoDesktopRemoto = "AnyDesk";
		f[i].IdAplicativoDesktopRemoto = "739 265 928 (Marcos) - era servidormaxima@ad (Marcelo)";
		f[i].usuarioWebservice = "ws-prolac";
		f[i].versaoJRE = "jre1.8.0_231";
		f[i].tipoJRE = "(64 bit)";
		f[i].tipoSO = "Windows Server 2012 R2";
		f[i].dirProgramFiles = "Program Files";
		f[i].cnpjFornecedor = "07182763000140"; 
		f[i].DiscoIntegrador = "C";
		f[i].EspacoLivreDisco = "269 GB";
		f[i].QtdDiasArquivosXmlGuardados = 100;
		f[i].MemoriaRamLivre = "6.5 GB";
		f[i].EnderecoIpPublicoServidor = "187.103.76.53";
		f[i].PortaIpAberta = "80";
		f[i].FrequenciaProcessamento = "15 min";

		i++;
		f[i].IdFornecedor = 21;
		f[i].NomeFantasiaEmpresa = "Marítimos";
		f[i].versaoIntegrador = "1.2.3";
		f[i].SiglaSistemaFornecedor = "WinThor";
		f[i].IsServicoNuvem = false;
		f[i].IsEmProducao = "Naõ";
		f[i].IsDebugAtivado = true;
		f[i].EmailResponsavelTI = "felipe.lolaia@maritimospescados.com.br"; 
		f[i].EmailResponsavelTIAlternativo = "";
		f[i].SkypeResponsavelTI = "Felipe Lolaia";
		f[i].PrenomeResponsavelTI = "Felipe";
		f[i].PrenomeResponsavelTIAlternativo = "";
		f[i].TelefoneResponsavelTI = "";
		f[i].AplicativoDesktopRemoto = "Team Viewer";
		f[i].usuarioWebservice = "ws-mpescados";
		f[i].versaoJRE = "jre1.8.0_92";
		f[i].tipoJRE = "(.. bit)";
		f[i].tipoSO = "?????????????????????";
		f[i].dirProgramFiles = "Program Files";
		f[i].cnpjFornecedor = "04666316000178";
		f[i].DiscoIntegrador = "C";
		f[i].EspacoLivreDisco = "48 GB";
		f[i].QtdDiasArquivosXmlGuardados = 100;
		f[i].MemoriaRamLivre = "";
		f[i].EnderecoIpPublicoServidor = "";
		f[i].PortaIpAberta = "";
		f[i].FrequenciaProcessamento = "15 min";

		i++;
		f[i].IdFornecedor = 170;
		f[i].NomeFantasiaEmpresa = "SOST";
		f[i].versaoIntegrador = "2.3.1";
		f[i].SiglaSistemaFornecedor = "WinThor";
		f[i].IsServicoNuvem = false;
		f[i].IsEmProducao = "Não";
		f[i].IsDebugAtivado = true;
		f[i].EmailResponsavelTI = "informatica@sost.com.br OU cleijonatassilva@sost.com.br"; 
		f[i].EmailResponsavelTIAlternativo = "informatica@sost.com.br OU carlossena@sost.com.br";
		f[i].SkypeResponsavelTI = "Cleijonatas S Silva";
		f[i].SkypeResponsavelTIAlternativo = "Sena Junior";
		f[i].PrenomeResponsavelTI = "Cleijonatas";
		f[i].PrenomeResponsavelTIAlternativo = "Sena";
		f[i].TelefoneResponsavelTI = "";
		f[i].AplicativoDesktopRemoto = "AnyDesk";
		f[i].usuarioWebservice = "ws-sost";
		f[i].versaoJRE = "jre1.8.0_92";
		f[i].tipoJRE = "(.. bit)";
		f[i].tipoSO = "Windows Server 2008 R2 SP1";
		f[i].dirProgramFiles = "Program Files";
		f[i].cnpjFornecedor = "07041307000180";
		f[i].DiscoIntegrador = "C";
		f[i].EspacoLivreDisco = "0.6 GB";
		f[i].QtdDiasArquivosXmlGuardados = 15;
		f[i].MemoriaRamLivre = "";
		f[i].EnderecoIpPublicoServidor = "";
		f[i].PortaIpAberta = "";
		f[i].FrequenciaProcessamento = "15 min";

		i++;
		f[i].IdFornecedor = 60;
		f[i].NomeFantasiaEmpresa = "Karne Keijo";
		f[i].versaoIntegrador = "2.4.1";
		f[i].SiglaSistemaFornecedor = "SAP";
		f[i].IsServicoNuvem = false;
		f[i].IsEmProducao = "Não";
		f[i].IsDebugAtivado = true;
		f[i].EmailResponsavelTI = "timons@kk.com.br";
		f[i].EmailResponsavelTIAlternativo = "";
		f[i].SkypeResponsavelTI = "Timon Dourado";
		f[i].PrenomeResponsavelTI = "Timon";
		f[i].PrenomeResponsavelTIAlternativo = "";
		f[i].TelefoneResponsavelTI = "";
		f[i].AplicativoDesktopRemoto = "Team Viewer";
		f[i].usuarioWebservice = "ws-karnekeijo";
		f[i].versaoJRE = "jre1.8.0_92";
		f[i].tipoJRE = "(.. bit)";
		f[i].tipoSO = "Windows Server 2008 R2 SP1";
		f[i].dirProgramFiles = "Program Files";
		f[i].cnpjFornecedor = "24150377000195";
		f[i].DiscoIntegrador = "C";
		f[i].EspacoLivreDisco = "62 GB";
		f[i].QtdDiasArquivosXmlGuardados = 100;
		f[i].MemoriaRamLivre = "";
		f[i].EnderecoIpPublicoServidor = "";
		f[i].PortaIpAberta = "";
		f[i].FrequenciaProcessamento = "15 min";

		i++;
		f[i].IdFornecedor = 33;
		f[i].NomeFantasiaEmpresa = "Comal";
		f[i].versaoIntegrador = "2.8.2";
		f[i].SiglaSistemaFornecedor = "WinThor";
		f[i].IsServicoNuvem = false;
		f[i].IsEmProducao = "Não";
		f[i].IsDebugAtivado = true;
		f[i].EmailResponsavelTI = "ti@comalcomercio.com.br";
		f[i].EmailResponsavelTIAlternativo = "";
		f[i].SkypeResponsavelTI = "Rildson Carlos";
		f[i].PrenomeResponsavelTI = "Rildson"; 
						// Tem vontade para ajudar, porém é muito incompetente 
						// e muito burro, tem que explicar tudo detalhadamente
		f[i].PrenomeResponsavelTIAlternativo = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
		f[i].TelefoneResponsavelTI = "98609-4887";
		f[i].AplicativoDesktopRemoto = "AnyDesk";
		f[i].IdAplicativoDesktopRemoto = "usuário FusionDMS: 833 323 573, Administrador: 886 263 056";
// - Comal: 1. usuário FusionDMS:    este usuário fica em uma VM, por isso usuário FusionDMS 
//									 não aparece na lista de usuários se conectar com Administrador.....)
// 			2. usuário Administrador: !!!!!! INSTALADO COM ESTE USUÁRIO!!!!!!!
		
		f[i].usuarioWebservice = "ws-comal";
		f[i].versaoJRE = "jre1.8.0_92";
		f[i].tipoJRE = "(.. bit)";
		f[i].tipoSO = "Windows Server 2016"; // Windows Server 2016 Standard
		f[i].dirProgramFiles = "Program Files";
		f[i].cnpjFornecedor = "07534303000133";
		f[i].DiscoIntegrador = "C";
		f[i].EspacoLivreDisco = "69 GB";
		f[i].MemoriaRamLivre = "";
		f[i].EnderecoIpPublicoServidor = "187.1.168.74";
		f[i].PortaIpAberta = "80";
		f[i].FrequenciaProcessamento = "15 min";

		i++;
		f[i].IdFornecedor = 171;
		f[i].NomeFantasiaEmpresa = "Propão";
		f[i].versaoIntegrador = "2.6.1";
		f[i].SiglaSistemaFornecedor = "WinThor";
		f[i].IsServicoNuvem = false;
		f[i].IsEmProducao = "Sim";
		f[i].IsDebugAtivado = true;
		f[i].EmailResponsavelTI = "ti@propao.com.br";
		f[i].EmailResponsavelTIAlternativo = "";
		f[i].SkypeResponsavelTI = "Apoio.propao@hotmail.com";
		f[i].PrenomeResponsavelTI = "Elthon"; 
						// Desenrolado, responsável para o cadastro de De-Para´s
		f[i].PrenomeResponsavelTIAlternativo = "";
		f[i].TelefoneResponsavelTI = "99535-1999";
		f[i].AplicativoDesktopRemoto = "AnyDesk";
		f[i].IdAplicativoDesktopRemoto = "382 127 135";
		f[i].usuarioWebservice = "ws-propao";
		f[i].versaoJRE = "jre1.8.0_92";
		f[i].tipoJRE = "(.. bit)";
		f[i].tipoSO = "Windows Server 2008 R2 SP1"; // Na verdade "Windows Server 2008 R2 Enterprise" sem nenhum service pack.........
		f[i].dirProgramFiles = "Program Files";
		f[i].cnpjFornecedor = "24407389000233";
		f[i].DiscoIntegrador = "C";
		f[i].EspacoLivreDisco = "167 GB";
		f[i].MemoriaRamLivre = "";
		f[i].EnderecoIpPublicoServidor = "187.113.65.138";
		f[i].PortaIpAberta = "Não se aplica";
		f[i].FrequenciaProcessamento = "15 min";

		i++;
		f[i].IdFornecedor = 14;
		f[i].NomeFantasiaEmpresa = "Padeirão";
		f[i].versaoIntegrador = "2.8.3";
		f[i].SiglaSistemaFornecedor = "WinThor";
		f[i].IsServicoNuvem = false;
		f[i].IsEmProducao = "Sim";
		f[i].IsDebugAtivado = true;
		f[i].EmailResponsavelTI = "tiagoautran@padeirao.com";
		f[i].EmailResponsavelTIAlternativo = "";
		f[i].SkypeResponsavelTI = "tiagoautran";
		f[i].PrenomeResponsavelTI = "Tiago"; 
		f[i].PrenomeResponsavelTIAlternativo = "";
		f[i].TelefoneResponsavelTI = "99116-8070 ou 3073-4551";
		f[i].AplicativoDesktopRemoto = "AnyDesk";
		f[i].usuarioWebservice = "ws-padeirao";
		f[i].versaoJRE = "jre1.8.0_111";
		f[i].tipoJRE = "(.. bit)";
		f[i].tipoSO = "Windows Server 2012 R2"; // Windows Server 2012 R2 Standard (sem nenhum service pack)
		f[i].dirProgramFiles = "Program Files";
		f[i].cnpjFornecedor = "03042263000151";
		f[i].DiscoIntegrador = "C";
		f[i].EspacoLivreDisco = "703 GB";
		f[i].MemoriaRamLivre = "";
		f[i].EnderecoIpPublicoServidor = "192.141.110.189";
		f[i].PortaIpAberta = "Não se aplica";
		f[i].FrequenciaProcessamento = "15 min";

		
		
		
		String versaoIntegradorApsCloud = "3.4.0";
		String SiglaSistemaFornecedorApsCloud = "APS";
		String AplicativoDesktopRemotoApsCloud = "AnyDesk";
		String IdAplicativoDesktopRemotoApsCloud = "759304784 (era desktop-fvkrc84@ad)";
		String tipoSOApsCloud = "Windows Server 2008 R2 SP1"; 
		String dirProgramFilesApsCloud = "Program Files (x86)";
		String versaoJREApsCloud = "jre1.8.0_191";
		String tipoJREApsCloud = "(32 bit)";
		String DiscoIntegradorApsCloud = "C";
		String EspacoLivreDiscoApsCloud = "18 GB";
		String MemoriaRamLivreApsCloud = "0 - 200 MB";
		String EnderecoIpPublicoServidorApsCloud = "18.214.93.120";
		String PortaIpAbertaApsCloud = "2525";
		String FrequenciaProcessamentoApsCloud = "15 min";
				
		String EmailResponsavelTIApsCloud = "bernardino@apsinformatica.com.br";
		String SkypeResponsavelTIApsCloud = "Bernardino Borba (live:berna31pe)";
		String PrenomeResponsavelTIApsCloud = "Bernardino"; 
		String FuncaoResponsavelTIApsCloud = "Sócio do APS";
		String TelefoneResponsavelTIApsCloud = "99780-1192";
		
		String EmailResponsavelTIAlternativoApsCloud = "saulo@apsinformatica.com.br";		
		String SkypeResponsavelTIAlternativoApsCloud = "Saulo Gomes de Lima (saulo_analista)";		
		String PrenomeResponsavelTIAlternativoApsCloud = "Saulo"; // Alguém foi demitido, provavelmente foi ele
		String FuncaoResponsavelTIAlternativoApsCloud = "Analista";
		
		String EmailResponsavelTI_NuvemApsCloud = "bernardino@apsinformatica.com.br";
		String SkypeResponsavelTI_NuvemApsCloud = "Bernardino Borba (live:berna31pe)"; 
		String PrenomeResponsavelTI_NuvemApsCloud = "Bernardino"; 
		String TelefoneResponsavelTI_NuvemApsCloud = "99780-1192"; 

		
		
		i++;
		f[i].IdFornecedor = 1995; // APS Cloud - primeiro fornecedor de muitos
		f[i].NomeFantasiaEmpresa = "Marizpan";   // APS Cloud - primeiro fornecedor de muitos
		f[i].cnpjFornecedor = "12286800000108";  // APS Cloud - primeiro fornecedor de muitos
		f[i].versaoIntegrador = versaoIntegradorApsCloud;
		f[i].SiglaSistemaFornecedor = SiglaSistemaFornecedorApsCloud;

		f[i].IsServicoNuvem = true;
		f[i].SequenciaInstanciaNuvem = 1;

		f[i].IsEmProducao = "Sim";
		f[i].IsDebugAtivado = true;
		f[i].AplicativoDesktopRemoto = AplicativoDesktopRemotoApsCloud;
		f[i].IdAplicativoDesktopRemoto = IdAplicativoDesktopRemotoApsCloud; 
		f[i].usuarioWebservice = "ws-marizpan";  // APS Cloud - primeiro fornecedor de muitos
		f[i].tipoSO = tipoSOApsCloud; 
		f[i].dirProgramFiles = dirProgramFilesApsCloud;
		f[i].versaoJRE = versaoJREApsCloud;
		f[i].tipoJRE = tipoJREApsCloud;
		f[i].DiscoIntegrador = DiscoIntegradorApsCloud;
		f[i].EspacoLivreDisco = EspacoLivreDiscoApsCloud;
		f[i].MemoriaRamLivre = MemoriaRamLivreApsCloud;
		f[i].EnderecoIpPublicoServidor = EnderecoIpPublicoServidorApsCloud;
		f[i].PortaIpAberta = PortaIpAbertaApsCloud;
		f[i].FrequenciaProcessamento = FrequenciaProcessamentoApsCloud; 

		f[i].ResponsavelDeParasProdutos = "Uma vendedora";  // APS Cloud - primeiro fornecedor de muitos

		f[i].EmailResponsavelTI = EmailResponsavelTIApsCloud;
		f[i].SkypeResponsavelTI = SkypeResponsavelTIApsCloud;
		f[i].PrenomeResponsavelTI = PrenomeResponsavelTIApsCloud; 
		f[i].FuncaoResponsavelTI = FuncaoResponsavelTIApsCloud;
		f[i].TelefoneResponsavelTI = TelefoneResponsavelTIApsCloud;
		
		f[i].EmailResponsavelTIAlternativo = EmailResponsavelTIAlternativoApsCloud;		
		f[i].SkypeResponsavelTIAlternativo = SkypeResponsavelTIAlternativoApsCloud;		
		f[i].PrenomeResponsavelTIAlternativo = PrenomeResponsavelTIAlternativoApsCloud;
		f[i].FuncaoResponsavelTIAlternativo = FuncaoResponsavelTIAlternativoApsCloud;
		
		f[i].EmailResponsavelTI_Nuvem = EmailResponsavelTI_NuvemApsCloud;
		f[i].SkypeResponsavelTI_Nuvem = SkypeResponsavelTI_NuvemApsCloud; 
		f[i].PrenomeResponsavelTI_Nuvem = PrenomeResponsavelTI_NuvemApsCloud;
		f[i].TelefoneResponsavelTI_Nuvem = TelefoneResponsavelTI_NuvemApsCloud;

		

		
		i++;
		f[i].IdFornecedor = 1; 
		f[i].NomeFantasiaEmpresa = "Atacamax";   
		f[i].cnpjFornecedor = "08305623000184";  
		f[i].versaoIntegrador = "3.2.0"; // versaoIntegradorApsCloud;
		f[i].SiglaSistemaFornecedor = SiglaSistemaFornecedorApsCloud;

		f[i].IsServicoNuvem = false; // true;
	 // f[i].SequenciaInstanciaNuvem = 2;

		f[i].IsEmProducao = "Sim";
		f[i].IsDebugAtivado = true;
		f[i].AplicativoDesktopRemoto = AplicativoDesktopRemotoApsCloud;
		f[i].usuarioWebservice = "ws-atacamax";  
		f[i].tipoSO = "Windows Server 2012 R2"; //tipoSOApsCloud; 
		f[i].dirProgramFiles = "Program Files (x86)"; // dirProgramFilesApsCloud;
		f[i].versaoJRE = "jre1.8.0_211"; // versaoJREApsCloud;
		f[i].tipoJRE = "(32 bit)";  // tipoJREApsCloud
		f[i].DiscoIntegrador = "C"; // DiscoIntegradorApsCloud;
		f[i].EspacoLivreDisco = "41 GB"; // EspacoLivreDiscoApsCloud;
		f[i].MemoriaRamLivre = "17 GB"; // MemoriaRamLivreApsCloud;
		f[i].EnderecoIpPublicoServidor = "179.180.145.146"; // EnderecoIpPublicoServidorApsCloud;
		f[i].PortaIpAberta = "???"; // PortaIpAbertaApsCloud;
		f[i].FrequenciaProcessamento = "15 min"; // FrequenciaProcessamentoApsCloud; 

		f[i].ResponsavelDeParasProdutos = "Uma vendedora";  

		f[i].EmailResponsavelTI = EmailResponsavelTIApsCloud;
		f[i].SkypeResponsavelTI = SkypeResponsavelTIApsCloud;
		f[i].PrenomeResponsavelTI = PrenomeResponsavelTIApsCloud; 
		f[i].FuncaoResponsavelTI = FuncaoResponsavelTIApsCloud;
		f[i].TelefoneResponsavelTI = TelefoneResponsavelTIApsCloud;
		
		f[i].EmailResponsavelTIAlternativo = EmailResponsavelTIAlternativoApsCloud;		
		f[i].SkypeResponsavelTIAlternativo = SkypeResponsavelTIAlternativoApsCloud;		
		f[i].PrenomeResponsavelTIAlternativo = PrenomeResponsavelTIAlternativoApsCloud;
		f[i].FuncaoResponsavelTIAlternativo = FuncaoResponsavelTIAlternativoApsCloud;
		
		f[i].EmailResponsavelTI_Nuvem = EmailResponsavelTI_NuvemApsCloud;
		f[i].SkypeResponsavelTI_Nuvem = SkypeResponsavelTI_NuvemApsCloud; 
		f[i].PrenomeResponsavelTI_Nuvem = PrenomeResponsavelTI_NuvemApsCloud;
		f[i].TelefoneResponsavelTI_Nuvem = TelefoneResponsavelTI_NuvemApsCloud;

		
/*  Template:
    =========
    !!!!!!!!! Não se esqueça incremementar o variável constante "qtdFornecedores" mais 1 !!!!!
    
		i++;
		f[i].IdFornecedor = ;
		f[i].NomeFantasiaEmpresa = "";
		f[i].cnpjFornecedor = "";
		f[i].versaoIntegrador = "";
		f[i].SiglaSistemaFornecedor = "";
		f[i].IsEmProducao = "Não";
		f[i].IsDebugAtivado = true;
		f[i].AplicativoDesktopRemoto = "AnyDesk";
		f[i].usuarioWebservice = "";
		f[i].tipoSO = ""; 
		f[i].dirProgramFiles = "";
		f[i].versaoJRE = "";
		f[i].tipoJRE = "(.. bit)";
		f[i].DiscoIntegrador = "C";
		f[i].EspacoLivreDisco = " GB";
		f[i].MemoriaRamLivre = "";
		f[i].EnderecoIpPublicoServidor = "";
		f[i].PortaIpAberta = ""; 
		f[i].FrequenciaProcessamento = "15 min";

		f[i].ResponsavelDeParasProdutos = "Uma vendedora";

		f[i].EmailResponsavelTI = "";
		f[i].SkypeResponsavelTI = "";
		f[i].PrenomeResponsavelTI = ""; 
		f[i].FuncaoResponsavelTI = "";
		f[i].TelefoneResponsavelTI = "";
		
		f[i].EmailResponsavelTIAlternativo = "";		
		f[i].SkypeResponsavelTIAlternativo = "";		
		f[i].PrenomeResponsavelTIAlternativo = "";
		f[i].FuncaoResponsavelTIAlternativo = "";
		
		f[i].EmailResponsavelTI_Nuvem = "";
		f[i].SkypeResponsavelTI_Nuvem = ""; 
		f[i].PrenomeResponsavelTI_Nuvem = ""; 
 */
		
		for (int j=0; j < (qtdFornecedores); j++) {
			hashMap.put(f[j].IdFornecedor, f[j]);
		}
		
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
			throw new Exception("Erro! O CNPJ da empresa fornecedora não foi informado no arquivo C:/Arquivos de Programas PC/Integração Fornecedor - Portal Cronos/" + Constants.DIR_ARQUIVOS_PROPERTIES + (!IntegracaoFornecedorCompleta.IsSistemaFornecedorNuvem ? Constants.NOME_ARQUIVO_PROPERTIES : IntegracaoFornecedorCompleta.nomeArquivoConfigNuvemAtual) + "!");
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
				throw new Exception("Erro! O CNPJ da empresa fornecedora \"" + cnpj + "\" no arquivo C:/Arquivos de Programas PC/Integração Fornecedor - Portal Cronos/" + Constants.DIR_ARQUIVOS_PROPERTIES + (!IntegracaoFornecedorCompleta.IsSistemaFornecedorNuvem ? Constants.NOME_ARQUIVO_PROPERTIES : IntegracaoFornecedorCompleta.nomeArquivoConfigNuvemAtual) + " está errado!");
			else
	            return f.IdFornecedor;
		}
	}

	
	
	public Fornecedor getFornecedor(Integer idFornecedor) throws Exception {		
		Fornecedor f = hashMap.get(idFornecedor);
		
		if (f == null)
			throw new Exception("Erro: idFornecedor " + idFornecedor.toString() + " não existe");

		return f;
	}

	private static void cargaTabelas() throws Exception
	{
		try {
	         factory = new Configuration().
	                   configure("hibernate.cfg.xml.Teste").
	                   addAnnotatedClass(ConfigMonitoradorIntegradores.class).
	                   buildSessionFactory();
	    } 
		catch (Throwable ex) 
		{ 
	         System.err.println("Failed to create sessionFactory object." + ex);
	         throw new ExceptionInInitializerError(ex); 
	    }
		
		Session session = factory.openSession();

		Utils.executarScriptEmConexaoHibernate(session, "Criar tabelas Instalador e Monitorador.sql");
		
		Transaction tx = null;
		
		try 
		{
			for (Entry<Integer, Fornecedor> entry : FornecedorRepositorio.hashMap.entrySet()) 
			{
				Object value = entry.getValue();
				Integer idFornecedor = ((Fornecedor) value).IdFornecedor;
				FornecedorRepositorio fRep = new FornecedorRepositorio();
				Fornecedor f = fRep.getFornecedor(idFornecedor);
				
				if (idFornecedor == null || idFornecedor < 0 || idFornecedor == 2016)
					continue;
				
				// Estouro de uma coluna dá erro "jdbc.SQLServerException: String or binary data would be truncated"
				// e nem Hibernate nem JDBC diz qual coluna é a causa e ainda menos qual valor é o problema.
				// Solução: usar o Hibernate Validator:
				ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		        Validator validator = factory.getValidator();
		 
				
		        tx = session.beginTransaction();
		        ConfigMonitoradorIntegradores confMon = new ConfigMonitoradorIntegradores();
		        confMon.IdFornecedor = idFornecedor;
		        confMon.PrenomeContatoTI = f.PrenomeResponsavelTI;
		        confMon.EmailContatoTI =f.EmailResponsavelTI;
		        confMon.SkypeContatoTI = f.SkypeResponsavelTI;
		        confMon.TelefoneContatoTI = f.TelefoneResponsavelTI;
			    confMon.FuncaoContatoTI = f.FuncaoResponsavelTI;
			    confMon.AplicativoDesktopRemoto = f.AplicativoDesktopRemoto;
			    confMon.IdAplicativoDesktopRemoto = f.IdAplicativoDesktopRemoto;
			    confMon.IsEmProducao = ( f.IsEmProducao.equals("Sim") ? true : false);
			    
		        confMon.PrenomeContatoTIsecundario = f.PrenomeResponsavelTIAlternativo;

		        Set<ConstraintViolation<ConfigMonitoradorIntegradores>> constraintViolations = validator.validate(confMon);
		        
		        if (constraintViolations.size() > 0) 
		        {
		            for (ConstraintViolation<ConfigMonitoradorIntegradores> violation : constraintViolations) 
		            {
		            	String prefix = "";
		            	String entidade = "";
		            	String atributo = "";
		            	String instanciaEntidade = "";
		            	String msg = violation.getMessage();
		            	
		            	if (Utils.isNullOrBlank(violation.getPropertyPath().toString()))
		            	{
		            		// Class-level constraint violation:
		            		entidade = "";          // A entidade já se encontra no EL na annotation 
		            		atributo = "";          // Não se aplica no nível de classe ( = entidade)
		            		instanciaEntidade = ""; // O IdFornecedor já se encontra no EL na annotation
			            	msg = msg.replace("pcronos.integracao.fornecedor.entidades.", "");

			              // if (msg.indexOf("@") > -1)
				          //	msg = msg.substring(0, msg.indexOf("@"));
		            	}
		            	else
		            	{
		            		// Field-level constraint violation:
		            		entidade = violation.getRootBeanClass().getSimpleName();
		            		atributo = "." + violation.getPropertyPath().toString();
		            		instanciaEntidade = Integer.toString(((ConfigMonitoradorIntegradores)(violation.getLeafBean())).getIdFornecedor());
		            		prefix = "IdFornecedor = " + instanciaEntidade + ": ";  
		            	}
		            	
		            	
		            	System.out.println(prefix + entidade + atributo + msg);
		                if (tx!=null) tx.rollback();
		            }
		            System.out.println("");
		        } 
		        else 
		        {
		            System.out.println("Valid Object");
		            session.save(confMon); 
		            tx.commit();
		        }
			} // loop sobre os fornecedores
			
			System.out.println("Carga concluída sem erros.");
	    } 
		
		// Usando o Entity Maneger de JPA:
//		catch (RollbackException e) {
//			Set<ConstraintViolation<?>> violations = ((ConstraintViolationException)e.getCause()).getConstraintViolations();
//			for (ConstraintViolation v : violations) {
//				System.out.println("InvalidValue = " +  v.getInvalidValue() + ", erro: " + v.getMessage());
//			}
//		}
		
		// Usando hibernate.validator.3.1.0.GA:  
//		catch (InvalidStateException vex) 
//		{
//			// Nunca entrou aqui. Talvez isso é por causa do uso dos mais modernos annotations ao invés de mapeamentos ORM via XML.....
//		
//	         if (tx!=null) tx.rollback();
//	         
//			   InvalidValue[] invalid = vex.getInvalidValues();
//			   for (int i=0; i<invalid.length; ++i) {
//			     InvalidValue bad = invalid[i];
//			     System.out.println("InvalidValue: " + bad.getPropertyPath() + ":" + bad.getPropertyName() + ":" + bad.getMessage());
//			   }
// 	    }
		
		catch (HibernateException hex) 
		{
			if (tx!=null) tx.rollback();
			
			 hex.printStackTrace(); 
	         System.out.println("Causa: " + hex.getCause());
	         
	         Throwable cause = hex.getCause();
	         if (cause instanceof SQLServerException) {
	             System.out.println("Causa: " + cause.getMessage());
	         }
	    } 
		finally 
		{
			Utils.executarScriptEmConexaoHibernate(session, "Drop tabelas Instalador e Monitorador.sql");
	        session.close(); 
	    }
		 
	}

	public static void main(String[] args) throws Exception
	{
		// Temporariamente executar o seguinte após qq alteração nos dados dos fornecedores, 
		// por enquanto que o Web Installer ainda não está pronto:
	   cargaTabelas();
	}

}
