package com.eVolGreen.eVolGreen;


import com.eVolGreen.eVolGreen.Configurations.Ocpp.OcppClient;
import com.eVolGreen.eVolGreen.Models.*;
import com.eVolGreen.eVolGreen.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.net.URI;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@SpringBootApplication
public class EVolGreenApplication {

	@Autowired
	public PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(EVolGreenApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData(LocationRepository locationRepository, CarRepository carRepository, DeviceIdentifierRepository deviceIdentifierRepository,
									  TransactionRepository transactionRepository, FeeRepository feeRepository, AccountRepository accountRepository, ClientRepository clientRepository,
									  CompanyRepository companyRepository, EmployeeRepository employeeRepository, ChargerRepository chargerRepository, ChargingStationRepository chargingStationRepository,
									  ConnectorRepository connectorRepository, ChargingStationStatusRepository chargingStationStatusRepository, ChargingUnitRepository chargingUnitRepository,
									  ReservationRepository reservationRepository, RoleRepository roleRepository, PermissionRepository permissionRepository) {
		return args -> {
			// Crear y guardar roles
			Role clientRole = new Role("Client");
			Role companyRole = new Role("Company");
			Role employeeRole = new Role("Employee");

			// Crear y guardar permisos
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
			Permission driverDelete = new Permission("driver_delete", "Permitir borrar conductores");
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

			// Asociar permisos a roles
			Set<Permission> permissions = new HashSet<>();
			permissions.add(clientView);
			permissions.add(driverView);

			clientRole.setPermissions(permissions);
			companyRole.setPermissions(permissions);
			employeeRole.setPermissions(permissions);

			// Guardar roles
			roleRepository.save(clientRole);
			roleRepository.save(companyRole);
			roleRepository.save(employeeRole);

			// Crear y guardar cliente
			Client client = new Client(
					"John",
					"Doe",
					123456789-9,
					"correo@cliente.com",
					12345678,
					passwordEncoder.encode("password"),
					clientRole);
			clientRepository.save(client);

			Account account = new Account("Client-12345",
					LocalDate.now(),
					TypeAccounts.Client);
			account.setClient(client);
			accountRepository.save(account);

			Location casaCliente = new Location(
					"-33.3978",
					"-70.5685",
					"Calle Ejemplo 123",
					"Vitacura",
					"Santiago",
					"Chile");
			casaCliente.setAccount(account);
			locationRepository.save(casaCliente);

			Car car = new Car(
					"ABC123",
					"Tesla Model S",
					"123456789",
					"Negro",
					"Tesla",
					"2024",
					new BigDecimal("100"));
			car.setAccount(account);
			carRepository.save(car);

			DeviceIdentifier cardIdentifier = new DeviceIdentifier(
					"Tarjeta1-VIN123456789",
					987654,
					"Tarjeta",
					car);
			deviceIdentifierRepository.save(cardIdentifier);

			LocalDate currentDate = LocalDate.now();
			LocalDateTime startDateTime = currentDate.atTime(9, 0);
			LocalDateTime endDateTime = currentDate.atTime(11, 0);

			// Crear y guardar compañía
			LocalDate fechaCreacion = LocalDate.now();
			Company company1 = new Company(
					"Mi Empresa S.A.",
					"correo@miempresa.com",
					123456789,
					1234567890,
					passwordEncoder.encode("password"),
					fechaCreacion,
					companyRole
			);
			companyRepository.save(company1);

			Account accountCompany = new Account("Company-12345",
					LocalDate.now(),
					TypeAccounts.Company);
			accountCompany.setCompany(company1);
			accountRepository.save(accountCompany);

			Location clinicaVitacura = new Location(
					"-33.3978",
					"-70.5685",
					"Av. Vitacura 5951",
					"Vitacura",
					"Santiago",
					"Chile");
			clinicaVitacura.setAccount(accountCompany);
			locationRepository.save(clinicaVitacura);

			Account accountEmployee = new Account("Employee-12345",
					LocalDate.now(),
					TypeAccounts.Employee);
			accountEmployee.addLocation(clinicaVitacura);
			accountEmployee.setCompany(company1);
			accountRepository.save(accountEmployee);

			// Crear y guardar empleado
			Employee nuevoEmpleado = new Employee(
					"Juan",
					"Pérez",
					"González",
					"juan@example.com",
					passwordEncoder.encode("password"),
					LocalDate.now(),
					company1
			);
			nuevoEmpleado.setRoles(Set.of(employeeRole));
			company1.addAccount(accountEmployee);
			employeeRepository.save(nuevoEmpleado);

			// Crear y guardar estación de carga
			ChargingStation chargingStation = new ChargingStation(
					"Estacion Clinica Vitacura",
					BigDecimal.valueOf(0),
					LocalDate.now());
			chargingStation.setLocation(clinicaVitacura);
			chargingStation.setAccount(accountCompany);
			chargingStation.setAccount(accountEmployee);
			chargingStation.setAccount(account);
			chargingStationRepository.save(chargingStation);

			// Crear y guardar transacción
			Transaction chargeTransaction = new Transaction(
					TransactionType.CREDIT,
					"Carga de energía para el vehículo",
					LocalDateTime.now(),
					startDateTime,
					endDateTime,
					new BigDecimal("80.00"),
					20000
			);
			chargeTransaction.setAccount(account);
			chargeTransaction.setChargingStation(chargingStation);
			transactionRepository.save(chargeTransaction);

			// Crear y guardar tarifas
			Fee fee1 = new Fee(
					"Tarifa 1",
					LocalDate.parse("2024-08-04"),
					LocalDate.parse("2024-08-10"),
					LocalTime.parse("13:00"),
					LocalTime.parse("18:00"),
					Set.of("Lunes", "Martes"),
					new BigDecimal("350")
			);
			fee1.setCompany(company1);
			feeRepository.save(fee1);

			Fee fee2 = new Fee(
					"Tarifa 2",
					LocalDate.parse("2024-09-01"),
					LocalDate.parse("2024-09-30"),
					LocalTime.parse("08:00"),
					LocalTime.parse("20:00"),
					Set.of("Martes", "Miércoles"),
					new BigDecimal("500")
			);
			fee2.setCompany(company1);
			feeRepository.save(fee2);

			// Crear y guardar cargador
			Charger charger = new Charger(
					"Modelo X",
					Time.valueOf("01:30:00"),
					BigDecimal.valueOf(50),
					true,
					LocalDate.now(),
					TypeOfLoad.AC,
					chargingStation
			);
			chargingStation.addCharger(charger);
			chargingStationRepository.save(chargingStation);
			chargerRepository.save(charger);

			ChargingUnit chargingUnit = new ChargingUnit("kWh", charger);
			chargingUnit.setCharger(charger);
			chargingUnitRepository.save(chargingUnit);

			ChargingStationStatus status = new ChargingStationStatus("Enabled");
			chargingStation.addChargingStationStatus(status);
			chargingStationRepository.save(chargingStation);
			chargingStationStatusRepository.save(status);

			Connector connector = new Connector(
					"Conector Tipo A",
					BigDecimal.valueOf(50),
					ConnectorStatus.CONNECTED,
					BigDecimal.valueOf(80),
					charger
			);
			connector.setCharger(charger);
			connectorRepository.save(connector);
		};
	}

	@Bean
	public OcppClient ocppClient() {
		return new OcppClient(URI.create("ws://localhost:8080/ocpp"));
	}
}
