package edunote.servicios.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edunote.pojos.Curso;
import edunote.repositorios.CursoRepository;
import edunote.servicios.CursoService;
@Service
public class CursoServiceImpl implements CursoService {
	@Autowired
	CursoRepository $$curso;
	
	@Override
	public Curso findById(Integer id) {
		return $$curso.findOne(id);
	}

	@Override
	public void guardar(Curso curso) {
		$$curso.save(curso);
		
	}

	@Override
	public void actualizar(Curso curso) {
		guardar(curso);
	}

	

	@Override
	public List<Curso> devolverTodos() {
		return $$curso.findAll();
	}

	@Override
	public boolean siExiste(Curso curso) {
		Curso c = $$curso.findByNombre(curso.getNombre());
		return c!=null;
	}

	@Override
	public List<Curso> devolverPorEstado(boolean estado) {
		return $$curso.findByEstado(estado);
		
	}

	@Override
	public void eliminarPorId(Integer id) {
		$$curso.delete(id);
		
	}

	@Override
	public Curso findByNombre(String nombre) {
		
		return $$curso.findByNombre(nombre);
	}

}
