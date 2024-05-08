//package com.eVolGreen.eVolGreen.Configurations;
//
//import com.eVolGreen.eVolGreen.Models.Client;
//import com.eVolGreen.eVolGreen.Models.Company;
//import com.eVolGreen.eVolGreen.Models.Employee;
//import com.eVolGreen.eVolGreen.Repositories.ClientRepository;
//import com.eVolGreen.eVolGreen.Repositories.CompanyRepository;
//import com.eVolGreen.eVolGreen.Repositories.EmployeeRepository;
//import com.eVolGreen.eVolGreen.Services.ClientService;
//import com.eVolGreen.eVolGreen.Services.CompanyService;
//import com.eVolGreen.eVolGreen.Services.EmployeeService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
//import org.springframework.security.core.authority.AuthorityUtils;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.factory.PasswordEncoderFactories;
//import org.springframework.security.crypto.password.PasswordEncoder;
//@Configuration
//public class WebAuthentication extends GlobalAuthenticationConfigurerAdapter {
//    @Autowired
//    private ClientService clientService;
//    @Autowired
//    private EmployeeService employeeService;
//    @Autowired
//    private CompanyService companyService;
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
//    }
//
//    @Override
//    public void init(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(username -> {
//            Client client = clientService.findByEmail(username);
//            if (client != null) {
//                return new User(client.getEmail(), client.getPassword(),
//                        AuthorityUtils.createAuthorityList("CLIENT"));
//            }
//
//            Employee employee = employeeService.
//            if (employee != null) {
//                return new User(employee.getEmail(), employee.getPassword(),
//                        AuthorityUtils.createAuthorityList("EMPLOYEE"));
//            }
//
//            Company company = companyService.findByEmailCompany(username);
//            if (company != null) {
//                return new User(company.getEmailCompany(), company.getPassword(),
//                        AuthorityUtils.createAuthorityList("COMPANY"));
//            }
//
//            throw new UsernameNotFoundException("Unknown user: " + username);
//        }).passwordEncoder(passwordEncoder());
//    }
//}
//
//
//
