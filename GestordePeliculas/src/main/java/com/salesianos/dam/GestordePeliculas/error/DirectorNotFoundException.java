package com.salesianos.dam.GestordePeliculas.error;

public class DirectorNotFoundException extends RuntimeException{
        public DirectorNotFoundException(Long id) {
            super("No se ha encontrado el director con id " + id);
        }
    }

