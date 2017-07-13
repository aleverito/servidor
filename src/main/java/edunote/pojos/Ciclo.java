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

import com.fasterxml.jackson.annotation.JsonManagedReference;
@Entity
@Table(schema="colegio")
public class Ciclo {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_ciclo")
	Integer id;
	@Column(nullable=false, unique=true)
	private String nombre;
	@OneToMany(fetch=FetchType.EAGER,mappedBy="ciclo")
	@JsonManagedReference
	private List<Registro> registros;
	public Ciclo() {}
	public Ciclo(String nombre) {
		this.nombre = nombre;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public boolean equals(Object arg) {
		return ((Ciclo)arg).getNombre().equalsIgnoreCase(this.getNombre());
	}
	public List<Registro> getRegistros() {
		return registros;
	}
	public void setRegistros(List<Registro> registros) {
		this.registros = registros;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String toString(){return String.format("Colegio: %s",this.nombre);}
}
