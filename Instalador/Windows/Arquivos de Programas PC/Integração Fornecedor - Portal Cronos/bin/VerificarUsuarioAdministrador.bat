REM AT > NUL
REM echo %ERRORLEVEL%
REM Funciona com Windows 2008 R2 SP1, porém deu 1 no Windows 2012 R2, indevidamente.....
REM pause


REM O seguinte foi testado e funcionou com:
REM =======================================
REM     - Windows Server 2008 R2 SP1
REM     - Windows Server 2012 R2
REM
REM Desvantagens:
REM =============
REM 1. This solution normally works great, but if the "Server" (LanmanServer) service is stopped, 
REM    the error code for "Server service has not been started" is the same error code that you get for 
REM    "Access is denied" resulting in a false negative.
REM    The Server service is not started while in Safe Mode (with or without networking).
REM 
REM 2. This code returns a false positive (at least on Windows 7) if the user is a Power User. 
REM    A Power User can also "elevate" and then run net session successfully (ERRORLEVEL = 0) - but 
REM    they don't actually have admin rights

REM >nul: hides visual output of command by redirecting the standard output (numeric handle 1 / STDOUT) stream to nul
REM 2>&1: redirects the standard error output stream (numeric handle 2 / STDERR) to the same destination as numeric handle 1
net session >nul 2>&1

IF %ERRORLEVEL% EQU 0 (
REM echo.
REM ECHO You are Administrator
REM echo.
REM 
REM echo MSGBOX "You are Administrator" > %temp%\TEMPmessage.vbs
REM call %temp%\TEMPmessage.vbs
REM del %temp%\TEMPmessage.vbs /f /q
) ELSE (
    echo.
    ECHO Favor conectar como Administrador, ou clicar em ^"Executar como Administrador^" com o botao direita do mouse!
    echo.

    echo MSGBOX "Favor conectar como Administrador, ou clicar em ""Executar como Administrador"" com o botão direita do mouse!" > %temp%\TEMPmessage.vbs
    call %temp%\TEMPmessage.vbs
    del %temp%\TEMPmessage.vbs /f /q
REM PING 127.0.0.1 > NUL 2>&1
REM EXIT /B 1
REM Sem /B para forçar fechar os scripts chamadores (AdicionarFornecedorNuvem.bat, 
REM                                                  RemoverFornecedorNuvem.bat, 
REM                                                  Desinstalador_Integrador_ou_Monitorador.bat,
REM                                                  Instalador_Integrador.bat,
REM                                                  Instalador_Monitorador.bat):  
    exit
)
