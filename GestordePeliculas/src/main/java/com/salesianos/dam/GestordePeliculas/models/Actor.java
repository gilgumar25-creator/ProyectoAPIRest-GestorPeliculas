package com.salesianos.dam.GestordePeliculas.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Actor {

    @Id @GeneratedValue
    private Long id;
    private String name;
    @ManyToMany(mappedBy = "actores")
    @Builder.Default
    private Set<Pelicula> peliculas = new HashSet<>();
    /*
    Relación muchos a muchos con pelicula
     */



}
