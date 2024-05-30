package com.eVolGreen.eVolGreen.Auth;

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
    String businessName;
    String emailCompany;
    Integer phoneCompany;
    Integer rut;
    Boolean isActive = false;
    String password;
    LocalDate createdDay;
}
