package com.eVolGreen.eVolGreen.Services.AccountService;

import com.eVolGreen.eVolGreen.DTOS.AccountDTO.PermissionCredentialDTO.CredentialDTO;
import com.eVolGreen.eVolGreen.Models.Account.PermissionCredential.Credential;
import com.eVolGreen.eVolGreen.Models.Account.PermissionCredential.PermissionCredential;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface CredentialService {

    void saveCredential(Credential credential);

    List<CredentialDTO> getCredentialsDTO();

    CredentialDTO getCredentialDTO(Long id);

    Credential findById(Long id);

//    Optional<Credential> findByName(String name);

    Credential convertToEntity(CredentialDTO credentialDTO);

    String validateCredentialDTO(CredentialDTO credentialDTO);

    void activateCredential(Long id);

    void deleteCredential(Long id);

    @Transactional
    Credential updateCredential(Long id, CredentialDTO credentialDTO, Set<PermissionCredential> newPermissions);

    void addPermissions(Long JobId, List<Long> permissionIds);

    void removePermissions(Long JobId, List<Long> permissionIds);




    void save(Credential administrador);

    List<Credential> findByNombre(String credencial);

    Optional<Credential> findFirstByNombre(String credencial);
}
