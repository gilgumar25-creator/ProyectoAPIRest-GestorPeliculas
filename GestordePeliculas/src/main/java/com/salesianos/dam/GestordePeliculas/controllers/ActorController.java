package com.salesianos.dam.GestordePeliculas.controllers;

import com.salesianos.dam.GestordePeliculas.dto.dtoActor.EditarActorDto;
import com.salesianos.dam.GestordePeliculas.dto.dtoActor.GetActorDetail;
import com.salesianos.dam.GestordePeliculas.dto.dtoActor.GetActorList;
import com.salesianos.dam.GestordePeliculas.models.Actor;
import com.salesianos.dam.GestordePeliculas.repos.ActorRepository;
import com.salesianos.dam.GestordePeliculas.services.ActorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/actores")
@Validated
@Tag(name="Actor Controller", description = "Un controlador de actores")
public class ActorController {

    private final ActorService actorService;

    @Operation(summary = "Crear un actor")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Actor creado"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada no válidos",
                    content = @Content(mediaType = "application/problem+json",
                            schema = @Schema(implementation = ProblemDetail.class)))})
    @PostMapping
    public ResponseEntity<GetActorDetail> addActor(@Valid @RequestBody EditarActorDto actor) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(GetActorDetail.of(actorService.addActor(actor.to())));
    }

    @Operation(summary = "Listar actores")
    @ApiResponse(responseCode = "200", description = "Listado de actores")
    @GetMapping
    public ResponseEntity<List<GetActorList>> getAllActors() {
        return ResponseEntity.ok(
                actorService.listarActores().stream().map(GetActorList::of).toList());
    }

    @Operation(summary = "Obtener un actor por id, con las películas en las que participa")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Actor encontrado"),
            @ApiResponse(responseCode = "400", description = "Id no válido",
                    content = @Content(mediaType = "application/problem+json",
                            schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "404", description = "Actor no encontrado",
                    content = @Content(mediaType = "application/problem+json",
                            schema = @Schema(implementation = ProblemDetail.class)))})
    @GetMapping("/{id}")
    public ResponseEntity<GetActorDetail> getActorById(@PathVariable @Min(value = 1) Long id) {
        return ResponseEntity.ok(GetActorDetail.of(actorService.buscarPorId(id)));
    }

    @Operation(summary = "Actualizar un actor")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Actor actualizado"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada o id no válidos",
                    content = @Content(mediaType = "application/problem+json",
                            schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "404", description = "Actor no encontrado",
                    content = @Content(mediaType = "application/problem+json",
                            schema = @Schema(implementation = ProblemDetail.class)))})
    @PutMapping("/{id}")
    public ResponseEntity<GetActorDetail> updateActor(@PathVariable @Min(value = 1) Long id,
                                                      @Valid @RequestBody EditarActorDto actor) {
        return ResponseEntity.ok(GetActorDetail.of(actorService.updateActor(id, actor)));
    }

    @Operation(summary = "Eliminar un actor",
            description = "También se elimina de los repartos de las películas en las que participaba.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Actor eliminado"),
            @ApiResponse(responseCode = "400", description = "Id no válido",
                    content = @Content(mediaType = "application/problem+json",
                            schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "404", description = "Actor no encontrado",
                    content = @Content(mediaType = "application/problem+json",
                            schema = @Schema(implementation = ProblemDetail.class)))})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteActor(@PathVariable @Min(value = 1) Long id) {
        actorService.deleteActor(id);
        return ResponseEntity.noContent().build();
    }
}
