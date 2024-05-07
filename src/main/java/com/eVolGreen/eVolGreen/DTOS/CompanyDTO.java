package com.eVolGreen.eVolGreen.DTOS;

import com.eVolGreen.eVolGreen.Models.Account;
import com.eVolGreen.eVolGreen.Models.Company;
import com.eVolGreen.eVolGreen.Models.Employee;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class CompanyDTO {
    private Long id;
    private String businessName;
    private String emailCompany;
    private Integer phoneCompany;
    private Integer rut;
    private String verifierCode;
    private Boolean enabled;
    private String password;
    private LocalDate createdDay;
    private Set<Long> employees;
    private Set<AccountDTO> accounts;


    public CompanyDTO(Company company) {
        id = company.getId();
        businessName = company.getBusinessName();
        emailCompany = company.getEmailCompany();
        phoneCompany = company.getPhoneCompany();
        rut = company.getRut();
        verifierCode = company.getVerifierCode();
        enabled = company.getEnabled();
        password = company.getPassword();
        createdDay = company.getCreatedDay();
        employees = company.getEmployees().stream().map(EmployeeDTO -> new EmployeeDTO(EmployeeDTO).getId()).collect(Collectors.toSet());
        accounts = company.getAccounts().stream().map(AccountDTO -> new AccountDTO(AccountDTO)).collect(Collectors.toSet());
    }


    public Long getId() {
        return id;
    }

    public String getBusinessName() {
        return businessName;
    }

    public String getEmailCompany() {
        return emailCompany;
    }

    public Integer getPhoneCompany() {
        return phoneCompany;
    }

    public Integer getRut() {
        return rut;
    }

    public String getVerifierCode() {
        return verifierCode;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public String getPassword() {
        return password;
    }

    public LocalDate getCreatedDay() {
        return createdDay;
    }


    public Set<Long> getEmployees() {
        return employees;
    }

    public Set<AccountDTO> getAccounts() {
        return accounts;
    }
}
