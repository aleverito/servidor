package edunote.pojos;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;
@Entity
@Table(schema="colegio")
public class Materia implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_m")
	Integer id;
	@Column(nullable=false, unique=true)
	String nombre;
	@Column
	Integer horas;
	@OneToMany(fetch=FetchType.EAGER,mappedBy="materia")
	@JsonManagedReference
	private List<Nota> notas;
	
	public Materia() {	}
	public Materia(String nombre, Integer horas) {
		this.nombre = nombre;
		this.horas = horas;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Integer getHoras() {
		return horas;
	}
	public void setHoras(Integer horas) {
		this.horas = horas;
	}
	public boolean addNota(Nota nota){
		return notas.add(nota);
	}
	public List<Nota> getNotas() {
		return notas;
	}
	public void setNotas(List<Nota> notas) {
		this.notas = notas;
	}
	public boolean equals(Object obj) {
		return this.getNombre().equalsIgnoreCase(((Materia)obj).getNombre());
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
}
