AT > NUL
IF %ERRORLEVEL% EQU 0 (
REM    ECHO you are Administrator
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
REM                                                  Desinstalar_Integração_Fornecedor_PortalCronos.bat,
REM                                                  Instalador_Integrador.bat,
REM                                                  Instalador_Monitorador.bat):  
    exit
)
