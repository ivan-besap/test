package com.eVolGreen.eVolGreen.Services.ImplementService.UserServiceImplement;


import com.eVolGreen.eVolGreen.DTOS.UserDTO.CompanyDTO.CompanyUserDTO;
import com.eVolGreen.eVolGreen.DTOS.UserDTO.CompanyDTO.CompanyUserRegisterDTO;
import com.eVolGreen.eVolGreen.Models.Account.Fee.Fee;
import com.eVolGreen.eVolGreen.Models.User.subclassUser.CompanyUser;
import com.eVolGreen.eVolGreen.Repositories.CompanyUserRepository;
import com.eVolGreen.eVolGreen.Services.DUserService.CompanyUserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyUserServiceImplement implements CompanyUserService {
    @Autowired
    private CompanyUserRepository companyUserRepository;

    @Override
    public List<CompanyUserDTO> getCompanieUsersDTO() {

        return companyUserRepository.findAll()
                .stream()
                .map(CompanyUserDTO::new)
                .collect(Collectors.toList());
    }
    @Override
    public void saveCompanyUser(CompanyUser company) {
        companyUserRepository.save(company);
    }

    @Override
    public CompanyUser findByEmailCompanyUser(String email) {
        return companyUserRepository.findByEmail(email);
    }

    @Override
    public CompanyUserDTO getCompanyDTOByEmailCurrent(String email) {
        CompanyUser company = companyUserRepository.findByEmail(email);
        if (company != null) {
            return new CompanyUserDTO(company);
        } else {
            return null;
        }
    }

    @Override
    public List<CompanyUserRegisterDTO> getCompanieUsersLoginDTO() {
        return companyUserRepository.findAll()
                .stream()
                .map(CompanyUserRegisterDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public CompanyUser findById(Long companyId) {
        return companyUserRepository.findById(companyId).orElse(null);
    }

    @Override
    public CompanyUserDTO getCompanyUserDTO(Long id) {
        return new CompanyUserDTO(this.findById(id));
    }

    @Override
    public List<Fee> getCompanyUserFeesById(Long companyId) {
        CompanyUser company = companyUserRepository.findById(companyId).orElse(null);
        return company != null ? company.getTarifas()
                .stream()
                .collect(Collectors.toList()) : null;
    }

    @Override
    public boolean existsByEmail(String email) {
        return companyUserRepository.existsByEmail(email);
    }

}
