package com.salesianos.dam.GestordePeliculas.validation;

import com.salesianos.dam.GestordePeliculas.dto.dtoPelicula.EditarPeliculaDto;
import com.salesianos.dam.GestordePeliculas.repos.DirectorRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DirectorMayorDeEdadValidator
        implements ConstraintValidator<DirectorMayorDeEdad, EditarPeliculaDto> {

    private final DirectorRepository directorRepository;
    private int edadMinima;

    public DirectorMayorDeEdadValidator(DirectorRepository directorRepository) {
        this.directorRepository = directorRepository;
    }


    @Override
    public boolean isValid(EditarPeliculaDto dto, ConstraintValidatorContext context) {
        if (dto.directorId() == null || dto.fechaEstreno() == null) return true;

        return directorRepository.findById(dto.directorId())
                .map(d -> {
                    boolean ok = dto.fechaEstreno().getYear() - d.getAnioNacimiento() >= edadMinima;
                    if (!ok) {
                        context.disableDefaultConstraintViolation();
                        context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
                                .addPropertyNode("fechaEstreno")
                                .addConstraintViolation();
                    }
                    return ok;
                })
                .orElse(true);
    }
}