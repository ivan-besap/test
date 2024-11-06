package com.eVolGreen.eVolGreen.Controllers.AccountController;


import com.amazonaws.services.cognitoidp.model.ResourceNotFoundException;
import com.eVolGreen.eVolGreen.DTOS.AccountDTO.*;
import com.eVolGreen.eVolGreen.DTOS.AccountDTO.FeeDTO.FeeDTO;
import com.eVolGreen.eVolGreen.Models.Account.Account;
import com.eVolGreen.eVolGreen.Models.Account.AuditLog;
import com.eVolGreen.eVolGreen.Models.Account.Empresa;
import com.eVolGreen.eVolGreen.Models.Account.Location;
import com.eVolGreen.eVolGreen.Models.Account.Permission.Permission;
import com.eVolGreen.eVolGreen.Models.Account.TypeOfAccount.TypeAccounts;
import com.eVolGreen.eVolGreen.Models.User.Role;
import com.eVolGreen.eVolGreen.Repositories.*;

import com.eVolGreen.eVolGreen.Services.AccountService.*;

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
    private AuditLogService auditLogService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AuditLogRepository auditLogRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private EmpresaRepository empresaRepository;

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


    @PostMapping("/create-client")
    public ResponseEntity<?> createClientAccount(@RequestBody AccountClientDTO accountClientDTO) {

        try {
            // Generar número de cuenta aleatorio con prefijo "cliente-"
            String numeroDeCuenta = "cliente-" + getStringRandomNumber();

            Role globalAdminRole = roleService.findById(1L)
                    .orElseThrow(() -> new RuntimeException("Rol 'ADMIN' no encontrado"));

            List<Permission> permisosClonados = globalAdminRole.getPermisos()
                    .stream()
                    .collect(Collectors.toList());

            if (!permisosClonados.isEmpty()) {
                permisosClonados = permisosClonados.subList(0, 1); // Mantener solo el primer permiso
            }

            Role clienteRole = new Role();
            clienteRole.setNombre("CLIENTE");
            clienteRole.setPermisos(permisosClonados);

            Empresa defaultEmpresa = empresaRepository.findByNombre("Empresa Predeterminada")
                    .orElseThrow(() -> new RuntimeException("Empresa 'Empresa Predeterminada' no encontrada"));


            roleService.saveRole(clienteRole);

            // Crear una nueva instancia de Account
            Account newAccount = new Account(
                    accountClientDTO.getNombre(),
                    accountClientDTO.getApellidoPaterno(),
                    null, // Apellido materno opcional
                    numeroDeCuenta,
                    accountClientDTO.getNombre() + " Cuenta", // Nombre de la cuenta
                    LocalDate.now(),
                    accountClientDTO.getEmail(),
                    passwordEncoder.encode(accountClientDTO.getPassword()),
                    TypeAccounts.CLIENT, // Tipo de cuenta CLIENT
                    clienteRole,
                    accountClientDTO.getTelefono(),
                    null, // Rut opcional
                    defaultEmpresa, // Empresa opcional
                    true, // Activo por defecto
                    false, // AlarmaCorreo por defecto
                    false  // AlarmaError por defecto
            );

            // Guardar la cuenta en el servicio
            accountService.saveAccount(newAccount);

            return new ResponseEntity<>("Client account created successfully", HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>("Error creating client account: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateClient/{id}")
    public ResponseEntity<?> updateAccount(@PathVariable Long id, @RequestBody AccountClientDTO accountClientDTO) {
        try {
            // Buscar la cuenta existente por ID
            Account existingAccount = accountService.findById(id);

            // Actualizar las propiedades solo si se han proporcionado
            if (accountClientDTO.getNombre() != null) {
                existingAccount.setNombre(accountClientDTO.getNombre());
            }
            if (accountClientDTO.getApellidoPaterno() != null) {
                existingAccount.setApellidoPaterno(accountClientDTO.getApellidoPaterno());
            }
            if (accountClientDTO.getEmail() != null) {
                existingAccount.setEmail(accountClientDTO.getEmail());
            }
            if (accountClientDTO.getPassword() != null) {
                // Codificar la nueva contraseña
                existingAccount.setPassword(passwordEncoder.encode(accountClientDTO.getPassword()));
            }
            if (accountClientDTO.getTelefono() != null) {
                existingAccount.setTelefono(accountClientDTO.getTelefono());
            }

            // Guardar los cambios
            accountService.saveAccount(existingAccount);

            return new ResponseEntity<>("Account updated successfully", HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>("Error updating account: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/companies/current/employee")
    public ResponseEntity<Object> registerEmployee(Authentication authentication,
                                                   @RequestBody EmployeeRegisterDTO employeeDTO) {

        Optional<Account> cuentaOpt = accountRepository.findByEmail(authentication.getName());
        if (cuentaOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Empresa no encontrada");
        }

        if (accountRepository.findByEmail(employeeDTO.getEmail()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Ya existe un usuario con ese correo electrónico.");
        }

        Account cuentaEmpresa = cuentaOpt.get();
        Account account2 = cuentaOpt.get();

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
                true,
                false,
                false
        );

        accountRepository.save(cuentaUsuario);

        String descripcion = "Usuario " + account2.getEmail() + " creó un usuario Empresa con el nombre: " + employeeDTO.getNombre() + " " + employeeDTO.getApellidoPaterno() + " y correo: " + employeeDTO.getEmail();
        auditLogService.recordAction(descripcion, account2);

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
        Account account2 = cuentaOpt.get();

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

        String descripcion = "Usuario " + account2.getEmail() + " eliminó un usuario Empresa con el nombre: " + cuentaTrabajador.getNombre() + " " + cuentaTrabajador.getApellidoPaterno() + " y correo: " + cuentaTrabajador.getEmail();
        auditLogService.recordAction(descripcion, account2);

        message = "Empleado desactivado exitosamente";
        return ResponseEntity.ok(message);
    }

    @GetMapping("/roles")
    public List<RoleDTO> getRoles(Authentication authentication) {
        String email = authentication.getName();
        return roleService.getRolesDTO(email);
    }

    @GetMapping("/roles/{id}")
    public ResponseEntity<RoleDTO> getRoleById(@PathVariable Long id) {
        Role role = roleService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found with id " + id));
        RoleDTO roleDTO = new RoleDTO(role);
        return ResponseEntity.ok(roleDTO);
    }

    @PutMapping("/roles/{id}")
    public ResponseEntity<String> updateRole( Authentication authentication,@PathVariable Long id, @RequestBody RoleRequestDTO roleRequestDTO) {

        Optional<Account> account = accountService.findByEmail(authentication.getName());

        if (account.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se encontró la cuenta del usuario autenticado");
        }
        Account account2 = account.get();
        Role role = roleService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found with id " + id));
        role.setNombre(roleRequestDTO.getNombre());
        List<Permission> permisos = permissionService.findAllById(roleRequestDTO.getPermisosIds());
        role.setPermisos(permisos);
        roleService.saveRole(role);

        String descripcion = "Usuario " + account2.getEmail() + " editó un rol con el nombre: " + role.getNombre();
        auditLogService.recordAction(descripcion, account2);

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
        Account account2 = cuentaOpt.get();

        Account cuentaEmpresa = cuentaOpt.get();

        // Buscar el empleado por ID
        Optional<Account> cuentaUsuario = accountRepository.findById(id);
        if (cuentaUsuario.isEmpty()) {  // Cambiado a .isEmpty() para verificar correctamente
            return new ResponseEntity<>("Usuario no encontrado", HttpStatus.NOT_FOUND);
        }

        Account cuentaTrabajador = cuentaUsuario.get();

        if (!cuentaTrabajador.getEmail().equals(employeeDTO.getEmail())) {
            if (accountRepository.findByEmail(employeeDTO.getEmail()).isPresent()) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Ya existe un usuario con ese correo electrónico.");
            }
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
        cuentaTrabajador.setRole(role);  // Asignar el nuevo role

        if (employeeDTO.getPassword() != null && !employeeDTO.getPassword().isBlank()) {
            cuentaTrabajador.setPassword(passwordEncoder.encode(employeeDTO.getPassword()));
        }

        // Guardar los cambios en la base de datos
        accountRepository.save(cuentaTrabajador);

        String descripcion = "Usuario " + account2.getEmail() + " modificó un usuario Empresa con el nombre: " + employeeDTO.getNombre() + " " + employeeDTO.getApellidoPaterno() + " y correo: " + employeeDTO.getEmail();
        auditLogService.recordAction(descripcion, account2);

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
        Account account2 = account.get();

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
        role.setActivo(true);

        String descripcion = "Usuario " + account2.getEmail() + " creó un rol con el nombre: " + role.getNombre();
        auditLogService.recordAction(descripcion, account2);

        return ResponseEntity.ok("Rol creado exitosamente");
    }

    @PatchMapping("/roles/{id}/delete")
    public ResponseEntity<Object> toggleRoleActiveStatus(Authentication authentication,@PathVariable Long id) {

        Optional<Account> account = accountService.findByEmail(authentication.getName());

        if (account.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se encontró la cuenta del usuario autenticado");
        }
        Account account2 = account.get();

        Optional<Role> optionalRole = roleService.findById(id);

        if (optionalRole.isEmpty()) {
            return new ResponseEntity<>("El rol no se encontró", HttpStatus.NOT_FOUND);
        }

        Role role = optionalRole.get();

        if (role.getActivo()) {
            role.setActivo(false);
            roleService.saveRole(role);
            String descripcion = "Usuario " + account2.getEmail() + " eliminó un rol con el nombre: " + role.getNombre();
            auditLogService.recordAction(descripcion, account2);
            return ResponseEntity.ok("El rol ha sido desactivado correctamente.");
        } else {
            role.setActivo(true);
            roleService.saveRole(role);

            return ResponseEntity.ok("El rol ha sido activado correctamente.");
        }
    }
    @GetMapping("/audit-logs")
    public List<AuditLogDTO> getAuditLogs() {
        List<AuditLog> auditLogs = auditLogRepository.findAll();

        // Convertir las entidades AuditLog a AuditLogDTO
        return auditLogs.stream().map(log -> new AuditLogDTO(
                log.getAccount().getEmail(),
                log.getAccount().getNombre(),
                log.getAccount().getApellidoPaterno(),
                log.getDescription(),
                log.getDate()
        )).collect(Collectors.toList());
    }

    @GetMapping("/verify-account")
    public ResponseEntity<String> verifyAccount(@RequestParam("email") String email) {
        Optional<Account> accountOpt = accountService.findByEmail(email);
        if (accountOpt.isEmpty()) {
            return new ResponseEntity<>("Cuenta no encontrada", HttpStatus.NOT_FOUND);
        }
        Account account = accountOpt.get();
        if (account.getActivo()) {
            return new ResponseEntity<>("La cuenta ya está activa", HttpStatus.BAD_REQUEST);
        }
        account.setActivo(true);
        accountService.saveAccount(account);
        return ResponseEntity.ok("Cuenta activada con éxito");
    }

    @GetMapping("/usuarios/alarmaCorreo")
    public ResponseEntity<List<AccountDTO>> getUsuariosPorAlarmaCorreo(Authentication authentication) {
        Optional<Account> account = accountService.findByEmail(authentication.getName());

        if (account.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.emptyList());
        }

        Empresa empresa = account.get().getEmpresa();

        List<Account> usuarios = accountRepository.findByEmpresaIdAndAlarmaCorreoTrue(empresa.getId());
        List<AccountDTO> usuariosDTO = usuarios.stream()
                .map(AccountDTO::new)
                .collect(Collectors.toList());
        return new ResponseEntity<>(usuariosDTO, HttpStatus.OK);
    }
    @PatchMapping("/usuarios/alarmaCorreo/{id}/remove")
    public ResponseEntity<String> removeUsuarioFromAlarmaCorreo(@PathVariable Long id, Authentication authentication) {
        Optional<Account> account = accountService.findByEmail(authentication.getName());
        if (account.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se encontró la cuenta del usuario autenticado");
        }
        Optional<Account> usuario = accountRepository.findById(id);

        if (usuario.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el usuario");
        }

        Account usuarioToUpdate = usuario.get();
        usuarioToUpdate.setAlarmaCorreo(false);
        accountRepository.save(usuarioToUpdate);

        return ResponseEntity.ok("Usuario eliminado de las alarmas diarias correctamente.");
    }


    @GetMapping("/usuarios/alarmaError")
    public ResponseEntity<List<AccountDTO>> getUsuariosPorAlarmaError(Authentication authentication) {
        Optional<Account> account = accountService.findByEmail(authentication.getName());

        if (account.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.emptyList());
        }

        Empresa empresa = account.get().getEmpresa();

        List<Account> usuarios = accountRepository.findByEmpresaIdAndAlarmaErrorTrue(empresa.getId());
        List<AccountDTO> usuariosDTO = usuarios.stream()
                .map(AccountDTO::new)
                .collect(Collectors.toList());
        return new ResponseEntity<>(usuariosDTO, HttpStatus.OK);
    }
    @PatchMapping("/usuarios/alarmaError/{id}/remove")
    public ResponseEntity<String> removeUsuarioFromAlarmaError(@PathVariable Long id, Authentication authentication) {
        // Buscar la cuenta del usuario autenticado
        Optional<Account> account = accountService.findByEmail(authentication.getName());

        if (account.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se encontró la cuenta del usuario autenticado");
        }

        Optional<Account> usuario = accountRepository.findById(id);

        if (usuario.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el usuario");
        }

        Account usuarioToUpdate = usuario.get();
        usuarioToUpdate.setAlarmaError(false);
        accountRepository.save(usuarioToUpdate);

        return ResponseEntity.ok("Usuario eliminado de las alertas de errores de conector correctamente.");
    }

    @GetMapping("/usuarios/correos")
    public ResponseEntity<List<Map<String, String>>> getCorreosPorEmpresa(Authentication authentication) {
        Optional<Account> account = accountService.findByEmail(authentication.getName());

        if (account.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.emptyList());
        }
        Empresa empresa = account.get().getEmpresa();

        List<Account> accounts = accountRepository.findByEmpresaAndActivo(empresa,true);
        List<Map<String, String>> correos = accounts.stream()
                .map(acc -> Map.of("value", String.valueOf(acc.getId()), "label", acc.getEmail()))
                .collect(Collectors.toList());

        return new ResponseEntity<>(correos, HttpStatus.OK);
    }

    @PostMapping("/usuarios/alarmaCorreo/add")
    public ResponseEntity<String> agregarUsuarioAlarmaCorreo(@RequestBody Map<String, String> payload, Authentication authentication) {
        Optional<Account> accountOptional = accountService.findByEmail(authentication.getName());

        if (accountOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se encontró la cuenta del usuario autenticado");
        }
        Account accountUsuario = accountOptional.get();
        String email = payload.get("email");

        Optional<Account> usuario = accountService.findByEmail(email);
        if (usuario.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El usuario con ese correo no fue encontrado");
        }
        Account account = usuario.get();
        account.setAlarmaCorreo(true);
        accountService.saveAccount(account);

        String descripcion = "Usuario " + accountUsuario.getEmail() + " añadió a " + account.getEmail() + " a las alarmas de correo.";
        auditLogService.recordAction(descripcion, accountUsuario);

        return ResponseEntity.ok("El usuario ha sido añadido correctamente a las alarmas de correo.");
    }

    @PostMapping("/usuarios/alarmaError/add")
    public ResponseEntity<String> agregarUsuarioAlarmaError(@RequestBody Map<String, String> payload, Authentication authentication) {
        Optional<Account> accountOptional = accountService.findByEmail(authentication.getName());

        if (accountOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se encontró la cuenta del usuario autenticado");
        }

        Account accountUsuario = accountOptional.get();
        String email = payload.get("email");

        Optional<Account> usuario = accountService.findByEmail(email);
        if (usuario.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El usuario con ese correo no fue encontrado");
        }

        Account account = usuario.get();
        account.setAlarmaError(true);  // Activa el campo de alarmaError
        accountService.saveAccount(account);

        String descripcion = "Usuario " + accountUsuario.getEmail() + " añadió a " + account.getEmail() + " a las alarmas de errores de conector.";
        auditLogService.recordAction(descripcion, accountUsuario);

        return ResponseEntity.ok("El usuario ha sido añadido correctamente a las alarmas de errores de conector.");
    }

    private String getStringRandomNumber() {
        int min = 10000000;
        int max = 99999999;
        int randomNumber = (int) ((Math.random() * (max - min)) + min);
        return String.valueOf(randomNumber);
    }

    public String getStringRandomEmployee() {
        int min = 0;
        int max = 99999999;
        int randomNumber = (int) ((Math.random() * (max - min)) + min);
        return String.format("%08d", randomNumber);
    }

}
