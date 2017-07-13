package edunote.pojos;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(schema="colegio")
public class Registro {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="codInscripcion")
	Long id;
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="insEst",nullable=false)
	Estudiante estudiante;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="insColegio",nullable=false)
	Colegio colegio;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="insCiclo",nullable=false)
	Ciclo ciclo;

	@Column(nullable=false)
	Integer gestion;
	
	@Column(nullable=false,columnDefinition="boolean default false")
	boolean retirado=false;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="ref_curso",nullable=true)
	Curso curso;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="registro")
	@JsonManagedReference
	private List<Nota> notas;
	
	public Estudiante getEstudiante() {
		return estudiante;
	}
	public void setEstudiante(Estudiante estudiante) {
		this.estudiante = estudiante;
	}
	public Colegio getColegio() {
		return colegio;
	}
	public void setColegio(Colegio colegio) {
		this.colegio = colegio;
	}
	public Ciclo getCiclo() {
		return ciclo;
	}
	public void setCiclo(Ciclo ciclo) {
		this.ciclo = ciclo;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getGestion() {
		return gestion;
	}
	public void setGestion(Integer gestion) {
		this.gestion = gestion;
	}
	public boolean isRetirado() {
		return retirado;
	}
	public void setRetirado(boolean retirado) {
		this.retirado = retirado;
	}
	public Curso getCurso() {
		return curso;
	}
	public void setCurso(Curso curso) {
		this.curso = curso;
	}
	public List<Nota> getNotas() {
		return notas;
	}
	public void setNotas(List<Nota> notas) {
		this.notas = notas;
	}
}
