package pcronos.integracao.fornecedor.entidades;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name="Sistema_Integrado")
public class SistemaIntegrado {
	
	public SistemaIntegrado() { }

	@Column(name="id_sistema_integrado_sisint")
	int Id;
	
	@Column(name="sg_sistema_integrado_sisint")
	String SiglaSistemaIntegrado;
	
}
