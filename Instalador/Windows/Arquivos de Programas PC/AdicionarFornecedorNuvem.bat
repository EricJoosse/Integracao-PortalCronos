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

call "Integração Fornecedor - Portal Cronos\bin\VerificarUsuarioAdministrador.bat"

echo      =================================================
echo    ^| TELA PROVISORIA PARA INCLUSAO DE INSTANCIA NOVA ^|
echo    ^| DO INTEGRADOR PCRONOS - APS CLOUD               ^|
echo      =================================================
:PerguntaNmFornecedor
echo. 
echo.
echo Nome fantasia da empresa cliente a ser adicionada:
echo.
SET /P nmFornecedor=Digite o nome fantasia ou C (= Cancelar), e em seguida clique na tecla ^<Enter^>: 
IF "%nmFornecedor%"=="C" exit
IF "%nmFornecedor%"=="c" exit
IF "%nmFornecedor%"=="" GOTO ErroNmFornecedor
if not "%nmFornecedor%"=="%nmFornecedor: =%" goto ErroEspacosNmFornecedor
GOTO PularErroNmFornecedor
:ErroEspacosNmFornecedor
echo MSGBOX "Erro: não pode ter nenhum espaço em branco no nome! Adicionamento da instância não efetuado!" > %temp%\TEMPmessage.vbs
call %temp%\TEMPmessage.vbs
del %temp%\TEMPmessage.vbs /f /q
cls
goto PerguntaNmFornecedor
:ErroNmFornecedor
echo MSGBOX "Erro: nome não informado! Adicionamento da instância não efetuado!" > %temp%\TEMPmessage.vbs
call %temp%\TEMPmessage.vbs
del %temp%\TEMPmessage.vbs /f /q
cls
goto PerguntaNmFornecedor
:PularErroNmFornecedor


if exist "C:/Arquivos de Programas PC/Integração Fornecedor - Portal Cronos/conf/Integração APS - Portal Cronos.%nmFornecedor%.properties" (
    echo.
    echo          Este fornecedor j  foi adicionado!
    echo.
    
    echo MSGBOX "Este fornecedor já foi adicionado!" > %temp%\TEMPmessage.vbs
    call %temp%\TEMPmessage.vbs
    del %temp%\TEMPmessage.vbs /f /q
    exit /B 0
)

cd\
cd "Arquivos de Programas PC"
if exist FornecedorAdicionalNuvem.Windows2008_R2.TaskSchedule.xml del /f /q FornecedorAdicionalNuvem.Windows2008_R2.TaskSchedule.xml 


cd "Integração Fornecedor - Portal Cronos"
cd conf
copy TemplateNuvemAPS.properties "Integração APS - Portal Cronos.%nmFornecedor%.properties"


cd\
cd "Arquivos de Programas PC"
call "Integração Fornecedor - Portal Cronos\bin\Inicializacoes.bat"
call "Integração Fornecedor - Portal Cronos\bin\Versao.bat"
call "Integração Fornecedor - Portal Cronos\bin\CaminhoJRE.bat" AdicionadorFornecedorNuvem.log AdicionadorFornecedorNuvem %nmFornecedor%

set arquivoLog="AdicionadorFornecedorNuvem.log"
set tamanhoArqLog=0

FOR /F "usebackq" %%A IN ('%arquivoLog%') DO set tamanhoArqLog=%%~zA

if %tamanhoArqLog% GTR 0 (
    echo.
    echo          O adicionamento da inst ncia nova falhou!
    echo.
    
    echo MSGBOX "O adicionamento da instância nova falhou!" > %temp%\TEMPmessage.vbs
    call %temp%\TEMPmessage.vbs
    del %temp%\TEMPmessage.vbs /f /q
    start notepad AdicionadorFornecedorNuvem.log
) else (
  SCHTASKS /Create /TN "Integração Portal Cronos - %nmFornecedor%" /XML "C:/Arquivos de Programas PC/FornecedorAdicionalNuvem.Windows2008_R2.TaskSchedule.xml"
  SCHTASKS /Run /TN "Integração Portal Cronos - %nmFornecedor%"

  if exist AdicionadorFornecedorNuvem.log del /f /q AdicionadorFornecedorNuvem.log 
  cd\
  cd "Arquivos de Programas PC"
  if exist FornecedorAdicionalNuvem.Windows2008_R2.TaskSchedule.xml del /f /q FornecedorAdicionalNuvem.Windows2008_R2.TaskSchedule.xml

  start notepad "Integração Fornecedor - Portal Cronos/conf/Integração APS - Portal Cronos.%nmFornecedor%.properties"
)

REM /B para não fechar os scripts chamadores (Instalador_Integrador.bat e Instalador_Monitorador.bat):  
exit /B 0


:FIM
