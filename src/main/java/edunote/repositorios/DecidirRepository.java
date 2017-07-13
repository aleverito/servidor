package edunote.repositorios;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import edunote.pojos.Decidir;

@Repository
public interface DecidirRepository extends JpaRepository<Decidir, Long>{

}
