package com.eVolGreen.eVolGreen.Models.Ocpp.Models;

import java.util.Arrays;
import java.util.Objects;

public final class AuthorizeRequest {
    private CustomData customData;
    private IdToken idToken;
    private String certificate;
    private String[] iso15118CertificateHashData;

    public AuthorizeRequest(IdToken idToken) {
        setIdToken(idToken);
    }

    public IdToken getIdToken() {
        return idToken;
    }

    public void setIdToken(IdToken idToken) {
        if (!isValidIdToken(idToken)) {
            throw new IllegalArgumentException("Invalid ID token");
        }
        this.idToken = idToken;
    }

    private boolean isValidIdToken(IdToken idToken) {
        return idToken != null && idToken.validate();
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    public String[] getIso15118CertificateHashData() {
        return iso15118CertificateHashData;
    }

    public void setIso15118CertificateHashData(String[] iso15118CertificateHashData) {
        this.iso15118CertificateHashData = iso15118CertificateHashData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorizeRequest that = (AuthorizeRequest) o;
        return Objects.equals(idToken, that.idToken) &&
                Objects.equals(certificate, that.certificate) &&
                Arrays.equals(iso15118CertificateHashData, that.iso15118CertificateHashData);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(idToken, certificate);
        result = 31 * result + Arrays.hashCode(iso15118CertificateHashData);
        return result;
    }

    @Override
    public String toString() {
        return "AuthorizeRequest{" +
                "idToken=" + idToken +
                ", certificate='" + certificate + '\'' +
                ", iso15118CertificateHashData=" + Arrays.toString(iso15118CertificateHashData) +
                '}';
    }
}
