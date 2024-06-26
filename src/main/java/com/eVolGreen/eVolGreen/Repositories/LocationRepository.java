package com.eVolGreen.eVolGreen.Repositories;

import com.eVolGreen.eVolGreen.Models.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface LocationRepository extends JpaRepository<Location, Long> {
}
