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

import edunote.pojos.Saber;
import edunote.pojos.Ser;
import edunote.servicios.impl.API;
import edunote.util.CustomErrorType;

@RestController
@Controller
@RequestMapping("/api/**")
public class APISaber extends API{
	//-------------------------------- devolver todo----------------------------------	
			public static final Logger logger = LoggerFactory.getLogger(APISaber.class);
			@RequestMapping(value = "/saber/",method = RequestMethod.GET)
			public ResponseEntity<List<?>> listAllSaber() {
				List<Saber> saber = $saber.devolverTodo();
				if (saber.isEmpty()) {
					return new ResponseEntity<List<?>>(HttpStatus.NO_CONTENT);
					// You many decide to return HttpStatus.NOT_FOUND
				}
				return new ResponseEntity<List<?>>(saber, HttpStatus.OK);
			}
	//-------------------------------------------------------------------------
	// -------------------Recuperar un saber ------------------------------------------

			@RequestMapping(value = "/saber/{id}", method = RequestMethod.GET)
			public ResponseEntity<?> getSaber(@PathVariable("id") Long id) {
			logger.info("Recuperando saber con id {}", id);
			Saber saber = $saber.devolverPorId(id);
				if (saber == null) {
					logger.error("Saber con id {} no encontrado.", id);
					return new ResponseEntity<>(new CustomErrorType(String.format("Saber con id %d no encontrado.", id)), HttpStatus.NOT_FOUND);
				}
					return new ResponseEntity<Saber>(saber, HttpStatus.OK);
				}
	//---------------------------------------------------------------------------
	// -------------------Crear Saber-------------------------------------------
			
			@RequestMapping(value="/saber/save/", method = RequestMethod.GET)
			public ResponseEntity<?> createSaber(@RequestBody Saber saber, UriComponentsBuilder ucBuilder) {
			logger.info("Creando nota : {}", saber);

			/*if ($saber.siExiste(saber)) {
				logger.error("Incapaz para crear. Una nota con ese id {} ya existe", saber.getId());
				return new ResponseEntity<>(new CustomErrorType(String.format("Incapaz para crear. Una nota saber con nombre %s ya existe", saber.getId())),HttpStatus.CONFLICT);
			}*/
				$saber.guardar(saber);

				HttpHeaders headers = new HttpHeaders();
				headers.setLocation(ucBuilder.path("/api/saber/{id}").buildAndExpand(saber.getId()).toUri());
				return new ResponseEntity<String>(headers, HttpStatus.CREATED);
			}
	//------------------------------------Actualizar nota saber ---------------------------------------
			@RequestMapping(value ="/saber/{id}", method = RequestMethod.PUT)
			public ResponseEntity<?> updateSaber(@PathVariable("id") String nombre_viejo, @RequestBody Saber saber) {
				logger.info("Updating saber with id {}", nombre_viejo);
				if(saber.getId()!=null  && !nombre_viejo.equals(saber.getId()) && $saber.siExiste(saber)){
					logger.error("Impsobile actualizar. El saber con id {} ya existe",saber.getId());
					return new ResponseEntity<>(new CustomErrorType(String.format("Imposible actualizar. La nota saber con id %s ya existe",saber.getId())),HttpStatus.CONFLICT);
				}
				Saber sa = $saber.devolverPorId(saber.getId());
				if (sa == null) {
					logger.error("Incapaz para actualizar. saber con id {} no es encontrado.", nombre_viejo);
					return new ResponseEntity<>(new CustomErrorType("Incapaz de actualizar. saber con id " + nombre_viejo + " no es encontrado."),
							HttpStatus.NOT_FOUND);
				}
				sa.setId		(saber.getId());
				$saber.actualizar(saber);
				return new ResponseEntity<Saber>(sa, HttpStatus.OK);
			}
}
