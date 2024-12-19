package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6;


import com.eVolGreen.eVolGreen.Configurations.MQ.AmazonMQCommunicator;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.*;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.OccurenceConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.UnsupportedFeatureException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Ocpp_JSON.JSONCommunicator;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Ocpp_JSON.JSONConfiguration;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Ocpp_JSON.WSS.BaseWssSocketBuilder;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Ocpp_JSON.WSS.WssSocketBuilder;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Ocpp_JSON.WebSocketTransmitter;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Feature.Profile.ClientCoreProfile;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Feature.Profile.Profile;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.protocols.Protocol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.util.Collections;
import java.util.UUID;
import java.util.concurrent.CompletionStage;

/**
 * Implementation of the OCPP 1.6 JSON WebSocket client with optional Amazon MQ support.
 * <p>
 * This client supports connecting, sending requests, and receiving confirmations
 * according to the OCPP 1.6 JSON protocol over WebSocket and optionally integrates
 * with Amazon MQ for message queuing support.
 * </p>
 */
public class JSONClient implements IClientAPI {

    private static final Logger logger = LoggerFactory.getLogger(JSONClient.class);

    public final Draft draftOcppOnly;
    private final WebSocketTransmitter transmitter;
    private final FeatureRepository featureRepository;
    private final Client client;
    private final String identity;
    private final AmazonMQCommunicator amazonMQCommunicator;

    /**
     * Constructor that initializes the client with the specified core profile.
     *
     * @param coreProfile The core profile that defines the features of the client.
     */
    public JSONClient(ClientCoreProfile coreProfile) {
        this(coreProfile, null, JSONConfiguration.get(), null);
    }

    /**
     * Constructs a JSONClient instance with a specified core profile, identity,
     * JSON configuration, and Amazon MQ communicator.
     *
     * @param coreProfile     The core profile that defines the features of the client.
     * @param identity        Optional identity to add to the URL.
     * @param configuration   JSON configuration settings for the client.
     * @param mqCommunicator  Optional AmazonMQCommunicator instance for MQ communication.
     */
    public JSONClient(ClientCoreProfile coreProfile, String identity, JSONConfiguration configuration, AmazonMQCommunicator mqCommunicator) {
        this.identity = identity;
        this.amazonMQCommunicator = mqCommunicator;
        this.draftOcppOnly = new Draft_6455(Collections.emptyList(), Collections.singletonList(new Protocol("ocpp1.6")));

        // Initialize the WebSocket transmitter
        this.transmitter = new WebSocketTransmitter(configuration, this.draftOcppOnly);

        // Create the JSON communicator using the WebSocket transmitter
        JSONCommunicator communicator = new JSONCommunicator(this.transmitter) {
            @Override
            public void receivedMessage(UUID sessionId, Object message) {

            }
        };

        // Initialize the feature repository and session
        this.featureRepository = new FeatureRepository();
        ISession session = new SessionFactory(this.featureRepository).createSession(communicator);

        // Initialize the client with the session and promise repository
        this.client = new Client(session, new PromiseRepository());

        // Add the core profile to the feature repository
        this.featureRepository.addFeatureProfile(coreProfile);
    }

    /**
     * Enables WSS (Secure WebSocket) support using the provided SSLContext.
     *
     * @param sslContext SSLContext used for secure socket connections.
     * @throws IOException If an I/O error occurs during initialization.
     */
    public void enableWSS(SSLContext sslContext) throws IOException {
        WssSocketBuilder wssSocketBuilder = BaseWssSocketBuilder.builder().sslSocketFactory(sslContext.getSocketFactory());
        this.enableWSS(wssSocketBuilder);
    }

    /**
     * Enables WSS (Secure WebSocket) support.
     *
     * @param wssSocketBuilder WssSocketBuilder instance used to verify and initialize WSS support.
     * @return The current JSONClient instance with WSS enabled.
     */
    public JSONClient enableWSS(WssSocketBuilder wssSocketBuilder) {
        wssSocketBuilder.verify();
        this.transmitter.enableWSS(wssSocketBuilder);
        return this;
    }

    /**
     * Adds an additional feature profile to the client.
     *
     * @param profile The feature profile to be added.
     */
    public void addFeatureProfile(Profile profile) {
        this.featureRepository.addFeatureProfile(profile);
    }

    /**
     * Connects the client to the specified URL with the provided client events handler.
     *
     * @param url           The URL to connect to.
     * @param clientEvents  The client events handler to manage connection events.
     */
    public void connect(String url, ClientEvents clientEvents) throws InterruptedException {
        logger.debug("Feature repository: {}", this.featureRepository);
        String identityUrl = this.identity != null ? String.format("%s/%s", url, this.identity) : url;
        this.client.connect(identityUrl, clientEvents);
    }

    /**
     * Sends a request to the connected server.
     *
     * @param request The request to be sent.
     * @return A CompletionStage that, when completed, contains the confirmation.
     * @throws OccurenceConstraintException   If the request violates occurrence constraints.
     * @throws UnsupportedFeatureException    If the request contains an unsupported feature.
     */
    public CompletionStage<Confirmation> send(Request request) throws OccurenceConstraintException, UnsupportedFeatureException {
        return this.client.send(request);
    }

    @Override
    public boolean asyncCompleteRequest(String uniqueId, Confirmation confirmation) throws UnsupportedFeatureException, OccurenceConstraintException {
        return false;
    }

    /**
     * Disconnects the client from the server.
     */
    public void disconnect() {
        this.client.disconnect();
    }

    /**
     * Checks if the WebSocket transmitter is closed.
     *
     * @return True if the transmitter is closed; otherwise, false.
     */
    public boolean isClosed() {
        return this.transmitter.isClosed();
    }

    /**
     * Retrieves the session ID of the current session.
     *
     * @return The UUID of the session.
     */
    public UUID getSessionId() {
        return this.client.getSessionId();
    }
}