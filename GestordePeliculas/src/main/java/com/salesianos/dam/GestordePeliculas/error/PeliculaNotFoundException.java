package com.salesianos.dam.GestordePeliculas.error;

public class PeliculaNotFoundException extends RuntimeException{
        public PeliculaNotFoundException(Long id) {
            super("No se ha encontrado la película con id " + id);
        }
    }

