package edunote.servicios;

import java.util.List;

import edunote.pojos.Materia;

public interface MateriaService {
	Materia devolverPorId(Integer id);
	Materia devolverPorNombre(String nombre);
	public void guardar(Materia materia);
	public void actualizar(Materia materia);
	List<Materia> devolverTodo();
	boolean siExiste(Materia materia);
}
