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
public class CorsConfig implements WebMvcConfigurer {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
//                        .allowedOrigins("https://app.evolgreen.com") // Cambia el puerto según el puerto de tu front-end
                        .allowedOrigins("*") // Cambia el puerto según el puerto de tu front-end
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH")
                        .allowedHeaders("*")
                        .allowCredentials(false); // Permitimos el uso de credenciales como cookies o cabeceras de autorización
            }
        };
    }

//    @RestController
//    public class PreflightHandler {
//
//        @CrossOrigin(origins = {"*"})
//        @RequestMapping(value = "/**", method = RequestMethod.OPTIONS)
//        public void handlePreflight() {
//            // Maneja las solicitudes preflight
//        }
//    }
}

//package com.eVolGreen.eVolGreen.Configurations;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
///**
// * Configuración de CORS para la aplicación eVolGreen.
// * Permite configuraciones de CORS globales para permitir accesos de diferentes orígenes.
// */
///**
// * Configuración de CORS para la aplicación.
// * Permite solicitudes desde cualquier origen y métodos específicos.
// */
//@Configuration
//public class CorsConfig implements WebMvcConfigurer {
//
//    /**
//     * Configura los mapeos de CORS para permitir todos los orígenes y métodos.
//     *
//     * @param registry el registro CORS para agregar las configuraciones
//     */
//
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowedOrigins("*")
//                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
//                .allowedHeaders("*")
//                .allowCredentials(false)
//                .maxAge(3600);
//    }
//}





