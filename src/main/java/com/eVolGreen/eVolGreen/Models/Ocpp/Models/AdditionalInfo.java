package com.eVolGreen.eVolGreen.Models.Ocpp.Models;

import java.util.Objects;

public class AdditionalInfo {
    private CustomData customData;
    private String additionalIdToken;
    private String type;

    public AdditionalInfo(String additionalIdToken, String type) {
        setAdditionalIdToken(additionalIdToken);
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

    public AdditionalInfo withCustomData(CustomData customData) {
        setCustomData(customData);
        return this;
    }

    public String getAdditionalIdToken() {
        return additionalIdToken;
    }

    public void setAdditionalIdToken(String additionalIdToken) {
        if (!isValidAdditionalIdToken(additionalIdToken)) {
            throw new IllegalArgumentException("Invalid additional ID token");
        }
        this.additionalIdToken = additionalIdToken;
    }

    private boolean isValidAdditionalIdToken(String additionalIdToken) {
        return additionalIdToken != null && additionalIdToken.length() <= 36;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        if (!isValidType(type)) {
            throw new IllegalArgumentException("Invalid type");
        }
        this.type = type;
    }

    private boolean isValidType(String type) {
        return type != null && type.length() <= 50;
    }

    public boolean validate() {
        return isValidCustomData(customData)
                && isValidAdditionalIdToken(additionalIdToken)
                && isValidType(type);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdditionalInfo that = (AdditionalInfo) o;
        return Objects.equals(customData, that.customData) &&
                Objects.equals(additionalIdToken, that.additionalIdToken) &&
                Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customData, additionalIdToken, type);
    }

    @Override
    public String toString() {
        return "AdditionalInfo{" +
                "customData=" + customData +
                ", additionalIdToken='" + additionalIdToken + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
