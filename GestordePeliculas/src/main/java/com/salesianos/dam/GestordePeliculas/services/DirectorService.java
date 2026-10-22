package com.salesianos.dam.GestordePeliculas.services;


import com.salesianos.dam.GestordePeliculas.dto.dtoDirector.EditarDirectorDto;
import com.salesianos.dam.GestordePeliculas.error.DirectorNotFoundException;
import com.salesianos.dam.GestordePeliculas.models.Director;
import com.salesianos.dam.GestordePeliculas.repos.DirectorRepository;
import com.salesianos.dam.GestordePeliculas.repos.PeliculaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DirectorService {

    private final DirectorRepository directorRepository;

    public List <Director> listar () {
        return directorRepository.findAll();
    }

    public Director buscarPorId (Long id) {
        return directorRepository.findById(id).orElseThrow(() -> new DirectorNotFoundException(id));
    }

    public Director addDirector(Director director){

        return directorRepository.save(director);
    }

    public Director updateDirector(Long id, EditarDirectorDto dto) {
        Director director = buscarPorId(id);
        director.setName(dto.name());
        director.setAnioNacimiento(dto.anioNacimiento());
        return directorRepository.save(director);
    }

    public void eliminarDirector(Long id) {
        directorRepository.delete(buscarPorId(id));
    }
    /*LOS MÉTODOS EDITAR NO PUEDO HACERLOS HASTA QUE TENGA EL DTO
    public Director editarDirector(Long id, Director datos) {
        Director director = buscarPorId(id);
        director.setName(datos.getName());
        director.setAnioNacimiento(datos.getAnioNacimiento());
        return directorRepository.save(director);
    }

     */




}
