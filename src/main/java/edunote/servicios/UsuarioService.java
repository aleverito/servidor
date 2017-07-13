package edunote.servicios;


import java.util.List;

import edunote.pojos.Persona;

public interface UsuarioService {
	
	Persona findById(Long id);
	Persona findByName(String name);
	void saveUser(Persona persona);
	void updateUser(Persona persona);
	void deleteUserById(Long id);
	void deleteAllUsers();
	List<Persona> findAllUsers();
	boolean isUserExist(Persona persona);
}