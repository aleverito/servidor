package edunote.pojos;

import java.io.Serializable;
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
public class Bimestre  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_bim")
	Integer id;
	@Column(nullable=false, unique=true)
	String nombre;
	@OneToMany(fetch=FetchType.EAGER, mappedBy="bimestre")
	@JsonManagedReference
	private List<Nota> notas;
	
	public Bimestre(){}
	public Bimestre(String nombre){ this.nombre = nombre;}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
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
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public boolean equals(Object a){ return ((Bimestre)a).getNombre().equalsIgnoreCase(getNombre());}
	public String toString(){return this.getNombre();}
	
}
