package pcronos.integracao.fornecedor.entidades;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;


import org.hibernate.validator.constraints.Length;
//import org.hibernate.validator.Length;  // hibernate.validator.3.1.0.GA
import javax.validation.constraints.Size;


//@Entity
@Table(name="Configuracao_Monitorador_Integradores")
public class ConfigMonitoradorIntegradores {

	public ConfigMonitoradorIntegradores() {}
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_config_monitorador_integradores_conmonint")
	public int Id;
	
	@Column(name="id_fornecedor_fornec")
	public int IdFornecedor;
	
	@Column(name="id_vendedor_responsavel_integracao_ususis")
	public int IdVendedorResponsavel;
	
	@Column(name="sn_em_producao_conmonint")
	public boolean IsEmProducao;

    @Column(name="apelido_contato_ti_conmonint")
    @Size(max=15)
    public String ApelidoContatoTI;
    
	@Column(name="email_contato_ti_conmonint")
    @Size(max=30)
	public String EmailContatoTI;
	
	@Column(name="skype_contato_ti_conmonint")
    @Size(max=30)
	public String SkypeContatoTI;
	
	@Column(name="telefone_contato_ti_conmonint")
    @Size(max=30)
	public String TelefoneContatoTI;
	
	@Column(name="funcao_contato_ti_conmonint")
    @Size(max=30)
	public String FuncaoContatoTI;

	@Column(name="apelido_contato_ti_secundario_conmonint")
 // @Length(max=15)
	@Length(max = 15, message = "ApelidoContatoTIsecundario = ${validatedValue}")
	public String ApelidoContatoTIsecundario;
	
	@Column(name="email_contato_ti_secundario_conmonint")
	@Size(max=30)
	public String EmailContatoTIsecundario;
	
	@Column(name="skype_contato_ti_secundario_conmonint")
    @Size(max=30)
	public String SkypeContatoTIsecundario;
	
	@Column(name="telefone_contato_ti_secundario_conmonint")
    @Size(max=30)
	public String TelefoneContatoTIsecundario;

	@Column(name="funcao_contato_ti_secundario_conmonint")
    @Size(max=30)
	public String FuncaoContatoTIsecundario;

	@Column(name="aplicativo_desktop_remoto_conmonint")
    @Size(max=30)
	public String AplicativoDesktopRemoto;
	
	@Column(name="id_aplicativo_desktop_remoto_conmonint")
    @Size(max=30)
	public String IdAplicativoDesktopRemoto;
	
	@Column(name="dt_cadastro_conmonint")
	public LocalDateTime DtCadastro;
 	
	@Column(name="dt_desativacao_conmonint")
	public LocalDateTime DtDesativacao;
 	
	@Column(name="dt_alteracao_conmonint")
	public LocalDateTime DtAlteracao;
 	
	@Column(name="user_id_ususis")
	public int IdUsuario;
}
