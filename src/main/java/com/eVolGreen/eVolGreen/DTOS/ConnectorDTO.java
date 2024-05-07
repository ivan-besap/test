package com.eVolGreen.eVolGreen.DTOS;

import com.eVolGreen.eVolGreen.Models.Connector;
import com.eVolGreen.eVolGreen.Models.ConnectorStatus;

import java.math.BigDecimal;

public class ConnectorDTO {
    private Long id;
    private String name;
    private BigDecimal power;
    private ConnectorStatus connectorStatus;
    private BigDecimal charge;

    public ConnectorDTO(Connector connector) {
        id = connector.getId();
        name = connector.getName();
        power = connector.getPower();
        connectorStatus = connector.getConnectorStatus();
        charge = connector.getCharge();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPower() {
        return power;
    }

    public ConnectorStatus getConnectorStatus() {
        return connectorStatus;
    }

    public BigDecimal getCharge() {
        return charge;
    }
}
