AT > NUL
IF %ERRORLEVEL% EQU 0 (
    ECHO you are Administrator
) ELSE (
    echo.
    ECHO Favor conectar como Administrador, ou clicar em ^"Executar como Administrador^"!
    echo.

    echo MSGBOX "Favor conectar como Administrador, ou clicar em ^"Executar como Administrador^"!" > %temp%\TEMPmessage.vbs
    call %temp%\TEMPmessage.vbs
    del %temp%\TEMPmessage.vbs /f /q
REM PING 127.0.0.1 > NUL 2>&1
REM EXIT /B 1
REM /B para não fechar o script chamador (Primeira_Instalacao_Versao_Windows.bat):  
    exit /B 0
)
