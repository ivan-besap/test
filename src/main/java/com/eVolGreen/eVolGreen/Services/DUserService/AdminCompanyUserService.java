package com.eVolGreen.eVolGreen.Services.DUserService;

import com.eVolGreen.eVolGreen.DTOS.RegisterDTO.AdminCompanyRegisterDTO;
import com.eVolGreen.eVolGreen.DTOS.UserDTO.AdminCompanyDTO;
import com.eVolGreen.eVolGreen.Models.User.subclassUser.AdminCompanyUser;
import jakarta.validation.constraints.NotNull;

import java.util.Optional;

public interface AdminCompanyUserService {


    void saveAdminCompanyUser(AdminCompanyUser adminCompanyUser);

    Optional<AdminCompanyUser> findById(Long id);

    AdminCompanyUser findByEmailAdminCompanyUser(String name);

    AdminCompanyDTO getAdminCompanyRegisterDTOByEmailCurrent(String name);

    AdminCompanyUser findByEmail(String email);
}
