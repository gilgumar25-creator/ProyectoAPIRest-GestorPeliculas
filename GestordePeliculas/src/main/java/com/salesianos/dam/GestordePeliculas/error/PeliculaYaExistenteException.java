package com.salesianos.dam.GestordePeliculas.error;

public class PeliculaYaExistenteException extends RuntimeException {
    public PeliculaYaExistenteException(String titulo) {
        super("Ya existe una película con el título '" + titulo + "'");
    }
}
