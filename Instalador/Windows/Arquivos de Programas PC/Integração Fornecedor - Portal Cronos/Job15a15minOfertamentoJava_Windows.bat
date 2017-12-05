cls
@echo off

REM Limpar CLASSPATH :
REM set CLASSPATH=

set path=%path%;C:\Program Files\Java\jre1.8.0_92\bin

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

REM Caminho completo para o caso que tiver 2 JRE´s no mesmo servidor,
REM e o caminho do outro JRE vem primeiro no PATH de DOS :
REM C:/"Program Files"/Java/jre1.8.0_92/bin/java.exe -cp integr-fornecedor-1.3.0.jar pcronos.integracao.fornecedor.TestesComponentes >> Job15a15minOfertamentoJava.log
C:/"Program Files"/Java/jre1.8.0_92/bin/java.exe -cp integr-fornecedor-1.3.0.jar pcronos.integracao.fornecedor.IntegracaoFornecedorCompleta >> Job15a15minOfertamentoJava.log

exit


