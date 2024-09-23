package com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities;

import java.util.concurrent.CompletableFuture;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implementación simple de la interfaz {@link PromiseFulfiller}.
 * Esta clase se encarga de completar una promesa ({@link CompletableFuture})
 * con una confirmación ({@link Confirmation}) generada a partir de una solicitud ({@link Request}).
 */
public class SimplePromiseFulfiller implements PromiseFulfiller {

    private static final Logger logger = LoggerFactory.getLogger(SimplePromiseFulfiller.class);

    /**
     * Este método se encarga de cumplir una promesa procesando una solicitud ({@link Request})
     * a través del manejador de eventos de la sesión ({@link SessionEvents}).
     *
     * <p>Primero, el método procesa la solicitud para generar una confirmación. Si la confirmación
     * es nula, se asume que la ejecución es asíncrona y se completa posteriormente. Si se produce
     * una excepción durante el procesamiento, se captura y la promesa se completa con la excepción.
     *
     * @param promise        la promesa ({@link CompletableFuture}) que será completada con la confirmación ({@link Confirmation}).
     * @param eventHandler   el manejador de eventos de la sesión que procesa la solicitud y genera la confirmación.
     * @param request        la solicitud ({@link Request}) que será procesada y confirmada.
     */
    @Override
    public void fulfill(CompletableFuture<Confirmation> promise, SessionEvents eventHandler, Request request) {
        try {
            // Procesa la solicitud y genera una confirmación
            Confirmation confirmation = eventHandler.handleRequest(request);

            // Si la confirmación no es nula, completa la solicitud de manera asíncrona
            if (confirmation != null) {
                eventHandler.asyncCompleteRequest(request.getOcppMessageId(), confirmation);
            }
        } catch (Exception ex) {
            // Si ocurre un error, registra la excepción y completa la promesa de manera excepcional
            logger.warn("Error al procesar la solicitud en fulfill()", ex);
            promise.completeExceptionally(ex);
        }
    }
}
