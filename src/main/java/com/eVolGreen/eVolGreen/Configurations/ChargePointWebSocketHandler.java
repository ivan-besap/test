//package com.eVolGreen.eVolGreen.Configurations;
//
//import org.springframework.web.socket.*;
//import org.springframework.stereotype.Component;
//
//@Component
//public class ChargePointWebSocketHandler implements WebSocketHandler {
//
//    @Override
//    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
//        // La conexión WebSocket se ha establecido
//        System.out.println("WebSocket connection established");
//    }
//
//    @Override
//    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
//
//    }
//
//    @Override
//    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
//        // Manejar errores de transporte
//        System.out.println("WebSocket transport error: " + exception.getMessage());
//    }
//
//    @Override
//    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
//        // La conexión WebSocket se ha cerrado
//        System.out.println("WebSocket connection closed");
//    }
//
//    @Override
//    public boolean supportsPartialMessages() {
//        return false;
//    }
//}
