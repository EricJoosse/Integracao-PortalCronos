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

REM Limpar CLASSPATH :
REM set CLASSPATH=

SETLOCAL

REM Se tiver um parentese dentro do path, o seguinte não funciona:

REM if %idFornecedor% == 30 (
REM     set path=C:\Program Files ^(x86^)\Java\jre1.8.0_161\bin;%path%
REM ) else (
REM     set path=C:\Program Files\Java\jre1.8.0_92\bin;%path%
REM )

if exist C:/"Program Files (x86)"/Java/jre1.8.0_161/bin/java.exe (
    goto PathProlac
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


if exist Instalador.log del /f /q Instalador.log

REM Caminho completo para o caso que tiver 2 JRE´s no mesmo servidor 
REM e o caminho do outro JRE está na primeira posição no PATH de DOS:

if exist C:/"Program Files (x86)"/Java/jre1.8.0_161/bin/java.exe (
    C:/"Program Files (x86)"/Java/jre1.8.0_161/bin/java.exe -cp integr-fornecedor-2.7.0.jar pcronos.integracao.fornecedor.Instalador %idFornecedor% >> Instalador.log
) else if exist C:/"Program Files"/Java/jre1.8.0_92/bin/java.exe (
    C:/"Program Files"/Java/jre1.8.0_92/bin/java.exe -cp integr-fornecedor-2.7.0.jar pcronos.integracao.fornecedor.Instalador %idFornecedor% >> Instalador.log
) else (
    echo MSGBOX "Erro! O JRE não foi encontrado!" > %temp%\TEMPmessage.vbs
    exit
)


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


