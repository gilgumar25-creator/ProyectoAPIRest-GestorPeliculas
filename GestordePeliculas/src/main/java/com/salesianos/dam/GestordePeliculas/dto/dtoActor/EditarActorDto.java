package com.salesianos.dam.GestordePeliculas.dto.dtoActor;

import com.salesianos.dam.GestordePeliculas.models.Actor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EditarActorDto(
        @NotBlank(message = "El nombre no puede estar vacío")
        String name

) {

    public Actor to() {
        return Actor.builder()
                .name(name)
                .build();
    }
}