package com.eVolGreen.eVolGreen.Models.Ocpp.Feature;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;

import java.util.UUID;

/**
 * Interfaz para definir una característica (Feature) en el sistema eVolGreen.
 * Esta interfaz vincula los tipos de {@link Request} y {@link Confirmation} con un nombre de acción específico.
 */
public interface Feature {

    /**
     * Maneja una solicitud.
     *
     * @param sessionIndex la fuente de la solicitud.
     * @param request la {@link Request} que se debe manejar.
     * @return la {@link Confirmation} que se enviará como respuesta.
     */
    Confirmation handleRequest(UUID sessionIndex, Request request);

    /**
     * Obtiene el tipo de la {@link Request} para la característica.
     *
     * @return el tipo de la {@link Request}.
     */
    Class<? extends Request> getRequestType();

    /**
     * Obtiene el tipo de la {@link Confirmation} para la característica.
     *
     * @return el tipo de la {@link Confirmation}.
     */
    Class<? extends Confirmation> getConfirmationType();

    /**
     * Obtiene el nombre de la acción asociada con la característica.
     *
     * @return el nombre de la acción.
     */
    String getAction();
}
