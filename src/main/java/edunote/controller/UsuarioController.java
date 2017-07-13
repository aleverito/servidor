package edunote.controller;

import org.apache.tomcat.util.codec.binary.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import edunote.pojos.Persona;
import edunote.servicios.impl.API;
@Controller
//@RequestMapping("")
public class UsuarioController extends API{
	public static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);
	@RequestMapping("/app/modules/dashboard/configFotoUsuario/{id}")
	public String loadPictureUser(@PathVariable Long id,Model $scope){
		Persona m=$usuario.findById(id);
		$scope.addAttribute("user", m );
		String res  = StringUtils.newStringUtf8( org.apache.tomcat.util.codec.binary.Base64.encodeBase64(m.getFoto(), false) );
		logger.info("Recuperando la info de User {} con su foto",m);
		$scope.addAttribute("base64",res);
		$scope.addAttribute("base64",res);
		$scope.addAttribute("datos", new String[]{"israel","marino","jerez"} );
		
		return "app/modules/dashboard/usuario/modalFoto";
	}
}
