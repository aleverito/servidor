package edunote.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edunote.pojos.Saber;

@Repository
public interface SaberRepository extends JpaRepository<Saber, Long> {

}
