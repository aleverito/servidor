package edunote.pojos;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;
@Entity
@Table(schema="colegio")
public class Colegio {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_col")
	Integer id;
	@Column(nullable=false, unique=true)
	private String nombre;
	@Column
	private String direccion;
	@Column
	private byte[] escudo;
	@OneToMany(fetch=FetchType.EAGER,mappedBy="colegio")
	@JsonManagedReference
	private List<Registro> registros;
	public Colegio() {	}
	public Colegio(String nombre, String direccion, byte[] escudo) {
		this.nombre = nombre;
		this.direccion = direccion;
		this.escudo = escudo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public byte[] getEscudo() {
		return escudo;
	}
	public void setEscudo(byte[] escudo) {
		this.escudo = escudo;
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
	
}
