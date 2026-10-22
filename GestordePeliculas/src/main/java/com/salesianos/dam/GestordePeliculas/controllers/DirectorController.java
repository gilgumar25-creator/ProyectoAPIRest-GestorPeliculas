package com.salesianos.dam.GestordePeliculas.controllers;

import com.salesianos.dam.GestordePeliculas.dto.dtoDirector.EditarDirectorDto;
import com.salesianos.dam.GestordePeliculas.dto.dtoDirector.GetDirectorDetail;
import com.salesianos.dam.GestordePeliculas.dto.dtoDirector.GetDirectorList;
import com.salesianos.dam.GestordePeliculas.services.DirectorService;
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
@RequestMapping("/api/v1/directores")
@Validated
@Tag(name = "Director Controller", description = "CRUD de directores")
public class DirectorController {

    private final DirectorService directorService;

    @Operation(summary = "Listar directores")
    @ApiResponse(responseCode = "200", description = "Listado de directores")
    @GetMapping
    public ResponseEntity<List<GetDirectorList>> listar() {
        return ResponseEntity.ok(
                directorService.listar().stream().map(GetDirectorList::of).toList());
    }

    @Operation(summary = "Obtener un director por id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Director encontrado"),
            @ApiResponse(responseCode = "404", description = "Director no encontrado",
                    content = @Content(mediaType = "application/problem+json",
                            schema = @Schema(implementation = ProblemDetail.class)))})
    @GetMapping("/{id}")
    public ResponseEntity<GetDirectorDetail> getById(@PathVariable @Min(value = 1) Long id) {
        return ResponseEntity.ok(GetDirectorDetail.of(directorService.buscarPorId(id)));
    }

    @Operation(summary = "Crear un director")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Director creado"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada no válidos",
                    content = @Content(mediaType = "application/problem+json",
                            schema = @Schema(implementation = ProblemDetail.class)))})
    @PostMapping
    public ResponseEntity<GetDirectorDetail> add(@Valid @RequestBody EditarDirectorDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(GetDirectorDetail.of(directorService.addDirector(dto.to())));
    }

    @Operation(summary = "Actualizar un director")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Director actualizado"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada no válidos",
                    content = @Content(mediaType = "application/problem+json",
                            schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "404", description = "Director no encontrado",
                    content = @Content(mediaType = "application/problem+json",
                            schema = @Schema(implementation = ProblemDetail.class)))})
    @PutMapping("/{id}")
    public ResponseEntity<GetDirectorDetail> update(@PathVariable @Min(value = 1) Long id,
                                                    @Valid @RequestBody EditarDirectorDto dto) {
        return ResponseEntity.ok(GetDirectorDetail.of(directorService.updateDirector(id, dto)));
    }

    @Operation(summary = "Eliminar un director")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Director eliminado"),
            @ApiResponse(responseCode = "404", description = "Director no encontrado",
                    content = @Content(mediaType = "application/problem+json",
                            schema = @Schema(implementation = ProblemDetail.class)))})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDirector(@PathVariable @Min(value = 1) Long id) {
        directorService.eliminarDirector(id);
        return ResponseEntity.noContent().build();
    }
}
