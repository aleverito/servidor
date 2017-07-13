package edunote.controller.rest;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import edunote.pojos.Ciclo;
import edunote.pojos.Colegio;
import edunote.pojos.Curso;
import edunote.pojos.Estudiante;
import edunote.pojos.Registro;
import edunote.servicios.impl.API;
import edunote.util.CustomErrorType;

@RestController
@Controller
@RequestMapping("/api/**")
public class APIRegistro extends API{
	//-------------------------------- devolver todos----------------------------------	
		@RequestMapping(value = "/registro/",method = RequestMethod.GET)
		public ResponseEntity<List<?>> listAllRegistro() {
			List<Registro> registro = $registro.devolverTodos();
			if (registro.isEmpty()) {
				return new ResponseEntity<List<?>>(HttpStatus.NO_CONTENT);
				// You many decide to return HttpStatus.NOT_FOUND
			}
			return new ResponseEntity<List<?>>(registro, HttpStatus.OK);
		}
		//------------------------------------------------------------------------------
	//---------------------- crear un registro --------------------------------
	public static final Logger logger = LoggerFactory.getLogger(APIRegistro.class);
	@RequestMapping(value="/registro/save/", method = RequestMethod.GET)
	public ResponseEntity<?> crearRegiistro(@RequestParam Integer id_col, @RequestParam Integer id_ci,
			@RequestParam Integer id_cur, @RequestParam Long id_est , UriComponentsBuilder ucBuilder) {

		Integer gestion=new Date().getYear()+1900;
	if ($registro.existe(id_col, id_ci, id_est, id_cur, gestion)) {
		logger.error("Incapaz para crear. Un registro en la gestion {}, con el colegio {}, con el curso {}, con el estudiante {} y ciclo {} ya existe", gestion,id_col,id_cur,id_est,id_ci);
		return new ResponseEntity<>(new CustomErrorType(String.format("Incapaz para crear. El Registro con los parametros establecidos ya existe")),HttpStatus.CONFLICT);
	}
		Colegio col=$colegio.devolverPorId(id_col);
		Curso cur=$curso.findById(id_cur);
		Ciclo ci=$ciclo.devolverPorId(id_ci);
		Estudiante est=$estudiante.findById(id_est);
		Registro reg=new Registro();
		reg.setGestion(gestion);
		reg.setCiclo(ci);
		reg.setColegio(col);
		reg.setEstudiante(est);
		reg.setCurso(cur);
		$registro.guardar(reg);

		HttpHeaders headers = new HttpHeaders();
//		headers.setLocation(ucBuilder.path("/api/registro/{id}").buildAndExpand(registro.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}
}
