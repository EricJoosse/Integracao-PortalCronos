package pcronos.integracao.fornecedor.entidades;

import java.time.LocalDateTime;
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




@Entity // Para evitar org.hibernate.MappingException: Unknown entity que acontece quando usar class-level constraints com EL
@Table(name="Configuracao_Instalador_Integrador_Nuvem")
public class ConfigInstaladorIntegradorNuvem {

	public ConfigInstaladorIntegradorNuvem() {}
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_config_instalador_integrador_nuvem_ciintnuv")
	int Id;
	
	@Column(name="id_sistema_integrado_sisint")
	int IdSistemaIntegrado;
	// O seguinte serve apenas para evitar erro "javax.el.PropertyNotFoundException" pelo class-level Hibernate constraint com EL:
	public int getIdSistemaIntegrado() { return IdSistemaIntegrado; }
	
    @Column(name="tipo_sist_operacional_ciintnuv")
    @Length(max=30)
    String TipoSO;
    
    @Column(name="sist_operacional_32_ou_64_bit_ciintnuv")
    @Length(max=6)
    String So32ou64bit;
    
    @Column(name="espaco_livre_disco_ciintnuv")
    @Length(max=10)
    String EspacoLivreDisco;
    
    @Column(name="memoria_ram_livre_ciintnuv")
    @Length(max=10)
    String MemoriaRamLivre;
    
    @Column(name="versao_jre_ciintnuv")
    @Length(max=15)
    String VersaoJRE;
    
    @Column(name="tipo_jre_ciintnuv")
    @Length(max=10)
    String TipoJRE;
    
    @Column(name="versao_integrador_ciintnuv")
    @Length(max=10)
    String VersaoIntegrador;
    
    @Column(name="disco_integrador_ciintnuv")
    @Length(max=1)
    char DiscoIntegrador;
    
    @Column(name="dir_programfiles_ciintnuv")
    @Length(max=30)
    String DirProgramfiles;
    
    @Column(name="endereco_ip_publico_servidor_ciintnuv")
    @Length(max=30)
    String EnderecoIpPublicoServidor;
    
    @Column(name="porta_ip_aberta_ciintnuv")
    @Length(max=15)
    String PortaIpAberta;
    
    @Column(name="frequencia_processamento_ciintnuv")
    @Length(max=10)
    String FrequenciaProcessamento;
    

    
	@Column(name="dt_cadastro_ciintnuv")
 	LocalDateTime DtCadastro;
 	
	@Column(name="dt_desativacao_ciintnuv")
 	LocalDateTime DtDesativacao;
 	
	@Column(name="dt_alteracao_ciintnuv")
 	LocalDateTime DtAlteracao;
 	
	@Column(name="user_id_ususis")
	int IdUsuario;
}
