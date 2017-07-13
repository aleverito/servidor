package edunote.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edunote.pojos.Registro;

@Repository
public interface RegistroRepository extends JpaRepository<Registro, Long>{
	@Query(value="SELECT r FROM Registro r WHERE r.colegio.id = ?1 and r.ciclo.id= ?2 and r.estudiante.id = ?3 and r.curso.id= ?4 and r.gestion = ?5")
	public Registro Existe(	Integer IDcolegio,
							Integer IDciclo,
							Long IDestudiante,
							Integer IDcurso,
							Integer gestion);
}
