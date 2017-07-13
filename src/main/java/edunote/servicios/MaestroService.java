package edunote.servicios;

import java.util.List;

import edunote.pojos.*;

public interface MaestroService{
	Maestro findByRda(Integer rda);
	Maestro findById(Long id);
	Maestro findByName(String name);
	void saveMaestro(Maestro persona);
	void updateMaestro(Maestro persona);
//	void deleteMaestroByRda(Integer id);
	void deleteMaestroById(Long id);
	void delete(Maestro maestro);
	void deleteAllMaestros();
	List<Maestro> findAllMaestros();
	boolean isMaestroExist(Maestro persona);
}
