package edunote.pojos;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class Director extends Maestro{
	@Temporal(TemporalType.DATE)
	Calendar fecha_ini;

	public Calendar getFecha_ini() {
		return fecha_ini;
	}
	public void setFecha_ini(Calendar fecha_ini) {
		this.fecha_ini = fecha_ini;
	}
	public String toString(){return String.format("Director: %s %s %s con rda: [%s]",getNombre(),getAp(),getAm(),getRda());}
	
}
