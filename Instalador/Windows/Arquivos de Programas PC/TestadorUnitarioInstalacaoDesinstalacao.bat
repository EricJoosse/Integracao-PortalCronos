cls
@echo off

chcp 1252>nul


REM goto DesinstalarManualTI
goto InstalarDirLog
REM goto DesinstalarDirLog


REM ================ Testes INstalação Manual Manutenção TI do menu de Windows: ========================================

call InstalarManualTI.bat
exit

REM ================ Testes DESinstalação Manual Manutenção TI do menu de Windows: ========================================

:DesinstalarManualTI
call DesinstalarManualTI.bat

IF %ERRORLEVEL% NEQ 0 (
    goto PULAR_MENSAGEM_SUCESSO
)
echo MSGBOX "Erro: este comando não foi pulado indevidamente!" > %temp%\TEMPmessage.vbs
call %temp%\TEMPmessage.vbs
del %temp%\TEMPmessage.vbs /f /q


:PULAR_MENSAGEM_SUCESSO
echo MSGBOX "Sucesso: o comando acima foi pulado devidamente!" > %temp%\TEMPmessage.vbs
call %temp%\TEMPmessage.vbs
del %temp%\TEMPmessage.vbs /f /q

exit

REM ================ Testes instalação diretório de Log: ========================================

:InstalarDirLog
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
REM Não precisa dar privilêgios mais, pois a Scheduled Task roda como SYSTEM

exit

REM ================ Testes DESinstalação diretório de Log: ========================================

:DesinstalarDirLog
cd\
cd ProgramData
REM O seguinte não funciona como deveria: rmdir /s /q PortalCronos
cd PortalCronos
cd Logs
rmdir /s /q Local
cd\
cd ProgramData
cd PortalCronos
rmdir /s /q Logs
cd\
cd ProgramData
rmdir /s /q PortalCronos
cd\

exit

:FIM
