package com.eVolGreen.eVolGreen.DTOS;

import com.eVolGreen.eVolGreen.Models.Account;
import com.eVolGreen.eVolGreen.Models.ChargingStation;
import com.eVolGreen.eVolGreen.Models.Location;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class LocationDTO {

    private long id;
    private String latitude;
    private String longitude;
    private String address;
    private String city;
    private String region;
    private String country;
    private Set<ChargingStationsDTO> chargingStations;


    public LocationDTO(Location location) {
        id = location.getId();
        latitude = location.getLatitude();
        longitude = location.getLongitude();
        address = location.getAddress();
        city = location.getCity();
        region = location.getRegion();
        country = location.getCountry();
        chargingStations = location.getChargingStations().stream().map(ChargingStationsDTO::new).collect(Collectors.toSet());
    }

    public long getId() {
        return id;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getRegion() {
        return region;
    }

    public String getCountry() {
        return country;
    }

    public Set<ChargingStationsDTO> getChargingStations() {
        return chargingStations;
    }
}
