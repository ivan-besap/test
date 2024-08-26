package com.eVolGreen.eVolGreen.Models.Ocpp;

import org.springframework.stereotype.Component;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;

@Component
public class OcppClient extends WebSocketClient {

    public OcppClient(URI serverUri) {
        super(serverUri);
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        System.out.println("Connected to server");
    }

    @Override
    public void onMessage(String message) {
        System.out.println("Received message: " + message);
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        System.out.println("Disconnected from server");
    }

    @Override
    public void onError(Exception ex) {
        System.out.println("Error: " + ex.getMessage());
    }
}
