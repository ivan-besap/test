package com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Ocpp_JSON;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.AuthenticationException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.ISessionFactory;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Listener;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.ListenerEvents;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.SessionInformation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Ocpp_JSON.WSS.WssFactoryBuilder;

import org.java_websocket.WebSocket;
import org.java_websocket.drafts.Draft;
import org.java_websocket.exceptions.InvalidDataException;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.handshake.ServerHandshakeBuilder;
import org.java_websocket.server.WebSocketServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Clase encargada de manejar conexiones WebSocket para la aplicación eVolGreen,
 * facilitando la creación de sesiones y control de la autenticación de los clientes.
 */
public class WebSocketListener implements Listener {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketListener.class);

    private static final int DEFAULT_WEBSOCKET_WORKER_COUNT = 4;
    private static final int TIMEOUT_IN_MILLIS = 10000;

    private static final int EVOLGREEN_CP_MIN_PASSWORD_LENGTH = 16;
    private static final int EVOLGREEN_CP_MAX_PASSWORD_LENGTH = 40;

    private static final String HTTP_HEADER_PROXIED_ADDRESS = "X-Forwarded-For";

    private final ISessionFactory sessionFactory;
    private final List<Draft> drafts;
    private final JSONConfiguration configuration;
    private volatile WebSocketServer server;
    private WssFactoryBuilder wssFactoryBuilder;
    private final Map<WebSocket, WebSocketReceiver> sockets = new ConcurrentHashMap<>();
    private volatile boolean closed = true;
    private boolean handleRequestAsync;

    /**
     * Constructor de la clase WebSocketListener que inicializa las sesiones del WebSocket.
     *
     * @param sessionFactory Fábrica de sesiones utilizada para crear nuevas sesiones.
     * @param configuration  Configuración de la red.
     * @param drafts         Versiones del protocolo WebSocket utilizadas.
     */
    public WebSocketListener(ISessionFactory sessionFactory, JSONConfiguration configuration, Draft... drafts) {
        this.sessionFactory = sessionFactory;
        this.configuration = configuration;
        this.drafts = Arrays.asList(drafts);
    }

    /**
     * Constructor alternativo que utiliza la configuración predeterminada.
     *
     * @param sessionFactory Fábrica de sesiones utilizada para crear nuevas sesiones.
     * @param drafts         Versiones del protocolo WebSocket utilizadas.
     */
    public WebSocketListener(ISessionFactory sessionFactory, Draft... drafts) {
        this(sessionFactory, JSONConfiguration.get(), drafts);
    }

    /**
     * Abre el servidor WebSocket y comienza a escuchar en un puerto determinado.
     *
     * @param hostname Dirección de host donde el servidor escuchará.
     * @param port     Puerto donde el servidor escuchará.
     * @param handler  Manejador de eventos del servidor.
     */
    public void open(String hostname, int port, ListenerEvents handler) {
        server = new WebSocketServer(new InetSocketAddress(hostname, port), configuration.getParameter(JSONConfiguration.WEBSOCKET_WORKER_COUNT, DEFAULT_WEBSOCKET_WORKER_COUNT), drafts) {
            @Override
            public void onOpen(WebSocket webSocket, ClientHandshake clientHandshake) {
                if (Draft_HttpHealthCheck.isHttp(clientHandshake)) {
                    logger.debug("Solicitud HTTP recibida, realizando verificación de salud");
                    webSocket.close(Draft_HttpHealthCheck.HTTP_HEALTH_CHECK_CLOSE_CODE);
                    return;
                }
                logger.debug("Conexión abierta (descriptor: {})", clientHandshake.getResourceDescriptor());

                WebSocketReceiver receiver = new WebSocketReceiver(new WebSocketReceiverEvents() {
                    @Override
                    public boolean isClosed() {
                        return closed;
                    }

                    @Override
                    public void close() {
                        webSocket.close();
                    }

                    @Override
                    public void relay(String message) {
                        webSocket.send(message);
                    }
                });

                sockets.put(webSocket, receiver);

                String proxiedAddress = clientHandshake.getFieldValue(HTTP_HEADER_PROXIED_ADDRESS);
                logger.debug("Conexión abierta desde: {} (direccion proxy: {})", webSocket.getRemoteSocketAddress(), proxiedAddress);

                SessionInformation information = new SessionInformation.Builder()
                        .Identifier(clientHandshake.getResourceDescriptor())
                        .InternetAddress(webSocket.getRemoteSocketAddress())
                        .ProxiedAddress(proxiedAddress)
                        .build();

                handler.newSession(sessionFactory.createSession(new JSONCommunicator(receiver)), information);
            }

            @Override
            public ServerHandshakeBuilder onWebsocketHandshakeReceivedAsServer(WebSocket webSocket, Draft draft, ClientHandshake clientHandshake) throws InvalidDataException {
                SessionInformation information = new SessionInformation.Builder()
                        .Identifier(clientHandshake.getResourceDescriptor())
                        .InternetAddress(webSocket.getRemoteSocketAddress())
                        .build();

                String username = null;
                String password = null;
                if (clientHandshake.hasFieldValue("Authorization")) {
                    String authorization = clientHandshake.getFieldValue("Authorization");
                    if (authorization != null && authorization.toLowerCase().startsWith("basic")) {
                        String base64Credentials = authorization.substring("Basic".length()).trim();
                        byte[] credDecoded = Base64.getDecoder().decode(base64Credentials);

                        for (int i = 0; i < credDecoded.length; i++) {
                            if (credDecoded[i] == ':') {
                                username = new String(Arrays.copyOfRange(credDecoded, 0, i), StandardCharsets.UTF_8);
                                if (i + 1 < credDecoded.length) {
                                    password = new String(Arrays.copyOfRange(credDecoded, i + 1, credDecoded.length));
                                }
                                break;
                            }
                        }
                    }

                    if (password == null || password.length() < configuration.getParameter(JSONConfiguration.EVOLGREEN_CP_MIN_PASSWORD_LENGTH, EVOLGREEN_CP_MIN_PASSWORD_LENGTH)
                            || password.length() > configuration.getParameter(JSONConfiguration.EVOLGREEN_CP_MAX_PASSWORD_LENGTH, EVOLGREEN_CP_MAX_PASSWORD_LENGTH)) {
                        throw new InvalidDataException(401, "La longitud de la contraseña no es válida");
                    }
                }

                try {
                    handler.authenticateSession(information, username, password);
                } catch (AuthenticationException e) {
                    throw new InvalidDataException(e.getErrorCode(), e.getMessage());
                } catch (Exception e) {
                    throw new InvalidDataException(401, e.getMessage());
                }

                return super.onWebsocketHandshakeReceivedAsServer(webSocket, draft, clientHandshake);
            }

            @Override
            public void onClose(WebSocket webSocket, int code, String reason, boolean remote) {
                logger.debug("Conexión cerrada (descriptor: {}, código: {}, motivo: {}, remoto: {})", webSocket.getResourceDescriptor(), code, reason, remote);

                if (code == Draft_HttpHealthCheck.HTTP_HEALTH_CHECK_CLOSE_CODE) return;

                WebSocketReceiver receiver = sockets.get(webSocket);
                if (receiver != null) {
                    receiver.disconnect();
                    sockets.remove(webSocket);
                } else {
                    logger.debug("Receptor no encontrado para el socket: {}", webSocket);
                }
            }

            @Override
            public void onMessage(WebSocket webSocket, String message) {
                sockets.get(webSocket).relay(message);
            }

            @Override
            public void onError(WebSocket webSocket, Exception ex) {
                String resourceDescriptor = (webSocket != null) ? webSocket.getResourceDescriptor() : "no definido (webSocket es nulo)";
                if (ex instanceof ConnectException) {
                    logger.error("Error en la conexión (descriptor: " + resourceDescriptor + ")", ex);
                } else {
                    logger.error("Error detectado (descriptor: " + resourceDescriptor + ")", ex);
                }
            }

            @Override
            public void onStart() {
                logger.debug("Servidor WebSocket iniciado");
            }
        };

        if (wssFactoryBuilder != null) {
            server.setWebSocketFactory(wssFactoryBuilder.build());
        }

        configure();
        server.start();
        closed = false;
    }

    /**
     * Configura las opciones del servidor WebSocket como reutilización de direcciones y el intervalo de ping.
     */
    void configure() {
        server.setReuseAddr(configuration.getParameter(JSONConfiguration.REUSE_ADDR_PARAMETER, true));
        server.setTcpNoDelay(configuration.getParameter(JSONConfiguration.TCP_NO_DELAY_PARAMETER, false));
        server.setConnectionLostTimeout(configuration.getParameter(JSONConfiguration.PING_INTERVAL_PARAMETER, 60));
    }

    /**
     * Habilita WSS (WebSocket Secure) utilizando un builder de fábrica de WSS.
     *
     * @param wssFactoryBuilder Fábrica para construir la configuración WSS.
     */
    public void enableWSS(WssFactoryBuilder wssFactoryBuilder) {
        if (server != null) {
            throw new IllegalStateException("No se puede habilitar WSS en un servidor ya en ejecución");
        }
        this.wssFactoryBuilder = wssFactoryBuilder;
    }

    @Override
    public void close() {
        if (server == null) {
            return;
        }

        try {
            server.stop(TIMEOUT_IN_MILLIS);
            sockets.clear();
        } catch (InterruptedException e) {
            try {
                server.stop();
            } catch (InterruptedException ex) {
                logger.error("Error al intentar cerrar el servidor WebSocket", ex);
            }
        } finally {
            closed = true;
            server = null;
        }
    }

    @Override
    public boolean isClosed() {
        return closed;
    }

    @Override
    public void setAsyncRequestHandler(boolean async) {
        this.handleRequestAsync = async;
    }
}