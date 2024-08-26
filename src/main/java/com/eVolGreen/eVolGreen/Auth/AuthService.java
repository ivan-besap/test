package com.eVolGreen.eVolGreen.Auth;

import com.eVolGreen.eVolGreen.Auth.Jwt.JwtService;
//import com.eVolGreen.eVolGreen.Configurations.Web.WebAuthentication;
//import com.eVolGreen.eVolGreen.Models.subclassUser.ClientUser;
import com.eVolGreen.eVolGreen.Auth.Request.AuthResquest;
import com.eVolGreen.eVolGreen.Auth.Request.LoginRequest;
import com.eVolGreen.eVolGreen.Configurations.Web.WebAuthentication;
import com.eVolGreen.eVolGreen.Models.User.subclassUser.AdminCompanyUser;
import com.eVolGreen.eVolGreen.Models.User.subclassUser.ClientUser;
import com.eVolGreen.eVolGreen.Models.User.subclassUser.CompanyUser;
import com.eVolGreen.eVolGreen.Models.User.subclassUser.EmployeeUser;
//import com.eVolGreen.eVolGreen.Repositories.ClientUserRepository;
import com.eVolGreen.eVolGreen.Repositories.ClientUserRepository;
import com.eVolGreen.eVolGreen.Repositories.CompanyUserRepository;
//import com.eVolGreen.eVolGreen.Repositories.EmployeeUserRepository;
import com.eVolGreen.eVolGreen.Repositories.EmployeeUserRepository;
import com.eVolGreen.eVolGreen.Services.AccountService.AccountService;
import com.eVolGreen.eVolGreen.Services.DUserService.AdminCompanyUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    @Autowired
    private CompanyUserRepository companyUserRepository;
    @Autowired
    private EmployeeUserRepository employeeUserRepository;
    @Autowired
    private ClientUserRepository clientUserRepository;
    @Autowired
    private AdminCompanyUserService adminCompanyUserService;
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

    public AuthResquest login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        UserDetails user = webAuthentication.loadUserByUsername(request.getUsername());
        String token = jwtService.getToken(user);
        String role = user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst()
                .orElse(null);

        Boolean isActive = null;
        switch (role) {
            case "COMPANY":
                CompanyUser Compañia = companyUserRepository.findByEmail(request.getUsername());
                if (Compañia != null) {
                    isActive = Compañia.getActivo();
                }
                break;
            case "EMPLOYEE":
                EmployeeUser Trabajador = employeeUserRepository.findByEmail(request.getUsername());
                if (Trabajador != null) {
                    isActive = Trabajador.getActivo();
                }
                break;
            case "CLIENT":
                ClientUser Cliente = clientUserRepository.findByEmail(request.getUsername());
                if (Cliente != null) {
                    isActive = Cliente.getActivo();
                }
                break;
            case "ADMIN_COMPANY":
                AdminCompanyUser adminCompanyUser = adminCompanyUserService.findByEmail(request.getUsername());
                if (adminCompanyUser != null) {
                    isActive = adminCompanyUser.getActivo();
                }
                break;
        }

        return AuthResquest.builder()
                .token(token)
                .role(role)
                .isActive(isActive)
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
