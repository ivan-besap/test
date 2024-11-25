package com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Ocpp_JSON;

import org.java_websocket.WebSocketImpl;
import org.java_websocket.drafts.Draft;
import org.java_websocket.enums.CloseHandshakeType;
import org.java_websocket.enums.HandshakeState;
import org.java_websocket.exceptions.InvalidDataException;
import org.java_websocket.exceptions.InvalidHandshakeException;
import org.java_websocket.framing.Framedata;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.handshake.ClientHandshakeBuilder;
import org.java_websocket.handshake.HandshakeBuilder;
import org.java_websocket.handshake.Handshakedata;
import org.java_websocket.handshake.ServerHandshake;
import org.java_websocket.handshake.ServerHandshakeBuilder;
import org.java_websocket.util.Charsetfunctions;

import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;

/**
 * Implementación de un draft HTTP para verificar la salud del sistema a través de WebSocket.
 * Esta clase se utiliza para gestionar solicitudes HTTP que no son propiamente WebSocket y enviar una respuesta
 * de salud a través del puerto WebSocket.
 */
public class Draft_HttpHealthCheck extends Draft {

    public static final int HTTP_HEALTH_CHECK_CLOSE_CODE = 10200;

    /**
     * Verifica si la solicitud es una petición HTTP.
     *
     * @param handshakedata los datos del handshake del cliente.
     * @return true si es una solicitud HTTP, de lo contrario false.
     */
    public static Boolean isHttp(ClientHandshake handshakedata) {
        String upgradeField = handshakedata.getFieldValue("Upgrade");
        return upgradeField == null || upgradeField == "";
    }

    /**
     * Crea una respuesta de handshake HTTP, simulando un "OK" de estado de WebSocket.
     *
     * @param handshakedata los datos del handshake.
     * @param withcontent si la respuesta debe contener contenido.
     * @return una lista con el ByteBuffer que contiene la respuesta.
     */
    @Override
    public List<ByteBuffer> createHandshake(Handshakedata handshakedata, boolean withcontent) {
        byte[] content = Charsetfunctions.asciiBytes("<h1>OCPP-J Websocket OK</h1>");
        byte[] header = Charsetfunctions.asciiBytes(
                "HTTP/1.0 200 OK\r\n" +
                        "Mime-Version: 1.0\r\n" +
                        "Content-Type: text/html\r\n" +
                        "Content-Length: " + content.length + " \r\n" +
                        "Connection: close\r\n" +
                        "\r\n"
        );

        ByteBuffer bytebuffer = ByteBuffer.allocate(content.length + header.length);
        bytebuffer.put(header);
        bytebuffer.put(content);
        bytebuffer.flip();
        return Collections.singletonList(bytebuffer);
    }

    /**
     * Valida el handshake como cliente (no aplicable en este draft).
     *
     * @param request la solicitud de handshake del cliente.
     * @param response la respuesta del servidor.
     * @throws InvalidHandshakeException lanza una excepción porque este draft no puede ser usado en un cliente.
     */
    @Override
    public HandshakeState acceptHandshakeAsClient(
            ClientHandshake request, ServerHandshake response
    ) throws InvalidHandshakeException {
        throw new InvalidHandshakeException("This draft can't be used on a client");
    }

    /**
     * Valida el handshake como servidor, verificando si es una solicitud HTTP.
     *
     * @param handshakedata los datos del handshake del cliente.
     * @return el estado del handshake (MATCHED si es HTTP, NOT_MATCHED si no lo es).
     */
    @Override
    public HandshakeState acceptHandshakeAsServer(
            ClientHandshake handshakedata
    ) throws InvalidHandshakeException {
        return (isHttp(handshakedata)) ? HandshakeState.MATCHED : HandshakeState.NOT_MATCHED;
    }

    @Override
    public ByteBuffer createBinaryFrame(Framedata framedata) {
        return null;
    }

    @Override
    public List<Framedata> createFrames(ByteBuffer binary, boolean mask) {
        return null;
    }

    @Override
    public List<Framedata> createFrames(String text, boolean mask) {
        return null;
    }

    @Override
    public void processFrame(
            WebSocketImpl webSocketImpl, Framedata frame
    ) throws InvalidDataException {
        throw new InvalidDataException(0, "Este draft no puede ser usado en un cliente");
    }

    @Override
    public void reset() {
        // No se requiere acción
    }

    @Override
    public ClientHandshakeBuilder postProcessHandshakeRequestAsClient(
            ClientHandshakeBuilder request
    ) throws InvalidHandshakeException {
        throw new InvalidHandshakeException("Este draft no puede ser usado en un cliente");
    }

    @Override
    public HandshakeBuilder postProcessHandshakeResponseAsServer(
            ClientHandshake request, ServerHandshakeBuilder response
    ) throws InvalidHandshakeException {
        return response;
    }

    @Override
    public List<Framedata> translateFrame(ByteBuffer buffer) throws InvalidDataException {
        throw new InvalidHandshakeException("Este draft no funciona con frames");
    }

    @Override
    public CloseHandshakeType getCloseHandshakeType() {
        return CloseHandshakeType.NONE;
    }

    @Override
    public Draft copyInstance() {
        return this;
    }
}
