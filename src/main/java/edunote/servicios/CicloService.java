package edunote.servicios;

import edunote.pojos.Ciclo;
import java.util.*;

public interface CicloService {
	Ciclo devolverPorId(Integer id);
	Ciclo findByName(String nombre);
	void guardarCiclo(Ciclo ciclo);
	void actualizarCiclo(Ciclo ciclo);
	void eliminarPorId(Integer id);
	List<Ciclo> devolverTodos();
	boolean siExisteCiclo(Ciclo ciclo);
}
