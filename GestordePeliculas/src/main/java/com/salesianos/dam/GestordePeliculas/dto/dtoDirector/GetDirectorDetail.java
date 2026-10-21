package com.salesianos.dam.GestordePeliculas.dto.dtoDirector;

import io.swagger.v3.oas.annotations.media.Schema;
import com.salesianos.dam.GestordePeliculas.models.Director;

    @Schema(name = "GetDirectorDetail", description = "Datos de un director")
    public record GetDirectorDetail(
            @Schema(description = "Id único del director", example = "1")
            Long id,

            @Schema(description = "Nombre del director", example = "Christopher Nolan")
            String name,

            @Schema(description = "Año de nacimiento", example = "1970")
            Integer anioNacimiento
    ) {

        public static GetDirectorDetail of(Director d) {
            return new GetDirectorDetail(d.getId(), d.getName(), d.getAnioNacimiento());
        }
    }

