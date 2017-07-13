package edunote.servicios;

import java.util.List;

import edunote.pojos.Estudiante;

public interface EstudianteService {
	Estudiante findById(Long id);
	Estudiante findByRude(String rude);
	Estudiante findByCi(String ci);
	void saveEstudiante(Estudiante persona);
	void updateEstudiante(Estudiante persona);
	void deleteEstudianteByRude(String rude);
	void deleteAllEstudiantes();
	List<Estudiante> findAllEstudiantes();
	boolean isEstudianteExist(Estudiante persona);
}
