package com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities;


import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.OccurenceConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.UnsupportedFeatureException;

/**
 * Interfaz que define los métodos necesarios para manejar eventos en una sesión OCPP.
 * Los métodos de esta interfaz permiten manejar confirmaciones, solicitudes entrantes,
 * errores y la apertura o cierre de conexiones.
 */
public interface SessionEvents {

    /**
     * Maneja una {@link Confirmation} en respuesta a una {@link Request} previamente enviada.
     *
     * @param uniqueId     el identificador único asociado con la solicitud.
     * @param confirmation la confirmación que responde a la solicitud.
     */
    void handleConfirmation(String uniqueId, Confirmation confirmation);

    /**
     * Maneja una solicitud {@link Request} entrante.
     *
     * @param request la solicitud entrante que necesita ser manejada.
     * @return una {@link Confirmation} como respuesta a la solicitud.
     * @throws UnsupportedFeatureException si la característica de la solicitud no es soportada.
     */
    Confirmation handleRequest(Request request) throws UnsupportedFeatureException;

    /**
     * Completa una solicitud pendiente {@link Request}.
     *
     * @param uniqueId     el identificador único asociado con la solicitud.
     * @param confirmation la confirmación correspondiente a la solicitud pendiente.
     * @return un booleano que indica si se encontró la solicitud pendiente.
     * @throws UnsupportedFeatureException si la característica de la solicitud no es soportada.
     * @throws OccurenceConstraintException si la solicitud no es válida según las reglas del protocolo.
     */
    boolean asyncCompleteRequest(String uniqueId, Confirmation confirmation)
            throws UnsupportedFeatureException, OccurenceConstraintException;

    /**
     * Maneja un error asociado a una {@link Request}.
     *
     * @param uniqueId        el identificador único de la solicitud.
     * @param errorCode       el código del error.
     * @param errorDescription la descripción detallada del error.
     * @param payload         información adicional sobre el error.
     */
    void handleError(String uniqueId, String errorCode, String errorDescription, Object payload);

    /** Maneja el evento de cierre de una conexión. */
    void handleConnectionClosed();

    /** Maneja el evento de apertura de una conexión. */
    void handleConnectionOpened();
}
