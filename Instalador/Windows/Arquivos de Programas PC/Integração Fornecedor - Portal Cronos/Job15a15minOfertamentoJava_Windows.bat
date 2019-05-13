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
cd "Integração Fornecedor - Portal Cronos"

call bin\Inicializacoes.bat
call bin\Versao.bat
call bin\CaminhoJRE.bat Job15a15minOfertamentoJava.log IntegracaoFornecedorCompleta %1

ENDLOCAL
exit


