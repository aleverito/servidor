package edunote.controller.rest;

import java.util.StringTokenizer;

import javax.xml.bind.DatatypeConverter;

import org.apache.tomcat.util.codec.binary.Base64;
import org.apache.tomcat.util.codec.binary.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import edunote.pojos.Account;
import edunote.pojos.Colegio;
import edunote.pojos.Persona;
import edunote.servicios.impl.API;

@RestController
@Controller
@RequestMapping("/api/**")
public class APIUsuario extends API{
	public static final Logger logger = LoggerFactory.getLogger(APIUsuario.class);
	@RequestMapping(value = PATH_USUARIO +"/upload/photo/{id}", method = RequestMethod.POST)
	public ResponseEntity<?> loadImage(@PathVariable Long id, @RequestParam("file") MultipartFile file, @RequestParam String foto, UriComponentsBuilder ucBuilder) {
		Persona usuario = $usuario.findById(id);
		StringTokenizer tk = new StringTokenizer(foto,",");
		String media = tk.nextToken();
		foto = tk.nextToken();
		System.out.println(foto);
		usuario.setMedia(media);
		usuario.setFoto(Base64.decodeBase64(foto));
		logger.info("Actualizando foto de Usuario : {}", usuario);
		$usuario.saveUser(usuario);
		HttpHeaders headers = new HttpHeaders();
//		headers.setLocation(ucBuilder.path("/api/user/{id}").buildAndExpand(colegio.getNombre()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}
	@RequestMapping(value="/download/user/{id}",method=RequestMethod.GET,produces="text/plain")
	public ResponseEntity<?> getImageAsResponseEntity(@PathVariable Long id){
		ResponseEntity<?> responseEntity;
		final HttpHeaders headers = new HttpHeaders();
//		final InputStream in = servletContext.getResourceAs
		Persona p = $usuario.findById(id);
		headers.setCacheControl(CacheControl.noCache().getHeaderValue());
		if( p.getMedia()==null && p.getFoto()==null){
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		String res  = StringUtils.newStringUtf8( org.apache.tomcat.util.codec.binary.Base64.encodeBase64(p.getFoto(), false) );
		responseEntity = new ResponseEntity<>(null,headers,HttpStatus.OK);
		StringBuilder str = new StringBuilder();
		str.append(p.getMedia());
		str.append(",");
		str.append(res);
		return responseEntity.ok().headers(headers).body(str.toString());
//		return responseEntity;
	}
	@RequestMapping(value="/authentication",method=RequestMethod.GET)
	public ResponseEntity<?> authentication(@RequestParam String username,@RequestParam String password){
		Account cuenta = $cuenta.authentication(username);
		final HttpHeaders headers = new HttpHeaders();
		headers.setCacheControl(CacheControl.noCache().getHeaderValue());
		if(cuenta!=null && cuenta.getPassword().equals(password)){
			ResponseEntity<?> responseEntity = new ResponseEntity<>(null,headers,HttpStatus.ACCEPTED);
			return responseEntity.accepted().headers(headers).body(cuenta);
		}
		return new ResponseEntity<>(null,headers,HttpStatus.NOT_ACCEPTABLE);
	}
}
