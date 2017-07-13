package edunote.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edunote.pojos.Ser;

@Repository
public interface SerRepository extends JpaRepository<Ser, Long>{

}
