package edunote.servicios;

import java.util.List;

import edunote.pojos.Decidir;

public interface DecidirService {
	Decidir devolverPorId(Long id);
	public void guardar(Decidir decidir);
	public void actualizar(Decidir decidir);
	List<Decidir> devolverTodo();
	boolean siExiste(Decidir decidir);
}
