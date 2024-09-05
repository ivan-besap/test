package com.eVolGreen.eVolGreen.Controllers.AccountController;


import com.amazonaws.services.cognitoidp.model.ResourceNotFoundException;
import com.eVolGreen.eVolGreen.DTOS.AccountDTO.AccountDTO;
import com.eVolGreen.eVolGreen.DTOS.AccountDTO.EmployeeRegisterDTO;
import com.eVolGreen.eVolGreen.DTOS.AccountDTO.RoleDTO;
import com.eVolGreen.eVolGreen.DTOS.AccountDTO.RoleRequestDTO;
import com.eVolGreen.eVolGreen.Models.Account.Account;
import com.eVolGreen.eVolGreen.Models.Account.Empresa;
import com.eVolGreen.eVolGreen.Models.Account.Location;
import com.eVolGreen.eVolGreen.Models.Account.Permission.Permission;
import com.eVolGreen.eVolGreen.Models.Account.TypeOfAccount.TypeAccounts;
import com.eVolGreen.eVolGreen.Models.User.Role;
import com.eVolGreen.eVolGreen.Repositories.AccountRepository;

import com.eVolGreen.eVolGreen.Repositories.LocationRepository;
import com.eVolGreen.eVolGreen.Repositories.RoleRepository;
import com.eVolGreen.eVolGreen.Services.AccountService.AccountService;

import com.eVolGreen.eVolGreen.Services.AccountService.PermissionService;
import com.eVolGreen.eVolGreen.Services.AccountService.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class AccountController {

    @Autowired
    private AccountService accountService;

//    @Autowired
//    private ClientUserService clientUserService;
//
//    @Autowired
//    private EmailService emailService;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PermissionService permissionService;
    @Autowired
    private RoleService roleService;

    @GetMapping("/accounts")
    public List<AccountDTO> getAccounts() {
        return accountService.getAccountsDTO();
    }
    @GetMapping("/accounts/{id}")
    public AccountDTO getAccount(@PathVariable Long id){
        return accountService.getAccountDTOCurrent(id);
    }

    @PatchMapping("/update-active-status")
    public ResponseEntity<String> updateActiveStatus(@RequestParam("activeStatus") boolean activeStatus, @RequestParam("accountId") Long accountId) {
        Account account = accountService.findById(accountId);
        if (account != null) {
            account.setActivo(activeStatus);
            accountService.saveAccount(account);
            return ResponseEntity.ok("El estado activo de la cuenta ha sido actualizado.");
        } else {
            return ResponseEntity.status(404).body("Cuenta no encontrada.");
        }
    }
//    @GetMapping("/clients/current/accounts/true")
//    public List<AccountDTO> getAccountDtoTrue(){
//        List<AccountDTO> accountDTOList= accountRepository.findAll().stream().map(AccountDTO::new).collect(Collectors.toList());
//        List<AccountDTO> accountDTOListTrue=accountDTOList.stream().filter(AccountDTO::getActive).collect(Collectors.toList());
//        return  accountDTOListTrue;
//    }
//    @PostMapping("/activate-accounts")
//    public ResponseEntity<String> activateAccount(@RequestParam String email, @RequestParam String checkDigit) {
//
//        String  message = "";
//
//        if (email.isBlank()) {
//            message = "Missing email";
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
//        }
//        if (checkDigit.isBlank()) {
//            message = "Missing check digit";
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
//        }
//
//        Client client = clientService.findByEmail(email);
//
//        if (client != null && client.getCheckDigit().equals(checkDigit)) {
//            Account account = client.getAccounts().stream().findFirst().orElse(null);
//            if (account != null) {
//                account.setActive(true);
//                accountService.saveAccount(account);
//
//                // Enviar correo electrónico de activación de cuenta
//                String body = "Your account has been activated successfully. You can now log in.";
//                Email emailMessage = new Email( email,
//                        "Your Account Has Been Successfully Activated!",
//                        body);
//                emailService.sendAccountActivationEmail(emailMessage);
//                return ResponseEntity.ok("Account activated successfully. Activation email sent.");
//            } else {
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Account not found.");
//            }
//        } else {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid email or check digit.");
//        }
//    }
//    @PatchMapping("/clients/current/accounts/delete/{id}")
//    public ResponseEntity<Object> deleteAccount (Authentication authentication,
//                                                 @PathVariable Long id){
//        ClientUser client = clientUserService.findByEmail(authentication.getName());
//        Account account = accountService.findById(id);
//        Boolean exists = client.getCuentaCliente().contains(account);
//        String message = " ";
//
//        if (account == null) {
//            message = "Account not found";
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
//        }
//        if (!exists) {
//            message = "Account not found";
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
//        }
//        if(!account.getActivo()){
//            message = "Account already deleted";
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
//        }
//
//        account.setActivo(false);
//        accountService.saveAccount(account);
//
//        message = "Account deleted successfully";
//        return ResponseEntity.ok(message);
//    }

//    @PostMapping("/clients/current/accounts")
//    public ResponseEntity<Object> registerAccount(Authentication authentication,
//                                                  @RequestParam String typeAccounts) {
//        Client client = clientUserService.findByEmail(authentication.getName());
//        String mensaje = "";
//
//        if (client == null) {
//            mensaje = "Client not found";
//            return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
//        }
//
//        List<Account> accounts = accountService.findByClientList(client);
//        if (accounts.size() >=3) {
//            mensaje = "Can't create more accounts";
//            return new ResponseEntity<>(mensaje, HttpStatus.FORBIDDEN);
//        }
//
//        String number = "VIN" + getStringRandomNumber();
//        Account account = new Account( number, LocalDate.now(), TypeAccounts.Client);
//        client.addAccount(account);
//        accountService.saveAccount(account);
//
//        mensaje= "New account created";
//        return new ResponseEntity<>(mensaje, HttpStatus.CREATED);
//    }



//Crud para Usuario (Trabajador)

    @PostMapping("/companies/current/employee")
    public ResponseEntity<Object> registerEmployee(Authentication authentication,
                                                   @RequestBody EmployeeRegisterDTO employeeDTO) {

        Optional<Account> cuentaOpt = accountRepository.findByEmail(authentication.getName());
        if (cuentaOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Empresa no encontrada");
        }

        Account cuentaEmpresa = cuentaOpt.get();

        String validationError = validateEmployeeDTO(employeeDTO);
        if (validationError != null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(validationError);
        }

        Optional<Role> roleOpt = roleRepository.findById(employeeDTO.getRole());
        if (roleOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Role no encontrado");
        }

        Role roleTrabajador = roleOpt.get();

        String numeroDeCuenta = "Usuario" + getStringRandomEmployee();
        Account cuentaUsuario = new Account(
                employeeDTO.getNombre(),
                employeeDTO.getApellidoPaterno(),
                employeeDTO.getApellidoMaterno(),
                numeroDeCuenta,
                employeeDTO.getNombre(),
                LocalDate.now(),
                employeeDTO.getEmail(),
                passwordEncoder.encode(employeeDTO.getPassword()),
                TypeAccounts.EMPLOYEE,
                roleTrabajador,
                employeeDTO.getTelefono(),
                employeeDTO.getRut(),
                cuentaEmpresa.getEmpresa(),
                true
        );

        accountRepository.save(cuentaUsuario);

        return ResponseEntity.ok("Empleado creado exitosamente");
    }

    @PatchMapping("/companies/current/employees/{id}/delete")
    public ResponseEntity<Object> deactivateEmployee(Authentication authentication,
                                                     @PathVariable Long id) {
        String message = " ";
        Optional<Account> cuentaOpt = accountRepository.findByEmail(authentication.getName());
        if (cuentaOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Empresa no encontrada");
        }

        Account cuentaEmpresa = cuentaOpt.get();

        // Buscar el empleado por ID
        Optional<Account> cuentaUsuario = accountRepository.findById(id);
        if (cuentaUsuario.isEmpty()) {  // Cambiado a .isEmpty() para verificar correctamente
            return new ResponseEntity<>("Usuario no encontrado", HttpStatus.NOT_FOUND);
        }

        Account cuentaTrabajador = cuentaUsuario.get();

        // Verificar si el empleado ya está desactivado
        if (!cuentaTrabajador.getActivo()) {
            message = "El empleado ya está desactivado";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }

        // Desactivar el empleado
        cuentaTrabajador.setActivo(false);
        accountRepository.save(cuentaTrabajador);

        message = "Empleado desactivado exitosamente";
        return ResponseEntity.ok(message);
    }

    @GetMapping("/roles")
    public List<RoleDTO> getRoles(Authentication authentication) {
        String email = authentication.getName();
        Optional<Account> account = accountService.findByEmail(email);
        if (account.isPresent()) {
            Empresa empresa = account.get().getEmpresa();
            if (empresa != null) {
                List<Role> roles = roleService.findRolesByEmpresa(empresa);
                return roles.stream().map(RoleDTO::new).collect(Collectors.toList());
            }
        }
        return Collections.emptyList();
    }

    @GetMapping("/roles/{id}")
    public ResponseEntity<RoleDTO> getRoleById(@PathVariable Long id) {
        Role role = roleService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found with id " + id));
        RoleDTO roleDTO = new RoleDTO(role);
        return ResponseEntity.ok(roleDTO);
    }

    @PutMapping("/roles/{id}")
    public ResponseEntity<String> updateRole(@PathVariable Long id, @RequestBody RoleRequestDTO roleRequestDTO) {
        Role role = roleService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found with id " + id));
        role.setNombre(roleRequestDTO.getNombre());
        List<Permission> permisos = permissionService.findAllById(roleRequestDTO.getPermisosIds());
        role.setPermisos(permisos);
        roleService.saveRole(role);

        return ResponseEntity.ok("Role updated successfully");
    }

    @PutMapping("/companies/current/employee/{id}")
    public ResponseEntity<Object> updateEmployee(Authentication authentication,
                                                 @PathVariable Long id,
                                                 @RequestBody EmployeeRegisterDTO employeeDTO) {

        Optional<Account> cuentaOpt = accountRepository.findByEmail(authentication.getName());
        if (cuentaOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Empresa no encontrada");
        }

        Account cuentaEmpresa = cuentaOpt.get();

        // Buscar el empleado por ID
        Optional<Account> cuentaUsuario = accountRepository.findById(id);
        if (cuentaUsuario.isEmpty()) {  // Cambiado a .isEmpty() para verificar correctamente
            return new ResponseEntity<>("Usuario no encontrado", HttpStatus.NOT_FOUND);
        }

        Account cuentaTrabajador = cuentaUsuario.get();

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
        /*if (employeeDTO.getPassword().isBlank()) {
            return new ResponseEntity<>("La contraseña es requerida", HttpStatus.FORBIDDEN);
        }*/

        // Buscar el Role por ID y asignarlo a la cuenta
        Optional<Role> roleOpt = roleRepository.findById(employeeDTO.getRole());
        if (roleOpt.isEmpty()) {
            return new ResponseEntity<>("Rol no encontrado", HttpStatus.NOT_FOUND);
        }
        Role role = roleOpt.get();

        // Actualizar la información del empleado existente
        cuentaTrabajador.setNombre(employeeDTO.getNombre());
        cuentaTrabajador.setApellidoPaterno(employeeDTO.getApellidoPaterno());
        cuentaTrabajador.setApellidoMaterno(employeeDTO.getApellidoMaterno());
        cuentaTrabajador.setEmail(employeeDTO.getEmail());
        /*cuentaTrabajador.setPassword(passwordEncoder.encode(employeeDTO.getPassword()));*/
        cuentaTrabajador.setRole(role);  // Asignar el nuevo role

        // Guardar los cambios en la base de datos
        accountRepository.save(cuentaTrabajador);

        return ResponseEntity.ok("Empleado actualizado exitosamente");
    }

    private String validateEmployeeDTO(EmployeeRegisterDTO employeeDTO) {
        if (employeeDTO.getNombre().isBlank()) {
            return "El nombre es requerido";
        }
        if (employeeDTO.getApellidoPaterno().isBlank()) {
            return "El apellido paterno es requerido";
        }
        if (employeeDTO.getApellidoMaterno().isBlank()) {
            return "El apellido materno es requerido";
        }
        if (employeeDTO.getEmail().isBlank()) {
            return "El email es requerido";
        }
        if (employeeDTO.getPassword().isBlank()) {
            return "La contraseña es requerida";
        }
        return null; // Sin errores
    }

    @GetMapping("/companies/current/employee")
    public ResponseEntity<List<AccountDTO>> getEmployees(Authentication authentication) {
        Optional<Account> cuentaOpt = accountRepository.findByEmail(authentication.getName());
        if (cuentaOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        Account cuenta = cuentaOpt.get();
        List<Account> empleados = accountRepository.findByEmpresaAndTipoCuentaAndActivo(cuenta.getEmpresa(), TypeAccounts.EMPLOYEE, true);

        List<AccountDTO> empleadosDTO = empleados.stream()
                .map(AccountDTO::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(empleadosDTO);
    }


    @PostMapping("/create-role")
    public ResponseEntity<String> createRole(@RequestBody RoleRequestDTO roleRequestDTO, Authentication authentication) {
        Optional<Account> account = accountService.findByEmail(authentication.getName());

        if (account.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se encontró la cuenta del usuario autenticado");
        }
        Empresa empresa = account.get().getEmpresa();

        if (empresa == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se encontró una empresa asociada a la cuenta");
        }
        Role role = new Role();
        role.setNombre(roleRequestDTO.getNombre());
        List<Permission> permisos = permissionService.findAllById(roleRequestDTO.getPermisosIds());
        role.setPermisos(permisos);
        role.setEmpresa(empresa);
        roleService.saveRole(role);
        return ResponseEntity.ok("Rol creado exitosamente");
    }







    public String getStringRandomEmployee() {
        int min = 0;
        int max = 99999999;
        int randomNumber = (int) ((Math.random() * (max - min)) + min);
        return String.format("%08d", randomNumber);
    }

}
