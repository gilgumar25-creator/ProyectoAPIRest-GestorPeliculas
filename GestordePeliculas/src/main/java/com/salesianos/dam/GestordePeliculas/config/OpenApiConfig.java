package com.salesianos.dam.GestordePeliculas.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI gestorPeliculasOpenAPI() {
        return new OpenAPI().info(new Info()
                .title("API REST - Gestión de películas")
                .version("1.0.0")
                .description("API para gestionar directores, películas y el reparto de actores. "
                        + "Los errores se devuelven en formato ProblemDetail (application/problem+json).")
                .contact(new Contact().name("gilgumar25-creator")));
    }
}
