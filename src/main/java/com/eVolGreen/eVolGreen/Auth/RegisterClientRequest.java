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

    String firstName;
    String lastName;
    Integer rut;
    String email;
    Integer phone;
    String password;


}
