SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
alter function [dbo].[getUFsFornecedorConcatenadas] (
    @idFornecedor int
)
RETURNS [varchar](max) AS 
begin

-- Função usada em : 
-- 1. dbo.getCotacoesFornecedorIntegracao
-- 2. 

declare @uf  varchar(max)
declare @ufs varchar(max)

declare c cursor for 
  SELECT uff.id_unidade_federativa_unifed
    FROM dbo.Unidade_Federativa_Fornecedor   uff
   WHERE uff.id_fornecedor_fornec = @idFornecedor
     and (uff.dt_desativacao_uffor is null or uff.dt_desativacao_uffor > getdate())

open c
fetch c into @uf

set @ufs = ''

while @@fetch_status = 0 
  begin

    set @ufs = @ufs + @uf + ','
    fetch c into @uf

  end

close c
deallocate c

if (Len(@ufs) > 2)
begin
   set @ufs = Substring(@ufs, 1, (Len(@ufs) - 1))
end

return @ufs

end
