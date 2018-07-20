package pcronos.integracao.fornecedor;

import java.awt.Window;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import mslinks.ShellLink;

public class ManualManutencao {

	private String nomeArquivo = null;
	private String nomeAtalho = null;
	private String conteudo;
	private Fornecedor fornecedor;
	private String caminhoManual = null;
	private String caminhoAtalhoManual = null;
	
	
	private void setCaminhoManualMaisCaminhoAtalho() throws Exception { 	
	    if (this.fornecedor.tipoSO.equals("Windows Server 2008 R2 SP1")) {
	    	this.caminhoManual = "C:/ProgramData/Microsoft/Windows/Start Menu/Programs/Portal Cronos/";
	    }
	    else if (this.fornecedor.tipoSO.equals("Windows Server 2012 R2")) {
	    	this.caminhoManual = "C:/Arquivos de Programas PC/";
		  	this.caminhoAtalhoManual = "C:/ProgramData/Microsoft/Windows/Start Menu/Programs/Portal Cronos/";
	    }
		else if (this.fornecedor.tipoSO.equals("Windows Server 2016") || this.fornecedor.tipoSO.equals("Windows 10 Pro")) {
		  	this.caminhoManual = "C:/Arquivos de Programas PC/";
		  	this.caminhoAtalhoManual = "C:/ProgramData/Microsoft/Windows/Start Menu/Programs/Portal Cronos/";
	    }
	    else
	    	throw new Exception("O sistema operacional \"" + this.fornecedor.tipoSO + "\" ainda está sem diretório padrão definido para o Manual de Manutenção para a TI.");
	}


	private void setNomeArquivoMaisAtalho() throws Exception { 	
		this.nomeArquivo = "Manual solucionamento paradas da integração Portal Cronos - v1.4.3 (12.07.2018).txt";
		this.nomeAtalho = "Manual Manutenção.lnk";

//		if (this.fornecedor.tipoSO.equals("Windows Server 2008 R2 SP1")) {
//			this.nomeArquivo = "Manual solucionamento paradas da integração Portal Cronos - v1.4.3 (12.07.2018).txt";
//	    }
//	    else if (this.fornecedor.tipoSO.equals("Windows Server 2012 R2")) {
//			this.nomeArquivo = "Portal Cronos - Manual solucionamento paradas da integração - v1.4.3 (12.07.2018).txt";
//	    }
//	    else
//	    	throw new Exception("O sistema operacional \"" + this.fornecedor.tipoSO + "\" ainda está sem nome definido para o arquivo do Manual de Manutenção para a TI.");
	}


	public void gravarEmArquivoNoMenuWindows() throws IOException, Exception {
		setCaminhoManualMaisCaminhoAtalho();
		
    	File diretorioManual = new File(caminhoManual);
    	if(!diretorioManual.exists()) { 
    		diretorioManual.mkdir();
    	}
    	else {
    		// Excluir eventuais manuais antigos:
    		for (final File file : diretorioManual.listFiles()) 
    		{
    			if (file.getName().startsWith("Manual") && file.getName().endsWith(".txt"))
   			       file.delete();
    		}
    	}

    	
    	BufferedWriter bWriter = new BufferedWriter(new FileWriter(caminhoManual + nomeArquivo, false));
        bWriter.write(this.conteudo);
        bWriter.flush();
        bWriter.close();
        
        
        
        if (     this.fornecedor.tipoSO.equals("Windows Server 2016") 
              || this.fornecedor.tipoSO.equals("Windows 10 Pro")
              || this.fornecedor.tipoSO.equals("Windows Server 2012 R2")
            ) {
        	
        	File diretorioAtalhoManual = new File(caminhoAtalhoManual);
        	if(!diretorioAtalhoManual.exists()) { 
        		diretorioAtalhoManual.mkdir();
        	}
        	else {
        		// Excluir eventuais atalhos antigos:
        		for (final File file : diretorioManual.listFiles()) 
        		{
        			if (file.getName().startsWith("Manual") && file.getName().endsWith(".lnk"))
       			       file.delete();
        		}
        	}

        	ShellLink.createLink(caminhoManual + nomeArquivo, caminhoAtalhoManual + nomeAtalho);
        }
	}
	
	
	
	public void gravarEmArquivoSoltoNoRaizDoProjeto() throws IOException, Exception {
		File f = new File(nomeArquivo);
		if(f.exists() && !f.isDirectory()) { 
		    f.delete();
		}
		
		BufferedWriter bWriter = new BufferedWriter(new FileWriter(nomeArquivo, false));
        bWriter.write(this.conteudo);
        bWriter.flush();
        bWriter.close();
	}
	
	
	
	public void removerPCronosDoMenuWindows() throws Exception { 
        setCaminhoManualMaisCaminhoAtalho();
		
        if (       this.fornecedor.tipoSO.equals("Windows Server 2016") 
        		|| this.fornecedor.tipoSO.equals("Windows 10 Pro")
                || this.fornecedor.tipoSO.equals("Windows Server 2012 R2")
           ) {
        	File diretorioAtalhoManual = new File(caminhoAtalhoManual);
        	
        	if(diretorioAtalhoManual.exists()) { 
        		boolean temOutrosAtalhos = false;
        		
        		for (final File shortcut : diretorioAtalhoManual.listFiles()) 
        		{
        			if (shortcut.getName().startsWith("Manual") && shortcut.getName().endsWith(".lnk"))
        				shortcut.delete();
        			else
        			   temOutrosAtalhos = true;
        		}
        		if (!temOutrosAtalhos) diretorioAtalhoManual.delete();
        	}
        }


        
        File diretorioManual = new File(caminhoManual);
    	
    	if(diretorioManual.exists()) { 
    		boolean temOutrosArquivos = false;
    		
    		for (final File file : diretorioManual.listFiles()) 
    		{
    			if (file.getName().startsWith("Manual") && file.getName().endsWith(".txt"))
  			       file.delete();
    			else
    			   temOutrosArquivos = true;
    		}
    		if (!temOutrosArquivos) diretorioManual.delete();
    	}
	}
	
	
	
	public ManualManutencao(Fornecedor f) throws Exception {
		this.fornecedor = f;
		setNomeArquivoMaisAtalho();
		

        this.conteudo = "" +
"Introdução técnica:" + "\r\n" +
"===================" + "\r\n" +
"O \"serviço\" de integração é um \"serviço\" de Java portanto é independente de Windows," + "\r\n" + 
"então não se encontra na lista de Serviços de Windows, nem aparece no \"Gerenciador de Tarefas\" (\"Task Manager\")" + "\r\n" + 
"de Windows, e também nunca aparece no \"Visualizador de Eventos\" (\"Event Viewer\") de Windows. " + "\r\n" +
"Nunca precisa reiniciar, parar ou iniciar este \"serviço\" e nem existem estas opções. " + "\r\n" +
"Reiniciamento é coisa de Windows, não tem nada a ver com Java. " + "\r\n" +
"Ao ligar ou reiniciar o servidor, o serviço starta automaticamente. " + "\r\n" +
"Apenas existe uma opção para desinstalação do serviço que será disponibilizada no menu de Windows" + "\r\n" + 
"em uma das próximas versões do serviço. " + "\r\n" +
" " + "\r\n" +
"Normalmente o serviço executa de 15 a 15 minutos." + "\r\n" + 
"Se a integração parou de ofertar automaticamente:  " + "\r\n" +
"  1. Se precisar, veja, opcionalmente, abaixo capítulo \"A\" com a lista de possíveis causas;" + "\r\n" + 
"  2. Em todos os casos, após a solução, veja no capítulo \"B\" como verificar se o serviço " + "\r\n" +
"     realmente voltou a funcionar." + "\r\n" +
"      " + "\r\n" +
"Favor NÃO SIMPLESMENTE REINICIAR O SERVIDOR, porém identificar a causa para podermos evitar repetição no futuro." + "\r\n" + 
"" + "\r\n" +
"Observação: este manual contem apenas a parte técnica da integração. Se precisar, o objetivo e os conceitos " + "\r\n" + 
"            podem ser explicados pelo gerente de vendas ou por Leão do Portal Cronos." + "\r\n" +
"            " + "\r\n" +
"" + "\r\n" +
"A. Possíveis causas de paradas da integração " + f.SiglaSistemaFornecedor + "/PCronos:" + "\r\n" +
"=============================================================" + "\r\n" +  
"  - O servidor da " + f.NomeFantasiaEmpresa + " que está hospedando este serviço está desligado?" + "\r\n" +
"" + "\r\n" +
"  - O disco rígido (\"HD\") C:\\ está cheio?" + "\r\n" +
"" + "\r\n" +
"  - Houve uma atualização de Windows?" + "\r\n" +
"    Isso pode acabar com o serviço. Neste caso, entrar em contato com \"Eric Jo\" via Skype (ou com eric.jo@bol.com.br via email)." + "\r\n" +
"" + "\r\n" +
"  - Houve uma atualização de Java?" + "\r\n" +
"    Isso acaba com o serviço com certeza. Neste caso, entrar em contato com \"Eric Jo\" via Skype (ou com eric.jo@bol.com.br via email)." + "\r\n" +
"" + "\r\n" +
"  - A memória RAM está 100 % cheia ou perto disso?" + "\r\n" +
"    No caso que a memória RAM estiver mais de 95 %, favor deixar assim por enquanto " + "\r\n" +
"    e não fazer nada ainda e avisar o contato \"Eric Jo\" via Skype (ou eric.jo@bol.com.br via email) " + "\r\n" +
"    primeiro para conectar via " + f.AplicativoDesktopRemoto + " para testar uma solução automática para este problema.  " + "\r\n" +
"    (Isso é necessário apenas na primeira vez que isso acontece.) " + "\r\n" +
"" + "\r\n" +
"  - Algum anti-virus está travando a máquina? (100 % memória RAM)" + "\r\n" +
"" + "\r\n";
		
		
if (f.SiglaSistemaFornecedor.equals("SAP"))		
	this.conteudo += "" +
"  - O endereço IP, usuário ou senha do SAP_API mudou? " + "\r\n" + 
"    Neste caso verifica se a mesma foi atualizada também no seguinte arquivo de configuração: " + "\r\n" +
"    C:\\Arquivos de Programas PC\\Integração Fornecedor - Portal Cronos\\conf\\SAP_API.jcoDestination" + "\r\n" +
"" + "\r\n";

	
this.conteudo += "" +
"  - O endereço IP, usuário ou senha da base de dados " + f.getTipoBaseDeDados() + " mudou?" + "\r\n" +
"" + "\r\n" +
"  - A senha do usuário " + f.usuarioWebservice + " do site do Portal Cronos foi alterada no site? " + "\r\n" + 
"    Neste caso verifica se a mesma foi atualizada também no seguinte arquivo de configuração: " + "\r\n" +
"    C:\\Arquivos de Programas PC\\Integração Fornecedor - Portal Cronos\\conf\\Integração Fornecedor - Portal Cronos.properties" + "\r\n" + 
"" + "\r\n" +
"  - No \"Gerenciador de Tarefas\" (\"Task Manager\") ordenar por nome do processo, e procurar" + "\r\n" + 
"    \"Java(TM) Platform SE binary\". " + "\r\n" +
"    Se não tiver nenhum processo com este nome, tudo está certo." + "\r\n" +   
"    Se tiver um ou mais processos na lista, verifica com o botão á direita do mouse," + "\r\n" + 
"    na aba \"Geral\" se o \"Local\" de cada processo for igual a \"C:/" + f.dirProgramFiles + "/Java/" + f.versaoJRE + "/bin/java.exe\"." + "\r\n" + 
"    Se não tiver nenhum processo com este \"Local\", tudo está certo.   " + "\r\n" +
"    Se tiver um processo com este \"Local\" enquanto que as ofertas automáticas estão paradas," + "\r\n" + 
"    este processo está travado. Neste caso, com o botão a direita do mouse clicar em \"Finalizar tarefa\".  " + "\r\n" +
"    Se tiver um processo com este \"Local\" enquanto que as ofertas automáticas estão processando " + "\r\n" +
"    normalmente, tudo está certo e não mexe com este processo.   " + "\r\n" +
"        " + "\r\n" +
"  - Outras ideias suas (Firewall, proxy, portas, Internet, etc)." + "\r\n" + 
"" + "\r\n" +
"  - No último caso, se não conseguir resolver, " + "\r\n" +
"    favor entrar em contato com o contato \"Eric Jo\" via Skype (ou com eric.jo@bol.com.br via email)," + "\r\n" + 
"    e colocar o " + f.AplicativoDesktopRemoto + " no ar, e informar o ID e a senha via Skype, " + "\r\n" +
"    para ele ver se a causa foi alguma falha dentro do serviço das ofertas automáticas." + "\r\n" +
"" + "\r\n" +
"" + "\r\n" +
"B. Como verificar se o serviço realmente voltou a funcionar:" + "\r\n" +
"============================================================" + "\r\n" +
"No caso que você mesmo consegue resolver o problema: " + "\r\n" +
"  1. Favor verificar após 15 minutos se o serviço atualizou o arquivo" + "\r\n" + 
"     \"C:/ProgramData/PortalCronos/Logs/Local/TemposExecução.log\" no final do arquivo." + "\r\n" +  
"      " + "\r\n" +
"  2. Em seguida: " + "\r\n" +
"     (i).  No caso de sucesso, favor verificar também se as cotações pendentes foram ofertadas automaticamente." + "\r\n" + 
"           Se você não tiver um usuário/senha no site do Portal Cronos, favor verificar isso com os vendedores. " + "\r\n" +
"     (ii). No caso que as ofertas automáticas ainda não voltaram a funcionar, " + "\r\n" +
"           veja as outras possíveis causas neste manual. " + "\r\n" +
"  " + "\r\n" +
"  " + "\r\n" +
"C. Outras dicas:" + "\r\n" +
"================" + "\r\n" +
"- O servidor de hospedagem do serviço das ofertas automáticas" + "\r\n" + 
"  é o servidor no qual existe um diretório C:\\Arquivos de Programas PC\\ " + "\r\n" +
"             " + "\r\n" +
"" + "\r\n";
	}
	
	
}
