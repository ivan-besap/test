package com.eVolGreen.eVolGreen.DTOS;

import com.eVolGreen.eVolGreen.Models.Car;
import com.eVolGreen.eVolGreen.Models.DeviceIdentifier;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class DeviceIdentifierDTO {
    private long id;
    private String identifierName;
    private long identifierNumber;
    private String identifierType;

    public DeviceIdentifierDTO(DeviceIdentifier deviceIdentifier) {
        id = deviceIdentifier.getId();
        identifierName = deviceIdentifier.getIdentifierName();
        identifierNumber = deviceIdentifier.getIdentifierNumber();
        identifierType = deviceIdentifier.getIdentifierType();
    }

    public long getId() {
        return id;
    }

    public String getIdentifierName() {
        return identifierName;
    }

    public long getIdentifierNumber() {
        return identifierNumber;
    }

    public String getIdentifierType() {
        return identifierType;
    }
}
