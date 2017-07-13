package edunote.pojos;
import org.hibernate.validator.constraints.NotEmpty;

import edunote.util.Estado;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@Table(schema="colegio")
public class Persona implements Serializable{
	
	private static final long serialVersionUID = -3067753855501679461L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="idper")
	private Long id;
	
	@NotEmpty
	@Pattern(regexp="^([0-9]{7}|[0-9]{7}-[a-zA-Z])$",message="debe ser un numero o numero mas una letra:  [numero]-[Letra]")
	@Column(name="ci", nullable=false)
	private String ci;
	@Transient
	private String ci_old;

	@NotEmpty
	@Column(name="nombre", nullable=false,length=50)
	private String nombre;
	
	@Column(name="ap", nullable=false)
	private String ap;
	
	@Column(name="am", nullable=false)
	private String am;

	@Column(name="telefono", nullable=false)
	private Integer telefono;
	
	@Column
	private byte[] foto;
	@Column
	private String media;
	
	public Persona() {	}

	public Persona(String ci, String nombre, String ap, String am, Integer telefono) {
		this.ci = ci;
		this.nombre = nombre;
		this.ap = ap;
		this.am = am;
		this.telefono = telefono;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String name) {
		this.nombre = name;
	}
	
	public String getAp() {
		return ap;
	}

	public void setAp(String ap) {
		this.ap = ap;
	}
	public String getCi() {
		return ci;
	}

	public void setCi(String ci) {
		this.ci = ci;
	}

	public String getAm() {
		return am;
	}

	public void setAm(String am) {
		this.am = am;
	}

	public Integer getTelefono() {
		return telefono;
	}

	public void setTelefono(Integer telefono) {
		this.telefono = telefono;
	}
	

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}
	
	public String getCi_old() {
		return ci_old;
	}

	public void setCi_old(String ci_old) {
		this.ci_old = ci_old;
	}

	public String getMedia() {
		return media;
	}

	public void setMedia(String media) {
		this.media = media;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Persona persona = (Persona) o;
		
		if (Double.compare(persona.telefono, telefono) != 0) return false;
		if (id != null ? !id.equals(persona.id) : persona.id != null) return false;
		if (nombre != null ? !nombre.equals(persona.nombre) : persona.nombre != null) return false;
		return ap != null ? ap.equals(persona.ap) : persona.ap == null;
	}

	@Override
	public int hashCode() {
		int result;
		long temp;
		result = id != null ? id.hashCode() : 0;
		result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
		result = 31 * result + (ap != null ? ap.hashCode() : 0);
		temp = Double.doubleToLongBits(telefono);
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public String toString() {
		return String.format("User [ci = %s] nombre= %s , ap = %s , am = %s , fono = %s\n" ,ci,nombre,ap,am,telefono);
	}
}
