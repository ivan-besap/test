package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function;

import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.DisplayManagement.Confirmation.NotifyDisplayMessagesResponse;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.DisplayManagement.Requests.NotifyDisplayMessagesRequest;

import java.util.UUID;

/**
 * Interfaz para el manejador de eventos del servidor relacionados con el bloque funcional
 * DisplayMessage.
 *
 * <p>Define el método que debe implementarse para manejar solicitudes del tipo
 * {@link NotifyDisplayMessagesRequest} provenientes de una estación de carga.</p>
 */
public interface ServerDisplayMessageEventHandler {

    /**
     * Maneja una solicitud {@link NotifyDisplayMessagesRequest} y devuelve una
     * {@link NotifyDisplayMessagesResponse}.
     *
     * @param sessionIndex Identificador de la sesión en la que se recibió la solicitud.
     * @param request Solicitud entrante {@link NotifyDisplayMessagesRequest} que debe ser procesada.
     * @return Respuesta {@link NotifyDisplayMessagesResponse} para enviar de vuelta al cliente.
     */
    NotifyDisplayMessagesResponse handleNotifyDisplayMessagesRequest(
            UUID sessionIndex, NotifyDisplayMessagesRequest request);
}
