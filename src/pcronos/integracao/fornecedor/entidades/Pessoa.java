package pcronos.integracao.fornecedor.entidades;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name="Pessoa")
public class Pessoa {
	
	public Pessoa() {  }

	@Column(name="id_pessoa")
	int Id;
	
	@Column(name="ds_email_pessoa")
	String Email;
	
}
