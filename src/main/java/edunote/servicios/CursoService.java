package edunote.servicios;

import java.util.List;

import edunote.pojos.Curso;

public interface  CursoService{
	Curso findById(Integer id);
	Curso findByNombre(String nombre);
	void guardar(Curso curso);
	void actualizar(Curso curso);
	void eliminarPorId(Integer id);
	 List<Curso> devolverTodos();
	boolean siExiste(Curso curso);
	List<Curso> devolverPorEstado(boolean estado);
}
