package com.salesianos.dam.GestordePeliculas.dto.dtoDirector;


import com.salesianos.dam.GestordePeliculas.models.Director;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;


@Schema(name = "GetDirectorList", description = "Item de una lista de directores")
public record GetDirectorList(
        @Schema(description = "Identificador único del director", example = "1")
        Long id,

        @Schema(description = "Nombre del director", example = "Christopher Nolan")
        String name,

        @Schema(description = "Año de nacimiento del director", example = "1970")
        Integer anioNacimiento


) {

    public static GetDirectorList of(Director d) {
        return new GetDirectorList(d.getId(), d.getName(), d.getAnioNacimiento());
    }
}




