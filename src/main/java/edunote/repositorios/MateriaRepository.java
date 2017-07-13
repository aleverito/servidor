package edunote.repositorios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import edunote.pojos.Materia;
import java.lang.String;
import java.util.List;

@Repository
public interface MateriaRepository extends JpaRepository<Materia, Integer>{
	Materia findByNombre(String nombre);
}
