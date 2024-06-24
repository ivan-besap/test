package com.eVolGreen.eVolGreen.DTOS;

import com.eVolGreen.eVolGreen.Models.*;
import jakarta.persistence.*;

import java.time.LocalDateTime;

public class ReservationDTO {

    private Long id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Charger charger;
    private Connector connector;

    public ReservationDTO() {
    }

    public ReservationDTO(Reservation reservation) {
        this.id = reservation.getId();
        this.startTime = reservation.getStartTime();
        this.endTime = reservation.getEndTime();
//        this.charger = reservation.getCharger();
//        this.connector = reservation.getConnector();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Charger getCharger() {
        return charger;
    }

    public void setCharger(Charger charger) {
        this.charger = charger;
    }

    public Connector getConnector() {
        return connector;
    }

    public void setConnector(Connector connector) {
        this.connector = connector;
    }
}
