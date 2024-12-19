package com.eVolGreen.eVolGreen.Configurations.MQ;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.*;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.SessionInformation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Ocpp_JSON.JSONCommunicator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.util.UUID;

import static com.eVolGreen.eVolGreen.Configurations.MQ.WebSocketHandler.sessionStore;

/**
 * `AmazonMQCommunicator` gestiona la comunicación con Amazon MQ usando STOMP sobre WSS.
 * Extiende `JSONCommunicator` y asegura la entrega de mensajes entre el sistema central y las estaciones de carga.
 * Proporciona métodos para inicializar sesiones de carga, enviar y recibir mensajes,
 * y gestionar la reconexión en caso de errores de conexión.
 */
@Component
public class AmazonMQCommunicator extends JSONCommunicator implements ServerEvents {

    private static final Logger logger = LoggerFactory.getLogger(AmazonMQCommunicator.class);

//    @Value("${amazon.mq.wss.endpoint}")
    private String wssEndpoint;

//    @Value("${amazon.mq.queue.name}")
    private String queueName;

    private final String username = "eVolGreen";
    private final String password = "eVolGreen123";

    private final SessionManager sessionManager;
    private final WebSocketStompClient stompClient;

    private final Queue queue;
    private final PromiseFulfiller fulfiller;
    private final IFeatureRepository featureRepository;

    /**
     * Inicializa AmazonMQCommunicator con una instancia de `Radio` y configura el cliente STOMP.
     *
     * @param radio instancia de `Radio` para transmisión de mensajes.
     * @param sessionManager El manejador de sesiones de cliente.
     */
    @Autowired
    public AmazonMQCommunicator(Radio radio, SessionManager sessionManager, Queue queue, PromiseFulfiller fulfiller, IFeatureRepository featureRepository) {
        super(radio);
        this.queue = queue;
        this.fulfiller = fulfiller;
        this.featureRepository = featureRepository;
        logger.info("Initializing AmazonMQCommunicator");
        this.sessionManager = sessionManager;
        try {
            logger.info("Creating STOMP client");
            this.stompClient = initializeStompClient();
            logger.info("STOMP client created successfully");
        } catch (Exception e) {
            logger.error("Error creating STOMP client", e);
            throw new RuntimeException("Failed to initialize AmazonMQCommunicator", e);
        }
    }

    /**
     * Configura el cliente WebSocket STOMP para WSS con heartbeat.
     *
     * @return instancia configurada de `WebSocketStompClient`.
     */
    private WebSocketStompClient initializeStompClient() {
        WebSocketClient webSocketClient = new StandardWebSocketClient();
        WebSocketStompClient stompClient = new WebSocketStompClient(webSocketClient);
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());
        stompClient.setDefaultHeartbeat(new long[]{10000, 10000});
        stompClient.setTaskScheduler(new ConcurrentTaskScheduler());
        return stompClient;
    }

    /**
     * Conecta un cliente específico a Amazon MQ con un `CustomStompSessionHandler`.
     *
     * @param clientId el identificador del cliente.
     */
    public void connectToMQ(String clientId) {
        if (!sessionManager.hasSession(clientId)) {
            try {
                StompHeaders connectHeaders = new StompHeaders();
                connectHeaders.setAcceptVersion("1.1");
                connectHeaders.setHeartbeat(new long[]{10000, 10000});
                connectHeaders.setHost("b-53524436-...amazonaws.com");
                connectHeaders.setLogin(username);
                connectHeaders.setPasscode(password);

                logger.info("Conectando al cliente {} a Amazon MQ a través de WSS", clientId);

                StompSessionHandler sessionHandler = new CustomStompSessionHandler(clientId, sessionManager, wssEndpoint, 5000);
                WebSocketHttpHeaders handshakeHeaders = new WebSocketHttpHeaders();

                stompClient.connect(wssEndpoint, handshakeHeaders, connectHeaders, sessionHandler);

                logger.info("Intentando conectar cliente {} a Amazon MQ a través de WSS", clientId);
            } catch (Exception e) {
                logger.error("Error al conectar con Amazon MQ para el cliente {}: {}", clientId, e.getMessage(), e);
            }
        } else {
            logger.warn("El cliente {} ya tiene una sesión activa.", clientId);
        }
    }

    /**
     * Inicializa una nueva sesión STOMP asociada a un chargePointId con Amazon MQ.
     *
     * @param clientId      El identificador del cliente.
     * @param chargePointId El identificador de la estación de carga.
     */
    public void initializeMQSession(String clientId, String chargePointId) {
        try {
            if (!sessionManager.hasSession(clientId)) {
                UUID sessionUUID = UUID.randomUUID();

                // Crear una sesión OCPP
                Session session = new Session(sessionUUID, this, queue, fulfiller, featureRepository);
                session.setChargePointId(chargePointId);

                // Registrar la sesión en el sessionStore
                sessionStore.put(sessionUUID, session);

                // Conectar a Amazon MQ para el cliente y asignar sesión
                connectToMQ(clientId);

                logger.info("Sesión STOMP para chargePoint {} registrada y conectada a Amazon MQ.", chargePointId);
            } else {
                logger.warn("El cliente {} ya tiene una sesión STOMP activa.", clientId);
            }
        } catch (Exception e) {
            logger.error("Error al establecer la conexión STOMP con Amazon MQ para el cliente {}: {}", clientId, e.getMessage(), e);
        }
    }


    /**
     * Inicializa una nueva sesión OCPP asociada a un chargePointId.
     *
     * @param sessionUUID   El identificador único de la sesión.
     * @param chargePointId El identificador de la estación de carga.
     * @return La instancia de {@link Session} que representa la sesión OCPP inicializada.
     */
    /**
     * Inicializa una nueva sesión OCPP asociada a un chargePointId.
     *
     * @param sessionUUID   El identificador único de la sesión.
     * @param chargePointId El identificador de la estación de carga.
     * @return La instancia de {@link Session} que representa la sesión OCPP inicializada.
     */
    private Session initializeSession(UUID sessionUUID, String chargePointId) {
        // Crear la sesión utilizando el AmazonMQCommunicator, Queue, PromiseFulfiller y el repositorio de características
        Session session = new Session(sessionUUID, this, queue, fulfiller, featureRepository);

        // Configurar el chargePointId en la sesión
        session.setChargePointId(chargePointId);

        // Agregar cualquier configuración adicional de la sesión si es necesario
        logger.info("Sesión OCPP inicializada con ID: {} y chargePointId: {}", sessionUUID, chargePointId);

        return session;
    }




    /**
     * Envía un mensaje a Amazon MQ para un cliente específico.
     *
     * @param clientId el ID del cliente.
     * @param message  El contenido del mensaje a enviar.
     */
    public void sendMessage(String clientId, String message) {
        StompSession session = sessionManager.getSession(clientId);
        if (session != null && session.isConnected()) {
            session.send(queueName, message);
            logger.info("Mensaje enviado a Amazon MQ por el cliente {}: {}", clientId, message);
        } else {
            logger.error("No se puede enviar el mensaje. Sesión STOMP no conectada para el cliente {}", clientId);
        }
    }

    /**
     * Cierra la sesión de STOMP para un cliente y elimina la sesión de `sessionManager`.
     *
     * @param clientId el identificador del cliente.
     */
    public void disconnectClient(String clientId) {
        StompSession session = sessionManager.getSession(clientId);
        if (session != null) {
            session.disconnect();
            sessionManager.removeSession(clientId);
            logger.info("Sesión STOMP desconectada para el cliente {}", clientId);
        } else {
            logger.warn("No se encontró sesión activa para el cliente {}", clientId);
        }
    }

    @Override
    public void receivedMessage(UUID sessionId, Object message) {
        // Implementación para manejar mensajes recibidos de Amazon MQ
    }

    @Override
    public void authenticateSession(SessionInformation information, String username, String password) {
        // Lógica de autenticación de sesión
    }

    @Override
    public void newSession(UUID sessionIndex, SessionInformation information) {
        // Crear una nueva sesión para un cliente
    }

    @Override
    public void lostSession(UUID sessionIndex) {
        // Lógica para manejar pérdida de sesión
    }

    @Override
    public void handleError(String uniqueId, String errorCode, String errorDescription, Object payload) {
        // Manejar errores de comunicación
    }

    @Override
    public void handleConfirmation(String uniqueId, Confirmation confirmation) {
        // Confirmación de mensaje
    }

    @Override
    public Confirmation handleRequest(Request request) {
        return null;
    }

    /**
     * Agrega una sesión STOMP a la lista de sesiones activas.
     *
     * @param sessionUUID      Identificador de la sesión.
     * @param webSocketSession La sesión de WebSocket.
     */
    public void addSession(UUID sessionUUID, WebSocketSession webSocketSession) {
        // Lógica para agregar una sesión
    }

    /**
     * Elimina una sesión STOMP de la lista de sesiones activas.
     *
     * @param sessionId El identificador de la sesión a eliminar.
     */
    public void removeSession(UUID sessionId) {
        // Lógica para remover una sesión
    }

    public boolean isConnected(String someClientId) {
        return sessionManager.hasSession(someClientId);
    }
}
