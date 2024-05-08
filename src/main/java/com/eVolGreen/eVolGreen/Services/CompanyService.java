package com.eVolGreen.eVolGreen.Services;

import com.eVolGreen.eVolGreen.DTOS.CompanyDTO;
import com.eVolGreen.eVolGreen.Models.Company;

import java.util.List;

public interface CompanyService {

    List<CompanyDTO> getCompaniesDTO();
    void saveCompany(Company company);
    Company findByEmailCompany(String email);
}
