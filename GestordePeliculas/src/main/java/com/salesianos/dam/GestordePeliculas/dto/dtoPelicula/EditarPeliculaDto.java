package com.salesianos.dam.GestordePeliculas.dto.dtoPelicula;

import com.salesianos.dam.GestordePeliculas.models.Director;
import com.salesianos.dam.GestordePeliculas.models.Pelicula;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public record EditarPeliculaDto(

        @NotBlank(message = "El título no puede estar vacío")
        String titulo,

        @NotBlank(message = "El género no puede estar vacío")
        String genero,

        @NotNull(message = "La fecha de estreno es obligatoria")
        LocalDate fechaEstreno,

        @NotNull(message = "El id del director es obligatorio")
        @Positive(message = "El id del director debe ser positivo")
        Long directorId

) {

    public Pelicula to() {
        return Pelicula.builder()
                .titulo(titulo)
                .genero(genero)
                .fechaEstreno(fechaEstreno)
                .build();
    }

}







