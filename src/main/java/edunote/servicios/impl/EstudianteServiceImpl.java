package edunote.servicios.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edunote.pojos.Estudiante;
import edunote.pojos.Maestro;
import edunote.repositorios.EstudianteRepository;
import edunote.servicios.EstudianteService;
@Service("estudianteService")
@Transactional
public class EstudianteServiceImpl implements EstudianteService{
	@Autowired
	EstudianteRepository $$estudiante;
	@Override
	public Estudiante findById(Long id) {
		
		return $$estudiante.findById(id);
	}
	public Estudiante findByRude(String rude) {
		return $$estudiante.findOne(rude);
	}
	@Override
	public Estudiante findByCi(String ci) {
		return $$estudiante.findByCi(ci);
	}

	public void saveEstudiante(Estudiante persona) {
		$$estudiante.save(persona);
	}

	public void updateEstudiante(Estudiante persona) {
		saveEstudiante(persona);
	}

	@Override
	public void deleteEstudianteByRude(String rude) {
		$$estudiante.delete(rude);
	}

	@Override
	public void deleteAllEstudiantes() {
		$$estudiante.findAll();
	}

	@Override
	public List<Estudiante> findAllEstudiantes() {
		return $$estudiante.findAll();
	}

	@Override
	public boolean isEstudianteExist(Estudiante est) {
		Estudiante m = $$estudiante.findByRude(est.getRude());
		return m!=null;
	}
}
