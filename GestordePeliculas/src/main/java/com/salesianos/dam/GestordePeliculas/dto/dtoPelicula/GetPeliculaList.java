package com.salesianos.dam.GestordePeliculas.dto.dtoPelicula;

import com.salesianos.dam.GestordePeliculas.models.Pelicula;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "GetPeliculaList", description = "Item de una lista de películas")
public record GetPeliculaList(
        @Schema(description = "Identificador único de la película", example = "3")
        Long id,

        @Schema(description = "Título de la película", example = "Harry Potter")
        String titulo,

        @Schema(description = "Género de la película", example = "Fantasía")
        String genero,

        @Schema(description = "Nombre del director", example = "Christopher Nolan")
        String directorName
) {

    public static GetPeliculaList of(Pelicula p) {
        return new GetPeliculaList(
                p.getId(),
                p.getTitulo(),
                p.getGenero(),
                p.getDirector() != null ? p.getDirector().getName() : null
        );
    }
}