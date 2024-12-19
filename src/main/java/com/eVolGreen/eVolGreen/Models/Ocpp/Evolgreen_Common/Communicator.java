package com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common;

import java.util.ArrayDeque;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.NotConnectedException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.*;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Feature.Feature;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.SugarUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;

import javax.xml.soap.SOAPMessage;

/**
 * Clase abstracta que maneja la comunicación básica: empaqueta y envía mensajes, recibe y desempaqueta mensajes.
 *
 * <p>Requiere un {@link Transmitter} para enviar y recibir mensajes. Debe ser sobrecargada para implementar
 * un formato específico.
 */
public abstract class Communicator {
    protected static final Logger logger = LoggerFactory.getLogger(Communicator.class);

    private RetryRunner retryRunner;
    protected Radio radio;
    private ArrayDeque<Object> transactionQueue;
    private CommunicatorEvents events;
    private boolean failedFlag;

    // Constructor sin argumentos
    public Communicator() {
    }

    /**
     * Convierte una cadena formateada en un {@link Request}/{@link Confirmation}. Esto es útil para
     * resultados de llamadas, donde el tipo de confirmación no se proporciona.
     *
     * @param payload la carga útil formateada en bruto.
     * @param type el tipo de retorno esperado.
     * @return la carga útil desempaquetada.
     * @throws Exception si ocurre un error durante la conversión.
     */
    public abstract <T> T unpackPayload(Object payload, Class<T> type) throws Exception;

    /**
     * Convierte un {@link Request}/{@link Confirmation} en una cadena formateada.
     *
     * @param payload el modelo de carga útil.
     * @return la carga útil en forma de cadena formateada.
     */
    public abstract Object packPayload(Object payload);

    /**
     * Crea un sobre de resultado de llamada para transmitir.
     *
     * @param uniqueId el id que el receptor espera.
     * @param action nombre de la acción de la característica.
     * @param payload carga útil empaquetada.
     * @return un mensaje completamente empaquetado listo para enviar.
     */
    protected abstract Object makeCallResult(String uniqueId, String action, Object payload);

    /**
     * Crea un sobre de llamada para transmitir al servidor.
     *
     * @param uniqueId el id con el que el receptor debe responder.
     * @param action nombre de la acción de la característica.
     * @param payload carga útil empaquetada.
     * @return un mensaje completamente empaquetado listo para enviar.
     */
    protected abstract Object makeCall(String uniqueId, String action, Object payload);

    /**
     * Crea un sobre de error de llamada para transmitir.
     *
     * @param uniqueId el id que el receptor espera.
     * @param errorCode un código de error OCPP.
     * @param errorDescription una descripción de error asociada.
     * @return un mensaje completamente empaquetado listo para enviar.
     */
    protected abstract Object makeCallError(
            String uniqueId, String action, String errorCode, String errorDescription);

    public abstract boolean isClosed();

    /**
     * Identifica una llamada entrante y la analiza en uno de los siguientes:
     * {@link CallMessage} una solicitud.
     * {@link CallResultMessage} una respuesta.
     *
     * @param message el mensaje en bruto
     * @return CallMessage o {@link CallResultMessage}
     */
    public abstract Message parse(Object message);

    /**
     * Maneja las inyecciones requeridas.
     *
     * @param transmitter {@link Transmitter} inyectado
     */
    public Communicator(Radio transmitter) {
        this(transmitter, true);
    }

    /**
     * Maneja las inyecciones requeridas.
     *
     * @param transmitter {@link Transmitter} inyectado
     * @param enableTransactionQueue bandera para habilitar/deshabilitar la cola de transacciones y el procesamiento asociado
     */
    public Communicator(Radio transmitter, boolean enableTransactionQueue) {
        if (transmitter == null) {
            throw new IllegalArgumentException("Radio no puede ser null");
        }
        this.radio = (transmitter != null) ? transmitter : new DefaultRadioImplementation(); // Crea una instancia por defecto si es null
        this.transactionQueue = enableTransactionQueue ? new ArrayDeque<>() : null;
        this.retryRunner = enableTransactionQueue ? new RetryRunner() : null;
        this.failedFlag = false;
    }


    /**
     * Utiliza el {@link Transmitter} inyectado para conectarse al servidor.
     *
     * @param uri la url y puerto del servidor.
     * @param events manejador para eventos de devolución de llamada.
     */
    public void connect(String uri, CommunicatorEvents events) {
        this.events = events;
        if (radio instanceof Transmitter) ((Transmitter) radio).connect(uri, new EventHandler(events));
    }

    /**
     * Utiliza el {@link Transmitter} inyectado para aceptar un cliente.
     *
     * @param events manejador para eventos de devolución de llamada.
     */
    public void accept(CommunicatorEvents events) {
        this.events = events;
        if (radio instanceof Receiver) ((Receiver) radio).accept(new EventHandler(events));
    }

    /**
     * Envía una nueva {@link Request}. Almacena {@link Request}s relacionadas con transacciones si está fuera de línea.
     * Nuevas {@link Request}s relacionadas con transacciones se colocarán detrás de la cola de {@link Request}s almacenadas.
     *
     * @param uniqueId el id que el receptor debe usar para responder.
     * @param action nombre de la acción de la {@link Feature}.
     * @param request la {@link Request} saliente
     */
    public synchronized void sendCall(String uniqueId, String action, Request request) {
        Object call = makeCall(uniqueId, action, packPayload(request));

        if (this.radio == null) {
            throw new IllegalStateException("El radio no está inicializado");
        }

        if (call != null) {
            if (call instanceof SOAPMessage) {
                logger.trace("Send a message: {}", SugarUtil.soapMessageToString((SOAPMessage) call));
            } else {
                logger.trace("Send a message: {}", call);
            }
        }

        try {
            if (radio.isClosed()) {
                if (request.transactionRelated() && transactionQueue != null) {
                    logger.warn("Not connected: storing request to queue: {}", request);
                    transactionQueue.add(call);
                } else {
                    logger.warn("Not connected: can't send request: {}", request);
                    events.onError(
                            uniqueId,
                            "Not connected",
                            "The request can't be sent due to the lack of connection",
                            request);
                }
            } else if (request.transactionRelated()
                    && transactionQueue != null
                    && transactionQueue.size() > 0) {
                transactionQueue.add(call);
                processTransactionQueue();
            } else {
                ObjectMapper objectMapper = new ObjectMapper();
                String jsonMessage = objectMapper.writeValueAsString(call);
                logger.debug("Enviando mensaje JSON: {}", jsonMessage);

                radio.send(jsonMessage);
            }
        } catch (NotConnectedException ex) {
            logger.warn("sendCall() failed: not connected");
            if (request.transactionRelated() && transactionQueue != null) {
                transactionQueue.add(call);
            } else {
                events.onError(
                        uniqueId,
                        "Not connected",
                        "The request can't be sent due to the lack of connection",
                        request);
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Envía una respuesta {@link Confirmation} a una {@link Request}.
     *
     * @param uniqueId el id que el receptor espera.
     * @param confirmation la {@link Confirmation} saliente
     */
    public void sendCallResult(String uniqueId, String action, Confirmation confirmation) {
        try {
            radio.send(makeCallResult(uniqueId, action, packPayload(confirmation)));

            ConfirmationCompletedHandler completedHandler = confirmation.getCompletedHandler();

            if (completedHandler != null) {
                try {
                    completedHandler.onConfirmationCompleted();
                } catch (Throwable e) {
                    events.onError(
                            uniqueId,
                            "ConfirmationCompletedHandlerFailed",
                            "The confirmation completed callback handler failed with exception " + e.toString(),
                            confirmation);
                }
            }
        } catch (NotConnectedException ex) {
            logger.warn("sendCallResult() failed", ex);
            events.onError(
                    uniqueId,
                    "Not connected",
                    "The confirmation couldn't be send due to the lack of connection",
                    confirmation);
        }
    }

    /**
     * Envía un error. Si está fuera de línea, el mensaje se descarta.
     *
     * @param uniqueId el id al que el receptor espera una respuesta.
     * @param errorCode un código de error OCPP
     * @param errorDescription una descripción de error asociada.
     */
    public void sendCallError(
            String uniqueId, String action, String errorCode, String errorDescription) {
        logger.error(
                "An error occurred. Sending this information: uniqueId {}: action: {}, errorCore: {}, errorDescription: {}",
                uniqueId,
                action,
                errorCode,
                errorDescription);
        try {
            radio.send(makeCallError(uniqueId, action, errorCode, errorDescription));
        } catch (NotConnectedException ex) {
            logger.warn("sendCallError() failed", ex);
            events.onError(
                    uniqueId,
                    "Not connected",
                    "The error couldn't be send due to the lack of connection",
                    errorCode);
        }
    }

    /**
     * Cierra la conexión. Utiliza el {@link Transmitter}.
     */
    public void disconnect() {
        radio.disconnect();
    }

    /**
     * Procesa la cola de transacciones de manera sincronizada.
     */
    private synchronized void processTransactionQueue() {
        if (retryRunner != null && !retryRunner.isAlive()) {
            if (retryRunner.getState() != Thread.State.NEW) {
                retryRunner = new RetryRunner();
            }
            retryRunner.start();
        }
    }

    /**
     * Clase interna que maneja los eventos de radio.
     */
    private class EventHandler implements RadioEvents {
        private final CommunicatorEvents events;

        public EventHandler(CommunicatorEvents events) {
            this.events = events;
        }

        @Override
        public void connected() {
            events.onConnected();
            processTransactionQueue();
        }

        @Override
        public void receivedMessage(Object input) {
            Message message = parse(input);
            if (message != null) {
                Object payload = message.getPayload();
                if (payload instanceof Document) {
                    logger.trace("Receive a message: {}", SugarUtil.docToString((Document) payload));
                } else {
                    logger.trace("Receive a message: {}", message);
                }
            }
            if (message instanceof CallResultMessage) {
                events.onCallResult(message.getId(), message.getAction(), message.getPayload());
            } else if (message instanceof CallErrorMessage) {
                failedFlag = true;
                CallErrorMessage call = (CallErrorMessage) message;
                events.onError(
                        call.getId(), call.getErrorCode(), call.getErrorDescription(), call.getRawPayload());
            } else if (message instanceof CallMessage) {
                CallMessage call = (CallMessage) message;
                events.onCall(call.getId(), call.getAction(), call.getPayload());
            }
        }

        @Override
        public void disconnected() {
            events.onDisconnected();
        }
    }

    /**
     * Obtiene el mensaje de reintento en cola relacionado con la transacción.
     *
     * @return solicitud o null si la cola está vacía.
     */
    private Object getRetryMessage() {
        Object result = null;
        if (transactionQueue != null && !transactionQueue.isEmpty()) result = transactionQueue.peek();
        return result;
    }

    /**
     * Verifica si se recibió un mensaje de error.
     *
     * @return si se ha levantado una bandera de fallo.
     */
    private boolean hasFailed() {
        return failedFlag;
    }

    /**
     * Elimina el mensaje de reintento de la cola.
     */
    private void popRetryMessage() {
        if (transactionQueue != null && !transactionQueue.isEmpty()) transactionQueue.pop();
    }

    /**
     * Clase interna que reenvía solicitudes relacionadas con transacciones.
     */
    private class RetryRunner extends Thread {
        private static final long DELAY_IN_MILLISECONDS = 1000;

        @Override
        public void run() {
            Object call;
            try {
                while ((call = getRetryMessage()) != null) {
                    failedFlag = false;
                    radio.send(call);
                    Thread.sleep(DELAY_IN_MILLISECONDS);
                    if (!hasFailed()) popRetryMessage();
                }
            } catch (Exception ex) {
                logger.warn("RetryRunner::run() failed", ex);
            }
        }
    }
}