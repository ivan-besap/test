package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource
public interface DiagnosticsFileRepository extends JpaRepository <DiagnosticsFile, Integer> {
}
