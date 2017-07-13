package edunote.servicios.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edunote.pojos.Ser;
import edunote.repositorios.SerRepository;
import edunote.servicios.SerService;

@Service
public class SerServiceImpl implements SerService{
	
	@Autowired
	SerRepository $$ser;

	@Override
	public Ser devolverPorId(Long id) {
		return $$ser.findOne(id);
	}

	@Override
	public void guardar(Ser ser) {
		$$ser.save(ser);
	}

	@Override
	public void actualizar(Ser ser) {
		guardar(ser);
	}

	@Override
	public List<Ser> devolverTodo() {
		return $$ser.findAll();
	}

	@Override
	public boolean siExiste(Ser ser) {
		Ser s=devolverPorId(ser.getId());
		return s!=null;
	}
	
	
}
