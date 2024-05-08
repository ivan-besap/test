package com.eVolGreen.eVolGreen.Services.Implement;

import com.eVolGreen.eVolGreen.DTOS.CompanyDTO;
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
}
