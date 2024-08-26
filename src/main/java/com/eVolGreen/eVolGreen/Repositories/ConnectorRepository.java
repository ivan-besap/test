package com.eVolGreen.eVolGreen.Repositories;

import com.eVolGreen.eVolGreen.Models.ChargingStation.Connector.Connector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ConnectorRepository extends JpaRepository<Connector, Long> {
}
