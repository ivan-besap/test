package com.eVolGreen.eVolGreen.Auth;

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
    private Integer rut;
    private String email;
    private Integer phone;
    private String password;
    private Boolean isActive;


}
