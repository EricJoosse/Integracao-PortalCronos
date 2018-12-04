cls
@echo off

REM Limpar CLASSPATH :
REM set CLASSPATH=

SETLOCAL

call bin\Versao.bat

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
REM         Errado em versões < 2.1.2B: set path=%path%;C:\Program Files (x86)\Java\jre1.8.0_191\bin
C:/"Program Files (x86)"/Java/jre1.8.0_191/bin/java.exe -cp integr-fornecedor-%versaoIntegrador%.jar pcronos.integracao.fornecedor.IntegracaoFornecedorCompleta >> Job15a15minOfertamentoJava.log
goto PularPathOutros

:PathPadeirao
set path=C:\Program Files (x86)\Java\jre1.8.0_111\bin;%path%
C:/"Program Files (x86)"/Java/jre1.8.0_111/bin/java.exe -cp integr-fornecedor-%versaoIntegrador%.jar pcronos.integracao.fornecedor.IntegracaoFornecedorCompleta >> Job15a15minOfertamentoJava.log
goto PularPathOutros

:PathOutros
set path=C:\Program Files\Java\jre1.8.0_92\bin;%path%
REM         Errado em versões < 2.1.2B: set path=%path%;C:\Program Files\Java\jre1.8.0_92\bin
C:/"Program Files"/Java/jre1.8.0_92/bin/java.exe -cp integr-fornecedor-%versaoIntegrador%.jar pcronos.integracao.fornecedor.IntegracaoFornecedorCompleta >> Job15a15minOfertamentoJava.log
:PularPathOutros



ENDLOCAL
exit


