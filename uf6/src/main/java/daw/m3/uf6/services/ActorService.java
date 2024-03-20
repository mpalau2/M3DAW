package daw.m3.uf6.services;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import daw.m3.uf6.objects.Actor;

@Service
public class ActorService {

	private static final Logger logger = LogManager.getLogger(ActorService.class);
	
	//@Autowired
	//RepositoriJDBCImpl jdbcRepo;
	
	public List<Actor> getAllActors(){
		logger.info("Mètode getAllActors No implementat");
		//Cridar al jdcbRepo per obtenir usuaris i processar-ne la resposta
		throw new UnsupportedOperationException("Mètode no implementat");
	}
	
	public Actor createActor(Actor a) {
		logger.info("Mètode createActors No implementat");
		//Cridar al jdcbRepo per crear Actor
		throw new UnsupportedOperationException("Mètode no implementat");
	}
	
	
}
