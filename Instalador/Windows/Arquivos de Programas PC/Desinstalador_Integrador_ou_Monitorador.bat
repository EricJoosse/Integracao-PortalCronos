
cls
@echo off

REM "chcp 1252>nul" é necessário para evitar que DOS não reconhece acentos Portugueses no caminho 
REM "Integração Fornecedor - Portal Cronos" em alguns ou talvez até em todos os servidores:

chcp 1252>nul

cd\
cd "Arquivos de Programas PC"
call "Integração Fornecedor - Portal Cronos\bin\VerificarUsuarioAdministrador.bat"

echo.
echo          Desinstalando...................
echo.

REM echo MSGBOX "Desinstalando..." > %temp%\TEMPmessage.vbs
REM call %temp%\TEMPmessage.vbs
REM del %temp%\TEMPmessage.vbs /f /q
  
    set drive=C:
REM set drive=D:

cd\
cd "Arquivos de Programas PC"

call "Integração Fornecedor - Portal Cronos\bin\IsAmbienteNuvem.bat"


REM ================ Remover as diversas opções do menu de Windows, ANTES da remoção dos programas de Java: ========================================

cd\
cd "Arquivos de Programas PC"

call "Integração Fornecedor - Portal Cronos\bin\Desinstalador.bat"

IF %ERRORLEVEL% NEQ 0 (
    goto PULAR_MENSAGEM_SUCESSO
)


REM ================ Remover Task(s) no Windows Scheduler: ========================================

REM Ambiente Nuvem:
REM Aspas duplas são necessárias porque se o variável for empty, este .bat vai falhar pois não entende o parentese direto após o ==
if "%IsAmbienteNuvem%" == "1" (
    for /f "tokens=2 delims=\" %%x in ('SCHTASKS /QUERY /FO:LIST ^| FINDSTR "Integração Portal Cronos."') do SCHTASKS /End /TN "\%%x"
    for /f "tokens=2 delims=\" %%x in ('SCHTASKS /QUERY /FO:LIST ^| FINDSTR "Integração Portal Cronos."') do SCHTASKS /Delete /TN "\%%x" /F
) else if "%IsAmbienteNuvem%" == "0" (
    SCHTASKS /End /TN "Integração Portal Cronos - Fornecedor"
    SCHTASKS /Delete /TN "Integração Portal Cronos - Fornecedor" /F
) else (
REM Não fazer cls aqui, para poder visualizar eventuais erros
    echo.
    echo          A desinstalação falhou! Variável IsAmbienteNuvem inválido!
    echo.
    
    echo MSGBOX "A instalação falhou! Variável IsAmbienteNuvem inválido!" > %temp%\TEMPmessage.vbs
    call %temp%\TEMPmessage.vbs
    del %temp%\TEMPmessage.vbs /f /q
    start notepad Desinstalador.log
)



REM ================ Remover subdiretórios de "Arquivos de Programas PC": ========================================

REM Esperar 2 minutos para o caso que tem um processo de java.exe rodando neste momento,
REM para evitar o seguinte erro: 
REM "integr-fornecedor-x.y.z.jar - The process cannot access the file because it is being used by another process."
REM e o mesmo erro para o arquivo Job15a15min.log e os jars no diretório /lib.

cls
echo.
echo.
REM Mais tarde terminar e descomentar os seguintes if´s:
REM if "%idOsVersion%" == "Windows 10 Pro" (
REM    if "%idFornecedor%" == "947" (
         echo Favor procurar o processo "java.exe" do Integrador
         echo no Windows Task Manager (taskmgr.exe), na aba "Processos", 
         echo ordenando por Nome, e se tiver, matar o processo manualmente.
         echo.
         echo Esperando 3 minutos... 
         echo.
         timeout /T 180 /nobreak
         goto FimEspera
REM    )
REM )
echo Esperando 2 minutos para o processo de integração terminar que está rodando neste momento... 
echo Favor não interromper!
echo.
timeout /T 120 /nobreak
:FimEspera


cd\
cd "Arquivos de Programas PC"
rmdir /s /q "Integração Fornecedor - Portal Cronos"
for %%f in (*) do if not %%~xf==.bat del /f /q "%%f"
REM Não precisa mais, pois o Instalador já apaga no final da instalação: del /f /q Instalador_Integrador.bat e del /f /q Instalador_Monitorador.bat

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

REM Nunca mais remover o JRE, mesmo no caso o JRE foi instalado pela primeira vez pelo Portal Cronos, 
REM pois pode ser que depois disso outro sistema foi instalado que passou a usar o mesmo JRE:
goto SKIP_JRE

REM ================ Remover JRE: ========================================

REM Mais tarde parametrizar no Desinstalador se o usuário quiser desinstalar o JRE também sim/não, 
REM perguntando sem ele tem certeza, pois outros sistemas podem parar de funcionar. 
REM Não dar nem esta opção no caso que o JRE (1.8.0_92) não foi instalado pelo Portal Cronos 
REM porém o Portal Cronos tinha usado um JRE já existente instalado anteriormente por terceiros.
REM 
REM No caso, também parametrizar 'Java 8 Update 92, 111, 191, etc.'

wmic product where "name like 'Java 8 Update 92%%'" call uninstall /nointeractive

:SKIP_JRE

:FIM

REM Não fazer cls aqui, para poder visualizar eventuais erros
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
