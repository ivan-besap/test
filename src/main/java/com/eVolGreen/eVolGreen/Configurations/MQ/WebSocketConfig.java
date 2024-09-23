package com.eVolGreen.eVolGreen.Configurations.MQ;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * Configuración de WebSocket para la aplicación.
 * Esta clase habilita el soporte de WebSocket y define las rutas para manejar conexiones WebSocket.
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    /**
     * Registra el manejador de WebSocket y la ruta a la que responde.
     * En este caso, todas las conexiones a "/ocpp" serán manejadas por el `WebSocketHandler`.
     *
     * @param registry Registro de manejadores de WebSocket para definir las rutas y handlers.
     */
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(new WebSocketHandler(), "/ocpp")
                .setAllowedOrigins("*"); // Permite conexiones desde cualquier origen (CORS).
    }

    /**
     * Define el `ServerEndpointExporter` para habilitar los endpoints WebSocket.
     * Esto es necesario para que Spring pueda gestionar los endpoints WebSocket.
     *
     * El bean solo se registra si el perfil activo no es "test", evitando conflictos en los tests.
     *
     * @return Una instancia de `ServerEndpointExporter`.
     */
    @Bean
    @Profile("!test")
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}
