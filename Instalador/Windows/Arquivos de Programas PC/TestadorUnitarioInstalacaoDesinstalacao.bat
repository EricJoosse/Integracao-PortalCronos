cls
@echo off

chcp 1252>nul

REM goto InstalarManualTI
REM goto DesinstalarManualTI
REM goto InstalarDirLog
REM goto DesinstalarDirLog
REM goto TesteIfNotExist
REM goto TesteDelProprioArq
REM goto TesteLimpeza
REM goto TesteDelInstalador
goto TesteRegedit


REM ================ Testes INstalação Manual Manutenção TI do menu de Windows: ========================================
REM ================  (testado com Windows Server 2008 R2 SP1, funcionou)       ========================================
REM ================  (testado com Windows Server 2012 R2,     funcionou)       ========================================

:InstalarManualTI
call InstalarManualTI.bat
exit

REM ================ Testes DESinstalação Manual Manutenção TI do menu de Windows: ========================================
REM ================  (testado com Windows Server 2008 R2 SP1, funcionou)       ========================================
REM ================  (testado com Windows Server 2012 R2,     funcionou)       ========================================

:DesinstalarManualTI
call DesinstalarManualTI.bat

echo Passou DesinstalarManualTI.bat
echo Testador.bat: ERRORLEVEL = %ERRORLEVEL%
pause

IF %ERRORLEVEL% NEQ 0 (
    goto PULAR_MENSAGEM_SUCESSO
)
echo MSGBOX "Erro: este comando não foi pulado indevidamente!" > %temp%\TEMPmessage.vbs
call %temp%\TEMPmessage.vbs
del %temp%\TEMPmessage.vbs /f /q


:PULAR_MENSAGEM_SUCESSO
echo MSGBOX "Sucesso: o comando acima foi pulado devidamente!" > %temp%\TEMPmessage.vbs
call %temp%\TEMPmessage.vbs
del %temp%\TEMPmessage.vbs /f /q

exit

REM ================ Testes instalação diretório de Log (testado com Windows Server 2008 R2 SP1, funcionou): ========================================
REM ================                                    (testado com Windows Server 2012 R2,     funcionou)  ========================================
REM ================                                     Obs.: foi testado também no caso que o dir =================================================
REM ================                                     C:\ProgramData\PortalCronos\Logs\Remoto\ já existia antes ==================================

:InstalarDirLog
cd\
if not exist C:\ProgramData\ (
    echo MSGBOX "O diretório padrão de Windows ProgramData não existe! Favor entrar em contato com o Suporte do Portal Cronos." > %temp%\TEMPmessage.vbs
    call %temp%\TEMPmessage.vbs
    del %temp%\TEMPmessage.vbs /f /q
    exit
)
cd ProgramData
if not exist C:\ProgramData\PortalCronos\ mkdir PortalCronos
cd PortalCronos
if not exist C:\ProgramData\PortalCronos\Logs\ mkdir Logs
cd Logs
if not exist C:\ProgramData\PortalCronos\Logs\Local\ mkdir Local
REM Não precisa dar privilêgios, pois a Scheduled Task roda como SYSTEM

exit

REM ================ Testes DESinstalação diretório de Log (testado com Windows Server 2008 R2 SP1, funcionou): ========================================
REM ================                                       (testado com Windows Server 2012 R2,     funcionou)  ========================================
REM ================                                        Obs.: foi testado também no caso que o dir =================================================
REM ================                                        C:\ProgramData\PortalCronos\Logs\Remoto\ também existe =====================================

:DesinstalarDirLog
REM Evitando interferência indevida com outros diretórios:
REM    C:\ProgramData\PortalCronos\Logs\Local
REM    C:\ProgramData\PortalCronos\Logs\Remoto\Integracao\
REM    C:\ProgramData\PortalCronos\Logs\Remoto\APK\

cd\
cd ProgramData

REM Foi testado que "rmdir /s /q PortalCronos" não funciona como deveria. 
REM Talvez isso é porque existem diversos níveis de subsubsubdiretórios:
 
cd PortalCronos
cd Logs
rmdir /s /q Local

if NOT exist "Remoto" (
  cd\
  cd ProgramData
  cd PortalCronos
  rmdir /s /q Logs
  cd\
  cd ProgramData
  rmdir /s /q PortalCronos
)
cd\

exit

REM ================ Testes if NOT exist subdir (testado, funcionou): ========================================

:TesteIfNotExist
cd\
cd ProgramData
cd PortalCronos
cd Logs

if NOT exist "Remoto" (
    echo MSGBOX "O subdiretório Remoto não existe." > %temp%\TEMPmessage.vbs
    call %temp%\TEMPmessage.vbs
    del %temp%\TEMPmessage.vbs /f /q
)

if exist "Remoto" (
    echo MSGBOX "O subdiretório Remoto existe sim." > %temp%\TEMPmessage.vbs
    call %temp%\TEMPmessage.vbs
    del %temp%\TEMPmessage.vbs /f /q
)

exit


REM ================ Teste de exclusão deste arquivo mesmo (testado, funcionou): ========================================

:TesteDelProprioArq
del /f /q TestadorUnitarioInstalacaoDesinstalacao.bat

exit


REM ================ Teste Limpeza com wildcards (testado, funcionou): ========================================

:TesteLimpeza
del /f /q *.reg
del /f /q *.xml
del /f /q *.exe
del /f /q InstalarManualTI.bat
del /f /q Primeira_Instalacao_Versao_Windows.bat
REM Excluir este próprio arquivo apenas no final, 
REM pois foi testado que não vai excluir os arquivos que viriam depois disso:
del /f /q TestadorUnitarioInstalacaoDesinstalacao.bat

exit


REM ================ Teste Exclusão Instalador (testado, funcionou): ========================================

:TesteDelInstalador
del /f /q C:\temp\"Instalador do Integrador Fornecedores - Portal Cronos.*.exe"

exit


REM ================ Teste instalação chaves no regedit (testado com Windows Server 2008 R2 SP1, NÃO funcionou    ): ========================================
REM ================                                    (testado com Windows Server 2012 R2,         funcionou sim)  ========================================

:TesteRegedit

REM Primeiro rodar na mão, como Administrador, se o JRE não estiver instalado: 
REM Start /wait jre-8u92-windows-x64.exe /s INSTALLDIR=C:\\"Program Files"\Java\jre1.8.0_92

set osVersion=Windows_Server_2012_R2
set /A tipoOS=64
set drive=C:
set Windows_10_Pro_64bit=0

if %osVersion% == Windows_10_Pro (
   if %tipoOS% == 64 (
      set Windows_10_Pro_64bit=1
   )
)

if %osVersion% == Windows_Server_2008_R2_SP1 (
    set arquivoRegedit="%drive%\\Arquivos de Programas PC\\DeshabilitarJavaUpdates.x64.reg"
) else if %osVersion% == Windows_Server_2012_R2 (
    set arquivoRegedit="%~dp0DeshabilitarJavaUpdates.x64.reg"
) else if %Windows_10_Pro_64bit% == 1 (
    set arquivoRegedit=DeshabilitarJavaUpdates.x64.reg
) else if %osVersion% == Windows_7_Professional_SP1 (
    set arquivoRegedit=DeshabilitarJavaUpdates.i586.reg
) else (
    echo MSGBOX "Esta versão de Windows ainda não está suportada! Favor entrar em contato com o Suporte do Portal Cronos." > %temp%\TEMPmessage.vbs
    call %temp%\TEMPmessage.vbs
    del %temp%\TEMPmessage.vbs /f /q
    exit
)

echo arquivoRegedit = %arquivoRegedit%
pause

if %tipoOS% == 64 (
    regedit.exe %arquivoRegedit%
) else if %tipoOS% == 32 (
    regedit.exe %arquivoRegedit%
) else (
    echo Tipo OS %tipoOS% não reconhecido ^! Opções: 32 ou 64 ^(Bits^)
)

pause

exit

REM ============================ Fim ========================================

:FIM
