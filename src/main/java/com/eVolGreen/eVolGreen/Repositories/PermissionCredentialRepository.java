package com.eVolGreen.eVolGreen.Repositories;


import com.eVolGreen.eVolGreen.Models.Account.PermissionCredential.Permission;
import com.eVolGreen.eVolGreen.Models.Account.PermissionCredential.PermissionCredential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource
public interface PermissionCredentialRepository extends JpaRepository<PermissionCredential, Long> {

}
