package edunote.servicios;

import java.util.List;

import edunote.pojos.Colegio;

public interface ColegioService {
	Colegio devolverPorId(Integer id);
	Colegio devolverPorNombre(String nombre);
	public void guardar(Colegio colegio);
	public void actualizar(Colegio colegio);
	List<Colegio> devolverTodo();
	boolean siExiste(Colegio colegio);
}
