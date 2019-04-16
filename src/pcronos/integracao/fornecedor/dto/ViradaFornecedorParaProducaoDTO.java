package pcronos.integracao.fornecedor.dto;

import java.time.LocalDateTime;

public class ViradaFornecedorParaProducaoDTO {
	public int IdFornecedor;
	public String TipoDTO;
	public LocalDateTime IniIntervalo;
	public LocalDateTime FimIntervalo;
}
