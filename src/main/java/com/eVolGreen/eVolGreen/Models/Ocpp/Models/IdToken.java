package com.eVolGreen.eVolGreen.Models.Ocpp.Models;

import java.util.Arrays;
import java.util.Objects;

public class IdToken {
    private CustomData customData;
    private AdditionalInfo[] additionalInfo;
    private String idToken;
    private IdTokenEnum type;

    public IdToken(String idToken, IdTokenEnum type) {
        setIdToken(idToken);
        setType(type);
    }

    public CustomData getCustomData() {
        return customData;
    }

    public void setCustomData(CustomData customData) {
        if (!isValidCustomData(customData)) {
            throw new IllegalArgumentException("Invalid custom data");
        }
        this.customData = customData;
    }

    private boolean isValidCustomData(CustomData customData) {
        return customData == null || customData.validate();
    }

    public IdToken withCustomData(CustomData customData) {
        setCustomData(customData);
        return this;
    }

    public AdditionalInfo[] getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(AdditionalInfo[] additionalInfo) {
        if (!isValidAdditionalInfo(additionalInfo)) {
            throw new IllegalArgumentException("Invalid additional info");
        }
        this.additionalInfo = additionalInfo;
    }

    private boolean isValidAdditionalInfo(AdditionalInfo[] additionalInfo) {
        return additionalInfo == null || (additionalInfo.length >= 1 && Arrays.stream(additionalInfo).allMatch(AdditionalInfo::validate));
    }

    public IdToken withAdditionalInfo(AdditionalInfo[] additionalInfo) {
        setAdditionalInfo(additionalInfo);
        return this;
    }

    public String getIdToken() {
        return idToken;
    }

    public void setIdToken(String idToken) {
        if (!isValidIdToken(idToken)) {
            throw new IllegalArgumentException("Invalid ID token");
        }
        this.idToken = idToken;
    }

    private boolean isValidIdToken(String idToken) {
        return idToken != null && idToken.length() <= 36;
    }

    public IdTokenEnum getType() {
        return type;
    }

    public void setType(IdTokenEnum type) {
        if (!isValidType(type)) {
            throw new IllegalArgumentException("Invalid type");
        }
        this.type = type;
    }

    private boolean isValidType(IdTokenEnum type) {
        return type != null;
    }

    public boolean validate() {
        return isValidCustomData(customData) && isValidAdditionalInfo(additionalInfo) && isValidIdToken(idToken) && isValidType(type);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IdToken idToken1 = (IdToken) o;
        return Objects.equals(customData, idToken1.customData) &&
                Arrays.equals(additionalInfo, idToken1.additionalInfo) &&
                Objects.equals(idToken, idToken1.idToken) &&
                type == idToken1.type;
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(customData, idToken, type);
        result = 31 * result + Arrays.hashCode(additionalInfo);
        return result;
    }

    @Override
    public String toString() {
        return "IdToken{" +
                "customData=" + customData +
                ", additionalInfo=" + Arrays.toString(additionalInfo) +
                ", idToken='" + idToken + '\'' +
                ", type=" + type +
                '}';
    }
}
