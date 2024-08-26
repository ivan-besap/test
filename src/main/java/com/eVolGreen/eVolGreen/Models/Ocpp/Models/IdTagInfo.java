package com.eVolGreen.eVolGreen.Models.Ocpp.Models;

import java.time.ZonedDateTime;
import java.util.Objects;

public class IdTagInfo {
    private AuthorizationStatus status;
    private ZonedDateTime expiryDate;

    // Constructor

    public IdTagInfo(AuthorizationStatus status, ZonedDateTime expiryDate) {
        this.status = status;
        this.expiryDate = expiryDate;
    }

    // Getters y Setters

    public AuthorizationStatus getStatus() {
        return status;
    }

    public void setStatus(AuthorizationStatus status) {
        this.status = status;
    }

    public ZonedDateTime getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(ZonedDateTime expiryDate) {
        this.expiryDate = expiryDate;
    }

    public boolean validate() {
        return status != null && expiryDate != null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IdTagInfo idTagInfo = (IdTagInfo) o;
        return status == idTagInfo.status &&
                Objects.equals(expiryDate, idTagInfo.expiryDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, expiryDate);
    }

    @Override
    public String toString() {
        return "IdTagInfo{" +
                "status=" + status +
                ", expiryDate=" + expiryDate +
                '}';
    }
}
