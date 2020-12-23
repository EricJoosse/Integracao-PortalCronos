
cls
@echo off

REM "chcp 1252>nul" é necessário para evitar que DOS não reconhece acentos Portugueses no caminho 
REM "Integração Fornecedor - Portal Cronos" em alguns ou talvez até em todos os servidores:

chcp 1252>nul


echo.
echo Favor entrar em contato com o setor Desenvolvimento do Portal Cronos para obter o ID da empresa fornecedora.
echo Digite -1, -2 ou -3 no caso de upgrade do Monitorador no servidor de de aplicação, base de dados, 
echo ou base de dados de contingência.
echo.

SET /P idFornecedor=Favor digitar o ID da empresa fornecedora: 
IF "%idFornecedor%"=="" GOTO ErroIdFornecedor
GOTO PularErro
:ErroIdFornecedor
echo MSGBOX "Erro: ID do fornecedor não informado! Upgrade para 3.1.0 não concluído!!" > %temp%\TEMPmessage.vbs
call %temp%\TEMPmessage.vbs
del %temp%\TEMPmessage.vbs /f /q
REM Fechar o script chamador também: 
exit
:PularErro


cd\
cd "Arquivos de Programas PC"
if exist "Integração Portal Cronos - Fornecedor.Windows.2008_R2.TaskSchedule.xml" del /f /q "Integração Portal Cronos - Fornecedor.Windows.2008_R2.TaskSchedule.xml"

SCHTASKS /End /TN "Integração Portal Cronos - Fornecedor"
SCHTASKS /Delete /TN "Integração Portal Cronos - Fornecedor" /F

cd\
cd "Arquivos de Programas PC"
REM ?????? call "Integração Fornecedor - Portal Cronos\bin\Inicializacoes.bat"
call "Integração Fornecedor - Portal Cronos\bin\Versao.bat"
call "Integração Fornecedor - Portal Cronos\bin\CaminhoJRE.bat" GeradorTarefaWindowsNaoNuvem.log GeradorTarefaWindowsNaoNuvem %idFornecedor%


set arquivoLog="GeradorTarefaWindowsNaoNuvem.log"
set tamanhoArqLog=0

FOR /F "usebackq" %%A IN ('%arquivoLog%') DO set tamanhoArqLog=%%~zA

if %tamanhoArqLog% GTR 0 (
    echo.
    echo          O adicionamento da inst ncia nova falhou!
    echo.
    
    echo MSGBOX "O upgrade para 3.1.0 falhou!" > %temp%\TEMPmessage.vbs
    call %temp%\TEMPmessage.vbs
    del %temp%\TEMPmessage.vbs /f /q
    start notepad GeradorTarefaWindowsNaoNuvem.log
) else (
    SCHTASKS /Create /TN "Integração Portal Cronos - Fornecedor" /XML "C:/Arquivos de Programas PC/Integração Portal Cronos - Fornecedor.Windows.2008_R2.TaskSchedule.xml"
    SCHTASKS /Run /TN "Integração Portal Cronos - Fornecedor"
    if exist GeradorTarefaWindowsNaoNuvem.log del /f /q GeradorTarefaWindowsNaoNuvem.log 
)

cd\
cd "Arquivos de Programas PC"
if exist "Integração Portal Cronos - Fornecedor.Windows.2008_R2.TaskSchedule.xml" del /f /q "Integração Portal Cronos - Fornecedor.Windows.2008_R2.TaskSchedule.xml"
del /f /q UpgradeNaoNuvem3.1.0.bat
