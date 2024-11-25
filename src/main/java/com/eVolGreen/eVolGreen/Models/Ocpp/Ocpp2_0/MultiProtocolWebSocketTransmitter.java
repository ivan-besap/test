package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0;

import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import javax.net.SocketFactory;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.NotConnectedException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Ocpp_JSON.JSONConfiguration;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Ocpp_JSON.WSS.WssSocketBuilder;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.ProtocolVersion;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.RadioEvents;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Transmitter;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.exceptions.WebsocketNotConnectedException;
import org.java_websocket.handshake.ServerHandshake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implementación de WebSocket para transmitir mensajes usando múltiples versiones del protocolo
 * como OCPP 1.6 y OCPP 2.0.1.
 */
public class MultiProtocolWebSocketTransmitter implements Transmitter {

    // Constantes
    public static final String WSS_SCHEME = "wss";

    private static final Logger logger =
            LoggerFactory.getLogger(MultiProtocolWebSocketTransmitter.class);

    private final MultiProtocolFeatureRepository featureRepository;
    private final JSONConfiguration configuration;
    private final Draft draft;

    private volatile boolean closed = true;
    private volatile WebSocketClient client;
    private WssSocketBuilder wssSocketBuilder;

    /**
     * Constructor principal.
     *
     * @param featureRepository Repositorio de características para el protocolo.
     * @param configuration Configuración de JSON.
     * @param draft Implementación de WebSocket draft, como `Draft_6455`.
     */
    public MultiProtocolWebSocketTransmitter(
            MultiProtocolFeatureRepository featureRepository,
            JSONConfiguration configuration,
            Draft draft) {
        this.featureRepository = featureRepository;
        this.configuration = configuration;
        this.draft = draft;
    }

    /**
     * Conecta el transmisor a un URI específico.
     *
     * @param uri Dirección del servidor al que conectarse.
     * @param events Manejador de eventos de la conexión.
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
                        String protocol = client.getProtocol().toString();
                        logger.debug(
                                "On connection open (HTTP status: {}, protocol: {})",
                                serverHandshake.getHttpStatus(),
                                protocol);
                        ProtocolVersion protocolVersion = ProtocolVersion.fromSubProtocolName(protocol);
                        featureRepository.selectProtocolVersion(protocolVersion);
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
                        featureRepository.selectProtocolVersion(null);
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

            SocketFactory socketFactory =
                    new SocketFactory() {
                        public Socket createSocket() throws IOException {
                            return wssSocketBuilder.uri(resource).build();
                        }

                        public Socket createSocket(String host, int port) {
                            throw new UnsupportedOperationException();
                        }

                        public Socket createSocket(
                                String host, int port, InetAddress clientAddress, int clientPort) {
                            throw new UnsupportedOperationException();
                        }

                        public Socket createSocket(InetAddress address, int port) {
                            throw new UnsupportedOperationException();
                        }

                        public Socket createSocket(
                                InetAddress address, int port, InetAddress clientAddress, int clientPort) {
                            throw new UnsupportedOperationException();
                        }
                    };

            client.setSocketFactory(socketFactory);
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

    byte[] joinByteArrays(byte[] a, byte[] b) {
        byte[] result = new byte[a.length + b.length];
        System.arraycopy(a, 0, result, 0, a.length);
        System.arraycopy(b, 0, result, a.length, b.length);
        return result;
    }

    void configure() {
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

    void enableWSS(WssSocketBuilder wssSocketBuilder) {
        if (client != null) {
            throw new IllegalStateException("Cannot enable WSS on already connected client");
        }
        this.wssSocketBuilder = wssSocketBuilder;
    }

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

    private boolean isNonBlockingParameterSet() {
        Object rawParam = configuration.getParameter(JSONConfiguration.CONNECT_NON_BLOCKING_PARAMETER);
        return rawParam instanceof Boolean ? (Boolean) rawParam : false;
    }

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

    public boolean isClosed() {
        return closed;
    }

}
