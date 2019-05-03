
REM Fazer o seguinte apenas nos servidores dos fornecedores e não dentro do Eclipse:
REM O seguinte é necessário se executar o .bat usando "Executar como Administrador" ou se rodar 
REM dentro do Task Scheduler: 
REM Windows, por default, usa o charset WINDOWS-1252 (ou outra variação, dependendo da lingua), 
REM que é uma extensão do ISO-8859-1. 
REM Mesmo assim, não se compara com UTF-8! O Linux, por default, usa o charset UTF-8. 
REM Eclipse herda o charset de Windows por default. 
REM "chcp 1252>nul" é necessário para evitar que DOS não reconhece acentos Portugueses no caminho 
REM "Integração Fornecedor - Portal Cronos" em alguns ou talvez até em todos os servidores:

chcp 1252>nul

if not exist C:/PCronos/"Integração Fornecedor - Portal Cronos"/Instalador/Windows/"Arquivos de Programas PC"/InstalarManualTI.bat (
  cd\
  cd "Arquivos de Programas PC"
  cd "Integração Fornecedor - Portal Cronos"
)

REM goto TesteVersao
REM goto TesteParamDosOptional
goto TesteQualquerSnippetJava

REM ================ Teste de chamada de arqs .bat em subdirs (testado, funcionou): ========================================

echo MSGBOX "Teste de chamada de arqs .bat em subdirs" > %temp%\TEMPmessage.vbs
call %temp%\TEMPmessage.vbs
del %temp%\TEMPmessage.vbs /f /q

pause
exit

REM ==================== Outros testes: =====================================================================================
REM ==================== 1. Teste de qualquer snippet de Java ===============================================================
REM ==================== 2. Teste Parâmetro optional CaminhoJRE.bat (testado, funcionou): ===================================

:TesteParamDosOptional
:TesteQualquerSnippetJava

cls
@echo off

call bin\Inicializacoes.bat
call bin\Versao.bat
call bin\ComponenteTestador.bat TestadorUnitario.log TestadorSnippets
start notepad TestadorUnitario.log

ENDLOCAL
REM !!!!!! Não pode comentar o pause abaixo, pois alguns eventuais erros aparecem na tela de DOS, e não no arquivo TestadorUnitario.log!
pause
exit


REM ==================== Teste Variável Versão: ===============================================================

:TesteVersao

SETLOCAL

call bin\Versao.bat
C:/"Program Files"/Java/jre1.8.0_92/bin/java.exe -cp integr-fornecedor-%versaoIntegrador%.jar pcronos.integracao.fornecedor.TestadorSnippets >> TestadorUnitario.log

ENDLOCAL
pause
exit

