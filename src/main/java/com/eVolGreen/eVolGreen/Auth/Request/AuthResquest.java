package com.eVolGreen.eVolGreen.Auth.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthResquest {
    private String token;
    private String role;
    private Boolean isActive;
    private String accountType;
}
