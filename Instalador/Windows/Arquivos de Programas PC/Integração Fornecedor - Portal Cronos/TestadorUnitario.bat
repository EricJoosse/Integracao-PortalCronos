
REM goto OutrosTestes
goto TesteVersao

REM ================ Teste de chamada de arqs .bat em subdirs (testado, funcionou): ========================================

echo MSGBOX "Teste de chamada de arqs .bat em subdirs" > %temp%\TEMPmessage.vbs
call %temp%\TEMPmessage.vbs
del %temp%\TEMPmessage.vbs /f /q

pause
exit

REM ==================== Outros testes: ===============================================================

:OutrosTestes

cls
@echo off

call bin\CaminhoJRE.bat TestadorUnitario.log TestadorSnippets

ENDLOCAL
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

