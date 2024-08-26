package com.eVolGreen.eVolGreen.Auth.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterClientRequest {

    private String firstName;
    private String lastName;
    private String rut;
    private String email;
    private String phoneNumber;
    private String password;
    private Boolean isActive;
}
