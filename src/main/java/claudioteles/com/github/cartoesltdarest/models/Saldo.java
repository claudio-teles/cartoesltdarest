package claudioteles.com.github.cartoesltdarest.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "saldo")
public class Saldo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4803588459010724071L;
	
	@Id
	@GeneratedValue(generator = "gerador_de_id_saldo", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "gerador_de_id_saldo", sequenceName = "sequencia_saldo", initialValue = 10, allocationSize = 1)
	@Column(nullable = false)
	private Long id;
	@Column(nullable = false, length = 15)
	private String user;
	@Column(nullable = false, length = 15)
	private String password;
	@Column(nullable = false)
	private Float disponivel;
	@Column(nullable = false)
	private Float receber;
	
	public Saldo() {
		super();
	}

	public Saldo(String user, String password, Float disponivel, Float receber) {
		super();
		this.user = user;
		this.password = password;
		this.disponivel = disponivel;
		this.receber = receber;
	}

	public Saldo(Long id, String user, String password, Float disponivel, Float receber) {
		super();
		this.user = user;
		this.password = password;
		this.disponivel = disponivel;
		this.receber = receber;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Float getDisponivel() {
		return disponivel;
	}

	public void setDisponivel(Float disponivel) {
		this.disponivel = disponivel;
	}

	public Float getReceber() {
		return receber;
	}

	public void setReceber(Float receber) {
		this.receber = receber;
	}

}
