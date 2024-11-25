package com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Ocpp_JSON;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.RadioEvents;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Receiver;

/**
 * Clase que implementa el comportamiento del receptor WebSocket para recibir y manejar
 * mensajes entrantes en la aplicación eVolGreen.
 *
 * Esta clase sirve como puente para manejar la desconexión y el envío de mensajes
 * a través de eventos de radio.
 */
public class WebSocketReceiver implements Receiver {

    private RadioEvents handler;
    private WebSocketReceiverEvents receiverEvents;

    /**
     * Constructor de la clase WebSocketReceiver.
     *
     * @param handler El manejador de eventos del receptor que maneja las operaciones
     *                de desconexión y envío de mensajes.
     */
    public WebSocketReceiver(WebSocketReceiverEvents handler) {
        this.receiverEvents = handler;
    }

    /**
     * Método encargado de desconectar el WebSocket. Notifica a los eventos de radio
     * sobre la desconexión y cierra la conexión.
     */
    @Override
    public void disconnect() {
        receiverEvents.close();
        handler.disconnected();
    }

    /**
     * Método encargado de relayar (reenviar) el mensaje recibido a través del
     * manejador de eventos.
     *
     * @param message El mensaje recibido en formato de String.
     */
    public void relay(String message) {
        handler.receivedMessage(message);
    }

    /**
     * Método para enviar un mensaje a través del WebSocket.
     *
     * @param message El mensaje que se enviará.
     */
    @Override
    public void send(Object message) {
        receiverEvents.relay(message.toString());
    }

    /**
     * Verifica si la conexión está cerrada.
     *
     * @return true si la conexión está cerrada, false en caso contrario.
     */
    @Override
    public boolean isClosed() {
        return receiverEvents.isClosed();
    }

    /**
     * Acepta un manejador de eventos de radio para manejar las operaciones
     * relacionadas con los eventos de comunicación del WebSocket.
     *
     * @param events El manejador de eventos de radio.
     */
    @Override
    public void accept(RadioEvents events) {
        this.handler = events;
    }
}
