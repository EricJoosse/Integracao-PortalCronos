cls
@echo off

REM Resettar ERRORLEVEL para 0 (não existe valor default):
call (exit /b 0)

call CaminhoJRE.bat

if exist Desinstalador.log del /f /q Desinstalador.log

REM Usar o caminho completo do JRE para o caso que tiver 2 JRE´s no mesmo servidor 
REM e o caminho do outro JRE está na primeira posição no PATH de DOS:

REM Se tiver um parentese dentro do path, "set path" não funciona dentro de if´s e else´s, 
REM então precisamos de goto´s:

if exist C:/"Program Files (x86)"/Java/jre1.8.0_191/bin/java.exe (
    goto PathProlac
) else if exist C:/"Program Files (x86)"/Java/jre1.8.0_111/bin/java.exe (
    goto PathPadeirao
) else if exist C:/"Program Files"/Java/jre1.8.0_92/bin/java.exe (
    goto PathOutros
) else (
    echo MSGBOX "Erro! O JRE não foi encontrado!" > %temp%\TEMPmessage.vbs
    exit
)

:PathProlac
set path=C:\Program Files (x86)\Java\jre1.8.0_191\bin;%path%
C:/"Program Files (x86)"/Java/jre1.8.0_191/bin/java.exe -cp integr-fornecedor-%versaoIntegrador%.jar pcronos.integracao.fornecedor.Desinstalador >> Desinstalador.log
goto PularPathOutros

:PathPadeirao
set path=C:\Program Files (x86)\Java\jre1.8.0_111\bin;%path%
C:/"Program Files (x86)"/Java/jre1.8.0_111/bin/java.exe -cp integr-fornecedor-%versaoIntegrador%.jar pcronos.integracao.fornecedor.Desinstalador >> Desinstalador.log
goto PularPathOutros

:PathOutros
set path=C:\Program Files\Java\jre1.8.0_92\bin;%path%
C:/"Program Files"/Java/jre1.8.0_92/bin/java.exe -cp integr-fornecedor-%versaoIntegrador%.jar pcronos.integracao.fornecedor.Desinstalador >> Desinstalador.log
:PularPathOutros



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


