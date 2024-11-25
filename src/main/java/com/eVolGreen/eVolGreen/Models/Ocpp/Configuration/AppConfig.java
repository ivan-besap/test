package com.eVolGreen.eVolGreen.Models.Ocpp.Configuration;

import com.eVolGreen.eVolGreen.Configurations.MQ.AmazonMQCommunicator;
import com.eVolGreen.eVolGreen.Configurations.MQ.SessionManager;
import com.eVolGreen.eVolGreen.Configurations.MQ.WebSocketHandler;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.*;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.AuthenticationException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.*;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Ocpp_JSON.WebSocketReceiver;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Ocpp_JSON.WebSocketReceiverEvents;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Feature.Handler.ClientCoreEventHandler;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Feature.Handler.ClientRemoteTriggerEventHandler;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Feature.Handler.ServerCoreEventHandler;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Feature.Profile.ClientCoreProfile;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Feature.Profile.ClientRemoteTriggerProfile;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Feature.Profile.ServerCoreProfile;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Feature.Profile.ServerRemoteTriggerProfile;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.JSONClient;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.JSONServer;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Ocpp_JSON.JSONConfiguration;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.RemoteTrigger.Confirmations.TriggerMessageConfirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.RemoteTrigger.Request.TriggerMessageRequest;
import com.eVolGreen.eVolGreen.Services.AccountService.UtilService;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

import static com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Ocpp_JSON.JSONCommunicator.*;

/**
 * AppConfig configura y gestiona los beans necesarios para la aplicación eVolGreen OCPP.
 * <p>
 * Esta configuración incluye la integración de Amazon MQ, configuración de sesiones y perfiles
 * de cliente y servidor OCPP, y el soporte para WebSocket a través de {@link WebSocketHandler}.
 * </p>
 */
@Configuration
public class AppConfig {

    private Communicator communicator;

    /**
     * Almacenamiento compartido de sesiones para manejar las conexiones activas.
     * <p>
     * Este mapa es utilizado por {@code WebSocketHandler} y {@code JSONServer} para acceder a las sesiones de forma consistente.
     * </p>
     * @return un mapa concurrente de sesiones.
     */
    @Bean
    public Map<UUID, ISession> sessionStore() {
        return new ConcurrentHashMap<>();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }


    /**
     * Configura y devuelve una instancia de {@link FeatureRepository}.
     *
     * @return una instancia configurada de {@link FeatureRepository}.
     */
    @Bean
    public FeatureRepository featureRepository() {
        return new FeatureRepository();
    }

    /**
     * Configura y devuelve una instancia de {@link JSONClient} para gestionar la comunicación OCPP utilizando el perfil de cliente.
     *
     * @param coreProfile El perfil de núcleo del cliente.
     * @param mqCommunicator El comunicador de Amazon MQ.
     * @return una instancia configurada de {@link JSONClient}.
     */
    @Bean
    public JSONClient jsonClient(ClientCoreProfile coreProfile, AmazonMQCommunicator mqCommunicator) {
        String identity = UUID.randomUUID().toString();
        JSONConfiguration configuration = JSONConfiguration.get();

//        // Crear la instancia de JSONClient
//        JSONClient client = new JSONClient(coreProfile, identity, configuration, mqCommunicator);
//
//        try {
//            // Crear y configurar el SSLContext para WSS
//            SSLContext sslContext = createSSLContext(); // Extrae la creación del SSLContext a un método separado
//            client.enableWSS(sslContext);
//        } catch (Exception e) {
//            throw new IOException("Error al configurar WSS en JSONClient: " + e.getMessage(), e);
//        }
//
//        return client;

        return new JSONClient(coreProfile, identity, configuration, mqCommunicator);
    }


//    /**
//     * Crea y configura el SSLContext para habilitar WSS en JSONClient.
//     * Configura el keystore y otros parámetros de seguridad necesarios.
//     *
//     * @return Un SSLContext configurado para WSS
//     * @throws Exception si ocurre un error durante la configuración
//     */
//    private SSLContext createSSLContext() throws Exception {
//        String keyStorePath = "src/main/resources/keystore.jks";
//        String keyStorePassword = "evolgreenpassword";
//
//        KeyStore keyStore = KeyStore.getInstance("JKS");
//        try (FileInputStream keyStoreInput = new FileInputStream(keyStorePath)) {
//            keyStore.load(keyStoreInput, keyStorePassword.toCharArray());
//        }
//
//        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance("SunX509");
//        keyManagerFactory.init(keyStore, keyStorePassword.toCharArray());
//
//        SSLContext sslContext = SSLContext.getInstance("TLS");
//        sslContext.init(keyManagerFactory.getKeyManagers(), null, new SecureRandom());
//
//        return sslContext;
//    }


    /**
     * Configura y devuelve una instancia de {@link ISessionFactory} para la creación de sesiones.
     *
     * @param featureRepository Repositorio de características para el perfil OCPP.
     * @return una instancia configurada de {@link ISessionFactory}.
     */
    @Bean
    public ISessionFactory sessionFactory(FeatureRepository featureRepository) {
        return new SessionFactory(featureRepository);
    }

    /**
     * Configura y devuelve una instancia de {@link WebSocketHandler} para manejar las conexiones WebSocket.
     *
     * @param sessionFactory La fábrica de sesiones.
     * @param communicator El comunicador OCPP.
     * @param jsonServer El servidor JSON.
     * @param coreProfile El perfil de núcleo del servidor.
//     * @param amazonMQCommunicator El comunicador de Amazon MQ.
     * @return una instancia configurada de {@link WebSocketHandler}.
     */
    @Bean
    public WebSocketHandler webSocketHandler(UtilService utilService, ISessionFactory sessionFactory, Communicator communicator,
                                             JSONServer jsonServer, ServerCoreProfile coreProfile, Queue queue,
                                             PromiseFulfiller fulfiller, FeatureRepository featureRepository) {

        return new WebSocketHandler(utilService, sessionFactory, communicator, jsonServer, coreProfile, queue,fulfiller,featureRepository);
    }

    /**
     * Configura y devuelve una instancia de {@link JSONServer} para gestionar la comunicación OCPP en el servidor.
     *
     * @param coreProfile El perfil de núcleo del servidor.
     * @param amazonMQCommunicator El comunicador de Amazon MQ.
     * @param jsonConfiguration Configuración de JSON.
     * @param sessionStore El almacenamiento compartido de sesiones.
     * @return una instancia configurada de {@link JSONServer}.
     */
    @Bean
    public JSONServer jsonServer(ServerCoreProfile coreProfile, AmazonMQCommunicator amazonMQCommunicator,
                                 JSONConfiguration jsonConfiguration, Map<UUID, ISession> sessionStore) {
//        String brokerId = amazonMQCommunicator.getBrokerId();
        return new JSONServer(coreProfile, jsonConfiguration, sessionStore);
    }

    /**
     * Configura y devuelve una instancia de {@link ClientCoreProfile} para definir y gestionar las acciones específicas del cliente.
     *
     * @param eventHandler Manejador de eventos del cliente.
     * @return una instancia configurada de {@link ClientCoreProfile}.
     */
    @Bean
    public ClientCoreProfile clientCoreProfile(ClientCoreEventHandler eventHandler) {
        return new ClientCoreProfile(eventHandler);
    }

    /**
     * Configura y devuelve una instancia de {@link ServerCoreProfile} para definir y gestionar las acciones específicas del servidor.
     *
     * @param serverEventHandler Manejador de eventos del servidor.
     * @return una instancia configurada de {@link ServerCoreProfile}.
     */
    @Bean
    public ServerCoreProfile serverCoreProfile(ServerCoreEventHandler serverEventHandler) {
        return new ServerCoreProfile(serverEventHandler);
    }

    /**
     * Configura y devuelve una instancia de {@link AmazonMQCommunicator} para la comunicación con Amazon MQ.
     *
     * @return una instancia configurada de {@link AmazonMQCommunicator}.
     */

//    @Bean
//    public AmazonMQCommunicator amazonMQCommunicator(Radio radio, SessionManager sessionManager, Queue queue, PromiseFulfiller fulfiller, IFeatureRepository featureRepository) {
//        try {
//            return new AmazonMQCommunicator(radio, sessionManager, queue, fulfiller, featureRepository);
//        } catch (Exception e) {
//            throw new RuntimeException("Error al instanciar AmazonMQCommunicator", e);
//        }
//    }

    @Bean
    public SessionManager sessionManager() {
        return new SessionManager();
    }

     /**
     * Configura y devuelve una instancia de {@link JSONConfiguration} para la configuración de JSON en OCPP.
     *
     * @return una instancia configurada de {@link JSONConfiguration}.
     */
    @Bean
    public JSONConfiguration jsonConfiguration() {
        return JSONConfiguration.get();
    }

    /**
     * Configura y devuelve una instancia de {@link ClientRemoteTriggerProfile} para el perfil de activación remota del cliente.
     *
     * @return una instancia configurada de {@link ClientRemoteTriggerProfile}.
     */
    @Bean
    public ClientRemoteTriggerProfile clientRemoteTriggerProfile() {
        return new ClientRemoteTriggerProfile(new ClientRemoteTriggerEventHandler() {
            @Override
            public TriggerMessageConfirmation handleTriggerMessageRequest(TriggerMessageRequest request) {
                return null;
            }
        });
    }

    /**
     * Configura y devuelve una instancia de {@link ServerRemoteTriggerProfile} para el perfil de activación remota del servidor.
     *
     * @param serverCoreEventHandler Manejador de eventos del núcleo del servidor.
     * @return una instancia configurada de {@link ServerRemoteTriggerProfile}.
     */
    @Bean
    public ServerRemoteTriggerProfile serverRemoteTriggerProfile(ServerCoreEventHandler serverCoreEventHandler) {
        return new ServerRemoteTriggerProfile();
    }

    @Bean
    public ServerEvents serverEvents() {
        return new ServerEvents() {
            @Override
            public void authenticateSession(SessionInformation information, String username, String password) throws AuthenticationException {

            }

            @Override
            public void newSession(UUID sessionIndex, SessionInformation information) {

            }

            @Override
            public void lostSession(UUID sessionIndex) {

            }

            @Override
            public void handleError(String uniqueId, String errorCode, String errorDescription, Object payload) {

            }

            @Override
            public void handleConfirmation(String uniqueId, Confirmation confirmation) {

            }

            @Override
            public Confirmation handleRequest(Request request) {
                return null;
            }
        };
    }

    @Bean
    public Queue queue() {
        return new Queue();
    }

    @Bean
    public PromiseFulfiller promiseFulfiller() {
        return new PromiseFulfiller() {
            @Override
            public void fulfill(CompletableFuture<Confirmation> promise, SessionEvents eventHandler, Request request) {
                // implement the logic to fulfill the promise

            }
        };
    }

    @Bean
    public Communicator communicator() {
        return new Communicator() {

            @Override
            public <T> T unpackPayload(Object payload, Class<T> type) throws Exception {
                // Deserialización del payload usando la lógica de OCPP y Gson
                return gson.fromJson(payload.toString(), type);
            }

            @Override
            public Object packPayload(Object payload) {
                // Serialización del payload usando la lógica de OCPP y Gson
                return gson.toJson(payload);
            }

            @Override
            protected Object makeCallResult(String uniqueId, String action, Object payload) {
                // Genera el CallResult OCPP con el formato adecuado
                return String.format(CALLRESULT_FORMAT, uniqueId, packPayload(payload));
            }

            @Override
            protected Object makeCall(String uniqueId, String action, Object payload) {
                // Genera el Call OCPP con el formato adecuado
                return String.format(CALL_FORMAT, uniqueId, action, packPayload(payload));
            }

            @Override
            protected Object makeCallError(String uniqueId, String action, String errorCode, String errorDescription) {
                // Genera el CallError OCPP con el formato adecuado
                return String.format(CALLERROR_FORMAT, uniqueId, errorCode, errorDescription, "{}");
            }

            @Override
            public boolean isClosed() {
                // Aquí llamamos a la lógica que verifica si la conexión está cerrada
                return radio.isClosed();
            }

            @Override
            public Message parse(Object message) {
                Message parsedMessage;
                JsonArray array;
                String messageId = "-1";

                try {
                    array = JsonParser.parseString(message.toString()).getAsJsonArray();
                    messageId = array.get(INDEX_UNIQUEID).getAsString();

                    int messageType = array.get(INDEX_MESSAGEID).getAsInt();
                    switch (messageType) {
                        case TYPENUMBER_CALL:
                            parsedMessage = new CallMessage();
                            parsedMessage.setAction(array.get(INDEX_CALL_ACTION).getAsString());
                            parsedMessage.setPayload(array.get(INDEX_CALL_PAYLOAD).toString());
                            break;
                        case TYPENUMBER_CALLRESULT:
                            parsedMessage = new CallResultMessage();
                            parsedMessage.setPayload(array.get(INDEX_CALLRESULT_PAYLOAD).toString());
                            break;
                        case TYPENUMBER_CALLERROR:
                            parsedMessage = new CallErrorMessage();
                            ((CallErrorMessage) parsedMessage).setErrorCode(array.get(INDEX_CALLERROR_ERRORCODE).getAsString());
                            ((CallErrorMessage) parsedMessage).setErrorDescription(array.get(INDEX_CALLERROR_DESCRIPTION).getAsString());
                            ((CallErrorMessage) parsedMessage).setRawPayload(array.get(INDEX_CALLERROR_PAYLOAD).toString());
                            break;
                        default:
                            logger.error("Tipo de mensaje desconocido: {}. Contenido del mensaje: {}", messageType, message);
                            sendCallError(messageId, null, "MessageTypeNotSupported", "Tipo de mensaje no soportado: " + messageType);
                            return null;
                    }
                } catch (JsonSyntaxException e) {
                    logger.error("Error de sintaxis JSON al analizar el mensaje: {}. Error: {}", message, e.getMessage());
                    sendCallError(messageId, null, "RpcFrameworkError", "Error de sintaxis JSON: " + e.getMessage());
                    return null;
                } catch (Exception e) {
                    logger.error("Error inesperado al analizar el mensaje: {}. Error: {}", message, e.getMessage());
                    sendCallError(messageId, null, "RpcFrameworkError", "Error inesperado: " + e.getMessage());
                    return null;
                }

                parsedMessage.setId(messageId);
                return parsedMessage;
            }
        };
    }


    @Bean
    public WebSocketReceiverEvents webSocketReceiverEvents(Communicator communicator) {
        return new WebSocketReceiverEvents() {
            private boolean closed = false;

            @Override
            public boolean isClosed() {
                return closed;
            }

            @Override
            public void close() {
                if (!closed) {
                    closed = true;
                    System.out.println("Disconnected from the server");
                    handleDisconnection();
                }
            }

            @Override
            public void relay(String message) {
                System.out.println("Received message: " + message);
                try {
                    // Usa la instancia de Communicator inyectada para procesar el mensaje
                    Message parsedMessage = communicator.parse(message);
                    if (parsedMessage != null) {
                        handleIncomingMessage(parsedMessage);
                    } else {
                        System.err.println("Failed to parse message: " + message);
                    }
                } catch (Exception e) {
                    System.err.println("Error processing message: " + e.getMessage());
                    e.printStackTrace();
                }
            }

            private void handleDisconnection() {
                System.out.println("Cleaning up resources after disconnection");
            }

            private void handleIncomingMessage(Message message) {
                if (message instanceof CallMessage) {
                    System.out.println("Handling CallMessage: " + message);
                } else if (message instanceof CallResultMessage) {
                    System.out.println("Handling CallResultMessage: " + message);
                } else if (message instanceof CallErrorMessage) {
                    System.out.println("Handling CallErrorMessage: " + message);
                } else {
                    System.out.println("Unknown message type: " + message);
                }
            }
        };
    }

    @Bean
    public Radio radio(WebSocketReceiverEvents receiverEvents) {
        return new WebSocketReceiver(receiverEvents);
    }




}
