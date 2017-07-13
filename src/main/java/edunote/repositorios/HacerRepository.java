package edunote.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edunote.pojos.Hacer;

@Repository
public interface HacerRepository extends JpaRepository<Hacer, Long>{
}
