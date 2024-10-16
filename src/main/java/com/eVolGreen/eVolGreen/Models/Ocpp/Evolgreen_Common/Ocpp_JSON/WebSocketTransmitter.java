package com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Ocpp_JSON;

import java.io.IOException;
import java.net.ConnectException;
import java.net.Proxy;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.NotConnectedException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Ocpp_JSON.WSS.WssSocketBuilder;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.RadioEvents;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Transmitter;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.exceptions.WebsocketNotConnectedException;
import org.java_websocket.handshake.ServerHandshake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implementación de WebSocket del Transmitter.
 */
@SuppressWarnings("deprecation")
public class WebSocketTransmitter implements Transmitter {
    private static final Logger logger = LoggerFactory.getLogger(WebSocketTransmitter.class);

    public static final String WSS_SCHEME = "wss";
    private final Draft draft;

    private final JSONConfiguration configuration;
    private volatile boolean closed = true;
    private volatile WebSocketClient client;
    private WssSocketBuilder wssSocketBuilder;

    /**
     * Constructor que inicializa el transmisor con una configuración y un borrador específicos.
     *
     * @param configuration La configuración JSON a utilizar.
     * @param draft El borrador del protocolo WebSocket a utilizar.
     */
    public WebSocketTransmitter(JSONConfiguration configuration, Draft draft) {
        this.configuration = configuration;
        this.draft = draft;
    }

    /**
     * Constructor que inicializa el transmisor con un borrador específico y la configuración por defecto.
     *
     * @param draft El borrador del protocolo WebSocket a utilizar.
     */
    public WebSocketTransmitter(Draft draft) {
        this(JSONConfiguration.get(), draft);
    }

    /**
     * Conecta al transmisor a la URI especificada.
     *
     * @param uri La URI a la que conectarse.
     * @param events Los eventos de radio a manejar.
     */
    @Override
    public void connect(String uri, RadioEvents events) {
        final URI resource = URI.create(uri);

        Map<String, String> httpHeaders = new HashMap<>();
        String username = configuration.getParameter(JSONConfiguration.USERNAME_PARAMETER);
        Object password = configuration.getParameter(JSONConfiguration.PASSWORD_PARAMETER);
        byte[] credentials = null;
        if (username != null && password != null) {
            if (password instanceof String) {
                credentials = (username + ":" + password).getBytes(StandardCharsets.UTF_8);
            } else if (password instanceof byte[]) {
                credentials =
                        joinByteArrays((username + ":").getBytes(StandardCharsets.UTF_8), (byte[]) password);
            }
        }
        if (credentials != null) {
            byte[] base64Credentials = Base64.getEncoder().encode(credentials);
            httpHeaders.put("Authorization", "Basic " + new String(base64Credentials));
        }

        int connectTimeout =
                this.configuration.getParameter(JSONConfiguration.CONNECT_TIMEOUT_IN_MS_PARAMETER, 0);

        client =
                new WebSocketClient(resource, draft, httpHeaders, connectTimeout) {
                    @Override
                    public void onOpen(ServerHandshake serverHandshake) {
                        logger.debug("On connection open (HTTP status: {})", serverHandshake.getHttpStatus());
                        events.connected();
                    }

                    @Override
                    public void onMessage(String message) {
                        events.receivedMessage(message);
                    }

                    @Override
                    public void onClose(int code, String reason, boolean remote) {
                        logger.debug(
                                "On connection close (code: {}, reason: {}, remote: {})", code, reason, remote);

                        events.disconnected();
                    }

                    @Override
                    public void onError(Exception ex) {
                        if (ex instanceof ConnectException) {
                            logger.error("On error triggered caused by:", ex);
                        } else {
                            logger.error("On error triggered:", ex);
                        }
                    }
                };

        if (WSS_SCHEME.equals(resource.getScheme())) {

            if (wssSocketBuilder == null) {
                throw new IllegalStateException(
                        "wssSocketBuilder must be set to support " + WSS_SCHEME + " scheme");
            }

            try {
                client.setSocket(wssSocketBuilder.uri(resource).build());
            } catch (IOException ex) {
                logger.error("SSL socket creation failed", ex);
            }
        }

        configure();

        boolean isNonBlocking = isNonBlockingParameterSet();

        logger.debug("Trying to connect to: {}{}", resource, isNonBlocking ? "" : " [blocking]");

        if (isNonBlocking) {
            try {
                client.connect();
                closed = false;
            } catch (Exception ex) {
                logger.warn("client.connect() failed", ex);
            }
        } else {
            try {
                client.connectBlocking();
                closed = false;
            } catch (Exception ex) {
                logger.warn("client.connectBlocking() failed", ex);
            }
        }
    }

    /**
     * Une dos arrays de bytes.
     *
     * @param a Primer array de bytes.
     * @param b Segundo array de bytes.
     * @return Un nuevo array de bytes que es la concatenación de a y b.
     */
    byte[] joinByteArrays(byte[] a, byte[] b) {
        byte[] result = new byte[a.length + b.length];
        System.arraycopy(a, 0, result, 0, a.length);
        System.arraycopy(b, 0, result, a.length, b.length);
        return result;
    }

    /**
     * Configura el cliente WebSocket con los parámetros de la configuración.
     */
    public void configure() {
        if (client == null) {
            return;
        }
        client.setReuseAddr(configuration.getParameter(JSONConfiguration.REUSE_ADDR_PARAMETER, false));
        client.setTcpNoDelay(
                configuration.getParameter(JSONConfiguration.TCP_NO_DELAY_PARAMETER, false));
        client.setConnectionLostTimeout(
                configuration.getParameter(JSONConfiguration.PING_INTERVAL_PARAMETER, 60));
        client.setProxy(configuration.getParameter(JSONConfiguration.PROXY_PARAMETER, Proxy.NO_PROXY));
    }

    /**
     * Habilita el soporte WSS (WebSocket Secure).
     *
     * @param wssSocketBuilder El constructor de sockets WSS a utilizar.
     * @throws IllegalStateException Si el cliente ya está conectado.
     */
    public void enableWSS(WssSocketBuilder wssSocketBuilder) {
        if (client != null) {
            throw new IllegalStateException("Cannot enable WSS on already connected client");
        }
        this.wssSocketBuilder = wssSocketBuilder;
    }

    /**
     * Desconecta el transmisor.
     */
    @Override
    public void disconnect() {
        if (client == null) {
            return;
        }

        boolean isNonBlocking = isNonBlockingParameterSet();

        logger.debug("Disconnecting{}", isNonBlocking ? "" : " [blocking]");

        if (isNonBlocking) {
            try {
                client.close();
            } catch (Exception ex) {
                logger.info("client.close() failed", ex);
            } finally {
                client = null;
                closed = true;
            }
        } else {
            try {
                client.closeBlocking();
            } catch (Exception ex) {
                logger.info("client.closeBlocking() failed", ex);
            } finally {
                client = null;
                closed = true;
            }
        }
    }

    /**
     * Verifica si el parámetro de conexión no bloqueante está establecido.
     *
     * @return true si la conexión es no bloqueante, false en caso contrario.
     */
    private boolean isNonBlockingParameterSet() {
        Object rawParam = configuration.getParameter(JSONConfiguration.CONNECT_NON_BLOCKING_PARAMETER);
        return rawParam instanceof Boolean ? (Boolean) rawParam : false;
    }

    /**
     * Envía una solicitud a través del WebSocket.
     *
     * @param request La solicitud a enviar.
     * @throws NotConnectedException Si el transmisor no está conectado.
     */
    @Override
    public void send(Object request) throws NotConnectedException {
        if (client == null) {
            throw new NotConnectedException();
        }

        try {
            client.send(request.toString());
        } catch (WebsocketNotConnectedException ex) {
            throw new NotConnectedException();
        }
    }

    /**
     * Verifica si la conexión está cerrada.
     *
     * @return true si la conexión está cerrada, false en caso contrario.
     */
    public boolean isClosed() {
        return closed;
    }
}