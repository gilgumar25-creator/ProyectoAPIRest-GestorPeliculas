package com.salesianos.dam.GestordePeliculas.services;

import com.salesianos.dam.GestordePeliculas.repos.PeliculaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class PeliculaService {

    private final PeliculaRepository peliculaRepository;
}
