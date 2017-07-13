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

import edunote.pojos.Decidir;
import edunote.pojos.Ser;
import edunote.servicios.impl.API;
import edunote.util.CustomErrorType;

@RestController
@Controller
@RequestMapping("/api/**")
public class APIDecidir extends API{
	//-------------------------------- devolver todo----------------------------------	
			public static final Logger logger = LoggerFactory.getLogger(APIDecidir.class);
			@RequestMapping(value = "/decidir/",method = RequestMethod.GET)
			public ResponseEntity<List<?>> listAllDecidir() {
				List<Decidir> decidir = $decidir.devolverTodo();
				if (decidir.isEmpty()) {
					return new ResponseEntity<List<?>>(HttpStatus.NO_CONTENT);
					// You many decide to return HttpStatus.NOT_FOUND
				}
				return new ResponseEntity<List<?>>(decidir, HttpStatus.OK);
			}
		//------------------------------------------------------------------------------
			// -------------------Recuperar un decidir ------------------------------------------

			@RequestMapping(value = "/decidir/{id}", method = RequestMethod.GET)
			public ResponseEntity<?> getDecidir(@PathVariable("id") Long id) {
			logger.info("Recuperando decidir con id {}", id);
			Decidir decidir = $decidir.devolverPorId(id);
				if (decidir == null) {
					logger.error("Decidir con id {} no encontrado.", id);
					return new ResponseEntity<>(new CustomErrorType(String.format(" con id %d no encontrado.", id)), HttpStatus.NOT_FOUND);
				}
					return new ResponseEntity<Decidir>(decidir, HttpStatus.OK);
				}
		//---------------------------------------------------------------------------
		// -------------------Crear Decidir -------------------------------------------
			
			@RequestMapping(value="/decidir/save/", method = RequestMethod.GET)
			public ResponseEntity<?> createDecidir(@RequestBody Decidir decidir, UriComponentsBuilder ucBuilder) {
			logger.info("Creando nota : {}", decidir);

			/*if ($decidir.siExiste(decidir)) {
				logger.error("Incapaz para crear. Una nota con ese id {} ya existe", decidir.getId());
				return new ResponseEntity<>(new CustomErrorType(String.format("Incapaz para crear. Un ciclo con nombre %s ya existe", decidir.getId())),HttpStatus.CONFLICT);
			}*/
				$decidir.guardar(decidir);

				HttpHeaders headers = new HttpHeaders();
				headers.setLocation(ucBuilder.path("/api/decidir/{id}").buildAndExpand(decidir.getId()).toUri());
				return new ResponseEntity<String>(headers, HttpStatus.CREATED);
			}
		//---------------------------------------------------------------------------------------------
			//------------------------------------------ Actualizar ser --------------------------------

			@RequestMapping(value ="/decidir/{id}", method = RequestMethod.PUT)
			public ResponseEntity<?> updateDecidir(@PathVariable("id") String nombre_viejo, @RequestBody Decidir decidir) {
				logger.info("Updating decidir with id {}", nombre_viejo);
				if(decidir.getId()!=null  && !nombre_viejo.equals(decidir.getId()) && $decidir.siExiste(decidir)){
					logger.error("Impsobile actualizar. El decidir con nombre {} ya existe",decidir.getId());
					return new ResponseEntity<>(new CustomErrorType(String.format("Imposible actualizar. El decidir con id %s ya existe",decidir.getId())),HttpStatus.CONFLICT);
				}
				Decidir de = $decidir.devolverPorId(decidir.getId());
				if (de == null) {
					logger.error("Incapaz para actualizar. decidir con id {} no es encontrado.", nombre_viejo);
					return new ResponseEntity<>(new CustomErrorType("Incapaz de actualizar. decidir con nombre " + nombre_viejo + " no es encontrado."),
							HttpStatus.NOT_FOUND);
				}
				de.setId		(decidir.getId());
				$decidir.actualizar(decidir);
				return new ResponseEntity<Decidir>(de, HttpStatus.OK);
			}
}
