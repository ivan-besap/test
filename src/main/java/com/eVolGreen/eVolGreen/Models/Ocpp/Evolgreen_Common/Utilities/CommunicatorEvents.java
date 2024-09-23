package com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities;

/**
 * Interfaz para manejar los eventos que ocurren durante la comunicación con el servidor
 * en el contexto de OCPP. Permite gestionar resultados, errores y el estado de la conexión.
 */
public interface CommunicatorEvents {

    /**
     * Maneja el resultado de una llamada.
     *
     * <p>Utiliza el identificador para vincular el resultado con la solicitud original.
     * Se puede utilizar el método {@code unpackPayload} del {@code Communicator} para
     * deserializar el contenido.
     *
     * @param id identificador único de la solicitud original.
     * @param action la acción opcional que se ejecutó (puede ser nulo).
     * @param payload el contenido crudo de la respuesta.
     */
    void onCallResult(String id, String action, Object payload);

    /**
     * Maneja una solicitud entrante.
     *
     * <p>Utiliza el nombre de la acción para identificar la solicitud. Luego, puedes usar el método
     * {@code unpackPayload} del {@code Communicator} para deserializar el contenido.
     *
     * @param id identificador único que se usará para responder al servidor.
     * @param action el nombre de la acción que identifica la característica.
     * @param payload el contenido crudo de la solicitud.
     */
    void onCall(String id, String action, Object payload);

    /**
     * Maneja un error en una llamada.
     *
     * <p>Utiliza el identificador único para vincular el error con la solicitud original.
     * Puedes usar el método {@code unpackPayload} del {@code Communicator} para deserializar el
     * contenido adjunto al error.
     *
     * @param id identificador único de la solicitud original.
     * @param errorCode un código corto para categorizar el error.
     * @param errorDescription una descripción más larga del error.
     * @param payload el contenido crudo adjunto al error.
     */
    void onError(String id, String errorCode, String errorDescription, Object payload);

    /**
     * Maneja el evento cuando la conexión se ha desconectado.
     */
    void onDisconnected();

    /**
     * Maneja el evento cuando se establece una conexión.
     */
    void onConnected();
}
