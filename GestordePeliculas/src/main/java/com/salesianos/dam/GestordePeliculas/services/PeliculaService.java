package com.salesianos.dam.GestordePeliculas.services;

import com.salesianos.dam.GestordePeliculas.error.ActorNotFoundException;
import com.salesianos.dam.GestordePeliculas.error.PeliculaNotFoundException;
import com.salesianos.dam.GestordePeliculas.models.Actor;
import com.salesianos.dam.GestordePeliculas.models.Pelicula;
import com.salesianos.dam.GestordePeliculas.repos.PeliculaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class PeliculaService {

    private final PeliculaRepository peliculaRepository;

    public List<Pelicula> listar() {
        return peliculaRepository.findAll();
    }

    public Pelicula buscarPorId(Long id) {
        return peliculaRepository.findById(id)
                .orElseThrow(() -> new PeliculaNotFoundException(id));
    }

    public Pelicula addActor(Pelicula pelicula) {
        return peliculaRepository.save(pelicula);
    }
    /*LOS MÉTODOS EDITAR NO PUEDO HACERLOS HASTA QUE TENGA EL DTO
    public Pelicula editarPelicula(Long id, Pelicula datos) {
        Pelicula pelicula = buscarPorId(id);
        Pelicula pelicula = Pelicula.builder()
                        .titulo()





                build()
        return actorRepository.save(actor);
    }*/
}
