package edunote.servicios.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edunote.pojos.Registro;
import edunote.repositorios.RegistroRepository;
import edunote.servicios.RegistroService;

@Service
public class RegistroServiceImpl implements RegistroService{
	
	@Autowired
	RegistroRepository $$registro;

	@Override
	public List<Registro> devolverTodos() {
		return $$registro.findAll();
	}

	@Override
	public Registro devolverPorId(Long id) {
		return $$registro.findOne(id);
	}

	@Override
	public void guardar(Registro registro) {
		$$registro.save(registro);
	}

	@Override
	public void actualizar(Registro registro) {
		guardar(registro);
	}

	@Override
	public boolean existe(Integer IDcolegio, Integer IDciclo, Long IDestudiante,
			Integer IDcurso, Integer gestion) {
		return $$registro.Existe(IDcolegio, IDciclo, IDestudiante, IDcurso, gestion)!=null;
	}
	
	
}
