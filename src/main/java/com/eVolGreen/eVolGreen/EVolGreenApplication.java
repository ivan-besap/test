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
			Permission dashboardView = new Permission("dashboard_view", "Permitir ver dashboard");
			permissionRepository.save(dashboardView);

			Permission dashboardViewPorEstacion = new Permission("dashboard_view_por_estacion", "Permitir ver dashboard por estacion");
			permissionRepository.save(dashboardViewPorEstacion);

			Permission ocppCommandsView = new Permission("ocpp_commands_view", "Permitir ver comandos OCPP");
			permissionRepository.save(ocppCommandsView);

			Permission ocppCommandsReiniciarCarga = new Permission("ocpp_commands_reiniciar_carga", "Permitir Reiniciar Carga OCPP");
			permissionRepository.save(ocppCommandsReiniciarCarga);

			Permission ocppCommandsIniciarCarga = new Permission("ocpp_commands_iniciar_carga", "Permitir Iniciar Carga OCPP");
			permissionRepository.save(ocppCommandsIniciarCarga);

			Permission ocppCommandsDetenerCarga = new Permission("ocpp_commands_detener_carga", "Permitir Detener Carga OCPP");
			permissionRepository.save(ocppCommandsDetenerCarga);

			Permission ocppCommandsHabilitarCarga = new Permission("ocpp_commands_habilitar_carga", "Permitir Habilitar Carga OCPP");
			permissionRepository.save(ocppCommandsHabilitarCarga);

			Permission ocppCommandsDeshabilitarCarga = new Permission("ocpp_commands_deshabilitar_carga", "Permitir Deshabilitar Carga OCPP");
			permissionRepository.save(ocppCommandsDeshabilitarCarga);

			Permission ocppCommandsDesbloquearCarga = new Permission("ocpp_commands_desbloquear_carga", "Permitir Desbloquear Carga OCPP");
			permissionRepository.save(ocppCommandsDesbloquearCarga);

			Permission tarifasView = new Permission("tarifas_view", "Permitir ver Tarifas");
			permissionRepository.save(tarifasView);

			Permission tarifasCrear = new Permission("tarifas_crear", "Permitir crear Tarifas");
			permissionRepository.save(tarifasCrear);

			Permission tarifasEditar = new Permission("tarifas_editar", "Permitir editar Tarifas");
			permissionRepository.save(tarifasEditar);

			Permission tarifasEliminar = new Permission("tarifas_eliminar", "Permitir eliminar Tarifas");
			permissionRepository.save(tarifasEliminar);

			Permission tarifasAsignar = new Permission("tarifas_asignar", "Permitir Asignar Tarifas");
			permissionRepository.save(tarifasAsignar);

			Permission flotasView = new Permission("flotas_view", "Permitir ver Flotas");
			permissionRepository.save(flotasView);

			Permission flotasCrear = new Permission("flotas_crear", "Permitir crear Flotas");
			permissionRepository.save(flotasCrear);

			Permission flotasEditar = new Permission("flotas_editar", "Permitir editar Flotas");
			permissionRepository.save(flotasEditar);

			Permission flotasEliminar = new Permission("flotas_eliminar", "Permitir eliminar Flotas");
			permissionRepository.save(flotasEliminar);

			Permission reporteCargaView = new Permission("reportes_cargas_view", "Permitir ver Reportes de cargas");
			permissionRepository.save(reporteCargaView);

			Permission reporteDetalleCargaView = new Permission("reportes_detalles_cargas_view", "Permitir ver Reportes detalles de carga");
			permissionRepository.save(reporteDetalleCargaView);

			Permission reporteCargaCargadorView = new Permission("reportes_cargas_cargador_view", "Permitir ver Reportes cargas por cargador");
			permissionRepository.save(reporteCargaCargadorView);

			Permission reporteCargasTerminalView = new Permission("reportes_cargas_terminal_view", "Permitir ver Reportes cargas por terminal");
			permissionRepository.save(reporteCargasTerminalView);

			Permission reporteCargasFlotaView = new Permission("reportes_cargas_flota_view", "Permitir ver Reportes cargas por flota");
			permissionRepository.save(reporteCargasFlotaView);

			Permission reporteErroresConectorView = new Permission("reportes_errores_conector_view", "Permitir ver Reportes errores por conector");
			permissionRepository.save(reporteErroresConectorView);

			Permission reporteErroresView = new Permission("reportes_errores_view", "Permitir ver Reportes errores");
			permissionRepository.save(reporteErroresView);

			Permission usuariosEmpresaView = new Permission("usuarios_empresa_view", "Permitir ver Usuarios Empresa");
			permissionRepository.save(usuariosEmpresaView);

			Permission usuariosEmpresaCrear = new Permission("usuarios_empresa_crear", "Permitir crear Usuarios Empresa");
			permissionRepository.save(usuariosEmpresaCrear);

			Permission usuariosEmpresaEditar = new Permission("usuarios_empresa_editar", "Permitir editar Usuarios Empresa");
			permissionRepository.save(usuariosEmpresaEditar);

			Permission usuariosEmpresaEliminar = new Permission("usuarios_empresa_eliminar", "Permitir eliminar Usuarios Empresa");
			permissionRepository.save(usuariosEmpresaEliminar);

			Permission rolesView = new Permission("roles_view", "Permitir ver Roles");
			permissionRepository.save(rolesView);

			Permission rolesEditar = new Permission("roles_editar", "Permitir editar Roles");
			permissionRepository.save(rolesEditar);

			Permission rolesEliminar = new Permission("roles_eliminar", "Permitir eliminar Roles");
			permissionRepository.save(rolesEliminar);

			Permission tarjetarfidView = new Permission("tartetarfid_view", "Permitir ver TarjetasRFID");
			permissionRepository.save(tarjetarfidView);

			Permission tarjetarfidCrear = new Permission("tartetarfid_crear", "Permitir crear TarjetasRFID");
			permissionRepository.save(tarjetarfidCrear);

			Permission tarjetarfidEditar = new Permission("tartetarfid_editar", "Permitir editar TarjetasRFID");
			permissionRepository.save(tarjetarfidEditar);

			Permission tarjetarfidEliminar = new Permission("tartetarfid_eliminar", "Permitir eliminar TarjetasRFID");
			permissionRepository.save(tarjetarfidEliminar);

			Permission estacionView = new Permission("estacion_view", "Permitir ver Estaciones");
			permissionRepository.save(estacionView);

			Permission estacionCrear = new Permission("estacion_crear", "Permitir crear Estaciones");
			permissionRepository.save(estacionCrear);

			Permission estacionEditar = new Permission("estacion_editar", "Permitir editar Estaciones");
			permissionRepository.save(estacionEditar);

			Permission estacionEliminar = new Permission("estacion_eliminar", "Permitir eliminar Estaciones");
			permissionRepository.save(estacionEliminar);

			Permission cargadorView = new Permission("cargador_view", "Permitir ver Cargadores");
			permissionRepository.save(cargadorView);

			Permission cargadorCrear = new Permission("cargador_crear", "Permitir crear Cargadores");
			permissionRepository.save(cargadorCrear);

			Permission cargadorEditar = new Permission("cargador_editar", "Permitir editar Cargadores");
			permissionRepository.save(cargadorEditar);

			Permission cargadorEliminar = new Permission("cargador_eliminar", "Permitir eliminar Cargadores");
			permissionRepository.save(cargadorEliminar);

			Permission cargadorMantenimientoCrear = new Permission("cargador_mantenimiento_crear", "Permitir Crear Mantenimiento Cargador");
			permissionRepository.save(cargadorMantenimientoCrear);

			Permission cargadorMantenimientoAsignar = new Permission("cargador_mantenimiento_asignar", "Permitir Asignar Mantenimiento Cargador");
			permissionRepository.save(cargadorMantenimientoAsignar);

			Permission conectorView = new Permission("conector_view", "Permitir ver Conectores");
			permissionRepository.save(conectorView);

			Permission conectorCrear = new Permission("conector_crear", "Permitir crear Conector");
			permissionRepository.save(conectorCrear);

			Permission conectorEditar = new Permission("conector_editar", "Permitir editar Conector");
			permissionRepository.save(conectorEditar);

			Permission conectorEliminar = new Permission("conector_eliminar", "Permitir eliminar Conector");
			permissionRepository.save(conectorEliminar);

			Permission configuracionCorreoAlertaView = new Permission("configuracion_correo_alerta_view", "Permitir ver Configuración de Correos Alertas");
			permissionRepository.save(configuracionCorreoAlertaView);

			Permission reporteAlarmaDiariaView = new Permission("reportes_alarma_diaria_view", "Permitir ver Alarmas diarias");
			permissionRepository.save(reporteAlarmaDiariaView);

			Permission rolesCreate = new Permission("roles_create", "Permitir crear Roles");
			permissionRepository.save(rolesCreate);

			Permission tarjetarfidAsignar = new Permission("tartetarfid_asignar", "Permitir Asignar TarjetasRFID");
			permissionRepository.save(tarjetarfidAsignar);



			List<Permission> permisos = permissionRepository.findAll();

			Empresa defaultEmpresa = new Empresa();
			defaultEmpresa.setNombre("Empresa Predeterminada");
			empresaRepository.save(defaultEmpresa);

			Role adminRole = new Role();
			adminRole.setNombre("ADMIN");
			adminRole.setPermisos(permisos);
			adminRole.setEmpresa(defaultEmpresa);

			roleRepository.save(adminRole);


			// Crear la cuenta principal de la compañía
			String numeroDeCuenta = "admin-" + getStringRandomNumber();
			String encriptedPassword = passwordEncoder.encode("password");
			Account account = new Account(
					"Mi Empresa",
					"Mi Empresa",
					"Mi Empresa",
					numeroDeCuenta,
					"Cuenta Principal de Mi Empresa",
					LocalDate.now(),
					"correo@miempresa.com",
					encriptedPassword,
					TypeAccounts.COMPANY,
					adminRole,
					"8889874322",
					"13.445.221-1",
					defaultEmpresa,
					true
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
