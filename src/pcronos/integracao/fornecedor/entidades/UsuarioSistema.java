package pcronos.integracao.fornecedor.entidades;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name="Usuario_Sistema")
public class UsuarioSistema {
	
	public UsuarioSistema() {  }

	@Column(name="user_id")
	public int Id;
	
	@Column(name="id_pessoa")
	int IdPessoa;
	
}
