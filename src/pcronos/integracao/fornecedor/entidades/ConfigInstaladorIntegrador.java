package pcronos.integracao.fornecedor.entidades;

import java.time.LocalDateTime;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;


//Foi testado que não faz nenhuma diferença usar "@Length" do Hibernate ou "@Size" do JPA,
//até os atributos "max" e "message" funcionam igualmente: 
//@Length(max = 15, message = " = ${validatedValue}, tamanho = ${validatedValue.length()}, máximo permitido = {max}")
//@Size(max = 15, message = " = ${validatedValue}, tamanho = ${validatedValue.length()}, máximo permitido = {max}")
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;
//import org.hibernate.validator.Length;  // hibernate.validator.3.1.0.GA

//Não funcionou: @Length(max = 15, message = "IdFornecedor = ${ConfigMonitoradorIntegradores.IdFornecedor}: PrenomeContatoTIsecundario = ${validatedValue}, tamanho = ${validatedValue.length()}, max = {max}")
//Não funcionou: @Length(max = 15, message = "IdFornecedor = ${IdFornecedor}: PrenomeContatoTIsecundario = ${validatedValue}, tamanho = ${validatedValue.length()}, max = {max}")
//Não funcionou: @Length(max = 15, message = "${propertyPath} = ${validatedValue}, tamanho = ${validatedValue.length()}, max = {max}")

import pcronos.integracao.fornecedor.interfaces.FornecedorInterface;
import pcronos.integracao.fornecedor.interfaces.FornecedorOuSistemaIntegradoInterface;


@Entity // Para evitar org.hibernate.MappingException: Unknown entity que acontece quando usar class-level constraints com EL
@Table(name="Configuracao_Instalador_Integrador")
public class ConfigInstaladorIntegrador implements FornecedorInterface, FornecedorOuSistemaIntegradoInterface {

	public ConfigInstaladorIntegrador() {}
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_config_instalador_integrador_coninsint")
	int Id;
	
	@Column(name="id_fornecedor_fornec")
	public int IdFornecedor;
	// O seguinte serve apenas para evitar erro "javax.el.PropertyNotFoundException" pelo class-level Hibernate constraint com EL:
	@Override
	public int getIdFornecedor() { return IdFornecedor; }
	@Override
	public int getIdFornecedorOuSistemaIntegrado() { return IdFornecedor; }
	
	@Column(name="id_sistema_integrado_sisint")
	int IdSistemaIntegrado;
	
    @Column(name="usuario_webservice_coninsint")
    @Length(max=30, message = " = ${validatedValue}, tamanho = ${validatedValue.length()}, máximo permitido = {max}")
    String UsuarioWebservice;
    
	@Column(name="sn_debug_ativado_coninsint")
	boolean IsDebugAtivado;

	@Column(name="qtd_dias_arquivos_xml_guardados_coninsint")
	int QtdDiasArquivosXmlGuardados;

	@Column(name="id_config_instalador_integrador_nuvem_ciintnuv")
	public int IdConfigInstaladorIntegradorNuvem;
	
	@Column(name="nr_sequencia_instancia_nuvem_coninsint")
	int NrSequenciaInstanciaNuvem;
	
    @Column(name="tipo_sist_operacional_coninsint")
    @Length(max=30, message = " = ${validatedValue}, tamanho = ${validatedValue.length()}, máximo permitido = {max}")
    String TipoSO;
    
    @Column(name="sist_operacional_32_ou_64_bit_coninsint")
    @Length(max=6, message = " = ${validatedValue}, tamanho = ${validatedValue.length()}, máximo permitido = {max}")
    String So32ou64bit;
    
    @Column(name="espaco_livre_disco_coninsint")
    @Length(max=10, message = " = ${validatedValue}, tamanho = ${validatedValue.length()}, máximo permitido = {max}")
    String EspacoLivreDisco;
    
    @Column(name="memoria_ram_livre_coninsint")
    @Length(max=10, message = " = ${validatedValue}, tamanho = ${validatedValue.length()}, máximo permitido = {max}")
    String MemoriaRamLivre;
    
    @Column(name="versao_jre_coninsint")
    @Length(max=15, message = " = ${validatedValue}, tamanho = ${validatedValue.length()}, máximo permitido = {max}")
    String VersaoJRE;
    
    @Column(name="tipo_jre_coninsint")
    @Length(max=10, message = " = ${validatedValue}, tamanho = ${validatedValue.length()}, máximo permitido = {max}")
    String TipoJRE;
    
    @Column(name="versao_integrador_coninsint")
    @Length(max=10, message = " = ${validatedValue}, tamanho = ${validatedValue.length()}, máximo permitido = {max}")
    String VersaoIntegrador;
    
    @Column(name="disco_integrador_coninsint")
    @Length(max=1, message = " = ${validatedValue}, tamanho = ${validatedValue.length()}, máximo permitido = {max}")
    String DiscoIntegrador;
    
    @Column(name="dir_programfiles_coninsint")
    @Length(max=30, message = " = ${validatedValue}, tamanho = ${validatedValue.length()}, máximo permitido = {max}")
    String DirProgramfiles;
    
    @Column(name="endereco_ip_publico_servidor_coninsint")
    @Length(max=30, message = " = ${validatedValue}, tamanho = ${validatedValue.length()}, máximo permitido = {max}")
    String EnderecoIpPublicoServidor;
    
    @Column(name="porta_ip_aberta_coninsint")
    @Length(max=15, message = " = ${validatedValue}, tamanho = ${validatedValue.length()}, máximo permitido = {max}")
    String PortaIpAberta;
    
    @Column(name="frequencia_processamento_coninsint")
    @Length(max=10, message = " = ${validatedValue}, tamanho = ${validatedValue.length()}, máximo permitido = {max}")
    String FrequenciaProcessamento;
    
	@Column(name="dt_cadastro_coninsint")
	public LocalDate DtCadastro;
 	
	@Column(name="dt_desativacao_coninsint")
 	LocalDateTime DtDesativacao;
 	
	@Column(name="dt_alteracao_coninsint")
 	LocalDateTime DtAlteracao;
 	
	@Column(name="user_id_ususis")
	public int IdUsuario;
}
