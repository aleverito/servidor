package edunote.servicios.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edunote.pojos.Ciclo;
import edunote.repositorios.CicloRepository;
import edunote.servicios.CicloService;

@Service
public class CicloServiceImpl implements CicloService{
	
	@Autowired
	CicloRepository $$ciclo;
	
	@Override
	public Ciclo devolverPorId(Integer id) {
		return $$ciclo.findOne(id);
	}

	@Override
	public void guardarCiclo(Ciclo ciclo) {
		$$ciclo.save(ciclo);
		
	}

	@Override
	public void actualizarCiclo(Ciclo ciclo) {
		guardarCiclo(ciclo);
		
	}

	@Override
	public void eliminarPorId(Integer id) {
		$$ciclo.delete(id);
		
	}

	@Override
	public List<Ciclo> devolverTodos() {
		return $$ciclo.findAll();
	}

	@Override
	public boolean siExisteCiclo(Ciclo ciclo) {
		Ciclo ci=$$ciclo.findByNombre(ciclo.getNombre());
		return ci!=null;
	}

	@Override
	public Ciclo findByName(String nombre) {
		return $$ciclo.findByNombre(nombre);
	}

}
