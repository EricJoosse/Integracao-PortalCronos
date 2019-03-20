SETLOCAL

REM "chcp 1252>nul" é necessário para evitar que DOS não reconhece acentos Portugueses no caminho 
REM "Integração Fornecedor - Portal Cronos" em alguns ou talvez até em todos os servidores:

chcp 1252>nul

echo.
echo ComponenteTestador.bat entrado: 
echo Param 0 = %0
echo Param 1 = %1
echo Param 2 = %2
echo Param 3 = %3
echo Versão = %versaoIntegrador%

REM pause

cd\

REM Se testar dentro do Eclipse ao invés de nos servidores:
if exist C:/PCronos/"Integração Fornecedor - Portal Cronos"/Instalador/Windows/"Arquivos de Programas PC"/InstalarManualTI.bat (
  cd PCronos
  cd "Integração Fornecedor - Portal Cronos"
  cd Instalador
  cd Windows
  cd "Arquivos de Programas PC"
  cd "Integração Fornecedor - Portal Cronos"
) else (  
  cd "Arquivos de Programas PC"
  cd "Integração Fornecedor - Portal Cronos"
)

REM "pwd" em Linux = "%cd% em DOS:
echo %cd%

if "%1"=="Desinstalador.log" (
  if exist Desinstalador.log del /f /q Desinstalador.log 
) else if "%1"=="Instalador.log" (
  if exist Instalador.log del /f /q Instalador.log 
) else if "%1"=="TesteTresParam.log" (
    echo MSGBOX "if TesteTresParam.log entrado corretamente!" > %temp%\TEMPmessage.vbs
    call %temp%\TEMPmessage.vbs
    del %temp%\TEMPmessage.vbs /f /q
) else if "%1"=="TestadorUnitario.log" (
  if exist TestadorUnitario.log del /f /q TestadorUnitario.log 
)

set path=C:\Program Files\Java\jre1.8.0_92\bin;%path%
C:/"Program Files"/Java/jre1.8.0_92/bin/java.exe -cp integr-fornecedor-%versaoIntegrador%.jar pcronos.integracao.fornecedor.%2 %3 >> %1


