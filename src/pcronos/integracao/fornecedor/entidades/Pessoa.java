package pcronos.integracao.fornecedor.entidades;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity // Para evitar erro "org.hibernate.hql.internal.ast.QuerySyntaxException: SistemaIntegrado is not mapped"
@Table(name="Pessoa")
public class Pessoa {
	
	public Pessoa() {  }

	@Id //  Para evitar erro "object.org.hibernate.AnnotationException: No identifier specified for entity"
	@Column(name="id_pessoa")
	int Id;
	
	@Column(name="ds_email_pessoa")
	String Email;
	
	@OneToMany(mappedBy = "pessoa")
	Set<UsuarioSistema> user = new HashSet();
	
}
