package com.salesianos.dam.GestordePeliculas.repos;

import com.salesianos.dam.GestordePeliculas.models.Director;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DirectorRepository extends JpaRepository<Long, Director> {
}
