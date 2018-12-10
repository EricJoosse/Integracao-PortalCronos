REM By default, variables are global to your entire command prompt session. 
REM Call the SETLOCAL command to make variables local to the scope of your script. 
REM After calling SETLOCAL, any variable assignments revert upon calling ENDLOCAL, 
REM calling EXIT, or when execution reaches the end of file (EOF) in your script.
REM The setlocal command sets the ERRORLEVEL variable to 1.
REM "SETLOCAL" é necessário aqui para poder usar ERRORLEVEL corretamente:   

SETLOCAL

REM Me esqueci para que serve "chcp 1252>nul", porém provavelmente isso é necessário 
REM para evitar que DOS não reconhece acentos Portugeses no caminho 
REM "Integração Fornecedor - Portal Cronos" em alguns ou talvez até em todos os servidores:

chcp 1252>nul

