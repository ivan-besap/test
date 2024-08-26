package com.eVolGreen.eVolGreen.Controllers.UserController;

import com.eVolGreen.eVolGreen.DTOS.AccountDTO.AccountDTO;
import com.eVolGreen.eVolGreen.DTOS.RegisterDTO.AdminCompanyRegisterDTO;
import com.eVolGreen.eVolGreen.DTOS.RegisterDTO.AdminCompanyUpdateDTO;
import com.eVolGreen.eVolGreen.DTOS.UserDTO.AdminCompanyDTO;
import com.eVolGreen.eVolGreen.DTOS.UserDTO.CompanyDTO.CompanyUserRegisterDTO;
import com.eVolGreen.eVolGreen.Models.Account.Account;
import com.eVolGreen.eVolGreen.Models.Account.Location;
import com.eVolGreen.eVolGreen.Models.Account.PermissionCredential.Credential;
import com.eVolGreen.eVolGreen.Models.Account.PermissionCredential.Permission;
import com.eVolGreen.eVolGreen.Models.Account.PermissionCredential.PermissionCredential;
import com.eVolGreen.eVolGreen.Models.Account.TypeOfAccount.AccountCompany;
import com.eVolGreen.eVolGreen.Models.Account.TypeOfAccount.TypeAccounts;
import com.eVolGreen.eVolGreen.Models.User.Role;
import com.eVolGreen.eVolGreen.Models.User.subclassUser.AdminCompanyUser;
import com.eVolGreen.eVolGreen.Models.User.subclassUser.CompanyUser;
import com.eVolGreen.eVolGreen.Repositories.CredentialRepository;
import com.eVolGreen.eVolGreen.Repositories.LocationRepository;
import com.eVolGreen.eVolGreen.Repositories.PermissionCredentialRepository;
import com.eVolGreen.eVolGreen.Repositories.PermissionRepository;
import com.eVolGreen.eVolGreen.Services.AccountService.AccountCompanyService;
import com.eVolGreen.eVolGreen.Services.AccountService.AccountService;
import com.eVolGreen.eVolGreen.Services.AccountService.CredentialService;
import com.eVolGreen.eVolGreen.Services.DUserService.AdminCompanyUserService;
import com.eVolGreen.eVolGreen.Services.DUserService.CompanyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class AdminCompanyController {

    @Autowired
    private AdminCompanyUserService adminCompanyUserService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountCompanyService accountCompanyService;

    @Autowired
    private CompanyUserService companyUserService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private CredentialService credentialService;

    @Autowired
    private PermissionCredentialRepository permissionCredentialRepository;


    @GetMapping("/adminCompany/current/accounts")
    public ResponseEntity<List<AccountDTO>> getAccountsForAuthenticatedAdminCompanyUser(Authentication authentication) {
        // Obtener el AdminCompanyUser autenticado
        AdminCompanyUser authenticatedAdminCompanyUser = adminCompanyUserService.findByEmailAdminCompanyUser(authentication.getName());

        // Mapear las cuentas asociadas a DTOs
        List<AccountDTO> accountDTOList = authenticatedAdminCompanyUser.getCuentaPrincipal()
                .stream()
                .map(AccountDTO::new) // Convertir cada Account en un AccountDTO
                .collect(Collectors.toList());

        // Retornar la lista de AccountDTOs en la respuesta
        return new ResponseEntity<>(accountDTOList, HttpStatus.OK);
    }

    @GetMapping("/adminCompany/current")
    public AdminCompanyDTO getAdminCompany(Authentication authentication) {
        return adminCompanyUserService.getAdminCompanyRegisterDTOByEmailCurrent(authentication.getName());
    }

    @GetMapping("/adminCompany/current/accounts/true")
    public List<AccountDTO> getAccountDtoTrue(){
        List<AccountDTO> accountDTOList= accountService.findAll();
        List<AccountDTO> accountDTOListTrue=accountDTOList.stream().filter(AccountDTO::getActivo).collect(Collectors.toList());
        return  accountDTOListTrue;
    }

    @PostMapping("/adminCompany/registerAdminCompany")
    public ResponseEntity<Object> registerAdminCompany(@RequestBody AdminCompanyRegisterDTO adminCompanyRegister) {

        String mensaje = " ";
        if (adminCompanyRegister.getNombreCompañia().isBlank()) {
            mensaje = "El nombre de la compañia es requerido";
            return new ResponseEntity<>(mensaje, HttpStatus.FORBIDDEN);
        }
        if (adminCompanyRegister.getPasswordConfirmation().isBlank()) {
            mensaje = "El password es requerido";
            return new ResponseEntity<>(mensaje, HttpStatus.FORBIDDEN);
        }
        if (adminCompanyRegister.getRut() == null) {
            mensaje = "El rut es requerido";
            return new ResponseEntity<>(mensaje, HttpStatus.FORBIDDEN);
        }
        if (adminCompanyRegister.getTelefono() == null) {
            mensaje = "El telefono es requerido";
            return new ResponseEntity<>(mensaje, HttpStatus.FORBIDDEN);
        }
        if (adminCompanyRegister.getEmail().isBlank()) {
            mensaje = "El email es requerido";
            return new ResponseEntity<>(mensaje, HttpStatus.FORBIDDEN);
        }
        if (adminCompanyRegister.getPassword().isBlank()) {
            mensaje = "El password es requerido";
            return new ResponseEntity<>(mensaje, HttpStatus.FORBIDDEN);
        }
        if (adminCompanyRegister.getPassword().equals(adminCompanyRegister.getPasswordConfirmation()) == false) {
            mensaje = "Las contraseñas no coinciden";
            return new ResponseEntity<>(mensaje, HttpStatus.FORBIDDEN);
        }

        AdminCompanyUser AdminCompañia = new AdminCompanyUser(
                adminCompanyRegister.getEmail(),
                (passwordEncoder.encode(adminCompanyRegister.getPassword())),
                adminCompanyRegister.getTelefono(),
                adminCompanyRegister.getNombreCompañia(),
                adminCompanyRegister.getRut(),
                Role.ADMIN_COMPANY,
                false
        );
        adminCompanyUserService.saveAdminCompanyUser(AdminCompañia);

        CompanyUser Gerente = new CompanyUser(
                null,
                null,
                null,
                adminCompanyRegister.getNombreCompañia(),
                null,
                Role.COMPANY,
                false
        );
        companyUserService.saveCompanyUser(Gerente);

        String numeroDeCuentaAdminCompany = "AdminCompany-" + getStringRandomNumber();
        String nombreDeCuenta = "Cuenta principal de la Compañia" + adminCompanyRegister.getNombreCompañia();
        Account accountAdminCompany = new Account(
                numeroDeCuentaAdminCompany,
                nombreDeCuenta,
                LocalDate.now(),
                adminCompanyRegister.getEmail(),
                (passwordEncoder.encode(adminCompanyRegister.getPassword())),
                Gerente,
                AdminCompañia
        );
        accountService.saveAccount(accountAdminCompany);

        mensaje = "Registro exitoso";
        return new ResponseEntity<>(mensaje, HttpStatus.OK);
    }

    @PutMapping("/adminCompany/updateAdminCompany")
    public ResponseEntity<Object> updateAdminCompany(Authentication authentication,
                                                     @RequestBody AdminCompanyRegisterDTO updatedCompany) {

        // Obtener el usuario autenticado
        AdminCompanyUser authenticatedAdminCompanyUser = adminCompanyUserService.findByEmailAdminCompanyUser(authentication.getName());

        if (authenticatedAdminCompanyUser == null) {
            return new ResponseEntity<>("Usuario no encontrado", HttpStatus.NOT_FOUND);
        }

        // Validaciones de los campos del DTO
        if (updatedCompany.getNombreCompañia().isBlank()) {
            return new ResponseEntity<>("El nombre de la empresa es requerido", HttpStatus.FORBIDDEN);
        }
        if (updatedCompany.getRut() == null) {
            return new ResponseEntity<>("El rut es requerido", HttpStatus.FORBIDDEN);
        }
        if (updatedCompany.getTelefono() == null) {
            return new ResponseEntity<>("El telefono es requerido", HttpStatus.FORBIDDEN);
        }
        if (updatedCompany.getEmail().isBlank()) {
            return new ResponseEntity<>("El email es requerido", HttpStatus.FORBIDDEN);
        }
        if (updatedCompany.getPassword().isBlank()) {
            return new ResponseEntity<>("El password es requerido", HttpStatus.FORBIDDEN);
        }
        if (!updatedCompany.getPassword().equals(updatedCompany.getPasswordConfirmation())) {
            return new ResponseEntity<>("Las contraseñas no coinciden", HttpStatus.FORBIDDEN);
        }

        // Actualizar los datos del AdminCompanyUser autenticado
        authenticatedAdminCompanyUser.setNombreCompañia(updatedCompany.getNombreCompañia());
        authenticatedAdminCompanyUser.setRut(updatedCompany.getRut());
        authenticatedAdminCompanyUser.setEmail(updatedCompany.getEmail());
        authenticatedAdminCompanyUser.setPassword(passwordEncoder.encode(updatedCompany.getPassword()));
        authenticatedAdminCompanyUser.setTelefono(updatedCompany.getTelefono());
        authenticatedAdminCompanyUser.setActivo(true);
        authenticatedAdminCompanyUser.setRol(Role.ADMIN_COMPANY);

        // Guardar los cambios en el AdminCompanyUser autenticado usando el repositorio de User

        adminCompanyUserService.saveAdminCompanyUser(authenticatedAdminCompanyUser);

        // Actualizar todas las cuentas asociadas al AdminCompanyUser autenticado
        authenticatedAdminCompanyUser.getCuentaPrincipal().forEach(account -> {
            account.setNombreCuenta("Cuenta principal de la Compañia " + updatedCompany.getNombreCompañia());
            account.setPassword(passwordEncoder.encode(updatedCompany.getPassword()));
            account.setEmail(updatedCompany.getEmail());
            accountService.saveAccount(account);
        });

        return new ResponseEntity<>("Usuario actualizado con éxito", HttpStatus.OK);
    }

    @PutMapping("/adminCompany/updateAdminCompany&CompanyUser")
    public ResponseEntity<Object> updateCompany(Authentication authentication,
                                                @RequestBody AdminCompanyUpdateDTO adminCompanyUpdate) {

        String mensaje = " ";
        // Obtener el usuario autenticado
        AdminCompanyUser authenticatedAdminCompanyUser = adminCompanyUserService.findByEmailAdminCompanyUser(authentication.getName());

        // Validaciones de los campos del DTO
        if (adminCompanyUpdate.getNombreCompañia().isBlank()) {
            mensaje = "El nombre de la empresa es requerido";
            return new ResponseEntity<>(mensaje, HttpStatus.FORBIDDEN);
        }
        if (adminCompanyUpdate.getRut() == null) {
            mensaje = "El rut es requerido";
            return new ResponseEntity<>(mensaje, HttpStatus.FORBIDDEN);
        }
        if (adminCompanyUpdate.getTelefono() == null) {
            mensaje = "El teléfono es requerido";
            return new ResponseEntity<>(mensaje, HttpStatus.FORBIDDEN);
        }
        if (adminCompanyUpdate.getEmail().isBlank()) {
            mensaje = "El email es requerido";
            return new ResponseEntity<>(mensaje, HttpStatus.FORBIDDEN);
        }
        if (adminCompanyUpdate.getPassword().isBlank()) {
            mensaje = "El password es requerido";
            return new ResponseEntity<>(mensaje, HttpStatus.FORBIDDEN);
        }
        if(adminCompanyUpdate.getEmailCompañia().isBlank()) {
            mensaje = "El email de la Compañía es requerido";
            return new ResponseEntity<>(mensaje, HttpStatus.FORBIDDEN);
        }
        if (adminCompanyUpdate.getPasswordCompañia().isBlank()) {
            mensaje = "El Password de la Compañía es requerido";
            return new ResponseEntity<>(mensaje, HttpStatus.FORBIDDEN);
        }
        if(adminCompanyUpdate.getRutCompañia().isBlank()) {
            mensaje = "El RUT de la Compañía es requerido";
            return new ResponseEntity<>(mensaje, HttpStatus.FORBIDDEN);
        }

        // Actualizar los datos del AdminCompanyUser autenticado
        authenticatedAdminCompanyUser.setNombreCompañia(adminCompanyUpdate.getNombreCompañia());
        authenticatedAdminCompanyUser.setRut(adminCompanyUpdate.getRut());
        authenticatedAdminCompanyUser.setEmail(adminCompanyUpdate.getEmail());
        authenticatedAdminCompanyUser.setPassword(adminCompanyUpdate.getPassword());
        authenticatedAdminCompanyUser.setTelefono(adminCompanyUpdate.getTelefono());

        // Guardar cambios en la subclase
        adminCompanyUserService.saveAdminCompanyUser(authenticatedAdminCompanyUser);


        // Actualizar los datos del CompanyUser relacionado al Account
        CompanyUser gerente = authenticatedAdminCompanyUser.getCuentaPrincipal()
                .stream()
                .map(Account::getCompañia)
                .filter(companyUser -> companyUser != null)
                .findFirst()
                .orElse(null);

        if (gerente != null) {
            gerente.setEmail(adminCompanyUpdate.getEmailCompañia());
            gerente.setPassword(adminCompanyUpdate.getPasswordCompañia());
            gerente.setRut(adminCompanyUpdate.getRutCompañia());

            // Luego guardar en CompanyUserRepository
            companyUserService.saveCompanyUser(gerente);
        } else {
            mensaje = "No se encontró un CompanyUser asociado para actualizar";
            return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
        }

        mensaje = "Usuario y compañía actualizados con éxito";
        return new ResponseEntity<>(mensaje, HttpStatus.OK);
    }

    @DeleteMapping("/adminCompany/deleteAdminCompany")
    public ResponseEntity<Object> removeAdminCompany(Authentication authentication) {

        String mensaje = " ";

        // Obtener el usuario autenticado a través del servicio.
        AdminCompanyUser authenticatedAdminCompanyUser = adminCompanyUserService.findByEmailAdminCompanyUser(authentication.getName());

        // Comprobar si el usuario ya está desactivado.
        if (!authenticatedAdminCompanyUser.getActivo()) {
            mensaje = "El usuario ya está desactivado";
            return new ResponseEntity<>(mensaje, HttpStatus.BAD_REQUEST);
        }

        // Desactivar el usuario principal.
        authenticatedAdminCompanyUser.setActivo(false);

        // Desactivar todas las cuentas asociadas a este usuario.
        authenticatedAdminCompanyUser.getCuentaPrincipal().forEach(cuenta -> {
            cuenta.setActivo(false);
            accountService.saveAccount(cuenta);
        });

        // Guardar el usuario con el estado actualizado.
        adminCompanyUserService.saveAdminCompanyUser(authenticatedAdminCompanyUser);

        // Devolver una respuesta exitosa.
        mensaje = "El usuario ha sido desactivado exitosamente";
        return new ResponseEntity<>(mensaje, HttpStatus.OK);
    }

    @PostMapping("/adminCompany/createCompanyUser")
    public ResponseEntity<Object> createCompanyUser(Authentication authentication,
                                                    @RequestBody CompanyUserRegisterDTO companyUserDTO) {

        String mensaje = " ";
        // Obtener el usuario autenticado
        AdminCompanyUser authenticatedAdminCompanyUser = adminCompanyUserService.findByEmailAdminCompanyUser(authentication.getName());

        if (authenticatedAdminCompanyUser == null) {
            mensaje = "AdminCompanyUser no encontrado";
            return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
        }
        if (companyUserDTO.getNombreCompañia() == null || companyUserDTO.getNombreCompañia().isBlank()) {
            mensaje = "El nombre de la Compañía es requerido";
            return new ResponseEntity<>(mensaje, HttpStatus.FORBIDDEN);
        }
        if (companyUserDTO.getTelefono() == null) {
            mensaje = "El telefono es requerido";
            return new ResponseEntity<>(mensaje, HttpStatus.FORBIDDEN);
        }
        if (companyUserDTO.getEmail().isBlank()) {
            mensaje = "El email es requerido";
            return new ResponseEntity<>(mensaje, HttpStatus.FORBIDDEN);
        }
        if (companyUserDTO.getPassword().isBlank()) {
            mensaje = "El password es requerido";
            return new ResponseEntity<>(mensaje, HttpStatus.FORBIDDEN);
        }
        if (companyUserDTO.getRut().isBlank()) {
            mensaje = "El RUT de la Compañía es requerido";
            return new ResponseEntity<>(mensaje, HttpStatus.FORBIDDEN);
        }

        // Verificar si el email ya está registrado
        if (companyUserService.existsByEmail(companyUserDTO.getEmail())) {
            mensaje = "El email ya está registrado en el sistema";
            return new ResponseEntity<>(mensaje, HttpStatus.CONFLICT);
        }

        // Crear y guardar las credenciales
        Credential administrador = new Credential("Administrador", true);
        credentialService.save(administrador);

        Credential electroBombero = new Credential("ElectroBombero", true);
        credentialService.save(electroBombero);

        Credential reportero = new Credential("Reportero", true);
        credentialService.save(reportero);

        Set<Credential> credentials = new HashSet<>();
        credentials.add(administrador);
        credentials.add(electroBombero);
        credentials.add(reportero);

        // Crear instancia de PermissionCredential
        PermissionCredential administradorCredencial = new PermissionCredential(
                administrador, // Credential previamente creada y guardada
                LocalDateTime.now(),
                LocalDateTime.now().plusYears(1),
                true,
                "Sistema",
                "Credenciales de administrador:" + " " + companyUserDTO.getEmail() + " "+ "Administrador, ElectroBombero y Reportero"
        );

        // Guardar PermissionCredential antes de añadir las Permissions
        permissionCredentialRepository.save(administradorCredencial);

        Set<PermissionCredential> permisosCredencialesDefault = new HashSet<>();
        permisosCredencialesDefault.add(administradorCredencial);

        // Crear y guardar permisos
        Permission chargerCreate = new Permission("charger_create", "Permitir crear un nuevo cargador");
        chargerCreate.setPermisonCredencial(administradorCredencial); // Establecer relación bidireccional
        permissionRepository.save(chargerCreate);
        administradorCredencial.getPermiso().add(chargerCreate); // Agregar permiso a PermissionCredential

        Permission chargerDelete = new Permission("charger_delete", "Permitir borrar un cargador");
        chargerDelete.setPermisonCredencial(administradorCredencial);
        permissionRepository.save(chargerDelete);
        administradorCredencial.getPermiso().add(chargerDelete);

        Permission chargerEdit = new Permission("charger_edit", "Permitir editar un cargador");
        chargerEdit.setPermisonCredencial(administradorCredencial);
        permissionRepository.save(chargerEdit);
        administradorCredencial.getPermiso().add(chargerEdit);

        Permission chargingStationView = new Permission("charging_station_view", "Permitir ver las cargas por terminal");
        chargingStationView.setPermisonCredencial(administradorCredencial);
        permissionRepository.save(chargingStationView);
        administradorCredencial.getPermiso().add(chargingStationView);

        Permission ocppChargerCommands = new Permission("ocpp_charger_commands", "Permitir comandos OCPP - Cargador");
        ocppChargerCommands.setPermisonCredencial(administradorCredencial);
        permissionRepository.save(ocppChargerCommands);
        administradorCredencial.getPermiso().add(ocppChargerCommands);

        Permission ocppConnectorCommands = new Permission("ocpp_connector_commands", "Permitir comandos OCPP - Conector");
        ocppConnectorCommands.setPermisonCredencial(administradorCredencial);
        permissionRepository.save(ocppConnectorCommands);
        administradorCredencial.getPermiso().add(ocppConnectorCommands);

        Permission ocppChargingStationCommands = new Permission("ocpp_charging_station_commands", "Permitir comandos OCPP - Carga por terminal");
        ocppChargingStationCommands.setPermisonCredencial(administradorCredencial);
        permissionRepository.save(ocppChargingStationCommands);
        administradorCredencial.getPermiso().add(ocppChargingStationCommands);

        Permission driverCreate = new Permission("driver_create", "Permitir crear conductores");
        driverCreate.setPermisonCredencial(administradorCredencial);
        permissionRepository.save(driverCreate);
        administradorCredencial.getPermiso().add(driverCreate);

        Permission driverDelete = new Permission("drive_delete", "Permitir borrar conductores");
        driverDelete.setPermisonCredencial(administradorCredencial);
        permissionRepository.save(driverDelete);
        administradorCredencial.getPermiso().add(driverDelete);

        Permission driverEdit = new Permission("driver_edit", "Permitir editar conductores");
        driverEdit.setPermisonCredencial(administradorCredencial);
        permissionRepository.save(driverEdit);
        administradorCredencial.getPermiso().add(driverEdit);

        Permission driverDisable = new Permission("driver_disable", "Permitir inhabilitar conductores");
        driverDisable.setPermisonCredencial(administradorCredencial);
        permissionRepository.save(driverDisable);
        administradorCredencial.getPermiso().add(driverDisable);

        Permission driverView = new Permission("driver_view", "Permitir ver conductores");
        driverView.setPermisonCredencial(administradorCredencial);
        permissionRepository.save(driverView);
        administradorCredencial.getPermiso().add(driverView);

        Permission chargerSettingsView = new Permission("charger_settings_view", "Permitir ver configuraciones de cargador");
        chargerSettingsView.setPermisonCredencial(administradorCredencial);
        permissionRepository.save(chargerSettingsView);
        administradorCredencial.getPermiso().add(chargerSettingsView);

        Permission powerControlCreate = new Permission("power_control_create", "Permitir crear control de potencia");
        powerControlCreate.setPermisonCredencial(administradorCredencial);
        permissionRepository.save(powerControlCreate);
        administradorCredencial.getPermiso().add(powerControlCreate);

        Permission powerControlEdit = new Permission("power_control_edit", "Permitir editar control de potencia");
        powerControlEdit.setPermisonCredencial(administradorCredencial);
        permissionRepository.save(powerControlEdit);
        administradorCredencial.getPermiso().add(powerControlEdit);

        Permission powerControlView = new Permission("power_control_view", "Permitir ver control de potencia");
        powerControlView.setPermisonCredencial(administradorCredencial);
        permissionRepository.save(powerControlView);
        administradorCredencial.getPermiso().add(powerControlView);

        Permission dashboardView = new Permission("dashboard_view", "Permitir ver el dashboard");
        dashboardView.setPermisonCredencial(administradorCredencial);
        permissionRepository.save(dashboardView);
        administradorCredencial.getPermiso().add(dashboardView);

        Permission scheduledDisablesCreate = new Permission("scheduled_disables_create", "Permitir crear inhabilitaciones programadas");
        scheduledDisablesCreate.setPermisonCredencial(administradorCredencial);
        permissionRepository.save(scheduledDisablesCreate);
        administradorCredencial.getPermiso().add(scheduledDisablesCreate);

        Permission scheduledDisablesEdit = new Permission("scheduled_disables_edit", "Permitir editar inhabilitaciones programadas");
        scheduledDisablesEdit.setPermisonCredencial(administradorCredencial);
        permissionRepository.save(scheduledDisablesEdit);
        administradorCredencial.getPermiso().add(scheduledDisablesEdit);

        Permission scheduledDisablesView = new Permission("scheduled_disables_view", "Permitir ver inhabilitaciones programadas");
        scheduledDisablesView.setPermisonCredencial(administradorCredencial);
        permissionRepository.save(scheduledDisablesView);
        administradorCredencial.getPermiso().add(scheduledDisablesView);

        Permission ocppEditSettings = new Permission("ocpp_edit_settings", "Permitir editar configuraciones OCPP");
        ocppEditSettings.setPermisonCredencial(administradorCredencial);
        permissionRepository.save(ocppEditSettings);
        administradorCredencial.getPermiso().add(ocppEditSettings);

        Permission ocppViewSettings = new Permission("ocpp_view_settings", "Permitir ver configuraciones OCPP");
        ocppViewSettings.setPermisonCredencial(administradorCredencial);
        permissionRepository.save(ocppViewSettings);
        administradorCredencial.getPermiso().add(ocppViewSettings);

        Permission peakShavingCreate = new Permission("peak_shaving_create", "Permitir crear peak shaving");
        peakShavingCreate.setPermisonCredencial(administradorCredencial);
        permissionRepository.save(peakShavingCreate);
        administradorCredencial.getPermiso().add(peakShavingCreate);

        Permission peakShavingEdit = new Permission("peak_shaving_edit", "Permitir editar peak shaving");
        peakShavingEdit.setPermisonCredencial(administradorCredencial);
        permissionRepository.save(peakShavingEdit);
        administradorCredencial.getPermiso().add(peakShavingEdit);

        Permission peakShavingView = new Permission("peak_shaving_view", "Permitir ver peak shaving");
        peakShavingView.setPermisonCredencial(administradorCredencial);
        permissionRepository.save(peakShavingView);
        administradorCredencial.getPermiso().add(peakShavingView);

        Permission chargeRecordsView = new Permission("charge_records_view", "Permitir ver registros de carga");
        chargeRecordsView.setPermisonCredencial(administradorCredencial);
        permissionRepository.save(chargeRecordsView);
        administradorCredencial.getPermiso().add(chargeRecordsView);

        Permission rolesDelete = new Permission("roles_delete", "Permitir borrar roles");
        rolesDelete.setPermisonCredencial(administradorCredencial);
        permissionRepository.save(rolesDelete);
        administradorCredencial.getPermiso().add(rolesDelete);

        Permission rolesCreate = new Permission("roles_create", "Permitir crear roles");
        rolesCreate.setPermisonCredencial(administradorCredencial);
        permissionRepository.save(rolesCreate);
        administradorCredencial.getPermiso().add(rolesCreate);

        Permission rolesEdit = new Permission("roles_edit", "Permitir editar roles");
        rolesEdit.setPermisonCredencial(administradorCredencial);
        permissionRepository.save(rolesEdit);
        administradorCredencial.getPermiso().add(rolesEdit);

        Permission rolesView = new Permission("roles_view", "Permitir ver roles");
        rolesView.setPermisonCredencial(administradorCredencial);
        permissionRepository.save(rolesView);
        administradorCredencial.getPermiso().add(rolesView);

        Permission terminalsView = new Permission("charging_station_view", "Permitir ver tecles");
        terminalsView.setPermisonCredencial(administradorCredencial);
        permissionRepository.save(terminalsView);
        administradorCredencial.getPermiso().add(terminalsView);

        Permission locationsCreate = new Permission("locations_create", "Permitir crear ubicaciones");
        locationsCreate.setPermisonCredencial(administradorCredencial);
        permissionRepository.save(locationsCreate);
        administradorCredencial.getPermiso().add(locationsCreate);

        Permission locationsView = new Permission("locations_view", "Permitir ver ubicaciones");
        locationsView.setPermisonCredencial(administradorCredencial);
        permissionRepository.save(locationsView);
        administradorCredencial.getPermiso().add(locationsView);

        Permission electricFirefighter = new Permission("electric_firefighter", "Permitir acceso de Electrobombero");
        electricFirefighter.setPermisonCredencial(administradorCredencial);
        permissionRepository.save(electricFirefighter);
        administradorCredencial.getPermiso().add(electricFirefighter);

        Permission chargersByUser = new Permission("chargers_by_user", "Permitir ver cargadores por usuario");
        chargersByUser.setPermisonCredencial(administradorCredencial);
        permissionRepository.save(chargersByUser);
        administradorCredencial.getPermiso().add(chargersByUser);

        Permission chargersByTerminal = new Permission("chargers_by_terminal", "Permitir ver cargadores por terminal");
        chargersByTerminal.setPermisonCredencial(administradorCredencial);
        permissionRepository.save(chargersByTerminal);
        administradorCredencial.getPermiso().add(chargersByTerminal);

        Permission clientView = new Permission("client_view", "Permitir vista clientes");
        clientView.setPermisonCredencial(administradorCredencial);
        permissionRepository.save(clientView);
        administradorCredencial.getPermiso().add(clientView);

        Permission employeeView = new Permission("employee_view", "Permitir vista encargado");
        employeeView.setPermisonCredencial(administradorCredencial);
        permissionRepository.save(employeeView);
        administradorCredencial.getPermiso().add(employeeView);

        permissionCredentialRepository.save(administradorCredencial);

        // Crear la entidad CompanyUser
        CompanyUser Gerente = new CompanyUser(
                companyUserDTO.getEmail(),
                passwordEncoder.encode(companyUserDTO.getPassword()),
                companyUserDTO.getTelefono(),
                companyUserDTO.getNombreCompañia(),
                companyUserDTO.getRut(),
                Role.COMPANY,
                false
        );
        // Persistir el CompanyUser en la base de datos
        companyUserService.saveCompanyUser(Gerente);

        // Crear la entidad AccountCompany
        Location UbicaccionCuentaCompañiaDefault = new Location("Default");
        locationRepository.save(UbicaccionCuentaCompañiaDefault);

        String numeroDeCuentaCompany = "Company-" + getStringRandomNumber();
        String nombreDeCuenta = "CuentaCompañia " + companyUserDTO.getNombreCompañia();
        AccountCompany newAccountCompany = new AccountCompany(
                numeroDeCuentaCompany,
                nombreDeCuenta,
                companyUserDTO.getRut(),
                companyUserDTO.getEmail(),
                passwordEncoder.encode(companyUserDTO.getPassword()),
                Role.COMPANY,
                TypeAccounts.Company,
                UbicaccionCuentaCompañiaDefault
        );

        Account cuentaPrincipal = authenticatedAdminCompanyUser.getCuentaPrincipal().stream().findFirst().get();

        newAccountCompany.setCuentaPrincipal(cuentaPrincipal);
        newAccountCompany.setCompañia(Gerente);
        newAccountCompany.setCredenciales(credentials);
        newAccountCompany.setPermisoCredenciales(permisosCredencialesDefault);
        administrador.setCuentaCompañia(newAccountCompany);
        electroBombero.setCuentaCompañia(newAccountCompany);
        reportero.setCuentaCompañia(newAccountCompany);

        newAccountCompany.addPermission(chargerCreate);
        chargerCreate.setCuentaCompañia(newAccountCompany);
        newAccountCompany.addPermission(chargerDelete);
        chargerDelete.setCuentaCompañia(newAccountCompany);
        newAccountCompany.addPermission(chargerEdit);
        chargerEdit.setCuentaCompañia(newAccountCompany);
        newAccountCompany.addPermission(chargingStationView);
        chargingStationView.setCuentaCompañia(newAccountCompany);
        newAccountCompany.addPermission(ocppChargerCommands);
        ocppChargerCommands.setCuentaCompañia(newAccountCompany);
        newAccountCompany.addPermission(ocppConnectorCommands);
        ocppConnectorCommands.setCuentaCompañia(newAccountCompany);
        newAccountCompany.addPermission(ocppChargingStationCommands);
        ocppChargingStationCommands.setCuentaCompañia(newAccountCompany);
        newAccountCompany.addPermission(driverCreate);
        driverCreate.setCuentaCompañia(newAccountCompany);
        newAccountCompany.addPermission(driverDelete);
        driverDelete.setCuentaCompañia(newAccountCompany);
        newAccountCompany.addPermission(driverEdit);
        driverEdit.setCuentaCompañia(newAccountCompany);
        newAccountCompany.addPermission(driverDisable);
        driverDisable.setCuentaCompañia(newAccountCompany);
        newAccountCompany.addPermission(driverView);
        driverView.setCuentaCompañia(newAccountCompany);
        newAccountCompany.addPermission(chargerSettingsView);
        chargerSettingsView.setCuentaCompañia(newAccountCompany);
        newAccountCompany.addPermission(powerControlCreate);
        powerControlCreate.setCuentaCompañia(newAccountCompany);
        newAccountCompany.addPermission(powerControlEdit);
        powerControlEdit.setCuentaCompañia(newAccountCompany);
        newAccountCompany.addPermission(powerControlView);
        powerControlView.setCuentaCompañia(newAccountCompany);
        newAccountCompany.addPermission(dashboardView);
        dashboardView.setCuentaCompañia(newAccountCompany);
        newAccountCompany.addPermission(scheduledDisablesCreate);
        scheduledDisablesCreate.setCuentaCompañia(newAccountCompany);
        newAccountCompany.addPermission(scheduledDisablesEdit);
        scheduledDisablesEdit.setCuentaCompañia(newAccountCompany);
        newAccountCompany.addPermission(scheduledDisablesView);
        scheduledDisablesView.setCuentaCompañia(newAccountCompany);
        newAccountCompany.addPermission(ocppEditSettings);
        ocppEditSettings.setCuentaCompañia(newAccountCompany);
        newAccountCompany.addPermission(ocppViewSettings);
        ocppViewSettings.setCuentaCompañia(newAccountCompany);
        newAccountCompany.addPermission(peakShavingCreate);
        peakShavingCreate.setCuentaCompañia(newAccountCompany);
        newAccountCompany.addPermission(peakShavingEdit);
        peakShavingEdit.setCuentaCompañia(newAccountCompany);
        newAccountCompany.addPermission(peakShavingView);
        peakShavingView.setCuentaCompañia(newAccountCompany);
        newAccountCompany.addPermission(chargeRecordsView);
        chargeRecordsView.setCuentaCompañia(newAccountCompany);
        newAccountCompany.addPermission(rolesDelete);
        rolesDelete.setCuentaCompañia(newAccountCompany);
        newAccountCompany.addPermission(rolesCreate);
        rolesCreate.setCuentaCompañia(newAccountCompany);
        newAccountCompany.addPermission(rolesEdit);
        rolesEdit.setCuentaCompañia(newAccountCompany);
        newAccountCompany.addPermission(rolesView);
        rolesView.setCuentaCompañia(newAccountCompany);
        newAccountCompany.addPermission(terminalsView);
        terminalsView.setCuentaCompañia(newAccountCompany);
        newAccountCompany.addPermission(locationsCreate);
        locationsCreate.setCuentaCompañia(newAccountCompany);
        newAccountCompany.addPermission(locationsView);
        locationsView.setCuentaCompañia(newAccountCompany);
        newAccountCompany.addPermission(electricFirefighter);
        electricFirefighter.setCuentaCompañia(newAccountCompany);
        newAccountCompany.addPermission(chargersByUser);
        chargersByUser.setCuentaCompañia(newAccountCompany);
        newAccountCompany.addPermission(chargersByTerminal);
        chargersByTerminal.setCuentaCompañia(newAccountCompany);
        newAccountCompany.addPermission(clientView);
        clientView.setCuentaCompañia(newAccountCompany);
        newAccountCompany.addPermission(employeeView);
        employeeView.setCuentaCompañia(newAccountCompany);


        administradorCredencial.setCuentaCompañia(newAccountCompany); // Asociar con AccountCompany

        // Persistir el AccountCompany en la base de datos antes de asignarlo a PermissionCredential
        accountCompanyService.saveCuentaCompañia(newAccountCompany);

        // Actualizar la relación entre CompanyUser y AccountCompany
        Gerente.getCuentaCompañia().add(newAccountCompany);
        companyUserService.saveCompanyUser(Gerente);


        // Persistir la relación PermissionCredential
        accountCompanyService.saveCuentaCompañia(newAccountCompany);

        mensaje = "Se ha creado con éxito CompanyUser con una cuenta por defecto";
        return new ResponseEntity<>(mensaje, HttpStatus.CREATED);

    }



//    public void asignarPermisosPorDefecto(AccountCompany accountCompany) {
//
//        // Crear una credencial de administrador
//        Credential administrador = new Credential("Administrador", true);
//        // Guarda la credencial
//        credentialService.save(administrador);
//
//        PermissionCredential permisoCredenciales = new PermissionCredential(
//                administrador,
//                LocalDateTime.now(),
//                LocalDateTime.now().plusYears(1),
//                true,
//                "Default",
//                "Permisos por defecto"
//        );
//        accountCompany.addPermissionCredential(permisoCredenciales);
//        permisoCredenciales.setCuentaCompañia(accountCompany);
//
//        permissionCredentialRepository.save(permisoCredenciales);
//
//
//        Set<Permission> permisosPorDefecto = new HashSet<>();
//        // Añadir permisos por defecto
//            Permission chargerCreate = new Permission("charger_create", "Permitir crear un nuevo cargador");
//			permissionRepository.save(chargerCreate);
//            permisosPorDefecto.add(chargerCreate);
//			Permission chargerDelete = new Permission("charger_delete", "Permitir borrar un cargador");
//			permissionRepository.save(chargerDelete);
//            permisosPorDefecto.add(chargerDelete);
//			Permission chargerEdit = new Permission("charger_edit", "Permitir editar un cargador");
//			permissionRepository.save(chargerEdit);
//            permisosPorDefecto.add(chargerEdit);
//			Permission chargingStationView = new Permission("charging_station_view", "Permitir ver las cargas por terminal");
//			permissionRepository.save(chargingStationView);
//            permisosPorDefecto.add(chargingStationView);
//			Permission ocppChargerCommands = new Permission("ocpp_charger_commands", "Permitir comandos OCPP - Cargador");
//			permissionRepository.save(ocppChargerCommands);
//            permisosPorDefecto.add(ocppChargerCommands);
//			Permission ocppConnectorCommands = new Permission("ocpp_connector_commands", "Permitir comandos OCPP - Conector");
//			permissionRepository.save(ocppConnectorCommands);
//            permisosPorDefecto.add(ocppConnectorCommands);
//			Permission ocppChargingStationCommands = new Permission("ocpp_charging_station_commands", "Permitir comandos OCPP - Carga por terminal");
//			permissionRepository.save(ocppChargingStationCommands);
//            permisosPorDefecto.add(ocppChargingStationCommands);
//			Permission driverCreate = new Permission("driver_create", "Permitir crear conductores");
//			permissionRepository.save(driverCreate);
//            permisosPorDefecto.add(driverCreate);
//			Permission driverDelete = new Permission("driver_delete", "Permitir borrar conductores");
//			permissionRepository.save(driverDelete);
//            permisosPorDefecto.add(driverDelete);
//			Permission driverEdit = new Permission("driver_edit", "Permitir editar conductores");
//			permissionRepository.save(driverEdit);
//            permisosPorDefecto.add(driverEdit);
//			Permission driverDisable = new Permission("driver_disable", "Permitir inhabilitar conductores");
//			permissionRepository.save(driverDisable);
//            permisosPorDefecto.add(driverDisable);
//			Permission driverView = new Permission("driver_view", "Permitir ver conductores");
//			permissionRepository.save(driverView);
//            permisosPorDefecto.add(driverView);
//			Permission chargerSettingsView = new Permission("charger_settings_view", "Permitir ver configuraciones de cargador");
//			permissionRepository.save(chargerSettingsView);
//            permisosPorDefecto.add(chargerSettingsView);
//			Permission powerControlCreate = new Permission("power_control_create", "Permitir crear control de potencia");
//			permissionRepository.save(powerControlCreate);
//            permisosPorDefecto.add(powerControlCreate);
//			Permission powerControlEdit = new Permission("power_control_edit", "Permitir editar control de potencia");
//			permissionRepository.save(powerControlEdit);
//            permisosPorDefecto.add(powerControlEdit);
//			Permission powerControlView = new Permission("power_control_view", "Permitir ver control de potencia");
//			permissionRepository.save(powerControlView);
//            permisosPorDefecto.add(powerControlView);
//			Permission dashboardView = new Permission("dashboard_view", "Permitir ver el dashboard");
//			permissionRepository.save(dashboardView);
//            permisosPorDefecto.add(dashboardView);
//			Permission scheduledDisablesCreate = new Permission("scheduled_disables_create", "Permitir crear inhabilitaciones programadas");
//			permissionRepository.save(scheduledDisablesCreate);
//            permisosPorDefecto.add(scheduledDisablesCreate);
//			Permission scheduledDisablesEdit = new Permission("scheduled_disables_edit", "Permitir editar inhabilitaciones programadas");
//			permissionRepository.save(scheduledDisablesEdit);
//            permisosPorDefecto.add(scheduledDisablesEdit);
//			Permission scheduledDisablesView = new Permission("scheduled_disables_view", "Permitir ver inhabilitaciones programadas");
//			permissionRepository.save(scheduledDisablesView);
//            permisosPorDefecto.add(scheduledDisablesView);
//			Permission ocppEditSettings = new Permission("ocpp_edit_settings", "Permitir editar configuraciones OCPP");
//			permissionRepository.save(ocppEditSettings);
//            permisosPorDefecto.add(ocppEditSettings);
//			Permission ocppViewSettings = new Permission("ocpp_view_settings", "Permitir ver configuraciones OCPP");
//			permissionRepository.save(ocppViewSettings);
//            permisosPorDefecto.add(ocppViewSettings);
//			Permission peakShavingCreate = new Permission("peak_shaving_create", "Permitir crear peak shaving");
//			permissionRepository.save(peakShavingCreate);
//            permisosPorDefecto.add(peakShavingCreate);
//			Permission peakShavingEdit = new Permission("peak_shaving_edit", "Permitir editar peak shaving");
//			permissionRepository.save(peakShavingEdit);
//            permisosPorDefecto.add(peakShavingEdit);
//			Permission peakShavingView = new Permission("peak_shaving_view", "Permitir ver peak shaving");
//			permissionRepository.save(peakShavingView);
//            permisosPorDefecto.add(peakShavingView);
//			Permission chargeRecordsView = new Permission("charge_records_view", "Permitir ver registros de carga");
//			permissionRepository.save(chargeRecordsView);
//            permisosPorDefecto.add(chargeRecordsView);
//			Permission rolesDelete = new Permission("roles_delete", "Permitir borrar roles");
//			permissionRepository.save(rolesDelete);
//            permisosPorDefecto.add(rolesDelete);
//			Permission rolesCreate = new Permission("roles_create", "Permitir crear roles");
//			permissionRepository.save(rolesCreate);
//            permisosPorDefecto.add(rolesCreate);
//			Permission rolesEdit = new Permission("roles_edit", "Permitir editar roles");
//			permissionRepository.save(rolesEdit);
//            permisosPorDefecto.add(rolesEdit);
//			Permission rolesView = new Permission("roles_view", "Permitir ver roles");
//			permissionRepository.save(rolesView);
//            permisosPorDefecto.add(rolesView);
//			Permission terminalsView = new Permission("charging_station_view", "Permitir ver tecles");
//			permissionRepository.save(terminalsView);
//            permisosPorDefecto.add(terminalsView);
//			Permission locationsCreate = new Permission("locations_create", "Permitir crear ubicaciones");
//			permissionRepository.save(locationsCreate);
//            permisosPorDefecto.add(locationsCreate);
//			Permission locationsView = new Permission("locations_view", "Permitir ver ubicaciones");
//			permissionRepository.save(locationsView);
//            permisosPorDefecto.add(locationsView);
//			Permission electricFirefighter = new Permission("electric_firefighter", "Permitir acceso de Electrobombero");
//			permissionRepository.save(electricFirefighter);
//            permisosPorDefecto.add(electricFirefighter);
//			Permission chargersByUser = new Permission("chargers_by_user", "Permitir ver cargadores por usuario");
//			permissionRepository.save(chargersByUser);
//            permisosPorDefecto.add(chargersByUser);
//			Permission chargersByTerminal = new Permission("chargers_by_terminal", "Permitir ver cargadores por terminal");
//			permissionRepository.save(chargersByTerminal);
//            permisosPorDefecto.add(chargersByTerminal);
//			Permission clientView = new Permission("client_view", "Permitir vista clientes");
//			permissionRepository.save(clientView);
//            permisosPorDefecto.add(clientView);
//			Permission employeeView = new Permission("employee_view", "Permitir vista encargado");
//			permissionRepository.save(employeeView);
//            permisosPorDefecto.add(employeeView);
//
//        // Asignar los permisos al AccountCompany
//        permisosPorDefecto.forEach(permission -> {
//            permission.setCuentaCompañia(accountCompany);
//            accountCompany.addPermission(permission);
//        });
//
//
//
//
//
//        // Guarda el AccountCompany si es necesario
//        accountCompanyService.saveCuentaCompañia(accountCompany);
//    }

    private String getStringRandomNumber() {
        int min = 10000000;
        int max = 99999999;
        int randomNumber = (int) ((Math.random() * (max - min)) + min);
        return String.valueOf(randomNumber);
    }
}
