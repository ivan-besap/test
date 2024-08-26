package com.eVolGreen.eVolGreen.Models.Ocpp.Models;

import java.time.ZonedDateTime;

public class HeartbeatConfirmation {
    private ZonedDateTime currentTime;

    // Getters y Setters

    public ZonedDateTime getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(ZonedDateTime currentTime) {
        this.currentTime = currentTime;
    }

    @Override
    public String toString() {
        return "HeartbeatConfirmation{" +
                "currentTime=" + currentTime +
                '}';
    }
}
