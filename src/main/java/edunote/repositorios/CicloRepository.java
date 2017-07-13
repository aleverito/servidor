package edunote.repositorios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import edunote.pojos.Ciclo;
import java.lang.String;
import java.util.List;

@Repository
public interface CicloRepository extends JpaRepository<Ciclo, Integer>{ 
	Ciclo findByNombre(String nombre);
}
