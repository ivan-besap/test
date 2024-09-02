package com.eVolGreen.eVolGreen;

import com.eVolGreen.eVolGreen.Models.Account.Account;
import com.eVolGreen.eVolGreen.Models.Account.Empresa;
import com.eVolGreen.eVolGreen.Models.Account.Location;
import com.eVolGreen.eVolGreen.Models.Account.Permission.Permission;
import com.eVolGreen.eVolGreen.Models.Account.TypeOfAccount.TypeAccounts;
import com.eVolGreen.eVolGreen.Models.ChargingStation.Charger.ChargerManufacturer;
import com.eVolGreen.eVolGreen.Models.ChargingStation.Charger.ChargerModel;
import com.eVolGreen.eVolGreen.Models.Ocpp.OcppClient;
import com.eVolGreen.eVolGreen.Models.User.Role;
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
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
									  ChargerRepository chargerRepository,
									  RoleRepository roleRepository,
									  ChargingStationRepository chargingStationRepository,
									  EmpresaRepository empresaRepository,
									  ConnectorRepository connectorRepository,
									  ChargingUnitRepository chargingUnitRepository,
									  ReservationRepository reservationRepository,
									  PermissionRepository permissionRepository,
									  LocationRepository locationRepository, ChargerManufacturerService chargerManufacturerService, ChargerModelService chargerModelService) {
		return args -> {

			/*

//			// Crear y guardar permisos
			Permission chargerCreate = new Permission("charger_create", "Permitir crear un nuevo cargador");
			permissionRepository.save(chargerCreate);

			Permission chargerDelete = new Permission("charger_delete", "Permitir borrar un cargador");
			permissionRepository.save(chargerDelete);

			Permission chargerEdit = new Permission("charger_edit", "Permitir editar un cargador");
			permissionRepository.save(chargerEdit);

			Permission chargingStationView = new Permission("charging_station_view", "Permitir ver las cargas por terminal");
			permissionRepository.save(chargingStationView);

			Permission ocppChargerCommands = new Permission("ocpp_charger_commands", "Permitir comandos OCPP - Cargador");
			permissionRepository.save(ocppChargerCommands);

			Permission ocppConnectorCommands = new Permission("ocpp_connector_commands", "Permitir comandos OCPP - Conector");
			permissionRepository.save(ocppConnectorCommands);

			Permission ocppChargingStationCommands = new Permission("ocpp_charging_station_commands", "Permitir comandos OCPP - Carga por terminal");
			permissionRepository.save(ocppChargingStationCommands);

			Permission driverCreate = new Permission("driver_create", "Permitir crear conductores");
			permissionRepository.save(driverCreate);

			Permission driverDelete = new Permission("drive_delete", "Permitir borrar conductores");
			permissionRepository.save(driverDelete);

			Permission driverEdit = new Permission("driver_edit", "Permitir editar conductores");
			permissionRepository.save(driverEdit);

			Permission driverDisable = new Permission("driver_disable", "Permitir inhabilitar conductores");
			permissionRepository.save(driverDisable);

			Permission driverView = new Permission("driver_view", "Permitir ver conductores");
			permissionRepository.save(driverView);

			Permission chargerSettingsView = new Permission("charger_settings_view", "Permitir ver configuraciones de cargador");
			permissionRepository.save(chargerSettingsView);

			Permission powerControlCreate = new Permission("power_control_create", "Permitir crear control de potencia");
			permissionRepository.save(powerControlCreate);

			Permission powerControlEdit = new Permission("power_control_edit", "Permitir editar control de potencia");
			permissionRepository.save(powerControlEdit);

			Permission powerControlView = new Permission("power_control_view", "Permitir ver control de potencia");
			permissionRepository.save(powerControlView);

			Permission dashboardView = new Permission("dashboard_view", "Permitir ver el dashboard");
			permissionRepository.save(dashboardView);

			Permission scheduledDisablesCreate = new Permission("scheduled_disables_create", "Permitir crear inhabilitaciones programadas");
			permissionRepository.save(scheduledDisablesCreate);

			Permission scheduledDisablesEdit = new Permission("scheduled_disables_edit", "Permitir editar inhabilitaciones programadas");
			permissionRepository.save(scheduledDisablesEdit);

			Permission scheduledDisablesView = new Permission("scheduled_disables_view", "Permitir ver inhabilitaciones programadas");
			permissionRepository.save(scheduledDisablesView);

			Permission ocppEditSettings = new Permission("ocpp_edit_settings", "Permitir editar configuraciones OCPP");
			permissionRepository.save(ocppEditSettings);

			Permission ocppViewSettings = new Permission("ocpp_view_settings", "Permitir ver configuraciones OCPP");
			permissionRepository.save(ocppViewSettings);

			Permission peakShavingCreate = new Permission("peak_shaving_create", "Permitir crear peak shaving");
			permissionRepository.save(peakShavingCreate);

			Permission peakShavingEdit = new Permission("peak_shaving_edit", "Permitir editar peak shaving");
			permissionRepository.save(peakShavingEdit);

			Permission peakShavingView = new Permission("peak_shaving_view", "Permitir ver peak shaving");
			permissionRepository.save(peakShavingView);

			Permission chargeRecordsView = new Permission("charge_records_view", "Permitir ver registros de carga");
			permissionRepository.save(chargeRecordsView);

			Permission rolesDelete = new Permission("roles_delete", "Permitir borrar roles");
			permissionRepository.save(rolesDelete);

			Permission rolesCreate = new Permission("roles_create", "Permitir crear roles");
			permissionRepository.save(rolesCreate);

			Permission rolesEdit = new Permission("roles_edit", "Permitir editar roles");
			permissionRepository.save(rolesEdit);

			Permission rolesView = new Permission("roles_view", "Permitir ver roles");
			permissionRepository.save(rolesView);

			Permission terminalsView = new Permission("charging_station_view", "Permitir ver tecles");
			permissionRepository.save(terminalsView);

			Permission locationsCreate = new Permission("locations_create", "Permitir crear ubicaciones");
			permissionRepository.save(locationsCreate);

			Permission locationsView = new Permission("locations_view", "Permitir ver ubicaciones");
			permissionRepository.save(locationsView);

			Permission electricFirefighter = new Permission("electric_firefighter", "Permitir acceso de Electrobombero");
			permissionRepository.save(electricFirefighter);

			Permission chargersByUser = new Permission("chargers_by_user", "Permitir ver cargadores por usuario");
			permissionRepository.save(chargersByUser);

			Permission chargersByTerminal = new Permission("chargers_by_terminal", "Permitir ver cargadores por terminal");
			permissionRepository.save(chargersByTerminal);

			Permission clientView = new Permission("client_view", "Permitir vista clientes");
			permissionRepository.save(clientView);

			Permission employeeView = new Permission("employee_view", "Permitir vista encargado");
			permissionRepository.save(employeeView);



			Set<Long> permisos = permissionRepository.findAll().stream()
					.map(Permission::getId)
					.collect(Collectors.toSet());

			Role adminRole = new Role();
			adminRole.setNombre("ADMIN");
			adminRole.setPermisos(permisos);

			roleRepository.save(adminRole);

			Empresa defaultEmpresa = new Empresa();
			defaultEmpresa.setNombre("Empresa Predeterminada");
			empresaRepository.save(defaultEmpresa);

			// Crear la cuenta principal de la compañía
			String numeroDeCuenta = "admin-" + getStringRandomNumber();
			String encriptedPassword = passwordEncoder.encode("password");
			Account account = new Account(
					numeroDeCuenta,
					"Cuenta Principal de Mi Empresa",
					LocalDate.now(),
					"correo@miempresa.com",
					encriptedPassword,
					TypeAccounts.COMPANY,
					adminRole,
					"8889874322",
					"13.445.221-1",
					defaultEmpresa
			);

			account.setActivo(true);
			accountRepository.save(account);


			ChargerManufacturer chargerManufacturer = new ChargerManufacturer(
					"Fabricante de Prueba 1"
			);
			chargerManufacturerService.saveChargerManufacturer(chargerManufacturer);

			ChargerModel chargerModel = new ChargerModel(
					"Modelo de Prueba 1"
			);
			chargerModelService.saveChargerModel(chargerModel);

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
