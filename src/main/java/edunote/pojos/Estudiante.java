package edunote.pojos;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
@Entity
@Table(schema="colegio")
@SuppressWarnings("serial")
public class Estudiante extends Persona{
	@Column(nullable=false,unique=true)
	private String rude;
	@OneToMany(fetch=FetchType.EAGER, mappedBy="estudiante")
	@JsonManagedReference
	private List<Registro> registros;
	@Transient
	public Integer rude_old;
	public Estudiante() {	super();	}
	public Estudiante(String rude) {	setRude(rude);	}
	public Estudiante(String rude, String ci, String nombre, String ap, String am, Integer telefono) {
		super( ci, nombre, ap, am, telefono);
		setRude(rude);
	}
	public String getRude() {
		return rude;
	}
	public void setRude(String rude) {
		this.rude = rude;
	}
	public List<Registro> getRegistros() {
		return registros;
	}
	public void setRegistros(List<Registro> registros) {
		this.registros = registros;
	}
	
}
