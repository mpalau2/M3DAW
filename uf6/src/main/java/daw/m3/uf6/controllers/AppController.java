package daw.m3.uf6.controllers;

import java.util.HashMap;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import daw.m3.uf6.objects.Actor;
import daw.m3.uf6.objects.http.AppErrorResponse;
import daw.m3.uf6.objects.http.ErrorMessage;
import daw.m3.uf6.objects.http.RequestActor;
import daw.m3.uf6.services.ActorService;

/**
 * Controller principal de l'aplicació, aquí es defineixen els endpoints (punts d'accés de l'app) les anotacions GetMapping, 
 * defineixen el mètode que s'ha d'utilitzar, i es poden rebre paràmetres per path, per pathVariable, requestParam i pel Body 
 * @author Marti
 *
 */
@Controller
public class AppController {
	
	private static final Logger logger = LogManager.getLogger(AppController.class);
	
	@Autowired
	private ActorService actorService;
	
	/**
	 * getAllActors recupera tots els actors de la BD i els retorna amb un array d'actors en JSON.
	 * @return
	 */
	@GetMapping("/getAllActors")
	public ResponseEntity<List<Actor>> getAllActors() {
        logger.debug("Inici mètode getAllActors");
        //Cridar al service respectiu que processi la informació
        List<Actor> listUser = actorService.getAllActors();
        logger.info("{} usuaris recuperats correctament",listUser.size());
        if(listUser==null || listUser.isEmpty()) {
        	AppErrorResponse errorResponse = new AppErrorResponse(ErrorMessage.E404.name());
        	return new ResponseEntity(errorResponse, errorResponse.getHttpCode());
        }else {       	
        	return ResponseEntity.ok(listUser);
        }
	}
	
	@PostMapping("/createActor")
	public ResponseEntity<Actor> createActor(@RequestBody RequestActor requestActor){
		logger.debug("Inici mètode createUser, user rebut[{}]",requestActor);
		
		HashMap<String,String> validacions = new HashMap<>();
		//Inicio les validacions dels diferents camps de l'objecte d'entrada
		if(requestActor!=null) {
			boolean valid = true;
			if(requestActor.getFirstName()==null || requestActor.getFirstName().isEmpty() || requestActor.getFirstName().isBlank()) {
				logger.warn("Error validant firstName actor rebut");
				validacions.put("firstName", "El camp firstName és obligatori i no pot ser buit o espais");
				valid = false;
			}
			if(requestActor.getSecondName()==null || requestActor.getSecondName().isEmpty() || requestActor.getSecondName().isBlank()) {
				logger.warn("Error validant secondName actor rebut");
				validacions.put("secondName", "El camp secondName és obligatori i no pot ser buit o espais");
				valid = false;
			}
					
			if(!valid) {
				logger.warn("Error en alguna de les validacions, resposta bad request");
				AppErrorResponse errorResponse = new AppErrorResponse(ErrorMessage.E001.name());
				errorResponse.setDetails(validacions);
			    return new ResponseEntity(errorResponse, errorResponse.getHttpCode());
			}else {
				logger.info("Usuari validat correctament, procedim a inserir-lo a BDD");
				Actor actor = new Actor();
				actor.setFirstName(requestActor.getFirstName());
				actor.setSecondName(requestActor.getSecondName());
				Actor u = actorService.createActor(actor);
				if(u!=null) {
					return ResponseEntity.ok(u);
				}else {
					AppErrorResponse errorResponse = new AppErrorResponse(ErrorMessage.E300.name());
					return new ResponseEntity(errorResponse,errorResponse.getHttpCode());
				}
			}
		}else {
			validacions.put("actor", "Objecte actor totalment buit");
			AppErrorResponse errorResponse = new AppErrorResponse(ErrorMessage.E001.name());
			errorResponse.setDetails(validacions);
		    return new ResponseEntity(errorResponse, errorResponse.getHttpCode());
		}		
	}
	
	
}
