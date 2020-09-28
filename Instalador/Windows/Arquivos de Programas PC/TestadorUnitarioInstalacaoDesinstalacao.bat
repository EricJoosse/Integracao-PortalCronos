cls
@echo off

REM Windows, por default, usa o charset WINDOWS-1252 (ou outra variação, dependendo da lingua), 
REM que é uma extensão do ISO-8859-1. 
REM Mesmo assim, não se compara com UTF-8! O Linux, por default, usa o charset UTF-8. 
REM Eclipse herda o charset de Windows por default. 
REM "chcp 1252>nul" é necessário para evitar que DOS não reconhece acentos Portugueses no caminho 
REM "Integração Fornecedor - Portal Cronos" em alguns ou talvez até em todos os servidores:

chcp 1252>nul


REM o diretório default de MS-DOS é C:\Windows\system32, então é melhor em todos os casos 
REM pelo menos navegar primeiro para o diretório onde fica este Testador Unitário do Instalador e do Desinstalador:

cd\

REM Se testar dentro do Eclipse ao invés de nos servidores:
if exist C:/PCronos/"Integração Fornecedor - Portal Cronos"/Instalador/Windows/"Arquivos de Programas PC"/Instalador.bat (
  cd PCronos
  cd "Integração Fornecedor - Portal Cronos"
  cd Instalador
  cd Windows
  cd "Arquivos de Programas PC"
) else (  
  cd "Arquivos de Programas PC"
)

REM "pwd" em Linux = "%cd% em DOS:
echo %cd%

REM goto InstalarManualTI
REM goto DesinstalarManualTI
REM goto InstalarDirLog
REM goto DesinstalarDirLog
REM goto TesteIfNotExist
REM goto TesteDelProprioArq
REM goto TesteLimpeza
REM goto TesteDelInstalador
REM goto TesteRegedit
REM goto TesteSubDirBin
REM goto TesteIfExist
REM goto TesteAttrib
REM goto TesteVersao
REM goto TesteTemplate
REM goto TesteDelTpl
REM goto TesteIsAmbienteNuvem
REM goto TesteRemocaoMultiplasTarefasWindows
REM goto TesteIfExistArqConfEspecifico
REM goto TesteFindTarefaEspecifica
goto TesteTresParam


REM ================ Testes Instalação Resolver Paradas do menu de Windows: ========================================
REM ================  (testado com Windows Server 2008 R2 SP1, funcionou)       ========================================
REM ================  (testado com Windows Server 2012 R2,     funcionou)       ========================================
REM ================  (testado com Windows Server 2016,        funcionou)       ========================================
REM ================  (testado com Windows Server 2016,        funcionou)       ========================================
REM ================  (testado com Windows 10 Pro - apenas testado no caso de um processador e SO 64 bit, funcionou)
:InstalarManualTI
call Instalador.bat
exit

REM ================ Testes Desinstalação Resolver Paradas do menu de Windows: ========================================
REM ================  (testado com Windows Server 2008 R2 SP1, funcionou)       ========================================
REM ================  (testado com Windows Server 2012 R2,     funcionou)       ========================================
REM ================  (testado com Windows Server 2016,        funcionou)       ========================================
REM ================  (testado com Windows 10 Pro - apenas testado no caso de um processador e SO 64 bit, funcionou)

:DesinstalarManualTI
call "Integração Fornecedor - Portal Cronos\bin\Desinstalador.bat"

echo Passou Desinstalador.bat
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

pause
exit

REM ================ Testes instalação diretório de Log (testado com Windows Server 2008 R2 SP1, funcionou): ========================================
REM ================                                    (testado com Windows Server 2012 R2,     funcionou)  ========================================
REM ================                                    (testado com Windows Server 2016,        funcionou)  ========================================
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

pause
exit

REM ================ Testes Desinstalação diretório de Log (testado com Windows Server 2008 R2 SP1, funcionou): ========================================
REM ================                                       (testado com Windows Server 2012 R2,     funcionou)  ========================================
REM ================                                       (testado com Windows Server 2016,        funcionou)  ========================================
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

pause
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

pause
exit


REM ================ Teste de exclusão deste arquivo mesmo (testado, funcionou): ========================================

:TesteDelProprioArq
del /f /q TestadorUnitarioInstalacaoDesinstalacao.bat

pause
exit


REM ================ Teste Limpeza com wildcards (testado, funcionou): ========================================

:TesteLimpeza
del /f /q *.reg
del /f /q *.xml
del /f /q *.exe
del /f /q Instalador.bat
del /f /q Instalador_Integrador.bat
del /f /q Instalador_Monitorador.bat
REM Excluir este próprio arquivo apenas no final, 
REM pois foi testado que não vai excluir os arquivos que viriam depois disso:
del /f /q TestadorUnitarioInstalacaoDesinstalacao.bat

pause
exit


REM ================ Teste Exclusão Instalador (testado, funcionou): ========================================

:TesteDelInstalador
del /f /q C:\temp\"Instalador do Integrador Fornecedores - Portal Cronos.*.exe"

pause
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
    echo Entrou no if 2008
    set arquivoRegedit="%drive%\\Arquivos de Programas PC\\DeshabilitarJavaUpdates.x64.reg"
) else if %osVersion% == Windows_Server_2012_R2 (
    echo Entrou no else if 2012
    set arquivoRegedit="%~dp0DeshabilitarJavaUpdates.x64.reg"
) else if %osVersion% == Windows_Server_2016 (
    echo Entrou no else if 2016
    set arquivoRegedit="%~dp0DeshabilitarJavaUpdates.x64.reg"
) else if %Windows_10_Pro_64bit% == 1 (
    echo Entrou no else if Win 10
    set arquivoRegedit=DeshabilitarJavaUpdates.x64.reg
) else if %osVersion% == Windows_7_Professional_SP1 (
    echo Entrou no else if Win 7 Professional
    set arquivoRegedit=DeshabilitarJavaUpdates.i586.reg
) else (
    echo Entrou no else das osVersions
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

REM ================ Teste de chamada de arqs .bat em subdirs (testado, funcionou): ========================================

:TesteSubDirBin

REM Mover temporariamente "TestadorUnitario.bat" de \Integração Fornecedor - Portal Cronos\
REM para \Integração Fornecedor - Portal Cronos\bin\;
call "Integração Fornecedor - Portal Cronos\bin\TestadorUnitario.bat"

pause
exit

REM ================ Teste unitário if exist C:/"Program Files (x86)"/Java/jre1.8.0_191/bin/java.exe (testado, funcionou): ========

:TesteIfExist

if exist C:/"Program Files (x86)"/Java/jre1.8.0_191/bin/java.exe (
    echo MSGBOX "jre1.8.0_191 existe!" > %temp%\TEMPmessage.vbs
    call %temp%\TEMPmessage.vbs
    del %temp%\TEMPmessage.vbs /f /q
) else if exist C:/"Program Files"/Java/jre1.8.0_92/bin/java.exe (
    echo MSGBOX "jre1.8.0_92 existe!" > %temp%\TEMPmessage.vbs
    call %temp%\TEMPmessage.vbs
    del %temp%\TEMPmessage.vbs /f /q
) else (
    echo MSGBOX "Erro! Nem jre1.8.0_191 nem jre1.8.0_92 existe!" > %temp%\TEMPmessage.vbs
    call %temp%\TEMPmessage.vbs
    del %temp%\TEMPmessage.vbs /f /q
)
		
pause
exit

REM ================ Teste attrib +S de diretório (testado, funcionou): ========================================

:TesteAttrib

REM Proteção contra exclusão indevida por gerente ignorante que gosta de apagar coisas 
REM e que apaga tudo que não foi ele mesmo que tinha feito, sem avisar ninguêm:

cd\
attrib "Arquivos de Programas PC" +S +H
attrib /S /D "Arquivos de Programas PC"

pause
exit

REM ================ Teste Versão (testado, funcionou): ========================================

:TesteVersao

SETLOCAL

call "Integração Fornecedor - Portal Cronos\bin\Versao.bat"
echo integr-fornecedor-%versaoIntegrador%.jar


ENDLOCAL

pause
exit

REM ================ Teste Passagem 3 parâmetros (testado, funcionou): ========================================

:TesteTresParam

REM Windows, por default, usa o charset WINDOWS-1252 (ou outra variação, dependendo da lingua), 
REM que é uma extensão do ISO-8859-1. 
REM Mesmo assim, não se compara com UTF-8! O Linux, por default, usa o charset UTF-8. 
REM Eclipse herda o charset de Windows por default. 
REM "chcp 1252>nul" é necessário para evitar que DOS não reconhece acentos Portugueses no caminho 
REM "Integração Fornecedor - Portal Cronos" em alguns ou talvez até em todos os servidores:

chcp 1252>nul

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

cd "Integração Fornecedor - Portal Cronos"
call bin\Versao.bat
call bin\ComponenteTestador.bat TesteTresParam.log TestadorSnippets %idFornecedor%


pause
exit

REM ================ Teste del tpl (testado, funcionou com -1 e com 33): ========================================

:TesteDelTpl

chcp 1252>nul

cd\
cd "Arquivos de Programas PC"
call Instalador.bat
cd\
cd "Arquivos de Programas PC"
cd "Integração Fornecedor - Portal Cronos"

if %idFornecedor% NEQ -1 (
    rmdir /s /q tpl
)


pause
exit

REM ================ Teste IsAmbienteNuvem (testado, funcionou): ========================================

:TesteIsAmbienteNuvem

chcp 1252>nul

cd\
cd "Arquivos de Programas PC"

call "Integração Fornecedor - Portal Cronos\bin\IsAmbienteNuvem.bat"

REM Aspas duplas são necessárias porque se o variável for empty, este .bat vai falhar pois não entende o parentese direto após o ==
if "%IsAmbienteNuvem%" == "1" (
       echo IsAmbienteNuvem == 1
) else if "%IsAmbienteNuvem%" == "0" (
       echo IsAmbienteNuvem == 0
) else (
    echo.
    echo          A desinstalação falhou! Variável IsAmbienteNuvem inválido!
    echo.
    
    echo MSGBOX "A instalação falhou! Variável IsAmbienteNuvem inválido!" > %temp%\TEMPmessage.vbs
    call %temp%\TEMPmessage.vbs
    del %temp%\TEMPmessage.vbs /f /q
REM start notepad Desinstalador.log
)

pause
exit

REM ================ Teste Remoção múltiplas tarefas Windows no caso de Integradores Cloud (testado, funcionou): ========================================

:TesteRemocaoMultiplasTarefasWindows

chcp 1252>nul
cd\
cd "Arquivos de Programas PC"
call "Integração Fornecedor - Portal Cronos\bin\IsAmbienteNuvem.bat"

for /f "tokens=* delims=\" %%x in ('SCHTASKS /QUERY /FO:LIST ^| FINDSTR "Integração Portal Cronos."') do echo "\%%x"

REM Aspas duplas são necessárias porque se o variável for empty, este .bat vai falhar pois não entende o parentese direto após o ==
if "%IsAmbienteNuvem%" == "1" (
    for /f "tokens=2 delims=\" %%x in ('SCHTASKS /QUERY /FO:LIST ^| FINDSTR "Integração Portal Cronos."') do SCHTASKS /End /TN "\%%x"
    for /f "tokens=2 delims=\" %%x in ('SCHTASKS /QUERY /FO:LIST ^| FINDSTR "Integração Portal Cronos."') do SCHTASKS /Delete /TN "\%%x" /F
) else (
    echo.
    echo          A desinstalação falhou! Variável IsAmbienteNuvem inválido!
    echo.
    
    echo MSGBOX "A instalação falhou! Variável IsAmbienteNuvem inválido!" > %temp%\TEMPmessage.vbs
    call %temp%\TEMPmessage.vbs
    del %temp%\TEMPmessage.vbs /f /q
REM start notepad Desinstalador.log
)


pause
exit

REM ================ Teste Find Tarefa Específica (testado??????????, funcionou????????????????): ========================================

:TesteFindTarefaEspecifica

chcp 1252>nul

cd\
cd "Arquivos de Programas PC"

echo.
echo.
SET /P nmFornecedor=Digite o nome fantasia da empresa cliente: 
IF "%nmFornecedor%"=="" GOTO ErroNmFornecedor
if not "%nmFornecedor%"=="%nmFornecedor: =%" goto ErroEspacosNmFornecedor
GOTO PularErroNmFornecedor
:ErroEspacosNmFornecedor
echo MSGBOX "Erro: não pode ter nenhum espaço em branco no nome! Remoção deste cliente não efetuada!" > %temp%\TEMPmessage.vbs
call %temp%\TEMPmessage.vbs
del %temp%\TEMPmessage.vbs /f /q
REM Fechar o script chamador também: 
exit
:ErroNmFornecedor
echo MSGBOX "Erro: nome não informado! Remoção deste cliente não efetuada!" > %temp%\TEMPmessage.vbs
call %temp%\TEMPmessage.vbs
del %temp%\TEMPmessage.vbs /f /q
REM Fechar o script chamador também: 
exit
:PularErroNmFornecedor

set ClienteExiste=0
for /f "tokens=2 delims=\" %%x in ('SCHTASKS /QUERY /FO:LIST ^| FINDSTR "Integração Portal Cronos - ^%nmFornecedor^%"') do set ClienteExiste=1
for /f "tokens=2 delims=\" %%x in ('SCHTASKS /QUERY /FO:LIST ^| FINDSTR "Integração Portal Cronos - ^%nmFornecedor^%"') do echo "\%%x"
if %ClienteExiste% == 0 (
    echo.
    echo          Nome inválido!
    echo.
    
    echo MSGBOX "Nome inválido!" > %temp%\TEMPmessage.vbs
    call %temp%\TEMPmessage.vbs
    del %temp%\TEMPmessage.vbs /f /q
    exit
) else (
    echo.
    echo          Tarefa de %nmFornecedor% encontrada!
    echo.
    
    echo MSGBOX "Tarefa de %nmFornecedor% encontrada!" > %temp%\TEMPmessage.vbs
    call %temp%\TEMPmessage.vbs
    del %temp%\TEMPmessage.vbs /f /q
    exit
)

pause
exit

REM ================ Teste If Exist Arq Conf Especifico (testado, funcionou): ========================================

:TesteIfExistArqConfEspecifico

chcp 1252>nul

cd\
cd "Arquivos de Programas PC"

echo.
echo.
SET /P nmFornecedor=Digite o nome fantasia da empresa cliente: 
IF "%nmFornecedor%"=="" GOTO ErroNmFornecedor
if not "%nmFornecedor%"=="%nmFornecedor: =%" goto ErroEspacosNmFornecedor
GOTO PularErroNmFornecedor
:ErroEspacosNmFornecedor
echo MSGBOX "Erro: não pode ter nenhum espaço em branco no nome! Remoção deste cliente não efetuada!" > %temp%\TEMPmessage.vbs
call %temp%\TEMPmessage.vbs
del %temp%\TEMPmessage.vbs /f /q
REM Fechar o script chamador também: 
exit
:ErroNmFornecedor
echo MSGBOX "Erro: nome não informado! Remoção deste cliente não efetuada!" > %temp%\TEMPmessage.vbs
call %temp%\TEMPmessage.vbs
del %temp%\TEMPmessage.vbs /f /q
REM Fechar o script chamador também: 
exit
:PularErroNmFornecedor

if not exist "C:/Arquivos de Programas PC/Integração Fornecedor - Portal Cronos/conf/Integração APS - Portal Cronos.%nmFornecedor%.properties" (
    echo.
    echo          Nome inválido!
    echo.
    
    echo MSGBOX "Nome inválido!" > %temp%\TEMPmessage.vbs
    call %temp%\TEMPmessage.vbs
    del %temp%\TEMPmessage.vbs /f /q
    exit
) else (
    echo.
    echo          Arq config de %nmFornecedor% encontrado!
    echo.
    
    echo MSGBOX "Arq config de %nmFornecedor% encontrado!" > %temp%\TEMPmessage.vbs
    call %temp%\TEMPmessage.vbs
    del %temp%\TEMPmessage.vbs /f /q
    exit
)


pause
exit

REM ================ Teste Template (testado??????????, funcionou????????????????): ========================================

:TesteTemplate

........comandos DOS..........


pause
exit

REM ============================ Fim =========================================================================

:FIM
