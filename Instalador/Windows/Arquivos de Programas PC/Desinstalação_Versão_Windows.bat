
cls
@echo off

chcp 1252>nul

echo.
echo          Desinstalando...................
echo.

REM echo MSGBOX "Desinstalando..." > %temp%\TEMPmessage.vbs
REM call %temp%\TEMPmessage.vbs
REM del %temp%\TEMPmessage.vbs /f /q
  
    set drive=C:
REM set drive=D:

REM ================ Remover Manual Manutenção TI do menu de Windows, ANTES da remoção dos programas de Java: ========================================

call DesinstalarManualTI.bat

IF %ERRORLEVEL% NEQ 0 (
    goto PULAR_MENSAGEM_SUCESSO
)


REM ================ Remover Task no Windows Scheduler: ========================================

SCHTASKS /End /TN "Integração Portal Cronos - Fornecedor"
SCHTASKS /Delete /TN "Integração Portal Cronos - Fornecedor" /F


REM ================ Remover subdiretórios de "Arquivos de Programas PC": ========================================

cd\
cd "Arquivos de Programas PC"
rmdir /s /q "Integração Fornecedor - Portal Cronos"
for %%f in (*) do if not %%~xf==.bat del /f /q "%%f"
REM Não precisa mais, pois o Instalador já apaga no final da instalação: del /f /q Primeira_Instalacao_Versao_Windows.bat

REM ================ Remover diretório de Log: ========================================

REM Evitando interferência indevida com outros diretórios:
REM    C:\ProgramData\PortalCronos\Logs\Local
REM    C:\ProgramData\PortalCronos\Logs\Remoto\Integracao\
REM    C:\ProgramData\PortalCronos\Logs\Remoto\APK\

cd\
cd ProgramData

REM Foi testado que "rmdir /s /q PortalCronos" não funciona como deveria. 
REM Talvez isso é porque existem diversos níveis de subsubsubdiretórios:
 
cd PortalCronos
cd Logs
rmdir /s /q Local

if NOT exist "Remoto" (
  cd\
  cd ProgramData
  cd PortalCronos
  rmdir /s /q Logs
  cd\
  cd ProgramData
  rmdir /s /q PortalCronos
)
cd\

REM ================ Remover JRE: ========================================

wmic product where "name like 'Java 8 Update 92%%'" call uninstall /nointeractive


:FIM

echo.
echo          Desinstalação concluida!
echo.

echo MSGBOX "Desinstalação concluida!" > %temp%\TEMPmessage.vbs
call %temp%\TEMPmessage.vbs
del %temp%\TEMPmessage.vbs /f /q

REM ================ Remover diretório "Arquivos de Programas PC": ========================================

REM O seguinte consegue remover todos os arquivos no diretório "Arquivos de Programas PC",
REM até este arquivo .bat, porém não consegue remover o diretório "Arquivos de Programas PC" :
REM ????????? Funcionou quando usei vbs acima antes disso !!!!!!!!!
cd\
rmdir /s /q "Arquivos de Programas PC"

:PULAR_MENSAGEM_SUCESSO
