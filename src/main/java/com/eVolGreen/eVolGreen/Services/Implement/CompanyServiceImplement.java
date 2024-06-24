package com.eVolGreen.eVolGreen.Services.Implement;


import com.eVolGreen.eVolGreen.DTOS.Company.CompanyDTO;
import com.eVolGreen.eVolGreen.DTOS.Company.CompanyLoginDTO;
import com.eVolGreen.eVolGreen.Models.Company;
import com.eVolGreen.eVolGreen.Repositories.CompanyRepository;
import com.eVolGreen.eVolGreen.Services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImplement implements CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public List<CompanyDTO> getCompaniesDTO() {

        return companyRepository.findAll()
                .stream()
                .map(CompanyDTO::new)
                .collect(Collectors.toList());
    }
    @Override
    public void saveCompany(Company company) {
        companyRepository.save(company);
    }

    @Override
    public Company findByEmailCompany(String email) {
        return companyRepository.findByEmailCompany(email);
    }

    @Override
    public CompanyDTO getCompanyDTOByEmailCurrent(String email) {
        Company company = companyRepository.findByEmailCompany(email);
        if (company != null) {
            return new CompanyDTO(company);
        } else {
            return null;
        }
    }

    @Override
    public List<CompanyLoginDTO> getCompaniesLoginDTO() {
        return companyRepository.findAll()
                .stream()
                .map(CompanyLoginDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public Company findById(Long companyId) {
        return companyRepository.findById(companyId).orElse(null);
    }

    @Override
    public CompanyDTO getCompanyDTO(Long id) {
        return new CompanyDTO(this.findById(id));
    }
}
