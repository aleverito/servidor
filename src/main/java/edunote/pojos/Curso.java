package edunote.pojos;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

import com.fasterxml.jackson.annotation.JsonManagedReference;
@Entity
@Table(schema="colegio")
public class Curso {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_curso")
	Integer id;
	@Column(nullable=false, unique=true)
	private String nombre;
	@ColumnDefault(value = "true")
	@Column(nullable = false)
	private Boolean estado=true;
	@OneToMany(fetch=FetchType.EAGER,mappedBy="curso")
	@JsonManagedReference
	private List<Registro> registros;
	
	public Curso(){}
	public Curso(String nombre){this.nombre = nombre;}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public boolean equals(Object arg){
		return ((Curso)arg).getNombre().equalsIgnoreCase(this.getNombre());
	}
	public String toString(){return String.format("Curso: ", this.getNombre());}
	public Boolean getEstado() {
		return estado;
	}
	public void setEstado(Boolean estado) {
		this.estado = estado;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public List<Registro> getRegistros() {
		return registros;
	}
	public void setRegistros(List<Registro> registros) {
		this.registros = registros;
	}
	
}
