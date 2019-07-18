REM Abertura dos arquivos .properties via este arquivo .bat
REM =======================================================
REM
REM Motivos: 
REM --------
REM  1. Para forçar o uso do Notepad:
REM     isso elimina a necessidade para associar arquivos com extensão ".properties" 
REM     para todos os usuários de Windows existentes e novos usuários adicionados no futuro.
REM
REM  2. Para forçar o usuário se conectar como Administrador ou para usar a opção "Executar como Administrador": 
REM     isso é necessário para o salvamento dos arquivos poder funcionaar. 



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

start notepad "Integração Fornecedor - Portal Cronos/conf/Integração APS - Portal Cronos.%1.properties"

exit


:FIM
