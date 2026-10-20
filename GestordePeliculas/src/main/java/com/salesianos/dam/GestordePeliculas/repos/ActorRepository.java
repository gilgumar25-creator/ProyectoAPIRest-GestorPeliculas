package com.salesianos.dam.GestordePeliculas.repos;

import com.salesianos.dam.GestordePeliculas.models.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorRepository extends JpaRepository<Long,Actor> {
}
