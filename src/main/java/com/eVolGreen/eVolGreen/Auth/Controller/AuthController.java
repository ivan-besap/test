package com.eVolGreen.eVolGreen.Auth.Controller;

import com.eVolGreen.eVolGreen.Auth.Request.AuthResquest;
import com.eVolGreen.eVolGreen.Auth.AuthService;
import com.eVolGreen.eVolGreen.Auth.Request.LoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Autowired
    private PasswordEncoder passwordEncoder;

//    @PostMapping("/register")
//    public ResponseEntity<AuthResponse> register(@RequestBody RegisterClientRequest request) {
//        return ResponseEntity.ok(authService.register(request));
//    }

    @PostMapping("/login")
    public ResponseEntity<AuthResquest> login(@RequestBody LoginRequest request) {
        // Agregar log para ver los datos de la solicitud
        String encryptedPassword = passwordEncoder.encode(request.getPassword());
        System.out.println("Login request AuthController: " + request.getUsername() + " " + encryptedPassword);
        return ResponseEntity.ok(authService.login(request));
    }

//    @PostMapping("/registerCompany")
//    public ResponseEntity<AuthResponse> registerCompany(@RequestBody RegisterCompanyRequest request) {
//        return ResponseEntity.ok(authService.registerCompany(request));
//    }
}
