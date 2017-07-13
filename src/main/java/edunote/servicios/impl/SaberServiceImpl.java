package edunote.servicios.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edunote.pojos.Saber;
import edunote.repositorios.SaberRepository;
import edunote.servicios.SaberService;

@Service
public class SaberServiceImpl implements SaberService {
	
	@Autowired
	SaberRepository $$saber;

	@Override
	public Saber devolverPorId(Long id) {
		return $$saber.findOne(id);
	}

	@Override
	public void guardar(Saber saber) {
		$$saber.save(saber);
	}

	@Override
	public void actualizar(Saber saber) {
		guardar(saber);
	}

	@Override
	public List<Saber> devolverTodo() {
		return $$saber.findAll();
	}

	@Override
	public boolean siExiste(Saber saber) {
		Saber sa = devolverPorId(saber.getId());
		return sa!=null;
	}
	
	
}
