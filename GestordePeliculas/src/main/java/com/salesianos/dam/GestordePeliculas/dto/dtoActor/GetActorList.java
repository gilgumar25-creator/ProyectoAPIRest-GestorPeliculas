package com.salesianos.dam.GestordePeliculas.dto.dtoActor;

import com.salesianos.dam.GestordePeliculas.models.Actor;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "GetActorList", description = "Item de una lista de actores")
public record GetActorList(
        @Schema(description = "Identificador único del actor", example = "7")
        Long id,

        @Schema(description = "Nombre del actor", example = "Leonardo DiCaprio")
        String name
) {

    public static GetActorList of(Actor a) {
        return new GetActorList(a.getId(), a.getName());
    }
}