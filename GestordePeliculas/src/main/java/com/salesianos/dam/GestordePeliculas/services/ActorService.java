package com.salesianos.dam.GestordePeliculas.services;


import com.salesianos.dam.GestordePeliculas.error.ActorNotFoundException;
import com.salesianos.dam.GestordePeliculas.models.Actor;
import com.salesianos.dam.GestordePeliculas.models.Director;
import com.salesianos.dam.GestordePeliculas.repos.ActorRepository;
import com.salesianos.dam.GestordePeliculas.repos.PeliculaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ActorService {

    private final ActorRepository actorRepository;

    public List<Actor> listar() {
        return actorRepository.findAll();
    }

    public Actor buscarPorId(Long id) {
        return actorRepository.findById(id)
                .orElseThrow(() -> new ActorNotFoundException(id));
    }

    public Actor addActor(Actor actor) {
        return actorRepository.save(actor);
    }
    
    /*LOS MÉTODOS EDITAR NO PUEDO HACERLOS HASTA QUE TENGA EL DTO
    public Actor editarActor(Long id, Actor datos) {
        Actor actor = buscarPorId(id);
        actor.setName(datos.getName());
        return actorRepository.save(actor);
    }

     */
}


