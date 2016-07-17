cls
@echo off

chcp 1252>nul

REM echo MSGBOX "Instalando..." > %temp%\TEMPmessage.vbs
REM call %temp%\TEMPmessage.vbs
REM del %temp%\TEMPmessage.vbs /f /q

echo.
echo          Instalando...................
echo.


REM set osVersion=Windows_Server_2016
REM set osVersion=Windows_Server_2012_R2
REM set osVersion=Windows_Server_2012
set osVersion=Windows_Server_2008_R2_SP1
REM set osVersion=Windows_Server_2008
REM set osVersion=Windows_Server_2003_R2
REM set osVersion=Windows_Server_2003
REM set osVersion=Windows_2000
REM set osVersion=Windows_NT_40
REM set osVersion=Windows_NT_351
REM set osVersion=Windows_NT_35
REM set osVersion=Windows_NT_31
REM set osVersion=Windows_8_Pro
REM set osVersion=Windows_7_Professional_SP1
  
  
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
) else (
REM set /A tipoOS=32
    set /A tipoOS=64
)

    set drive=C:
REM set drive=D:

REM goto SKIP_JRE
REM goto SKIP_JRE_TEMPDIR
REM goto SKIP_JRE_TEMPDIR_PROGRAMDIR
REM goto SKIP_JRE_TEMPDIR_PROGRAMDIR_TASK
REM goto FIM

REM !!!!!!!!!!!!!!! CUIDADO PARA NÃO CORROMPER O REGEDIT !!!!!!!!!
REM !!!!!!!!!!!!!!! CUIDADO PARA NÃO CORROMPER O REGEDIT !!!!!!!!!
REM !!!!!!!!!!!!!!! CUIDADO PARA NÃO CORROMPER O REGEDIT !!!!!!!!!
REM !!!!!!!!!!!!!!! CUIDADO PARA NÃO CORROMPER O REGEDIT !!!!!!!!!
REM !!!!!!!!!!!!!!! CUIDADO PARA NÃO CORROMPER O REGEDIT !!!!!!!!!
REM !!!!!!!!!!!!!!! CUIDADO PARA NÃO CORROMPER O REGEDIT !!!!!!!!!
REM !!!!!!!!!!!!!!! CUIDADO PARA NÃO CORROMPER O REGEDIT !!!!!!!!!
REM !!!!!!!!!!!!!!! CUIDADO PARA NÃO CORROMPER O REGEDIT !!!!!!!!!

if %osVersion% == Windows_Server_2008_R2_SP1 (
    set arquivoRegedit="%drive%\\Arquivos de Programas PC\\DeshabilitarJavaUpdates.x64.reg"
) else if %osVersion% == Windows_Server_2012_R2 (
    set arquivoRegedit=%~dp0DeshabilitarJavaUpdates.x64.reg
) else if %osVersion% == Windows_7_Professional_SP1 (
    set arquivoRegedit=DeshabilitarJavaUpdates.i586.reg
) else (
    echo MSGBOX "Esta versão de Windows ainda não está suportada ! Favor contatar o  Suporte do Portal Cronos." > %temp%\TEMPmessage.vbs
    call %temp%\TEMPmessage.vbs
    del %temp%\TEMPmessage.vbs /f /q
    exit
)

REM goto FIM


REM ================ Instalar JRE ========================================

if %tipoOS% == 64 (
    Start /wait jre-8u92-windows-x64.exe /s INSTALLDIR=%drive%\\"Program Files"\Java\jre1.8.0_92
    regedit.exe /s %arquivoRegedit%
) else if %tipoOS% == 32 (
    Start /wait jre-8u92-windows-i586.exe /s INSTALLDIR=%drive%\\"Program Files"\Java\jre1.8.0_92
    regedit.exe /s %arquivoRegedit%
) else (
    echo Tipo OS %tipoOS% não reconhecido ^! Opções: 32 ou 64 ^(Bits^)
)


:SKIP_JRE

REM ================ Instalar diretório Temp ========================================

cd\
if not exist C:\temp\ mkdir temp
cd temp
mkdir PortalCronos
cd PortalCronos
mkdir XML
REM Não precisa dar privilêgios mais, pois a Scheduled Task roda como SYSTEM

:SKIP_JRE_TEMPDIR

REM ================ Instalar diretório Arquivos de Programas PC ========================================

REM Já instalado pelo self-extractable file 
REM cd\
REM cd "Arquivos de Programas PC"

:SKIP_JRE_TEMPDIR_PROGRAMDIR

REM ================ Instalar Task no Windows Scheduler ========================================

REM O seguinte não marca a opção "Run whether user is logged on or not" :
REM SCHTASKS /Create /TN "Teste Automação SCHTASKS" /TR "C:\Arquivos de Programas PC\Integração Fornecedor - Portal Cronos\Job15a15minOfertamentoJava_Windows.bat" /SC MINUTE /MO 15

REM O seguinte não cria automaticamente todas as configurações desejadas : 
REM SCHTASKS /Create /TN "Integração Portal Cronos - Fornecedor" /TR "C:\Arquivos de Programas PC\Integração Fornecedor - Portal Cronos\Job15a15minOfertamentoJava_Windows.bat" /SC MINUTE /MO 15 /RU SYSTEM /NP /RL HIGHEST

REM O seguinte XML tem as configurações completas e foi criado criando a task manualmente,
REM e em seguida exportada para XML : 
SCHTASKS /Create /TN "Integração Portal Cronos - Fornecedor" /XML "C:/Arquivos de Programas PC/Integração Portal Cronos - Fornecedor.Windows.2008_R2.TaskSchedule.xml"

SCHTASKS /Run /TN "Integração Portal Cronos - Fornecedor"

:SKIP_JRE_TEMPDIR_PROGRAMDIR_TASK

:FIM

echo.
echo          Instalação concluida!
echo.

echo MSGBOX "Instalação concluida!" > %temp%\TEMPmessage.vbs
call %temp%\TEMPmessage.vbs
del %temp%\TEMPmessage.vbs /f /q

