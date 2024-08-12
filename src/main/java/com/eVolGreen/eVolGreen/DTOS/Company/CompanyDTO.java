package com.eVolGreen.eVolGreen.DTOS.Company;

import com.eVolGreen.eVolGreen.DTOS.AccountDTO;
import com.eVolGreen.eVolGreen.DTOS.Employee.EmployeeDTO;
import com.eVolGreen.eVolGreen.DTOS.FeeDTO;
import com.eVolGreen.eVolGreen.Models.Company;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

public class CompanyDTO {
    private Long id;
    private String businessName;
    private String emailCompany;
    private Integer phoneCompany;
    private Integer rut;
    private String password;
    private LocalDate createdDay;
    private Boolean isActive;
    private Set<Long> employees;
    private Set<AccountDTO> accounts;
    private Set<FeeDTO> fees;

    public CompanyDTO( ) { }

    public CompanyDTO(Company company) {
        id = company.getId();
        businessName = company.getBusinessName();
        emailCompany = company.getEmailCompany();
        phoneCompany = company.getPhoneCompany();
        rut = company.getRut();
        password = company.getPassword();
        createdDay = company.getCreatedDay();
        isActive = company.getActive();
        employees = company.getEmployees().stream().map(EmployeeDTO -> new EmployeeDTO(EmployeeDTO).getId()).collect(Collectors.toSet());
        accounts = company.getAccounts().stream().map(AccountDTO -> new AccountDTO(AccountDTO)).collect(Collectors.toSet());
        fees = company.getFees().stream().map(FeeDTO -> new FeeDTO(FeeDTO)).collect(Collectors.toSet());
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

    public String getPassword() {
        return password;
    }

    public LocalDate getCreatedDay() {
        return createdDay;
    }

    public Boolean getActive() {
        return isActive;
    }

    public Set<Long> getEmployees() {
        return employees;
    }

    public Set<AccountDTO> getAccounts() {
        return accounts;
    }

    public Set<FeeDTO> getFees() {
        return fees;
    }

}
