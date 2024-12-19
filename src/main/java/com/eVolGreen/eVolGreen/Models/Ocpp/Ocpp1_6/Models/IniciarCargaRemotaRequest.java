package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models;

import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Core.Requests.Utils.ChargingProfile;

public class IniciarCargaRemotaRequest {
    private int connectorId;
    private String idTag;
    private ChargingProfile chargingProfile;

    // Getters y setters
    public int getConnectorId() {
        return connectorId;
    }

    public void setConnectorId(int connectorId) {
        this.connectorId = connectorId;
    }

    public String getIdTag() {
        return idTag;
    }

    public void setIdTag(String idTag) {
        this.idTag = idTag;
    }

    public ChargingProfile getChargingProfile() {
        return chargingProfile;
    }

    public void setChargingProfile(ChargingProfile chargingProfile) {
        this.chargingProfile = chargingProfile;
    }
}
