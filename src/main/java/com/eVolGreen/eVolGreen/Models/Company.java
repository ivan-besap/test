package com.eVolGreen.eVolGreen.Models;

import com.eVolGreen.eVolGreen.Auth.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Company implements UserDetails {
    @Id
    @GenericGenerator(name = "native", strategy = "native")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String businessName;
    private String emailCompany;
    private Integer phoneCompany;
    private Integer rut;
    private Boolean isActive = false;
    private String password;
    private LocalDate createdDay;
    @Enumerated(EnumType.STRING)
    private Role role;
    @OneToMany(mappedBy = "company", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Employee> employees = new HashSet<>();
    @OneToMany(mappedBy = "company", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Account> accounts = new HashSet<>();


    public Company() {
    }

    public Company(String businessName, String emailCompany, Integer phoneCompany, Integer rut, String password, LocalDate createdDay, Role role) {
        this.businessName = businessName;
        this.emailCompany = emailCompany;
        this.phoneCompany = phoneCompany;
        this.rut = rut;
        this.password = password;
        this.createdDay = createdDay;
        this.role = Role.COMPANY;
    }

    public Boolean getActive() {
        return isActive;
    }
    public void setActive(Boolean active) {
        isActive = active;
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

    public Set<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
    }

    public void setRut(Integer rut) {
        this.rut = rut;
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
    public void addEmployee(Employee employee) {
        employee.setCompany(this);
        this.employees.add(employee);
    }
    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority((role.name())));
    }
    public String getPassword() {
        return password;
    }
    @Override
    public String getUsername() {
        return emailCompany;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }
}
