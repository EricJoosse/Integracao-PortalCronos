cls
@echo off

REM Resettar ERRORLEVEL para 0 (não existe valor default):
call (exit /b 0)


call Inicializacoes.bat
call Versao.bat
call CaminhoJRE.bat Desinstalador.log Desinstalador


set arquivoLog="Desinstalador.log"
set tamanhoArqLog=0

FOR /F "usebackq" %%A IN ('%arquivoLog%') DO set tamanhoArqLog=%%~zA

if %tamanhoArqLog% GTR 0 (
    echo.
    echo          A desinstalação falhou!
    echo.
    
    echo MSGBOX "A desinstalação falhou!" > %temp%\TEMPmessage.vbs
    call %temp%\TEMPmessage.vbs
    del %temp%\TEMPmessage.vbs /f /q
    start notepad Desinstalador.log
    ENDLOCAL
    exit /B 1
) else (
    ENDLOCAL
REM /B para não fechar o script chamador:  
    exit /B 0
)


