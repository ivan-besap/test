package com.eVolGreen.eVolGreen.Repositories;

import com.eVolGreen.eVolGreen.Models.Account.Permission.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;
import java.util.Set;

@RepositoryRestResource
public interface PermissionRepository extends JpaRepository<Permission, Long> {

    // Buscar un permiso por su nombre
    Optional<Permission> findByNombre(String nombre);

    // Buscar permisos por un conjunto de nombres
    Set<Permission> findAllByNombreIn(Set<String> nombres);
}
