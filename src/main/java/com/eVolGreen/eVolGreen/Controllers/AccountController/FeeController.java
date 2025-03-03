package com.eVolGreen.eVolGreen.Controllers.AccountController;

import com.eVolGreen.eVolGreen.DTOS.AccountDTO.FeeDTO.FeeDTO;
import com.eVolGreen.eVolGreen.DTOS.AccountDTO.FeeDTO.NewFeeDTO;
import com.eVolGreen.eVolGreen.DTOS.AccountDTO.FeeDTO.NewFeeWithProfileRequest;
import com.eVolGreen.eVolGreen.DTOS.ChargingStationDTO.ChargerDTO.ChargerDTO;
import com.eVolGreen.eVolGreen.Models.Account.Account;
import com.eVolGreen.eVolGreen.Models.Account.Empresa;
import com.eVolGreen.eVolGreen.Models.Account.Fee.EstadoPerfil;
import com.eVolGreen.eVolGreen.Models.Account.Fee.Fee;
import com.eVolGreen.eVolGreen.Models.ChargingStation.Charger.Charger;
import com.eVolGreen.eVolGreen.Models.ChargingStation.Charger.PerfilCargaCargador;
import com.eVolGreen.eVolGreen.Models.Ocpp.Controller.OcppController;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Core.Requests.Enums.ChargingProfileKindType;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Core.Requests.Enums.ChargingProfilePurposeType;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Core.Requests.Enums.ChargingRateUnitType;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Core.Requests.Enums.RecurrencyKindType;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Core.Requests.Utils.ChargingProfile;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Core.Requests.Utils.ChargingSchedule;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Core.Requests.Utils.ChargingSchedulePeriod;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.SmartCharging.Confirmations.Enums.ChargingProfileStatus;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.SmartCharging.Confirmations.SetChargingProfileConfirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.SmartCharging.Request.SetChargingProfileRequest;
import com.eVolGreen.eVolGreen.Services.AccountService.AccountService;
import com.eVolGreen.eVolGreen.Services.AccountService.AuditLogService;
import com.eVolGreen.eVolGreen.Services.AccountService.FeeService;
import com.eVolGreen.eVolGreen.Services.ChargingStationService.ChargerService;
import com.eVolGreen.eVolGreen.Services.ChargingStationService.PerfilCargaCargadorService;
import com.eVolGreen.eVolGreen.Services.ChargingStationService.ProfileSchedulerService;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api")
public class FeeController {

    @Autowired
    private FeeService feeService;

    @Autowired
    private AuditLogService auditLogService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private PerfilCargaCargadorService perfilCargaRepository;

    @Autowired
    private ChargerService chargerService;

    @Autowired
    private OcppController ocppController;

    @Autowired
    private ProfileSchedulerService profileSchedulerService;

    private static final Logger logger = LoggerFactory.getLogger(FeeController.class);

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    @GetMapping("/fees")
    public List<FeeDTO> getFees(Authentication authentication) {
        String email = authentication.getName();
        return feeService.getFeesDTO(email);
    }

    @GetMapping("/fees/{id}")
    public ResponseEntity<Object> getFeeDTOById(@PathVariable Long id) {
        String message;
        FeeDTO feeDTO = feeService.getFeeDTOById(id);

        if (feeDTO == null) {
            message = "No se encontró la tarifa con el id proporcionado.";
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(feeDTO, HttpStatus.OK);
    }

    @PostConstruct
    public void init() {
        logger.info("Inicializando FeeController...");
        profileSchedulerService.initializePendingProfiles();
    }

    @PostMapping("/fees")
    @Transactional
    public ResponseEntity<Object> createFee(Authentication authentication, @RequestBody NewFeeWithProfileRequest request) {
        Optional<Account> optionalAccount = accountService.findByEmail(authentication.getName());
        String mensaje;

        if (optionalAccount.isEmpty()) {
            mensaje = "La cuenta no se encontró";
            return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
        }

        Account account = optionalAccount.get();
        Empresa empresa = account.getEmpresa();
        if (empresa == null) {
            mensaje = "No se encontró una empresa asociada a la cuenta";
            return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
        }

        Fee tarifaRequest = request.getTarifa();
        PerfilCargaCargador perfilRequest = request.getPerfilCarga();

        // Validaciones básicas
        if (tarifaRequest == null || perfilRequest == null) {
            mensaje = "Tanto la tarifa como el perfil de carga son obligatorios";
            return new ResponseEntity<>(mensaje, HttpStatus.BAD_REQUEST);
        }
        if (tarifaRequest.getNombreTarifa() == null) {
            return new ResponseEntity<>("El nombre de la tarifa es necesario", HttpStatus.BAD_REQUEST);
        }
        if (tarifaRequest.getFechaInicio() == null) {
            return new ResponseEntity<>("La fecha de inicio es necesaria", HttpStatus.BAD_REQUEST);
        }
        if (tarifaRequest.getDiasDeLaSemana() == null || tarifaRequest.getDiasDeLaSemana().isEmpty()) {
            return new ResponseEntity<>("Es necesario marcar al menos un día de la semana", HttpStatus.BAD_REQUEST);
        }
        if (tarifaRequest.getPrecioTarifa() == null) {
            return new ResponseEntity<>("El precio de la tarifa es necesario", HttpStatus.BAD_REQUEST);
        }
        if (tarifaRequest.getNombreCargador() == null) {
            return new ResponseEntity<>("El nombre del cargador es necesario", HttpStatus.BAD_REQUEST);
        }
        if (perfilRequest.getLimite() == null) {
            return new ResponseEntity<>("El límite de energía es necesario", HttpStatus.BAD_REQUEST);
        }
        if (perfilRequest.getStartPeriod() == null) {
            return new ResponseEntity<>("El periodo de inicio es necesario", HttpStatus.BAD_REQUEST);
        }
        if (perfilRequest.getConnectorId() == null) {
            return new ResponseEntity<>("El ID del conector es necesario", HttpStatus.BAD_REQUEST);
        }
        if (perfilRequest.getChargingRateUnit() == null) {
            return new ResponseEntity<>("La unidad de tasa de carga es necesaria", HttpStatus.BAD_REQUEST);
        }
        if (perfilRequest.getStackLevel() == null) {
            return new ResponseEntity<>("El nivel de pila es necesario", HttpStatus.BAD_REQUEST);
        }
        if (perfilRequest.getChargingProfilePurpose() == null) {
            return new ResponseEntity<>("El propósito del perfil de carga es necesario", HttpStatus.BAD_REQUEST);
        }
        if (perfilRequest.getChargingProfileKind() == null) {
            return new ResponseEntity<>("El tipo de perfil de carga es necesario", HttpStatus.BAD_REQUEST);
        }

        // Crear instancias en memoria
        Fee nuevaTarifa = new Fee();
        nuevaTarifa.setNombreTarifa(tarifaRequest.getNombreTarifa());
        nuevaTarifa.setFechaInicio(tarifaRequest.getFechaInicio());
        nuevaTarifa.setFechaFin(tarifaRequest.getFechaFin());
        nuevaTarifa.setDiasDeLaSemana(new HashSet<>(tarifaRequest.getDiasDeLaSemana()));
        nuevaTarifa.setPrecioTarifa(tarifaRequest.getPrecioTarifa());
        nuevaTarifa.setConsumoDeEnergiaAlarma(tarifaRequest.getConsumoDeEnergiaAlarma());
        nuevaTarifa.setNombreConector(tarifaRequest.getNombreConector());
        nuevaTarifa.setNombreCargador(tarifaRequest.getNombreCargador());

        PerfilCargaCargador perfil = new PerfilCargaCargador();
        perfil.setConnectorId(perfilRequest.getConnectorId());
        perfil.setChargingProfileId(perfilRequest.getChargingProfileId());
        perfil.setStackLevel(perfilRequest.getStackLevel());
        perfil.setChargingRateUnit(perfilRequest.getChargingRateUnit());
        perfil.setLimite(perfilRequest.getLimite());
        perfil.setChargingProfilePurpose(perfilRequest.getChargingProfilePurpose());
        perfil.setChargingProfileKind(perfilRequest.getChargingProfileKind());
        perfil.setStartPeriod(perfilRequest.getStartPeriod());
        perfil.setStartSchedule(perfilRequest.getStartSchedule());
        perfil.setRecurrencyKind(perfilRequest.getRecurrencyKind());
        perfil.setDuration(perfilRequest.getDuration());
        perfil.setMinChargingRate(perfilRequest.getMinChargingRate());
        perfil.setNumberPhases(perfilRequest.getNumberPhases() != null ? perfilRequest.getNumberPhases() : 1);
        perfil.setValidFrom(tarifaRequest.getFechaInicio().withZoneSameInstant(ZoneOffset.UTC).toLocalDateTime());
        perfil.setValidTo(tarifaRequest.getFechaFin() != null ? tarifaRequest.getFechaFin().withZoneSameInstant(ZoneOffset.UTC).toLocalDateTime() : null);

        Charger charger = chargerService.findByOCPPid(nuevaTarifa.getNombreCargador());
        if (charger == null) {
            mensaje = "No se encontró un cargador con el OCPP ID: " + nuevaTarifa.getNombreCargador();
            return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
        }

        nuevaTarifa.setEmpresa(empresa);
        nuevaTarifa.setActivo(true);
        perfil.setEstado(EstadoPerfil.PENDIENTE);
        perfil.setChargerId(charger.getId());

        nuevaTarifa.setPerfilCarga(perfil);
        perfil.setTarifa(nuevaTarifa);

        ZonedDateTime ahora = ZonedDateTime.now(nuevaTarifa.getFechaInicio().getZone());
        ZonedDateTime inicio = nuevaTarifa.getFechaInicio();
        boolean dayMatches = nuevaTarifa.getDiasDeLaSemana().contains(ahora.getDayOfWeek().toString());
        logger.info("Ahora: " + ahora + ", Inicio: " + inicio + ", DayMatches: " + dayMatches);

        if (dayMatches && !ahora.isBefore(inicio)) {
            ResponseEntity<?> ocppResponse = applyChargingProfilePreview(charger, perfil);
            if (ocppResponse.getStatusCode() == HttpStatus.OK) {
                SetChargingProfileConfirmation confirmation = (SetChargingProfileConfirmation) ocppResponse.getBody();
                if (confirmation != null && confirmation.getStatus() == ChargingProfileStatus.Accepted) {
                    Fee tarifaGuardada = feeService.saveFee(nuevaTarifa);
                    perfil.setEstado(EstadoPerfil.ACTIVO);
                    perfilCargaRepository.save(perfil);
                    logger.info("Tarifa guardada con ID: " + tarifaGuardada.getId() + " y perfil aplicado exitosamente");
                    if (tarifaGuardada.getFechaFin() != null) {
                        scheduleDefaultProfileRestoration(charger, tarifaGuardada.getFechaFin(), perfil);
                    }
                    String descripcion = "Usuario " + account.getEmail() + " creó una nueva tarifa con el nombre: " + nuevaTarifa.getNombreTarifa();
                    auditLogService.recordAction(descripcion, account);
                    return new ResponseEntity<>(tarifaGuardada, HttpStatus.CREATED);
                } else {
                    mensaje = "El perfil de carga fue rechazado por el cargador: " + (confirmation != null ? confirmation.getStatus() : "Sin estado");
                    return new ResponseEntity<>(mensaje, HttpStatus.BAD_REQUEST);
                }
            } else {
                mensaje = "Fallo al comunicarse con el cargador: " + ocppResponse.getStatusCode() + " - " + (ocppResponse.getBody() != null ? ocppResponse.getBody().toString() : "Sin detalles");
                return new ResponseEntity<>(mensaje, HttpStatus.BAD_REQUEST);
            }
        } else {
            long delay = Duration.between(ahora, inicio).toMillis();
            if (delay < 0) delay = 0;
            scheduler.schedule(() -> {
                ResponseEntity<?> ocppResponse = applyChargingProfilePreview(charger, perfil);
                if (ocppResponse.getStatusCode() == HttpStatus.OK) {
                    SetChargingProfileConfirmation confirmation = (SetChargingProfileConfirmation) ocppResponse.getBody();
                    if (confirmation != null && confirmation.getStatus() == ChargingProfileStatus.Accepted) {
                        Fee tarifaGuardada = feeService.saveFee(nuevaTarifa);
                        perfil.setEstado(EstadoPerfil.ACTIVO);
                        perfilCargaRepository.save(perfil);
                        logger.info("Tarifa guardada con ID: " + tarifaGuardada.getId() + " y perfil aplicado exitosamente");
                        if (tarifaGuardada.getFechaFin() != null) {
                            scheduleDefaultProfileRestoration(charger, tarifaGuardada.getFechaFin(), perfil);
                        }
                        String descripcion = "Usuario " + account.getEmail() + " creó una nueva tarifa con el nombre: " + nuevaTarifa.getNombreTarifa();
                        auditLogService.recordAction(descripcion, account);
                    } else {
                        logger.warn("Perfil rechazado por el cargador, no se guardó la tarifa: " + (confirmation != null ? confirmation.getStatus() : "Sin estado"));
                    }
                } else {
                    logger.warn("Fallo al comunicarse con el cargador al programar: " + ocppResponse.getStatusCode() + " - " + (ocppResponse.getBody() != null ? ocppResponse.getBody().toString() : "Sin detalles"));
                }
            }, delay, TimeUnit.MILLISECONDS);
            logger.info("Programado envío de perfil para " + inicio + " con delay de " + delay + " ms");
            return new ResponseEntity<>("Perfil programado para " + inicio + ", se guardará solo si es aceptado por el cargador", HttpStatus.ACCEPTED);
        }
    }

    private ResponseEntity<?> applyChargingProfilePreview(Charger charger, PerfilCargaCargador perfil) {
        SetChargingProfileRequest ocppRequest = convertirAPerfilOcpp(perfil);
        logger.info("Probando envío de perfil al cargador " + charger.getoCPPid());
        logger.info("Perfil: " + ocppRequest);
        ResponseEntity<?> response = ocppController.setChargingProfile(charger.getoCPPid(), ocppRequest);
        logger.debug("Respuesta del cargador: Status=" + response.getStatusCode() + ", Body=" + response.getBody());
        return response;
    }

    private void applyChargingProfile(Charger charger, PerfilCargaCargador perfil) {
        // Buscar y desactivar cualquier perfil activo existente
        PerfilCargaCargador perfilActivoActual = perfilCargaRepository.findByChargerIdAndEstado(charger.getId(), EstadoPerfil.ACTIVO);
        if (perfilActivoActual != null && !perfilActivoActual.getId().equals(perfil.getId())) {
            logger.info("Desactivando perfil activo actual ID: " + perfilActivoActual.getId() + " para cargador " + charger.getoCPPid());
            perfilActivoActual.setEstado(EstadoPerfil.PENDIENTE);
            perfilCargaRepository.save(perfilActivoActual);
        } else {
            logger.debug("No se encontró perfil activo actual o es el mismo perfil para cargador " + charger.getoCPPid());
        }

        SetChargingProfileRequest ocppRequest = convertirAPerfilOcpp(perfil);
        logger.info("Enviando perfil de carga al cargador " + charger.getoCPPid());
        logger.info("Perfil: " + ocppRequest);
        ResponseEntity<?> ocppResponse = ocppController.setChargingProfile(charger.getoCPPid(), ocppRequest);
        logger.debug("Respuesta del cargador: Status=" + ocppResponse.getStatusCode() + ", Body=" + ocppResponse.getBody());

        if (ocppResponse.getStatusCode() == HttpStatus.OK) {
            SetChargingProfileConfirmation confirmation = (SetChargingProfileConfirmation) ocppResponse.getBody();
            if (confirmation != null && confirmation.getStatus() == ChargingProfileStatus.Accepted) {
                perfil.setEstado(EstadoPerfil.ACTIVO);
                perfilCargaRepository.save(perfil);
                logger.info("Perfil aplicado exitosamente al cargador " + charger.getoCPPid() + ", ID: " + perfil.getId());
            } else {
                logger.warn("Perfil rechazado por el cargador " + charger.getoCPPid() + ": " + (confirmation != null ? confirmation.getStatus() : "Sin estado"));
            }
        } else {
            String errorBody = ocppResponse.getBody() != null ? ocppResponse.getBody().toString() : "Error desconocido";
            logger.error("Fallo al aplicar perfil al cargador " + charger.getoCPPid() + ": Status=" + ocppResponse.getStatusCode() + ", Detalle=" + errorBody);
        }
    }

    private void scheduleDefaultProfileRestoration(Charger charger, ZonedDateTime fin, PerfilCargaCargador perfilTemporal) {
        long delay = Duration.between(ZonedDateTime.now(ZoneId.systemDefault()), fin).toMillis();
        if (delay < 0) delay = 0;
        scheduler.schedule(() -> {
            logger.info("Iniciando restauración del perfil por defecto para cargador " + charger.getoCPPid());
            // Finalizar el perfil temporal
            if (perfilTemporal != null) {
                perfilTemporal.setEstado(EstadoPerfil.FINALIZADO);
                perfilCargaRepository.save(perfilTemporal);
                logger.info("Perfil temporal ID: " + perfilTemporal.getId() + " finalizado para cargador " + charger.getoCPPid());
            }
            // Restaurar el perfil por defecto
            restoreDefaultProfile(charger);
        }, delay, TimeUnit.MILLISECONDS);
        logger.info("Programada restauración del perfil por defecto para " + fin + " con delay de " + delay + " ms");
    }

    private void restoreDefaultProfile(Charger charger) {
        PerfilCargaCargador defaultProfile = perfilCargaRepository.findByChargerIdAndStackLevel(charger.getId(), 0);
        if (defaultProfile != null) {
            ZonedDateTime ahora = ZonedDateTime.now(ZoneId.systemDefault());
            Fee tarifaDefault = defaultProfile.getTarifa();
            logger.info("Restaurando perfil por defecto ID: " + defaultProfile.getId() + " para cargador " + charger.getoCPPid());
            defaultProfile.setEstado(EstadoPerfil.ACTIVO);
            perfilCargaRepository.save(defaultProfile);
            applyChargingProfile(charger, defaultProfile); // Enviar al cargador
        } else {
            logger.warn("No se encontró un perfil por defecto (stackLevel=0) para el cargador " + charger.getoCPPid());
        }
    }

    private SetChargingProfileRequest convertirAPerfilOcpp(PerfilCargaCargador perfil) {
        ChargingSchedulePeriod period1 = new ChargingSchedulePeriod();
        period1.setStartPeriod(perfil.getStartPeriod() != null ? perfil.getStartPeriod() : 0);
        period1.setLimit(perfil.getLimite() != null ? perfil.getLimite() : 32.0);
        period1.setNumberPhases(perfil.getNumberPhases() != null ? perfil.getNumberPhases() : 1);

        ChargingSchedule schedule = new ChargingSchedule();
        schedule.setChargingRateUnit(ChargingRateUnitType.valueOf(perfil.getChargingRateUnit()));
        schedule.setChargingSchedulePeriod(new ChargingSchedulePeriod[] { period1 });
        if (perfil.getDuration() != null) {
            schedule.setDuration(perfil.getDuration());
        }
        if (perfil.getMinChargingRate() != null) {
            schedule.setMinChargingRate(perfil.getMinChargingRate());
        }
        schedule.setStartSchedule(perfil.getStartSchedule() != null ? perfil.getStartSchedule().atZone(ZoneOffset.UTC) : null);

        ChargingProfile chargingProfile = new ChargingProfile();
        chargingProfile.setChargingProfileId(perfil.getChargingProfileId() != null ? perfil.getChargingProfileId() : 1);
        chargingProfile.setStackLevel(perfil.getStackLevel());
        chargingProfile.setChargingProfilePurpose(ChargingProfilePurposeType.valueOf(perfil.getChargingProfilePurpose()));
        chargingProfile.setChargingProfileKind(ChargingProfileKindType.valueOf(perfil.getChargingProfileKind()));
        if (perfil.getValidFrom() != null) {
            chargingProfile.setValidFrom(perfil.getValidFrom().atZone(ZoneOffset.UTC));
        }
        if (perfil.getValidTo() != null) {
            chargingProfile.setValidTo(perfil.getValidTo().atZone(ZoneOffset.UTC));
        }
        chargingProfile.setChargingSchedule(schedule);
        chargingProfile.setRecurrencyKind(RecurrencyKindType.valueOf(perfil.getRecurrencyKind()));

        SetChargingProfileRequest request = new SetChargingProfileRequest();
        request.setConnectorId(perfil.getConnectorId());
        request.setCsChargingProfiles(chargingProfile);

        return request;
    }

    @PutMapping("/fees/{id}")
    public ResponseEntity<Object> updateFee(Authentication authentication, @PathVariable Long id, @RequestBody NewFeeDTO feeDTO) {
        String mensaje;

        Optional<Account> optionalAccount = accountService.findByEmail(authentication.getName());
        if (optionalAccount.isEmpty()) {
            mensaje = "La cuenta no se encontró";
            return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
        }
        Account account = optionalAccount.get();

        Fee existingFee = feeService.findById(id);
        if (existingFee == null) {
            mensaje = "La tarifa no se encontró";
            return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
        }

        if (feeDTO.getNombreTarifa() == null) {
            mensaje = "El nombre es necesario";
            return new ResponseEntity<>(mensaje, HttpStatus.FORBIDDEN);
        }
        if (feeDTO.getFechaInicio() == null) {
            mensaje = "La Fecha de inicio es necesaria";
            return new ResponseEntity<>(mensaje, HttpStatus.FORBIDDEN);
        }
        if (feeDTO.getDiasDeLaSemana() == null) {
            mensaje = "Es necesario marcar al menos un día de la semana";
            return new ResponseEntity<>(mensaje, HttpStatus.FORBIDDEN);
        }
        if (feeDTO.getPrecioTarifa() == null) {
            mensaje = "El precio de la tarifa es necesario";
            return new ResponseEntity<>(mensaje, HttpStatus.FORBIDDEN);
        }
        if (feeDTO.getNombreCargador() == null) {
            mensaje = "El nombre del cargador es necesario";
            return new ResponseEntity<>(mensaje, HttpStatus.FORBIDDEN);
        }
        if (feeDTO.getConsumoDeEnergiaAlarma() == null) {
            mensaje = "El límite de energía es necesario";
            return new ResponseEntity<>(mensaje, HttpStatus.FORBIDDEN);
        }

        existingFee.setNombreTarifa(feeDTO.getNombreTarifa());
        existingFee.setFechaInicio(feeDTO.getFechaInicio());
        existingFee.setFechaFin(feeDTO.getFechaFin());
        existingFee.setDiasDeLaSemana(feeDTO.getDiasDeLaSemana());
        existingFee.setPrecioTarifa(feeDTO.getPrecioTarifa());

        feeService.saveFee(existingFee);

        String descripcion = "Usuario " + account.getEmail() + " modificó una tarifa con el nombre: " + existingFee.getNombreTarifa();
        auditLogService.recordAction(descripcion, account);

        mensaje = "La tarifa fue actualizada exitosamente";
        return ResponseEntity.status(HttpStatus.OK).body(mensaje);
    }

    @PatchMapping("/fees/{id}/delete")
    public ResponseEntity<Object> deleteFee(Authentication authentication, @PathVariable Long id) {
        String mensaje;

        Optional<Account> optionalAccount = accountService.findByEmail(authentication.getName());
        if (optionalAccount.isEmpty()) {
            mensaje = "La cuenta no se encontró";
            return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
        }
        Account account = optionalAccount.get();

        Fee existingFee = feeService.findById(id);
        if (existingFee == null) {
            mensaje = "La tarifa no se encontró";
            return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
        }

        if (!existingFee.getActivo()) {
            mensaje = "La tarifa ya está desactivada";
            return new ResponseEntity<>(mensaje, HttpStatus.BAD_REQUEST);
        }

        existingFee.setActivo(false);
        feeService.saveFee(existingFee);

        String descripcion = "Usuario " + account.getEmail() + " eliminó una tarifa con el nombre: " + existingFee.getNombreTarifa();
        auditLogService.recordAction(descripcion, account);

        mensaje = "La tarifa fue desactivada exitosamente";
        return ResponseEntity.ok(mensaje);
    }
}