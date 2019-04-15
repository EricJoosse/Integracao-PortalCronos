package pcronos.integracao.fornecedor;

import java.time.LocalDateTime;

public class CotacaoNaoOfertadaDTO 
{
	int IdFornecedor;
    String Nmfornecedor;
    String Ocorrência;
    int QtdMeusProdutos;
    int QtdTentativas;
    String  Erro; 
    LocalDateTime IniIntervalo;
    LocalDateTime FimIntervalo;
    LocalDateTime DtCadastro;
    LocalDateTime DtFimVigencia;
    String CdCotacao;
    
    
    String IdFornecedorString;
}
