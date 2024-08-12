package com.eVolGreen.eVolGreen.DTOS.Employee;

import com.eVolGreen.eVolGreen.DTOS.AccountDTO;
import com.eVolGreen.eVolGreen.Models.Company;
import com.eVolGreen.eVolGreen.Models.Employee;
import com.eVolGreen.eVolGreen.Models.TypeAccounts;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

public class EmployeeDTO {
    private Long id;
    private String name;
    private String firstSurname;
    private String lastSurname;
    private String email;
    private String password;
    private LocalDate createdDay;
    private Boolean isActive = false;
    private String company;
    private Set<AccountDTO> accounts;
    private Set<String> roles;

    public EmployeeDTO() {
    }

    public EmployeeDTO(Employee employee) {
        id = employee.getId();
        name = employee.getName();
        firstSurname = employee.getFirstSurname();
        lastSurname = employee.getLastSurname();
        email = employee.getEmail();
        password = employee.getPassword();
        createdDay = employee.getCreatedDay();
        isActive = employee.getActive();
        company = employee.getCompany().getBusinessName();
        accounts = employee.getCompany().getAccounts().stream()
                .filter(account -> account.getTypeAccounts() == TypeAccounts.Employee)
                .map(AccountDTO::new)
                .collect(Collectors.toSet());
        roles = employee.getRoles().stream()
                .map(role -> role.getName())
                .collect(Collectors.toSet());
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFirstSurname() {
        return firstSurname;
    }

    public String getLastSurname() {
        return lastSurname;
    }

    public String getEmail() {
        return email;
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

    public String getCompany() {
        return company;
    }

    public Set<AccountDTO> getAccounts() {
        return accounts;
    }

    public Set<String> getRoles() {
        return roles;
    }
}
