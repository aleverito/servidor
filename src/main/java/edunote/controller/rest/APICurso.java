package edunote.controller.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import edunote.pojos.Curso;
import edunote.pojos.Estudiante;
import edunote.pojos.Persona;
import edunote.servicios.impl.API;
import edunote.util.CustomErrorType;

@RestController
@Controller
@RequestMapping("/api/**")
public class APICurso extends API {
//-------------------------------- devolver todo----------------------------------	
	public static final Logger logger = LoggerFactory.getLogger(APICurso.class);
	@RequestMapping(value = "/curso/",method = RequestMethod.GET)
	public ResponseEntity<List<Curso>> listAllUsers(@RequestParam (defaultValue="-1") Integer estado) {
		List<Curso> c = null;
		if(estado==-1)
				c = $curso.devolverTodos();
		else 
		c=$curso.devolverPorEstado(estado==1?true:false);
		if (c.isEmpty()) {
			return new ResponseEntity<List<Curso>>(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<Curso>>(c, HttpStatus.OK);
	}
//------------------------------------------------------------------------------
	// -------------------Recuperar un solo curso------------------------------------------

			@RequestMapping(value = "/curso/{nombre}", method = RequestMethod.GET)
			public ResponseEntity<?> getUser(@PathVariable("id") String nombre) {
				logger.info("Buscando Usuario con id {}", nombre);
				Curso c = $curso.findByNombre(nombre);
				if (c == null) {
					logger.error("Curso con nombre {} no encontrado.", nombre);
					return new ResponseEntity<>(new CustomErrorType("Curso con nombre " + nombre 
							+ " no encontrado"), HttpStatus.NOT_FOUND);
				}
				return new ResponseEntity<Curso>(c, HttpStatus.OK);
			}

			// -------------------Create a User-------------------------------------------

			@RequestMapping(value="/curso/save/", method = RequestMethod.GET)
			public ResponseEntity<?> createUser(@ModelAttribute   Curso curso, UriComponentsBuilder ucBuilder) {
				logger.info("Creando  : {}", curso);

				if ($curso.siExiste(curso)) {
					logger.error("Incapaz para crear. Un curso con ese nombre {} ya existe", curso.getNombre());
					return new ResponseEntity<>(new CustomErrorType(String.format("Incapaz para crear. Un curso con nombre %s ya existe", curso.getNombre())),HttpStatus.CONFLICT);
				}
				$curso.guardar(curso);

				HttpHeaders headers = new HttpHeaders();
				headers.setLocation(ucBuilder.path("/api/curso/{nombre}").buildAndExpand(curso.getNombre()).toUri());
				return new ResponseEntity<String>(headers, HttpStatus.CREATED);
			}
//---------------------------------------------------------------------------------------
// ------------------- Update a User ------------------------------------------------

			@RequestMapping(value ="/curso/{nombre}", method = RequestMethod.PUT)
			public ResponseEntity<?> updateUser(@PathVariable("nombre") String nombre_viejo, @RequestBody Curso curso) {
				logger.info("Updating User with id {}", nombre_viejo);
				if(curso.getNombre()!=null  && !nombre_viejo.equals(curso.getNombre()) && $curso.siExiste(curso)){
					logger.error("Impsobile actualizar. El curso con nombre {} ya existe",curso.getNombre());
					return new ResponseEntity<>(new CustomErrorType(String.format("Imposible actualizar. El curso con nombre %s ya existe",curso.getNombre())),HttpStatus.CONFLICT);
				}
				Curso c = $curso.findByNombre(nombre_viejo);
				if (c == null) {
					logger.error("Incapaz para actualizar. curso con nombre {} no es encontrado.", nombre_viejo);
					return new ResponseEntity<>(new CustomErrorType("Incapaz de actualizar. curso con nombre " + nombre_viejo + " no es encontrado."),
							HttpStatus.NOT_FOUND);
				}
				c.setNombre		(curso.getNombre());
				$curso.actualizar(c);
				return new ResponseEntity<Curso>(c, HttpStatus.OK);
			}
//-------------------------------------------------------------------------------------
			@RequestMapping(value = "/curso/{nombre}", method = RequestMethod.DELETE)
			public ResponseEntity<?> deleteUser(@PathVariable("nombre") String nombre) {
				logger.info("Buscando & eleminando curso con nombre {}", nombre);

				Curso curso = $curso.findByNombre(nombre);
				if (curso == null) {
					logger.error("Incapaz para eliminar. Curso con nombre {} no encontrado.", nombre);
					return new ResponseEntity<>(new CustomErrorType("Incapaz para eliminar. Curso con nombre " + nombre + " no encontrado."),
							HttpStatus.NOT_FOUND);
				}
				curso.setEstado(false);
				$curso.actualizar(curso);
				return new ResponseEntity<Curso>(HttpStatus.NO_CONTENT);
			}
	
}
