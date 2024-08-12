package com.eVolGreen.eVolGreen.Configurations;

import com.eVolGreen.eVolGreen.Auth.Jwt.JwtAuthenticationFilter;
import com.eVolGreen.eVolGreen.Models.Client;
import com.eVolGreen.eVolGreen.Models.Company;
import com.eVolGreen.eVolGreen.Models.Employee;
import com.eVolGreen.eVolGreen.Services.ClientService;
import com.eVolGreen.eVolGreen.Services.CompanyService;
import com.eVolGreen.eVolGreen.Services.EmployeeService;
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
    private ClientService clientService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private CompanyService companyService;

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
        Client client = clientService.findByEmail(username);
        if (client != null) {
        //    System.out.println("WebAuthentication: User found as Client");
            return new User(client.getEmail(), client.getPassword(),
                    AuthorityUtils.createAuthorityList("CLIENT"));
        }

        Employee employee = employeeService.findByEmail(username);
        if (employee != null) {
            System.out.println("WebAuthentication: User found as Employee");
            return new User(employee.getEmail(), employee.getPassword(),
                    AuthorityUtils.createAuthorityList(
                            employee.getRoles().stream()
                                    .map(role -> "EMPLOYEE_" + role.getName().toUpperCase())
                                    .toArray(String[]::new)
                    ));
        }

        Company company = companyService.findByEmailCompany(username);
        if (company != null) {
            System.out.println("WebAuthentication: User found as Company");
            return new User(company.getEmailCompany(), company.getPassword(),
                    AuthorityUtils.createAuthorityList("COMPANY"));
        }

        System.out.println("WebAuthentication: Unknown user: " + username);
        throw new UsernameNotFoundException("Unknown user: " + username);
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
