package com.eVolGreen.eVolGreen.Services;

import com.eVolGreen.eVolGreen.DTOS.RoleDTO;
import com.eVolGreen.eVolGreen.Models.Role;
import com.eVolGreen.eVolGreen.Models.Permission;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface RoleService {

    void saveRole(Role role);

    List<RoleDTO> getRolesDTO();

    RoleDTO getRoleDTO(Long id);

    Role findById(Long id);

    Optional<Role> findByName(String name);

    Role convertToEntity(RoleDTO roleDTO);

    String validateRoleDTO(RoleDTO roleDTO);

    void activateRole(Long id);

    void deleteRole(Long id);

    @Transactional
    Role updateRole(Long id, RoleDTO roleDTO, Set<Permission> newPermissions);

    void addPermissions(Long roleId, List<Long> permissionIds);

    void removePermissions(Long roleId, List<Long> permissionIds);
}
