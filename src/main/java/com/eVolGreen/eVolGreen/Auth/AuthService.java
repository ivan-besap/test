package com.eVolGreen.eVolGreen.Auth;

import com.eVolGreen.eVolGreen.Auth.Jwt.JwtService;
import com.eVolGreen.eVolGreen.Configurations.WebAuthentication;
import com.eVolGreen.eVolGreen.Models.*;
import com.eVolGreen.eVolGreen.Services.AccountService;
import com.eVolGreen.eVolGreen.Services.ClientService;
import com.eVolGreen.eVolGreen.Services.CompanyService;
import com.eVolGreen.eVolGreen.Services.EmployeeService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class AuthService {
    @Autowired
    private CompanyService companyService;
    @Autowired
    private EmployeeService  employeeService;
    @Autowired
    private ClientService  clientService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private WebAuthentication webAuthentication;

    public AuthResponse login(LoginRequest request) {
        // Autenticar al usuario
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        // Cargar los detalles del usuario desde UserDetailsService
        UserDetails user = webAuthentication.userDetailsService().loadUserByUsername(request.getUsername());
        // Generar el token JWT
        String token = jwtService.getToken(user);
        // Devolver la respuesta con el token
        return AuthResponse.builder()
                .token(token)
                .build();
    }

    public AuthResponse register(RegisterClientRequest request) {
        Client client = new Client(
                request.getFirstName(),
                request.getLastName(),
                request.getRut(),
                request.getEmail(),
                request.getPhone(),
                passwordEncoder.encode(request.getPassword()),
                Role.CLIENT
        );
        clientService.saveClient(client);
        // Verificar si el cliente no es administrador
        if (!request.getEmail().contains("@admin.com")) {
            String accountNumber = "VIN" + getStringRandomClient();
            Account newAccount = new Account(accountNumber, LocalDate.now(), TypeAccounts.Client);
            client.addAccount(newAccount);
            accountService.saveAccount(newAccount);
        }
        return AuthResponse.builder()
                .token(jwtService.getToken(client))
                .build();
    }

    public AuthResponse registerCompany(RegisterCompanyRequest request) {
        Company company = new Company(
                request.getBusinessName(),
                request.getEmailCompany(),
                request.getPhoneCompany(),
                request.getRut(),
                passwordEncoder.encode(request.getPassword()),
                request.getCreatedDay(),
                Role.COMPANY
        );
        companyService.saveCompany(company);
        // Verificar si el cliente no es administrador
        if (!request.getEmailCompany().contains("@admin.com")) {
            String accountNumber = "VIN" + getStringRandomClient();
            Account newAccount = new Account(accountNumber, LocalDate.now(), TypeAccounts.Company);
            company.addAccount(newAccount);
            accountService.saveAccount(newAccount);
        }

        return AuthResponse.builder()
                .token(jwtService.getToken(company))
                .build();
    }

    int min = 00000000;
    int max = 99999999;

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public String getStringRandomClient() {
        int randomNumber = getRandomNumber(min, max);
        return String.valueOf(randomNumber);
    }


//    public AuthResponse logout(LogoutRequest request) {
//        return AuthResponse.builder()
//                .token("")
//                .build();
//    }
}
