package com.salesianos.dam.GestordePeliculas.controllers;

import com.salesianos.dam.GestordePeliculas.dto.dtoPelicula.EditarPeliculaDto;
import com.salesianos.dam.GestordePeliculas.dto.dtoPelicula.GetPeliculaDetail;
import com.salesianos.dam.GestordePeliculas.dto.dtoPelicula.GetPeliculaList;
import com.salesianos.dam.GestordePeliculas.services.PeliculaService;
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
@RequestMapping("/api/v1/peliculas")
@Validated
@Tag(name = "Película Controller", description = "CRUD de películas y gestión del reparto (relación M:M con actores)")
public class PeliculaController {

    private final PeliculaService peliculaService;

    @Operation(summary = "Listar películas")
    @ApiResponse(responseCode = "200", description = "Listado de películas")
    @GetMapping
    public ResponseEntity<List<GetPeliculaList>> listar() {
        return ResponseEntity.ok(peliculaService.listar().stream().map(GetPeliculaList::of).toList());
    }

    @Operation(summary = "Obtener una película con su director y su reparto de actores")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Película encontrada"),
            @ApiResponse(responseCode = "404", description = "Película no encontrada",
                    content = @Content(mediaType = "application/problem+json",
                            schema = @Schema(implementation = ProblemDetail.class)))})
    @GetMapping("/{peliculaId}")
    public ResponseEntity<GetPeliculaDetail> getById(@PathVariable @Min(value = 1) Long peliculaId) {
        return ResponseEntity.ok(GetPeliculaDetail.of(peliculaService.buscarPorId(peliculaId)));
    }

    @Operation(summary = "Crear una película", description = "Requiere el id de un director existente, con al menos 18 años en la fecha de estreno.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Película creada"),
            @ApiResponse(responseCode = "400", description = "Datos no válidos (incluye director menor de 18 años)",
                    content = @Content(mediaType = "application/problem+json",
                            schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "404", description = "Director no encontrado",
                    content = @Content(mediaType = "application/problem+json",
                            schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "409", description = "Ya existe una película con ese título",
                    content = @Content(mediaType = "application/problem+json",
                            schema = @Schema(implementation = ProblemDetail.class)))})
    @PostMapping
    public ResponseEntity<GetPeliculaDetail> add(@Valid @RequestBody EditarPeliculaDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(GetPeliculaDetail.of(peliculaService.addPelicula(dto)));
    }

    @Operation(summary = "Actualizar una película")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Película actualizada"),
            @ApiResponse(responseCode = "400", description = "Datos no válidos",
                    content = @Content(mediaType = "application/problem+json",
                            schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "404", description = "Película o director no encontrado",
                    content = @Content(mediaType = "application/problem+json",
                            schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "409", description = "Título duplicado",
                    content = @Content(mediaType = "application/problem+json",
                            schema = @Schema(implementation = ProblemDetail.class)))})
    @PutMapping("/{peliculaId}")
    public ResponseEntity<GetPeliculaDetail> update(@PathVariable @Min(value = 1) Long peliculaId,
                                                    @Valid @RequestBody EditarPeliculaDto dto) {
        return ResponseEntity.ok(GetPeliculaDetail.of(peliculaService.updatePelicula(peliculaId, dto)));
    }

    @Operation(summary = "Eliminar una película")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Película eliminada"),
            @ApiResponse(responseCode = "404", description = "Película no encontrada",
                    content = @Content(mediaType = "application/problem+json",
                            schema = @Schema(implementation = ProblemDetail.class)))})
    @DeleteMapping("/{peliculaId}")
    public ResponseEntity<Void> delete(@PathVariable @Min(value = 1) Long peliculaId) {
        peliculaService.deletePelicula(peliculaId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Asignar un actor al reparto de una película (relación M:M)")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Actor asignado; devuelve la película con su reparto actualizado"),
            @ApiResponse(responseCode = "404", description = "Película o actor no encontrado",
                    content = @Content(mediaType = "application/problem+json",
                            schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "409", description = "El actor ya está en el reparto de esa película",
                    content = @Content(mediaType = "application/problem+json",
                            schema = @Schema(implementation = ProblemDetail.class)))})
    @PostMapping("/{peliculaId}/actores/{actorId}")
    public ResponseEntity<GetPeliculaDetail> asignarActor(@PathVariable @Min(value = 1) Long peliculaId,
                                                          @PathVariable @Min(value = 1) Long actorId) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(GetPeliculaDetail.of(peliculaService.asignarActor(peliculaId, actorId)));
    }
}