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
public class Ser extends Nota{
//	@Id
//	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_ser")
	private Long id;
	@Column(nullable=false,name="n_respecto")
	private Integer respeto;
	@Column(nullable=false,name="n_solidaridad")
	private Integer solidaridad;
	@Column(nullable=false,name="n_responsabilidad")
	private Integer responsabilidad;
	@Column(nullable=false,name="n_puntualidad")
	private Integer puntualidad;
	@Column
	private Double promedio;
	
	public Ser() {
		super();
	}

	public Ser(Long id, Integer respeto, Integer solidaridad, Integer responsabilidad, Integer puntualidad,
			Double promedio) {
		super();
		this.id = id;
		this.respeto = respeto;
		this.solidaridad = solidaridad;
		this.responsabilidad = responsabilidad;
		this.puntualidad = puntualidad;
		this.promedio = promedio;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getRespeto() {
		return respeto;
	}

	public void setRespeto(Integer respeto) {
		this.respeto = respeto;
	}

	public Integer getSolidaridad() {
		return solidaridad;
	}

	public void setSolidaridad(Integer solidaridad) {
		this.solidaridad = solidaridad;
	}

	public Integer getResponsabilidad() {
		return responsabilidad;
	}

	public void setResponsabilidad(Integer responsabilidad) {
		this.responsabilidad = responsabilidad;
	}

	public Integer getPuntualidad() {
		return puntualidad;
	}

	public void setPuntualidad(Integer puntualidad) {
		this.puntualidad = puntualidad;
	}

	public Double getPromedio() {
		return promedio;
	}

	public void setPromedio(Double promedio) {
		this.promedio = promedio;
	}
}
