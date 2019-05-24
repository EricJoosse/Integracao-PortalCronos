package pcronos.integracao.fornecedor;

import java.util.HashMap;
import java.util.Map.Entry;

public class FornecedorRepositorio {

	public static HashMap<Integer, Fornecedor> hashMap;

    static {
		hashMap = new HashMap<Integer, Fornecedor>(); 
		final int qtdFornecedores = 12;
		Fornecedor[] f = new Fornecedor[qtdFornecedores];

		for (int j=0; j < (qtdFornecedores); j++) {
			f[j] = new Fornecedor();			
		}
		
		int i = 0;
		
		f[i].IdFornecedor = 2016;
		f[i].NomeFantasiaEmpresa = "Teste Windows Server 2016";
		f[i].versaoIntegrador = "";
		f[i].SiglaSistemaFornecedor = "WinThor";
		f[i].EmailResponsavelTI = "";
		f[i].EmailResponsavelTIAlternativo = "";
		f[i].ApelidoResponsavelTI = "";
		f[i].ApelidoResponsavelTIAlternativo = "";
		f[i].AplicativoDesktopRemoto = "";
		f[i].usuarioWebservice = "";
		f[i].versaoJRE = "";
		f[i].tipoSO = "Windows Server 2016";
		f[i].dirProgramFiles = "Program Files";

		i++;
		f[i].IdFornecedor = null;
		f[i].NomeFantasiaEmpresa = "Monitoramento";
		f[i].versaoIntegrador = "3.0.0";
		f[i].SiglaSistemaFornecedor = "PCronos";
		f[i].EmailResponsavelTI = "";
		f[i].EmailResponsavelTIAlternativo = "";
		f[i].ApelidoResponsavelTI = "";
		f[i].ApelidoResponsavelTIAlternativo = "";
		f[i].AplicativoDesktopRemoto = "mstsc";
		f[i].versaoJRE = "jre1.8.0_92";
		f[i].tipoSO = "Windows Server 2008 R2 SP1";
		f[i].dirProgramFiles = "Program Files";
		f[i].MemoriaRamLivre = "1.4 - 1.9 GB";

		i++;
		f[i].IdFornecedor = 13;
		f[i].NomeFantasiaEmpresa = "Formaggio";
		f[i].versaoIntegrador = "2.6";
		f[i].SiglaSistemaFornecedor = "APS";
		f[i].EmailResponsavelTI = "projetosti@formaggio.com.br";  
		f[i].EmailResponsavelTIAlternativo = "ti@formaggio.com.br??????"; 
		f[i].SkypeResponsavelTI = "live:projetosti_1 = Geymison Lima - TI Formaggio";
		f[i].SkypeResponsavelTIAlternativo = "Braytner Vasconcelos";
		f[i].ApelidoResponsavelTI = "Geymison";
		f[i].ApelidoResponsavelTIAlternativo = "";
		f[i].FuncaoResponsavelTI = "Coordenador de Projetos de TI";
		f[i].FuncaoResponsavelTIAlternativo = "";
		f[i].AplicativoDesktopRemoto = "Team Viewer";
		f[i].usuarioWebservice = "ws-formaggio";
		f[i].versaoJRE = "jre1.8.0_92";
		f[i].tipoSO = "Windows Server 2012 R2";
		f[i].dirProgramFiles = "Program Files";
		f[i].cnpjFornecedor = "02870737000190";
		f[i].MemoriaRamLivre = "";

		i++;
		f[i].IdFornecedor = 947;
		f[i].NomeFantasiaEmpresa = "JR Distribui��o";
		f[i].versaoIntegrador = "2.8.1";
		f[i].SiglaSistemaFornecedor = "WinThor";
		f[i].EmailResponsavelTI = "jrembalagem.ti@gmail.com"; 
		f[i].EmailResponsavelTIAlternativo = "";
		f[i].SkypeResponsavelTI = "ivan barros";
		f[i].ApelidoResponsavelTI = "Ivan";
		f[i].ApelidoResponsavelTIAlternativo = "";
		f[i].AplicativoDesktopRemoto = "AnyDesk";
		f[i].IdAplicativoDesktopRemoto = "734228115";
		f[i].usuarioWebservice = "ws-jrembalagem";
		f[i].versaoJRE = "jre1.8.0_92";
		f[i].tipoSO = "Windows 10 Pro";
		f[i].SO32ou64bit = "64bit";
		f[i].dirProgramFiles = "Program Files";
		f[i].cnpjFornecedor = "00680755000265";
		f[i].MemoriaRamLivre = "";

		i++;
		f[i].IdFornecedor = 30;
		f[i].NomeFantasiaEmpresa = "Prolac";
		f[i].versaoIntegrador = "2.8.4";
		f[i].SiglaSistemaFornecedor = "WinThor";
		f[i].EmailResponsavelTI = "mscomprolac@gmail.com"; 
		f[i].EmailResponsavelTIAlternativo = "marcelo@casadoqueijo.net.br";
		f[i].SkypeResponsavelTI = "marcos.scognamiglio";
		f[i].SkypeResponsavelTIAlternativo = "Marcelo Bezerra";
		f[i].ApelidoResponsavelTI = "Marcos";
		f[i].ApelidoResponsavelTIAlternativo = "Marcelo";
		f[i].AplicativoDesktopRemoto = "AnyDesk";
		f[i].IdAplicativoDesktopRemoto = "685 885 292";
		f[i].usuarioWebservice = "ws-prolac";
		f[i].versaoJRE = "jre1.8.0_92";
		f[i].tipoSO = "Windows Server 2012 R2";
		f[i].dirProgramFiles = "Program Files";
		f[i].cnpjFornecedor = "07182763000140"; 
		f[i].MemoriaRamLivre = "";

		i++;
		f[i].IdFornecedor = 21;
		f[i].NomeFantasiaEmpresa = "Mar�timos";
		f[i].versaoIntegrador = "1.2.3";
		f[i].SiglaSistemaFornecedor = "WinThor";
		f[i].EmailResponsavelTI = "felipe.lolaia@maritimospescados.com.br"; 
		f[i].EmailResponsavelTIAlternativo = "";
		f[i].SkypeResponsavelTI = "Felipe Lolaia";
		f[i].ApelidoResponsavelTI = "Felipe";
		f[i].ApelidoResponsavelTIAlternativo = "";
		f[i].AplicativoDesktopRemoto = "Team Viewer";
		f[i].usuarioWebservice = "ws-mpescados";
		f[i].versaoJRE = "jre1.8.0_92";
		f[i].tipoSO = "?????????????????????";
		f[i].dirProgramFiles = "Program Files";
		f[i].cnpjFornecedor = "04666316000178";
		f[i].MemoriaRamLivre = "";

		i++;
		f[i].IdFornecedor = 170;
		f[i].NomeFantasiaEmpresa = "SOST";
		f[i].versaoIntegrador = "2.3.1";
		f[i].SiglaSistemaFornecedor = "WinThor";
		f[i].EmailResponsavelTI = "informatica@sost.com.br OU cleijonatassilva@sost.com.br"; 
		f[i].EmailResponsavelTIAlternativo = "informatica@sost.com.br OU carlossena@sost.com.br";
		f[i].SkypeResponsavelTI = "Cleijonatas S Silva";
		f[i].SkypeResponsavelTIAlternativo = "Sena Junior";
		f[i].ApelidoResponsavelTI = "Cleijonatas";
		f[i].ApelidoResponsavelTIAlternativo = "Sena";
		f[i].AplicativoDesktopRemoto = "AnyDesk";
		f[i].usuarioWebservice = "ws-sost";
		f[i].versaoJRE = "jre1.8.0_92";
		f[i].tipoSO = "Windows Server 2008 R2 SP1";
		f[i].dirProgramFiles = "Program Files";
		f[i].cnpjFornecedor = "07041307000180";
		f[i].MemoriaRamLivre = "";

		i++;
		f[i].IdFornecedor = 60;
		f[i].NomeFantasiaEmpresa = "Karne Keijo";
		f[i].versaoIntegrador = "2.4.1";
		f[i].SiglaSistemaFornecedor = "SAP";
		f[i].EmailResponsavelTI = "timons@kk.com.br";
		f[i].EmailResponsavelTIAlternativo = "";
		f[i].SkypeResponsavelTI = "Timon Dourado";
		f[i].ApelidoResponsavelTI = "Timon";
		f[i].ApelidoResponsavelTIAlternativo = "";
		f[i].AplicativoDesktopRemoto = "Team Viewer";
		f[i].usuarioWebservice = "ws-karnekeijo";
		f[i].versaoJRE = "jre1.8.0_92";
		f[i].tipoSO = "Windows Server 2008 R2 SP1";
		f[i].dirProgramFiles = "Program Files";
		f[i].cnpjFornecedor = "24150377000195";
		f[i].MemoriaRamLivre = "";

		i++;
		f[i].IdFornecedor = 33;
		f[i].NomeFantasiaEmpresa = "Comal";
		f[i].versaoIntegrador = "2.8.2";
		f[i].SiglaSistemaFornecedor = "WinThor";
		f[i].EmailResponsavelTI = "ti@comalcomercio.com.br";
		f[i].EmailResponsavelTIAlternativo = "";
		f[i].SkypeResponsavelTI = "Rildson Carlos";
		f[i].ApelidoResponsavelTI = "Rildson"; 
						// Tem vontade para ajudar, por�m � muito incompetente 
						// e muito burro, tem que explicar tudo detalhadamente
		f[i].ApelidoResponsavelTIAlternativo = "";
		f[i].AplicativoDesktopRemoto = "AnyDesk";
		f[i].usuarioWebservice = "ws-comal";
		f[i].versaoJRE = "jre1.8.0_92";
		f[i].tipoSO = "Windows Server 2016"; // Windows Server 2016 Standard
		f[i].dirProgramFiles = "Program Files";
		f[i].cnpjFornecedor = "07534303000133";
		f[i].MemoriaRamLivre = "";

		i++;
		f[i].IdFornecedor = 171;
		f[i].NomeFantasiaEmpresa = "Prop�o";
		f[i].versaoIntegrador = "2.6.1";
		f[i].SiglaSistemaFornecedor = "WinThor";
		f[i].EmailResponsavelTI = "ti@propao.com.br";
		f[i].EmailResponsavelTIAlternativo = "";
		f[i].SkypeResponsavelTI = "Apoio.propao@hotmail.com";
		f[i].ApelidoResponsavelTI = "Elthon"; 
						// Desenrolado, respons�vel para o cadastro de De-Para�s
		f[i].ApelidoResponsavelTIAlternativo = "";
		f[i].AplicativoDesktopRemoto = "AnyDesk";
		f[i].usuarioWebservice = "ws-propao";
		f[i].versaoJRE = "jre1.8.0_92";
		f[i].tipoSO = "Windows Server 2008 R2 SP1"; // Na verdade "Windows Server 2008 R2 Enterprise" sem nenhum service pack.........
		f[i].dirProgramFiles = "Program Files";
		f[i].cnpjFornecedor = "24407389000233";
		f[i].MemoriaRamLivre = "";

		i++;
		f[i].IdFornecedor = 14;
		f[i].NomeFantasiaEmpresa = "Padeir�o";
		f[i].versaoIntegrador = "2.8.3";
		f[i].SiglaSistemaFornecedor = "WinThor";
		f[i].EmailResponsavelTI = "tiagoautran@padeirao.com";
		f[i].EmailResponsavelTIAlternativo = "";
		f[i].SkypeResponsavelTI = "tiagoautran";
		f[i].ApelidoResponsavelTI = "Tiago"; 
		f[i].ApelidoResponsavelTIAlternativo = "";
		f[i].AplicativoDesktopRemoto = "AnyDesk";
		f[i].usuarioWebservice = "ws-padeirao";
		f[i].versaoJRE = "jre1.8.0_111";
		f[i].tipoSO = "Windows Server 2012 R2"; // Windows Server 2012 R2 Standard (sem nenhum service pack)
		f[i].dirProgramFiles = "Program Files";
		f[i].cnpjFornecedor = "03042263000151";
		f[i].MemoriaRamLivre = "";

		
		i++;
		f[i].IsServicoNuvem = true;
		f[i].IdFornecedor = 1995; // APS Cloud - primeiro fornecedor de muitos
		f[i].NomeFantasiaEmpresa = "Marizpan";   // APS Cloud - primeiro fornecedor de muitos
		f[i].cnpjFornecedor = "12286800000108";  // APS Cloud - primeiro fornecedor de muitos
		f[i].versaoIntegrador = "2.8.5";
		f[i].SiglaSistemaFornecedor = "APS";
		f[i].AplicativoDesktopRemoto = "AnyDesk";
		f[i].usuarioWebservice = "ws-marizpan";  // APS Cloud - primeiro fornecedor de muitos
		f[i].tipoSO = "Windows Server 2008 R2 SP1"; 
		f[i].dirProgramFiles = "Program Files (x86)";
		f[i].versaoJRE = "jre1.8.0_191";
		f[i].MemoriaRamLivre = "";
		f[i].ResponsavelDeParasProdutos = "Uma vendedora";  // APS Cloud - primeiro fornecedor de muitos

		f[i].EmailResponsavelTI = "saulo@apsinformatica.com.br";
		f[i].SkypeResponsavelTI = "Saulo Gomes de Lima (saulo_analista)";
		f[i].ApelidoResponsavelTI = "Saulo"; 
		f[i].FuncaoResponsavelTI = "Analista";
		
		f[i].EmailResponsavelTIAlternativo = "bernardino@apsinformatica.com.br";		
		f[i].SkypeResponsavelTIAlternativo = "Bernardino Borba (live:berna31pe)";		
		f[i].ApelidoResponsavelTIAlternativo = "Bernardino";
		f[i].FuncaoResponsavelTIAlternativo = "S�cio do APS";
		
		f[i].EmailResponsavelTI_Nuvem = "saulo@apsinformatica.com.br";
		f[i].SkypeResponsavelTI_Nuvem = "Saulo Gomes de Lima (saulo_analista)"; 
		f[i].ApelidoResponsavelTI_Nuvem = "Saulo"; 

		
/*  Template:
    =========
    !!!!!!!!! N�o se esque�a incremementar o vari�vel constante "qtdFornecedores" mais 1 !!!!!
    
		i++;
		f[i].IdFornecedor = ;
		f[i].NomeFantasiaEmpresa = "";
		f[i].cnpjFornecedor = "";
		f[i].versaoIntegrador = "";
		f[i].SiglaSistemaFornecedor = "";
		f[i].AplicativoDesktopRemoto = "AnyDesk";
		f[i].usuarioWebservice = "";
		f[i].tipoSO = ""; 
		f[i].dirProgramFiles = "";
		f[i].versaoJRE = "";
		f[i].MemoriaRamLivre = "";
		f[i].ResponsavelDeParasProdutos = "Uma vendedora";

		f[i].EmailResponsavelTI = "";
		f[i].SkypeResponsavelTI = "";
		f[i].ApelidoResponsavelTI = ""; 
		f[i].FuncaoResponsavelTI = "";
		
		f[i].EmailResponsavelTIAlternativo = "";		
		f[i].SkypeResponsavelTIAlternativo = "";		
		f[i].ApelidoResponsavelTIAlternativo = "";
		f[i].FuncaoResponsavelTIAlternativo = "";
		
		f[i].EmailResponsavelTI_Nuvem = "";
		f[i].SkypeResponsavelTI_Nuvem = ""; 
		f[i].ApelidoResponsavelTI_Nuvem = ""; 
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

	
	
	Fornecedor getFornecedor(Integer idFornecedor) throws Exception {		
		Fornecedor f = hashMap.get(idFornecedor);
		
		if (f == null)
			throw new Exception("Erro: idFornecedor " + idFornecedor.toString() + " n�o existe");

		return f;
	}

}
