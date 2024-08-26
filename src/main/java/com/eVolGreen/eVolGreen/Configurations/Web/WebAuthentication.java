package com.eVolGreen.eVolGreen.Configurations.Web;

import com.eVolGreen.eVolGreen.Models.User.subclassUser.AdminCompanyUser;
import com.eVolGreen.eVolGreen.Models.User.subclassUser.ClientUser;
import com.eVolGreen.eVolGreen.Models.User.subclassUser.CompanyUser;
import com.eVolGreen.eVolGreen.Models.User.subclassUser.EmployeeUser;
import com.eVolGreen.eVolGreen.Repositories.AdminCompanyUserRepository;
import com.eVolGreen.eVolGreen.Repositories.ClientUserRepository;
import com.eVolGreen.eVolGreen.Repositories.CompanyUserRepository;
import com.eVolGreen.eVolGreen.Repositories.EmployeeUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebAuthentication {

    @Autowired
    private ClientUserRepository clientUserRepository;

    @Autowired
    private EmployeeUserRepository employeeUserRepository;

    @Autowired
    private CompanyUserRepository companyUserRepository;

    @Autowired
    private AdminCompanyUserRepository adminCompanyUserRepository;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return this::loadUserByUsername;
    }

    public UserDetails loadUserByUsername(String username) {
        System.out.println("WebAuthentication: Loading user details for " + username);

        // Buscar en cada tipo de usuario
        AdminCompanyUser admin = adminCompanyUserRepository.findByEmail(username);
        if (admin != null) {
            return new User(admin.getEmail(), admin.getPassword(),
                    AuthorityUtils.createAuthorityList("ADMIN_COMPANY"));
        }

        CompanyUser company = companyUserRepository.findByEmail(username);
        if (company != null) {
            return new User(company.getEmail(), company.getPassword(),
                    AuthorityUtils.createAuthorityList("COMPANY"));
        }

        EmployeeUser employee = employeeUserRepository.findByEmail(username);
        if (employee != null) {
            return new User(employee.getEmail(), employee.getPassword(),
                    AuthorityUtils.createAuthorityList("EMPLOYEE"));
        }

        ClientUser client = clientUserRepository.findByEmail(username);
        if (client != null) {
            return new User(client.getEmail(), client.getPassword(),
                    AuthorityUtils.createAuthorityList("CLIENT"));
        }

        // Si no se encuentra el usuario, lanzar una excepción
        throw new UsernameNotFoundException("Usuario no encontrado: " + username);
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
