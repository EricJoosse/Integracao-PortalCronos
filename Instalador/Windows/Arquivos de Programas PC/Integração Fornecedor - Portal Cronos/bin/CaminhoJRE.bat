REM Limpar CLASSPATH:
REM set CLASSPATH=


REM By default, variables are global to your entire command prompt session. 
REM Call the SETLOCAL command to make variables local to the scope of your script. 
REM After calling SETLOCAL, any variable assignments revert upon calling ENDLOCAL, 
REM calling EXIT, or when execution reaches the end of file (EOF) in your script.
REM The setlocal command sets the ERRORLEVEL variable to 1.
REM "SETLOCAL" é necessário aqui para poder usar ERRORLEVEL corretamente:   
SETLOCAL

REM call bin\Versao.bat
REM call "Integração Fornecedor - Portal Cronos\bin\Versao.bat"
call Versao.bat

REM http://stackoverflow.com/questions/23730887/why-is-rt-jar-not-part-of-the-class-path-system-property: 
REM 	"rt.jar doesn't need to be in the classpath, since it is already in the bootclasspath. 
REM It is safe to remove it from your classpath."
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

