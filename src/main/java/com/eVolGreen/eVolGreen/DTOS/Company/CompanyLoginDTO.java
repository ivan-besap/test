package com.eVolGreen.eVolGreen.DTOS.Company;

import com.eVolGreen.eVolGreen.Models.Company;

public class CompanyLoginDTO {
    private Long id;
    private String businessName;
    private String emailCompany;
    private Boolean isActive = false;


    public CompanyLoginDTO(Company company) {
        id = company.getId();
        businessName = company.getBusinessName();
        emailCompany = company.getEmailCompany();
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

    public Boolean getActive() {
        return isActive;
    }
}
