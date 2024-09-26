package com.eVolGreen.eVolGreen.Models.Ocpp;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.OccurenceConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.UnsupportedFeatureException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.IFeatureRepository;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.ISession;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.SessionEvents;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Confirmations.BootNotificationConfirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Confirmations.Enums.RegistrationStatus;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Utils.TimeoutTimer;

import java.util.UUID;

/**
 * Decorador de sesión con capacidad de manejar tiempos de espera (timeout).
 *
 * Esta clase extiende la funcionalidad de una sesión (ISession) añadiendo
 * manejo de temporizadores, reiniciándolos y gestionando el cierre de conexiones
 * basado en tiempos de espera predefinidos.
 */
public class TimeoutSessionDecorator implements ISession {

    private TimeoutTimer timeoutTimer;
    private final ISession session;

    /**
     * Constructor del decorador de sesión.
     *
     * @param timeoutTimer el temporizador de timeout a utilizar.
     * @param session la sesión original que será decorada con capacidades de timeout.
     */
    public TimeoutSessionDecorator(TimeoutTimer timeoutTimer, ISession session) {
        this.timeoutTimer = timeoutTimer;
        this.session = session;
    }

    /**
     * Reinicia el temporizador basado en un intervalo de tiempo dado en segundos.
     *
     * @param timeoutInSec el tiempo en segundos para reiniciar el temporizador.
     */
    private void resetTimer(int timeoutInSec) {
        if (timeoutTimer != null) {
            timeoutTimer.setTimeout(timeoutInSec * 1000);
        }
        resetTimer();
    }

    /**
     * Reinicia el temporizador al valor predeterminado.
     */
    private void resetTimer() {
        if (timeoutTimer != null) {
            timeoutTimer.reset();
        }
    }

    /**
     * Detiene el temporizador de timeout.
     */
    private void stopTimer() {
        if (timeoutTimer != null) {
            timeoutTimer.end();
        }
    }

    /**
     * Inicia el temporizador de timeout.
     */
    private void startTimer() {
        if (timeoutTimer != null) {
            timeoutTimer.begin();
        }
    }

    // Métodos de ISession delegados a la sesión original

    @Override
    public IFeatureRepository getFeatureRepository() {
        return session.getFeatureRepository();
    }

    @Override
    public UUID getSessionId() {
        return session.getSessionId();
    }

    @Override
    public void open(String uri, SessionEvents eventHandler) {
        SessionEvents events = createEventHandler(eventHandler);
        this.session.open(uri, events);
    }

    @Override
    public void accept(SessionEvents eventHandler) {
        SessionEvents events = createEventHandler(eventHandler);
        this.session.accept(events);
    }

    @Override
    public String storeRequest(Request payload) {
        return this.session.storeRequest(payload);
    }

    @Override
    public void sendRequest(String action, Request payload, String uuid) {
        this.session.sendRequest(action, payload, uuid);
    }

    @Override
    public boolean completePendingPromise(String id, Confirmation confirmation) throws UnsupportedFeatureException, OccurenceConstraintException {
        return this.session.completePendingPromise(id, confirmation);
    }

    @Override
    public void close() {
        this.session.close();
    }

    /**
     * Crea un manejador de eventos (SessionEvents) que extiende el comportamiento de los eventos
     * originales con la capacidad de reiniciar y gestionar el temporizador de timeout.
     *
     * @param eventHandler el manejador de eventos original.
     * @return un nuevo manejador de eventos decorado.
     */
    private SessionEvents createEventHandler(SessionEvents eventHandler) {
        return new SessionEvents() {

            @Override
            public void handleConfirmation(String uniqueId, Confirmation confirmation) {
                resetTimer();
                eventHandler.handleConfirmation(uniqueId, confirmation);
            }

            @Override
            public synchronized Confirmation handleRequest(Request request) throws UnsupportedFeatureException {
                resetTimer();
                Confirmation confirmation = eventHandler.handleRequest(request);

                // Si se recibe una confirmación de BootNotification, se ajusta el intervalo de timeout.
                if (confirmation instanceof BootNotificationConfirmation) {
                    BootNotificationConfirmation bootNotification = (BootNotificationConfirmation) confirmation;
                    if (bootNotification.getStatus() == RegistrationStatus.Accepted) {
                        resetTimer(bootNotification.getInterval());
                    }
                }
                return confirmation;
            }

            @Override
            public boolean asyncCompleteRequest(String uniqueId, Confirmation confirmation) throws UnsupportedFeatureException, OccurenceConstraintException {
                return eventHandler.asyncCompleteRequest(uniqueId, confirmation);
            }

            @Override
            public void handleError(String uniqueId, String errorCode, String errorDescription, Object payload) {
                eventHandler.handleError(uniqueId, errorCode, errorDescription, payload);
            }

            @Override
            public void handleConnectionClosed() {
                eventHandler.handleConnectionClosed();
                stopTimer();
            }

            @Override
            public void handleConnectionOpened() {
                eventHandler.handleConnectionOpened();
                startTimer();
            }
        };
    }
}
