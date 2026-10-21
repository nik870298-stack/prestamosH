package com.example.prestamosH.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**") // Aplica a todos los endpoints de la API
                        .allowedOriginPatterns("*") // Permite cualquier origen (Render, Android, Web, Postman)
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Métodos HTTP habilitados
                        .allowedHeaders("*") // Permite todas las cabeceras
                        .allowCredentials(false) // Desactivado para evitar conflictos cuando se usan comodines en orígenes
                        .maxAge(3600); // Tiempo de caché para las peticiones pre-flight (1 hora)
            }
        };
    }
}
