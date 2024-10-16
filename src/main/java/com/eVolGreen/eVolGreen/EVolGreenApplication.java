package com.eVolGreen.eVolGreen;

import com.eVolGreen.eVolGreen.Repositories.*;
import com.eVolGreen.eVolGreen.Services.ChargingStationService.ChargerManufacturerService;
import com.eVolGreen.eVolGreen.Services.ChargingStationService.ChargerModelService;
import com.eVolGreen.eVolGreen.Services.ChargingStationService.ConnectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.net.URI;

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
									  ConnectorService connectorService,
									  ChargingUnitRepository chargingUnitRepository,
									  ReservationRepository reservationRepository,
									  PermissionRepository permissionRepository,
									  LocationRepository locationRepository, ChargerManufacturerService chargerManufacturerService, ChargerModelService chargerModelService) {
		return args -> {

//
//			if (permissionRepository.count() == 0) {
//				System.out.println("Creando permisos ...");
//				//			// Crear y guardar permisos
//				Permission dashboardView = new Permission("dashboard_view", "Permitir ver dashboard");
//				permissionRepository.save(dashboardView);
//
//				Permission dashboardViewPorEstacion = new Permission("dashboard_view_por_estacion", "Permitir ver dashboard por estacion");
//				permissionRepository.save(dashboardViewPorEstacion);
//
//				Permission ocppCommandsView = new Permission("ocpp_commands_view", "Permitir ver comandos OCPP");
//				permissionRepository.save(ocppCommandsView);
//
//				Permission ocppCommandsReiniciarCarga = new Permission("ocpp_commands_reiniciar_carga", "Permitir Reiniciar Carga OCPP");
//				permissionRepository.save(ocppCommandsReiniciarCarga);
//
//				Permission ocppCommandsIniciarCarga = new Permission("ocpp_commands_iniciar_carga", "Permitir Iniciar Carga OCPP");
//				permissionRepository.save(ocppCommandsIniciarCarga);
//
//				Permission ocppCommandsDetenerCarga = new Permission("ocpp_commands_detener_carga", "Permitir Detener Carga OCPP");
//				permissionRepository.save(ocppCommandsDetenerCarga);
//
//				Permission ocppCommandsHabilitarCarga = new Permission("ocpp_commands_habilitar_carga", "Permitir Habilitar Carga OCPP");
//				permissionRepository.save(ocppCommandsHabilitarCarga);
//
//				Permission ocppCommandsDeshabilitarCarga = new Permission("ocpp_commands_deshabilitar_carga", "Permitir Deshabilitar Carga OCPP");
//				permissionRepository.save(ocppCommandsDeshabilitarCarga);
//
//				Permission ocppCommandsDesbloquearCarga = new Permission("ocpp_commands_desbloquear_carga", "Permitir Desbloquear Carga OCPP");
//				permissionRepository.save(ocppCommandsDesbloquearCarga);
//
//				Permission tarifasView = new Permission("tarifas_view", "Permitir ver Tarifas");
//				permissionRepository.save(tarifasView);
//
//				Permission tarifasCrear = new Permission("tarifas_crear", "Permitir crear Tarifas");
//				permissionRepository.save(tarifasCrear);
//
//				Permission tarifasEditar = new Permission("tarifas_editar", "Permitir editar Tarifas");
//				permissionRepository.save(tarifasEditar);
//
//				Permission tarifasEliminar = new Permission("tarifas_eliminar", "Permitir eliminar Tarifas");
//				permissionRepository.save(tarifasEliminar);
//
//				Permission tarifasAsignar = new Permission("tarifas_asignar", "Permitir Asignar Tarifas");
//				permissionRepository.save(tarifasAsignar);
//
//				Permission vehiculoView = new Permission("vehiculos_view", "Permitir ver Vehiculos");
//				permissionRepository.save(vehiculoView);
//
//				Permission vehiculoCrear = new Permission("vehiculos_crear", "Permitir crear Vehiculos");
//				permissionRepository.save(vehiculoCrear);
//
//				Permission vehiculoEditar = new Permission("vehiculos_editar", "Permitir editar Vehiculos");
//				permissionRepository.save(vehiculoEditar);
//
//				Permission vehiculoEliminar = new Permission("vehiculos_eliminar", "Permitir eliminar Vehiculos");
//				permissionRepository.save(vehiculoEliminar);
//
//				Permission reporteCargaView = new Permission("reportes_cargas_view", "Permitir ver Reportes de cargas");
//				permissionRepository.save(reporteCargaView);
//
//				Permission reporteDetalleCargaView = new Permission("reportes_detalles_cargas_view", "Permitir ver Reportes detalles de carga");
//				permissionRepository.save(reporteDetalleCargaView);
//
//				Permission reporteCargaCargadorView = new Permission("reportes_cargas_cargador_view", "Permitir ver Reportes cargas por cargador");
//				permissionRepository.save(reporteCargaCargadorView);
//
//				Permission reporteCargasTerminalView = new Permission("reportes_cargas_terminal_view", "Permitir ver Reportes cargas por terminal");
//				permissionRepository.save(reporteCargasTerminalView);
//
//				Permission reporteCargasFlotaView = new Permission("reportes_cargas_flota_view", "Permitir ver Reportes cargas por flota");
//				permissionRepository.save(reporteCargasFlotaView);
//
//				Permission reporteErroresConectorView = new Permission("reportes_errores_conector_view", "Permitir ver Reportes errores por conector");
//				permissionRepository.save(reporteErroresConectorView);
//
//				Permission reporteErroresView = new Permission("reportes_errores_view", "Permitir ver Reportes errores");
//				permissionRepository.save(reporteErroresView);
//
//				Permission usuariosEmpresaView = new Permission("usuarios_empresa_view", "Permitir ver Usuarios Empresa");
//				permissionRepository.save(usuariosEmpresaView);
//
//				Permission usuariosEmpresaCrear = new Permission("usuarios_empresa_crear", "Permitir crear Usuarios Empresa");
//				permissionRepository.save(usuariosEmpresaCrear);
//
//				Permission usuariosEmpresaEditar = new Permission("usuarios_empresa_editar", "Permitir editar Usuarios Empresa");
//				permissionRepository.save(usuariosEmpresaEditar);
//
//				Permission usuariosEmpresaEliminar = new Permission("usuarios_empresa_eliminar", "Permitir eliminar Usuarios Empresa");
//				permissionRepository.save(usuariosEmpresaEliminar);
//
//				Permission rolesView = new Permission("roles_view", "Permitir ver Roles");
//				permissionRepository.save(rolesView);
//
//				Permission rolesEditar = new Permission("roles_editar", "Permitir editar Roles");
//				permissionRepository.save(rolesEditar);
//
//				Permission rolesEliminar = new Permission("roles_eliminar", "Permitir eliminar Roles");
//				permissionRepository.save(rolesEliminar);
//
//				Permission tarjetarfidView = new Permission("tartetarfid_view", "Permitir ver TarjetasRFID");
//				permissionRepository.save(tarjetarfidView);
//
//				Permission tarjetarfidCrear = new Permission("tartetarfid_crear", "Permitir crear TarjetasRFID");
//				permissionRepository.save(tarjetarfidCrear);
//
//				Permission tarjetarfidEditar = new Permission("tartetarfid_editar", "Permitir editar TarjetasRFID");
//				permissionRepository.save(tarjetarfidEditar);
//
//				Permission tarjetarfidEliminar = new Permission("tartetarfid_eliminar", "Permitir eliminar TarjetasRFID");
//				permissionRepository.save(tarjetarfidEliminar);
//
//				Permission estacionView = new Permission("estacion_view", "Permitir ver Estaciones");
//				permissionRepository.save(estacionView);
//
//				Permission estacionCrear = new Permission("estacion_crear", "Permitir crear Estaciones");
//				permissionRepository.save(estacionCrear);
//
//				Permission estacionEditar = new Permission("estacion_editar", "Permitir editar Estaciones");
//				permissionRepository.save(estacionEditar);
//
//				Permission estacionEliminar = new Permission("estacion_eliminar", "Permitir eliminar Estaciones");
//				permissionRepository.save(estacionEliminar);
//
//				Permission cargadorView = new Permission("cargador_view", "Permitir ver Cargadores");
//				permissionRepository.save(cargadorView);
//
//				Permission cargadorCrear = new Permission("cargador_crear", "Permitir crear Cargadores");
//				permissionRepository.save(cargadorCrear);
//
//				Permission cargadorEditar = new Permission("cargador_editar", "Permitir editar Cargadores");
//				permissionRepository.save(cargadorEditar);
//
//				Permission cargadorEliminar = new Permission("cargador_eliminar", "Permitir eliminar Cargadores");
//				permissionRepository.save(cargadorEliminar);
//
//				Permission cargadorMantenimientoCrear = new Permission("cargador_mantenimiento_crear", "Permitir Crear Mantenimiento Cargador");
//				permissionRepository.save(cargadorMantenimientoCrear);
//
//				Permission cargadorMantenimientoAsignar = new Permission("cargador_mantenimiento_asignar", "Permitir Asignar Mantenimiento Cargador");
//				permissionRepository.save(cargadorMantenimientoAsignar);
//
//				Permission conectorView = new Permission("conector_view", "Permitir ver Conectores");
//				permissionRepository.save(conectorView);
//
//				Permission conectorCrear = new Permission("conector_crear", "Permitir crear Conector");
//				permissionRepository.save(conectorCrear);
//
//				Permission conectorEditar = new Permission("conector_editar", "Permitir editar Conector");
//				permissionRepository.save(conectorEditar);
//
//				Permission conectorEliminar = new Permission("conector_eliminar", "Permitir eliminar Conector");
//				permissionRepository.save(conectorEliminar);
//
//				Permission configuracionCorreoAlertaView = new Permission("configuracion_correo_alerta_view", "Permitir ver Configuración de Correos Alertas");
//				permissionRepository.save(configuracionCorreoAlertaView);
//
//				Permission reporteAlarmaDiariaView = new Permission("reportes_alarma_diaria_view", "Permitir ver Alarmas diarias");
//				permissionRepository.save(reporteAlarmaDiariaView);
//
//				Permission rolesCreate = new Permission("roles_create", "Permitir crear Roles");
//				permissionRepository.save(rolesCreate);
//
//				Permission tarjetarfidAsignar = new Permission("tartetarfid_asignar", "Permitir Asignar TarjetasRFID");
//				permissionRepository.save(tarjetarfidAsignar);
//
//				Permission reportesVentaPorEstacionView = new Permission("reportes_venta_por_estacion_view", "Permitir ver Reportes venta por estacion");
//				permissionRepository.save(reportesVentaPorEstacionView);
//
//				Permission configuracionCorreoAlertaAgregar = new Permission("configuracion_correo_alerta_agregar", "Permitir agregar correo para alerta");
//				permissionRepository.save(configuracionCorreoAlertaAgregar);
//
//				Permission configuracionCorreoAlertaEliminar = new Permission("configuracion_correo_alerta_eliminar", "Permitir eliminar correo para alerta");
//				permissionRepository.save(configuracionCorreoAlertaEliminar);
//
//				Permission vehiculosDescargarPlantillaExcel = new Permission("vehiculos_descargar_plantilla_Excel", "Permitir Descargar Plantilla Excel Vehiculos");
//				permissionRepository.save(vehiculosDescargarPlantillaExcel);
//
//				Permission vehiculosSubirPlantillaExcel = new Permission("vehiculos_subir_plantilla_Excel", "Permitir Subir Plantilla Excel Vehiculos");
//				permissionRepository.save(vehiculosSubirPlantillaExcel);
//
//				Permission cargadorFabricanteCrear = new Permission("cargador_fabricante_crear", "Permitir crear Fabricante Cargador");
//				permissionRepository.save(cargadorFabricanteCrear);
//
//				Permission cargadorModeloCrear = new Permission("cargador_modelo_crear", "Permitir crear Modelo Cargador");
//				permissionRepository.save(cargadorModeloCrear);
//
//				Permission conectorTipoCrear = new Permission("conector_tipo_crear", "Permitir crear Tipo de Conector");
//				permissionRepository.save(conectorTipoCrear);
//
//				System.out.println("OK");
//
//
//				List<Permission> permisos = permissionRepository.findAll();
//
//				System.out.println("Creando Empresa Default ...");
//
//				Empresa defaultEmpresa = new Empresa();
//				defaultEmpresa.setNombre("Empresa Predeterminada");
//				empresaRepository.save(defaultEmpresa);
//
//				System.out.println("OK");
//
//				System.out.println("Creando Rol ADMIN Default ...");
//				Role adminRole = new Role();
//				adminRole.setNombre("ADMIN");
//				adminRole.setPermisos(permisos);
//				adminRole.setEmpresa(defaultEmpresa);
//
//				roleRepository.save(adminRole);
//				System.out.println("OK");
//
//
//				System.out.println("Creando cuenta compañía default ....");
//				// Crear la cuenta principal de la compañía
//				String numeroDeCuenta = "admin-" + getStringRandomNumber();
//				String encriptedPassword = passwordEncoder.encode("password");
//				Account account = new Account(
//						"Mi Empresa",
//						"Mi Empresa",
//						"Mi Empresa",
//						numeroDeCuenta,
//						"Cuenta Principal de Mi Empresa",
//						LocalDate.now(),
//						"correo@miempresa.com",
//						encriptedPassword,
//						TypeAccounts.COMPANY,
//						adminRole,
//						"8889874322",
//						"13.445.221-1",
//						defaultEmpresa,
//						true,
//						false,
//						false
//				);
//
//				account.setActivo(true);
//				accountRepository.save(account);
//				System.out.println("OK");
//
//
//				System.out.println("Creando fabricante de prueba ....");
//				ChargerManufacturer chargerManufacturer = new ChargerManufacturer(
//						"Fabricante de Prueba 1"
//				);
//				chargerManufacturerService.saveChargerManufacturer(chargerManufacturer);
//				System.out.println("OK");
//
//				System.out.println("Creando Modelo De prueba ...");
//				ChargerModel chargerModel = new ChargerModel(
//						"Modelo de Prueba 1"
//				);
//				chargerModelService.saveChargerModel(chargerModel);
//				System.out.println("OK");
//
//				System.out.println("Creando tipos de conectores de prueba ...");
//				NewTypeConnectorDTO typeConnector1 = new NewTypeConnectorDTO("Tipo 1");
//				NewTypeConnectorDTO typeConnector2 = new NewTypeConnectorDTO("Tipo 2");
//				NewTypeConnectorDTO typeConnector3 = new NewTypeConnectorDTO("CSS");
//
//				connectorService.saveTypeConnector(typeConnector1);
//				connectorService.saveTypeConnector(typeConnector2);
//				connectorService.saveTypeConnector(typeConnector3);
//				System.out.println("OK");
//				System.out.println("Entidades Defaults creadas");
//			} else {
//				System.out.println("Entidades Defaults ya estaban creadas");
//			}
		};
	}

	private String getStringRandomNumber() {
		int min = 10000000;
		int max = 99999999;
		int randomNumber = (int) ((Math.random() * (max - min)) + min);
		return String.valueOf(randomNumber);
	}

//	@Bean
//	public OcppClient ocppClient() {
//		return new OcppClient(URI.create("ws://localhost:8088/ocpp"));
//	}
}
