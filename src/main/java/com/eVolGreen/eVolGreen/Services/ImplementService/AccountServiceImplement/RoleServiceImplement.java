package com.eVolGreen.eVolGreen.Services.ImplementService.AccountServiceImplement;

import com.eVolGreen.eVolGreen.DTOS.AccountDTO.FeeDTO.FeeDTO;
import com.eVolGreen.eVolGreen.DTOS.AccountDTO.RoleDTO;
import com.eVolGreen.eVolGreen.Models.Account.Account;
import com.eVolGreen.eVolGreen.Models.Account.Empresa;
import com.eVolGreen.eVolGreen.Models.Account.Fee.Fee;
import com.eVolGreen.eVolGreen.Models.User.Role;
import com.eVolGreen.eVolGreen.Repositories.RoleRepository;
import com.eVolGreen.eVolGreen.Services.AccountService.AccountService;
import com.eVolGreen.eVolGreen.Services.AccountService.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoleServiceImplement implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AccountService accountService;

    @Override
    public Optional<Role> findByNombre(String name) {
        return roleRepository.findByNombre(name);
    }

    @Override
    public void saveRole(Role role) {
        roleRepository.save(role);
    }

    @Override
    public Optional<Role> findById(Long id) {
        return roleRepository.findById(id);
    }

    @Override
    public List<RoleDTO> getRolesDTO(String email) {
        Optional<Account> account = accountService.findByEmail(email);
        if (account.isPresent()) {
            Empresa empresa = account.get().getEmpresa();
            if (empresa != null) {
                return roleRepository.findByEmpresa(empresa)
                        .stream()
                        .filter(Role::getActivo)
                        .map(RoleDTO::new)
                        .collect(Collectors.toList());
            }
        }
        return Collections.emptyList();
    }
}
