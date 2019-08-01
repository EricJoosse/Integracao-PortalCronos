cls
@echo off

REM Windows, por default, usa o charset WINDOWS-1252 (ou outra variação, dependendo da lingua), 
REM que é uma extensão do ISO-8859-1. 
REM Mesmo assim, não se compara com UTF-8! O Linux, por default, usa o charset UTF-8. 
REM Eclipse herda o charset de Windows por default. 
REM "chcp 1252>nul" é necessário para evitar que DOS não reconhece acentos Portugueses no caminho 
REM "Integração Fornecedor - Portal Cronos" em alguns ou talvez até em todos os servidores:

chcp 1252>nul

cd\
cd "Arquivos de Programas PC"
call "Integração Fornecedor - Portal Cronos\bin\VerificarUsuarioAdministrador.bat"

echo.
echo.
echo Tipos de Windows homologados: 
echo.
echo 1 = Windows Server 2008 R2 SP1
echo 2 = Windows Server 2012 R2
echo 3 = Windows Server 2016
echo.

SET /P idOsVersion=Favor digitar o ID do tipo de Windows: 
IF "%idOsVersion%"=="" GOTO ErroTipoWin
GOTO PularErroTipoWin
:ErroTipoWin
echo MSGBOX "Erro: ID do tipo de Windows não informado! Instalação abortada!!" > %temp%\TEMPmessage.vbs
call %temp%\TEMPmessage.vbs
del %temp%\TEMPmessage.vbs /f /q
REM Fechar o script chamador também: 
exit
:PularErroTipoWin


echo.
echo.
echo Tipo de instalação: 
echo.
echo 1 = Hospedagem Local
echo 2 = Nuvem
echo.

SET /P tipoInstalacao=Favor digitar o ID do tipo de instalação: 
IF "%tipoInstalacao%"=="" GOTO ErroTipoInst
GOTO PularErroTipoInst
:ErroTipoInst
echo MSGBOX "Erro: ID do tipo de instalação não informado! Instalação abortada!!" > %temp%\TEMPmessage.vbs
call %temp%\TEMPmessage.vbs
del %temp%\TEMPmessage.vbs /f /q
REM Fechar o script chamador também: 
exit
:PularErroTipoInst



REM set osVersion=Windows_Server_2016
REM set osVersion=Windows_Server_2012_R2
REM set osVersion=Windows_Server_2012
REM set osVersion=Windows_Server_2008_R2_SP1
REM set osVersion=Windows_Server_2008
REM set osVersion=Windows_Server_2003_R2
REM set osVersion=Windows_Server_2003
REM set osVersion=Windows_2000
REM set osVersion=Windows_NT_40
REM set osVersion=Windows_NT_351
REM set osVersion=Windows_NT_35
REM set osVersion=Windows_NT_31
REM set osVersion=Windows_7_Professional_SP1
REM set osVersion=Windows_8_Pro
REM set osVersion=Windows_10_Pro
  
  
if "%idOsVersion%"=="1" (
    set osVersion=Windows_Server_2008_R2_SP1
) else if "%idOsVersion%"=="2" (
    set osVersion=Windows_Server_2012_R2
) else if "%idOsVersion%"=="3" (
    set osVersion=Windows_Server_2016
) else (
    echo MSGBOX "Erro: ID do tipo de Windows inválido! Instalação abortada!!" > %temp%\TEMPmessage.vbs
    call %temp%\TEMPmessage.vbs
    del %temp%\TEMPmessage.vbs /f /q
    REM Fechar o script chamador também: 
    exit
)

  
REM echo MSGBOX "Instalando..." > %temp%\TEMPmessage.vbs
REM call %temp%\TEMPmessage.vbs
REM del %temp%\TEMPmessage.vbs /f /q

echo.
echo          Instalando...................
echo.


if %osVersion% == Windows_Server_2016 (
    set /A tipoOS=64
) else if %osVersion% == Windows_Server_2012_R2 (
    set /A tipoOS=64
) else if %osVersion% == Windows_Server_2012 (
    set /A tipoOS=64
) else if %osVersion% == Windows_NT_40 (
    set /A tipoOS=32
) else if %osVersion% == Windows_NT_351 (
    set /A tipoOS=32
) else if %osVersion% == Windows_NT_35 (
    set /A tipoOS=32
) else if %osVersion% == Windows_NT_31 (
    set /A tipoOS=32
) else if %osVersion% == Windows_10_Pro (
REM set /A tipoOS=32
    set /A tipoOS=64
) else (
REM set /A tipoOS=32
    set /A tipoOS=64
)


set Windows_10_Pro_64bit=0
if %osVersion% == Windows_10_Pro (
   if %tipoOS% == 64 (
      set Windows_10_Pro_64bit=1
   )
)


    set drive=C:
REM set drive=D:



REM goto SKIP_JRE
REM goto SKIP_JRE_TEMPDIR
REM goto SKIP_JRE_TEMPDIR_PROGRAMDIR
REM goto FIM

REM !!!!!!!!!!!!!!! CUIDADO PARA NÃO CORROMPER O REGEDIT !!!!!!!!!
REM !!!!!!!!!!!!!!! CUIDADO PARA NÃO CORROMPER O REGEDIT !!!!!!!!!
REM !!!!!!!!!!!!!!! CUIDADO PARA NÃO CORROMPER O REGEDIT !!!!!!!!!
REM !!!!!!!!!!!!!!! CUIDADO PARA NÃO CORROMPER O REGEDIT !!!!!!!!!
REM !!!!!!!!!!!!!!! CUIDADO PARA NÃO CORROMPER O REGEDIT !!!!!!!!!
REM !!!!!!!!!!!!!!! CUIDADO PARA NÃO CORROMPER O REGEDIT !!!!!!!!!
REM !!!!!!!!!!!!!!! CUIDADO PARA NÃO CORROMPER O REGEDIT !!!!!!!!!
REM !!!!!!!!!!!!!!! CUIDADO PARA NÃO CORROMPER O REGEDIT !!!!!!!!!




REM Falta testar o seguinte!!!!!!!!!!!!!!!!!!!!!!!!
REM Se é para usar %~dp0DeshabilitarJavaUpdates.x64.reg ou DeshabilitarJavaUpdates.x64.reg
REM no seguinte caso de if Windows_10_Pro_64bit == 1:
REM ) else if %Windows_10_Pro_64bit% == 1 (
REM     set arquivoRegedit=DeshabilitarJavaUpdates.x64.reg


cd\
cd "Arquivos de Programas PC"

if %osVersion% == Windows_Server_2008_R2_SP1 (
    set arquivoRegedit="%drive%\\Arquivos de Programas PC\\DeshabilitarJavaUpdates.x64.reg"
) else if %osVersion% == Windows_Server_2012_R2 (
    set arquivoRegedit="%~dp0DeshabilitarJavaUpdates.x64.reg"
) else if %osVersion% == Windows_Server_2016 (
    set arquivoRegedit="%~dp0DeshabilitarJavaUpdates.x64.reg"
) else if %Windows_10_Pro_64bit% == 1 (
    set arquivoRegedit=DeshabilitarJavaUpdates.x64.reg
) else if %osVersion% == Windows_7_Professional_SP1 (
    set arquivoRegedit=DeshabilitarJavaUpdates.i586.reg
) else (
    echo MSGBOX "Esta versão de Windows ainda não está suportada! Favor entrar em contato com o Suporte do Portal Cronos." > %temp%\TEMPmessage.vbs
    call %temp%\TEMPmessage.vbs
    del %temp%\TEMPmessage.vbs /f /q
    exit
)

REM goto FIM


REM ================ Instalar JRE + deshabilitar updates automáticos: ========================================

if %tipoOS% == 64 (
    Start "" /wait jre-8u92-windows-x64.exe /s INSTALLDIR=%drive%\\"Program Files"\Java\jre1.8.0_92
REM O seguinte não adiantou: 
REM echo Favor esperar 300 segundos, e NÃO CONTINUAR! Não aperta nenhuma tecla!
REM timeout 300
    regedit.exe /s %arquivoRegedit%
) else if %tipoOS% == 32 (
    Start "" /wait jre-8u92-windows-i586.exe /s INSTALLDIR=%drive%\\"Program Files"\Java\jre1.8.0_92
REM O seguinte não adiantou: 
REM echo Favor esperar 300 segundos, e NÃO CONTINUAR! Não aperta nenhuma tecla!
REM timeout 300
    regedit.exe /s %arquivoRegedit%
) else (
    echo Tipo OS %tipoOS% não reconhecido ^! Opções: 32 ou 64 ^(Bits^)
)


:SKIP_JRE

REM ================ Instalar diretório de Log: ========================================

cd\
if not exist C:\ProgramData\ (
    echo MSGBOX "O diretório padrão de Windows ProgramData não existe! Favor entrar em contato com o Suporte do Portal Cronos." > %temp%\TEMPmessage.vbs
    call %temp%\TEMPmessage.vbs
    del %temp%\TEMPmessage.vbs /f /q
    exit
)
cd ProgramData
if not exist C:\ProgramData\PortalCronos\ mkdir PortalCronos
cd PortalCronos
if not exist C:\ProgramData\PortalCronos\Logs\ mkdir Logs
cd Logs
if not exist C:\ProgramData\PortalCronos\Logs\Local\ mkdir Local
REM Não precisa dar privilêgios, pois a Scheduled Task roda como SYSTEM

:SKIP_JRE_TEMPDIR

REM ================ Instalar diretório Arquivos de Programas PC: ========================================

REM Já instalado pelo self-extractable file 
REM cd\
REM cd "Arquivos de Programas PC"

:SKIP_JRE_TEMPDIR_PROGRAMDIR

REM ================ Instalar as diversas opções no menu de Windows, e gravar IsAmbienteNuvem.bat, DEPOIS da instalação dos programas de Java: ========================================

cd\
cd "Arquivos de Programas PC"

call Instalador.bat

cd\


REM Proteção contra exclusão indevida por gerente TI ignorante que gosta de apagar coisas 
REM e que apaga tudo que não foi ele mesmo que tinha feito, sem avisar ninguêm,
REM apenas no servidor de monitoramento:

if %idFornecedor% == -1 (
    attrib "Arquivos de Programas PC" +S +H
)

if %tipoInstalacao% == 2 (
    attrib "Arquivos de Programas PC" +S +H
)

cd "Arquivos de Programas PC"

del /f /q *.reg
del /f /q *.exe
del /f /q Instalador.bat
del /f /q TestadorUnitarioInstalacaoDesinstalacao.bat
del /f /q .gitignore

if %tipoInstalacao% == 1 (
    del /f /q AdicionarFornecedorNuvem.bat
    del /f /q RemoverFornecedorNuvem.bat
)

cd "Integração Fornecedor - Portal Cronos"
del /f /q TestadorUnitario.log
del /f /q TesteTresParam.log
del /f /q .gitignore

if %idFornecedor% NEQ -1 (
    rmdir /s /q tpl
)

cd conf

if %tipoInstalacao% == 1 (
    del /f /q TemplateNuvemAPS.properties
) else if %tipoInstalacao% == 2 (
    del /f /q "Integração Fornecedor - Portal Cronos.properties"
)



cd\
cd "Arquivos de Programas PC"

REM Foi testado via teste integrado completo que o seguinte funciona, mesmo que deixar o arquivo selecionado 
REM após o duplo-clique para executar a instalação:
REM Foi testado que o seguinte funciona também no caso que o diretório for C:\Temp\ ao invés de C:\temp\:
del /f /q C:\temp\"Instalador do Integrador Fornecedores - Portal Cronos.*.exe"



REM ================ Instalar Task Não-Nuvem no Windows Scheduler: ========================================

if %tipoInstalacao% == 2 (
    goto SKIP_TASK_WINDOWS_SCHEDULER
)

cd\
cd "Arquivos de Programas PC"

REM O seguinte não marca a opção "Run whether user is logged on or not" :
REM SCHTASKS /Create /TN "Teste Automação SCHTASKS" /TR "C:\Arquivos de Programas PC\Integração Fornecedor - Portal Cronos\Job15a15min.bat" /SC MINUTE /MO 15

REM O seguinte não cria automaticamente todas as configurações desejadas : 
REM SCHTASKS /Create /TN "Integração Portal Cronos - Fornecedor" /TR "C:\Arquivos de Programas PC\Integração Fornecedor - Portal Cronos\Job15a15min.bat" /SC MINUTE /MO 15 /RU SYSTEM /NP /RL HIGHEST

REM O seguinte XML tem as configurações completas e foi criado criando a task manualmente,
REM e em seguida exportada para XML : 

if %osVersion% == Windows_Server_2008_R2_SP1 (
REM Testado: 
    SCHTASKS /Create /TN "Integração Portal Cronos - Fornecedor" /XML "C:/Arquivos de Programas PC/Integração Portal Cronos - Fornecedor.Windows.2008_R2.TaskSchedule.xml"
) else if %Windows_10_Pro_64bit% == 1 (
REM Testado: 
    SCHTASKS /Create /TN "Integração Portal Cronos - Fornecedor" /XML "C:/Arquivos de Programas PC/Integração Portal Cronos - Fornecedor.Windows.2008_R2.TaskSchedule.xml"
) else if %osVersion% == Windows_Server_2012_R2 (
REM Testado: 
    SCHTASKS /Create /TN "Integração Portal Cronos - Fornecedor" /XML "C:/Arquivos de Programas PC/Integração Portal Cronos - Fornecedor.Windows.2008_R2.TaskSchedule.xml"
) else if %osVersion% == Windows_Server_2016 (
REM Testado: 
    SCHTASKS /Create /TN "Integração Portal Cronos - Fornecedor" /XML "C:/Arquivos de Programas PC/Integração Portal Cronos - Fornecedor.Windows.2008_R2.TaskSchedule.xml"
) else (
    SCHTASKS /Create /TN "Integração Portal Cronos - Fornecedor" /XML "C:/Arquivos de Programas PC/Integração Portal Cronos - Fornecedor.Windows.2008_R2.TaskSchedule.xml"
)

SCHTASKS /Run /TN "Integração Portal Cronos - Fornecedor"

:SKIP_TASK_WINDOWS_SCHEDULER

REM ================ Fim instalação Task Não-Nuvem no Windows Scheduler. ========================================

cd\
cd "Arquivos de Programas PC"

REM Apagar o arquivo "Integração Portal Cronos - Fornecedor.Windows.2008_R2.TaskSchedule.xml" e outros parecidos: 
del /f /q *.xml

if %tipoInstalacao% == 2 (
    call AdicionarFornecedorNuvem.bat

    echo.
    echo          Primeira fase da instalação concluida!
    echo          Para complementar a instalação em qualquer momento,
    echo          veja as diversas opções no menu de Windows Iniciar ^> Portal Cronos.
    echo.
    
    echo MSGBOX "Primeira fase da instalação concluida! Para complementar a instalação em qualquer momento, veja as diversas opções no menu de Windows Iniciar > Portal Cronos." > %temp%\TEMPmessage.vbs
    call %temp%\TEMPmessage.vbs
    del %temp%\TEMPmessage.vbs /f /q
)

REM Excluir este próprio arquivo apenas no final, 
REM pois foi testado que não vai excluir os arquivos que viriam depois disso:
del /f /q Primeira_Instalacao_Versao_Windows.bat

exit

:FIM

