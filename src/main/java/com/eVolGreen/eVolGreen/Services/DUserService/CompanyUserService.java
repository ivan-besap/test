package com.eVolGreen.eVolGreen.Services.DUserService;



import com.eVolGreen.eVolGreen.DTOS.UserDTO.CompanyDTO.CompanyUserDTO;
import com.eVolGreen.eVolGreen.DTOS.UserDTO.CompanyDTO.CompanyUserRegisterDTO;
import com.eVolGreen.eVolGreen.Models.Account.Fee.Fee;
import com.eVolGreen.eVolGreen.Models.User.subclassUser.CompanyUser;

import java.util.List;

public interface CompanyUserService {

    List<CompanyUserDTO> getCompanieUsersDTO();

    void saveCompanyUser(CompanyUser company);

    CompanyUser findByEmailCompanyUser(String email);

    CompanyUserDTO getCompanyDTOByEmailCurrent(String email);

    List<CompanyUserRegisterDTO> getCompanieUsersLoginDTO();

    CompanyUser findById(Long companyUserId);

    CompanyUserDTO getCompanyUserDTO(Long id);

    List<Fee> getCompanyUserFeesById(Long companyUserId);


    boolean existsByEmail(String email);
}
