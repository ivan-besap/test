package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function;

import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Confirmations.ChangeAvailabilityResponse;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Requests.ChangeAvailabilityRequest;

/**
 * Interfaz para manejar eventos relacionados con la disponibilidad en el cliente.
 *
 * <p>`ClientAvailabilityEventHandler` define un contrato para procesar solicitudes de cambio de
 * disponibilidad de estaciones de carga en el cliente OCPP.
 */
public interface ClientAvailabilityEventHandler {

    /**
     * Maneja una solicitud de cambio de disponibilidad ({@link ChangeAvailabilityRequest}) y devuelve
     * una respuesta ({@link ChangeAvailabilityResponse}).
     *
     * <p>Este método permite procesar solicitudes entrantes para cambiar el estado de disponibilidad
     * de una estación de carga o un conector específico.
     *
     * @param request La solicitud entrante de tipo {@link ChangeAvailabilityRequest}, que incluye
     *                detalles sobre el estado deseado de disponibilidad.
     * @return Una instancia de {@link ChangeAvailabilityResponse} que contiene el resultado del
     *         cambio solicitado, como "Accepted" o "Rejected".
     */
    ChangeAvailabilityResponse handleChangeAvailabilityRequest(ChangeAvailabilityRequest request);
}
