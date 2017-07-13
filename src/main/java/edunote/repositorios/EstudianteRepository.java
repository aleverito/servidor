package edunote.repositorios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import edunote.pojos.Estudiante;
import java.lang.String;
import java.lang.Long;
@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante, String>{
	Estudiante findById(Long id);
	Estudiante findByCi(String ci);
	Estudiante findByRude(String rude);
}
