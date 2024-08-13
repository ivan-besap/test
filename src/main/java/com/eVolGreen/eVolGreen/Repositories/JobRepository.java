package com.eVolGreen.eVolGreen.Repositories;

import com.eVolGreen.eVolGreen.Models.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource
public interface JobRepository extends JpaRepository<Job, Long> {
    Optional<Job> findByName(String name);
}