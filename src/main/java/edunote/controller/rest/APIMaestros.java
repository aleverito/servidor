package edunote.controller.rest;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import edunote.pojos.*;
import edunote.repositorios.ColegioRepository;
import edunote.servicios.impl.*;
import edunote.util.CustomErrorType;

@RestController
@Controller
@RequestMapping("/api/**")
public class APIMaestros extends API{
	ColegioRepository colegioRepository;
	// -------------------Recuperar Todos Users---------------------------------------------
	public static final Logger logger = LoggerFactory.getLogger(APIEstudiantes.class);
		@RequestMapping(value = PATH_MAESTRO,method = RequestMethod.GET)
		public ResponseEntity<List<?>> listAllUsers() {
			List<Maestro> personas = $maestro.findAllMaestros();
			if (personas.isEmpty()) {
				return new ResponseEntity<List<?>>(HttpStatus.NO_CONTENT);
				// You many decide to return HttpStatus.NOT_FOUND
			}
			return new ResponseEntity<List<?>>(personas, HttpStatus.OK);
		}

		// -------------------Retrieve Single User------------------------------------------

		@RequestMapping(value = PATH_MAESTRO+"{id}", method = RequestMethod.GET)
		public ResponseEntity<?> getUser(@PathVariable("id") long id) {
			logger.info("Recuperando Maestro con id {}", id);
			Maestro persona = $maestro.findById(id);
			if (persona == null) {
				logger.error("Maestro con id {} no encontrado.", id);
				return new ResponseEntity<>(new CustomErrorType(String.format("Maestro con id %d no encontrado.", id)), HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Maestro>(persona, HttpStatus.OK);
		}

		// -------------------Create a User-------------------------------------------
		
		@RequestMapping(value=PATH_MAESTRO, method = RequestMethod.POST)
		public ResponseEntity<?> createUser(@RequestBody Maestro maestro, UriComponentsBuilder ucBuilder) {
			logger.info("Creando Maestro : {}", maestro);
			System.out.println( maestro );

			if ($maestro.isMaestroExist(maestro)) {
				logger.error("Imposible para crear. Un Maestro con rude {} ya existe", maestro.getRda());
				return new ResponseEntity<>(new CustomErrorType(String.format("Imposible para crear. Un Maestro con rude %s ya existe", maestro.getRda())),HttpStatus.CONFLICT);
			}
			$maestro.saveMaestro(maestro);

			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucBuilder.path("/api/maestro/{id}").buildAndExpand(maestro.getId()).toUri());
			return new ResponseEntity<String>(headers, HttpStatus.CREATED);
		}
	

		// ------------------- Update a User ------------------------------------------------

		@RequestMapping(value = PATH_MAESTRO+"{id}", method = RequestMethod.PUT)
		public ResponseEntity<?> updateUser(@PathVariable("id") long id, @RequestBody Maestro data) {
			logger.info("Actualizando Maestro con id {}", id);
			if(data.rda_old!=null && (int)data.rda_old!=(int)data.getRda() && $maestro.isMaestroExist(data)){
				logger.error("Impsobile actualizar. El Maestro con rda {} ya existe",data.getRda());
				return new ResponseEntity<>(new CustomErrorType(String.format("Imposible actualizar. El Maestro con rude %s con rda ya existe",data.getRda())),HttpStatus.CONFLICT);
			}
			Maestro currentUser = $maestro.findById(id);
			if (currentUser == null) {
				logger.error("Imposible actualizar. Maestro con id {} no encontrado.", id);
				return new ResponseEntity<>(new CustomErrorType(String.format("Imposible actualizar. Maestro con id {} no encontrado.", id)),	HttpStatus.NOT_FOUND);
			}
			currentUser.setCi		(data.getCi());
			currentUser.setAm		(data.getAm());
			currentUser.setNombre	(data.getNombre());
			currentUser.setAp		(data.getAp());
			currentUser.setRda		(data.getRda());
			currentUser.setTelefono	(data.getTelefono());
			$maestro.updateMaestro(currentUser);
			return new ResponseEntity<Maestro>(currentUser, HttpStatus.OK);
		}

		// ------------------- Delete a User-----------------------------------------

		@RequestMapping(value = PATH_MAESTRO+"{id}", method = RequestMethod.DELETE)
		public ResponseEntity<?> deleteUser(@PathVariable("id") Integer rda) {
			logger.info("Procesando & Eliminando Maestro con rda {}", rda);

			Maestro maestro = $maestro.findByRda(rda);
			if (maestro == null) {
				logger.error("Imposible de Eliminar. Maestro con rda {} no encontrado.", rda);
				return new ResponseEntity<>(new CustomErrorType(String.format("Imposible de Eliminar. Maestro con rda %s not encontrado.", rda)),
						HttpStatus.NOT_FOUND);
			}
			$maestro.delete(maestro);
			return new ResponseEntity<Maestro>(HttpStatus.NO_CONTENT);
		}

		// ------------------- Delete All Users-----------------------------

		@RequestMapping(value=PATH_MAESTRO, method = RequestMethod.DELETE)
		public ResponseEntity<Maestro> deleteAllUsers() {
			logger.info("Eliminando Todos los Maestros");
			return new ResponseEntity<Maestro>(HttpStatus.NO_CONTENT);
		}
	
}
