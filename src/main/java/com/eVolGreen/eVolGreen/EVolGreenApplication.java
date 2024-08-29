package com.eVolGreen.eVolGreen;

import com.eVolGreen.eVolGreen.Models.Account.Account;
import com.eVolGreen.eVolGreen.Models.Account.Location;
import com.eVolGreen.eVolGreen.Models.Account.PermissionCredential.Credential;
import com.eVolGreen.eVolGreen.Models.Account.PermissionCredential.Permission;
import com.eVolGreen.eVolGreen.Models.Account.PermissionCredential.PermissionCredential;
import com.eVolGreen.eVolGreen.Models.Account.TypeOfAccount.AccountCompany;
import com.eVolGreen.eVolGreen.Models.Account.TypeOfAccount.TypeAccounts;
import com.eVolGreen.eVolGreen.Models.ChargingStation.Charger.ChargerManufacturer;
import com.eVolGreen.eVolGreen.Models.ChargingStation.Charger.ChargerModel;
import com.eVolGreen.eVolGreen.Models.Ocpp.OcppClient;
import com.eVolGreen.eVolGreen.Models.User.Role;
import com.eVolGreen.eVolGreen.Models.User.subclassUser.CompanyUser;
import com.eVolGreen.eVolGreen.Repositories.*;
import com.eVolGreen.eVolGreen.Services.ChargingStationService.ChargerManufacturerService;
import com.eVolGreen.eVolGreen.Services.ChargingStationService.ChargerModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class EVolGreenApplication {

	@Autowired
	public PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(EVolGreenApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData(CarRepository carRepository,
									  DeviceIdentifierRepository deviceIdentifierRepository,
									  TransactionRepository transactionRepository,
									  FeeRepository feeRepository,
									  AccountRepository accountRepository,
									  CompanyUserRepository companyUserRepository,
									  EmployeeUserRepository employeeUserRepository,
									  ChargerRepository chargerRepository,
									  ChargingStationRepository chargingStationRepository,
									  ConnectorRepository connectorRepository,
									  ChargingUnitRepository chargingUnitRepository,
									  ReservationRepository reservationRepository,
									  CredentialRepository credentialRepository,
									  PermissionRepository permissionRepository,
									  AccountEmployeeRepository accountEmployeeRepository,
									  AccountCompanyRepository accountCompanyRepository,
									  CompanyEmployeeRelationRepository companyEmployeeRelationRepository,
									  CompanyClientRelationRepository companyClientRelationRepository, PermissionCredentialRepository permissionCredentialRepository,
									  ClientUserRepository clientUserRepository, LocationRepository locationRepository, ChargerManufacturerService chargerManufacturerService, ChargerModelService chargerModelService) {
		return args -> {

			/*

//			// Crear y guardar las credenciales
			Credential administrador = new Credential("Administrador", true);
			credentialRepository.save(administrador);

			Credential electroBombero = new Credential("ElectroBombero", true);
			credentialRepository.save(electroBombero);

			Credential reportero = new Credential("Reportero", true);
			credentialRepository.save(reportero);


			// Crear instancia de PermissionCredential
			PermissionCredential administradorCredencial = new PermissionCredential(
					administrador, // Credential previamente creada y guardada
					LocalDateTime.now(),
					LocalDateTime.now().plusYears(1),
					true,
					"Sistema",
					"Credenciales de administrador"
			);

			// Guardar PermissionCredential antes de añadir las Permissions
			permissionCredentialRepository.save(administradorCredencial);


//			// Crear y guardar permisos
			Permission chargerCreate = new Permission("charger_create", "Permitir crear un nuevo cargador");
			chargerCreate.setPermisonCredencial(administradorCredencial); // Establecer relación bidireccional
			administradorCredencial.getPermiso().add(chargerCreate); // Agregar permiso a PermissionCredential
			permissionRepository.save(chargerCreate);

			Permission chargerDelete = new Permission("charger_delete", "Permitir borrar un cargador");
			chargerDelete.setPermisonCredencial(administradorCredencial);
			administradorCredencial.getPermiso().add(chargerDelete);
			permissionRepository.save(chargerDelete);
			Permission chargerEdit = new Permission("charger_edit", "Permitir editar un cargador");
			chargerEdit.setPermisonCredencial(administradorCredencial);
			administradorCredencial.getPermiso().add(chargerEdit);
			permissionRepository.save(chargerEdit);
			Permission chargingStationView = new Permission("charging_station_view", "Permitir ver las cargas por terminal");
			chargingStationView.setPermisonCredencial(administradorCredencial);
			administradorCredencial.getPermiso().add(chargingStationView);
			permissionRepository.save(chargingStationView);
			Permission ocppChargerCommands = new Permission("ocpp_charger_commands", "Permitir comandos OCPP - Cargador");
			ocppChargerCommands.setPermisonCredencial(administradorCredencial);
			administradorCredencial.getPermiso().add(ocppChargerCommands);
			permissionRepository.save(ocppChargerCommands);
			Permission ocppConnectorCommands = new Permission("ocpp_connector_commands", "Permitir comandos OCPP - Conector");
			ocppConnectorCommands.setPermisonCredencial(administradorCredencial);
			administradorCredencial.getPermiso().add(ocppConnectorCommands);
			permissionRepository.save(ocppConnectorCommands);
			Permission ocppChargingStationCommands = new Permission("ocpp_charging_station_commands", "Permitir comandos OCPP - Carga por terminal");
			ocppChargingStationCommands.setPermisonCredencial(administradorCredencial);
			administradorCredencial.getPermiso().add(ocppChargingStationCommands);
			permissionRepository.save(ocppChargingStationCommands);
			Permission driverCreate = new Permission("driver_create", "Permitir crear conductores");
			driverCreate.setPermisonCredencial(administradorCredencial);
			administradorCredencial.getPermiso().add(driverCreate);
			permissionRepository.save(driverCreate);
			Permission driverDelete = new Permission("drive_delete", "Permitir borrar conductores");
			driverDelete.setPermisonCredencial(administradorCredencial);
			administradorCredencial.getPermiso().add(driverDelete);
			permissionRepository.save(driverDelete);
			Permission driverEdit = new Permission("driver_edit", "Permitir editar conductores");
			driverEdit.setPermisonCredencial(administradorCredencial);
			administradorCredencial.getPermiso().add(driverEdit);
			permissionRepository.save(driverEdit);
			Permission driverDisable = new Permission("driver_disable", "Permitir inhabilitar conductores");
			driverDisable.setPermisonCredencial(administradorCredencial);
			administradorCredencial.getPermiso().add(driverDisable);
			permissionRepository.save(driverDisable);
			Permission driverView = new Permission("driver_view", "Permitir ver conductores");
			driverView.setPermisonCredencial(administradorCredencial);
			administradorCredencial.getPermiso().add(driverView);
			permissionRepository.save(driverView);
			Permission chargerSettingsView = new Permission("charger_settings_view", "Permitir ver configuraciones de cargador");
			chargerSettingsView.setPermisonCredencial(administradorCredencial);
			administradorCredencial.getPermiso().add(chargerSettingsView);
			permissionRepository.save(chargerSettingsView);
			Permission powerControlCreate = new Permission("power_control_create", "Permitir crear control de potencia");
			powerControlCreate.setPermisonCredencial(administradorCredencial);
			administradorCredencial.getPermiso().add(powerControlCreate);
			permissionRepository.save(powerControlCreate);
			Permission powerControlEdit = new Permission("power_control_edit", "Permitir editar control de potencia");
			powerControlEdit.setPermisonCredencial(administradorCredencial);
			administradorCredencial.getPermiso().add(powerControlEdit);
			permissionRepository.save(powerControlEdit);
			Permission powerControlView = new Permission("power_control_view", "Permitir ver control de potencia");
			powerControlView.setPermisonCredencial(administradorCredencial);
			permissionRepository.save(powerControlView);
			Permission dashboardView = new Permission("dashboard_view", "Permitir ver el dashboard");
			dashboardView.setPermisonCredencial(administradorCredencial);
			permissionRepository.save(dashboardView);
			Permission scheduledDisablesCreate = new Permission("scheduled_disables_create", "Permitir crear inhabilitaciones programadas");
			scheduledDisablesCreate.setPermisonCredencial(administradorCredencial);
			administradorCredencial.getPermiso().add(scheduledDisablesCreate);
			permissionRepository.save(scheduledDisablesCreate);
			Permission scheduledDisablesEdit = new Permission("scheduled_disables_edit", "Permitir editar inhabilitaciones programadas");
			scheduledDisablesEdit.setPermisonCredencial(administradorCredencial);
			administradorCredencial.getPermiso().add(scheduledDisablesEdit);
			permissionRepository.save(scheduledDisablesEdit);
			Permission scheduledDisablesView = new Permission("scheduled_disables_view", "Permitir ver inhabilitaciones programadas");
			scheduledDisablesView.setPermisonCredencial(administradorCredencial);
			administradorCredencial.getPermiso().add(scheduledDisablesView);
			permissionRepository.save(scheduledDisablesView);
			Permission ocppEditSettings = new Permission("ocpp_edit_settings", "Permitir editar configuraciones OCPP");
			ocppEditSettings.setPermisonCredencial(administradorCredencial);
			administradorCredencial.getPermiso().add(ocppEditSettings);
			permissionRepository.save(ocppEditSettings);
			Permission ocppViewSettings = new Permission("ocpp_view_settings", "Permitir ver configuraciones OCPP");
			ocppViewSettings.setPermisonCredencial(administradorCredencial);
			administradorCredencial.getPermiso().add(ocppViewSettings);
			permissionRepository.save(ocppViewSettings);
			Permission peakShavingCreate = new Permission("peak_shaving_create", "Permitir crear peak shaving");
			peakShavingCreate.setPermisonCredencial(administradorCredencial);
			administradorCredencial.getPermiso().add(peakShavingCreate);
			permissionRepository.save(peakShavingCreate);
			Permission peakShavingEdit = new Permission("peak_shaving_edit", "Permitir editar peak shaving");
			peakShavingEdit.setPermisonCredencial(administradorCredencial);
			administradorCredencial.getPermiso().add(peakShavingEdit);
			permissionRepository.save(peakShavingEdit);
			Permission peakShavingView = new Permission("peak_shaving_view", "Permitir ver peak shaving");
			peakShavingView.setPermisonCredencial(administradorCredencial);
			administradorCredencial.getPermiso().add(peakShavingView);
			permissionRepository.save(peakShavingView);
			Permission chargeRecordsView = new Permission("charge_records_view", "Permitir ver registros de carga");
			chargeRecordsView.setPermisonCredencial(administradorCredencial);
			administradorCredencial.getPermiso().add(chargeRecordsView);
			permissionRepository.save(chargeRecordsView);
			Permission rolesDelete = new Permission("roles_delete", "Permitir borrar roles");
			rolesDelete.setPermisonCredencial(administradorCredencial);
			administradorCredencial.getPermiso().add(rolesDelete);
			permissionRepository.save(rolesDelete);
			Permission rolesCreate = new Permission("roles_create", "Permitir crear roles");
			rolesCreate.setPermisonCredencial(administradorCredencial);
			administradorCredencial.getPermiso().add(rolesCreate);
			permissionRepository.save(rolesCreate);
			Permission rolesEdit = new Permission("roles_edit", "Permitir editar roles");
			rolesEdit.setPermisonCredencial(administradorCredencial);
			administradorCredencial.getPermiso().add(rolesEdit);
			permissionRepository.save(rolesEdit);
			Permission rolesView = new Permission("roles_view", "Permitir ver roles");
			rolesView.setPermisonCredencial(administradorCredencial);
			administradorCredencial.getPermiso().add(rolesView);
			permissionRepository.save(rolesView);
			Permission terminalsView = new Permission("charging_station_view", "Permitir ver tecles");
			terminalsView.setPermisonCredencial(administradorCredencial);
			administradorCredencial.getPermiso().add(terminalsView);
			permissionRepository.save(terminalsView);
			Permission locationsCreate = new Permission("locations_create", "Permitir crear ubicaciones");
			locationsCreate.setPermisonCredencial(administradorCredencial);
			administradorCredencial.getPermiso().add(locationsCreate);
			permissionRepository.save(locationsCreate);
			Permission locationsView = new Permission("locations_view", "Permitir ver ubicaciones");
			locationsView.setPermisonCredencial(administradorCredencial);
			administradorCredencial.getPermiso().add(locationsView);
			permissionRepository.save(locationsView);
			Permission electricFirefighter = new Permission("electric_firefighter", "Permitir acceso de Electrobombero");
			electricFirefighter.setPermisonCredencial(administradorCredencial);
			administradorCredencial.getPermiso().add(electricFirefighter);
			permissionRepository.save(electricFirefighter);
			Permission chargersByUser = new Permission("chargers_by_user", "Permitir ver cargadores por usuario");
			chargersByUser.setPermisonCredencial(administradorCredencial);
			administradorCredencial.getPermiso().add(chargersByUser);
			permissionRepository.save(chargersByUser);
			Permission chargersByTerminal = new Permission("chargers_by_terminal", "Permitir ver cargadores por terminal");
			chargersByTerminal.setPermisonCredencial(administradorCredencial);
			administradorCredencial.getPermiso().add(chargersByTerminal);
			permissionRepository.save(chargersByTerminal);
			Permission clientView = new Permission("client_view", "Permitir vista clientes");
			clientView.setPermisonCredencial(administradorCredencial);
			administradorCredencial.getPermiso().add(clientView);
			permissionRepository.save(clientView);
			Permission employeeView = new Permission("employee_view", "Permitir vista encargado");
			employeeView.setPermisonCredencial(administradorCredencial);
			administradorCredencial.getPermiso().add(employeeView);
			permissionRepository.save(employeeView);

			// Guardar PermissionCredential junto con sus Permissions
			permissionCredentialRepository.save(administradorCredencial);

			Location Default = new Location("Default");
			locationRepository.save(Default);



			// Crear un usuario de compañía
			CompanyUser company = new CompanyUser(
					"correo@miempresa.com",
					passwordEncoder.encode("password"),
					88894,
					"Mi Empresa S.A.",
					"123456789",
					Role.COMPANY,
					true
			);
			companyUserRepository.save(company);

			// Crear la cuenta principal de la compañía
			String numeroDeCuenta = "admin-" + getStringRandomNumber();
			Account account = new Account(
					numeroDeCuenta,
					"Cuenta Principal de " + company.getNombreCompañia(),
					LocalDate.now(),
					company.getEmail(),
					company.getPassword(),
					company,
					null
			);
			account.setActivo(true);
			accountRepository.save(account);

			// Crear la cuenta de la compañía
			String numeroDeCuentaCompañia = "Compañia-" + getStringRandomNumber();
			AccountCompany accountCompany = new AccountCompany(
					numeroDeCuentaCompañia,
					company.getNombreCompañia(),
					company.getRut(),
					company.getEmail(),
					company.getPassword(),
					Role.COMPANY,
					TypeAccounts.Company,
					new Location("Default Location Address")
			);
			accountCompany.setCuentaPrincipal(account);
			accountCompany.setCompañia(company);
			accountCompany.setActivo(true);
			accountCompanyRepository.save(accountCompany);

			// Asignar credenciales a la cuenta de la compañía
			Set<Credential> credentials = new HashSet<>();
			credentials.add(administrador);
			credentials.add(electroBombero);
			credentials.add(reportero);
			accountCompany.setCredenciales(credentials);

			accountCompanyRepository.save(accountCompany);

			ChargerManufacturer chargerManufacturer = new ChargerManufacturer(
					"Fabricante de Prueba 1"
			);
			chargerManufacturerService.saveChargerManufacturer(chargerManufacturer);

			ChargerModel chargerModel = new ChargerModel(
					"Modelo de Prueba 1"
			);
			chargerModelService.saveChargerModel(chargerModel);


			System.out.println("Company Account created successfully with ID: " + accountCompany.getId());

			*/
		};
	}

	private String getStringRandomNumber() {
		int min = 10000000;
		int max = 99999999;
		int randomNumber = (int) ((Math.random() * (max - min)) + min);
		return String.valueOf(randomNumber);
	}

	@Bean
	public OcppClient ocppClient() {
		return new OcppClient(URI.create("ws://localhost:8080/ocpp"));
	}
}
