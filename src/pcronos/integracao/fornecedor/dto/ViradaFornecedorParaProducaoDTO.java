package pcronos.integracao.fornecedor.dto;

import java.time.LocalDateTime;

public class ViradaFornecedorParaProducaoDTO {
	public String IdFornecedor;
	public String TipoDTO;
	public LocalDateTime IniIntervalo;
	public LocalDateTime FimIntervalo;
}
