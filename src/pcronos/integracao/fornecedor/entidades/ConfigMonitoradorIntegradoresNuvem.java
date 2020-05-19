package pcronos.integracao.fornecedor.entidades;

import java.time.LocalDateTime;

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



@Entity // Para evitar org.hibernate.MappingException: Unknown entity que acontece quando usar class-level constraints com EL
@Table(name="Configuracao_Monitorador_Integradores_Nuvem")
public class ConfigMonitoradorIntegradoresNuvem {

	public ConfigMonitoradorIntegradoresNuvem() {}
	

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_config_monitorador_integradores_nuvem_cmintnuv")
	public int Id;

	
	@Column(name="id_sistema_integrado_sisint")
	int IdSistemaIntegrado;
	// O seguinte serve apenas para evitar erro "javax.el.PropertyNotFoundException" pelo class-level Hibernate constraint com EL:
	public int getIdSistemaIntegrado() { return IdSistemaIntegrado; }
	
	
    @Column(name="id_contato_TI_integrador_contiint")
    public int IdContatoTiIntegrador;

    @Column(name="id_contato_TI_secundario_integrador_contiint")
    public Integer IdContatoTiSecundarioIntegrador;

	@Column(name="aplicativo_desktop_remoto_cmintnuv")
	@Size(max=30, message = " = ${validatedValue}, tamanho = ${validatedValue.length()}, máximo permitido = {max}")
	public String AplicativoDesktopRemoto;
	
	@Column(name="id_aplicativo_desktop_remoto_cmintnuv")
	@Size(max=30, message = " = ${validatedValue}, tamanho = ${validatedValue.length()}, máximo permitido = {max}")
	public String IdAplicativoDesktopRemoto;
	
	@Column(name="dt_cadastro_cmintnuv")
	public LocalDateTime DtCadastro;
 	
	@Column(name="dt_desativacao_cmintnuv")
	public LocalDateTime DtDesativacao;
 	
	@Column(name="dt_alteracao_cmintnuv")
	public LocalDateTime DtAlteracao;
 	
	@Column(name="user_id_ususis")
	public int IdUsuario;
}
