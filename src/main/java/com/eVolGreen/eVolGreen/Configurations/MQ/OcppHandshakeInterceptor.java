//package com.eVolGreen.eVolGreen.Configurations.MQ;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.http.server.ServerHttpRequest;
//import org.springframework.http.server.ServerHttpResponse;
//import org.springframework.web.socket.server.HandshakeInterceptor;
//
//import java.util.List;
//import java.util.Map;
//
//public class OcppHandshakeInterceptor implements HandshakeInterceptor {
//    private static final Logger logger = LoggerFactory.getLogger(OcppHandshakeInterceptor.class);
//    private final String supportedProtocol;
//
//    public OcppHandshakeInterceptor(String supportedProtocol) {
//        this.supportedProtocol = supportedProtocol;
//    }
//
//    @Override
//    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, org.springframework.web.socket.WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
//        String protocol = request.getHeaders().getFirst("Sec-WebSocket-Protocol");
//        if ("ocpp1.6".equals(protocol)) {
//            attributes.put("subProtocol", protocol); // Almacena el subprotocolo en los atributos de la sesión
//            logger.info("Subprotocolo validado: {}", protocol);
//            return true;
//        }
//        logger.warn("Subprotocolo no soportado: {}", protocol);
//        return false;
//    }
//
//    @Override
//    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response,
//                               org.springframework.web.socket.WebSocketHandler wsHandler,
//                               Exception exception) {
//
//    }
//}
