cls
@echo off

echo.
echo Favor entrar em contato com o setor Desenvolvimento do Portal Cronos para obter o ID da empresa fornecedora.
echo.

SET /P idFornecedor=Favor digitar o ID da empresa fornecedora: 
IF "%idFornecedor%"=="" GOTO ErroIdFornecedor
GOTO PularErro
:ErroIdFornecedor
echo MSGBOX "Erro: ID do fornecedor não informado! Instalação não concluída!!" > %temp%\TEMPmessage.vbs
call %temp%\TEMPmessage.vbs
del %temp%\TEMPmessage.vbs /f /q
REM Fechar o script chamador também: 
exit
:PularErro

call "Integração Fornecedor - Portal Cronos\bin\CaminhoJRE.bat"

REM Se tiver um parentese dentro do path, o seguinte não funciona:

REM if %idFornecedor% == 30 (
REM     set path=C:\Program Files ^(x86^)\Java\jre1.8.0_191\bin;%path%
REM ) else (
REM     set path=C:\Program Files\Java\jre1.8.0_92\bin;%path%
REM )


if exist Instalador.log del /f /q Instalador.log

REM Usar o caminho completo do JRE para o caso que tiver 2 JRE´s no mesmo servidor 
REM e o caminho do outro JRE está na primeira posição no PATH de DOS:

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

REM if %idFornecedor% == 30 (
REM     goto PathProlac
REM ) else (
REM     goto PathOutros
REM )

:PathProlac
set path=C:\Program Files (x86)\Java\jre1.8.0_191\bin;%path%
C:/"Program Files (x86)"/Java/jre1.8.0_191/bin/java.exe -cp integr-fornecedor-%versaoIntegrador%.jar pcronos.integracao.fornecedor.Instalador %idFornecedor% >> Instalador.log
goto PularPathOutros

:PathPadeirao
set path=C:\Program Files (x86)\Java\jre1.8.0_111\bin;%path%
C:/"Program Files (x86)"/Java/jre1.8.0_111/bin/java.exe -cp integr-fornecedor-%versaoIntegrador%.jar pcronos.integracao.fornecedor.Instalador %idFornecedor% >> Instalador.log
goto PularPathOutros

:PathOutros
set path=C:\Program Files\Java\jre1.8.0_92\bin;%path%
C:/"Program Files"/Java/jre1.8.0_92/bin/java.exe -cp integr-fornecedor-%versaoIntegrador%.jar pcronos.integracao.fornecedor.Instalador %idFornecedor% >> Instalador.log
:PularPathOutros




set arquivoLog="Instalador.log"
set tamanhoArqLog=0

FOR /F "usebackq" %%A IN ('%arquivoLog%') DO set tamanhoArqLog=%%~zA

if %tamanhoArqLog% GTR 0 (
    echo.
    echo          A instalação falhou!
    echo.
    
    echo MSGBOX "A instalação falhou!" > %temp%\TEMPmessage.vbs
    call %temp%\TEMPmessage.vbs
    del %temp%\TEMPmessage.vbs /f /q
    start notepad Instalador.log
) ELSE (
    echo.
    echo          Primeira fase da instalação concluida!
    echo.
    
    echo MSGBOX "Primeira fase da instalação concluida!" > %temp%\TEMPmessage.vbs
    call %temp%\TEMPmessage.vbs
    del %temp%\TEMPmessage.vbs /f /q
)


ENDLOCAL
REM /B para não fechar o script chamador:  
exit /B 0


