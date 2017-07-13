package edunote.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema="colegio")
public class Decidir extends Nota{
//	@Id
//	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_decidir")
	Long id;
	@Column(name="n_creatividad",nullable=false)
	Integer creatividad; 
	@Column(name="n_desarrollo_psp",nullable=false)
	Integer psp;
	@Column(name="n_autodeterminacion",nullable=false)
	Integer autodeterminacion;
	@Column(name="n_desarrollo_critico",nullable=false)
	Integer critico;
	@Column
	Double promedio;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getCreatividad() {
		return creatividad;
	}
	public void setCreatividad(Integer creatividad) {
		this.creatividad = creatividad;
	}
	public Integer getPsp() {
		return psp;
	}
	public void setPsp(Integer psp) {
		this.psp = psp;
	}
	public Integer getAutodeterminacion() {
		return autodeterminacion;
	}
	public void setAutodeterminacion(Integer autodeterminacion) {
		this.autodeterminacion = autodeterminacion;
	}
	public Integer getCritico() {
		return critico;
	}
	public void setCritico(Integer critico) {
		this.critico = critico;
	}
	public Double getPromedio() {
		return promedio;
	}
	public void setPromedio(Double promedio) {
		this.promedio = promedio;
	}
	
	
}
