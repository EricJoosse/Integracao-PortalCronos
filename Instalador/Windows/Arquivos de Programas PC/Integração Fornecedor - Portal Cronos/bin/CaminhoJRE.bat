


REM Limpar CLASSPATH:
REM set CLASSPATH=

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

cd\
cd "Arquivos de Programas PC"
cd "Integração Fornecedor - Portal Cronos"


if "%1"=="Desinstalador.log" (
  if exist Desinstalador.log del /f /q Desinstalador.log 
) else if "%1"=="Instalador.log" (
  if exist Instalador.log del /f /q Instalador.log 
) else if "%1"=="TestadorUnitario.log" (
  if exist TestadorUnitario.log del /f /q TestadorUnitario.log 
) else if "%1"=="AdicionadorFornecedorNuvem.log" (
  if exist AdicionadorFornecedorNuvem.log del /f /q AdicionadorFornecedorNuvem.log 
) else if "%1"=="RemovedorFornecedorNuvem.log" (
  if exist RemovedorFornecedorNuvem.log del /f /q RemovedorFornecedorNuvem.log 
) else if "%1"=="GeradorTarefaWindowsNaoNuvem.log" (
  if exist GeradorTarefaWindowsNaoNuvem.log del /f /q GeradorTarefaWindowsNaoNuvem.log 
)
REM Não apagar o arquivo Job15a15min.log aqui, pois neste lugar não verificamos se o tamanho 
REM deste arquivo é zero ou se tem conteúdo  

REM Usar o caminho completo do JRE para o caso que tiver 2 JRE´s no mesmo servidor 
REM e o caminho do outro JRE está na primeira posição no PATH de DOS:

REM Se tiver um parentese dentro do path, "set path" não funciona dentro de if´s e else´s, 
REM como na seguinte tentativa, então precisamos de goto´s ao invés de if´s e else´s:
REM 
REM if %idFornecedor% == 30 (
REM     set path=C:\Program Files ^(x86^)\Java\jre1.8.0_191\bin;%path%
REM ) else (
REM     set path=C:\Program Files\Java\jre1.8.0_92\bin;%path%
REM )

if exist C:/"Program Files (x86)"/Java/jre1.8.0_111/bin/java.exe (
    goto PathPadeirao
) else if exist C:/"Program Files (x86)"/Java/jre1.8.0_191/bin/java.exe (
    goto PathApsCloud
) else if exist C:/"Program Files (x86)"/Java/jre1.8.0_211/bin/java.exe (
    goto PathAtacamaxNaoNuvem
) else if exist C:/"Program Files"/Java/jre1.8.0_231/bin/java.exe (
    goto PathProlac
) else if exist C:/"Program Files"/Java/jre1.8.0_92/bin/java.exe (
    goto PathOutros
) else (
    echo MSGBOX "Erro! O JRE não foi encontrado!" > %temp%\TEMPmessage.vbs
    exit
)

:PathPadeirao
set path=C:\Program Files (x86)\Java\jre1.8.0_111\bin;%path%
REM Errado em versões < 2.1.2B: set path=%path%;C:\Program Files (x86)\Java\jre1.8.0_111\bin
C:/"Program Files (x86)"/Java/jre1.8.0_111/bin/java.exe -cp integr-fornecedor-%versaoIntegrador%.jar pcronos.integracao.fornecedor.%2 %3 %4 >> %1
goto PularPathOutros

:PathApsCloud
set path=C:\Program Files (x86)\Java\jre1.8.0_191\bin;%path%
C:/"Program Files (x86)"/Java/jre1.8.0_191/bin/java.exe -cp integr-fornecedor-%versaoIntegrador%.jar pcronos.integracao.fornecedor.%2 %3 %4 >> %1
goto PularPathOutros

:PathAtacamaxNaoNuvem
set path=C:\Program Files (x86)\Java\jre1.8.0_211\bin;%path%
C:/"Program Files (x86)"/Java/jre1.8.0_211/bin/java.exe -cp integr-fornecedor-%versaoIntegrador%.jar pcronos.integracao.fornecedor.%2 %3 %4 >> %1
goto PularPathOutros

:PathProlac
set path=C:\Program Files\Java\jre1.8.0_231\bin;%path%
C:/"Program Files"/Java/jre1.8.0_231/bin/java.exe -cp integr-fornecedor-%versaoIntegrador%.jar pcronos.integracao.fornecedor.%2 %3 %4 >> %1
goto PularPathOutros

:PathOutros
set path=C:\Program Files\Java\jre1.8.0_92\bin;%path%
REM Errado em versões < 2.1.2B: set path=%path%;C:\Program Files\Java\jre1.8.0_92\bin
C:/"Program Files"/Java/jre1.8.0_92/bin/java.exe -cp integr-fornecedor-%versaoIntegrador%.jar pcronos.integracao.fornecedor.%2 %3 %4 >> %1
:PularPathOutros





