package com.eVolGreen.eVolGreen.Repositories;

import com.eVolGreen.eVolGreen.Models.Account.PermissionCredential.Credential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource
public interface CredentialRepository extends JpaRepository<Credential, Long> {


    Credential findByNombre(String credencial);

    List<Credential> findAllByNombre(String administrador);

    Optional<Credential> findFirstByNombre(String credencial);
}