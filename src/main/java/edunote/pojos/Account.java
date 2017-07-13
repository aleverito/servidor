package edunote.pojos;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="cuenta",schema="colegio")
public class Account implements Serializable{
	
	private static final long serialVersionUID = 7518458972945330038L;
	@Id
	@NotEmpty
	@Column(name="username",nullable=false,unique=true)
	String username;
	
	@NotEmpty
	@Column(name="password",nullable=false)
	String password;
	
	@ColumnDefault(value="1")
	@Column(name="estado",nullable=false)
	Integer status;
	
	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(nullable=false,unique=true)
	Persona persona;
	
	public Account() {	}

	public Account(String username, String password, Integer status) {
		this.username = username;
		this.password = password;
		this.status = status;
	}
	
	public Account(String username, String password, Integer status, Persona persona) {
		this.username = username;
		this.password = password;
		this.status = status;
		this.persona = persona;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}
}
