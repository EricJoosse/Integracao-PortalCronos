package pcronos.integracao.fornecedor.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity // Para evitar erro "org.hibernate.hql.internal.ast.QuerySyntaxException: SistemaIntegrado is not mapped"
@Table(name="Usuario_Sistema")
public class UsuarioSistema {
	
	public UsuarioSistema() {  }

	@Id //  Para evitar erro "object.org.hibernate.AnnotationException: No identifier specified for entity"
	@Column(name="user_id")
	public int Id;
	
	@OneToOne
	@JoinColumn(name="id_pessoa")
	public Pessoa pessoa;
	
}
