package pcronos.integracao.fornecedor.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity // Para evitar erro "org.hibernate.hql.internal.ast.QuerySyntaxException: SistemaIntegrado is not mapped"
@Table(name="Sistema_Integrado")
public class SistemaIntegrado {
	
	public SistemaIntegrado() { }

	@Id //  Para evitar erro "object.org.hibernate.AnnotationException: No identifier specified for entity"
	@Column(name="id_sistema_integrado_sisint")
	public int Id;
	
	@Column(name="sg_sistema_integrado_sisint")
	public String SiglaSistemaIntegrado;
	
}
