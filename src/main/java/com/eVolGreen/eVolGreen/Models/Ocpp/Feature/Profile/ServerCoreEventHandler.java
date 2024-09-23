package com.eVolGreen.eVolGreen.Models.Ocpp.Feature.Profile;

import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Confirmations.*;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Requests.*;

import java.util.UUID;

/**
 * La interfaz {@code ServerCoreEventHandler} define los métodos necesarios para manejar los eventos
 * principales del servidor dentro del protocolo OCPP. Estos eventos son solicitudes que envía el
 * punto de carga al backend y requieren una respuesta adecuada.
 */
public interface ServerCoreEventHandler {

    /**
     * Maneja una solicitud de autorización y devuelve una confirmación de autorización.
     *
     * @param sessionIndex el identificador único de la sesión.
     * @param request la solicitud de autorización {@link AuthorizeRequest}.
     * @return una confirmación de autorización {@link AuthorizeConfirmation}.
     */
    AuthorizeConfirmation handleAuthorizeRequest(UUID sessionIndex, AuthorizeRequest request);

    /**
     * Maneja una solicitud de notificación de inicio (boot) y devuelve una confirmación.
     *
     * @param sessionIndex el identificador único de la sesión.
     * @param request la solicitud de notificación de inicio {@link BootNotificationRequest}.
     * @return una confirmación de notificación de inicio {@link BootNotificationConfirmation}.
     */
    BootNotificationConfirmation handleBootNotificationRequest(UUID sessionIndex, BootNotificationRequest request);

    /**
     * Maneja una solicitud de transferencia de datos y devuelve una confirmación.
     *
     * @param sessionIndex el identificador único de la sesión.
     * @param request la solicitud de transferencia de datos {@link DataTransferRequest}.
     * @return una confirmación de transferencia de datos {@link DataTransferConfirmation}.
     */
    DataTransferConfirmation handleDataTransferRequest(UUID sessionIndex, DataTransferRequest request);

    /**
     * Maneja una solicitud de latido (heartbeat) y devuelve una confirmación de latido.
     *
     * @param sessionIndex el identificador único de la sesión.
     * @param request la solicitud de latido {@link HeartbeatRequest}.
     * @return una confirmación de latido {@link HeartbeatConfirmation}.
     */
    HeartbeatConfirmation handleHeartbeatRequest(UUID sessionIndex, HeartbeatRequest request);

    /**
     * Maneja una solicitud de valores del medidor y devuelve una confirmación de valores del medidor.
     *
     * @param sessionIndex el identificador único de la sesión.
     * @param request la solicitud de valores del medidor {@link MeterValuesRequest}.
     * @return una confirmación de valores del medidor {@link MeterValuesConfirmation}.
     */
    MeterValuesConfirmation handleMeterValuesRequest(UUID sessionIndex, MeterValuesRequest request);

    /**
     * Maneja una solicitud de inicio de transacción y devuelve una confirmación de inicio de transacción.
     *
     * @param sessionIndex el identificador único de la sesión.
     * @param request la solicitud de inicio de transacción {@link StartTransactionRequest}.
     * @return una confirmación de inicio de transacción {@link StartTransactionConfirmation}.
     */
    StartTransactionConfirmation handleStartTransactionRequest(UUID sessionIndex, StartTransactionRequest request);

    /**
     * Maneja una solicitud de notificación de estado y devuelve una confirmación de notificación de estado.
     *
     * @param sessionIndex el identificador único de la sesión.
     * @param request la solicitud de notificación de estado {@link StatusNotificationRequest}.
     * @return una confirmación de notificación de estado {@link StatusNotificationConfirmation}.
     */
    StatusNotificationConfirmation handleStatusNotificationRequest(UUID sessionIndex, StatusNotificationRequest request);

    /**
     * Maneja una solicitud de finalización de transacción y devuelve una confirmación de finalización de transacción.
     *
     * @param sessionIndex el identificador único de la sesión.
     * @param request la solicitud de finalización de transacción {@link StopTransactionRequest}.
     * @return una confirmación de finalización de transacción {@link StopTransactionConfirmation}.
     */
    StopTransactionConfirmation handleStopTransactionRequest(UUID sessionIndex, StopTransactionRequest request);
}
