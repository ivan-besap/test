package com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * La clase abstracta {@code RequestWithId} es la base para todas las solicitudes que deben
 * incluir un identificador de mensaje OCPP (Open Charge Point Protocol).
 * <p>
 * Esta clase proporciona los métodos necesarios para obtener y establecer el identificador
 * único de una solicitud dentro del contexto de OCPP. Al ser abstracta, requiere que las clases
 * que la extiendan implementen el comportamiento específico de las solicitudes.
 * </p>
 */
public abstract class RequestWithId implements Request {

    @JsonIgnore
    private String ocppMessageId;

    /**
     * Obtiene el identificador único del mensaje OCPP asociado a esta solicitud.
     *
     * @return el identificador del mensaje OCPP como cadena.
     */
    @Override
    public String getOcppMessageId() {
        return ocppMessageId;
    }

    /**
     * Establece el identificador único del mensaje OCPP para esta solicitud.
     *
     * @param requestId el identificador del mensaje OCPP a establecer.
     */
    @Override
    public void setOcppMessageId(String requestId) {
        this.ocppMessageId = requestId;
    }

    /**
     * Devuelve una representación en cadena de la solicitud, incluyendo el identificador
     * del mensaje OCPP.
     *
     * @return una cadena que representa el estado del objeto {@code RequestWithId}.
     */
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("ocppMessageId", ocppMessageId)
                .toString();
    }
}
