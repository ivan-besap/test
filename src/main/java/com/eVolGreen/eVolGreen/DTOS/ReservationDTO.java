package com.eVolGreen.eVolGreen.DTOS;

import com.eVolGreen.eVolGreen.Models.Reservation;
import jakarta.persistence.*;
import java.time.LocalDateTime;

public class ReservationDTO {

    private Long id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Long stationId;
    private Long chargerId;
    private Long connectorId;

    public ReservationDTO() {
    }

    public ReservationDTO(Reservation reservation) {
        this.id = reservation.getId();
        this.startTime = reservation.getStartTime();
        this.endTime = reservation.getEndTime();
        this.chargerId = reservation.getCharger() != null ? reservation.getCharger().getId() : null;
        this.connectorId = reservation.getConnector() != null ? reservation.getConnector().getId() : null;
        this.stationId = reservation.getStation() != null ? reservation.getStation().getId() : null;
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

    public Long getChargerId() {
        return chargerId;
    }

    public void setChargerId(Long chargerId) {
        this.chargerId = chargerId;
    }

    public Long getConnectorId() {
        return connectorId;
    }

    public void setConnectorId(Long connectorId) {
        this.connectorId = connectorId;
    }

    public Long getStationId() {
        return stationId;
    }
}
