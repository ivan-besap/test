//package com.eVolGreen.eVolGreen.Configurations;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class CorsConfig {
//
//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/api/**") // Especifica el path que deseas permitir CORS
//                        .allowedOrigins("http://localhost:8083") // Especifica los orígenes permitidos (en este caso, tu frontend)
//                        .allowedMethods("GET", "POST", "PUT", "DELETE") // Especifica los métodos HTTP permitidos
//                        .allowedHeaders("*"); // Permite todos los encabezados
//            }
//        };
//    }
//}
//
