package com.eVolGreen.eVolGreen.Services;

import com.eVolGreen.eVolGreen.DTOS.Company.CompanyDTO;
import com.eVolGreen.eVolGreen.DTOS.Company.CompanyLoginDTO;
import com.eVolGreen.eVolGreen.Models.Company;

import java.util.List;

public interface CompanyService {

    List<CompanyDTO> getCompaniesDTO();
    void saveCompany(Company company);
    Company findByEmailCompany(String email);

    CompanyDTO getCompanyDTOByEmailCurrent(String email);

    List<CompanyLoginDTO> getCompaniesLoginDTO();
}
