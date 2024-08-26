package com.eVolGreen.eVolGreen.Services.ImplementService.UserServiceImplement;

import com.eVolGreen.eVolGreen.DTOS.RegisterDTO.AdminCompanyRegisterDTO;
import com.eVolGreen.eVolGreen.DTOS.UserDTO.AdminCompanyDTO;
import com.eVolGreen.eVolGreen.Models.User.subclassUser.AdminCompanyUser;
import com.eVolGreen.eVolGreen.Repositories.AdminCompanyUserRepository;
import com.eVolGreen.eVolGreen.Services.DUserService.AdminCompanyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminCompanyUserServiceImplement implements AdminCompanyUserService {

    @Autowired
    private AdminCompanyUserRepository adminCompanyUserRepository;

    @Override
    public void saveAdminCompanyUser(AdminCompanyUser adminCompanyUser) {
        adminCompanyUserRepository.save(adminCompanyUser);
    }

    @Override
    public Optional<AdminCompanyUser> findById(Long id) {
        return adminCompanyUserRepository.findById(id);
    }

    @Override
    public AdminCompanyUser findByEmailAdminCompanyUser(String name) {
        return adminCompanyUserRepository.findByEmail(name);
    }

    @Override
    public AdminCompanyDTO getAdminCompanyRegisterDTOByEmailCurrent(String authentication) {
        return new AdminCompanyDTO(this.findByEmail(authentication));
    }

    @Override
    public AdminCompanyUser findByEmail(String email) {
        return adminCompanyUserRepository.findByEmail(email);
    }

}
