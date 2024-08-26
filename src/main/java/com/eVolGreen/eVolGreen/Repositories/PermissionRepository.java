package com.eVolGreen.eVolGreen.Repositories;

import com.eVolGreen.eVolGreen.Models.Account.PermissionCredential.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RepositoryRestResource
public interface PermissionRepository extends JpaRepository<Permission, Long> {


    Optional<Permission> findByNombre(String nombre);


    List<Permission> findByCuentaCompañia_Id(Long companyId);

    Set<Permission> findAllByNombreIn(Set<String> chargerCreate);
}
