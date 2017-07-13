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
import edunote.pojos.Ser;
import edunote.servicios.impl.API;
import edunote.util.CustomErrorType;

@RestController
@Controller
@RequestMapping("/api/**")
public class APISer extends API{
	//-------------------------------- devolver todo----------------------------------	
		public static final Logger logger = LoggerFactory.getLogger(APISer.class);
		@RequestMapping(value = "/ser/",method = RequestMethod.GET)
		public ResponseEntity<List<?>> listAllSer() {
			List<Ser> ser = $ser.devolverTodo();
			if (ser.isEmpty()) {
				return new ResponseEntity<List<?>>(HttpStatus.NO_CONTENT);
				// You many decide to return HttpStatus.NOT_FOUND
			}
			return new ResponseEntity<List<?>>(ser, HttpStatus.OK);
		}
	//------------------------------------------------------------------------------
	// -------------------Recuperar un ser ------------------------------------------

		@RequestMapping(value = "/ser/{id}", method = RequestMethod.GET)
		public ResponseEntity<?> getSer(@PathVariable("id") Long id) {
		logger.info("Recuperando ser con id {}", id);
		Ser ser = $ser.devolverPorId(id);
			if (ser == null) {
				logger.error("Ser con id {} no encontrado.", id);
				return new ResponseEntity<>(new CustomErrorType(String.format("Ser con id %d no encontrado.", id)), HttpStatus.NOT_FOUND);
			}
				return new ResponseEntity<Ser>(ser, HttpStatus.OK);
			}
	//---------------------------------------------------------------------------
	// -------------------Crear Ser-------------------------------------------
		
		@RequestMapping(value="/ser/save/", method = RequestMethod.GET)
		public ResponseEntity<?> createSer(@RequestBody Ser ser, UriComponentsBuilder ucBuilder) {
		logger.info("Creando nota : {}", ser);

		/*if ($ser.siExiste(ser)) {
			logger.error("Incapaz para crear. Una nota con ese id {} ya existe", ser.getId());
			return new ResponseEntity<>(new CustomErrorType(String.format("Incapaz para crear. Un ciclo con nombre %s ya existe", ser.getId())),HttpStatus.CONFLICT);
		}*/
			$ser.guardar(ser);

			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucBuilder.path("/api/ser/{id}").buildAndExpand(ser.getId()).toUri());
			return new ResponseEntity<String>(headers, HttpStatus.CREATED);
		}
	//---------------------------------------------------------------------------------------------
	//------------------------------------------ Actualizar ser --------------------------------

		@RequestMapping(value ="/ser/{id}", method = RequestMethod.PUT)
		public ResponseEntity<?> updateSer(@PathVariable("id") String nombre_viejo, @RequestBody Ser ser) {
			logger.info("Updating ser with id {}", nombre_viejo);
			if(ser.getId()!=null  && !nombre_viejo.equals(ser.getId()) && $ser.siExiste(ser)){
				logger.error("Impsobile actualizar. El ciclo con nombre {} ya existe",ser.getId());
				return new ResponseEntity<>(new CustomErrorType(String.format("Imposible actualizar. El curso con nombre %s ya existe",ser.getId())),HttpStatus.CONFLICT);
			}
			Ser s = $ser.devolverPorId(ser.getId());
			if (s == null) {
				logger.error("Incapaz para actualizar. ser con id {} no es encontrado.", nombre_viejo);
				return new ResponseEntity<>(new CustomErrorType("Incapaz de actualizar. ciclo con nombre " + nombre_viejo + " no es encontrado."),
						HttpStatus.NOT_FOUND);
			}
			s.setId		(ser.getId());
			$ser.actualizar(ser);
			return new ResponseEntity<Ser>(s, HttpStatus.OK);
		}
}
