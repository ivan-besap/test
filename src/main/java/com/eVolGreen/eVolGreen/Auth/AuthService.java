package com.eVolGreen.eVolGreen.Auth;

import com.eVolGreen.eVolGreen.Auth.Jwt.JwtService;
import com.eVolGreen.eVolGreen.Configurations.WebAuthentication;
import com.eVolGreen.eVolGreen.Models.*;
import com.eVolGreen.eVolGreen.Services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
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
    private EmployeeService employeeService;
    @Autowired
    private ClientService clientService;
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
        //    System.out.println("AuthService: Starting authentication process for " + request.getUsername());

        // Autenticar al usuario con el AuthenticationManager
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        // Cargar los detalles del usuario desde UserDetailsService solo una vez
        //    System.out.println("AuthService: Loading user details for " + request.getUsername());
        UserDetails user = webAuthentication.loadUserByUsername(request.getUsername());

        // Generar el token JWT para el usuario autenticado
        String token = jwtService.getToken(user);

        // Obtener el rol del usuario
        String role = user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst()
                .orElse(null);

        //    System.out.println("AuthService: Authentication successful for " + request.getUsername());

        Boolean isActive = null;
        if ("COMPANY".equals(role)) {
            Company company = companyService.findByEmailCompany(request.getUsername());
            isActive = company.getActive();
        } else if ("EMPLOYEE".equals(role)) {
            Employee employee = employeeService.findByEmail(request.getUsername());
            isActive = employee.getActive();
        } else if ("CLIENT".equals(role)) {
            Client client = clientService.findByEmail(request.getUsername());
            isActive = client.getActive();
        }

        // Devolver la respuesta con el token, el rol y el estado activo
        return AuthResponse.builder()
                .token(token)
                .role(role)
                .isActive(isActive)
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

        client.setActive(false);

        clientService.saveClient(client);
        if (!request.getEmail().contains("@admin.com")) {
            String accountNumber = "VIN" + getStringRandomClient();
            Account newAccount = new Account(accountNumber, LocalDate.now(), TypeAccounts.Client);
            client.addAccount(newAccount);
            accountService.saveAccount(newAccount);
        }

        String role = client.getRole().name();
        return AuthResponse.builder()
                .token(jwtService.getToken(client))
                .role(role)
                .isActive(client.getActive())
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

        company.setActive(false);

        companyService.saveCompany(company);
        if (!request.getEmailCompany().contains("@admin.com")) {
            String accountNumber = "VIN" + getStringRandomClient();
            Account newAccount = new Account(accountNumber, LocalDate.now(), TypeAccounts.Company);
            company.addAccount(newAccount);
            accountService.saveAccount(newAccount);
        }

        String role = company.getRole().name();
        return AuthResponse.builder()
                .token(jwtService.getToken(company))
                .role(role)
                .isActive(company.getActive())
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
}
