cls
@echo off

REM Resettar ERRORLEVEL para 0 (não existe valor default):
call (exit /b 0)

SET /P idFornecedor=Favor digitar o ID do fornecedor: 
IF "%idFornecedor%"=="" GOTO ErroIdFornecedor
GOTO PularErro
:ErroIdFornecedor
echo MSGBOX "Erro: ID do fornecedor não informado! Desinstalação abortada!!" > %temp%\TEMPmessage.vbs
call %temp%\TEMPmessage.vbs
del %temp%\TEMPmessage.vbs /f /q
REM O seguinte sai settando ERRORLEVEL para 1, sem fechar o script chamador: 
exit /B 1
:PularErro

REM Limpar CLASSPATH :
REM set CLASSPATH=

SETLOCAL


REM Se tiver um parentese dentro do path, o seguinte não funciona:

REM if %idFornecedor% == 30 (
REM     set path=C:\Program Files ^(x86^)\Java\jre1.8.0_161\bin;%path%
REM ) else (
REM     set path=C:\Program Files\Java\jre1.8.0_92\bin;%path%
REM )

if %idFornecedor% == 30 (
    goto PathProlac
) else (
    goto PathOutros
)

:PathProlac
set path=C:\Program Files (x86)\Java\jre1.8.0_161\bin;%path%
goto PularPathOutros
:PathOutros
set path=C:\Program Files\Java\jre1.8.0_92\bin;%path%
:PularPathOutros


REM         Errado em versões < 2.1.2B: set path=%path%;C:\Program Files\Java\jre1.8.0_161\bin
REM Prolac: Errado em versões < 2.1.2B: set path=%path%;C:\Program Files (x86)\Java\jre1.8.0_161\bin

REM http://stackoverflow.com/questions/23730887/why-is-rt-jar-not-part-of-the-class-path-system-property : 
REM 	"rt.jar doesn't need to be in the classpath, since it is already in the bootclasspath. It is safe to remove it from your classpath."
REM set CLASSPATH=%CLASSPATH%;C:\Program Files\Java\jre1.8.0_92\lib\rt.jar

REM set CLASSPATH=%CLASSPATH%;lib/jersey-bundle-1.12.jar
REM set CLASSPATH=%CLASSPATH%;lib/jersey-core-1.12.jar
REM set CLASSPATH=%CLASSPATH%;lib/jersey-multipart-1.12.jar
REM set CLASSPATH=%CLASSPATH%;lib/jdbc-oracle.jar
REM set CLASSPATH=%CLASSPATH%;lib/jaybird-full-2.2.10.jar
REM set CLASSPATH=%CLASSPATH%;.

REM echo %classpath%

chcp 1252>nul
cd\
cd "Arquivos de Programas PC"
cd "Integração Fornecedor - Portal Cronos"

if exist Desinstalador.log del /f /q Desinstalador.log

REM Caminho completo para o caso que tiver 2 JRE´s no mesmo servidor,
REM e o caminho do outro JRE vem primeiro no PATH de DOS :

if %idFornecedor% == 30 (
    C:/"Program Files (x86)"/Java/jre1.8.0_161/bin/java.exe -cp integr-fornecedor-2.6.1.jar pcronos.integracao.fornecedor.Desinstalador %idFornecedor% >> Desinstalador.log
) else (
    C:/"Program Files"/Java/jre1.8.0_92/bin/java.exe -cp integr-fornecedor-2.6.1.jar pcronos.integracao.fornecedor.Desinstalador %idFornecedor% >> Desinstalador.log
)

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


