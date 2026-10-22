package com.salesianos.dam.GestordePeliculas.controllers;

import com.salesianos.dam.GestordePeliculas.dto.dtoActor.EditarActorDto;
import com.salesianos.dam.GestordePeliculas.dto.dtoActor.GetActorDetail;
import com.salesianos.dam.GestordePeliculas.dto.dtoActor.GetActorList;
import com.salesianos.dam.GestordePeliculas.models.Actor;
import com.salesianos.dam.GestordePeliculas.repos.ActorRepository;
import com.salesianos.dam.GestordePeliculas.services.ActorService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/actor")
@Validated
@Tag(name="Actor Controller", description = "Un controlador de actores")
public class ActorController {

    private final ActorRepository actorRepository;
    private final ActorService actorService;

    @PostMapping
    public ResponseEntity<GetActorDetail> addActor(
            @Valid @RequestBody EditarActorDto actor) {

        return ResponseEntity.status(201)
                .body(GetActorDetail.of(actorService.addActor(actor)));

    }
    @GetMapping
    public ResponseEntity<List<GetActorList>> getAllActors() {

        List<Actor> result = actorService.listarActores();
        return ResponseEntity.ok(
                result
                        .stream()
                        .map(GetActorList::of)
                        .toList());
    }

    @GetMapping
    public ResponseEntity<GetActorDetail> getActorById(@PathVariable @Min(value = 1) Long id) {

        return ResponseEntity.ok(GetActorDetail.of(actorService.buscarPorId(id)));

    }

    @PutMapping("/{id}")
    public ResponseEntity<GetActorDetail> updateActor(
            @PathVariable Long id,
            @RequestBody EditarActorDto actor) {

        return ResponseEntity.ok(
                GetActorDetail.of(actorService.updateActor(id , actor))
        );


    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteActor(
            @PathVariable  @Min(value = 1 )Long id){
            actorService.deleteActor(id);
            return  ResponseEntity.noContent().build();

    }








}
