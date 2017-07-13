package edunote.repositorios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import edunote.pojos.Persona;
import java.lang.String;

@Repository
public interface UsuarioRepository extends JpaRepository<Persona, Long> {
    Persona findByNombre(String nombre);
}
