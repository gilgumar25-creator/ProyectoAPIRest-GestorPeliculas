package com.salesianos.dam.GestordePeliculas.validation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = DirectorMayorDeEdadValidator.class)
public @interface DirectorMayorDeEdad {
    String message() default "El director debe tener al menos {edadMinima} años en la fecha de estreno";
    int edadMinima() default 18;
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

