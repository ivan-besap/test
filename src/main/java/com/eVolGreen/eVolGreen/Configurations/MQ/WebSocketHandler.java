package com.eVolGreen.eVolGreen.Configurations.MQ;

import org.springframework.web.socket.*;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

/**
 * Manejador de conexiones WebSocket que soporta múltiples sesiones.
 * Esta clase se encarga de gestionar varias conexiones WebSocket simultáneas,
 * manejar mensajes de texto entrantes y gestionar errores durante la comunicación.
 *
 * <p>Usa un {@link ConcurrentHashMap} para gestionar varias sesiones activas de manera concurrente.</p>
 *
 * <b>Ejemplo de uso:</b>
 * <pre>
 *     {@code
 *     WebSocketHandler webSocketHandler = new WebSocketHandler();
 *     webSocketHandler.sendMessageToAll("¡Mensaje de prueba para todas las sesiones!");
 *     }
 * </pre>
 */
@Component
public class WebSocketHandler extends TextWebSocketHandler {

    /**
     * Mapa que contiene las sesiones WebSocket activas.
     * La clave es la sesión WebSocket y el valor es el identificador único asociado a esa sesión.
     */
    private final Map<WebSocketSession, String> sessions = new ConcurrentHashMap<>();

    /**
     * Se invoca cuando se establece una conexión WebSocket. La nueva sesión se almacena en el mapa de sesiones activas.
     *
     * @param session La sesión WebSocket recién establecida.
     * @throws Exception Si ocurre algún error durante el establecimiento de la conexión.
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // Se asocia un identificador único a la sesión basado en la dirección remota.
        String sessionId = session.getRemoteAddress().toString();
        sessions.put(session, sessionId);
        System.out.println("Conexión WebSocket establecida: " + sessionId);
    }

    /**
     * Maneja los mensajes de texto recibidos desde el cliente. Este método se invoca cada vez que se recibe un mensaje.
     *
     * @param session La sesión WebSocket desde la cual se recibe el mensaje.
     * @param message El mensaje de texto recibido.
     * @throws Exception Si ocurre algún error durante el procesamiento del mensaje.
     */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        System.out.println("Mensaje recibido de " + sessions.get(session) + ": " + message.getPayload());
        // Enviar una respuesta de confirmación al cliente.
        session.sendMessage(new TextMessage("Mensaje recibido correctamente"));
    }

    /**
     * Maneja los errores de transporte que ocurren durante la comunicación WebSocket.
     * Se invoca cuando ocurre un error, como pérdida de conexión o problemas de red.
     *
     * @param session La sesión WebSocket donde ocurrió el error.
     * @param exception La excepción que describe el error ocurrido.
     * @throws Exception Si ocurre algún error adicional al manejar la excepción.
     */
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        System.err.println("Error de transporte en WebSocket para la sesión " + sessions.get(session) + ": " + exception.getMessage());
    }

    /**
     * Se invoca cuando se cierra la conexión WebSocket. La sesión se elimina del mapa de sesiones activas.
     *
     * @param session La sesión WebSocket que se ha cerrado.
     * @param status El estado de cierre de la conexión.
     * @throws Exception Si ocurre algún error durante el cierre de la sesión.
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        System.out.println("Conexión cerrada: " + sessions.get(session) + ", Estado: " + status);
        sessions.remove(session); // Eliminar la sesión del mapa cuando se cierra la conexión.
    }

    /**
     * Indica si el manejador soporta mensajes parciales.
     * Los mensajes parciales permiten enviar mensajes grandes en fragmentos. En este caso, el soporte está deshabilitado.
     *
     * @return {@code false}, ya que los mensajes parciales no están soportados.
     */
    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    /**
     * Envía un mensaje de texto a una sesión específica identificada por la sesión WebSocket.
     * Si la sesión no está activa o la conexión está cerrada, no se enviará el mensaje.
     *
     * @param session La sesión WebSocket a la que se desea enviar el mensaje.
     * @param messageString El mensaje de texto que se desea enviar.
     */
    public void sendMessageToSession(WebSocketSession session, String messageString) {
        if (session != null && session.isOpen()) {
            try {
                session.sendMessage(new TextMessage(messageString));
                System.out.println("Mensaje enviado a la sesión " + sessions.get(session) + ": " + messageString);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("Sesión WebSocket inactiva o cerrada: " + sessions.get(session));
        }
    }

    /**
     * Envía un mensaje de texto a todas las sesiones activas.
     * Si no hay sesiones activas, no se enviará ningún mensaje.
     *
     * @param messageString El mensaje de texto que se desea enviar a todas las sesiones.
     */
    public void sendMessageToAll(String messageString) {
        for (WebSocketSession session : sessions.keySet()) {
            sendMessageToSession(session, messageString);
        }
    }


}
