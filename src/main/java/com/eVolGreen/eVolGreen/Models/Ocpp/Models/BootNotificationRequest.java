package com.eVolGreen.eVolGreen.Models.Ocpp.Models;

import java.time.ZonedDateTime;

public class BootNotificationRequest {

    private String chargePointModel;
    private String chargePointVendor;

    // Getters y Setters

    public String getChargePointModel() {
        return chargePointModel;
    }

    public void setChargePointModel(String chargePointModel) {
        this.chargePointModel = chargePointModel;
    }

    public String getChargePointVendor() {
        return chargePointVendor;
    }

    public void setChargePointVendor(String chargePointVendor) {
        this.chargePointVendor = chargePointVendor;
    }

    @Override
    public String toString() {
        return "BootNotificationRequest{" +
                "chargePointModel='" + chargePointModel + '\'' +
                ", chargePointVendor='" + chargePointVendor + '\'' +
                '}';
    }
}
