package com.eVolGreen.eVolGreen.DTOS;

import com.eVolGreen.eVolGreen.Models.Location;

public class LocationDTO {

    private long id;
    private String address;

    // Constructor sin argumentos
    public LocationDTO() {  }

    // Constructor con argumentos
    public LocationDTO(long id, String address) {
        this.id = id;
        this.address = address;
    }

    public LocationDTO(Location location) {
        id  = location.getId();
        address = location.getAddress();
    }

    // Getters y Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
