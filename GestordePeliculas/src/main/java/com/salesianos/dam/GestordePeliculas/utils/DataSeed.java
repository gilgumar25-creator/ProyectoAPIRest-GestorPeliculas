package com.salesianos.dam.GestordePeliculas.utils;

import com.salesianos.dam.GestordePeliculas.models.Actor;
import com.salesianos.dam.GestordePeliculas.models.Director;
import com.salesianos.dam.GestordePeliculas.models.Pelicula;
import com.salesianos.dam.GestordePeliculas.repos.ActorRepository;
import com.salesianos.dam.GestordePeliculas.repos.DirectorRepository;
import com.salesianos.dam.GestordePeliculas.repos.PeliculaRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataSeed {

    private final DirectorRepository directorRepository;
    private final ActorRepository actorRepository;
    private final PeliculaRepository peliculaRepository;

    @PostConstruct
    public void initData() {

        // ---------- DIRECTORES ----------
        Director d1 = director("Christopher Nolan", 1970);
        Director d2 = director("Quentin Tarantino", 1963);
        Director d3 = director("Steven Spielberg", 1946);
        Director d4 = director("Martin Scorsese", 1942);
        Director d5 = director("Greta Gerwig", 1983);
        Director d6 = director("Denis Villeneuve", 1967);
        Director d7 = director("Guillermo del Toro", 1964);
        Director d8 = director("Pedro Almodóvar", 1949);
        Director d9 = director("Alejandro Amenábar", 1972);
        Director d10 = director("Bong Joon-ho", 1969);

        directorRepository.saveAll(List.of(d1, d2, d3, d4, d5, d6, d7, d8, d9, d10));

        // ---------- ACTORES ----------
        Actor a1 = actor("Leonardo DiCaprio");
        Actor a2 = actor("Joseph Gordon-Levitt");
        Actor a3 = actor("Matthew McConaughey");
        Actor a4 = actor("Anne Hathaway");
        Actor a5 = actor("Christian Bale");
        Actor a6 = actor("Heath Ledger");
        Actor a7 = actor("John Travolta");
        Actor a8 = actor("Samuel L. Jackson");
        Actor a9 = actor("Uma Thurman");
        Actor a10 = actor("Jamie Foxx");
        Actor a11 = actor("Sam Neill");
        Actor a12 = actor("Laura Dern");
        Actor a13 = actor("Jeff Goldblum");
        Actor a14 = actor("Liam Neeson");
        Actor a15 = actor("Tom Hanks");
        Actor a16 = actor("Matt Damon");
        Actor a17 = actor("Robert De Niro");
        Actor a18 = actor("Jodie Foster");
        Actor a19 = actor("Jack Nicholson");
        Actor a20 = actor("Margot Robbie");
        Actor a21 = actor("Saoirse Ronan");
        Actor a22 = actor("Timothée Chalamet");
        Actor a23 = actor("Ryan Gosling");
        Actor a24 = actor("Harrison Ford");
        Actor a25 = actor("Zendaya");
        Actor a26 = actor("Emily Blunt");
        Actor a27 = actor("Penélope Cruz");
        Actor a28 = actor("Javier Bardem");
        Actor a29 = actor("Cecilia Roth");
        Actor a30 = actor("Nicole Kidman");
        Actor a31 = actor("Song Kang-ho");
        Actor a32 = actor("Sally Hawkins");
        Actor a33 = actor("Ivana Baquero");
        Actor a34 = actor("Antonio Banderas");
        Actor a35 = actor("Eduardo Noriega");
        Actor a36 = actor("Ana Torrent");
        Actor a37 = actor("Tilda Swinton");
        Actor a38 = actor("Florence Pugh");
        Actor a39 = actor("Emma Watson");
        Actor a40 = actor("Marion Cotillard");

        actorRepository.saveAll(List.of(
                a1, a2, a3, a4, a5, a6, a7, a8, a9, a10,
                a11, a12, a13, a14, a15, a16, a17, a18, a19, a20,
                a21, a22, a23, a24, a25, a26, a27, a28, a29, a30,
                a31, a32, a33, a34, a35, a36, a37, a38, a39, a40
        ));

        // ---------- PELÍCULAS (con su reparto) ----------
        peliculaRepository.saveAll(List.of(
                // Christopher Nolan
                pelicula("Origen", "Ciencia ficción", "2010-07-16", d1, a1, a2, a40),
                pelicula("Interstellar", "Ciencia ficción", "2014-11-07", d1, a3, a4, a16),
                pelicula("El caballero oscuro", "Acción", "2008-07-18", d1, a5, a6),

                // Quentin Tarantino
                pelicula("Pulp Fiction", "Crimen", "1994-10-14", d2, a7, a8, a9),
                pelicula("Django desencadenado", "Western", "2012-12-25", d2, a1, a8, a10),
                pelicula("Kill Bill: Volumen 1", "Acción", "2003-10-10", d2, a9),

                // Steven Spielberg
                pelicula("Parque Jurásico", "Aventuras", "1993-06-11", d3, a11, a12, a13, a8),
                pelicula("La lista de Schindler", "Drama", "1993-12-15", d3, a14),
                pelicula("Salvar al soldado Ryan", "Bélico", "1998-07-24", d3, a15, a16),

                // Martin Scorsese
                pelicula("Taxi Driver", "Drama", "1976-02-08", d4, a17, a18),
                pelicula("Infiltrados", "Crimen", "2006-10-06", d4, a1, a16, a19),
                pelicula("El lobo de Wall Street", "Comedia", "2013-12-25", d4, a1, a20, a3),

                // Greta Gerwig
                pelicula("Lady Bird", "Comedia", "2017-11-03", d5, a21),
                pelicula("Mujercitas", "Drama", "2019-12-25", d5, a21, a22, a38, a39),
                pelicula("Barbie", "Comedia", "2023-07-21", d5, a20, a23),

                // Denis Villeneuve
                pelicula("Blade Runner 2049", "Ciencia ficción", "2017-10-06", d6, a23, a24),
                pelicula("Dune", "Ciencia ficción", "2021-10-22", d6, a22, a25, a28),
                pelicula("Sicario", "Thriller", "2015-09-18", d6, a26),

                // Guillermo del Toro
                pelicula("El laberinto del fauno", "Fantasía", "2006-10-11", d7, a33),
                pelicula("La forma del agua", "Fantasía", "2017-12-01", d7, a32),
                pelicula("El espinazo del diablo", "Terror", "2001-04-20", d7, a35),

                // Pedro Almodóvar
                pelicula("Todo sobre mi madre", "Drama", "1999-04-16", d8, a29, a27),
                pelicula("Volver", "Drama", "2006-03-17", d8, a27),
                pelicula("Dolor y gloria", "Drama", "2019-03-22", d8, a34, a27),

                // Alejandro Amenábar
                pelicula("Los otros", "Terror", "2001-08-02", d9, a30),
                pelicula("Tesis", "Thriller", "1996-04-12", d9, a35, a36),
                pelicula("Mar adentro", "Drama", "2004-09-03", d9, a28),

                // Bong Joon-ho
                pelicula("Parásitos", "Thriller", "2019-05-30", d10, a31),
                pelicula("Memorias de un asesino", "Crimen", "2003-04-25", d10, a31),
                pelicula("Okja", "Aventuras", "2017-06-28", d10, a31, a37)
        ));
    }

    // ---------- MÉTODOS AUXILIARES ----------

    private Director director(String name, int anioNacimiento) {
        return Director.builder()
                .name(name)
                .anioNacimiento(anioNacimiento)
                .build();
    }

    private Actor actor(String name) {
        return Actor.builder()
                .name(name)
                .build();
    }

    private Pelicula pelicula(String titulo, String genero, String fechaEstreno,
                              Director director, Actor... actores) {
        return Pelicula.builder()
                .titulo(titulo)
                .genero(genero)
                .fechaEstreno(LocalDate.parse(fechaEstreno))
                .director(director)
                .actores(new HashSet<>(Arrays.asList(actores)))
                .build();
    }

}
