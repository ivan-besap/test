package com.eVolGreen.eVolGreen.Models;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Company {
    @Id
    @GenericGenerator(name = "native", strategy = "native")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String businessName;

    private String emailCompany;

    private Integer phoneCompany;

    private Integer rut;

    private String verifierCode;

    private Boolean enabled;
    private String password;

    private LocalDate createdDay;

    @OneToMany(mappedBy = "company", fetch = FetchType.EAGER)
    private Set<Employee> employees = new HashSet<>();

    @OneToMany(mappedBy = "company", fetch = FetchType.EAGER)
    private Set<Account> accounts = new HashSet<>();

    public Company() {
    }

    public Company(String businessName, String emailCompany, Integer phoneCompany, Integer rut, String verifierCode, Boolean enabled, String password, LocalDate createdDay) {
        this.businessName = businessName;
        this.emailCompany = emailCompany;
        this.phoneCompany = phoneCompany;
        this.rut = rut;
        this.verifierCode = verifierCode;
        this.enabled = enabled;
        this.password = password;
        this.createdDay = createdDay;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getEmailCompany() {
        return emailCompany;
    }

    public void setEmailCompany(String emailCompany) {
        this.emailCompany = emailCompany;
    }

    public Integer getPhoneCompany() {
        return phoneCompany;
    }

    public void setPhoneCompany(Integer phoneCompany) {
        this.phoneCompany = phoneCompany;
    }

    public Integer getRut() {
        return rut;
    }

    public void setRut(Integer rut) {
        this.rut = rut;
    }

    public String getVerifierCode() {
        return verifierCode;
    }

    public void setVerifierCode(String verifierCode) {
        this.verifierCode = verifierCode;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getCreatedDay() {
        return createdDay;
    }

    public void setCreatedDay(LocalDate createdDay) {
        this.createdDay = createdDay;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }
    public void addAccount(Account account) {
       account.setCompany(this);
       this.accounts.add(account);
    }

}
