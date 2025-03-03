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
        List<PerfilCargaCargador> perfiles = perfilCargaRepository.findAll();
        ZonedDateTime ahora = ZonedDateTime.now(ZoneId.systemDefault());

        for (PerfilCargaCargador perfil : perfiles) {
            Fee tarifa = perfil.getTarifa();
            if (tarifa != null) {
                tarifa.getDiasDeLaSemana().size(); // Inicializa la colección perezosa
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

            if (perfil.getEstado() == EstadoPerfil.PENDIENTE) {
                ZonedDateTime inicio = perfil.getValidFrom().atZone(ZoneOffset.UTC).withZoneSameInstant(ZoneId.systemDefault());
                boolean dayMatches = tarifa != null && tarifa.getDiasDeLaSemana().contains(ahora.getDayOfWeek().toString());

                if (inicio.isBefore(ahora) && dayMatches) {
                    logger.info("Perfil pendiente ID " + perfil.getId() + " debería estar activo ahora, intentando aplicar.");
                    applyChargingProfile(charger, perfil);
                } else if (inicio.isAfter(ahora)) {
                    logger.info("Programando envío futuro para perfil ID " + perfil.getId());
                    scheduleProfileActivation(charger, perfil, inicio);
                }
            } else if (perfil.getEstado() == EstadoPerfil.ACTIVO && perfil.getValidTo() != null) {
                ZonedDateTime fin = perfil.getValidTo().atZone(ZoneOffset.UTC).withZoneSameInstant(ZoneId.systemDefault());
                if (fin.isBefore(ahora)) {
                    logger.info("Perfil activo ID " + perfil.getId() + " expiró, restaurando perfil por defecto.");
                    restoreDefaultProfile(charger);
                } else if (fin.isAfter(ahora)) {
                    logger.info("Programando restauración del perfil por defecto para perfil ID " + perfil.getId());
                    scheduleDefaultProfileRestoration(charger, fin);
                }
            }
        }
        logger.info("Revisión de perfiles completada.");
    }

    private void applyChargingProfile(Charger charger, PerfilCargaCargador perfil) {
        SetChargingProfileRequest ocppRequest = convertirAPerfilOcpp(perfil);
        logger.info("Enviando perfil de carga al cargador " + charger.getoCPPid());
        logger.info("Perfil: " + ocppRequest);
        ResponseEntity<?> ocppResponse = ocppController.setChargingProfile(charger.getoCPPid(), ocppRequest);
        if (ocppResponse.getStatusCode() == HttpStatus.OK) {
            perfil.setEstado(EstadoPerfil.ACTIVO);
            perfilCargaRepository.save(perfil);
            logger.info("Perfil aplicado exitosamente al cargador " + charger.getoCPPid());
        } else {
            String errorBody = ocppResponse.getBody() != null ? ocppResponse.getBody().toString() : "Error desconocido";
            logger.warn("Fallo al aplicar perfil al cargador " + charger.getoCPPid() + ": " + errorBody);
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
                applyChargingProfile(charger, defaultProfile);
            } else {
                logger.warn("No hay un perfil por defecto vigente para el cargador " + charger.getoCPPid());
            }
        } else {
            logger.warn("No se encontró un perfil por defecto (stackLevel=0) para el cargador " + charger.getoCPPid());
        }
    }

    private SetChargingProfileRequest convertirAPerfilOcpp(PerfilCargaCargador perfil) {
        ChargingSchedulePeriod period = new ChargingSchedulePeriod();
        period.setStartPeriod(perfil.getStartPeriod());
        period.setLimit(perfil.getLimite());

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
        chargingProfile.setChargingProfileId(perfil.getChargingProfileId());
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