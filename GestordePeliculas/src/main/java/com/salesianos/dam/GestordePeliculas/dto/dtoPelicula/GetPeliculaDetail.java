package com.salesianos.dam.GestordePeliculas.dto.dtoPelicula;


import com.salesianos.dam.GestordePeliculas.models.Pelicula;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.util.List;

@Schema(name="GetPeliculaDetail", description= "Detalle de una película")
public record GetPeliculaDetail (

    @Schema(description = "Identificador único de la película", example = "1")
        Long id,

    @Schema(description = "Título de la película", example = "Señor de los anillos")
        String titulo,

    @Schema(description = "Género de la película", example = "Fantasía")
        String genero,

    @Schema(description = "Fecha de estreno", example = "2010-07-16")
        LocalDate fechaEstreno,

        @Schema(description = "Director de la película")
                    DirectorSimpleDTO director,
            @Schema(description = "Reparto de la película")
    List<ActorSimpleDTO> actores
        ) {
                public record DirectorSimpleDTO(Long id, String name) {}
                public record ActorSimpleDTO(Long id, String name) {}

                public static GetPeliculaDetail of(Pelicula p) {
                        return new GetPeliculaDetail(
                                p.getId(),
                                p.getTitulo(),
                                p.getGenero(),
                                p.getFechaEstreno(),
                                new DirectorSimpleDTO(p.getDirector().getId(), p.getDirector().getName()),
                                p.getActores().stream()
                                        .map(a -> new ActorSimpleDTO(a.getId(), a.getName()))
                                        .toList()
                        );
                }
}







