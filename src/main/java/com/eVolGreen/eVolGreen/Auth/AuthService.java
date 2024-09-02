package com.eVolGreen.eVolGreen.Auth;

import com.eVolGreen.eVolGreen.Auth.Jwt.JwtService;
//import com.eVolGreen.eVolGreen.Configurations.Web.WebAuthentication;
//import com.eVolGreen.eVolGreen.Models.subclassUser.ClientUser;
import com.eVolGreen.eVolGreen.Auth.Request.AuthResquest;
import com.eVolGreen.eVolGreen.Auth.Request.LoginRequest;
import com.eVolGreen.eVolGreen.Configurations.Web.WebAuthentication;
import com.eVolGreen.eVolGreen.Models.Account.Account;
//import com.eVolGreen.eVolGreen.Repositories.ClientUserRepository;
import com.eVolGreen.eVolGreen.Repositories.AccountRepository;
//import com.eVolGreen.eVolGreen.Repositories.EmployeeUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class AuthService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private WebAuthentication webAuthentication;

    public AuthResquest login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        Account account = accountRepository.findByEmail(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Obtener el nombre del rol desde la entidad Role
        String roleName = account.getRol().getNombre();

        // Crear una lista de permisos desde la entidad Role
        List<SimpleGrantedAuthority> authorities = account.getRol().getPermisos().stream()
                .map(permisoId -> new SimpleGrantedAuthority("PERMISO_" + permisoId))
                .collect(Collectors.toList());

        // Crear el token JWT
        String token = jwtService.getToken(new org.springframework.security.core.userdetails.User(
                account.getEmail(),
                account.getPassword(),
                authorities));

        // Incluir el tipo de cuenta en la respuesta
        String accountType = account.getTipoCuenta().name();

        return AuthResquest.builder()
                .token(token)
                .role(roleName)
                .isActive(account.getActivo())
                .accountType(accountType) // Añadir el tipo de cuenta a la respuesta
                .build();
    }
//    public AuthResponse register(RegisterClientRequest request) {
//        ClientUser client = new ClientUser(
//                request.getEmail(),
//                passwordEncoder.encode(request.getPassword()),
//                request.getFirstName(),
//                request.getLastName(),
//                request.getPhoneNumber(),
//                request.getRut(),
//                Role.CLIENT
//        );
//
//        client.setActive(false);
//        clientUserService.saveClient(client);
//
//        if (!request.getEmail().contains("@admin.com")) {
//            String accountNumber = "VIN" + getStringRandomClient();
//            AccountClient newAccount = new AccountClient(accountNumber, LocalDate.now(), TypeAccounts.CLIENT, true, new Location());
//            client.addAccount(newAccount);
//            accountService.saveAccount(newAccount);
//        }
//
//        String role = client.getRole().name();
//        return AuthResponse.builder()
//                .token(jwtService.getToken(client))
//                .role(role)
//                .isActive(client.getActive())
//                .build();
//    }
//
//    public AuthResponse registerCompany(RegisterCompanyRequest request) {
//        CompanyUser company = new CompanyUser(
//                request.getEmailCompany(),
//                passwordEncoder.encode(request.getPassword()),
//                request.getBusinessName(),
//                Role.COMPANY
//        );
//
//        company.setActive(false);
//        companyUserService.saveCompany(company);
//
//        if (!request.getEmailCompany().contains("@admin.com")) {
//            String accountNumber = "VIN" + getStringRandomClient();
//            AccountCompany newAccount = new AccountCompany(accountNumber, LocalDate.now(), TypeAccounts.COMPANY, true, new Location());
//            company.addAccount(newAccount);
//            accountService.saveAccount(newAccount);
//        }
//
//        String role = company.getRole().name();
//        return AuthResponse.builder()
//                .token(jwtService.getToken(company))
//                .role(role)
//                .isActive(company.getActive())
//                .build();
//    }
//
//    private static final List<String> DEFAULT_PERMISSION_NAMES = Arrays.asList(
//            "charger_create", "charger_delete", "charger_edit",
//            "charging_station_view", "ocpp_charger_commands", "ocpp_connector_commands",
//            "ocpp_charging_station_commands", "driver_create", "driver_delete",
//            "driver_edit", "driver_disable", "driver_view", "charger_settings_view",
//            "power_control_create", "power_control_edit", "power_control_view",
//            "dashboard_view", "scheduled_disables_create", "scheduled_disables_edit",
//            "scheduled_disables_view", "ocpp_edit_settings", "ocpp_view_settings",
//            "peak_shaving_create", "peak_shaving_edit", "peak_shaving_view",
//            "charge_records_view", "roles_delete", "roles_create", "roles_edit",
//            "roles_view", "charging_station_view", "locations_create", "locations_view",
//            "electric_firefighter", "chargers_by_user", "chargers_by_terminal",
//            "client_view", "employee_view"
//    );
//
//    int min = 00000000;
//    int max = 99999999;
//
//    public int getRandomNumber(int min, int max) {
//        return (int) ((Math.random() * (max - min)) + min);
//    }
//
//    public String getStringRandomClient() {
//        int randomNumber = getRandomNumber(min, max);
//        return String.valueOf(randomNumber);
//    }
}
