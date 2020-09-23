SETLOCAL

REM Windows, por default, usa o charset WINDOWS-1252 (ou outra variação, dependendo da lingua), 
REM que é uma extensão do ISO-8859-1. 
REM Mesmo assim, não se compara com UTF-8! O Linux, por default, usa o charset UTF-8. 
REM Eclipse herda o charset de Windows por default. 
REM "chcp 1252>nul" é necessário para evitar que DOS não reconhece acentos Portugueses no caminho 
REM "Integração Fornecedor - Portal Cronos" em alguns ou talvez até em todos os servidores:

chcp 1252>nul

echo.
echo ComponenteTestador.bat entrado: 
echo Param 0 = %0
echo Param 1 = %1
echo Param 2 = %2
echo Param 3 = %3
echo Versão = %versaoIntegrador%

REM pause

call bin\CaminhoJRE.bat %1 %2 %3
