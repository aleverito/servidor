package edunote.servicios.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edunote.pojos.Colegio;
import edunote.repositorios.ColegioRepository;
import edunote.servicios.ColegioService;
@Service
public class ColegioServiceImpl implements ColegioService {
	
	@Autowired
	ColegioRepository $$colegio;
	
	@Override
	public Colegio devolverPorId(Integer id) {
		return $$colegio.findOne(id);
	}

	@Override
	public Colegio devolverPorNombre(String nombre) {
		return $$colegio.findByNombre(nombre);
	}

	@Override
	public void guardar(Colegio colegio) {
		$$colegio.save(colegio);
		
	}

	@Override
	public void actualizar(Colegio colegio) {
		guardar(colegio);
	}

	@Override
	public List<Colegio> devolverTodo() {
		return $$colegio.findAll();
	}

	@Override
	public boolean siExiste(Colegio colegio) {
		Colegio cole = $$colegio.findByNombre(colegio.getNombre());
		return cole!=null;
	}

}
