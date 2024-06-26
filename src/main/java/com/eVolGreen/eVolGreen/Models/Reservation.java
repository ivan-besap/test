package com.eVolGreen.eVolGreen.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = true)
    @JsonBackReference("account-reservation")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "charger_id")
    @JsonBackReference("charger-reservation")
    private Charger charger;

    @ManyToOne
    @JoinColumn(name = "connector_id")
    @JsonBackReference("connector-reservation")
    private Connector connector;

    public Reservation() {}

    public Reservation(LocalDateTime startTime, LocalDateTime endTime, Account account, Charger charger, Connector connector) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.account = account;
        this.charger = charger;
        this.connector = connector;
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

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
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
