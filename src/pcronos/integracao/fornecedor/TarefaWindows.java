package pcronos.integracao.fornecedor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TarefaWindows {

	private String tarefa = null;
	
	public TarefaWindows(String nmFornecedor) throws Exception 
	{
		int minIni = 0; // 04 em todos os fornecedores até a versão 3.0.0
		                // 19 na versão 3.0.0
		int segIni = 0; // Não existe na versão 3.0.0 e versões mais antigas
		
		// A partir da versão 3.1.0 incrementar 1 minuto, pois muitas vezes o processamento demora menos de 1 minuto:
		File dirConfig = new File(Constants.DIR_ARQUIVOS_PROPERTIES); 
		int qtdFornecedores = 0;
		for (final File file : dirConfig.listFiles()) 
		{
			 if (    file.getName().startsWith("Integração APS - Portal Cronos.") 
		    	  && file.getName().endsWith(".properties") 
		    	  && file.getName().toLowerCase().indexOf("copy")  == -1 
		    	  && file.getName().toLowerCase().indexOf("cópia") == -1 
		    	  && file.getName().toLowerCase().indexOf("copia") == -1 
		    	  && file.getName().toLowerCase().indexOf("backup")  == -1 
		    	  && file.getName().toLowerCase().indexOf("bck") == -1 
		    	  && file.getName().toLowerCase().indexOf("template") == -1 
		    	)
		     {
				 qtdFornecedores += 1;
		     }
		}
		
		
		// Solução temporária e rápida feita na versão 3.0.0:
		
//		if (nmFornecedor.equals("Marizpan") || nmFornecedor.toLowerCase().equals("varig"))
//			minIni = minIni + 0;
//		else if (nmFornecedor.equals("Atacamax") || nmFornecedor.toLowerCase().equals("vasp"))
//			minIni = minIni + 7;
//		else 
//			minIni = minIni + 12;



		minIni = qtdFornecedores * 1;
		segIni = 0; 
		
		String strMinIni = Integer.toString(minIni);
		String strSegIni = Integer.toString(segIni); 

		if (minIni < 10)
			strMinIni = "0" + strMinIni;
		
		if (segIni < 10)
			strSegIni = "0" + strSegIni;
		
		
		this.tarefa = "" +
"<?xml version=\"1.0\" encoding=\"UTF-16\"?>" + "\r\n" + 
"<Task version=\"1.2\" xmlns=\"http://schemas.microsoft.com/windows/2004/02/mit/task\">" + "\r\n" + 
"  <RegistrationInfo>" + "\r\n" + 
"    <Date>2016-04-28T09:22:44.1862</Date><Author>system</Author>" + "\r\n" + 
"    " + "\r\n" + 
"  </RegistrationInfo>" + "\r\n" + 
"  <Triggers>" + "\r\n" + 
"    <CalendarTrigger>" + "\r\n" + 
"      <Repetition>" + "\r\n" + 
"        <Interval>PT15M</Interval>" + "\r\n" + 
"        <StopAtDurationEnd>false</StopAtDurationEnd>" + "\r\n" + 
"      </Repetition>" + "\r\n" + 
"      <StartBoundary>2016-04-28T18:" + strMinIni + ":" + strSegIni + ".7016603</StartBoundary>" + "\r\n" + 
"      <Enabled>true</Enabled>" + "\r\n" + 
"      <ScheduleByDay>" + "\r\n" + 
"        <DaysInterval>1</DaysInterval>" + "\r\n" + 
"      </ScheduleByDay>" + "\r\n" + 
"    </CalendarTrigger>" + "\r\n" + 
"  </Triggers>" + "\r\n" + 
"  <Principals>" + "\r\n" + 
"    <Principal id=\"Author\">" + "\r\n" + 
"      <UserId>system</UserId>" + "\r\n" + 
"      <LogonType>S4U</LogonType>" + "\r\n" + 
"      <RunLevel>HighestAvailable</RunLevel>" + "\r\n" + 
"    </Principal>" + "\r\n" + 
"  </Principals>" + "\r\n" + 
"  <Settings>" + "\r\n" + 
"    <MultipleInstancesPolicy>StopExisting</MultipleInstancesPolicy>" + "\r\n" + 
"    <DisallowStartIfOnBatteries>false</DisallowStartIfOnBatteries>" + "\r\n" + 
"    <StopIfGoingOnBatteries>false</StopIfGoingOnBatteries>" + "\r\n" + 
"    <AllowHardTerminate>false</AllowHardTerminate>" + "\r\n" + 
"    <StartWhenAvailable>true</StartWhenAvailable>" + "\r\n" + 
"    <RunOnlyIfNetworkAvailable>false</RunOnlyIfNetworkAvailable>" + "\r\n" + 
"    <IdleSettings>" + "\r\n" + 
"      <StopOnIdleEnd>true</StopOnIdleEnd>" + "\r\n" + 
"      <RestartOnIdle>false</RestartOnIdle>" + "\r\n" + 
"    </IdleSettings>" + "\r\n" + 
"    <AllowStartOnDemand>true</AllowStartOnDemand>" + "\r\n" + 
"    <Enabled>true</Enabled>" + "\r\n" + 
"    <Hidden>true</Hidden>" + "\r\n" + 
"    <RunOnlyIfIdle>false</RunOnlyIfIdle>" + "\r\n" + 
"    <WakeToRun>false</WakeToRun>" + "\r\n" + 
"    <ExecutionTimeLimit>PT0S</ExecutionTimeLimit>" + "\r\n" + 
"    <Priority>7</Priority>" + "\r\n" + 
"    <RestartOnFailure>" + "\r\n" + 
"      <Interval>PT1M</Interval>" + "\r\n" + 
"      <Count>3</Count>" + "\r\n" + 
"    </RestartOnFailure>" + "\r\n" + 
"  </Settings>" + "\r\n" + 
"  <Actions Context=\"Author\">" + "\r\n" + 
"    <Exec>" + "\r\n" + 
"      <Command>\"C:\\Arquivos de Programas PC\\Integração Fornecedor - Portal Cronos\\Job15a15minOfertamentoJava_Windows.bat\"</Command>" + "\r\n" + 
"      <Arguments>" + nmFornecedor + "</Arguments>" + "\r\n" + 
"    </Exec>" + "\r\n" + 
"  </Actions>" + "\r\n" + 
"</Task>";

	}

	public void gravarEmArquivoXML() throws IOException, Exception 
	{
		String nomeArquivoXML = "C:/Arquivos de Programas PC/FornecedorAdicionalNuvem.Windows2008_R2.TaskSchedule.xml";
		File f = new File(nomeArquivoXML);
		if(f.exists() && !f.isDirectory()) { 
		    f.delete();
		}
		
		BufferedWriter bWriter = new BufferedWriter(new FileWriter(nomeArquivoXML, false));
        bWriter.write(this.tarefa);
        bWriter.flush();
        bWriter.close();
	}

}
