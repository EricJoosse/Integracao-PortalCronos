
cls
@echo off

chcp 1252>nul

echo.
echo          Desinstalando...................
echo.

REM echo MSGBOX "Desinstalando..." > %temp%\TEMPmessage.vbs
REM call %temp%\TEMPmessage.vbs
REM del %temp%\TEMPmessage.vbs /f /q
  
    set drive=C:
REM set drive=D:

REM ================ Remover Task no Windows Scheduler ========================================

SCHTASKS /End /TN "Integração Portal Cronos - Fornecedor"
SCHTASKS /Delete /TN "Integração Portal Cronos - Fornecedor" /F


REM ================ Remover subdiretórios de "Arquivos de Programas PC" ========================================

cd\
cd "Arquivos de Programas PC"
rmdir /s /q "Integração Fornecedor - Portal Cronos"
for %%f in (*) do if not %%~xf==.bat del /f /q "%%f"
del /f /q Primeira_Instalacao_Versao_Windows.bat

REM ================ Remover diretório Temp ========================================

cd\
cd temp
rmdir /s /q PortalCronos
cd\


REM ================ Remover JRE ========================================

wmic product where "name like 'Java 8 Update 92%%'" call uninstall /nointeractive


:FIM

echo.
echo          Instalação concluida!
echo.

echo MSGBOX "Desinstalação concluida!" > %temp%\TEMPmessage.vbs
call %temp%\TEMPmessage.vbs
del %temp%\TEMPmessage.vbs /f /q

REM ================ Remover diretório "Arquivos de Programas PC" ========================================

REM O seguinte consegue remover todos os arquivos no diretório "Arquivos de Programas PC",
REM até este arquivo .bat, porém não consegue remover o diretório "Arquivos de Programas PC" :
REM ????????? Funcionou quando usei vbs acima antes disso !!!!!!!!!
cd\
rmdir /s /q "Arquivos de Programas PC"

