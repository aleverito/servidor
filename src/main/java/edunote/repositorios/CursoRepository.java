package edunote.repositorios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import edunote.pojos.Curso;
import java.lang.Boolean;
import java.util.List;
import java.lang.String;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Integer>{// clase generica
	List<Curso> findByEstado(Boolean estado);
	Curso findByNombre(String nombre);
}
