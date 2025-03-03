package com.eVolGreen.eVolGreen;

import com.eVolGreen.eVolGreen.Configurations.MQ.WebSocketMetricsConfig;
import com.eVolGreen.eVolGreen.DTOS.ChargingStationDTO.NewTypeConnectorDTO;
import com.eVolGreen.eVolGreen.Models.Account.Account;
import com.eVolGreen.eVolGreen.Models.Account.Empresa;
import com.eVolGreen.eVolGreen.Models.Account.Permission.Permission;
import com.eVolGreen.eVolGreen.Models.Account.TypeOfAccount.TypeAccounts;
import com.eVolGreen.eVolGreen.Models.ChargingStation.Charger.ChargerManufacturer;
import com.eVolGreen.eVolGreen.Models.ChargingStation.Charger.ChargerModel;
import com.eVolGreen.eVolGreen.Models.ChargingStation.Charger.PerfilCargaCargador;
import com.eVolGreen.eVolGreen.Models.User.Role;
import com.eVolGreen.eVolGreen.Repositories.*;
import com.eVolGreen.eVolGreen.Services.ChargingStationService.ChargerManufacturerService;
import com.eVolGreen.eVolGreen.Services.ChargingStationService.ChargerModelService;
import com.eVolGreen.eVolGreen.Services.ChargingStationService.ConnectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@SpringBootApplication
@ComponentScan(basePackages = "com.eVolGreen.eVolGreen")
@EnableTransactionManagement
@EnableScheduling
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
									  LocationRepository locationRepository, ChargerManufacturerService chargerManufacturerService, ChargerModelService chargerModelService,
									  WebSocketMetricsConfig webSocketMetricsConfig, PerfilCargaCargadorRepository perfilCargaCargadorRepository) {
		return args -> {


			if (permissionRepository.count() == 0) {
				System.out.println("Creando permisos ...");
				//			// Crear y guardar permisos
				Permission dashboardView = new Permission("dashboard_view", "Permitir ver dashboard");
				permissionRepository.save(dashboardView);

//				Permission dashboardViewPorEstacion = new Permission("dashboard_view_por_estacion", "Permitir ver dashboard por estacion");
//				permissionRepository.save(dashboardViewPorEstacion);

				Permission ocppCommandsView = new Permission("gestiones_remotas_view", "Permitir ver Gestiones Remotas");
				permissionRepository.save(ocppCommandsView);

				Permission ocppCommandsReiniciarCarga = new Permission("gestiones_remotas_reiniciar_carga", "Permitir Reiniciar Cargador Mediante Gestión Remota");
				permissionRepository.save(ocppCommandsReiniciarCarga);

				Permission ocppCommandsIniciarCarga = new Permission("gestiones_remotas_iniciar_carga", "Permitir Iniciar Carga Mediante Gestión Remota");
				permissionRepository.save(ocppCommandsIniciarCarga);

				Permission ocppCommandsDetenerCarga = new Permission("gestiones_remotas_detener_carga", "Permitir Detener Carga Mediante Gestión Remota");
				permissionRepository.save(ocppCommandsDetenerCarga);

//				Permission ocppCommandsHabilitarCarga = new Permission("ocpp_commands_habilitar_carga", "Permitir Habilitar Carga OCPP");
//				permissionRepository.save(ocppCommandsHabilitarCarga);
//
//				Permission ocppCommandsDeshabilitarCarga = new Permission("ocpp_commands_deshabilitar_carga", "Permitir Deshabilitar Carga OCPP");
//				permissionRepository.save(ocppCommandsDeshabilitarCarga);

				Permission ocppCommandsDesbloquearCarga = new Permission("gestiones_remotas_desbloquear_carga", "Permitir Desbloquear Conector Mediante Gestión Remota");
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

				Permission verFlota = new Permission("flotas_view", "Permitir ver Flotas");
				permissionRepository.save(verFlota);

				Permission crearFlota = new Permission("flotas_create", "Permitir crear Flotas");
				permissionRepository.save(crearFlota);

				Permission editarFlota = new Permission("flotas_edit", "Permitir Editar Flotas");
				permissionRepository.save(editarFlota);

				Permission eliminarFlota = new Permission("flotas_delete", "Permitir Eliminar Flotas");
				permissionRepository.save(eliminarFlota);

				Permission asignarVehiculo = new Permission("vehiculo_asignar", "Permitir Asignar Vehiculo");
				permissionRepository.save(asignarVehiculo);

				Permission vehiculoView = new Permission("vehiculos_view", "Permitir ver Vehiculos");
				permissionRepository.save(vehiculoView);

				Permission vehiculoCrear = new Permission("vehiculos_crear", "Permitir crear Vehiculos");
				permissionRepository.save(vehiculoCrear);

				Permission vehiculoEditar = new Permission("vehiculos_editar", "Permitir editar Vehiculos");
				permissionRepository.save(vehiculoEditar);

				Permission vehiculoEliminar = new Permission("vehiculos_eliminar", "Permitir eliminar Vehiculos");
				permissionRepository.save(vehiculoEliminar);

				Permission vehiculoDescargarPlantilla = new Permission("vehiculos_descargar_plantilla", "Permitir Descargar Plantilla Excel Vehículos");
				permissionRepository.save(vehiculoDescargarPlantilla);

				Permission vehiculosSubirPlantillaExcel = new Permission("vehiculos_subir_plantilla_Excel", "Permitir Subir Plantilla Excel Vehiculos");
				permissionRepository.save(vehiculosSubirPlantillaExcel);

				Permission reporteCargaView = new Permission("reportes_cargas_app_view", "Permitir ver Reportes de cargas APP");
				permissionRepository.save(reporteCargaView);

				Permission reporteDetalleCargaView = new Permission("reportes_cargas_rfid_view", "Permitir ver Reportes de cargas RFID");
				permissionRepository.save(reporteDetalleCargaView);

//				Permission reporteCargaCargadorView = new Permission("reportes_cargas_cargador_view", "Permitir ver Reportes cargas por cargador");
//				permissionRepository.save(reporteCargaCargadorView);
//
//				Permission reporteCargasTerminalView = new Permission("reportes_cargas_terminal_view", "Permitir ver Reportes cargas por terminal");
//				permissionRepository.save(reporteCargasTerminalView);
//
//				Permission reporteCargasFlotaView = new Permission("reportes_cargas_flota_view", "Permitir ver Reportes cargas por flota");
//				permissionRepository.save(reporteCargasFlotaView);

				Permission reporteErroresConectorView = new Permission("reportes_errores_conector_view", "Permitir ver Reportes errores por conector");
				permissionRepository.save(reporteErroresConectorView);


				Permission reporteVentasPorEstacion = new Permission("reportes_ventas_estacion_view", "Permitir ver Reportes Ventas por Estación");
				permissionRepository.save(reporteVentasPorEstacion);

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

				Permission rolesCrear = new Permission("roles_create", "Permitir crear Roles");
				permissionRepository.save(rolesCrear);

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

				Permission tarjetarfidAsignar = new Permission("tartetarfid_asignar", "Permitir asignar TarjetasRFID");
				permissionRepository.save(tarjetarfidAsignar);

				Permission estacionView = new Permission("estacion_view", "Permitir ver Estaciones");
				permissionRepository.save(estacionView);

				Permission estacionCrear = new Permission("estacion_crear", "Permitir crear Estaciones");
				permissionRepository.save(estacionCrear);

				Permission estacionEditar = new Permission("estacion_editar", "Permitir editar Estaciones");
				permissionRepository.save(estacionEditar);

				Permission estacionEliminar = new Permission("estacion_eliminar", "Permitir eliminar Estaciones");
				permissionRepository.save(estacionEliminar);

				Permission estacionLista = new Permission("estacion_lista", "Permitir ver Lista de Estaciones");
				permissionRepository.save(estacionLista);

				Permission cargadorView = new Permission("cargador_view", "Permitir ver Cargadores");
				permissionRepository.save(cargadorView);

				Permission cargadorCrear = new Permission("cargador_crear", "Permitir crear Cargadores");
				permissionRepository.save(cargadorCrear);

				Permission cargadorFabricanteCrear = new Permission("cargador_fabricante_crear", "Permitir crear Fabricante Cargador");
				permissionRepository.save(cargadorFabricanteCrear);

				Permission cargadorModeloCrear = new Permission("cargador_modelo_crear", "Permitir crear Modelo Cargador");
				permissionRepository.save(cargadorModeloCrear);

				Permission cargadorEditar = new Permission("cargador_editar", "Permitir editar Cargadores");
				permissionRepository.save(cargadorEditar);


				Permission cargaInteligente = new Permission("carga_inteligente", "Permitir modificar Carga Inteligente");
				permissionRepository.save(cargaInteligente);

				Permission cargadorEliminar = new Permission("cargador_eliminar", "Permitir eliminar Cargadores");
				permissionRepository.save(cargadorEliminar);

				Permission cargadorMantenimientoCrear = new Permission("cargador_mantenimiento_crear", "Permitir Crear Mantenimiento Cargador");
				permissionRepository.save(cargadorMantenimientoCrear);

				Permission cargadorMantenimientoAsignar = new Permission("cargador_mantenimiento_asignar", "Permitir Asignar Mantenimiento a Cargador");
				permissionRepository.save(cargadorMantenimientoAsignar);

				Permission cargadorMantenimientoVer = new Permission("mantenimiento_view", "Permitir Ver Mantenimientos");
				permissionRepository.save(cargadorMantenimientoVer);

				Permission cargadorMantenimientoEditar = new Permission("mantenimiento_edit", "Permitir Editar Mantenimientos");
				permissionRepository.save(cargadorMantenimientoEditar);

				Permission cargadorMantenimientoEliminar = new Permission("mantenimiento_delete", "Permitir Eliminar Mantenimientos");
				permissionRepository.save(cargadorMantenimientoEliminar);

				Permission conectorView = new Permission("conector_view", "Permitir ver Conectores");
				permissionRepository.save(conectorView);

				Permission conectorCrear = new Permission("conector_crear", "Permitir crear Conector");
				permissionRepository.save(conectorCrear);

				Permission conectorEditar = new Permission("conector_editar", "Permitir editar Conector");
				permissionRepository.save(conectorEditar);

				Permission conectorEliminar = new Permission("conector_eliminar", "Permitir eliminar Conector");
				permissionRepository.save(conectorEliminar);

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
//
//
////				Permission conectorTipoCrear = new Permission("conector_tipo_crear", "Permitir crear Tipo de Conector");
////				permissionRepository.save(conectorTipoCrear);
//
//				Permission estacionSwitch = new Permission("estacion_switch", "Permitir activar o desacttivar la estación");
//				permissionRepository.save(estacionSwitch);
//
//				Permission cargadorSwitch = new Permission("cargador_switch", "Permitir activar o desacttivar el cargador");
//				permissionRepository.save(cargadorSwitch);
//
//				Permission conectorSwitch = new Permission("conector_switch", "Permitir activar o desacttivar el conector");
//				permissionRepository.save(conectorSwitch);
//
//				Permission listaEstacionesDetallada = new Permission("lista_estaciones_detallada", "Permitir Ver la lista de estaciones asociadas");
//				permissionRepository.save(listaEstacionesDetallada);
//
				Permission listaFabricantes = new Permission("lista_fabricantes", "Permitir Ver la lista de fabricantes");
				permissionRepository.save(listaFabricantes);

				Permission listaModelos = new Permission("lista_modelos", "Permitir Ver la lista de modelos");
				permissionRepository.save(listaModelos);

				Permission actualizarFabricante = new Permission("actualizar_fabricante", "Permitir actualizar Fabricante");
				permissionRepository.save(actualizarFabricante);

				Permission actualizarModelo = new Permission("actualizar_modelo", "Permitir actualizar Modelo");
				permissionRepository.save(actualizarModelo);

				Permission eliminarFabricante = new Permission("eliminar_fabricante", "Permitir eliminar Fabricante");
				permissionRepository.save(eliminarFabricante);

				Permission eliminarModelo = new Permission("eliminar_modelo", "Permitir eliminar Modelo");
				permissionRepository.save(eliminarModelo);

//				Permission crearMantenimiento = new Permission("crear_mantenimiento", "Permitir crear Mantenimiento");
//				permissionRepository.save(crearMantenimiento);
//
//				Permission listaMantenimientos = new Permission("ver_mantenimientos", "Permitir ver Mantenimientos");
//				permissionRepository.save(listaMantenimientos);
//
//				Permission actualizarMantenimientos = new Permission("actualizar_mantenimientos", "Permitir actualizar Mantenimientos");
//				permissionRepository.save(actualizarMantenimientos);
//
//				Permission eliminarMantenimientos = new Permission("eliminar_mantenimientos", "Permitir eliminar Mantenimientos");
//				permissionRepository.save(eliminarMantenimientos);
//
//				Permission asignarMantenimientos = new Permission("asignar_mantenimientos", "Permitir asignar Mantenimientos");
//				permissionRepository.save(asignarMantenimientos);
//




				System.out.println("OK");


				List<Permission> permisos = permissionRepository.findAll();

				System.out.println("Creando Empresa Default ...");

				Empresa defaultEmpresa = new Empresa();
				defaultEmpresa.setNombre("Empresa Predeterminada");
				empresaRepository.save(defaultEmpresa);

				System.out.println("OK");

				System.out.println("Creando Rol ADMIN Default ...");
				Role adminRole = new Role();
				adminRole.setNombre("ADMIN");
				adminRole.setPermisos(permisos);
				adminRole.setEmpresa(defaultEmpresa);

				roleRepository.save(adminRole);
				System.out.println("OK");


				System.out.println("Creando cuenta compañía default ....");
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
						true,
						false,
						false
				);

				DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

				PerfilCargaCargador perfil = new PerfilCargaCargador();
				perfil.setConnectorId(1);
				perfil.setChargingProfileId(10);
				perfil.setStackLevel(2);
				perfil.setChargingProfilePurpose("TxProfile");
				perfil.setChargingProfileKind("Absolute");
				perfil.setValidFrom(LocalDateTime.parse("2025-01-01T00:00:00Z", formatter));
				perfil.setValidTo(LocalDateTime.parse("2025-12-31T00:00:00Z", formatter));

				perfilCargaCargadorRepository.save(perfil);
				System.out.println("Perfil creado correctamente.");

				account.setActivo(true);
				accountRepository.save(account);
				System.out.println("OK");


				System.out.println("Creando fabricante de prueba ....");
				ChargerManufacturer chargerManufacturer = new ChargerManufacturer(
						"Fabricante de Prueba 1",
						defaultEmpresa
				);
				chargerManufacturerService.saveChargerManufacturer(chargerManufacturer);
				System.out.println("OK");

				System.out.println("Creando Modelo De prueba ...");
				ChargerModel chargerModel = new ChargerModel(
						"Modelo de Prueba 1",
						defaultEmpresa
				);
				chargerModelService.saveChargerModel(chargerModel);
				System.out.println("OK");

				System.out.println("Creando tipos de conectores de prueba ...");
				NewTypeConnectorDTO typeConnector1 = new NewTypeConnectorDTO("Tipo 1");
				NewTypeConnectorDTO typeConnector2 = new NewTypeConnectorDTO("Tipo 2");
				NewTypeConnectorDTO typeConnector3 = new NewTypeConnectorDTO("CSS");

				connectorService.saveTypeConnector(typeConnector1);
				connectorService.saveTypeConnector(typeConnector2);
				connectorService.saveTypeConnector(typeConnector3);
				System.out.println("OK");
				System.out.println("Entidades Defaults creadas");
			} else {
				System.out.println("Entidades Defaults ya estaban creadas");
			}
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
