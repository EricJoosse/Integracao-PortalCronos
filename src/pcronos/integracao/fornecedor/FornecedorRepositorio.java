package pcronos.integracao.fornecedor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;

import pcronos.integracao.fornecedor.interfaces.FornecedorInterface;
import pcronos.integracao.fornecedor.interfaces.SistemaIntegradoInterface;
import pcronos.integracao.fornecedor.interfaces.FornecedorOuSistemaIntegradoInterface;
import pcronos.integracao.fornecedor.entidades.ConfigInstaladorIntegrador;
import pcronos.integracao.fornecedor.entidades.ConfigInstaladorIntegradorNuvem;
import pcronos.integracao.fornecedor.entidades.ConfigMonitoradorIntegradores;
import pcronos.integracao.fornecedor.entidades.ConfigMonitoradorIntegradoresNuvem;
import pcronos.integracao.fornecedor.entidades.ContatoTiIntegrador;
import pcronos.integracao.fornecedor.entidades.ContatoTiIntegradorNuvem;
import pcronos.integracao.fornecedor.entidades.Pessoa;
import pcronos.integracao.fornecedor.entidades.SistemaIntegrado;
import pcronos.integracao.fornecedor.entidades.UsuarioSistema;

import com.microsoft.sqlserver.jdbc.SQLServerException;

import javax.persistence.NoResultException;

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
		final int qtdFornecedores = 16;
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
		f[i].IsEmProducao = "N�o";
		f[i].EmailResponsavelTI = "";
		f[i].EmailResponsavelTIAlternativo = "";
		f[i].PrenomeResponsavelTI = "";
		f[i].PrenomeResponsavelTIAlternativo = "";
		f[i].AplicativoDesktopRemoto = "";
		f[i].usuarioWebservice = "";
		f[i].versaoJRE = "";
		f[i].tipoSO = "Windows Server 2016";
		f[i].SO32ou64bit = "64bit";
		f[i].IdiomaSO = "";
		f[i].dirProgramFiles = "Program Files";
		f[i].QtdDiasArquivosXmlGuardados = 100;
		f[i].FrequenciaProcessamento = "15 min";

		i++;
		f[i].IdFornecedor = -1;
		f[i].NomeFantasiaEmpresa = "Monitorador Integradores";
		f[i].versaoIntegrador = "3.4.3";
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
		f[i].tipoSO = "Windows Server 2012 R2"; // Era "Windows Server 2008 R2 SP1";
		f[i].SO32ou64bit = "?";
		f[i].IdiomaSO = "";
		f[i].dirProgramFiles = "Program Files";
		f[i].DiscoIntegrador = "C";
		f[i].EspacoLivreDisco = "90 GB";
		f[i].QtdDiasArquivosXmlGuardados = 32;
		f[i].MemoriaRamLivre = "1.4 - 1.9 GB";
		f[i].EnderecoIpPublicoServidor = "N�o se aplica";
		f[i].PortaIpAberta = "N�o se aplica";
		f[i].FrequenciaProcessamento = "15 min";

		i++;
		f[i].IdFornecedor = -2;
		f[i].NomeFantasiaEmpresa = "Monitorador Banco";
		f[i].versaoIntegrador = "3.4.3";
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
		f[i].SO32ou64bit = "64bit";
		f[i].IdiomaSO = "";
		f[i].dirProgramFiles = "Program Files";
		f[i].DiscoIntegrador = "C";
		f[i].EspacoLivreDisco = "43 GB";
		f[i].QtdDiasArquivosXmlGuardados = 32;
		f[i].MemoriaRamLivre = "1.2 GB";
		f[i].EnderecoIpPublicoServidor = "N�o se aplica";
		f[i].PortaIpAberta = "N�o se aplica";
		f[i].FrequenciaProcessamento = "5 min";

		i++;
		f[i].IdFornecedor = -3;
		f[i].NomeFantasiaEmpresa = "Monitorador Banco de Conting�ncia";
		f[i].versaoIntegrador = "3.4.3";
		f[i].SiglaSistemaFornecedor = "PCronos";
		f[i].IsServicoNuvem = false;
		f[i].IsEmProducao = "N�o"; // Tem "exit" no in�cio do Job.bat
		f[i].IsDebugAtivado = false;
		f[i].EmailResponsavelTI = "";
		f[i].EmailResponsavelTIAlternativo = "";
		f[i].PrenomeResponsavelTI = "";
		f[i].PrenomeResponsavelTIAlternativo = "";
		f[i].AplicativoDesktopRemoto = "mstsc";
		f[i].versaoJRE = "jre1.8.0_92";
		f[i].tipoJRE = "(.. bit)";
		f[i].tipoSO = "Windows Server 2012 R2";
		f[i].SO32ou64bit = "64bit";
		f[i].IdiomaSO = "";
		f[i].dirProgramFiles = "Program Files";
		f[i].DiscoIntegrador = "C";
		f[i].EspacoLivreDisco = "43 GB";
		f[i].QtdDiasArquivosXmlGuardados = 32;
		f[i].MemoriaRamLivre = "1.2 GB";
		f[i].EnderecoIpPublicoServidor = "N�o se aplica";
		f[i].PortaIpAberta = "N�o se aplica";
		f[i].FrequenciaProcessamento = "5 min";

		i++;
		f[i].IdFornecedor = 13;
		f[i].NomeFantasiaEmpresa = "Formaggio";
		f[i].versaoIntegrador = "3.4.1";
		f[i].SiglaSistemaFornecedor = "APS";
		f[i].IsServicoNuvem = false;
		f[i].IsEmProducao = "N�o";
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
		f[i].AplicativoDesktopRemoto = "AnyDesk"; // 299 273 091
		f[i].usuarioWebservice = "ws-formaggio";
		f[i].versaoJRE = "jre1.8.0_161";
		f[i].tipoJRE = "(64 bit)"; // 32 bit est� instsalado tb e funciona tb, no Program Files (x86)
		f[i].tipoSO = "Windows Server 2012 R2";
		f[i].SO32ou64bit = "64bit";
		f[i].IdiomaSO = "";
		f[i].dirProgramFiles = "Program Files";
		f[i].cnpjFornecedor = "02870737000190";
		f[i].DiscoIntegrador = "C";
		f[i].EspacoLivreDisco = "4.8 GB";
		f[i].QtdDiasArquivosXmlGuardados = 100;
		f[i].MemoriaRamLivre = "1 GB";
		f[i].EnderecoIpPublicoServidor = "179.189.251.54"; // Era 187.113.120.97
		f[i].PortaIpAberta = "3389"; // era 80
		f[i].FrequenciaProcessamento = "15 min";
		f[i].DtCadastro = LocalDate.of(2017, 6, 27);

		i++;
		f[i].IdFornecedor = 947;
		f[i].NomeFantasiaEmpresa = "JR Distribui��o";
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
		f[i].IdiomaSO = "";
		f[i].dirProgramFiles = "Program Files";
		f[i].cnpjFornecedor = "00680755000265";
		f[i].DiscoIntegrador = "C";
		f[i].EspacoLivreDisco = "902 GB";
		f[i].QtdDiasArquivosXmlGuardados = 100;
		f[i].MemoriaRamLivre = "";
		f[i].EnderecoIpPublicoServidor = "jrolinda.dyndns.org";
		f[i].PortaIpAberta = "N�o precisa";
		f[i].FrequenciaProcessamento = "15 min";
		f[i].DtCadastro = LocalDate.of(2018, 10, 17);

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
		f[i].SO32ou64bit = "64bit";
		f[i].IdiomaSO = "";
		f[i].dirProgramFiles = "Program Files";
		f[i].cnpjFornecedor = "07182763000140"; 
		f[i].DiscoIntegrador = "C";
		f[i].EspacoLivreDisco = "269 GB";
		f[i].QtdDiasArquivosXmlGuardados = 100;
		f[i].MemoriaRamLivre = "6.5 GB";
		f[i].EnderecoIpPublicoServidor = "187.103.76.53";
		f[i].PortaIpAberta = "80";
		f[i].FrequenciaProcessamento = "15 min";
		f[i].DtCadastro = LocalDate.of(2019, 11, 27);

		i++;
		f[i].IdFornecedor = 21;
		f[i].NomeFantasiaEmpresa = "Mar�timos";
		f[i].versaoIntegrador = "1.2.3";
		f[i].SiglaSistemaFornecedor = "WinThor";
		f[i].IsServicoNuvem = false;
		f[i].IsEmProducao = "Na�";
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
		f[i].SO32ou64bit = "?";
		f[i].IdiomaSO = "";
		f[i].dirProgramFiles = "Program Files";
		f[i].cnpjFornecedor = "04666316000178";
		f[i].DiscoIntegrador = "C";
		f[i].EspacoLivreDisco = "48 GB";
		f[i].QtdDiasArquivosXmlGuardados = 100;
		f[i].MemoriaRamLivre = "";
		f[i].EnderecoIpPublicoServidor = "";
		f[i].PortaIpAberta = "";
		f[i].FrequenciaProcessamento = "15 min";
		f[i].DtCadastro = LocalDate.of(2017, 8, 8);

		i++;
		f[i].IdFornecedor = 170;
		f[i].NomeFantasiaEmpresa = "SOST";
		f[i].versaoIntegrador = "2.3.1";
		f[i].SiglaSistemaFornecedor = "WinThor";
		f[i].IsServicoNuvem = false;
		f[i].IsEmProducao = "N�o";
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
		f[i].SO32ou64bit = "?";
		f[i].IdiomaSO = "";
		f[i].dirProgramFiles = "Program Files";
		f[i].cnpjFornecedor = "07041307000180";
		f[i].DiscoIntegrador = "C";
		f[i].EspacoLivreDisco = "0.6 GB";
		f[i].QtdDiasArquivosXmlGuardados = 15;
		f[i].MemoriaRamLivre = "";
		f[i].EnderecoIpPublicoServidor = "";
		f[i].PortaIpAberta = "";
		f[i].FrequenciaProcessamento = "15 min";
		f[i].DtCadastro = LocalDate.of(2018, 3, 22);

		i++;
		f[i].IdFornecedor = 60;
		f[i].NomeFantasiaEmpresa = "Karne Keijo";
		f[i].versaoIntegrador = "2.4.1";
		f[i].SiglaSistemaFornecedor = "SAP";
		f[i].IsServicoNuvem = false;
		f[i].IsEmProducao = "N�o";
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
		f[i].SO32ou64bit = "?";
		f[i].IdiomaSO = "";
		f[i].dirProgramFiles = "Program Files";
		f[i].cnpjFornecedor = "24150377000195";
		f[i].DiscoIntegrador = "C";
		f[i].EspacoLivreDisco = "62 GB";
		f[i].QtdDiasArquivosXmlGuardados = 100;
		f[i].MemoriaRamLivre = "";
		f[i].EnderecoIpPublicoServidor = "";
		f[i].PortaIpAberta = "";
		f[i].FrequenciaProcessamento = "15 min";
		f[i].DtCadastro = LocalDate.of(2018, 5, 11);

		i++;
		f[i].IdFornecedor = 33;
		f[i].NomeFantasiaEmpresa = "Comal";
		f[i].versaoIntegrador = "2.8.2";
		f[i].SiglaSistemaFornecedor = "WinThor";
		f[i].IsServicoNuvem = false;
		f[i].IsEmProducao = "N�o";
		f[i].IsDebugAtivado = true;
		f[i].EmailResponsavelTI = "ti@comalcomercio.com.br";
		f[i].EmailResponsavelTIAlternativo = "";
		f[i].SkypeResponsavelTI = "Rildson Carlos";
		f[i].PrenomeResponsavelTI = "Rildson"; 
						// Tem vontade para ajudar, por�m � muito incompetente 
						// e muito burro, tem que explicar tudo detalhadamente
		f[i].PrenomeResponsavelTIAlternativo = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
		f[i].TelefoneResponsavelTI = "98609-4887";
		f[i].AplicativoDesktopRemoto = "AnyDesk";
		f[i].IdAplicativoDesktopRemoto = "usu�rio FusionDMS: 833 323 573, Administrador: 886 263 056";
// - Comal: 1. usu�rio FusionDMS:    este usu�rio fica em uma VM, por isso usu�rio FusionDMS 
//									 n�o aparece na lista de usu�rios se conectar com Administrador.....)
// 			2. usu�rio Administrador: !!!!!! INSTALADO COM ESTE USU�RIO!!!!!!!
		
		f[i].usuarioWebservice = "ws-comal";
		f[i].versaoJRE = "jre1.8.0_92";
		f[i].tipoJRE = "(.. bit)";
		f[i].tipoSO = "Windows Server 2016"; // Windows Server 2016 Standard
		f[i].SO32ou64bit = "64bit";
		f[i].IdiomaSO = "";
		f[i].dirProgramFiles = "Program Files";
		f[i].cnpjFornecedor = "07534303000133";
		f[i].DiscoIntegrador = "C";
		f[i].EspacoLivreDisco = "69 GB";
		f[i].MemoriaRamLivre = "";
		f[i].EnderecoIpPublicoServidor = "187.1.168.74";
		f[i].PortaIpAberta = "80";
		f[i].FrequenciaProcessamento = "15 min";
		f[i].DtCadastro = LocalDate.of(2018, 10, 17);
		f[i].EmailResponsavelDeParasProdutos = "";

		i++;
		f[i].IdFornecedor = 171;
		f[i].NomeFantasiaEmpresa = "Prop�o";
		f[i].versaoIntegrador = "3.4.2"; // No servidor anterior era "2.6.1"
		f[i].SiglaSistemaFornecedor = "WinThor";
		f[i].IsServicoNuvem = false;
		f[i].IsEmProducao = "Sim";
		f[i].IsDebugAtivado = true;
		f[i].EmailResponsavelTI = "ti@propao.com.br";
		f[i].EmailResponsavelTIAlternativo = "ti@propao.com.br";         //  O MESMO!!!!!!
		f[i].SkypeResponsavelTI = "Apoio.propao@hotmail.com";
		f[i].SkypeResponsavelTIAlternativo = "Apoio.propao@hotmail.com"; //  O MESMO!!!!!!
		f[i].PrenomeResponsavelTI = "Elthon"; 
						// Desenrolado, respons�vel para o cadastro de De-Para�s
		f[i].PrenomeResponsavelTIAlternativo = "Nicolas"; // Nicolas Florencio
		f[i].TelefoneResponsavelTI = "99535-1999";
		f[i].TelefoneResponsavelTIAlternativo = "99846-7777 ou 3727-9400";
		f[i].FuncaoResponsavelTI = "";
		f[i].FuncaoResponsavelTIAlternativo = "Supervisor de Ti";
		f[i].AplicativoDesktopRemoto = "AnyDesk";
		f[i].IdAplicativoDesktopRemoto = "928 767 259/propao/administrador"; //No servidor anterior era "382 127 135";
		f[i].usuarioWebservice = "ws-propao";
		f[i].versaoJRE = "jre1.8.0_261";
		f[i].tipoJRE = "(64 bit)";
		f[i].tipoSO = "Windows Server 2019"; // Na verdade "Windows Server 2019 Standard" // No servidor anterior era "Windows Server 2008 R2 SP1"; // Na verdade "Windows Server 2008 R2 Enterprise" sem nenhum service pack.........
		f[i].SO32ou64bit = "64bit";
		f[i].IdiomaSO = "PT";
		f[i].dirProgramFiles = "Program Files"; // Alias = "Programas" no servidor novo de 2019 PT // No servidor anterior era "Program Files";
		f[i].cnpjFornecedor = "24407389000233";
		f[i].DiscoIntegrador = "C";
		f[i].EspacoLivreDisco = "78 GB";  // No servidor anterior era "167 GB";
		f[i].MemoriaRamLivre = "3.8 GB"; // No servidor anterior era "210 - 240 MB"
		
		f[i].EnderecoIpPublicoServidor = "186.235.190.42"; // No servidor anterior era "187.113.65.138";
		f[i].PortaIpAberta = "5050"; // No servidor anterior era "N�o se aplica";
		// Observa��o: se tnsping d� erro "timeout period expired", o servidor est� desligado ou fora da Internet; 
		//             se tnsping d� erro "the computer refused the connection", o servidor est� funcionando normalmente.
		//             Isso foi testado com outro servidor que estava desligado (o o antigo servidor de teste que foi excluido do Azure).
		
		f[i].FrequenciaProcessamento = "15 min";
		f[i].DtCadastro = LocalDate.of(2018, 10, 17);

		i++;
		f[i].IdFornecedor = 14;
		f[i].NomeFantasiaEmpresa = "Padeir�o";
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
		f[i].SO32ou64bit = "64bit";
		f[i].IdiomaSO = "";
		f[i].dirProgramFiles = "Program Files";
		f[i].cnpjFornecedor = "03042263000151";
		f[i].DiscoIntegrador = "C";
		f[i].EspacoLivreDisco = "703 GB";
		f[i].MemoriaRamLivre = "";
		f[i].EnderecoIpPublicoServidor = "192.141.110.189";
		f[i].PortaIpAberta = "N�o se aplica";
		f[i].FrequenciaProcessamento = "15 min";
		f[i].DtCadastro = LocalDate.of(2018, 11, 29);

		
		
		
		String versaoIntegradorApsCloud = "3.4.0";
		String SiglaSistemaFornecedorApsCloud = "APS";
		String AplicativoDesktopRemotoApsCloud = "AnyDesk";
		String IdAplicativoDesktopRemotoApsCloud = "759304784 (era desktop-fvkrc84@ad)";
		String tipoSOApsCloud = "Windows Server 2008 R2 SP1"; 
		String SO32ou64bitApsCloud = "?"; 
		String IdiomaSOApsCloud = ""; 
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
		String FuncaoResponsavelTIApsCloud = "S�cio do APS";
		String TelefoneResponsavelTIApsCloud = "99780-1192";
		
		String EmailResponsavelTIAlternativoApsCloud = "saulo@apsinformatica.com.br";		
		String SkypeResponsavelTIAlternativoApsCloud = "Saulo Gomes de Lima (saulo_analista)";		
		String PrenomeResponsavelTIAlternativoApsCloud = "Saulo"; // Algu�m foi demitido, provavelmente foi ele
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
		f[i].SO32ou64bit = SO32ou64bitApsCloud;
		f[i].IdiomaSO = IdiomaSOApsCloud;
		f[i].dirProgramFiles = dirProgramFilesApsCloud;
		f[i].versaoJRE = versaoJREApsCloud;
		f[i].tipoJRE = tipoJREApsCloud;
		f[i].DiscoIntegrador = DiscoIntegradorApsCloud;
		f[i].EspacoLivreDisco = EspacoLivreDiscoApsCloud;
		f[i].MemoriaRamLivre = MemoriaRamLivreApsCloud;
		f[i].EnderecoIpPublicoServidor = EnderecoIpPublicoServidorApsCloud;
		f[i].PortaIpAberta = PortaIpAbertaApsCloud;
		f[i].FrequenciaProcessamento = FrequenciaProcessamentoApsCloud; 

		f[i].EmailResponsavelDeParasProdutos = "Uma vendedora";  // APS Cloud - primeiro fornecedor de muitos

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

		f[i].DtCadastro = LocalDate.of(2019, 5, 10);
		

		
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
		f[i].SO32ou64bit = "64bit"; // SO32ou64bitApsCloud;
		f[i].IdiomaSO = ""; // IdiomaSOApsCloud;
		f[i].dirProgramFiles = "Program Files (x86)"; // dirProgramFilesApsCloud;
		f[i].versaoJRE = "jre1.8.0_211"; // versaoJREApsCloud;
		f[i].tipoJRE = "(32 bit)";  // tipoJREApsCloud
		f[i].DiscoIntegrador = "C"; // DiscoIntegradorApsCloud;
		f[i].EspacoLivreDisco = "41 GB"; // EspacoLivreDiscoApsCloud;
		f[i].MemoriaRamLivre = "17 GB"; // MemoriaRamLivreApsCloud;
		f[i].EnderecoIpPublicoServidor = "179.180.145.146"; // EnderecoIpPublicoServidorApsCloud;
		f[i].PortaIpAberta = "???"; // PortaIpAbertaApsCloud;
		f[i].FrequenciaProcessamento = "15 min"; // FrequenciaProcessamentoApsCloud; 

		f[i].EmailResponsavelDeParasProdutos = "Uma vendedora";  

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

		f[i].DtCadastro = LocalDate.of(2019, 11, 11);
		

		
		i++;
		f[i].IdFornecedor = 23;
		f[i].NomeFantasiaEmpresa = "Ing� Vinhos";
		f[i].cnpjFornecedor = "05390477000225";
		f[i].versaoIntegrador = "3.4.3";
		f[i].SiglaSistemaFornecedor = "WinThor";
		f[i].IsServicoNuvem = false;
		f[i].IsEmProducao = "N�o";
		f[i].IsDebugAtivado = true;
		f[i].AplicativoDesktopRemoto = "AnyDesk"; // 411 964 317/legolas/administrador
		f[i].usuarioWebservice = "ws-inga";
		f[i].tipoSO = "Windows Server 2008 R2 SP1"; 
		f[i].SO32ou64bit = "64bit"; // "32bit" ou "64bit"
		f[i].IdiomaSO = "PT-BR - syntax????"; 
		f[i].dirProgramFiles = "Program Files";
		f[i].versaoJRE = "jre1.8.0_271";
		f[i].tipoJRE = "(64 bit)";
		f[i].DiscoIntegrador = "C";
		f[i].EspacoLivreDisco = "46 GB";
		f[i].QtdDiasArquivosXmlGuardados = 100;
		f[i].MemoriaRamLivre = "3.5 GB";
		f[i].EnderecoIpPublicoServidor = "170.239.108.202";
		f[i].PortaIpAberta = "N�o se aplica"; 
		f[i].FrequenciaProcessamento = "15 min";

		f[i].EmailResponsavelDeParasProdutos = "Uma vendedora";

		f[i].EmailResponsavelTI = "fabiano@ingavinhos.com.br";
		f[i].SkypeResponsavelTI = "FABIANO NAVARRO (live:fabiano_631)";
		f[i].PrenomeResponsavelTI = "Fabiano"; 
		f[i].FuncaoResponsavelTI = "";
		f[i].TelefoneResponsavelTI = "99117-6551";
		
		f[i].EmailResponsavelTIAlternativo = "";		
		f[i].SkypeResponsavelTIAlternativo = "";		
		f[i].PrenomeResponsavelTIAlternativo = "";
		f[i].FuncaoResponsavelTIAlternativo = "";
		
		f[i].EmailResponsavelTI_Nuvem = "";
		f[i].SkypeResponsavelTI_Nuvem = ""; 
		f[i].PrenomeResponsavelTI_Nuvem = ""; 

		f[i].DtCadastro = LocalDate.of(2021, 2, 8);

		
/*  Template:
    =========
    !!!!!!!!! N�o se esque�a incremementar o vari�vel constante "qtdFornecedores" mais 1 !!!!!
    
		i++;
		f[i].IdFornecedor = ;
		f[i].NomeFantasiaEmpresa = "";
		f[i].cnpjFornecedor = "";
		f[i].versaoIntegrador = "";
		f[i].SiglaSistemaFornecedor = ""; // "WinThor" ou "APS" ou "SAP" ou "PCronos"
		f[i].IsServicoNuvem = true/false;
		f[i].IsEmProducao = "N�o";
		f[i].IsDebugAtivado = true;
		f[i].AplicativoDesktopRemoto = "AnyDesk";
		f[i].IdAplicativoDesktopRemoto = "";
		f[i].usuarioWebservice = "";
		f[i].tipoSO = "";          // "Windows Server 2008 R2 SP1" ou "Windows Server 2012 R2" ou "Windows 10 Pro" ou "Windows Server 2016" ou "Windows Server 2019"
		f[i].SO32ou64bit = "";     // "32bit" ou "64bit"
		f[i].IdiomaSO = "";        // "I" = "Ingl�s", "P" = "Portugu�s"
		f[i].dirProgramFiles = ""; // "Program Files" ou "Program Files (x86)"
		f[i].versaoJRE = "";
		f[i].tipoJRE = "(.. bit)";
		f[i].DiscoIntegrador = "C";
		f[i].EspacoLivreDisco = " GB";
		f[i].QtdDiasArquivosXmlGuardados = ...;
		f[i].MemoriaRamLivre = "";
		f[i].EnderecoIpPublicoServidor = "";
		f[i].PortaIpAberta = ""; // Por exemplo "N�o se aplica"
		f[i].FrequenciaProcessamento = "15 min";

		f[i].EmailResponsavelDeParasProdutos = "Uma vendedora";

		f[i].EmailResponsavelTI = "";
		f[i].SkypeResponsavelTI = "...Display name... (live:......)"; // Veja o live-id nas propriedades do usu�rio no Skype
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

		f[i].DtCadastro = LocalDate.of(yyyy, m, d);  // Sem leading zeros
 */
		
		for (int j=0; j < (qtdFornecedores); j++) {
			hashMap.put(f[j].IdFornecedor, f[j]);
		}
		
    }
	
	public FornecedorRepositorio() {
	}
	

	// O m�todo getIdFornecedorByCnpj(String cnpj) nunca fica chamado se siglaSistema == "PCronos", 
	// ent�o n�o precisa retornar um objeto Integer (nullable). 
	// Observa��o: se um dia precisar trocar "int" por "Integer", tem que tomar providencias para  
	// continuar funcionando com valor -1, veja https://docs.oracle.com/javase/tutorial/java/nutsandbolts/datatypes.html
	int getIdFornecedorByCnpj(String cnpj) throws Exception 
	{
		if (cnpj == null || cnpj.equals("11222333444455"))
			throw new Exception("Erro! O CNPJ da empresa fornecedora n�o foi informado no arquivo C:/Arquivos de Programas PC/Integra��o Fornecedor - Portal Cronos/" + Constants.DIR_ARQUIVOS_PROPERTIES + (!IntegracaoFornecedorCompleta.IsSistemaFornecedorNuvem ? Constants.NOME_ARQUIVO_PROPERTIES : IntegracaoFornecedorCompleta.nomeArquivoConfigNuvemAtual) + "!");
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
				throw new Exception("Erro! O CNPJ da empresa fornecedora \"" + cnpj + "\" no arquivo C:/Arquivos de Programas PC/Integra��o Fornecedor - Portal Cronos/" + Constants.DIR_ARQUIVOS_PROPERTIES + (!IntegracaoFornecedorCompleta.IsSistemaFornecedorNuvem ? Constants.NOME_ARQUIVO_PROPERTIES : IntegracaoFornecedorCompleta.nomeArquivoConfigNuvemAtual) + " est� errado!");
			else
	            return f.IdFornecedor;
		}
	}

	
	
	public Fornecedor getFornecedor(Integer idFornecedor) throws Exception {		
		Fornecedor f = hashMap.get(idFornecedor);
		
		if (f == null)
			throw new Exception("Erro: idFornecedor " + idFornecedor.toString() + " n�o existe");

		return f;
	}

	
	private static <T extends FornecedorOuSistemaIntegradoInterface> int listarValidacoesEntidade(Transaction tx, Validator validator, T t1, T t2)
	{
		Set<ConstraintViolation<T>> constraintViolations = null;
        Set<ConstraintViolation<T>> constraintViolations1 = validator.validate(t1);
        
        if (t2 != null)
        {
            Set<ConstraintViolation<T>> constraintViolations2 = validator.validate(t2);
	        constraintViolations = constraintViolations1.stream() 
                                                        .collect(Collectors.toSet()); 
	        constraintViolations.addAll(constraintViolations2); 
	        
        }
        else
        	constraintViolations = constraintViolations1;
        
        
        for (ConstraintViolation<T> violation : constraintViolations) 
        {
        	String prefix = "";
        	String entidade = "";
        	String atributo = "";
        	String instanciaEntidade = "";
        	String msg = violation.getMessage();

        	
        	if (Utils.isNullOrBlank(violation.getPropertyPath().toString()))
        	{
        		// Class-level constraint violation:
        		entidade = "";          // A entidade j� se encontra no EL na annotation 
        		atributo = "";          // N�o se aplica no n�vel de classe ( = entidade)
        		instanciaEntidade = ""; // O IdFornecedor j� se encontra no EL na annotation
            	msg = msg.replace("pcronos.integracao.fornecedor.entidades.", "");

              // if (msg.indexOf("@") > -1)
	          //	msg = msg.substring(0, msg.indexOf("@"));
        	}
        	else
        	{
        		// Field-level constraint violation:
        		entidade = violation.getRootBeanClass().getSimpleName();
        		atributo = "." + violation.getPropertyPath().toString();
        		instanciaEntidade = Integer.toString(((T)(violation.getLeafBean())).getIdFornecedorOuSistemaIntegrado());
        		
        		if (t1 instanceof FornecedorInterface)
            	   prefix = "IdFornecedor = " + instanciaEntidade + ": ";  
        		else if (t1 instanceof SistemaIntegradoInterface)
         		   prefix = "IdSistema = " + instanciaEntidade + ": ";  
        	}
        	
        	
        	System.out.println(prefix + entidade + atributo + msg);
            if (tx!=null) tx.rollback();
        }
        System.out.println("");
        return constraintViolations.size();
	}
	

	
	private static void cargaTabelas() throws Exception
	{
		try {
	         factory = new Configuration().
	                   configure("hibernate.cfg.xml.Teste").
	                   addAnnotatedClass(ConfigMonitoradorIntegradores.class).
	                   addAnnotatedClass(ConfigMonitoradorIntegradoresNuvem.class).
	                   addAnnotatedClass(ConfigInstaladorIntegrador.class).
	                   addAnnotatedClass(ConfigInstaladorIntegradorNuvem.class).
	                   addAnnotatedClass(SistemaIntegrado.class).
	                   addAnnotatedClass(Pessoa.class).
	                   addAnnotatedClass(UsuarioSistema.class).
	                   addAnnotatedClass(ContatoTiIntegrador.class).
	                   addAnnotatedClass(ContatoTiIntegradorNuvem.class).
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
            Integer IdconTINuvem = null;
            Integer IdconTIsecundarioNuvem = null;
            Integer IdConfigInstaladorIntegradorNuvem = null;
            Integer IdConfigMonitoradorIntegradoresNuvem = null;

            for (Entry<Integer, Fornecedor> entry : FornecedorRepositorio.hashMap.entrySet()) 
			{
				Object value = entry.getValue();
				Integer idFornecedor = ((Fornecedor) value).IdFornecedor;
				FornecedorRepositorio fRep = new FornecedorRepositorio();
				Fornecedor f = fRep.getFornecedor(idFornecedor);
				
				if (idFornecedor == null || idFornecedor < 0 || idFornecedor == 2016)
					continue;
				
			    System.out.println("f.IdFornecedor = " + f.IdFornecedor);

			    // Estouro de uma coluna d� erro "jdbc.SQLServerException: String or binary data would be truncated"
				// e nem Hibernate nem JDBC diz qual coluna � a causa e ainda menos qual valor � o problema.
				// Solu��o: usar o Hibernate Validator:
				ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		        Validator validator = factory.getValidator();
		 
				
		        tx = session.beginTransaction();
		        
		        ContatoTiIntegrador conTI = new ContatoTiIntegrador();
		        ContatoTiIntegrador conTIsecundario = new ContatoTiIntegrador();
		        ContatoTiIntegradorNuvem conTINuvem = new ContatoTiIntegradorNuvem();
		        ContatoTiIntegradorNuvem conTIsecundarioNuvem = new ContatoTiIntegradorNuvem();
		        
		        ConfigInstaladorIntegrador confInst = new ConfigInstaladorIntegrador(); 
		        ConfigInstaladorIntegradorNuvem confInstNuvem = new ConfigInstaladorIntegradorNuvem();
		        
		        ConfigMonitoradorIntegradores confMon = new ConfigMonitoradorIntegradores();
		        ConfigMonitoradorIntegradoresNuvem confMonNuvem = new ConfigMonitoradorIntegradoresNuvem();
		        
		        conTI.IdFornecedor = idFornecedor;
		        conTI.nrSequenciaContato = 1;
		        conTI.PrenomeContatoTI = f.PrenomeResponsavelTI;
		        conTI.EmailContatoTI =f.EmailResponsavelTI;
		        conTI.SkypeContatoTI = f.SkypeResponsavelTI;
		        conTI.TelefoneContatoTI = f.TelefoneResponsavelTI;
			    conTI.FuncaoContatoTI = f.FuncaoResponsavelTI;
			    conTI.DtCadastro = f.DtCadastro;
			    conTI.IdUsuario = 14767; // login "eric"

			    if (!Utils.isNullOrBlank(f.PrenomeResponsavelTIAlternativo) || !Utils.isNullOrBlank(f.EmailResponsavelTIAlternativo))
			    {
				    conTIsecundario.IdFornecedor = idFornecedor;
				    conTIsecundario.nrSequenciaContato = 2;
			        conTIsecundario.PrenomeContatoTI = f.PrenomeResponsavelTIAlternativo;
			        conTIsecundario.EmailContatoTI = f.EmailResponsavelTIAlternativo;
			        conTIsecundario.SkypeContatoTI = f.SkypeResponsavelTIAlternativo;
			        conTIsecundario.TelefoneContatoTI = f.TelefoneResponsavelTIAlternativo;
			        conTIsecundario.FuncaoContatoTI = f.FuncaoResponsavelTIAlternativo;
				    conTIsecundario.DtCadastro = f.DtCadastro;
				    conTIsecundario.IdUsuario = 14767; // login "eric"
			    }

			    if (f.IsServicoNuvem && IdconTINuvem == null)
			    {
				    conTINuvem.EmailContatoTI = "yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy";
			        conTINuvem.nrSequenciaContato = 1;
				    conTINuvem.DtCadastro = f.DtCadastro;
				    conTINuvem.IdUsuario = 14767; // login "eric"
	
				    if (!Utils.isNullOrBlank(f.PrenomeResponsavelTIAlternativo) || !Utils.isNullOrBlank(f.EmailResponsavelTIAlternativo))
				    {
					    conTIsecundarioNuvem.nrSequenciaContato = 2;
					    conTIsecundarioNuvem.DtCadastro = f.DtCadastro;
					    conTIsecundarioNuvem.IdUsuario = 14767; // login "eric"
				    }
			    }

			    // Colunas valendo para ambos os ambientes Nuvem e N�o-Nuvem:
			    confInst.UsuarioWebservice = f.usuarioWebservice;
			    confInst.IsDebugAtivado = f.IsDebugAtivado;
			    
			    Query q = session.createQuery("from SistemaIntegrado where upper(SiglaSistemaIntegrado) = '" + f.SiglaSistemaFornecedor.toUpperCase() + "'");
			    SistemaIntegrado s = (SistemaIntegrado) (q.getSingleResult());
			    confInst.IdSistemaIntegrado = s.Id;
			    System.out.println("f.SiglaSistemaFornecedor = " + f.SiglaSistemaFornecedor);
			    System.out.println("confInst.IdSistemaIntegrado = " + confInst.IdSistemaIntegrado + "\r\n");
			    
		        confInst.IdFornecedor = idFornecedor;
		        confInst.DtCadastro = f.DtCadastro;
		        confInst.IdUsuario = 14767; // login "eric"
		        
		        confMon.IdFornecedor = idFornecedor;
		        
		        if (!Utils.isNullOrBlank(f.EmailResponsavelDeParasProdutos))
		        {
			        q = session.createQuery("select u from UsuarioSistema u inner join u.pessoa p where lower(p.Email) = '" + f.EmailResponsavelDeParasProdutos.toLowerCase() + "'");
			        if (q != null)
			        {
			        	try
			        	{
				        	UsuarioSistema u = (UsuarioSistema) (q.getSingleResult()); 
				        	confMon.IdVendedorResponsavel = u.Id;
						    System.out.println("f.EmailResponsavelDeParasProdutos = " + f.EmailResponsavelDeParasProdutos);
						    System.out.println("confMon.IdVendedorResponsavel = " + confMon.IdVendedorResponsavel + "\r\n");
			        	}
			        	catch (NoResultException nex)
			        	{ }
			        	
			        }
		        }
		        
			    confMon.IsEmProducao = ( f.IsEmProducao.equals("Sim") ? true : false);
			    confMon.DtCadastro = f.DtCadastro;
			    confMon.IdUsuario = 14767; // login "eric"
			    
			    if (f.IsServicoNuvem)
			    {
			        confInstNuvem.DtCadastro = f.DtCadastro;
			        confInstNuvem.IdUsuario = 14767; // login "eric"
				    confMonNuvem.DtCadastro = f.DtCadastro;
				    confMonNuvem.IdUsuario = 14767; // login "eric"
			    }


			    int qtdViolatons = 0;
		        
	        	qtdViolatons += listarValidacoesEntidade(tx, validator, conTI, conTIsecundario);
	        	if (f.IsServicoNuvem && IdconTINuvem == null) qtdViolatons += listarValidacoesEntidade(tx, validator, conTINuvem, conTIsecundarioNuvem);
	        	qtdViolatons += listarValidacoesEntidade(tx, validator, confInst, null);
	        	if (f.IsServicoNuvem && IdconTINuvem == null) qtdViolatons += listarValidacoesEntidade(tx, validator, confInstNuvem, null);	        	
	        	qtdViolatons += listarValidacoesEntidade(tx, validator, confMon, null);	        	
	        	if (f.IsServicoNuvem && IdconTINuvem == null) qtdViolatons += listarValidacoesEntidade(tx, validator, confMonNuvem, null);

	        	
	        	if (qtdViolatons == 0)
		        {
		            System.out.println("Valid Object");
		            
		            int IdContatoTiIntegrador = (int)session.save(conTI);
		            
		            Integer IdContatoTiSecundarioIntegrador = null;
		            if (!Utils.isNullOrBlank(f.PrenomeResponsavelTIAlternativo) || !Utils.isNullOrBlank(f.EmailResponsavelTIAlternativo))
		            {
		            	IdContatoTiSecundarioIntegrador = (int)session.save(conTIsecundario);
		            }

		            
		            if (f.IsServicoNuvem) 
		            {
		            	if (IdconTINuvem == null)
		            		IdconTINuvem = (int)session.save(conTINuvem);
		            	
		                confMonNuvem.IdContatoTiIntegrador = IdconTINuvem;
		            }


		            
		            if (f.IsServicoNuvem) 
		            {
		            	if (IdconTIsecundarioNuvem == null)
		            		IdconTIsecundarioNuvem = (int)session.save(conTIsecundarioNuvem);
		            	
		                confMonNuvem.IdContatoTiSecundarioIntegrador = (int)IdconTIsecundarioNuvem;
		            }
		            

		            
		            if (f.IsServicoNuvem) 
		            {
		            	if (IdConfigInstaladorIntegradorNuvem == null)
		            		IdConfigInstaladorIntegradorNuvem = (int) session.save(confInstNuvem);
		            	
		            	confInst.IdConfigInstaladorIntegradorNuvem = IdConfigInstaladorIntegradorNuvem;
		            	confInst.NrSequenciaInstanciaIntegradorNuvem = f.SequenciaInstanciaNuvem;
		            }
		            else 
		            {
		            	confInst.DirProgramfiles = f.dirProgramFiles;
		            	confInst.DiscoIntegrador = f.DiscoIntegrador;
		            	confInst.EnderecoIpPublicoServidor = f.EnderecoIpPublicoServidor;
		            	confInst.EspacoLivreDisco = f.EspacoLivreDisco;
		            	confInst.FrequenciaProcessamento = f.FrequenciaProcessamento;
		            	confInst.MemoriaRamLivre = f.MemoriaRamLivre;
		            	confInst.PortaIpAberta = f.PortaIpAberta;
		            	confInst.So32ou64bit = f.SO32ou64bit;
		            	confInst.TipoJRE = f.tipoJRE;
		            	confInst.TipoSO = f.tipoSO;
		            	confInst.VersaoIntegrador = f.versaoIntegrador;
		            	confInst.VersaoJRE = f.versaoJRE;
		            }
		            session.save(confInst);

		            
		            
		            if (f.IsServicoNuvem) 
		            {
		            	if (IdConfigMonitoradorIntegradoresNuvem == null)
		            	      IdConfigMonitoradorIntegradoresNuvem = (int) session.save(confMonNuvem);
		            	
		            	confMon.IdConfigMonitoradorIntegradoresNuvem = IdConfigMonitoradorIntegradoresNuvem;
		            }
		            else
		            {
					    confMon.IdContatoTiIntegrador = IdContatoTiIntegrador;
					    confMon.IdContatoTiSecundarioIntegrador = IdContatoTiSecundarioIntegrador;
				        confMon.AplicativoDesktopRemoto = f.AplicativoDesktopRemoto;
					    confMon.IdAplicativoDesktopRemoto = f.IdAplicativoDesktopRemoto;
		            }
		            session.save(confMon); 
		            
		            
		            tx.commit();
		        }
			} // loop sobre os fornecedores
			
			System.out.println("Carga conclu�da sem erros.");
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
//			// Nunca entrou aqui. Talvez isso � por causa do uso dos mais modernos annotations ao inv�s de mapeamentos ORM via XML.....
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
		// Temporariamente executar o seguinte ap�s qq altera��o nos dados dos fornecedores, 
		// por enquanto que o Web Installer ainda n�o est� pronto:
	   cargaTabelas();
	}

}
