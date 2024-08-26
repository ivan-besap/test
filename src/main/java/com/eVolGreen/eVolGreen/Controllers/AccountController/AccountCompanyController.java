package com.eVolGreen.eVolGreen.Controllers.AccountController;


import com.eVolGreen.eVolGreen.DTOS.UserDTO.EmployeeDTO.EmployeeRegisterDTO;
import com.eVolGreen.eVolGreen.Models.Account.PermissionCredential.Credential;
import com.eVolGreen.eVolGreen.Models.Account.TypeOfAccount.AccountCompany;
import com.eVolGreen.eVolGreen.Models.Account.TypeOfAccount.AccountEmployee;
import com.eVolGreen.eVolGreen.Models.Account.TypeOfAccount.TypeAccounts;
import com.eVolGreen.eVolGreen.Models.User.Role;
import com.eVolGreen.eVolGreen.Models.User.subclassUser.CompanyUser;
import com.eVolGreen.eVolGreen.Models.User.subclassUser.EmployeeUser;
import com.eVolGreen.eVolGreen.Services.AccountService.AccountCompanyService;
import com.eVolGreen.eVolGreen.Services.AccountService.AccountEmployeeService;
import com.eVolGreen.eVolGreen.Services.AccountService.CredentialService;
import com.eVolGreen.eVolGreen.Services.DUserService.CompanyUserService;
import com.eVolGreen.eVolGreen.Services.DUserService.EmployeeUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class AccountCompanyController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CompanyUserService companyService;

    @Autowired
    private EmployeeUserService employeeService;

    @Autowired
    private CredentialService credentialService;

    @Autowired
    private AccountCompanyService accountCompanyService;

    @Autowired
    private AccountEmployeeService accountEmployeeService;



}
