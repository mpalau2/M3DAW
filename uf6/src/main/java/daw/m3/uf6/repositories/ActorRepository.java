package daw.m3.uf6.repositories;

import java.util.List;

import org.springframework.stereotype.Repository;

import daw.m3.uf6.objects.Actor;

@Repository
public interface ActorRepository {

	Actor createActor(Actor a);
	List<Actor> getAllActors();
	
}