package com.eVolGreen.eVolGreen.Services.ImplementService.AccountServiceImplement;

import com.eVolGreen.eVolGreen.DTOS.AccountDTO.PermissionCredentialDTO.CredentialDTO;
import com.eVolGreen.eVolGreen.Models.Account.PermissionCredential.Credential;
import com.eVolGreen.eVolGreen.Models.Account.PermissionCredential.Permission;
import com.eVolGreen.eVolGreen.Models.Account.PermissionCredential.PermissionCredential;
import com.eVolGreen.eVolGreen.Repositories.CompanyUserRepository;
import com.eVolGreen.eVolGreen.Repositories.PermissionRepository;
import com.eVolGreen.eVolGreen.Repositories.CredentialRepository;
import com.eVolGreen.eVolGreen.Services.AccountService.CredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CredentialServiceImplement implements CredentialService {

    @Autowired
    private CredentialRepository credentialRepository;

    @Autowired
    private CompanyUserRepository companyUserRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    @Transactional
    public void saveCredential(Credential credential) {
        credentialRepository.save(credential);
    }

    @Override
    public CredentialDTO getCredentialDTO(Long id) {
        return credentialRepository.findById(id).map(CredentialDTO::new).orElse(null);
    }

    @Override
    public List<CredentialDTO> getCredentialsDTO() {
        return credentialRepository.findAll()
                .stream()
                .map(CredentialDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public Credential findById(Long id) {
        return credentialRepository.findById(id).orElse(null);
    }

//    @Override
//    public Optional<Credential> findByName(String name) {
//        return credentialRepository.findByCredential(name);
//    }

    @Override
    public Credential convertToEntity(CredentialDTO credentialDTO) {
        Credential credential = new Credential();
        credential.setNombre(credentialDTO.getNombre());
        return credential;
    }

    @Override
    public String validateCredentialDTO(CredentialDTO credentialDTO) {
        if (credentialDTO.getNombre() == null || credentialDTO.getNombre().isBlank()) {
            return "El nombre del rol no puede estar vacío.";
        }
        return null;
    }


    @Override
    @Transactional
    public void activateCredential(Long id) {
        Credential credential = credentialRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role no encontrado."));
        credential.setActivo(true);
        credentialRepository.save(credential);
    }

    @Override
    @Transactional
    public void deleteCredential(Long id) {
        Credential credential = credentialRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role no encontrado."));

        if (!credential.isActivo()) {
            throw new RuntimeException("El rol ya está eliminado.");
        }

        credential.setActivo(false);
        credentialRepository.save(credential);
    }

    @Override
    @Transactional
    public Credential updateCredential(Long id, CredentialDTO credentialDTO, Set<PermissionCredential> newPermissions) {
        Credential credential = credentialRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role no encontrado."));

        credential.setNombre(credentialDTO.getNombre());

        // Actualizar los permisos
        credential.getPermisonCredencial().clear();
        credential.getPermisonCredencial().addAll(newPermissions);

        credential.setActivo(true);

        return credentialRepository.save(credential);
    }

    @Override
    @Transactional
    public void addPermissions(Long CredentialId, List<Long> permissionIds) {
        Credential credential = credentialRepository.findById(CredentialId)
                .orElseThrow(() -> new RuntimeException("Role no encontrado."));
        Set<Permission> permissions = Set.copyOf(permissionRepository.findAllById(permissionIds));
//        credential.getPermisonCredencial().addAll(permissions);
        credentialRepository.save(credential);
    }

    @Override
    @Transactional
    public void removePermissions(Long CredentialId, List<Long> permissionIds) {
        Credential credential = credentialRepository.findById(CredentialId)
                .orElseThrow(() -> new RuntimeException("Credencial no encontrada."));
        Set<Permission> permissions = Set.copyOf(permissionRepository.findAllById(permissionIds));
        credential.getPermisonCredencial().removeAll(permissions);
        credentialRepository.save(credential);
    }



    @Override
    public void save(Credential administrador) {

        credentialRepository.save(administrador);

    }

    @Override
    public List<Credential> findByNombre(String credencial) {
        return credentialRepository.findAllByNombre(credencial);
    }


    @Override
    public Optional<Credential> findFirstByNombre(String credencial) {

        return credentialRepository.findFirstByNombre(credencial);
    }

//    public Credential createCredentialForEmployee(String name, String otherData) {
//        Optional<Credential> existingCredential = findByName(name);
//
//        if (existingCredential.isPresent()) {
//            // Manejar el caso donde la credencial ya existe
//            throw new IllegalArgumentException("Credential with name " + name + " already exists.");
//        }
//
//        // Crear y guardar una nueva credencial
//        Credential credential = new Credential();
//        credential.setNombre(name);
//        // establecer otros campos...
//
//        return credentialRepository.save(credential);
//    }
}
