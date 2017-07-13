package edunote.controller.rest;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import edunote.pojos.Materia;
import edunote.servicios.impl.API;
import edunote.util.CustomErrorType;

@RestController
@Controller
@RequestMapping("/api/**")
public class APIMateria extends API {
	// -------------------------------- devolver
	// todo----------------------------------
	public static final Logger logger = LoggerFactory
			.getLogger(APIMateria.class);

	@RequestMapping(value = "/materia/", method = RequestMethod.GET)
	public ResponseEntity<List<?>> listAllMateria() {
		List<Materia> materia = $materia.devolverTodo();
		if (materia.isEmpty()) {
			return new ResponseEntity<List<?>>(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<?>>(materia, HttpStatus.OK);
	}

	// ------------------------------------------------------------------------------
	// -------------------Recuperar una sola materia
	// ------------------------------------------

	@RequestMapping(value = "/materia/{nombre}", method = RequestMethod.GET)
	public ResponseEntity<?> getUser(@PathVariable("id") String nombre) {
		logger.info("Recuperando materia con id {}", nombre);
		Materia materia = $materia.devolverPorNombre(nombre);
		if (materia == null) {
			logger.error("Materia con nombre {} no encontrado.", nombre);
			return new ResponseEntity<>(new CustomErrorType(String.format(
					"Materia con nombre %d no encontrado.", nombre)),
					HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Materia>(materia, HttpStatus.OK);
	}

	// --------------------------------------------------------------------------------------------
	// -------------------Crear
	// materia-------------------------------------------

	@RequestMapping(value = "/materia/save/", method = RequestMethod.GET)
	public ResponseEntity<?> createMateria(@RequestBody Materia materia,
			UriComponentsBuilder ucBuilder) {
		logger.info("Creando  : {}", materia);

		if ($materia.siExiste(materia)) {
			logger.error(
					"Incapaz para crear. Una materia con ese nombre {} ya existe",
					materia.getNombre());
			return new ResponseEntity<>(new CustomErrorType(String.format(
					"Incapaz para crear. Una materia con nombre %s ya existe",
					materia.getNombre())), HttpStatus.CONFLICT);
		}
		$materia.guardar(materia);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/materia/{nombre}")
				.buildAndExpand(materia.getNombre()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	// ---------------------------------------------------------------------------------------------
	// ------------------------------------------ Actualizar materia
	// ----------------------------------

	@RequestMapping(value = "/materia/{nombre}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateMateria(
			@PathVariable("nombre") String nombre_viejo,
			@RequestBody Materia materia) {
		logger.info("Updating materia with id {}", nombre_viejo);
		if (materia.getNombre() != null
				&& !nombre_viejo.equals(materia.getNombre())
				&& $materia.siExiste(materia)) {
			logger.error(
					"Impsobile actualizar. La materia con nombre {} ya existe",
					materia.getNombre());
			return new ResponseEntity<>(new CustomErrorType(String.format(
					"Imposible actualizar. El curso con nombre %s ya existe",
					materia.getNombre())), HttpStatus.CONFLICT);
		}
		Materia mat = $materia.devolverPorNombre(nombre_viejo);
		if (mat == null) {
			logger.error(
					"Incapaz para actualizar. materia con nombre {} no es encontrado.",
					nombre_viejo);
			return new ResponseEntity<>(new CustomErrorType(
					"Incapaz de actualizar. materia con nombre " + nombre_viejo
							+ " no es encontrado."), HttpStatus.NOT_FOUND);
		}
		mat.setNombre(materia.getNombre());
		$materia.actualizar(materia);
		;
		return new ResponseEntity<Materia>(mat, HttpStatus.OK);
	}
}
