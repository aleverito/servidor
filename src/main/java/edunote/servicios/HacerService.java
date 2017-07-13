package edunote.servicios;

import java.util.List;

import edunote.pojos.Hacer;

public interface HacerService {
	Hacer devolverPorId(Long id);
	public void guardar(Hacer hacer);
	public void actualizar(Hacer hacer);
	List<Hacer> devolverTodo();
	boolean siExiste(Hacer hacer);
}
