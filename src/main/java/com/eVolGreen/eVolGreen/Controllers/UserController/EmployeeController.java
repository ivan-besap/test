package com.eVolGreen.eVolGreen.Controllers.UserController;

import com.eVolGreen.eVolGreen.DTOS.UserDTO.EmployeeDTO.EmployeeRegisterDTO;
import com.eVolGreen.eVolGreen.DTOS.UserDTO.EmployeeDTO.EmployeeUserDTO;
import com.eVolGreen.eVolGreen.DTOS.UserDTO.EmployeeDTO.EmployeeUserLoginDTO;
import com.eVolGreen.eVolGreen.Models.Account.Location;
import com.eVolGreen.eVolGreen.Models.Account.PermissionCredential.Credential;
import com.eVolGreen.eVolGreen.Models.Account.RelationTypeCompany.CompanyEmployeeRelation;
import com.eVolGreen.eVolGreen.Models.Account.RelationTypeCompany.RelationType;
import com.eVolGreen.eVolGreen.Models.Account.TypeOfAccount.AccountCompany;
import com.eVolGreen.eVolGreen.Models.Account.TypeOfAccount.AccountEmployee;
import com.eVolGreen.eVolGreen.Models.Account.TypeOfAccount.TypeAccounts;
import com.eVolGreen.eVolGreen.Models.User.Role;
import com.eVolGreen.eVolGreen.Models.User.subclassUser.CompanyUser;
import com.eVolGreen.eVolGreen.Models.User.subclassUser.EmployeeUser;
import com.eVolGreen.eVolGreen.Repositories.CompanyEmployeeRelationRepository;
import com.eVolGreen.eVolGreen.Repositories.CompanyUserRepository;
import com.eVolGreen.eVolGreen.Repositories.EmployeeUserRepository;
import com.eVolGreen.eVolGreen.Repositories.LocationRepository;
import com.eVolGreen.eVolGreen.Services.AccountService.AccountCompanyService;
import com.eVolGreen.eVolGreen.Services.AccountService.AccountEmployeeService;
import com.eVolGreen.eVolGreen.Services.AccountService.CredentialService;
import com.eVolGreen.eVolGreen.Services.AccountService.LocationService;
import com.eVolGreen.eVolGreen.Services.DUserService.EmployeeUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    private EmployeeUserService employeeUserService;

    @Autowired
    private EmployeeUserRepository employeeUserRepository;

    @Autowired
    private CompanyUserRepository companyUserRepository;

    @Autowired
    private AccountEmployeeService accountEmployeeService;

    @Autowired
    private CredentialService credentialService;

    @Autowired
    private LocationService locationService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CompanyEmployeeRelationRepository companyEmployeeRelationRepository;

    @Autowired
    private AccountCompanyService accountCompanyService;

    @GetMapping("/employees")
    public List<EmployeeUserDTO> getEmployees() {
        return employeeUserService.getEmployeeUsersDTO();
    }

    @GetMapping("/employees/{id}")
    public EmployeeUserDTO getEmployee(@PathVariable Long id){
        return employeeUserService.getEmployeeUserDTO(id);
    }

    @GetMapping("/employees/logins")
    public List<EmployeeUserLoginDTO> getEmployeesLogin() {
        return employeeUserService.getEmployeeUsersLoginDTO();
    }

    @PostMapping("/companies/current/employee")
    public ResponseEntity<Object> registerEmployee(Authentication authentication,
                                                   @RequestBody EmployeeRegisterDTO employeeDTO) {

        CompanyUser company = companyUserRepository.findByEmail(authentication.getName());
        if (company == null) {
            return new ResponseEntity<>("Empresa no encontrada", HttpStatus.NOT_FOUND);
        }

        Optional<AccountCompany> optionalAccountCompany = company.getCuentaCompañia().stream().findFirst();
        if (optionalAccountCompany.isEmpty()) {
            return new ResponseEntity<>("No se encontró la cuenta de la compañía", HttpStatus.NOT_FOUND);
        }

        AccountCompany accountCompany = optionalAccountCompany.get();

        String message = "";

        if (employeeDTO.getNombre().isBlank()) {
            return new ResponseEntity<>("El nombre es requerido", HttpStatus.FORBIDDEN);
        }
        if (employeeDTO.getApellidoPaterno().isBlank()) {
            return new ResponseEntity<>("El apellido paterno es requerido", HttpStatus.FORBIDDEN);
        }
        if (employeeDTO.getApellidoMaterno().isBlank()) {
            return new ResponseEntity<>("El apellido materno es requerido", HttpStatus.FORBIDDEN);
        }
        if (employeeDTO.getEmail().isBlank()) {
            return new ResponseEntity<>("El email es requerido", HttpStatus.FORBIDDEN);
        }
        if (employeeDTO.getPassword().isBlank()) {
            return new ResponseEntity<>("La contraseña es requerida", HttpStatus.FORBIDDEN);
        }

        // Capturar la credencial usando el nombre proporcionado en el DTO
        Optional<Credential> credentials = credentialService.findFirstByNombre(employeeDTO.getNombreCredencial());
        if (credentials.isEmpty()) {
            return new ResponseEntity<>("Credencial no encontrada", HttpStatus.NOT_FOUND);
        }

        // Si hay más de una credencial con el mismo nombre, selecciona la primera o maneja el caso según tu lógica de negocio
        Credential originalCredential = credentials.get();

        Role RolTrabajador = Role.EMPLOYEE;
        EmployeeUser Trabajador = new EmployeeUser(
                employeeDTO.getEmail(),
                passwordEncoder.encode(employeeDTO.getPassword()),
                employeeDTO.getTelefono(),
                employeeDTO.getNombre(),
                employeeDTO.getApellidoPaterno(),
                employeeDTO.getApellidoMaterno(),
                RolTrabajador,
                false
        );
        employeeUserService.saveEmployee(Trabajador);

        Location ubicacionTrabajadorDefault = new Location("Default");
        locationService.saveLocation(ubicacionTrabajadorDefault);

        String NumeroDeCuenta = "Usuario" + getStringRandomEmployee();
        AccountEmployee newAccount = new AccountEmployee(
                NumeroDeCuenta,
                employeeDTO.getNombre(),
                employeeDTO.getApellidoPaterno(),
                employeeDTO.getApellidoMaterno(),
                null,
                null,
                employeeDTO.getEmail(),
                passwordEncoder.encode(employeeDTO.getPassword()),
                RolTrabajador,
                TypeAccounts.Employee,
                ubicacionTrabajadorDefault
        );
        newAccount.setCuentaCompañia(accountCompany);
        newAccount.setTrabajador(Trabajador);
        accountCompany.getCuentaEmpleado().add(newAccount);
        Trabajador.setCuentaCompañia(accountCompany);

        // Clonar la credencial
        Credential clonedCredential = new Credential();
        clonedCredential.setNombre(originalCredential.getNombre());
        clonedCredential.setCuentaCompañia(accountCompany);
        clonedCredential.setCuentaTrabajador(newAccount);
        clonedCredential.setActivo(originalCredential.isActivo());

        credentialService.saveCredential(clonedCredential);

        // Asociar la credencial clonada a la cuenta de empleado
        newAccount.getCredenciales().add(clonedCredential);

        CompanyEmployeeRelation relacionCompañiaTrabajador = new CompanyEmployeeRelation(
                company,
                Trabajador,
                RelationType.EMPLOYEE
        );
        companyEmployeeRelationRepository.save(relacionCompañiaTrabajador);

        company.getRelacionCompañiaEmpleado().add(relacionCompañiaTrabajador);
        Trabajador.getRelacionCompañiaEmpleado().add(relacionCompañiaTrabajador);

        accountEmployeeService.saveCuentaTrabajador(newAccount);
        accountCompanyService.saveCuentaCompañia(accountCompany);

        return ResponseEntity.ok("Empleado creado exitosamente");
    }

    @PutMapping("/companies/current/employee/{id}")
    public ResponseEntity<Object> updateEmployee(Authentication authentication,
                                                 @PathVariable Long id,
                                                 @RequestBody EmployeeRegisterDTO employeeDTO) {

        // Obtener la compañía del usuario autenticado
        CompanyUser company = companyUserRepository.findByEmail(authentication.getName());
        if (company == null) {
            return new ResponseEntity<>("Empresa no encontrada", HttpStatus.NOT_FOUND);
        }

        // Buscar el empleado por ID
        EmployeeUser existingEmployee = employeeUserService.findById(id);
        if (existingEmployee == null) {
            return new ResponseEntity<>("Empleado no encontrado", HttpStatus.NOT_FOUND);
        }

        // Validar los campos requeridos en el DTO
        if (employeeDTO.getNombre().isBlank()) {
            return new ResponseEntity<>("El nombre es requerido", HttpStatus.FORBIDDEN);
        }
        if (employeeDTO.getApellidoPaterno().isBlank()) {
            return new ResponseEntity<>("El apellido paterno es requerido", HttpStatus.FORBIDDEN);
        }
        if (employeeDTO.getApellidoMaterno().isBlank()) {
            return new ResponseEntity<>("El apellido materno es requerido", HttpStatus.FORBIDDEN);
        }
        if (employeeDTO.getEmail().isBlank()) {
            return new ResponseEntity<>("El email es requerido", HttpStatus.FORBIDDEN);
        }
        if (employeeDTO.getPassword().isBlank()) {
            return new ResponseEntity<>("La contraseña es requerida", HttpStatus.FORBIDDEN);
        }

        // Actualizar la información del empleado existente
        existingEmployee.setNombre(employeeDTO.getNombre());
        existingEmployee.setApellidoPaterno(employeeDTO.getApellidoPaterno());
        existingEmployee.setApellidoMaterno(employeeDTO.getApellidoMaterno());
        existingEmployee.setEmail(employeeDTO.getEmail());
        existingEmployee.setPassword(passwordEncoder.encode(employeeDTO.getPassword()));
        existingEmployee.setTelefono(employeeDTO.getTelefono());

        // Guardar los cambios en la base de datos
        employeeUserService.saveEmployee(existingEmployee);

        return ResponseEntity.ok("Empleado actualizado exitosamente");
    }

    @PatchMapping("/companies/current/employees/{id}/delete")
    public ResponseEntity<Object> deactivateEmployee(Authentication authentication,
                                                     @PathVariable Long id) {
        // Obtener la compañía del usuario autenticado
        CompanyUser companyUser = companyUserRepository.findByEmail(authentication.getName());
        String message;

        if (companyUser == null) {
            message = "Empresa no encontrada";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }

        // Buscar el empleado por ID
        EmployeeUser employeeUser = employeeUserService.findById(id);
        if (employeeUser == null) {
            message = "Empleado no encontrado";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }

        // Verificar si el empleado ya está desactivado
        if (!employeeUser.getActivo()) {
            message = "El empleado ya está desactivado";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }

        // Desactivar el empleado
        employeeUser.setActivo(false);
        employeeUserService.saveEmployee(employeeUser);

        message = "Empleado desactivado exitosamente";
        return ResponseEntity.ok(message);
    }


    // Cambia isActiveStatus del cliente actualmente autenticada (Test)
    @PutMapping("/employees/change-active-status")
    public ResponseEntity<Object> changeActiveStatus(Authentication authentication,
                                                     @RequestParam boolean activeStatus) {

        EmployeeUser employee = employeeUserService.findByEmail(authentication.getName());
        String message = "";

        if (employee == null) {
            message = "Employee not found.";
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }

        employee.setActivo(activeStatus);
        employeeUserService.saveEmployee(employee);

        return ResponseEntity.ok("Active status updated to: " + activeStatus);
    }

    public String getStringRandomEmployee() {
        int min = 0;
        int max = 99999999;
        int randomNumber = (int) ((Math.random() * (max - min)) + min);
        return String.format("%08d", randomNumber);
    }
}
