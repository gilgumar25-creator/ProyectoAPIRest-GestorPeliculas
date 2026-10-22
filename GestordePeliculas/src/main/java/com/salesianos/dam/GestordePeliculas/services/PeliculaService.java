package com.salesianos.dam.GestordePeliculas.services;

import com.salesianos.dam.GestordePeliculas.dto.dtoPelicula.EditarPeliculaDto;
import com.salesianos.dam.GestordePeliculas.error.ActorNotFoundException;
import com.salesianos.dam.GestordePeliculas.error.ActorYaEnRepartoException;
import com.salesianos.dam.GestordePeliculas.error.PeliculaNotFoundException;
import com.salesianos.dam.GestordePeliculas.error.PeliculaYaExistenteException;
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
    private final DirectorService directorService;
    private final ActorService actorService;

    public List<Pelicula> listar() {
        return peliculaRepository.findAll();
    }

    public Pelicula buscarPorId(Long id) {
        return peliculaRepository.findById(id)
                .orElseThrow(() -> new PeliculaNotFoundException(id));
    }

    public Pelicula addPelicula(EditarPeliculaDto dto) {
        if (peliculaRepository.existsByTituloIgnoreCase(dto.titulo()))
            throw new PeliculaYaExistenteException(dto.titulo());

        Pelicula pelicula = dto.to();
        pelicula.setDirector(directorService.buscarPorId(dto.directorId()));
        return peliculaRepository.save(pelicula);
    }

    /*Métodos update añadidos, los dtos están configurados correctamente
     */
    public Pelicula updatePelicula(Long id, EditarPeliculaDto dto) {
        Pelicula pelicula = buscarPorId(id);
        if (peliculaRepository.existsByTituloIgnoreCaseAndIdNot(dto.titulo(), id))
            throw new PeliculaYaExistenteException(dto.titulo());

        pelicula.setTitulo(dto.titulo());
        pelicula.setGenero(dto.genero());
        pelicula.setFechaEstreno(dto.fechaEstreno());
        pelicula.setDirector(directorService.buscarPorId(dto.directorId()));
        return peliculaRepository.save(pelicula);
    }

    public void deletePelicula(Long id) {
        peliculaRepository.delete(buscarPorId(id));
    }



    public Pelicula asignarActor(Long peliculaId, Long actorId) {
        Pelicula pelicula = buscarPorId(peliculaId);
        Actor actor = actorService.buscarPorId(actorId);

        boolean yaEnReparto = pelicula.getActores().stream()
                .anyMatch(a -> a.getId().equals(actorId));
        if (yaEnReparto)
            throw new ActorYaEnRepartoException(actorId, peliculaId);

        pelicula.getActores().add(actor);
        return peliculaRepository.save(pelicula);
    }
}
