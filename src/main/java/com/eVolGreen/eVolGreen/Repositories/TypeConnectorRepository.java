package com.eVolGreen.eVolGreen.Repositories;
import com.eVolGreen.eVolGreen.Models.ChargingStation.Connector.TypeConnector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface TypeConnectorRepository extends JpaRepository<TypeConnector, Long> {
}
