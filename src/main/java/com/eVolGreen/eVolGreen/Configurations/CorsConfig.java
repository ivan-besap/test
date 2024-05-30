package com.eVolGreen.eVolGreen.Configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")  // Asegúrate de cubrir todas las rutas
                        .allowedOrigins("http://localhost:45000", "http://localhost:53672")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // Asegúrate de incluir OPTIONS para preflight requests
                        .allowedHeaders("*")
                        .allowCredentials(true);  // Permite el uso de credenciales si es necesario
            }
        };
    }


    @RestController
    public class PreflightHandler {

        @CrossOrigin(origins = {"http://localhost:45000", "http://localhost:53672"})
        @RequestMapping(value = "/**", method = RequestMethod.OPTIONS)
        public void handlePreflight() {
            // Maneja las solicitudes preflight

        }
    }
}
