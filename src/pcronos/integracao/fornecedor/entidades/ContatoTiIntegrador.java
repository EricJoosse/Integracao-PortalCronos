package pcronos.integracao.fornecedor.entidades;

import java.time.LocalDateTime;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;


// Foi testado que não faz nenhuma diferença usar "@Length" do Hibernate ou "@Size" do JPA,
// até os atributos "max" e "message" funcionam igualmente: 
// @Length(max = 15, message = " = ${validatedValue}, tamanho = ${validatedValue.length()}, máximo permitido = {max}")
// @Size(max = 15, message = " = ${validatedValue}, tamanho = ${validatedValue.length()}, máximo permitido = {max}")
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;
//import org.hibernate.validator.Length;  // hibernate.validator.3.1.0.GA

// Não funcionou: @Length(max = 15, message = "IdFornecedor = ${ConfigMonitoradorIntegradores.IdFornecedor}: PrenomeContatoTIsecundario = ${validatedValue}, tamanho = ${validatedValue.length()}, max = {max}")
// Não funcionou: @Length(max = 15, message = "IdFornecedor = ${IdFornecedor}: PrenomeContatoTIsecundario = ${validatedValue}, tamanho = ${validatedValue.length()}, max = {max}")
// Não funcionou: @Length(max = 15, message = "${propertyPath} = ${validatedValue}, tamanho = ${validatedValue.length()}, max = {max}")


import pcronos.integracao.fornecedor.annotations.ValidConfigMonitoradorIntegradores;
import pcronos.integracao.fornecedor.interfaces.FornecedorInterface;
import pcronos.integracao.fornecedor.interfaces.FornecedorOuSistemaIntegradoInterface;



@Entity // Para evitar org.hibernate.MappingException: Unknown entity que acontece quando usar class-level constraints com EL
@Table(name="Contato_TI_Integrador")
public class ContatoTiIntegrador implements FornecedorInterface, FornecedorOuSistemaIntegradoInterface {

	public ContatoTiIntegrador() {}
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_contato_TI_integrador_contiint")
	public int Id;
	
	@Column(name="id_fornecedor_fornec")
	public int IdFornecedor;
	// O seguinte serve apenas para evitar erro "javax.el.PropertyNotFoundException" pelo class-level Hibernate constraint com EL:
	@Override
	public int getIdFornecedor() { return IdFornecedor; }
	@Override
	public int getIdFornecedorOuSistemaIntegrado() { return IdFornecedor; }
	
	@Column(name="nr_sequencia_contato_contiint")
	public byte nrSequenciaContato;

	@Column(name="prenome_contato_ti_contiint")
	@Length(max = 15, message = " = ${validatedValue}, tamanho = ${validatedValue.length()}, máximo permitido = {max}")
    public String PrenomeContatoTI;
    
	@Column(name="email_contato_ti_contiint")
    @Size(max=30, message = " = ${validatedValue}, tamanho = ${validatedValue.length()}, máximo permitido = {max}")
	public String EmailContatoTI;
	
	@Column(name="skype_contato_ti_contiint")
    @Size(max=30, message = " = ${validatedValue}, tamanho = ${validatedValue.length()}, máximo permitido = {max}")
	public String SkypeContatoTI;
	
	@Column(name="telefone_contato_ti_contiint")
    @Size(max=30, message = " = ${validatedValue}, tamanho = ${validatedValue.length()}, máximo permitido = {max}")
	public String TelefoneContatoTI;
	
	@Column(name="funcao_contato_ti_contiint")
    @Size(max=30, message = " = ${validatedValue}, tamanho = ${validatedValue.length()}, máximo permitido = {max}")
	public String FuncaoContatoTI;

	@Column(name="dt_cadastro_contiint")
	public LocalDate DtCadastro;
 	
	@Column(name="dt_desativacao_contiint")
	public LocalDateTime DtDesativacao;
 	
	@Column(name="dt_alteracao_contiint")
	public LocalDateTime DtAlteracao;
 	
	@Column(name="user_id_ususis")
	public int IdUsuario;
}

