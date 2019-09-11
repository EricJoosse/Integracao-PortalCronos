package pcronos.integracao.fornecedor;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;

import mslinks.ShellLink;
//import com.erigir.mslinks.*;
import pcronos.integracao.DefaultCharsetException;
import pcronos.integracao.EmailAutomatico;


public class AdicionadorFornecedorNuvem 
{
	private static boolean toDebugar = false;

	private static void editarArquivoConfig(String caminhoMaisNomeArquivo, String nmFornecedor) throws IOException, DefaultCharsetException
	{
		if (!Utils.getDefaultCharsetJVM().equals("windows-1252"))
		{
			throw new DefaultCharsetException(Utils.getDefaultCharsetJVM());
		}
		
     	String conteudoArquivo = new String(Files.readAllBytes(Paths.get(caminhoMaisNomeArquivo)), Charset.forName("windows-1252"));
     	
     	if (toDebugar) System.out.println("nmFornecedor = " + nmFornecedor);
     	if (toDebugar) System.out.println("conteudoArquivo.length() = " + conteudoArquivo.length());

     	conteudoArquivo = conteudoArquivo.replace("NomeEmpresa", nmFornecedor).replace("ws-empresa", "ws-" + nmFornecedor.toLowerCase());
     	
     	if (toDebugar) System.out.println("conteudoArquivo.length() = " + conteudoArquivo.length());
     	
     	int tamanhoArqCortado = conteudoArquivo.indexOf("######### Configurações E-mail automático");
     	
     	if (toDebugar) System.out.println("tamanhoArqCortado = " + tamanhoArqCortado);
     	
     	conteudoArquivo = conteudoArquivo.substring(0, tamanhoArqCortado);

     	if (toDebugar) System.out.println("conteudoArquivo = " + conteudoArquivo);

     	BufferedWriter bWriter = new BufferedWriter(new FileWriter(caminhoMaisNomeArquivo, false));
        bWriter.write(conteudoArquivo);
        bWriter.flush();
        bWriter.close();
	}

	
	public static void main(String[] args) throws IOException, Exception 
	{
		String nmFornecedor = args[0];

   	    IntegracaoFornecedorCompleta.Inicializar(Constants.DIR_ARQUIVOS_PROPERTIES + Constants.NOME_TEMPLATE_CLOUD_PROPERTIES);
		
		if (IntegracaoFornecedorCompleta.toEnviarEmailAutomatico)
		{
		    LocalDateTime horaInicio = LocalDateTime.now();
			String assunto = "Fornecedor novo " + nmFornecedor + " adicionado na integração APS Cloud / PCronos!";
			String body = "Favor incluir este fornecedor (por enquanto manualmente) no Monitorador: " + "\r\n"
						+ "veja o arquivo \"Passo a passo inclusão fornecedor nuvem no Monitorador.txt\" no projeto Eclipse." + "\r\n";
            EmailAutomatico.enviar(IntegracaoFornecedorCompleta.remetenteEmailAutomatico, IntegracaoFornecedorCompleta.destinoEmailAutomatico, IntegracaoFornecedorCompleta.ccEmailAutomatico, assunto, null, body, IntegracaoFornecedorCompleta.provedorEmailAutomatico, IntegracaoFornecedorCompleta.portaEmailAutomatico, IntegracaoFornecedorCompleta.usuarioEmailAutomatico, IntegracaoFornecedorCompleta.senhaCriptografadaEmailAutomatico, IntegracaoFornecedorCompleta.diretorioArquivosXmlSemBarraNoFinal, horaInicio, IntegracaoFornecedorCompleta.diretorioArquivosXml, "INI", null);
		}

        TarefaWindows tarefaWindows = new TarefaWindows(true, nmFornecedor, null);
		tarefaWindows.gravarEmArquivoXML();
		  
	    String atalho = "C:/Arquivos de Programas PC/AbrirConfigFornecedor.bat";
	    // O seguinte não funciona: Windows pergunta indesejavelmente com qual programa é para abrir "x.bat + parametro"............
	 // String atalho = "C:/Arquivos de Programas PC/AbrirConfigFornecedor.bat " + nmFornecedor;
	 // String atalho = "\"C:/Arquivos de Programas PC/AbrirConfigFornecedor.bat\" " + nmFornecedor;
	    String caminhoMaisNomeArquivo = "C:/Arquivos de Programas PC/Integração Fornecedor - Portal Cronos/conf/Integração APS - Portal Cronos." + nmFornecedor + ".properties";
	    
       	editarArquivoConfig(caminhoMaisNomeArquivo, nmFornecedor);

	    // Criar atalho no menu de Windows:
		String nomeAtalho = "C:/ProgramData/Microsoft/Windows/Start Menu/Programs/Portal Cronos/Integrador APS Cloud/Configurações " + nmFornecedor + ".lnk";
     //	ShellLink sLink = ShellLink.createLink(caminhoMaisNomeArquivo, nomeAtalho);
       	ShellLink sLink = ShellLink.createLink(atalho, nomeAtalho);
     //	sLink.setCMDArgs(nmFornecedor); // Não funcionou, nem com mslinks vatbub 1.0.5, nem com com.erigir.mslinks
       	
     //	sLink.setIconLocation("%SystemRoot%\\System32\\shell32.dll"); // Não funcionou
       	sLink.setIconLocation("C:\\Windows\\System32\\shell32.dll");
       	sLink.getHeader().setIconIndex(5); // não existe no vatbub
     //	sLink.setIconLocation("C:/Arquivos de Programas PC/Integração Fornecedor - Portal Cronos/res/ConfiguraçõesInstância.ico");
     //	sLink.getHeader().setIconIndex(0); // não existe no vatbub
       	  
		  
	}

}
