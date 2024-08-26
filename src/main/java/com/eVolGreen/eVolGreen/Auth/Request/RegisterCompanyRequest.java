package com.eVolGreen.eVolGreen.Auth.Request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterCompanyRequest {
    private String businessName;
    private String emailCompany;
    private Integer phoneCompany;
    private Integer rut;
    private Boolean isActive;
    private String password;
    private LocalDate createdDay;
}
