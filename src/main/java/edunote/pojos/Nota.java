package edunote.pojos;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
@SuppressWarnings("serial")
@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@Table(schema="colegio")
public class Nota implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_nota")
	private Long id;
	@Column(name="pf",nullable=true)
	private Double pf;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="ref_mat",nullable=false)
	private Maestro maestro;
	
	@ManyToOne
	@JoinColumn(name="ref_profe",nullable=false)
	private Materia materia;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="ref_bim",nullable=false)
	private Bimestre bimestre;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="registro",nullable=false)
	private Registro registro;
	
	@Column(nullable=false,columnDefinition="boolean default true")
	private boolean incorporado = true;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Double getPf() {
		return pf;
	}
	public void setPf(Double pF) {
		pf = pF;
	}
	public Materia getMateria() {
		return materia;
	}
	public void setMateria(Materia materia) {
		this.materia = materia;
	}
	public Maestro getMaestro() {
		return maestro;
	}
	public void setMaestro(Maestro maestro) {
		this.maestro = maestro;
	}
	public Bimestre getBimestre() {
		return bimestre;
	}
	public void setBimestre(Bimestre bimestre) {
		this.bimestre = bimestre;
	}
	public boolean isIncorporado() {
		return incorporado;
	}
	public void setIncorporado(boolean incorporado) {
		this.incorporado = incorporado;
	}
	public Registro getRegistro() {
		return registro;
	}
	public void setRegistro(Registro registro) {
		this.registro = registro;
	}
	
}
