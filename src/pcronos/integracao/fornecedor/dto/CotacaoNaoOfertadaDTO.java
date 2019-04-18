package pcronos.integracao.fornecedor.dto;

import java.time.LocalDateTime;

public class CotacaoNaoOfertadaDTO 
{
	public int IdFornecedor;
	public String Nmfornecedor;
	public String Ocorrência;
	public int QtdMeusProdutos;
	public int QtdTentativas;
	public String  Erro; 
	public LocalDateTime IniIntervalo;
	public LocalDateTime FimIntervalo;
	public LocalDateTime DtCadastro;
	public LocalDateTime DtFimVigencia;
	public String CdCotacao;
    
    // Campo auxiliar:
	public String IdFornecedorString;
}
