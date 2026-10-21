package com.salesianos.dam.GestordePeliculas.services;


import com.salesianos.dam.GestordePeliculas.repos.DirectorRepository;
import com.salesianos.dam.GestordePeliculas.repos.PeliculaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DirectorService {

    private final DirectorRepository directorRepository;

}
