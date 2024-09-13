package com.eVolGreen.eVolGreen.Models.Account;

import com.eVolGreen.eVolGreen.Models.Account.Account;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account; // Usuario que hizo la acción

    @NotNull(message = "La descripción es obligatoria")
    private String description; // Descripción de la acción

    @NotNull(message = "La fecha es obligatoria")
    private LocalDateTime date; // Fecha y hora de la acción

    @PrePersist
    public void prePersist() {
        this.date = LocalDateTime.now(); // Asignar fecha actual antes de persistir
    }

    public AuditLog() {}

    public AuditLog(Account account, String description, LocalDateTime date) {
        this.account = account;
        this.description = description;
        this.date = date;
    }

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
