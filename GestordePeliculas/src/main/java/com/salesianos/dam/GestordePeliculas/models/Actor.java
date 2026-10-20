package com.salesianos.dam.GestordePeliculas.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Actor {

    @Id @GeneratedValue
    private Long id;
    private String name;
    @ManyToMany
    @JoinTable(name = "reparto",
            joinColumns = @JoinColumn(name = "peliculaId"),
            inverseJoinColumns = @JoinColumn(name = "actorId")
    )
    private Pelicula pelicula;
    /*
    Relación muchos a muchos con pelicula
     */



}
