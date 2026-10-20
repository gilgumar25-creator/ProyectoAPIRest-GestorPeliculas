package com.salesianos.dam.GestordePeliculas.services;


import com.salesianos.dam.GestordePeliculas.repos.ActorRepository;
import com.salesianos.dam.GestordePeliculas.repos.PeliculaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ActorService {

    private final ActorRepository actorRepository;

}
