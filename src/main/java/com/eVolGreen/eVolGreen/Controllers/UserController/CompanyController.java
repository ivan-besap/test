package com.eVolGreen.eVolGreen.Controllers.UserController;

import com.eVolGreen.eVolGreen.DTOS.AccountDTO.CarDTO.CarCompanyDTO;
import com.eVolGreen.eVolGreen.DTOS.AccountDTO.CarDTO.DeviceIdentifierDTO;
import com.eVolGreen.eVolGreen.DTOS.AccountDTO.CarDTO.NewCarCompanyDTO;
import com.eVolGreen.eVolGreen.DTOS.AccountDTO.CarDTO.NewDeviceIdentifierDTO;
import com.eVolGreen.eVolGreen.DTOS.AccountDTO.FeeDTO.FeeDTO;
import com.eVolGreen.eVolGreen.DTOS.AccountDTO.FeeDTO.NewFeeConnectorDTO;
import com.eVolGreen.eVolGreen.DTOS.AccountDTO.FeeDTO.NewFeeDTO;
import com.eVolGreen.eVolGreen.DTOS.AccountDTO.PermissionCredentialDTO.NewPermisionCredentialDTO;
import com.eVolGreen.eVolGreen.DTOS.AccountDTO.PermissionCredentialDTO.PermissionCredentialDTO;
import com.eVolGreen.eVolGreen.DTOS.ChargingStationDTO.ChargerDTO.NewChargerDTO;
import com.eVolGreen.eVolGreen.DTOS.ChargingStationDTO.ChargingStationsDTO;
import com.eVolGreen.eVolGreen.DTOS.ChargingStationDTO.NewChargingStationsDTO;
import com.eVolGreen.eVolGreen.DTOS.ChargingStationDTO.NewConnectorDTO;
import com.eVolGreen.eVolGreen.DTOS.UserDTO.CompanyDTO.CompanyUserDTO;
import com.eVolGreen.eVolGreen.DTOS.UserDTO.CompanyDTO.CompanyUserRegisterDTO;
import com.eVolGreen.eVolGreen.DTOS.UserDTO.EmployeeDTO.EmployeeRegisterDTO;
import com.eVolGreen.eVolGreen.Models.Account.Car.Car;
import com.eVolGreen.eVolGreen.Models.Account.Car.DeviceIdentifier;
import com.eVolGreen.eVolGreen.Models.Account.Fee.Fee;
import com.eVolGreen.eVolGreen.Models.Account.Fee.FeeConnector;
import com.eVolGreen.eVolGreen.Models.Account.Location;
import com.eVolGreen.eVolGreen.Models.Account.PermissionCredential.Credential;
import com.eVolGreen.eVolGreen.Models.Account.PermissionCredential.Permission;
import com.eVolGreen.eVolGreen.Models.Account.PermissionCredential.PermissionCredential;
import com.eVolGreen.eVolGreen.Models.Account.RelationTypeCompany.CompanyEmployeeRelation;
import com.eVolGreen.eVolGreen.Models.Account.RelationTypeCompany.RelationType;
import com.eVolGreen.eVolGreen.Models.Account.TypeOfAccount.AccountCompany;
import com.eVolGreen.eVolGreen.Models.Account.TypeOfAccount.AccountEmployee;
import com.eVolGreen.eVolGreen.Models.Account.TypeOfAccount.TypeAccounts;
import com.eVolGreen.eVolGreen.Models.ChargingStation.Charger.Charger;
import com.eVolGreen.eVolGreen.Models.ChargingStation.ChargingStation;
import com.eVolGreen.eVolGreen.Models.ChargingStation.ChargingStationStatus;
import com.eVolGreen.eVolGreen.Models.ChargingStation.Connector.Connector;
import com.eVolGreen.eVolGreen.Models.ChargingStation.Connector.ConnectorStatus;
import com.eVolGreen.eVolGreen.Models.User.Role;
import com.eVolGreen.eVolGreen.Models.User.subclassUser.CompanyUser;
import com.eVolGreen.eVolGreen.Models.User.subclassUser.EmployeeUser;
import com.eVolGreen.eVolGreen.Repositories.*;
import com.eVolGreen.eVolGreen.Services.AccountService.*;
import com.eVolGreen.eVolGreen.Services.ChargingStationService.ChargerService;
import com.eVolGreen.eVolGreen.Services.ChargingStationService.ChargingStationsService;
import com.eVolGreen.eVolGreen.Services.ChargingStationService.ConnectorService;
import com.eVolGreen.eVolGreen.Services.DUserService.CompanyUserService;
import com.eVolGreen.eVolGreen.Services.DUserService.EmployeeUserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class CompanyController {

    @Autowired
    private CompanyUserService companyService;
    @Autowired
    private CompanyUserRepository companyUserRepository;
    @Autowired
    private AccountCompanyService accountCompanyService;
    @Autowired
    private AccountEmployeeService accountEmployeeService;
    @Autowired
    private EmployeeUserService employeeService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private CarService carService;
    @Autowired
    private DeviceIdentifierService deviceIdentifierService;
    @Autowired
    private CredentialService credentialService;
    @Autowired
    private PermissionRepository permissionRepository;
    @Autowired
    private LocationService locationService;
    @Autowired
    private ChargingStationsService chargingStationsService;
    @Autowired
    private ChargerService chargerService;
    @Autowired
    private ConnectorService connectorService;
    @Autowired
    private FeeService feeService;
    @Autowired
    private CompanyEmployeeRelationRepository companyEmployeeRelationRepository;
    @Autowired
    private PermissionCredentialRepository permissionCredentialRepository;
    @Autowired
    private FeeConnectorRepository feeConnectorRepository;

    @GetMapping("/companies")
    public List<CompanyUserDTO> getCompanies() {
        return companyService.getCompanieUsersDTO();
    }

    @GetMapping("/companies/{id}")
    public CompanyUserDTO getCompanyUser(@PathVariable Long id) {
        return companyService.getCompanyUserDTO(id);
    }

    @GetMapping("/companies/logins")
    public List<CompanyUserRegisterDTO> getCompaniesUserLogin() {
        return companyService.getCompanieUsersLoginDTO();
    }

    @GetMapping("/companies/current")
    public CompanyUserDTO getCurrentCompanyUser(Authentication authentication){
        return companyService.getCompanyDTOByEmailCurrent(authentication.getName());
    }

    @PutMapping("/companies/change-active-status")
    public ResponseEntity<Object> changeActiveStatus(Authentication authentication,
                                                     @RequestParam boolean activeStatus) {

        CompanyUser company = companyService.findByEmailCompanyUser(authentication.getName());
        String message = "";

        if (company == null) {
            message = "Company not found.";
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }

        company.setActivo(activeStatus);
        companyService.saveCompanyUser(company);

        return ResponseEntity.ok("Active status updated to: " + activeStatus);
    }

}
