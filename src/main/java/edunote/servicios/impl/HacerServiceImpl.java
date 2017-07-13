package edunote.servicios.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edunote.pojos.Hacer;
import edunote.repositorios.HacerRepository;
import edunote.servicios.HacerService;

@Service
public class HacerServiceImpl implements HacerService{
	
	@Autowired
	HacerRepository $$hacer;

	@Override
	public Hacer devolverPorId(Long id) {
		return $$hacer.findOne(id);
	}

	@Override
	public void guardar(Hacer hacer) {
		$$hacer.save(hacer);
	}

	@Override
	public void actualizar(Hacer hacer) {
		guardar(hacer);
	}

	@Override
	public List<Hacer> devolverTodo() {
		return $$hacer.findAll();
	}

	@Override
	public boolean siExiste(Hacer hacer) {
		Hacer ha=devolverPorId(hacer.getId());
		return ha!=null;
	}
	
	
}
