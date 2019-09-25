cls
@echo off

REM Windows, por default, usa o charset WINDOWS-1252 (ou outra variação, dependendo da lingua), 
REM que é uma extensão do ISO-8859-1. 
REM Mesmo assim, não se compara com UTF-8! O Linux, por default, usa o charset UTF-8. 
REM Eclipse herda o charset de Windows por default. 
REM "chcp 1252>nul" é necessário para evitar que DOS não reconhece acentos Portugueses no caminho 
REM "Integração Fornecedor - Portal Cronos" em alguns ou talvez até em todos os servidores:

chcp 1252>nul

if %tipoInstalacao% == 2 (
   set idFornecedor=1995
   goto PularPerguntaIdFornecedor
)

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
echo Favor entrar em contato com o setor Desenvolvimento do Portal Cronos para obter o ID da empresa fornecedora.
echo.

SET /P idFornecedor=Favor digitar o ID da empresa fornecedora: 
IF "%idFornecedor%"=="" GOTO ErroIdFornecedor
GOTO PularErro
:ErroIdFornecedor
echo MSGBOX "Erro: ID do fornecedor não informado! Instalação não concluída!!" > %temp%\TEMPmessage.vbs
call %temp%\TEMPmessage.vbs
del %temp%\TEMPmessage.vbs /f /q
REM Fechar o script chamador também: 
exit
:PularErro
:PularPerguntaIdFornecedor


REM Alternativo antigo: 
REM 
REM if %idFornecedor% == 30 (
REM     goto PathProlac
REM ) else (
REM     goto PathOutros
REM )


call "Integração Fornecedor - Portal Cronos\bin\Inicializacoes.bat"
call "Integração Fornecedor - Portal Cronos\bin\Versao.bat"
call "Integração Fornecedor - Portal Cronos\bin\CaminhoJRE.bat" Instalador.log Instalador %idFornecedor% %2



set arquivoLog="Instalador.log"
set tamanhoArqLog=0

FOR /F "usebackq" %%A IN ('%arquivoLog%') DO set tamanhoArqLog=%%~zA

if %tamanhoArqLog% GTR 0 (
    echo.
    echo          A instalação falhou!
    echo.
    
    echo MSGBOX "A instalação falhou!" > %temp%\TEMPmessage.vbs
    call %temp%\TEMPmessage.vbs
    del %temp%\TEMPmessage.vbs /f /q
    start notepad Instalador.log
) else if %tipoInstalacao% == 1 (
    if exist Instalador.log del /f /q Instalador.log 
    echo.
    echo          Primeira fase da instalação concluida!
    echo.
    
    echo MSGBOX "Primeira fase da instalação concluida!" > %temp%\TEMPmessage.vbs
    call %temp%\TEMPmessage.vbs
    del %temp%\TEMPmessage.vbs /f /q
) else (
    if exist Instalador.log del /f /q Instalador.log 
)


ENDLOCAL
REM /B para não fechar os scripts chamadores (Instalador_Integrador.bat e Instalador_Monitorador.bat):  
exit /B 0


