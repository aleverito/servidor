package edunote.servicios;

import java.util.List;

import edunote.pojos.Ser;

public interface SerService {
	Ser devolverPorId(Long id);
	public void guardar(Ser ser);
	public void actualizar(Ser ser);
	List<Ser> devolverTodo();
	boolean siExiste(Ser ser);
}
