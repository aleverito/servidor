package edunote.servicios.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edunote.repositorios.ColegioRepository;
import edunote.servicios.*;
@Service
public class API {

//	public static final Logger logger = LoggerFactory.getLogger(RestApiController.class);

	@Autowired
	public UsuarioService $usuario; //Service which will do all data retrieval/manipulation work
	
	@Autowired
	public AccountService $cuenta;
	
	@Autowired
	public MaestroService $maestro;
	
	@Autowired
	public EstudianteService $estudiante;
	
	@Autowired
	public ColegioRepository $$colegio;
	
	@Autowired
	public CursoService $curso;
	
	@Autowired
	public CicloService $ciclo;
	
	@Autowired
	public ColegioService $colegio;
	
	@Autowired
	public MateriaService $materia;
	
	@Autowired
	public SerService $ser;
	
	@Autowired
	public SaberService $saber;
	
	@Autowired
	public HacerService $hacer;
	
	@Autowired
	public DecidirService $decidir;
	
	@Autowired
	public RegistroService $registro;
	
	protected final String PATH_ESTUDIANTE = "/estudiante/";
	protected final String PATH_MAESTRO = "/maestro/";
	protected final String PATH_USUARIO = "/usuario/";
}