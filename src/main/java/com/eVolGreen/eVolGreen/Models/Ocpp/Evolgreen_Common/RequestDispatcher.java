package com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;

import java.util.concurrent.CompletableFuture;

/**
 * Clase que implementa la lógica para despachar peticiones y manejar sus correspondientes respuestas.
 * <p>
 * Utiliza un {@link PromiseFulfiller} para procesar la petición y devolver una respuesta de confirmación,
 * manejando los eventos a través de {@link SessionEvents}.
 * </p>
 */
public class RequestDispatcher implements IRequestDispatcher {

    private final PromiseFulfiller fulfiller;
    protected SessionEvents eventHandler;

    /**
     * Constructor de la clase {@code RequestDispatcher}.
     *
     * @param fulfiller instancia de {@link PromiseFulfiller} para procesar las peticiones.
     */
    public RequestDispatcher(PromiseFulfiller fulfiller) {
        this.fulfiller = fulfiller;
    }

    /**
     * Maneja una petición y utiliza un {@link PromiseFulfiller} para cumplir con la respuesta.
     *
     * @param promise instancia de {@link CompletableFuture} que representa la confirmación a ser procesada.
     * @param request instancia de {@link Request} que representa la petición a ser manejada.
     */
    public void handleRequest(CompletableFuture<Confirmation> promise, Request request) {
        fulfiller.fulfill(promise, eventHandler, request);
    }

    /**
     * Asigna un manejador de eventos para gestionar las sesiones.
     *
     * @param eventHandler instancia de {@link SessionEvents} que manejará los eventos de la sesión.
     */
    public void setEventHandler(SessionEvents eventHandler) {
        this.eventHandler = eventHandler;
    }
}
