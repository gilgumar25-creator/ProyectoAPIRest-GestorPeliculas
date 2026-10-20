package com.salesianos.dam.GestordePeliculas.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pelicula {

    @Id
    @GeneratedValue
    private Long id;
    private String titulo;
    private String genero;
    private LocalDate fechaEstreno;
    @ManyToMany
    @JoinTable(name = "reparto",
            joinColumns = @JoinColumn(name = "actorId"),
            inverseJoinColumns = @JoinColumn(name = "peliculaId")
    )
    private Actor actor;
    @ManyToOne
    @JoinColumn(name = "director_id")
    private Director director;

    /*
    Asociación un director dirige muchas películas
     */

}