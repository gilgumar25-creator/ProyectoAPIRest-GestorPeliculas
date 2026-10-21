package com.salesianos.dam.GestordePeliculas.error;

public class ActorNotFoundException extends RuntimeException{
        public ActorNotFoundException(Long id) {
            super("No se ha encontrado el actor con id " + id);
        }
    }

