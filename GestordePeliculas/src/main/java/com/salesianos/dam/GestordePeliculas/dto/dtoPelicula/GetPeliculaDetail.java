package com.salesianos.dam.GestordePeliculas.dto.dtoPelicula;


import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

@Schema(name="GetPeliculaDetail", description= "Detalle de una película")
public record GetPeliculaDetail (

    @Schema(description = "Identificador único de la película", example = "1")
        Long id,

    @Schema(description = "Título de la película", example = "Señor de los anillos")
        String titulo,

    @Schema(description = "Género de la película", example = "Fantasía")
        String genero,

    @Schema(description = "Fecha de estreno", example = "2010-07-16")
        LocalDate fechaEstreno

){

}







