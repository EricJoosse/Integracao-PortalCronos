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
    goto PerguntaIdMonitorador
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

:PerguntaIdMonitorador
echo.
echo.
echo ID da empresa fornecedora:
echo.
echo -1 = Monitorador do Servidor de Aplicação
echo -2 = Monitorador do Servidor de Banco
echo -3 = Monitorador do Servidor de Banco de Contingência
echo  C = Cancelar instalação (com rollback)
echo.

SET /P idFornecedor=Digite -1, -2, -3 ou C + a tecla ^<Enter^>: 
IF "%idFornecedor%"=="C" GOTO CancelarInstalacao
IF "%idFornecedor%"=="c" GOTO CancelarInstalacao
IF "%idFornecedor%"=="" GOTO ErroIdMonitorador
IF "%idFornecedor%"=="-1" GOTO PularErroIdMonitorador 
IF "%idFornecedor%"=="-2" GOTO PularErroIdMonitorador 
IF "%idFornecedor%"=="-3" GOTO PularErroIdMonitorador 
echo MSGBOX "Erro: Opção inválida!" > %temp%\TEMPmessage.vbs
call %temp%\TEMPmessage.vbs
del %temp%\TEMPmessage.vbs /f /q
REM SET /P doesn't change the content of a variable, if the user doesn't enter text:
SET "idFornecedor="
cls
goto PerguntaIdMonitorador
:ErroIdMonitorador
echo MSGBOX "Erro: ID do fornecedor não informado!" > %temp%\TEMPmessage.vbs
call %temp%\TEMPmessage.vbs
del %temp%\TEMPmessage.vbs /f /q
cls
goto PerguntaIdMonitorador
:PularErroIdMonitorador


:PerguntaIdFornecedor
echo.
echo.
echo Entre em contato com o setor Desenvolvimento do Portal Cronos 
echo para obter o ID da empresa fornecedora:
echo.

SET /P idFornecedor=Digite este ID ou a tecla C (= Cancelar) + a tecla ^<Enter^>: 
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
    copy /Y Instalador.log C:\temp\Instalador.log
    start notepad C:\temp\Instalador.log
    del /f /q C:\temp\"Instalador do Integrador Fornecedores - Portal Cronos.*.exe"

    echo.
    echo          A instalação falhou! Foi feito rollback da instalação!
    echo.
    
    echo MSGBOX "A instalação falhou! Foi feito rollback da instalação!" > %temp%\TEMPmessage.vbs
    call %temp%\TEMPmessage.vbs
    del %temp%\TEMPmessage.vbs /f /q

REM O seguinte consegue remover todos os arquivos no diretório "Arquivos de Programas PC",
REM até este arquivo .bat, porém não consegue remover o diretório "Arquivos de Programas PC" :
REM ????????? Funcionou quando usei vbs acima imediatamente antes disso,
REM e quando movi todos os outros camandos DOS mais para cima para antes do echo !!!!!!!!!
    cd\
    rmdir /s /q "Arquivos de Programas PC"
REM Abortar a instalação, então fechar este arquivo .bat atual 
REM e também fechar o arquivo .bat chamador (Instalador_Integrador.bat e Instalador_Monitorador.bat):     
    exit
) else (
    if exist Instalador.log del /f /q Instalador.log 
)


ENDLOCAL
REM /B para não fechar os scripts chamadores (Instalador_Integrador.bat e Instalador_Monitorador.bat):  
exit /B 0


goto PularCancelarInstalacao
:CancelarInstalacao

REM Foi testado via teste integrado completo que o seguinte funciona, mesmo que deixar o arquivo selecionado 
REM após o duplo-clique para executar a instalação:
REM Foi testado que o seguinte funciona também no caso que o diretório for C:\Temp\ ao invés de C:\temp\:
del /f /q C:\temp\"Instalador do Integrador Fornecedores - Portal Cronos.*.exe"

REM Não fazer cls aqui, para poder visualizar eventuais erros
echo.
echo          Rollback da instalação concluida!
echo.

echo MSGBOX "Rollback da instalação concluida!" > %temp%\TEMPmessage.vbs
call %temp%\TEMPmessage.vbs
del %temp%\TEMPmessage.vbs /f /q

REM ================ Remover diretório "Arquivos de Programas PC": ========================================

REM O seguinte consegue remover todos os arquivos no diretório "Arquivos de Programas PC",
REM até este arquivo .bat, porém não consegue remover o diretório "Arquivos de Programas PC" :
REM ????????? Funcionou quando usei vbs acima antes disso !!!!!!!!!
cd\
rmdir /s /q "Arquivos de Programas PC"
REM Neste caso também fechar os scripts chamadores (Instalador_Integrador.bat ou Instalador_Monitorador.bat):
exit
:PularCancelarInstalacao



