package com.eVolGreen.eVolGreen.Repositories;

import com.eVolGreen.eVolGreen.Models.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface PermissionRepository extends JpaRepository<Permission, Long> {
    List<Permission> findByNameIn(List<String> names);
    List<Permission> findByCompanyId(Long companyId);

}
