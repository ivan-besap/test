package com.eVolGreen.eVolGreen;

import com.eVolGreen.eVolGreen.Models.*;
import com.eVolGreen.eVolGreen.Repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootApplication
public class EVolGreenApplication {

	public static void main(String[] args) {
		SpringApplication.run(EVolGreenApplication.class, args);
	}
	@Bean
	public CommandLineRunner initData(LocationRepository locationRepository, CarRepository carRepository, DeviceIdentifierRepository deviceIdentifierRepository, TransactionRepository transactionRepository, PlanRepository planRepository, AccountRepository accountRepository, ClientRepository clientRepository, CompanyRepository companyRepository, EmployeeRepository employeeRepository, ChargerRepository chargerRepository, ChargingStationRepository chargingStationRepository ,ConnectorRepository connectorRepository, ChargingStationStatusRepository chargingStationStatusRepository) {
		return args -> {

			Location clinicaVitacura = new Location(
					"-33.3978",
					"-70.5685",
					"Av. Vitacura 5951",
					"Vitacura",
					"Santiago",
					"Chile");
			locationRepository.save(clinicaVitacura);

			Location casaCliente = new Location(
					"-33.3978",
					"-70.5685",
					"Calle Ejemplo 123",
					"Vitacura",
					"Santiago",
					"Chile");
			locationRepository.save(casaCliente);

			Car car = new Car(
					"ABC123",
					"Tesla Model S",
					"123456789",
					"Negro",
					"Tesla",
					"2024",
					new BigDecimal("100"));
			carRepository.save(car);

			DeviceIdentifier cardIdentifier = new DeviceIdentifier(
					"Tarjeta1-VIN123456789",
					987654,
					"Tarjeta", car);
			deviceIdentifierRepository.save(cardIdentifier);
			car.addDeviceIdentifier(cardIdentifier);
			carRepository.save(car);

			LocalDate currentDate = LocalDate.now();

			// Supongamos que la carga comienza a las 9:00 AM y termina a las 11:00 AM del día actual
			LocalDateTime startDateTime = currentDate.atTime(9, 0);
			LocalDateTime endDateTime = currentDate.atTime(11, 0);

			Transaction chargeTransaction = new Transaction(
					TransactionType.CREDIT,
					"Carga de energía para el vehículo",
					LocalDateTime.now(),
					startDateTime,
					endDateTime,
					new BigDecimal("50.00"),
					20000
			);
			transactionRepository.save(chargeTransaction);

			Plan planBasico = new Plan(
					"Plan Básico",
					"Plan básico de carga eléctrica para vehículos",
					30,
					new BigDecimal("250.00"));
			planRepository.save(planBasico);

			Plan planBasicoCompany = new Plan(
					"Plan Básico",
					"Plan básico de carga eléctrica para vehículos",
					30,
					new BigDecimal("25000.00"));
			planRepository.save(planBasicoCompany);

			Account account = new Account("Client-12345",
					LocalDate.now(),
					TypeAccounts.Client);
			account.addTransaction(chargeTransaction);
			account.addPlan(planBasico);
			account.addLocation(casaCliente);
			account.addCar(car);
			accountRepository.save(account);

			Client client = new Client(
					"John Doe",
					"john@example.com",
					"Doe",
					123456789,
					12345678,
					"A",
					"password");
			client.setAccount(account);
			clientRepository.save(client);

			Account accountCompany = new Account("Company-12345",
					LocalDate.now(),
					TypeAccounts.Company);
			accountCompany.addLocation(clinicaVitacura);
			accountRepository.save(accountCompany);

			LocalDate fechaCreacion = LocalDate.now();
			Company company1 = new Company(
					"Mi Empresa S.A.",
					"correo@miempresa.com",
					123456789,
					1234567890,
					"AB",
					true,
					"mi_contraseña",
					fechaCreacion
			);
			company1.addAccount(accountCompany);
			companyRepository.save(company1);


			Account accountEmployee = new Account("Employee-12345",
					LocalDate.now(),
					TypeAccounts.Employee);
			accountEmployee.addLocation(clinicaVitacura);
			accountRepository.save(accountEmployee);
			Employee nuevoEmpleado = new Employee(
					"Juan",
					"Pérez",
					"González",
					"juan@example.com",
					"juanpg",
					"contraseña",
					LocalDate.now(),
					company1);

			company1.addAccount(accountEmployee);
			employeeRepository.save(nuevoEmpleado);

			ChargingStation chargingStation = new ChargingStation(
					"Estacion Clinica Vitacura",
					BigDecimal.valueOf(0),
					LocalDate.now());
			chargingStation.setLocation(clinicaVitacura);
			chargingStation.setAccount(accountCompany);
			chargingStation.setAccount(accountEmployee);
			chargingStation.addPlan(planBasicoCompany);
			chargingStation.addTransaction(chargeTransaction);
			chargingStationRepository.save(chargingStation);


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
			charger.addChargingUnit(chargingUnit);
			chargerRepository.save(charger);

			ChargingStationStatus status = new ChargingStationStatus("Enabled");
			chargingStation.addChargingStationStatus(status);
			chargingStationRepository.save(chargingStation);
			chargingStationStatusRepository.save(status);



		};
	}

}
