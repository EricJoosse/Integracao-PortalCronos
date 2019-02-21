cls
@echo off

REM "chcp 1252>nul" é necessário para evitar que DOS não reconhece acentos Portugueses no caminho 
REM "Integração Fornecedor - Portal Cronos" em alguns ou talvez até em todos os servidores:

chcp 1252>nul

cd\
cd "Arquivos de Programas PC"
cd "Integração Fornecedor - Portal Cronos"

call bin\Inicializacoes.bat
call bin\Versao.bat
call bin\CaminhoJRE.bat Job15a15minOfertamentoJava.log IntegracaoFornecedorCompleta

ENDLOCAL
exit


