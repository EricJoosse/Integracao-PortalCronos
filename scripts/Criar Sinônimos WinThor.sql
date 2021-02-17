Conectar no banco Oracle como usuário inga ou usuário SYS ou outro usuário DBA e rodar o seguinte:

CREATE PUBLIC SYNONYM PCCLIENT FOR inga.PCCLIENT;
CREATE PUBLIC SYNONYM PCPRODUT FOR inga.PCPRODUT;
CREATE PUBLIC SYNONYM PCEST FOR inga.PCEST;
CREATE PUBLIC SYNONYM PCTABPR FOR inga.PCTABPR;
CREATE PUBLIC SYNONYM PCPLPAG FOR inga.PCPLPAG;
CREATE PUBLIC SYNONYM PCPRACA FOR inga.PCPRACA;
CREATE PUBLIC SYNONYM PCSECAO FOR inga.PCSECAO;

Depois disso conectar no banco Oracle como usuário "cronos" e testar o seguinte:
select * from PCCLIENT;
