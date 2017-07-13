package edunote.controller.rest;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import edunote.pojos.Hacer;
import edunote.servicios.impl.API;
import edunote.util.CustomErrorType;

@RestController
@Controller
@RequestMapping("/api/**")
public class APIHacer extends API{
//-------------------------------- devolver todo----------------------------------	
	public static final Logger logger = LoggerFactory.getLogger(APIHacer.class);
	@RequestMapping(value = "/hacer/",method = RequestMethod.GET)
	public ResponseEntity<List<?>> listAllHacer() {
	List<Hacer> hacer = $hacer.devolverTodo();
		if (hacer.isEmpty()) {
			return new ResponseEntity<List<?>>(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
			return new ResponseEntity<List<?>>(hacer, HttpStatus.OK);
	}
//------------------------------------------------------------------------------
// -------------------Recuperar un ser ------------------------------------------

	@RequestMapping(value = "/hacer/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getHacer(@PathVariable("id") Long id) {
	logger.info("Recuperando hacer con id {}", id);
	Hacer hacer = $hacer.devolverPorId(id);
	if (hacer == null) {
		logger.error("Hacer con id {} no encontrado.", id);
		return new ResponseEntity<>(new CustomErrorType(String.format("Ser con id %d no encontrado.", id)), HttpStatus.NOT_FOUND);
	}
		return new ResponseEntity<Hacer>(hacer, HttpStatus.OK);
	}
//---------------------------------------------------------------------------
}
