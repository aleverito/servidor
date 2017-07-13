package edunote.servicios.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edunote.pojos.Maestro;
import edunote.repositorios.MaestroRepository;
import edunote.servicios.MaestroService;

@Service("maestroService")
@Transactional
public class MaestroServiceImpl implements MaestroService{
	@Autowired
	private MaestroRepository $$maestro;
	
	public Maestro findByRda(Integer rda) {
		return $$maestro.findByRda(rda);
	}
	public Maestro findByName(String nombre) {
		return $$maestro.findByNombre(nombre);
	}
	public void saveMaestro(Maestro maestro) {
		$$maestro.save(maestro);
	}
	public void updateMaestro(Maestro maestro) {
		saveMaestro(maestro);
	}
	public void deleteMaestroById(Long id) {
		$$maestro.delete(id);
	}
	public void delete(Maestro maestro) {
		$$maestro.delete(maestro);
	}
	public void deleteAllMaestros() {
		$$maestro.deleteAll();		
	}
	public List<Maestro> findAllMaestros() {
		return $$maestro.findAll();
	}
	public Maestro findById(Long id) {
		return $$maestro.findById(id);
	}
	public boolean isMaestroExist(Maestro maestro) {
		Maestro m = $$maestro.findByRda(maestro.getRda());
		return m!=null;
	}
	
}
