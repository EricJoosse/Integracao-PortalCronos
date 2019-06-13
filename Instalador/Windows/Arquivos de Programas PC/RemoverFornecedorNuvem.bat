cls
@echo off

REM Windows, por default, usa o charset WINDOWS-1252 (ou outra variação, dependendo da lingua), 
REM que é uma extensão do ISO-8859-1. 
REM Mesmo assim, não se compara com UTF-8! O Linux, por default, usa o charset UTF-8. 
REM Eclipse herda o charset de Windows por default. 
REM "chcp 1252>nul" é necessário para evitar que DOS não reconhece acentos Portugueses no caminho 
REM "Integração Fornecedor - Portal Cronos" em alguns ou talvez até em todos os servidores:

chcp 1252>nul

cd\
cd "Arquivos de Programas PC"


echo.
echo.
SET /P nmFornecedor=Digite o nome fantasia da empresa cliente: 
IF "%nmFornecedor%"=="" GOTO ErroNmFornecedor
if not "%nmFornecedor%"=="%nmFornecedor: =%" goto ErroEspacosNmFornecedor
GOTO PularErroNmFornecedor
:ErroEspacosNmFornecedor
echo MSGBOX "Erro: não pode ter nenhum espaço em branco no nome! Remoção deste cliente não concluída!" > %temp%\TEMPmessage.vbs
call %temp%\TEMPmessage.vbs
del %temp%\TEMPmessage.vbs /f /q
REM Fechar o script chamador também: 
exit
:ErroNmFornecedor
echo MSGBOX "Erro: nome não informado! Remoção deste cliente não concluída!" > %temp%\TEMPmessage.vbs
call %temp%\TEMPmessage.vbs
del %temp%\TEMPmessage.vbs /f /q
REM Fechar o script chamador também: 
exit
:PularErroNmFornecedor


if not exist "C:/Arquivos de Programas PC/Integração Fornecedor - Portal Cronos/conf/Integração APS - Portal Cronos.%nmFornecedor%.properties" (
    echo.
    echo          Nome inválido!
    echo.
    
    echo MSGBOX "Nome inválido!" > %temp%\TEMPmessage.vbs
    call %temp%\TEMPmessage.vbs
    del %temp%\TEMPmessage.vbs /f /q
    exit
)

if exist FornecedorAdicionalNuvem.Windows2008_R2.TaskSchedule.xml del /f /q FornecedorAdicionalNuvem.Windows2008_R2.TaskSchedule.xml 


cd "Integração Fornecedor - Portal Cronos"
cd conf
copy TemplateNuvemAPS.properties "Integração APS - Portal Cronos.%nmFornecedor%.properties"


cd\
cd "Arquivos de Programas PC"
call "Integração Fornecedor - Portal Cronos\bin\Inicializacoes.bat"
call "Integração Fornecedor - Portal Cronos\bin\Versao.bat"
call "Integração Fornecedor - Portal Cronos\bin\CaminhoJRE.bat" RemovedorFornecedorNuvem.log RemovedorFornecedorNuvem %nmFornecedor%

set arquivoLog="RemovedorFornecedorNuvem.log"
set tamanhoArqLog=0

FOR /F "usebackq" %%A IN ('%arquivoLog%') DO set tamanhoArqLog=%%~zA

if %tamanhoArqLog% GTR 0 (
    echo.
    echo          A remoção falhou!
    echo.
    
    echo MSGBOX "A remoção falhou!" > %temp%\TEMPmessage.vbs
    call %temp%\TEMPmessage.vbs
    del %temp%\TEMPmessage.vbs /f /q
    start notepad RemovedorFornecedorNuvem.log
) else (
  SCHTASKS /Create /TN "Integração Portal Cronos - %nmFornecedor%" /XML "C:/Arquivos de Programas PC/FornecedorAdicionalNuvem.Windows2008_R2.TaskSchedule.xml"
  SCHTASKS /Run /TN "Integração Portal Cronos - %nmFornecedor%"

  cd\
  cd "Arquivos de Programas PC"
  if exist FornecedorAdicionalNuvem.Windows2008_R2.TaskSchedule.xml del /f /q FornecedorAdicionalNuvem.Windows2008_R2.TaskSchedule.xml

  start notepad "Integração Fornecedor - Portal Cronos/conf/Integração APS - Portal Cronos.%nmFornecedor%.properties"
)

REM /B para não fechar o script chamador (Primeira_Instalacao_Versao_Windows.bat):  
exit /B 0


:FIM
