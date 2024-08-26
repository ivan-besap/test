package com.eVolGreen.eVolGreen.Models.Ocpp.Models;

import java.util.Objects;

public class AuthorizeConfirmation {
    private CustomData customData;
    private IdTagInfo idTagInfo;
    private AuthorizeCertificateStatusEnum certificateStatus;

    // Getters y Setters

    public CustomData getCustomData() {
        return customData;
    }

    public void setCustomData(CustomData customData) {
        if (!isValidCustomData(customData)) {
            throw new IllegalArgumentException("Invalid custom data");
        }
        this.customData = customData;
    }

    public IdTagInfo getIdTagInfo() {
        return idTagInfo;
    }

    public void setIdTagInfo(IdTagInfo idTagInfo) {
        if (!isValidIdTagInfo(idTagInfo)) {
            throw new IllegalArgumentException("Invalid ID tag info");
        }
        this.idTagInfo = idTagInfo;
    }

    public AuthorizeCertificateStatusEnum getCertificateStatus() {
        return certificateStatus;
    }

    public void setCertificateStatus(AuthorizeCertificateStatusEnum certificateStatus) {
        this.certificateStatus = certificateStatus;
    }

    // Validación

    private boolean isValidCustomData(CustomData customData) {
        return customData == null || customData.validate();
    }

    private boolean isValidIdTagInfo(IdTagInfo idTagInfo) {
        return idTagInfo != null && idTagInfo.validate();
    }

    public boolean validate() {
        return isValidCustomData(customData) && isValidIdTagInfo(idTagInfo);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorizeConfirmation that = (AuthorizeConfirmation) o;
        return Objects.equals(customData, that.customData) &&
                Objects.equals(idTagInfo, that.idTagInfo) &&
                certificateStatus == that.certificateStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(customData, idTagInfo, certificateStatus);
    }

    @Override
    public String toString() {
        return "AuthorizeConfirmation{" +
                "customData=" + customData +
                ", idTagInfo=" + idTagInfo +
                ", certificateStatus=" + certificateStatus +
                ", isValid=" + validate() +
                '}';
    }
}
