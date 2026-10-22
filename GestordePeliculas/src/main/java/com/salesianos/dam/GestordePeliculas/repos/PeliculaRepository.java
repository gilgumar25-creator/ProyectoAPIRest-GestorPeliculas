package com.salesianos.dam.GestordePeliculas.repos;

import com.salesianos.dam.GestordePeliculas.models.Pelicula;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PeliculaRepository extends JpaRepository<Pelicula,Long> {
    boolean existsByTituloIgnoreCase(@NotBlank(message = "El título no puede estar vacío") String titulo);
    boolean existsByTituloIgnoreCaseAndIdNot(@NotBlank(message = "El título no puede estar vacío") String titulo, Long id);
}
