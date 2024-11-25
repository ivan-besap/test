package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function;

import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Confirmations.StatusNotificationResponse;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Requests.StatusNotificationRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Diagnostics.Confirmation.NotifyEventResponse;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Diagnostics.Requests.NotifyEventRequest;

import java.util.UUID;

/**
 * Interfaz que define los manejadores de eventos para el bloque funcional de disponibilidad en el servidor.
 *
 * <p>Esta interfaz permite que el servidor procese solicitudes relacionadas con eventos de disponibilidad
 * y notificaciones de estado provenientes de los clientes OCPP.</p>
 */
public interface ServerAvailabilityEventHandler {

    /**
     * Maneja una solicitud {@link NotifyEventRequest} recibida desde un cliente.
     *
     * <p>Este método se utiliza para procesar notificaciones de eventos enviadas por un cliente, que pueden
     * incluir información relacionada con el estado de los componentes o variables del sistema.</p>
     *
     * @param sessionIndex El identificador único de la sesión donde se recibió la solicitud.
     * @param request       La solicitud {@link NotifyEventRequest} que contiene los detalles del evento notificado.
     * @return Una instancia de {@link NotifyEventResponse} como respuesta a la solicitud procesada.
     */
    NotifyEventResponse handleNotifyEventRequest(UUID sessionIndex, NotifyEventRequest request);

    /**
     * Maneja una solicitud {@link StatusNotificationRequest} recibida desde un cliente.
     *
     * <p>Este método se utiliza para procesar notificaciones de estado enviadas por un cliente,
     * incluyendo el estado actual de los conectores o estaciones de carga.</p>
     *
     * @param sessionIndex El identificador único de la sesión donde se recibió la solicitud.
     * @param request       La solicitud {@link StatusNotificationRequest} que contiene los detalles del estado notificado.
     * @return Una instancia de {@link StatusNotificationResponse} como respuesta a la solicitud procesada.
     */
    StatusNotificationResponse handleStatusNotificationRequest(
            UUID sessionIndex, StatusNotificationRequest request);
}
