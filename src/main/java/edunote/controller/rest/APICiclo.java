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

import edunote.pojos.Ciclo;
import edunote.pojos.Curso;
import edunote.pojos.Maestro;
import edunote.servicios.impl.API;
import edunote.util.CustomErrorType;

@RestController
@Controller
@RequestMapping("/api/**")
public class APICiclo extends API{
	//-------------------------------- devolver todo----------------------------------	
	public static final Logger logger = LoggerFactory.getLogger(APICiclo.class);
	@RequestMapping(value = "/ciclo/",method = RequestMethod.GET)
	public ResponseEntity<List<?>> listAllCiclo() {
		List<Ciclo> ciclo = $ciclo.devolverTodos();
		if (ciclo.isEmpty()) {
			return new ResponseEntity<List<?>>(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<?>>(ciclo, HttpStatus.OK);
	}
	//------------------------------------------------------------------------------
	// -------------------Recuperar un solo ciclo ------------------------------------------

			@RequestMapping(value = "/ciclo/{nombre}", method = RequestMethod.GET)
			public ResponseEntity<?> getUser(@PathVariable("id") String nombre) {
				logger.info("Recuperando ciclo con id {}", nombre);
				Ciclo ciclo = $ciclo.findByName(nombre);
				if (ciclo == null) {
					logger.error("Ciclo con nombre {} no encontrado.", nombre);
					return new ResponseEntity<>(new CustomErrorType(String.format("Ciclo con nombre %d no encontrado.", nombre)), HttpStatus.NOT_FOUND);
				}
				return new ResponseEntity<Ciclo>(ciclo, HttpStatus.OK);
			}
//--------------------------------------------------------------------------------------------			
	// -------------------Crear ciclo-------------------------------------------
			
			@RequestMapping(value="/ciclo/save/", method = RequestMethod.GET)
			public ResponseEntity<?> createCiclo(@ModelAttribute   Ciclo ciclo, UriComponentsBuilder ucBuilder) {
				logger.info("Creando  : {}", ciclo);

				if ($ciclo.siExisteCiclo(ciclo)) {
					logger.error("Incapaz para crear. Un ciclo con ese nombre {} ya existe", ciclo.getNombre());
					return new ResponseEntity<>(new CustomErrorType(String.format("Incapaz para crear. Un ciclo con nombre %s ya existe", ciclo.getNombre())),HttpStatus.CONFLICT);
				}
				$ciclo.guardarCiclo(ciclo);

				HttpHeaders headers = new HttpHeaders();
				headers.setLocation(ucBuilder.path("/api/ciclo/{nombre}").buildAndExpand(ciclo.getNombre()).toUri());
				return new ResponseEntity<String>(headers, HttpStatus.CREATED);
			}
//---------------------------------------------------------------------------------------------
//------------------------------------------ Actualizar ciclo ----------------------------------

			@RequestMapping(value ="/ciclo/{nombre}", method = RequestMethod.PUT)
			public ResponseEntity<?> updateCiclo(@PathVariable("nombre") String nombre_viejo, @RequestBody Ciclo ciclo) {
				logger.info("Updating ciclo with id {}", nombre_viejo);
				if(ciclo.getNombre()!=null  && !nombre_viejo.equals(ciclo.getNombre()) && $ciclo.siExisteCiclo(ciclo)){
					logger.error("Impsobile actualizar. El ciclo con nombre {} ya existe",ciclo.getNombre());
					return new ResponseEntity<>(new CustomErrorType(String.format("Imposible actualizar. El curso con nombre %s ya existe",ciclo.getNombre())),HttpStatus.CONFLICT);
				}
				Ciclo cicl = $ciclo.findByName(nombre_viejo);
				if (cicl == null) {
					logger.error("Incapaz para actualizar. ciclo con nombre {} no es encontrado.", nombre_viejo);
					return new ResponseEntity<>(new CustomErrorType("Incapaz de actualizar. ciclo con nombre " + nombre_viejo + " no es encontrado."),
							HttpStatus.NOT_FOUND);
				}
				cicl.setNombre		(ciclo.getNombre());
				$ciclo.actualizarCiclo(cicl);
				return new ResponseEntity<Ciclo>(cicl, HttpStatus.OK);
			}
}
