
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

call bin\CaminhoJRE.bat

REM Usar o caminho completo do JRE para o caso que tiver 2 JRE´s no mesmo servidor 
REM e o caminho do outro JRE está na primeira posição no PATH de DOS:

REM Se tiver um parentese dentro do path, "set path" não funciona dentro de if´s e else´s, 
REM então precisamos de goto´s:


if exist C:/"Program Files (x86)"/Java/jre1.8.0_191/bin/java.exe (
    goto PathProlac
) else if exist C:/"Program Files (x86)"/Java/jre1.8.0_111/bin/java.exe (
    goto PathPadeirao
) else if exist C:/"Program Files"/Java/jre1.8.0_92/bin/java.exe (
    goto PathOutros
) else (
    echo MSGBOX "Erro! O JRE não foi encontrado!" > %temp%\TEMPmessage.vbs
    exit
)

:PathProlac
set path=C:\Program Files (x86)\Java\jre1.8.0_191\bin;%path%
REM         Errado em versões < 2.1.2B: set path=%path%;C:\Program Files (x86)\Java\jre1.8.0_191\bin
C:/"Program Files (x86)"/Java/jre1.8.0_191/bin/java.exe -cp integr-fornecedor-%versaoIntegrador%.jar pcronos.integracao.fornecedor.TestadorSnippets >> TestadorUnitario.log
goto PularPathOutros

:PathPadeirao
set path=C:\Program Files (x86)\Java\jre1.8.0_111\bin;%path%
C:/"Program Files (x86)"/Java/jre1.8.0_111/bin/java.exe -cp integr-fornecedor-%versaoIntegrador%.jar pcronos.integracao.fornecedor.TestadorSnippets >> TestadorUnitario.log
goto PularPathOutros

:PathOutros
set path=C:\Program Files\Java\jre1.8.0_92\bin;%path%
REM         Errado em versões < 2.1.2B: set path=%path%;C:\Program Files\Java\jre1.8.0_92\bin
C:/"Program Files"/Java/jre1.8.0_92/bin/java.exe -cp integr-fornecedor-%versaoIntegrador%.jar pcronos.integracao.fornecedor.TestadorSnippets >> TestadorUnitario.log
:PularPathOutros



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

