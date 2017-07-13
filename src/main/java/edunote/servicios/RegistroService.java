package edunote.servicios;

import java.util.List;

import edunote.pojos.Registro;

public interface RegistroService {
	
	public List<Registro> devolverTodos();
	public Registro devolverPorId(Long id);
	public boolean existe(Integer IDcolegio,Integer IDciclo,Long IDest,Integer IDcurso,Integer gestion); 
	public void guardar(Registro registro);
	public void actualizar(Registro registro);
}
