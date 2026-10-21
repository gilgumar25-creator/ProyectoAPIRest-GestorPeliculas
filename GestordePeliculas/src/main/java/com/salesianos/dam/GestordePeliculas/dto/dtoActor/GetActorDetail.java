package com.salesianos.dam.GestordePeliculas.dto.dtoActor;

import com.salesianos.dam.GestordePeliculas.dto.dtoPelicula.GetPeliculaList;
import io.swagger.v3.oas.annotations.media.Schema;
import com.salesianos.dam.GestordePeliculas.models.Actor;

import java.util.List;
import java.util.Set;

@Schema(name = "GetActorDetail", description = "Datos de un actor")
public record GetActorDetail(
        @Schema(description = "Id único del actor", example = "1")
        Long id,

        @Schema(description = "Nombre del actor", example = "Daniel Raddclife")
        String name,

        @Schema(description = "Lista de Películas", example = "[Harry Potter, Esto es Magia 2...]")
        List<GetPeliculaList> peliculas
) {

    public static GetActorDetail of(Actor a) {
        return new GetActorDetail(
                a.getId(),
                a.getName(),
                a.getPeliculas().stream().map(GetPeliculaList::of).toList()
        );
    }
}