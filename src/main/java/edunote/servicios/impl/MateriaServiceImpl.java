package edunote.servicios.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edunote.pojos.Materia;
import edunote.repositorios.MateriaRepository;
import edunote.servicios.MateriaService;

@Service
public class MateriaServiceImpl implements MateriaService {
	
	@Autowired
	MateriaRepository $$materia;

	@Override
	public Materia devolverPorId(Integer id) {
		return $$materia.findOne(id);
	}

	@Override
	public void guardar(Materia materia) {
		$$materia.save(materia);
	}

	@Override
	public void actualizar(Materia materia) {
		guardar(materia);
	}

	@Override
	public List<Materia> devolverTodo() {
		return $$materia.findAll();
	}

	@Override
	public boolean siExiste(Materia materia) {
		Materia mat = devolverPorId(materia.getId());
		return mat != null;
	}

	@Override
	public Materia devolverPorNombre(String nombre) {
		return $$materia.findByNombre(nombre);
	}
	
}
