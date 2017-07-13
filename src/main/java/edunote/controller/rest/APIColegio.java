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
import edunote.pojos.Colegio;
import edunote.servicios.impl.API;
import edunote.util.CustomErrorType;

@RestController
@Controller
@RequestMapping("/api/**")
public class APIColegio extends API{
	
	//-------------------------------- devolver todo----------------------------------	
		public static final Logger logger = LoggerFactory.getLogger(APIColegio.class);
		@RequestMapping(value = "/colegio/",method = RequestMethod.GET)
		public ResponseEntity<List<?>> listAllColegio() {
			List<Colegio> colegio = $colegio.devolverTodo();
			if (colegio.isEmpty()) {
				return new ResponseEntity<List<?>>(HttpStatus.NO_CONTENT);
				// You many decide to return HttpStatus.NOT_FOUND
			}
			return new ResponseEntity<List<?>>(colegio, HttpStatus.OK);
		}
	//-----------------------------------------------------------------------------------
	//------------------------------- Recuperar un colegio ------------------------------
		@RequestMapping(value = "/colegio/{nombre}", method = RequestMethod.GET)
		public ResponseEntity<?> getColegio(@PathVariable("id") String nombre) {
			logger.info("Recuperando colegio con id {}", nombre);
			Colegio colegio = $colegio.devolverPorNombre(nombre);
			if (colegio == null) {
				logger.error("Colegio con nombre {} no encontrado.", nombre);
				return new ResponseEntity<>(new CustomErrorType(String.format("Ciclo con nombre %d no encontrado.", nombre)), HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Colegio>(colegio, HttpStatus.OK);
		}
	//--------------------------------------------------------------------------------------
	//----------------------------------- Crear Colegio ------------------------------------
		@RequestMapping(value="/colegio/save/", method = RequestMethod.GET)
		public ResponseEntity<?> createColegio(@ModelAttribute Colegio colegio, UriComponentsBuilder ucBuilder) {
			logger.info("Creando  : {}", colegio);

			if ($colegio.siExiste(colegio)) {
				logger.error("Incapaz para crear. Un colegio con ese nombre {} ya existe", colegio.getNombre());
				return new ResponseEntity<>(new CustomErrorType(String.format("Incapaz para crear. Un colegio con nombre %s ya existe", colegio.getNombre())),HttpStatus.CONFLICT);
			}
			$colegio.guardar(colegio);

			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucBuilder.path("/api/colegio/{nombre}").buildAndExpand(colegio.getNombre()).toUri());
			return new ResponseEntity<String>(headers, HttpStatus.CREATED);
		}
		//---------------------------------------------------------------------------------------------
		//------------------------------------------ Actualizar colegio ----------------------------------

					@RequestMapping(value ="/colegio/{nombre}", method = RequestMethod.PUT)
					public ResponseEntity<?> updateColegio(@PathVariable("nombre") String nombre_viejo, @RequestBody Colegio colegio) {
						logger.info("Updating colegio with id {}", nombre_viejo);
						if(colegio.getNombre()!=null  && !nombre_viejo.equals(colegio.getNombre()) && $colegio.siExiste(colegio)){
							logger.error("Impsobile actualizar. El colegio con nombre {} ya existe",colegio.getNombre());
							return new ResponseEntity<>(new CustomErrorType(String.format("Imposible actualizar. El colegio con nombre %s ya existe",colegio.getNombre())),HttpStatus.CONFLICT);
						}
						Colegio col = $colegio.devolverPorNombre(nombre_viejo);
						if (col == null) {
							logger.error("Incapaz para actualizar. colegio con nombre {} no es encontrado.", nombre_viejo);
							return new ResponseEntity<>(new CustomErrorType("Incapaz de actualizar. colegio con nombre " + nombre_viejo + " no es encontrado."),
									HttpStatus.NOT_FOUND);
						}
						col.setNombre		(col.getNombre());
						$colegio.actualizar(col);
						return new ResponseEntity<Colegio>(col, HttpStatus.OK);
					}
}
