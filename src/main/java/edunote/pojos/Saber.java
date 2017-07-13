package edunote.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(schema="colegio")
public class Saber extends Nota{
//	@Id
//	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_saber")
	Long id;
	@Column(nullable=false,name="n_examen1")
	Integer examen1;
	@Column(nullable=false,name="n_examen2")
	Integer examen2;
	@Column(nullable=false,name="n_examen3")
	Integer examen3;
	@Column(nullable=false,name="n_examen4")
	Integer examen4;
	@Column(nullable=false,name="n_examen5")
	Integer examen5;
	@Column
	Double promedio;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getExamen1() {
		return examen1;
	}
	public void setExamen1(Integer examen1) {
		this.examen1 = examen1;
	}
	public Integer getExamen2() {
		return examen2;
	}
	public void setExamen2(Integer examen2) {
		this.examen2 = examen2;
	}
	public Integer getExamen3() {
		return examen3;
	}
	public void setExamen3(Integer examen3) {
		this.examen3 = examen3;
	}
	public Integer getExamen4() {
		return examen4;
	}
	public void setExamen4(Integer examen4) {
		this.examen4 = examen4;
	}
	public Integer getExamen5() {
		return examen5;
	}
	public void setExamen5(Integer examen5) {
		this.examen5 = examen5;
	}
	public Double getPromedio() {
		return promedio;
	}
	public void setPromedio(Double promedio) {
		this.promedio = promedio;
	}
	
}
