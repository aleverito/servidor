package edunote.pojos;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
@Entity
@Table(schema="colegio")
public class Maestro  extends Persona {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(nullable=false,unique=true)
	private Integer rda;
	@OneToMany(fetch=FetchType.EAGER,mappedBy="maestro")
	@JsonIgnore
	private List<Nota> notas;
	
	@Transient
	public Integer rda_old;

	public Maestro() {	}
	public Maestro(Integer rda) {	setRda(rda);	}
	public Maestro(String ci, String nombre, String ap, String am, Integer telefono) {
		super(ci, nombre, ap, am, telefono);
	}
	public Maestro(Integer rda,String ci, String nombre, String ap, String am, Integer telefono) {
		super(ci, nombre, ap, am, telefono);
		setRda(rda);
	}
	public Integer getRda() {
		return rda;
	}
	public void setRda(Integer rda) {
		this.rda = rda;
	}
	public List<Nota> getNotas() {
		return notas;
	}
	public void setNotas(List<Nota> notas) {
		this.notas = notas;
	}
	public boolean addNota(Nota nota){
		return notas.add(nota);
	}
}
