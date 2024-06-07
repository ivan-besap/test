package com.eVolGreen.eVolGreen.Auth;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterClientRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/registerCompany")
    public ResponseEntity<AuthResponse> registerCompany(@RequestBody RegisterCompanyRequest request) {
        return ResponseEntity.ok(authService.registerCompany(request));
    }

//    @PostMapping ("/logout")
//    public ResponseEntity<AuthResponse> logout(@RequestBody LogoutRequest request) {
//        return ResponseEntity.ok(authService.logout(request));
//    }
}
