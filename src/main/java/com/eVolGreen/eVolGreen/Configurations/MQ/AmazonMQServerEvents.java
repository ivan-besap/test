package com.eVolGreen.eVolGreen.Configurations.MQ;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.AuthenticationException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.UnsupportedFeatureException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.SessionInformation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Feature.Handler.ServerCoreEventHandler;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Core.Confirmations.*;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Core.Requests.*;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.RemoteTrigger.Request.TriggerMessageRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

/**
 * Implementación de {@link ServerCoreEventHandler} para manejar eventos del servidor y enviar
 * eventos de sesión y confirmaciones a Amazon MQ.
 */
public class AmazonMQServerEvents implements ServerCoreEventHandler {

    private static final Logger logger = LoggerFactory.getLogger(AmazonMQServerEvents.class);
    private final AmazonMQCommunicator amazonMQCommunicator;

    /**
     * Constructor que inicializa el manejador de eventos con el comunicador de Amazon MQ.
     *
     * @param amazonMQCommunicator el comunicador de Amazon MQ para enviar eventos.
     */
    public AmazonMQServerEvents(AmazonMQCommunicator amazonMQCommunicator) {
        this.amazonMQCommunicator = amazonMQCommunicator;
    }

    @Override
    public void authenticateSession(SessionInformation information, String username, String password) throws AuthenticationException {
        logger.info("Autenticando sesión: {}", information.getIdentifier());
        // Autenticar la sesión con credenciales de usuario y contraseña
        if (!"admin".equals(username) || !"password".equals(password)) {
            throw new AuthenticationException(100, "Credenciales incorrectas");
        }
    }

    @Override
    public void newSession(UUID sessionIndex, SessionInformation information) {
        logger.info("Nueva sesión iniciada: {}", sessionIndex);
//        amazonMQCommunicator.sendSessionEvent("Nueva sesión iniciada: " + sessionIndex);
    }

    @Override
    public void lostSession(UUID sessionIndex) {
        logger.warn("Sesión perdida: {}", sessionIndex);
//        amazonMQCommunicator.sendSessionEvent("Sesión perdida: " + sessionIndex);
    }

    @Override
    public void handleError(String uniqueId, String errorCode, String errorDescription, Object payload) {
        logger.error("Error en la solicitud: {} - {}", errorCode, errorDescription);
//        amazonMQCommunicator.sendErrorEvent("Error en la solicitud: " + errorCode + " - " + errorDescription);
    }

    @Override
    public void handleConfirmation(String uniqueId, Confirmation confirmation) {
        logger.info("Confirmación recibida: {}", confirmation);
//        amazonMQCommunicator.sendConfirmationEvent("Confirmación recibida: " + confirmation.toString());
    }

    @Override
    public Confirmation handleRequest(Request request) throws UnsupportedFeatureException {
        logger.debug("Solicitud recibida: {}", request);
        // Manejo de solicitudes personalizadas si es necesario
        throw new UnsupportedFeatureException("Solicitud no soportada: " + request.getClass().getName());
    }

    @Override
    public AuthorizeConfirmation handleAuthorizeRequest(UUID sessionIndex, AuthorizeRequest request) {
        logger.info("Manejando solicitud Authorize para sesión: {}", sessionIndex);
        // Implementación de la lógica para AuthorizeRequest
        return new AuthorizeConfirmation();
    }

    @Override
    public BootNotificationConfirmation handleBootNotificationRequest(UUID sessionIndex, BootNotificationRequest request) {
        logger.info("Manejando solicitud BootNotification para sesión: {}", sessionIndex);
        // Implementación de la lógica para BootNotificationRequest
        return new BootNotificationConfirmation();
    }

    @Override
    public DataTransferConfirmation handleDataTransferRequest(UUID sessionIndex, DataTransferRequest request) {
        logger.info("Manejando solicitud DataTransfer para sesión: {}", sessionIndex);
        // Implementación de la lógica para DataTransferRequest
        return new DataTransferConfirmation();
    }

    @Override
    public HeartbeatConfirmation handleHeartbeatRequest(UUID sessionIndex, HeartbeatRequest request) {
        logger.info("Manejando solicitud Heartbeat para sesión: {}", sessionIndex);
        // Implementación de la lógica para HeartbeatRequest
        return new HeartbeatConfirmation();
    }

    @Override
    public MeterValuesConfirmation handleMeterValuesRequest(UUID sessionIndex, MeterValuesRequest request) {
        logger.info("Manejando solicitud MeterValues para sesión: {}", sessionIndex);
        // Implementación de la lógica para MeterValuesRequest
        return new MeterValuesConfirmation();
    }

    @Override
    public StartTransactionConfirmation handleStartTransactionRequest(UUID sessionIndex, StartTransactionRequest request) {
        logger.info("Manejando solicitud StartTransaction para sesión: {}", sessionIndex);
        // Implementación de la lógica para StartTransactionRequest
        return new StartTransactionConfirmation();
    }

    @Override
    public StatusNotificationConfirmation handleStatusNotificationRequest(UUID sessionIndex, StatusNotificationRequest request) {
        logger.info("Manejando solicitud StatusNotification para sesión: {}", sessionIndex);
        // Implementación de la lógica para StatusNotificationRequest
        return new StatusNotificationConfirmation();
    }

    @Override
    public StopTransactionConfirmation handleStopTransactionRequest(UUID sessionIndex, StopTransactionRequest request) {
        logger.info("Manejando solicitud StopTransaction para sesión: {}", sessionIndex);
        // Implementación de la lógica para StopTransactionRequest
        return new StopTransactionConfirmation();
    }

    @Override
    public void handleRequest(TriggerMessageRequest request) {
        logger.info("Manejando solicitud TriggerMessage: {}", request);
        // Implementación para manejar TriggerMessageRequest
    }
}
