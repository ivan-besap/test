package com.eVolGreen.eVolGreen.Configurations.MQ;

import org.springframework.messaging.simp.stomp.StompSession;

import java.util.concurrent.ConcurrentHashMap;

/**
 * `SessionManager` administra las sesiones activas de clientes conectados a Amazon MQ.
 * Utiliza una estructura de datos concurrente para almacenar y gestionar las sesiones
 * de manera segura en un entorno multihilo.
 *
 * Esta clase proporciona métodos para agregar, eliminar, y recuperar sesiones de clientes,
 * permitiendo una administración centralizada y escalable de las conexiones STOMP.
 */
public class SessionManager {

    // Mapa concurrente que asocia los IDs de los clientes con sus sesiones STOMP
    private final ConcurrentHashMap<String, StompSession> sessions = new ConcurrentHashMap<>();

    /**
     * Agrega una nueva sesión para un cliente específico.
     *
     * @param clientId el identificador del cliente.
     * @param session la sesión STOMP del cliente.
     */
    public void addSession(String clientId, StompSession session) {
        sessions.put(clientId, session);
    }

    /**
     * Elimina la sesión de un cliente específico.
     *
     * @param clientId el identificador del cliente cuya sesión se eliminará.
     */
    public void removeSession(String clientId) {
        sessions.remove(clientId);
    }

    /**
     * Recupera la sesión de un cliente específico.
     *
     * @param clientId el identificador del cliente.
     * @return la sesión STOMP del cliente, o `null` si no existe una sesión activa.
     */
    public StompSession getSession(String clientId) {
        return sessions.get(clientId);
    }

    /**
     * Verifica si un cliente específico tiene una sesión activa.
     *
     * @param clientId el identificador del cliente.
     * @return `true` si el cliente tiene una sesión activa; `false` en caso contrario.
     */
    public boolean hasSession(String clientId) {
        return sessions.containsKey(clientId);
    }

    /**
     * Obtiene la cantidad total de sesiones activas.
     *
     * @return el número de sesiones activas actualmente.
     */
    public int getActiveSessionCount() {
        return sessions.size();
    }
}
