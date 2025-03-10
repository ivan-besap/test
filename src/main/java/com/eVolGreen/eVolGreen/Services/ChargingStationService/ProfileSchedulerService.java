package com.eVolGreen.eVolGreen.Services.ChargingStationService;

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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class ProfileSchedulerService {

    @Autowired
    private PerfilCargaCargadorService perfilCargaRepository;

    @Autowired
    private ChargerService chargerService;

    @Autowired
    private OcppController ocppController;

    @Autowired
    private Environment env;

    private static final Logger logger = LoggerFactory.getLogger(ProfileSchedulerService.class);

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    @PostConstruct
    public void scheduleInitialProfileCheck() {
        if (!Arrays.asList(env.getActiveProfiles()).contains("test")) {
            long delay = 5 * 60 * 1000; // 5 minutos
            scheduler.schedule(this::initializePendingProfiles, delay, TimeUnit.MILLISECONDS);
            logger.info("Programada la revisión inicial de perfiles para dentro de 5 minutos.");
        } else {
            logger.info("Revisión inicial omitida en entorno de prueba.");
        }
    }

    @Transactional
    public void initializePendingProfiles() {
        logger.info("Iniciando revisión de perfiles pendientes y activos...");
        List<PerfilCargaCargador> perfiles = perfilCargaRepository.findAllWithFeeAndDays(); // Usar la nueva consulta
        ZonedDateTime ahora = ZonedDateTime.now(ZoneId.systemDefault());

        if (perfiles.isEmpty()) {
            logger.info("No se encontraron perfiles para revisar.");
            return;
        }

        logger.info("Total de perfiles encontrados: " + perfiles.size());

        for (PerfilCargaCargador perfil : perfiles) {
            try {
                Fee tarifa = perfil.getTarifa();
                if (tarifa != null) {
                    logger.debug("Perfil ID " + perfil.getId() + " tiene tarifa con días: " + tarifa.getDiasDeLaSemana());
                } else {
                    logger.warn("Perfil ID " + perfil.getId() + " no tiene tarifa asociada.");
                }

                Long chargerId = perfil.getChargerId();
                if (chargerId == null) {
                    logger.warn("Perfil ID " + perfil.getId() + " tiene chargerId nulo, omitiendo.");
                    continue;
                }

                Charger charger = chargerService.findById(chargerId);
                if (charger == null) {
                    logger.warn("Cargador no encontrado para perfil ID: " + perfil.getId() + " con chargerId: " + chargerId);
                    continue;
                }

                logger.debug("Revisando perfil ID: " + perfil.getId() + ", Estado: " + perfil.getEstado() + ", Charger: " + charger.getoCPPid() + ", ValidFrom: " + perfil.getValidFrom() + ", ValidTo: " + perfil.getValidTo());

                if (perfil.getEstado() == EstadoPerfil.PENDIENTE) {
                    ZonedDateTime inicio = perfil.getValidFrom().atZone(ZoneOffset.UTC).withZoneSameInstant(ZoneId.systemDefault());
                    boolean dayMatches = tarifa != null && tarifa.getDiasDeLaSemana().contains(ahora.getDayOfWeek().toString());

                    logger.debug("Perfil ID " + perfil.getId() + " - Inicio: " + inicio + ", Ahora: " + ahora + ", DayMatches: " + dayMatches);

                    if (inicio.isBefore(ahora) && dayMatches) {
                        logger.info("Perfil pendiente ID " + perfil.getId() + " debería estar activo ahora, intentando aplicar.");
                        applyChargingProfile(charger, perfil);
                    } else if (inicio.isAfter(ahora)) {
                        logger.info("Programando envío futuro para perfil ID " + perfil.getId() + " para " + inicio);
                        scheduleProfileActivation(charger, perfil, inicio);
                    } else if (inicio.isBefore(ahora) && !dayMatches) {
                        logger.info("Perfil pendiente ID " + perfil.getId() + " expiró y no aplica para el día actual (" + ahora.getDayOfWeek() + "), marcando como FINALIZADO y restaurando perfil por defecto.");
                        perfil.setEstado(EstadoPerfil.FINALIZADO);
                        perfilCargaRepository.save(perfil);
                        restoreDefaultProfile(charger);
                    } else {
                        logger.info("Perfil pendiente ID " + perfil.getId() + " no aplica para el día actual (" + ahora.getDayOfWeek() + ").");
                    }
                } else if (perfil.getEstado() == EstadoPerfil.ACTIVO && perfil.getValidTo() != null) {
                    ZonedDateTime fin = perfil.getValidTo().atZone(ZoneOffset.UTC).withZoneSameInstant(ZoneId.systemDefault());
                    logger.debug("Perfil ID " + perfil.getId() + " - Fin: " + fin + ", Ahora: " + ahora);

                    if (fin.isBefore(ahora)) {
                        logger.info("Perfil activo ID " + perfil.getId() + " expiró, marcando como FINALIZADO y restaurando perfil por defecto.");
                        perfil.setEstado(EstadoPerfil.FINALIZADO);
                        perfilCargaRepository.save(perfil);
                        restoreDefaultProfile(charger);
                    } else if (fin.isAfter(ahora)) {
                        logger.info("Programando restauración del perfil por defecto para perfil ID " + perfil.getId() + " para " + fin);
                        scheduleDefaultProfileRestoration(charger, fin);
                    }
                } else {
                    logger.debug("Perfil ID " + perfil.getId() + " en estado " + perfil.getEstado() + " no requiere acción.");
                }
            } catch (Exception e) {
                logger.error("Error procesando perfil ID " + perfil.getId() + ": " + e.getMessage(), e);
            }
        }
        logger.info("Revisión de perfiles completada.");
    }

    private void applyChargingProfile(Charger charger, PerfilCargaCargador perfil) {
        List<PerfilCargaCargador> perfilesActivos = perfilCargaRepository.findAllByChargerIdAndEstado(charger.getId(), EstadoPerfil.ACTIVO);
        for (PerfilCargaCargador activo : perfilesActivos) {
            if (!activo.getId().equals(perfil.getId())) {
                logger.info("Desactivando perfil activo ID: " + activo.getId() + " para cargador " + charger.getoCPPid());
                activo.setEstado(EstadoPerfil.PENDIENTE);
                perfilCargaRepository.save(activo);
            }
        }

        SetChargingProfileRequest ocppRequest = convertirAPerfilOcpp(perfil);
        logger.info("Enviando perfil de carga al cargador " + charger.getoCPPid());
        logger.info("Perfil: " + ocppRequest);
        ResponseEntity<?> ocppResponse = ocppController.setChargingProfile(charger.getoCPPid(), ocppRequest);
        logger.debug("Respuesta OCPP: Status=" + ocppResponse.getStatusCode() + ", Body=" + ocppResponse.getBody());

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

    public void scheduleProfileActivation(Charger charger, PerfilCargaCargador perfil, ZonedDateTime inicio) {
        long delay = Duration.between(ZonedDateTime.now(ZoneId.systemDefault()), inicio).toMillis();
        if (delay < 0) delay = 0;
        scheduler.schedule(() -> {
            Fee tarifa = perfil.getTarifa();
            ZonedDateTime ahora = ZonedDateTime.now(ZoneId.systemDefault());
            if (tarifa != null && tarifa.estaVigente(ahora)) {
                applyChargingProfile(charger, perfil);
            } else {
                logger.info("Perfil ID " + perfil.getId() + " no aplica en " + ahora + " según la tarifa.");
            }
        }, delay, TimeUnit.MILLISECONDS);
        logger.info("Programado envío de perfil ID " + perfil.getId() + " para " + inicio + " con delay de " + delay + " ms");
    }

    public void scheduleDefaultProfileRestoration(Charger charger, ZonedDateTime fin) {
        long delay = Duration.between(ZonedDateTime.now(ZoneId.systemDefault()), fin).toMillis();
        if (delay < 0) delay = 0;
        scheduler.schedule(() -> {
            restoreDefaultProfile(charger);
        }, delay, TimeUnit.MILLISECONDS);
        logger.info("Programada restauración del perfil por defecto para " + fin + " con delay de " + delay + " ms");
    }

    private void restoreDefaultProfile(Charger charger) {
        PerfilCargaCargador defaultProfile = perfilCargaRepository.findByChargerIdAndStackLevel(charger.getId(), 0);
        if (defaultProfile != null) {
            ZonedDateTime ahora = ZonedDateTime.now(ZoneId.systemDefault());
            Fee tarifaDefault = defaultProfile.getTarifa();
            if (tarifaDefault != null && tarifaDefault.estaVigente(ahora)) {
                logger.info("Restaurando perfil por defecto ID: " + defaultProfile.getId() + " para cargador " + charger.getoCPPid());
                applyChargingProfile(charger, defaultProfile);
            } else {
                logger.warn("El perfil por defecto ID " + defaultProfile.getId() + " no está vigente en " + ahora);
            }
        } else {
            logger.warn("No se encontró un perfil por defecto (stackLevel=0) para el cargador " + charger.getoCPPid());
        }
    }

    private SetChargingProfileRequest convertirAPerfilOcpp(PerfilCargaCargador perfil) {
        ChargingSchedulePeriod period = new ChargingSchedulePeriod();
        period.setStartPeriod(perfil.getStartPeriod() != null ? perfil.getStartPeriod() : 0);
        period.setLimit(perfil.getLimite() != null ? perfil.getLimite() : 32.0);
        period.setNumberPhases(perfil.getNumberPhases() != null ? perfil.getNumberPhases() : 1);

        ChargingSchedule schedule = new ChargingSchedule();
        schedule.setChargingRateUnit(ChargingRateUnitType.valueOf(perfil.getChargingRateUnit()));
        schedule.setChargingSchedulePeriod(new ChargingSchedulePeriod[] { period });
        if (perfil.getDuration() != null) {
            schedule.setDuration(perfil.getDuration());
        }
        if (perfil.getStartSchedule() != null) {
            schedule.setStartSchedule(perfil.getStartSchedule().atZone(ZoneOffset.UTC));
        }
        if (perfil.getMinChargingRate() != null) {
            schedule.setMinChargingRate(perfil.getMinChargingRate());
        }

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
        if (perfil.getRecurrencyKind() != null) {
            chargingProfile.setRecurrencyKind(RecurrencyKindType.valueOf(perfil.getRecurrencyKind()));
        }

        SetChargingProfileRequest request = new SetChargingProfileRequest();
        request.setConnectorId(perfil.getConnectorId());
        request.setCsChargingProfiles(chargingProfile);

        return request;
    }
}