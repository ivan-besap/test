package com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common;

//import com.evolgreen.ocpp.model.Confirmation;
//import com.evolgreen.ocpp.model.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.OccurenceConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.UnsupportedFeatureException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;

import java.util.UUID;

/**
 * La interfaz ISession define el contrato para gestionar una sesión de comunicación con un servidor OCPP
 * en nuestro CMS eVolGreen.
 * <p>
 * Se encarga de manejar la apertura, el almacenamiento de solicitudes, el envío y la recepción
 * de respuestas, así como el cierre de la sesión.
 * </p>
 */
public interface ISession {

    /**
     * Obtiene el repositorio de características soportadas por la sesión.
     *
     * @return El repositorio de características.
     */
    IFeatureRepository getFeatureRepository();

    /**
     * Obtiene el identificador único de la sesión.
     *
     * @return UUID de la sesión.
     */
    UUID getSessionId();

    /**
     * Abre la sesión y establece una conexión con el servidor en la URI proporcionada.
     *
     * @param uri La URI del servidor al que conectarse.
     * @param eventHandler Manejador de eventos de la sesión.
     */
    void open(String uri, SessionEvents eventHandler);

    /**
     * Acepta una sesión entrante, permitiendo al servidor iniciar una conexión.
     *
     * @param eventHandler Manejador de eventos de la sesión.
     */
    void accept(SessionEvents eventHandler);

    /**
     * Almacena una solicitud enviada para rastrear su respuesta pendiente.
     *
     * @param payload La solicitud a almacenar.
     * @return Un identificador único para la solicitud almacenada.
     */
    String storeRequest(Request payload);

    /**
     * Envía una solicitud al servidor.
     *
     * @param action La acción que representa la solicitud.
     * @param payload El contenido de la solicitud.
     * @param uuid El identificador único para esta solicitud.
     */
    void sendRequest(String action, Request payload, String uuid);

    /**
     * Completa de manera asincrónica una solicitud pendiente cuando se recibe una confirmación.
     *
     * @param id El identificador de la solicitud pendiente.
     * @param confirmation La confirmación recibida para la solicitud.
     * @return true si la solicitud se completa exitosamente.
     * @throws UnsupportedFeatureException Si la confirmación corresponde a una característica no soportada.
     * @throws OccurenceConstraintException Si la confirmación no es válida.
     */
    boolean completePendingPromise(String id, Confirmation confirmation)
            throws UnsupportedFeatureException, OccurenceConstraintException;

    /**
     * Cierra la sesión y desconecta del servidor.
     */
    void close();

    void registerSession(SessionEvents eventHandler);


}
