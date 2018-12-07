SETLOCAL

echo ComponenteTestador.bat entrado

call Versao.bat

pause

chcp 1252>nul
cd\
cd "Arquivos de Programas PC"
cd "Integração Fornecedor - Portal Cronos"


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
