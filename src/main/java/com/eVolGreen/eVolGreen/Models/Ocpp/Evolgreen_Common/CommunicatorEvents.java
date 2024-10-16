package com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common;

/**
 * La interfaz {@code CommunicatorEvents} define los métodos para manejar eventos de comunicación.
 *
 * <p>Es implementada por clases que necesitan responder a eventos como:
 * - Conexiones exitosas y desconexiones.
 * - Recepción de mensajes y resultados de llamada.
 * - Errores de comunicación.
 * </p>
 * <p>
 * Implementación de ejemplo:
 * </p>
 *
 * <pre>
 * {@code
 * CommunicatorEvents events = new CommunicatorEvents() {
 *     @Override
 *     public void onConnected() {
 *         System.out.println("Conectado al servidor");
 *     }
 *     // Otros métodos...
 * };
 * }
 * </pre>
 */
public interface CommunicatorEvents {

    /**
     * Se llama cuando se establece una conexión exitosa con el servidor.
     */
    void onConnected();

    /**
     * Se llama cuando se pierde la conexión con el servidor.
     */
    void onDisconnected();

    /**
     * Se llama cuando ocurre un error en la comunicación.
     *
     * @param id el identificador único del mensaje relacionado con el error.
     * @param errorCode el código de error OCPP.
     * @param errorDescription la descripción del error.
     * @param payload el contenido del mensaje relacionado con el error.
     */
    void onError(String id, String errorCode, String errorDescription, Object payload);

    /**
     * Se llama cuando se recibe una nueva llamada desde el servidor.
     *
     * @param id el identificador único de la llamada.
     * @param action la acción que se solicita realizar.
     * @param payload el contenido del mensaje relacionado con la llamada.
     */
    void onCall(String id, String action, Object payload);

    /**
     * Se llama cuando se recibe el resultado de una llamada previamente realizada.
     *
     * @param id el identificador único de la llamada original.
     * @param action la acción realizada.
     * @param payload el contenido del mensaje con el resultado de la llamada.
     */
    void onCallResult(String id, String action, Object payload);
}
