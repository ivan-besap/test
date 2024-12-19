package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function;

import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.FirmwareManagement.Confirmation.FirmwareStatusNotificationResponse;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.FirmwareManagement.Confirmation.PublishFirmwareStatusNotificationResponse;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.FirmwareManagement.Requests.FirmwareStatusNotificationRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.FirmwareManagement.Requests.PublishFirmwareStatusNotificationRequest;

import java.util.UUID;

/**
 * Interface que define el manejador de eventos para el bloque funcional de gestión de firmware
 * en el servidor.
 *
 * <p>Este manejador procesa las solicitudes relacionadas con notificaciones de estado de firmware
 * y publicaciones de firmware recibidas desde estaciones de carga.</p>
 */
public interface ServerFirmwareManagementEventHandler {

    /**
     * Maneja una solicitud de notificación del estado del firmware.
     *
     * @param sessionIndex Identificador de la sesión en la que se recibió la solicitud.
     * @param request Solicitud entrante de tipo {@link FirmwareStatusNotificationRequest}.
     * @return Respuesta de tipo {@link FirmwareStatusNotificationResponse} para enviar al cliente.
     */
    FirmwareStatusNotificationResponse handleFirmwareStatusNotificationRequest(
            UUID sessionIndex, FirmwareStatusNotificationRequest request);

    /**
     * Maneja una solicitud de notificación del estado de publicación del firmware.
     *
     * @param sessionIndex Identificador de la sesión en la que se recibió la solicitud.
     * @param request Solicitud entrante de tipo {@link PublishFirmwareStatusNotificationRequest}.
     * @return Respuesta de tipo {@link PublishFirmwareStatusNotificationResponse} para enviar al cliente.
     */
    PublishFirmwareStatusNotificationResponse handlePublishFirmwareStatusNotificationRequest(
            UUID sessionIndex, PublishFirmwareStatusNotificationRequest request);
}
