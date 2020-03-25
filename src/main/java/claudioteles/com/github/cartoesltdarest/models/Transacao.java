package claudioteles.com.github.cartoesltdarest.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "transacao")
public class Transacao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 173604237181291010L;
	
	@Id
	@GeneratedValue(generator = "gerador_de_id_transacao", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "gerador_de_id_transacao", sequenceName = "sequencia_transacao", initialValue = 459355, allocationSize = 1)
	@Column(nullable = false)
	private Long nsu;
	@Column(nullable = false)
	private Float valor;
	@Column(nullable = true)
	private Float liquido;
	@Column(nullable = false, length = 20)
	private String bandeira;
	@Column(nullable = false, length = 20)
	private String modalidade;
	@Column(nullable = true)
	private Date horario;
	@Column(nullable = true)
	private Date disponivel;
	
	public Transacao() {
		super();
	}

	public Transacao(Float valor, String bandeira, String modalidade) {
		super();
		this.valor = valor;
		this.bandeira = bandeira;
		this.modalidade = modalidade;
	}

	public Long getNsu() {
		return nsu;
	}

	public void setNsu(Long nsu) {
		this.nsu = nsu;
	}

	public Float getValor() {
		return valor;
	}

	public void setValor(Float valor) {
		this.valor = valor;
	}

	public Float getLiquido() {
		return liquido;
	}

	public void setLiquido(Float liquido) {
		this.liquido = liquido;
	}

	public String getBandeira() {
		return bandeira;
	}

	public void setBandeira(String bandeira) {
		this.bandeira = bandeira;
	}

	public String getModalidade() {
		return modalidade;
	}

	public void setModalidade(String modalidade) {
		this.modalidade = modalidade;
	}

	public Date getHorario() {
		return horario;
	}

	public void setHorario(Date horario) {
		this.horario = horario;
	}

	public Date getDisponivel() {
		return disponivel;
	}

	public void setDisponivel(Date disponivel) {
		this.disponivel = disponivel;
	}

}
