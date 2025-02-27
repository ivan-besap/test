package com.eVolGreen.eVolGreen.Auth.Controller;

import com.eVolGreen.eVolGreen.Auth.Request.AuthResquest;
import com.eVolGreen.eVolGreen.Auth.AuthService;
import com.eVolGreen.eVolGreen.Auth.Request.LoginRequest;
import com.eVolGreen.eVolGreen.DTOS.AccountDTO.RegisterRequestDTO;
import com.eVolGreen.eVolGreen.Models.Account.Account;
import com.eVolGreen.eVolGreen.Models.Account.Empresa;
import com.eVolGreen.eVolGreen.Models.Account.Permission.Permission;
import com.eVolGreen.eVolGreen.Models.Account.TypeOfAccount.TypeAccounts;
import com.eVolGreen.eVolGreen.Models.User.Role;
import com.eVolGreen.eVolGreen.Services.AccountService.AccountService;
import com.eVolGreen.eVolGreen.Services.AccountService.EmailService;
import com.eVolGreen.eVolGreen.Services.AccountService.EmpresaService;
import com.eVolGreen.eVolGreen.Services.AccountService.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    @Autowired
    private AuthService authService;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private EmpresaService empresaService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private RoleService roleService;


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

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequestDTO request) {

        if (accountService.findByEmail(request.getEmail()).isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ya existe un usuario con ese email");
        }


        Empresa empresa = new Empresa();
        empresa.setNombre(request.getNombreEmpresa());
        empresaService.saveEmpresa(empresa);


        Role globalAdminRole = roleService.findById(1L)
                .orElseThrow(() -> new RuntimeException("Rol 'ADMIN' no encontrado"));


        List<Permission> permisosClonados = globalAdminRole.getPermisos()
                .stream()
                .collect(Collectors.toList());


        Role companyAdminRole = new Role();
        companyAdminRole.setNombre("ADMIN");
        companyAdminRole.setPermisos(permisosClonados);
        companyAdminRole.setEmpresa(empresa);
        roleService.saveRole(companyAdminRole);


        String numeroDeCuenta = "admin-" + getStringRandomNumber();
        String encriptedPassword = passwordEncoder.encode(request.getPassword());

        Account account = new Account();
        account.setNumeroDeCuenta(numeroDeCuenta);
        account.setNombreCuenta("Cuenta Principal de " + request.getNombreEmpresa());
        account.setFechaDeCreacion(LocalDate.now());
        account.setEmail(request.getEmail());
        account.setPassword(encriptedPassword);
        account.setActivo(false);
        account.setTipoCuenta(TypeAccounts.COMPANY);
        account.setRol(companyAdminRole);
        account.setTelefono(request.getTelefono());
        account.setRut(request.getRut());
        account.setEmpresa(empresa);
        account.setNombre(request.getNombre());
        account.setApellidoPaterno(request.getApellidoPaterno());
        account.setApellidoMaterno(request.getApellidoMaterno());

        accountService.saveAccount(account);

        String verificationUrl = "http://localhost:8088/api/verify-account?email=" + account.getEmail();
        emailService.sendVerificationEmail(account.getEmail(), verificationUrl);

        return ResponseEntity.status(HttpStatus.CREATED).body("Registro exitoso");
    }

    private String getStringRandomNumber() {
        int min = 10000000;
        int max = 99999999;
        int randomNumber = (int) ((Math.random() * (max - min)) + min);
        return String.valueOf(randomNumber);
    }

//    @PostMapping("/registerCompany")
//    public ResponseEntity<AuthResponse> registerCompany(@RequestBody RegisterCompanyRequest request) {
//        return ResponseEntity.ok(authService.registerCompany(request));
//    }
}
