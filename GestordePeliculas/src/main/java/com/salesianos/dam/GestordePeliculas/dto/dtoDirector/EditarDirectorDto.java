package com.salesianos.dam.GestordePeliculas.dto.dtoDirector;

import com.salesianos.dam.GestordePeliculas.models.Director;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EditarDirectorDto(
        @NotBlank(message = "El nombre no puede estar vacío")
        String name,

        @NotNull(message = "El año de nacimiento es obligatorio")
        Integer anioNacimiento
) {

    public Director to() {
        return Director.builder()
                .name(name)
                .anioNacimiento(anioNacimiento)
                .build();
    }
}