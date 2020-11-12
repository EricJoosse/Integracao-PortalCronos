cls
@echo off

REM Windows, por default, usa o charset WINDOWS-1252 (ou outra variação, dependendo da lingua), 
REM que é uma extensão do ISO-8859-1. 
REM Mesmo assim, não se compara com UTF-8! O Linux, por default, usa o charset UTF-8. 
REM Eclipse herda o charset de Windows por default. 
REM "chcp 1252>nul" é necessário para evitar que DOS não reconhece acentos Portugueses no caminho 
REM "Integração Fornecedor - Portal Cronos" em alguns ou talvez até em todos os servidores:

chcp 1252>nul

echo.
echo Subarquivo .bat entrado: 
echo Param 0 = %0
echo Param 1 = %1
echo Param 2 = %2
echo Param 3 = %3

if "%1"=="m" (
    set idFornecedor=-1
    goto PularPerguntaIdFornecedor
) else if "%1"=="i" (
    goto PerguntaIdFornecedor
) else (
    echo.
    ECHO Erro: apenas os tipos de instalação ^"i^" e ^"m^" estão permitidos!
    echo.

    echo MSGBOX "Erro: apenas os tipos de instalação ""i"" e ""m"" estão permitidos!" > %temp%\TEMPmessage.vbs
    call %temp%\TEMPmessage.vbs
    del %temp%\TEMPmessage.vbs /f /q
REM Fechar o script chamador também: 
    exit
)

:PerguntaIdFornecedor
echo.
echo ID da empresa fornecedora:
echo (Entre em contato com o setor Desenvolvimento do Portal Cronos para obter este ID)
echo.

SET /P idFornecedor=Digite este ID ou C (= Cancelar) + a tecla ^<Enter^>: 
IF "%idFornecedor%"=="C" GOTO CancelarInstalacao
IF "%idFornecedor%"=="c" GOTO CancelarInstalacao
IF "%idFornecedor%"=="" GOTO ErroIdFornecedor
GOTO PularErroIdFornecedor
:ErroIdFornecedor
echo MSGBOX "Erro: ID do fornecedor não informado!" > %temp%\TEMPmessage.vbs
call %temp%\TEMPmessage.vbs
del %temp%\TEMPmessage.vbs /f /q
cls
goto PerguntaIdFornecedor
:PularErroIdFornecedor
:PularPerguntaIdFornecedor


echo Pergunta Passada

ENDLOCAL
REM /B para não fechar os scripts chamadores (Instalador_Integrador.bat e Instalador_Monitorador.bat):  
exit /B 0


