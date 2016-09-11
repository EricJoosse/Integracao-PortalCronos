cls

REM Limpar CLASSPATH :
set CLASSPATH=

set path=%path%;C:\Program Files\Java\jdk1.8.0_66\bin

REM http://stackoverflow.com/questions/23730887/why-is-rt-jar-not-part-of-the-class-path-system-property : 
REM 	"rt.jar doesn't need to be in the classpath, since it is already in the bootclasspath of the JVM. It is safe to remove it from your classpath."
REM set CLASSPATH=%CLASSPATH%;C:\Program Files\Java\jre1.8.0_92\lib\rt.jar

set CLASSPATH=%CLASSPATH%;Instalador/Windows/Arquivos de Programas PC/Integração Fornecedor - Portal Cronos/lib/jersey-bundle-1.12.jar
set CLASSPATH=%CLASSPATH%;Instalador/Windows/Arquivos de Programas PC/Integração Fornecedor - Portal Cronos/lib/jersey-core-1.12.jar
set CLASSPATH=%CLASSPATH%;Instalador/Windows/Arquivos de Programas PC/Integração Fornecedor - Portal Cronos/lib/jersey-multipart-1.12.jar
set CLASSPATH=%CLASSPATH%;Instalador/Windows/Arquivos de Programas PC/Integração Fornecedor - Portal Cronos/lib/jdbc-oracle-10.2.0.5.0.jar
set CLASSPATH=%CLASSPATH%;Instalador/Windows/Arquivos de Programas PC/Integração Fornecedor - Portal Cronos/lib/jaybird-full-2.2.10.jar
set CLASSPATH=%CLASSPATH%;./target/classes
set CLASSPATH=%CLASSPATH%;.

@echo %classpath%

cd\
cd PCronos
cd "Integração Fornecedor - Portal Cronos"


REM javac src/pcronos/integracao/fornecedor/TestesComponentes.java
REM javac src/pcronos/integracao/fornecedor/IntegracaoFornecedorCompleta.java
javac -d target/classes src/pcronos/integracao/fornecedor/*.java

REM http://docs.oracle.com/javase/7/docs/technotes/tools/windows/java.html : 
REM     "-classpath or -cp overrides any setting of the CLASSPATH environment variable" 
REM Então o seguinte dá erro "Class not found" (jersey) :
REM java -cp ./target/classes pcronos.integracao.fornecedor.IntegracaoFornecedorCompleta

REM java pcronos.integracao.fornecedor.TestesComponentes  REM (java TestesComponentes.class dá erro !)
java pcronos.integracao.fornecedor.IntegracaoFornecedorCompleta
