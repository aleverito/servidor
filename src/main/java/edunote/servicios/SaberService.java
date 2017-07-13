package edunote.servicios;

import java.util.List;

import edunote.pojos.Saber;

public interface SaberService {
	Saber devolverPorId(Long id);
	public void guardar(Saber saber);
	public void actualizar(Saber saber);
	List<Saber> devolverTodo();
	boolean siExiste(Saber saber);
}
