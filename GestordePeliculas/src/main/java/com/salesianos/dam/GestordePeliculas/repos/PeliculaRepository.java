package com.salesianos.dam.GestordePeliculas.repos;

import com.salesianos.dam.GestordePeliculas.models.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PeliculaRepository extends JpaRepository<Long,Pelicula> {
}
