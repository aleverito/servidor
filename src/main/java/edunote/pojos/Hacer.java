package edunote.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(schema="colegio")
public class Hacer extends Nota{
//	@Id
//	@GeneratedValue(strategy=GenerationType.IDENTITY)
//	@Column(name="id_hacer")
//	Long id;
	@Column(name="n_tpractico",nullable=false)
	Integer pratico;
	@Column(name="n_disertacion",nullable=false)
	Integer disertacion;
	@Column(name="n_cuadernos",nullable=false)
	Integer cuadernos;
	@Column(name="n_album",nullable=false)
	Integer album;
	@Column(name="n_resumenes",nullable=false)
	Integer resumenes;
	@Column(name="n_participacion",nullable=false)
	Integer participacion;
	@Column
	Double promedio;
	public Integer getPratico() {
		return pratico;
	}
	public void setPratico(Integer pratico) {
		this.pratico = pratico;
	}
	public Integer getDisertacion() {
		return disertacion;
	}
	public void setDisertacion(Integer disertacion) {
		this.disertacion = disertacion;
	}
	public Integer getCuadernos() {
		return cuadernos;
	}
	public void setCuadernos(Integer cuadernos) {
		this.cuadernos = cuadernos;
	}
	public Integer getAlbum() {
		return album;
	}
	public void setAlbum(Integer album) {
		this.album = album;
	}
	public Integer getResumenes() {
		return resumenes;
	}
	public void setResumenes(Integer resumenes) {
		this.resumenes = resumenes;
	}
	public Integer getParticipacion() {
		return participacion;
	}
	public void setParticipacion(Integer participacion) {
		this.participacion = participacion;
	}
	public Double getPromedio() {
		return promedio;
	}
	public void setPromedio(Double promedio) {
		this.promedio = promedio;
	}
}
