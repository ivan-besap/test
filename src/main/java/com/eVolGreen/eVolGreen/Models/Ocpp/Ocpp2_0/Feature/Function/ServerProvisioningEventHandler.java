package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function;

import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Confirmations.BootNotificationResponse;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Confirmations.HeartbeatResponse;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Requests.BootNotificationRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Requests.HeartbeatRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Diagnostics.Confirmation.NotifyReportResponse;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Diagnostics.Requests.NotifyReportRequest;

import java.util.UUID;

/**
 * Interfaz para manejar eventos del bloque funcional de aprovisionamiento (Provisioning) en el
 * servidor.
 * <p>
 * Define los métodos de callback para procesar las solicitudes relacionadas con el
 * aprovisionamiento, como notificaciones de inicio, latidos (heartbeats) y reportes.
 */
public interface ServerProvisioningEventHandler {

    /**
     * Maneja una solicitud de notificación de inicio (BootNotificationRequest) y devuelve una
     * respuesta (BootNotificationResponse).
     *
     * @param sessionIndex Identificador único de la sesión en la que se recibió la solicitud.
     * @param request Solicitud entrante de tipo {@link BootNotificationRequest}.
     * @return Respuesta de tipo {@link BootNotificationResponse} a la solicitud procesada.
     */
    BootNotificationResponse handleBootNotificationRequest(
            UUID sessionIndex, BootNotificationRequest request);

    /**
     * Maneja una solicitud de latido (HeartbeatRequest) y devuelve una respuesta (HeartbeatResponse).
     *
     * @param sessionIndex Identificador único de la sesión en la que se recibió la solicitud.
     * @param request Solicitud entrante de tipo {@link HeartbeatRequest}.
     * @return Respuesta de tipo {@link HeartbeatResponse} a la solicitud procesada.
     */
    HeartbeatResponse handleHeartbeatRequest(UUID sessionIndex, HeartbeatRequest request);

    /**
     * Maneja una solicitud de notificación de reporte (NotifyReportRequest) y devuelve una
     * respuesta (NotifyReportResponse).
     *
     * @param sessionIndex Identificador único de la sesión en la que se recibió la solicitud.
     * @param request Solicitud entrante de tipo {@link NotifyReportRequest}.
     * @return Respuesta de tipo {@link NotifyReportResponse} a la solicitud procesada.
     */
    NotifyReportResponse handleNotifyReportRequest(UUID sessionIndex, NotifyReportRequest request);
}
