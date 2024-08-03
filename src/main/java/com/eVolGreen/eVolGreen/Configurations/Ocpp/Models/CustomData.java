package com.eVolGreen.eVolGreen.Configurations.Ocpp.Models;

public class CustomData {
    private String vendorId;

    public CustomData(String vendorId) {
        setVendorId(vendorId);
    }

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        if (!isValidVendorId(vendorId)) {
            throw new IllegalArgumentException("Invalid vendor ID");
        }
        this.vendorId = vendorId;
    }

    private boolean isValidVendorId(String vendorId) {
        return vendorId != null && vendorId.length() <= 255;
    }

    public boolean validate() {
        return isValidVendorId(vendorId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomData that = (CustomData) o;
        return vendorId.equals(that.vendorId);
    }

    @Override
    public int hashCode() {
        return vendorId.hashCode();
    }

    @Override
    public String toString() {
        return "CustomData{" +
                "vendorId='" + vendorId + '\'' +
                '}';
    }
}
