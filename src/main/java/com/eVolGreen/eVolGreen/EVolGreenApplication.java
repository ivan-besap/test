package com.eVolGreen.eVolGreen;

import com.eVolGreen.eVolGreen.Auth.Role;
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

@SpringBootApplication
public class EVolGreenApplication {

	@Autowired
	public PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(EVolGreenApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData(LocationRepository locationRepository, CarRepository carRepository, DeviceIdentifierRepository deviceIdentifierRepository,
									  TransactionRepository transactionRepository, PlanRepository planRepository, AccountRepository accountRepository, ClientRepository clientRepository,
									  CompanyRepository companyRepository, EmployeeRepository employeeRepository, ChargerRepository chargerRepository, ChargingStationRepository chargingStationRepository,
									  ConnectorRepository connectorRepository, ChargingStationStatusRepository chargingStationStatusRepository, ChargingUnitRepository chargingUnitRepository,
									  ReservationRepository  reservationRepository) {
		return args -> {
		/*
			Role clientRole = Role.CLIENT;
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
			// Supongamos que la carga comienza a las 9:00 AM y termina a las 11:00 AM del día actual
			LocalDateTime startDateTime = currentDate.atTime(9, 0);
			LocalDateTime endDateTime = currentDate.atTime(11, 0);

			Plan planBasico = new Plan(
					"Plan Básico",
					"Plan básico de carga eléctrica para vehículos",
					30,
					new BigDecimal("250.00"),
					new BigDecimal("350"),
					new BigDecimal("280"));
			planBasico.setAccount(account);
			planRepository.save(planBasico);









			Role companyRole = Role.COMPANY;
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

			Role role = Role.EMPLOYEE;
			Employee nuevoEmpleado = new Employee(
					"Juan",
					"Pérez",
					"González",
					"juan@example.com",
					passwordEncoder.encode("password"),
					LocalDate.now(),
					company1,
					role
			);

			company1.addAccount(accountEmployee);
			employeeRepository.save(nuevoEmpleado);

			ChargingStation chargingStation = new ChargingStation(
					"Estacion Clinica Vitacura",
					BigDecimal.valueOf(0),
					LocalDate.now());
			chargingStation.setLocation(clinicaVitacura);
			chargingStation.setAccount(accountCompany);
			chargingStation.setAccount(accountEmployee);
			chargingStation.setAccount(account);
			chargingStationRepository.save(chargingStation);


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


			Plan planBasicoCompany = new Plan(
					"Plan Básico",
					"Plan básico de carga eléctrica para vehículos",
					30,
					new BigDecimal("25000.00"),
					new BigDecimal("20000.00"),
					new BigDecimal("19920.00"));
			planBasicoCompany.setAccount(accountCompany);
			planBasicoCompany.setChargingStation(chargingStation);
			planRepository.save(planBasicoCompany);





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
*/
		};
	}

	@Bean
	public OcppClient ocppClient() {
		return new OcppClient(URI.create("ws://localhost:8081/ocpp"));
	}
}
