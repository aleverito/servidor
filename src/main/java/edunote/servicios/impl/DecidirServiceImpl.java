package edunote.servicios.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edunote.pojos.Decidir;
import edunote.repositorios.DecidirRepository;
import edunote.servicios.DecidirService;

@Service
public class DecidirServiceImpl implements DecidirService{
	
	@Autowired
	DecidirRepository $$decidir;

	@Override
	public Decidir devolverPorId(Long id) {
		return $$decidir.findOne(id);
	}

	@Override
	public void guardar(Decidir decidir) {
		$$decidir.save(decidir);
	}

	@Override
	public void actualizar(Decidir decidir) {
		guardar(decidir);
	}

	@Override
	public List<Decidir> devolverTodo() {
		return $$decidir.findAll();
	}

	@Override
	public boolean siExiste(Decidir decidir) {
		Decidir de = $$decidir.findOne(decidir.getId());
		return de != null;
	}
	
	
	
}
