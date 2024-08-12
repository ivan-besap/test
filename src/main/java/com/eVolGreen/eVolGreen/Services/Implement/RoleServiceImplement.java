package com.eVolGreen.eVolGreen.Services.Implement;

import com.eVolGreen.eVolGreen.DTOS.RoleDTO;
import com.eVolGreen.eVolGreen.Models.Permission;
import com.eVolGreen.eVolGreen.Models.Role;
import com.eVolGreen.eVolGreen.Repositories.CompanyRepository;
import com.eVolGreen.eVolGreen.Repositories.PermissionRepository;
import com.eVolGreen.eVolGreen.Repositories.RoleRepository;
import com.eVolGreen.eVolGreen.Services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoleServiceImplement implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    @Transactional
    public void saveRole(Role role) {
        roleRepository.save(role);
    }

    @Override
    public RoleDTO getRoleDTO(Long id) {
        return roleRepository.findById(id).map(RoleDTO::new).orElse(null);
    }

    @Override
    public List<RoleDTO> getRolesDTO() {
        return roleRepository.findAll()
                .stream()
                .map(RoleDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public Role findById(Long id) {
        return roleRepository.findById(id).orElse(null);
    }

    @Override
    public Optional<Role> findByName(String name) {
        return roleRepository.findByName(name);
    }

    @Override
    public Role convertToEntity(RoleDTO roleDTO) {
        Role role = new Role();
        role.setName(roleDTO.getName());
        return role;
    }

    @Override
    public String validateRoleDTO(RoleDTO roleDTO) {
        if (roleDTO.getName() == null || roleDTO.getName().isBlank()) {
            return "El nombre del rol no puede estar vacío.";
        }
        return null;
    }


    @Override
    @Transactional
    public void activateRole(Long id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role no encontrado."));
        role.setIsActive(true);
        roleRepository.save(role);
    }

    @Override
    @Transactional
    public void deleteRole(Long id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role no encontrado."));

        if (!role.getIsActive()) {
            throw new RuntimeException("El rol ya está eliminado.");
        }

        role.setIsActive(false);
        roleRepository.save(role);
    }

    @Override
    @Transactional
    public Role updateRole(Long id, RoleDTO roleDTO, Set<Permission> newPermissions) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role no encontrado."));

        role.setName(roleDTO.getName());

        // Actualizar los permisos
        role.getPermissions().clear();
        role.getPermissions().addAll(newPermissions);

        role.setIsActive(true);

        return roleRepository.save(role);
    }






    @Override
    @Transactional
    public void addPermissions(Long roleId, List<Long> permissionIds) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new RuntimeException("Role no encontrado."));
        Set<Permission> permissions = Set.copyOf(permissionRepository.findAllById(permissionIds));
        role.getPermissions().addAll(permissions);
        roleRepository.save(role);
    }

    @Override
    @Transactional
    public void removePermissions(Long roleId, List<Long> permissionIds) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new RuntimeException("Role no encontrado."));
        Set<Permission> permissions = Set.copyOf(permissionRepository.findAllById(permissionIds));
        role.getPermissions().removeAll(permissions);
        roleRepository.save(role);
    }
}
