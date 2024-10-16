package com.eVolGreen.eVolGreen.Configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuración de CORS para la aplicación eVolGreen.
 * Permite configuraciones de CORS globales para permitir accesos de diferentes orígenes.
 */
/**
 * Configuración de CORS para la aplicación.
 * Permite solicitudes desde cualquier origen y métodos específicos.
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    /**
     * Configura los mapeos de CORS para permitir todos los orígenes y métodos.
     *
     * @param registry el registro CORS para agregar las configuraciones
     */

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(false)
                .maxAge(3600);
    }
}



