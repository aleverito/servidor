package edunote.servicios.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import edunote.pojos.Persona;
import edunote.repositorios.UsuarioRepository;
import edunote.servicios.UsuarioService;

@Service("usuarioService")
@Transactional
public class UsuarioServiceImpl implements UsuarioService{

	@Autowired
	private UsuarioRepository usuarioRepository;

	public Persona findById(Long id) {
		return usuarioRepository.findOne(id);
	}

	public Persona findByName(String nombre) {
		return usuarioRepository.findByNombre(nombre);
	}

	public void saveUser(Persona maestro) {
		usuarioRepository.save(maestro);
	}

	public void updateUser(Persona maestro){
		saveUser(maestro);
	}

	public void deleteUserById(Long id){
		usuarioRepository.delete(id);
	}

	public void deleteAllUsers(){
		usuarioRepository.deleteAll();
	}

	public List<Persona> findAllUsers(){
		return usuarioRepository.findAll();
	}

	public boolean isUserExist(Persona persona) {
		return findByName(persona.getNombre()) != null;
	}

}
