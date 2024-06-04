//package com.eVolGreen.eVolGreen.Configurations;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.socket.config.annotation.EnableWebSocket;
//import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
//import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
//
//@Configuration
//@EnableWebSocket
//public class WebSocketConfig implements WebSocketConfigurer {
//
//    private final ChargePointWebSocketHandler handler;
//
//    public WebSocketConfig(ChargePointWebSocketHandler handler) {
//        this.handler = handler;
//    }
//
//    @Override
//    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
//        registry.addHandler(handler, "/ws")
//                .setAllowedOrigins("*"); // Permitir conexiones desde cualquier origen (ajusta según sea necesario)
//    }
//}
