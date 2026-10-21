package com.salesianos.dam.GestordePeliculas.dto.dtoPelicula;

public class EditarPeliculaDto {

    @NotBlank(message = "El título no puede estar vacío")
    String titulo,

    @NotBlank(message = "El género no puede estar vacío")
    String genero,

    @NotNull(message = "La fecha de estreno es obligatoria")
    LocalDate fechaEstreno,


) {

        public Pelicula to() {
            return Pelicula.builder()
                    .titulo(titulo)
                    .genero(genero)
                    .fechaEstreno(fechaEstreno)
                    .build();
        }




}
