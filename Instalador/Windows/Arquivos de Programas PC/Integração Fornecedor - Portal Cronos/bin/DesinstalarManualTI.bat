cls
@echo off

REM Resettar ERRORLEVEL para 0 (não existe valor default):
call (exit /b 0)

REM Limpar CLASSPATH :
REM set CLASSPATH=

SETLOCAL


REM Se tiver um parentese dentro do path, o seguinte não funciona:

if exist C:/"Program Files (x86)"/Java/jre1.8.0_161/bin/java.exe (
    goto PathProlac
) else if exist C:/"Program Files"/Java/jre1.8.0_92/bin/java.exe (
    goto PathOutros
) else (
    echo MSGBOX "Erro! O JRE não foi encontrado!" > %temp%\TEMPmessage.vbs
    exit
)

:PathProlac
set path=C:\Program Files (x86)\Java\jre1.8.0_161\bin;%path%
goto PularPathOutros
:PathOutros
set path=C:\Program Files\Java\jre1.8.0_92\bin;%path%
:PularPathOutros


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

REM Caminho completo para o caso que tiver 2 JRE´s no mesmo servidor 
REM e o caminho do outro JRE está na primeira posição no PATH de DOS:

if exist C:/"Program Files (x86)"/Java/jre1.8.0_161/bin/java.exe (
    C:/"Program Files (x86)"/Java/jre1.8.0_161/bin/java.exe -cp integr-fornecedor-2.6.1.jar pcronos.integracao.fornecedor.Desinstalador >> Desinstalador.log
) else if exist C:/"Program Files"/Java/jre1.8.0_92/bin/java.exe (
    C:/"Program Files"/Java/jre1.8.0_92/bin/java.exe -cp integr-fornecedor-2.6.1.jar pcronos.integracao.fornecedor.Desinstalador >> Desinstalador.log
) else (
    echo MSGBOX "Erro! O JRE não foi encontrado!" > %temp%\TEMPmessage.vbs
    exit
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


