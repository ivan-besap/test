package com.eVolGreen.eVolGreen.Controllers;

import com.eVolGreen.eVolGreen.DTOS.Company.CompanyLoginDTO;
import com.eVolGreen.eVolGreen.Models.*;
import com.eVolGreen.eVolGreen.Repositories.PermissionRepository;
import com.eVolGreen.eVolGreen.Services.*;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.eVolGreen.eVolGreen.DTOS.Company.CompanyDTO;
import com.eVolGreen.eVolGreen.Repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/api")
public class CompanyController {

    @Autowired
    private CompanyService companyService;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private AccountService  accountService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private CarService  carService;
    @Autowired
    private DeviceIdentifierService deviceIdentifierService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionRepository permissionRepository;

    @GetMapping("/companies")
    public List<CompanyDTO> getCompanies() {
        return companyService.getCompaniesDTO();
    }

    @GetMapping("/companies/{id}")
    public CompanyDTO getCompany(@PathVariable Long id) {
        return companyService.getCompanyDTO(id);
    }

    @GetMapping("/companies/logins")
    public List<CompanyLoginDTO> getCompaniesLogin() {
        return companyService.getCompaniesLoginDTO();
    }

    @GetMapping("/companies/current")
    public CompanyDTO getCurrentCompany(Authentication authentication){
        return companyService.getCompanyDTOByEmailCurrent(authentication.getName());
    }

//    @PostMapping("/companies/employee")
//    public ResponseEntity<Object> registerEmployee(Authentication authentication,
//                                                   @RequestBody Employee newEmployee) {
//
//        Company company = companyService.findByEmailCompany(authentication.getName());
//        String message = "";
//
//        if (newEmployee.getName().isBlank()) {
//            message = "Name is required";
//            return new ResponseEntity<>(message, HttpStatus.FORBIDDEN);
//        }
//        if (newEmployee.getFirstSurname().isBlank()) {
//            message = "First surname is required";
//            return new ResponseEntity<>(message, HttpStatus.FORBIDDEN);
//        }
//        if (newEmployee.getLastSurname().isBlank()) {
//            message = "Last surname is required";
//            return new ResponseEntity<>(message, HttpStatus.FORBIDDEN);
//        }
//        if (newEmployee.getEmail().isBlank()) {
//            message = "Email is required";
//            return new ResponseEntity<>(message, HttpStatus.FORBIDDEN);
//        }
//        if (newEmployee.getPassword().isBlank()) {
//            message = "Password is required";
//            return new ResponseEntity<>(message, HttpStatus.FORBIDDEN);
//        }
//
//        // Obtener el rol de empleado de la base de datos o crearlo si no existe
//        Role employeeRole = roleService.findByName("EMPLOYEE");
//        if (employeeRole == null) {
//            Optional<Permission> employeeViewPermission = permissionRepository.findByName("employee_view");
//            if (employeeViewPermission == null) {
//                employeeViewPermission = Optional.of(new Permission("employee_view", "Permitir vista encargado"));
//                permissionRepository.save(employeeViewPermission.get());
//            }
//
//            employeeRole = new Role("EMPLOYEE");
//            employeeRole.setPermissions(new HashSet<>(List.of(employeeViewPermission.get())));
//            roleService.saveRole(employeeRole);
//        }
//
//        // Asignar roles
//        Set<Role> roles = new HashSet<>();
//        roles.add(employeeRole);
//
//        // Codificar la contraseña antes de guardar el empleado
//        Employee employee = new Employee(
//                newEmployee.getName(),
//                newEmployee.getFirstSurname(),
//                newEmployee.getLastSurname(),
//                newEmployee.getEmail(),
//                passwordEncoder.encode(newEmployee.getPassword()),  // codificar la contraseña
//                LocalDate.now(),
//                company
//        );
//        employee.setRoles(roles);
//
//        employeeService.saveEmployee(employee);
//
//        // Verificar si el empleado no es administrador
//        if (!employee.getEmail().contains("@admin.com")) {
//            String accountNumber = "VIN" + getStringRandomEmployee();
//            Account newAccount = new Account(accountNumber, LocalDate.now(), TypeAccounts.Employee);
//            company.addAccount(newAccount);
//            accountService.saveAccount(newAccount);
//        }
//
//        company.addEmployee(employee);
//        companyRepository.save(company);
//
//        message = "Employee created successfully";
//        return ResponseEntity.ok(message);
//    }


    int min = 00000000;
    int max = 99999999;

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public String getStringRandomEmployee() {
        int randomNumber = getRandomNumber(min, max);
        return String.valueOf(randomNumber);
    }

    @PostMapping("/company/current/car")
    public ResponseEntity<Object> createCar(Authentication authentication,
                                            @RequestBody Car newCar,
                                            @RequestParam String numberAccount) {

        Company clientCurrent = companyService.findByEmailCompany(authentication.getName());
        String message = "";
        try {
            // Validate parameters
            if (newCar.getLicensePlate().isBlank()){
                message = "Missing license plate";
                return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
            }
            if (newCar.getModel().isBlank()){
                message = "Missing model";
                return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
            }
            if (newCar.getVin().isBlank()){
                message = "Missing vin";
                return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
            }
            if (newCar.getColor().isBlank()){
                message = "Missing color";
                return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
            }
            if (newCar.getBrand().isBlank()){
                message = "Missing make";
                return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
            }
            if (newCar.getManufactureYear().isBlank()){
                message = "Missing year";
                return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
            }
            if (newCar.getCapacityFullPower() == null){
                message = "Missing mileage";
                return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
            }
            if (numberAccount.isBlank()){
                message = "Missing account number";
                return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
            }

            // Find the account with the provided account number
            Optional<Account> optionalAccount = clientCurrent.getAccounts().stream()
                    .filter(account -> account.getNumber().equals(numberAccount))
                    .findFirst();

            // Check if the account exists
            if (optionalAccount.isEmpty()) {
                message = "The provided account number does not belong to the current client";
                return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
            }

            // Get the account
            Account selectedAccount = optionalAccount.get();

            // Create the car
            Car car = new Car( newCar.getLicensePlate(),
                    newCar.getModel(),
                    newCar.getVin(),
                    newCar.getColor(),
                    newCar.getBrand(),
                    newCar.getManufactureYear(),
                    newCar.getCapacityFullPower());

            // Set the car's identifier
            String indentifierName ="CARD-" + getStringRandomIdentifier();
            long indentifierNumber = getIndentifierNumber() ;
            DeviceIdentifier cardIdentifier = new DeviceIdentifier(
                    indentifierName,
                    indentifierNumber,
                    "Card",
                    car);
            car.addDeviceIdentifier(cardIdentifier);
            cardIdentifier.setCar(car);

            String body = String.valueOf(cardIdentifier);
            // Set the car's account
            car.setAccount(selectedAccount);

//            String email = clientCurrent.getEmail();
//            Email emailMessage = new Email( email,
//                    "Your Car Has Been Successfully Created!",
//                    body);
//            emailService.sendCarCreatedEmail(emailMessage);

            // Save the car
            carService.saveCar(car);
            deviceIdentifierService.saveDeviceIdentifier(cardIdentifier);
            return new ResponseEntity<>("Car created successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error creating car: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public String getStringRandomIdentifier() {
        int randomNumber = getRandomNumber(min, max);
        return String.valueOf(randomNumber);
    }

    public Long getIndentifierNumber (){
        long randomNumber = getRandomNumber(min, max);
        return randomNumber;
    }

    @PutMapping("/companies/change-active-status")
    public ResponseEntity<Object> changeActiveStatus(Authentication authentication,
                                                     @RequestParam boolean activeStatus) {

        Company company = companyService.findByEmailCompany(authentication.getName());
        String message = "";

        if (company == null) {
            message = "Company not found.";
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }

        company.setActive(activeStatus);
        companyService.saveCompany(company);

        return ResponseEntity.ok("Active status updated to: " + activeStatus);
    }

}
