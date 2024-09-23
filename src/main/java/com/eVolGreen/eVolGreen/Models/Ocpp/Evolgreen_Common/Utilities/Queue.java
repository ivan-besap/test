package com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Utils.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Utils.Stopwatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Clase para almacenar y recuperar solicitudes ({@link Request}) basadas en un identificador único.
 * <p>
 * Proporciona una estructura que permite almacenar solicitudes de manera temporal y restaurarlas
 * usando una identificación única generada al momento del almacenamiento.
 * </p>
 */
public class Queue {

    private static final Logger logger = LoggerFactory.getLogger(Queue.class);

    /** Capacidad inicial de la cola de solicitudes. */
    public static final int REQUEST_QUEUE_INITIAL_CAPACITY = 1000;

    /** Mapa concurrente para almacenar solicitudes con su identificador único. */
    private final Map<String, Request> requestQueue;

    /** Constructor que inicializa la cola de solicitudes con la capacidad definida. */
    public Queue() {
        requestQueue = new ConcurrentHashMap<>(REQUEST_QUEUE_INITIAL_CAPACITY);
    }

    /**
     * Almacena una solicitud y devuelve un identificador único para recuperarla más tarde.
     * <p>
     * Este identificador puede usarse para restaurar la solicitud en una llamada futura.
     * </p>
     *
     * @param request La solicitud ({@link Request}) a almacenar.
     * @return Un identificador único usado para recuperar la solicitud más tarde.
     */
    public String store(Request request) {
        Stopwatch stopwatch = Stopwatch.createStarted();

        String ticket = UUID.randomUUID().toString();
        requestQueue.put(ticket, request);

        logger.debug("Tamaño de la cola: {}, tiempo de almacenamiento: {}", requestQueue.size(), stopwatch.stop());

        return ticket;
    }

    /**
     * Restaura una solicitud almacenada usando el identificador único.
     * <p>
     * El identificador solo puede usarse una vez, ya que la solicitud se elimina después de ser restaurada.
     * Si no se encuentra la solicitud, se devuelve un valor vacío.
     * </p>
     *
     * @param ticket Identificador único devuelto cuando la solicitud fue almacenada.
     * @return Un {@link Optional} con la solicitud almacenada o vacío si no se encuentra.
     */
    public Optional<Request> restoreRequest(String ticket) {
        Stopwatch stopwatch = Stopwatch.createStarted();

        try {
            Request request = requestQueue.get(ticket);
            requestQueue.remove(ticket);

            logger.debug("Tamaño de la cola: {}, tiempo de restauración: {}", requestQueue.size(), stopwatch.stop());

            return Optional.ofNullable(request);
        } catch (Exception ex) {
            logger.warn("Error al restaurar la solicitud con ticket {}", ticket, ex);
        }
        return Optional.empty();
    }

    /**
     * Devuelve una representación en cadena de la cola de solicitudes.
     *
     * @return Una cadena que representa el estado actual de la cola de solicitudes.
     */
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).add("requestQueue", requestQueue).toString();
    }
}
