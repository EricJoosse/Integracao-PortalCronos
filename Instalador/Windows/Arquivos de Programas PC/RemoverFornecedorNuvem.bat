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


REM echo.
REM echo.
REM echo Tem certeza que deseja remover a instância de um cliente?
REM echo.
REM echo IMPORTANTE: É recomend vel primeiro fazer backup das configura‡oes do cliente!
REM echo.
REM 
REM SET /P temCerteza=Digite S (=Sim) ou N (=Nao):  
REM IF "%temCerteza%"=="" GOTO ErroTemCerteza
REM if "%temCerteza%"=="S" (
REM GOTO PularErroTemCerteza
REM ) else (
REM REM Fechar o script chamador também: 
REM    exit
REM )
REM :ErroTemCerteza
REM echo MSGBOX "Erro: confirmação não informada! Remoção de nenhuma instância efetuada!" > %temp%\TEMPmessage.vbs
REM call %temp%\TEMPmessage.vbs
REM del %temp%\TEMPmessage.vbs /f /q
REM REM Fechar o script chamador também: 
REM exit
REM :PularErroTemCerteza

REM cls

echo.
echo.
SET /P nmFornecedor=Digite o nome fantasia da empresa cliente: 
IF "%nmFornecedor%"=="" GOTO ErroNmFornecedor
if not "%nmFornecedor%"=="%nmFornecedor: =%" goto ErroEspacosNmFornecedor
GOTO PularErroNmFornecedor
:ErroEspacosNmFornecedor
echo MSGBOX "Erro: não pode ter nenhum espaço em branco no nome! Remoção da instância não efetuada!" > %temp%\TEMPmessage.vbs
call %temp%\TEMPmessage.vbs
del %temp%\TEMPmessage.vbs /f /q
REM Fechar o script chamador também: 
exit
:ErroNmFornecedor
echo MSGBOX "Erro: nome não informado! Remoção da instância não efetuada!" > %temp%\TEMPmessage.vbs
call %temp%\TEMPmessage.vbs
del %temp%\TEMPmessage.vbs /f /q
REM Fechar o script chamador também: 
exit
:PularErroNmFornecedor



REM set ClienteExiste=0
REM for /f "tokens=2 delims=\" %%x in ('SCHTASKS /QUERY /FO:LIST ^| FINDSTR "Integração Portal Cronos - %nmFornecedor%"') do set ClienteExiste=1
REM if %ClienteExiste% == 0 (
REM     echo.
REM     echo          Nome inv lido!
REM     echo.
REM     
REM     echo MSGBOX "Nome inválido!" > %temp%\TEMPmessage.vbs
REM     call %temp%\TEMPmessage.vbs
REM     del %temp%\TEMPmessage.vbs /f /q
REM     exit
REM )


if not exist "C:/Arquivos de Programas PC/Integração Fornecedor - Portal Cronos/conf/Integração APS - Portal Cronos.%nmFornecedor%.properties" (
    echo.
    echo          Nome inv lido!
    echo.
    
    echo MSGBOX "Nome inválido!" > %temp%\TEMPmessage.vbs
    call %temp%\TEMPmessage.vbs
    del %temp%\TEMPmessage.vbs /f /q
    exit
)


REM Remover as coisas na seguinte ordem:
REM  1. primeiro remover o Task Schedule,
REM  2. em seguida remover a entrada no menu de Windows,
REM  3. no final remover o arquivo de configuração .properties: 


SCHTASKS /End /TN "Integração Portal Cronos - %nmFornecedor%"
SCHTASKS /Delete /TN "Integração Portal Cronos - %nmFornecedor%" /F



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
    echo          A remo‡ao da inst ncia falhou!
    echo.
    
    echo MSGBOX "A remoção da instância falhou!" > %temp%\TEMPmessage.vbs
    call %temp%\TEMPmessage.vbs
    del %temp%\TEMPmessage.vbs /f /q
    start notepad RemovedorFornecedorNuvem.log
) else (
  cd\
  cd "Arquivos de Programas PC"
  cd "Integração Fornecedor - Portal Cronos"
  cd conf
  del /f /q "Integração APS - Portal Cronos.%nmFornecedor%.properties"
)


REM /B para não fechar possíveis scripts chamadores no futuro:  
exit /B 0


:FIM
