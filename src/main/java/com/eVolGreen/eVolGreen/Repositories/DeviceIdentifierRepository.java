package com.eVolGreen.eVolGreen.Repositories;

import com.eVolGreen.eVolGreen.Models.DeviceIdentifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface DeviceIdentifierRepository extends JpaRepository<DeviceIdentifier, Long> {
}
