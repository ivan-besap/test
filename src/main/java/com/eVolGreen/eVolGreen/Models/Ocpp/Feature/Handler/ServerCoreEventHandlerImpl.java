package com.eVolGreen.eVolGreen.Models.Ocpp.Feature.Handler;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.AuthenticationException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.UnsupportedFeatureException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.SessionInformation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Confirmations.*;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Confirmations.Enums.AuthorizationStatus;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Confirmations.Enums.RegistrationStatus;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Confirmations.Utils.IdTagInfo;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Requests.*;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.RemoteTrigger.Request.TriggerMessageRequest;
import org.springframework.stereotype.Component;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * Implementación de {@link ServerCoreEventHandler}.
 * Esta clase maneja las solicitudes del punto de carga enviadas al backend y proporciona confirmaciones de respuesta.
 */
@Component
public class ServerCoreEventHandlerImpl implements ServerCoreEventHandler {

    @Override
    public void authenticateSession(SessionInformation information, String username, String password) throws AuthenticationException {

    }

    @Override
    public void newSession(UUID sessionIndex, SessionInformation information) {

    }

    @Override
    public void lostSession(UUID sessionIndex) {

    }

    @Override
    public void handleError(String uniqueId, String errorCode, String errorDescription, Object payload) {

    }

    @Override
    public void handleConfirmation(String uniqueId, Confirmation confirmation) {

    }

    @Override
    public Confirmation handleRequest(Request request) throws UnsupportedFeatureException {
        return null;
    }

    /**
     * Maneja una solicitud de autorización.
     *
     * @param sessionIndex el identificador único de la sesión.
     * @param request la solicitud de autorización {@link AuthorizeRequest}.
     * @return una confirmación de autorización {@link AuthorizeConfirmation} con el estado de autorización.
     */
    @Override
    public AuthorizeConfirmation handleAuthorizeRequest(UUID sessionIndex, AuthorizeRequest request) {
        AuthorizationStatus status = AuthorizationStatus.Accepted;
        ZonedDateTime expiryDate = ZonedDateTime.now(ZoneOffset.UTC).plusMonths(6); // Expiración en 6 meses
        String parentIdTag = "Parent-" + request.getIdTag(); // Un parentIdTag basado en el idTag del request

        IdTagInfo idTagInfo = new IdTagInfo(status, expiryDate);
        idTagInfo.setParentIdTag(parentIdTag);

        return new AuthorizeConfirmation(idTagInfo);
    }



    /**
     * Maneja una solicitud de notificación de inicio (boot).
     *
     * @param sessionIndex el identificador único de la sesión.
     * @param request la solicitud de notificación de inicio {@link BootNotificationRequest}.
     * @return una confirmación de notificación de inicio {@link BootNotificationConfirmation} con el estado de aceptación.
     */
    @Override
    public BootNotificationConfirmation handleBootNotificationRequest(UUID sessionIndex, BootNotificationRequest request) {
        // Define el estado usando el enum RegistrationStatus
        RegistrationStatus status = RegistrationStatus.Accepted;
        // Usa ZonedDateTime para la hora actual
        ZonedDateTime currentTime = ZonedDateTime.now(ZoneOffset.UTC);
        // Define el intervalo como un entero
        int interval = 300;

        // Crea la instancia de BootNotificationConfirmation con los tipos correctos
        return new BootNotificationConfirmation(currentTime, interval , status);
    }


    /**
     * Maneja una solicitud de transferencia de datos.
     *
     * @param sessionIndex el identificador único de la sesión.
     * @param request la solicitud de transferencia de datos {@link DataTransferRequest}.
     * @return una confirmación de transferencia de datos {@link DataTransferConfirmation} con el estado de aceptación.
     */
    @Override
    public DataTransferConfirmation handleDataTransferRequest(UUID sessionIndex, DataTransferRequest request) {
        return new DataTransferConfirmation();
    }

    /**
     * Maneja una solicitud de latido (heartbeat).
     *
     * @param sessionIndex el identificador único de la sesión.
     * @param request la solicitud de latido {@link HeartbeatRequest}.
     * @return una confirmación de latido {@link HeartbeatConfirmation} con el estado de OK.
     */
    @Override
    public HeartbeatConfirmation handleHeartbeatRequest(UUID sessionIndex, HeartbeatRequest request) {
        return new HeartbeatConfirmation();
    }

    /**
     * Maneja una solicitud de valores del medidor.
     *
     * @param sessionIndex el identificador único de la sesión.
     * @param request la solicitud de valores del medidor {@link MeterValuesRequest}.
     * @return una confirmación de valores del medidor {@link MeterValuesConfirmation} con el estado de aceptación.
     */
    @Override
    public MeterValuesConfirmation handleMeterValuesRequest(UUID sessionIndex, MeterValuesRequest request) {
        return new MeterValuesConfirmation();
    }

    /**
     * Maneja una solicitud de inicio de transacción.
     *
     * @param sessionIndex el identificador único de la sesión.
     * @param request la solicitud de inicio de transacción {@link StartTransactionRequest}.
     * @return una confirmación de inicio de transacción {@link StartTransactionConfirmation} con el estado de aceptación.
     */
    @Override
    public StartTransactionConfirmation handleStartTransactionRequest(UUID sessionIndex, StartTransactionRequest request) {
        return new StartTransactionConfirmation();
    }

    /**
     * Maneja una solicitud de notificación de estado.
     *
     * @param sessionIndex el identificador único de la sesión.
     * @param request la solicitud de notificación de estado {@link StatusNotificationRequest}.
     * @return una confirmación de notificación de estado {@link StatusNotificationConfirmation} con el estado de OK.
     */
    @Override
    public StatusNotificationConfirmation handleStatusNotificationRequest(UUID sessionIndex, StatusNotificationRequest request) {
        return new StatusNotificationConfirmation();
    }

    /**
     * Maneja una solicitud de finalización de transacción.
     *
     * @param sessionIndex el identificador único de la sesión.
     * @param request la solicitud de finalización de transacción {@link StopTransactionRequest}.
     * @return una confirmación de finalización de transacción {@link StopTransactionConfirmation} con el estado de aceptación.
     */
    @Override
    public StopTransactionConfirmation handleStopTransactionRequest(UUID sessionIndex, StopTransactionRequest request) {
        return new StopTransactionConfirmation();
    }

    @Override
    public void handleRequest(TriggerMessageRequest request) {

    }
}
