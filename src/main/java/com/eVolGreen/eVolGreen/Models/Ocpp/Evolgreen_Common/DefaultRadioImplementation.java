package com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.NotConnectedException;

public class DefaultRadioImplementation implements Radio {
    private boolean closed = true;

    @Override
    public void disconnect() {
        closed = true;
        System.out.println("Radio disconnected.");
    }

    @Override
    public void send(Object message) throws NotConnectedException {
        if (isClosed()) {
            throw new NotConnectedException("Cannot send message, Radio is closed.");
        }
        System.out.println("Message sent: " + message);
    }

    @Override
    public boolean isClosed() {
        return closed;
    }

    public void connect() {
        closed = false;
        System.out.println("Radio connected.");
    }
}