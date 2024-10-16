package com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.OccurenceConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.UnsupportedFeatureException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.SessionInformation;

import java.util.UUID;

/**
 * Interfaz para manejar eventos de la sesión.
 */
public interface SessionEvents {
    void handleConfirmation(String uniqueId, Confirmation confirmation);

    Confirmation handleRequest(Request request) throws UnsupportedFeatureException;

    boolean asyncCompleteRequest(String uniqueId, Confirmation confirmation) throws UnsupportedFeatureException, OccurenceConstraintException;

    void handleError(String uniqueId, String errorCode, String errorDescription, Object payload);

    void handleConnectionOpened();

    void handleConnectionClosed();

    void onError(String ocppMessageId, String internalError, String errorProcessingRequest, Request request);

    void newSession(UUID sessionIndex, SessionInformation information);

    void lostSession(UUID sessionIndex);
}
