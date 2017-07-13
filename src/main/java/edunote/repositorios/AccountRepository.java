package edunote.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edunote.pojos.Account;
import edunote.pojos.Persona;

import java.util.List;
import java.lang.String;

@Repository
public interface AccountRepository extends JpaRepository<Account, String>{
	List<Account> findByPersona(Persona persona);
	Account findByUsername(String username);
}
