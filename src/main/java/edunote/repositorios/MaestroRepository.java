package edunote.repositorios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import edunote.pojos.Maestro;
import java.lang.String;
import java.lang.Integer;
import java.lang.Long;

@Repository
public interface MaestroRepository  extends JpaRepository<Maestro, Long>{
	Maestro findByNombre(String nombre);
	Maestro findByRda(Integer rda);
	Maestro findById(Long id);
}
