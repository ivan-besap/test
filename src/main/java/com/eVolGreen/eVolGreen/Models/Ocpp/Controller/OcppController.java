package com.eVolGreen.eVolGreen.Models.Ocpp.Controller;

import com.eVolGreen.eVolGreen.Configurations.MQ.WebSocketHandler;
import com.eVolGreen.eVolGreen.DTOS.AccountDTO.CarDTO.DeviceIdentifierDTO;
import com.eVolGreen.eVolGreen.DTOS.AccountDTO.TransactionDTO.ChargePointEnergyForMonthsProjection;
import com.eVolGreen.eVolGreen.DTOS.AccountDTO.TransactionDTO.ChargePointsSummaryResponseDTO;
import com.eVolGreen.eVolGreen.DTOS.AccountDTO.TransactionDTO.TotalEnergyResponseDTO;
import com.eVolGreen.eVolGreen.Models.Account.Transaction.TransactionInfo;
import com.eVolGreen.eVolGreen.Models.ChargingStation.Charger.Charger;
import com.eVolGreen.eVolGreen.Models.ChargingStation.ChargerStatus;
import com.eVolGreen.eVolGreen.Models.Ocpp.CargasOcpp;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Session;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.DiagnosticsFile;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.DiagnosticsFileRepository;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Core.Confirmations.*;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Core.Confirmations.Enums.*;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Core.Requests.*;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Firmware.Confirmations.GetDiagnosticsConfirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Firmware.Confirmations.UpdateFirmwareConfirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Firmware.Request.GetDiagnosticsRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Firmware.Request.UpdateFirmwareRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.LocalAuthList.Confirmations.GetLocalListVersionConfirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.LocalAuthList.Confirmations.SendLocalListConfirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.LocalAuthList.Request.GetLocalListVersionRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.LocalAuthList.Request.SendLocalListRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.RemoteTrigger.Confirmations.TriggerMessageConfirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.RemoteTrigger.Request.Enums.TriggerMessageRequestType;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.RemoteTrigger.Request.TriggerMessageRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Reservation.Confirmations.CancelReservationConfirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Reservation.Confirmations.ReserveNowConfirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Reservation.Request.CancelReservationRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Reservation.Request.ReserveNowRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.SmartCharging.Confirmations.ClearChargingProfileConfirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.SmartCharging.Confirmations.GetCompositeScheduleConfirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.SmartCharging.Confirmations.SetChargingProfileConfirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.SmartCharging.Request.ClearChargingProfileRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.SmartCharging.Request.GetCompositeScheduleRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.SmartCharging.Request.SetChargingProfileRequest;
import com.eVolGreen.eVolGreen.Repositories.CargasOcppRepository;
import com.eVolGreen.eVolGreen.Repositories.TransactionInfoRepository;
import com.eVolGreen.eVolGreen.Services.AccountService.DeviceIdentifierService;
import com.eVolGreen.eVolGreen.Services.AccountService.UtilService;
import com.eVolGreen.eVolGreen.Services.ChargingStationService.ChargerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api")
public class OcppController {

    @Autowired
    private WebSocketHandler webSocketHandler;

    @Autowired
    private UtilService utilService;

    @Autowired
    private CargasOcppRepository cargasOcppRepository;

    @Autowired
    private ChargerService chargerService;

    @Autowired
    private DiagnosticsFileRepository diagnosticsFileRepository;

    private static final int MAX_KEYS_LIMIT = 20;

    private static final Logger logger = LoggerFactory.getLogger(OcppController.class);

    @Autowired
    private DeviceIdentifierService deviceIdentifierService;

    @Autowired
    private TransactionInfoRepository transactionInfoRepository;

    @PostMapping("/iniciar-carga-remota")
    public ResponseEntity<?> iniciarCargaRemotaEnSimulador(
            @RequestParam String chargePointId,
            @RequestBody RemoteStartTransactionRequest request) {
        try {
            if (request == null || request.getIdTag() == null || request.getIdTag().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El campo 'idTag' es requerido.");
            }

            logger.info("Solicitud para iniciar carga remota recibida. Carcador: {}, idTag: {}", chargePointId, request.getIdTag());

            // Verifica si el chargingProfile está presente y si no tiene transactionId
            if (request.getChargingProfile() != null && request.getChargingProfile().getTransactionId() == null) {
                // Genera el transactionId si no está presente
                Integer transactionId = generarTransactionId();
                request.getChargingProfile().setTransactionId(transactionId);
                logger.info("Se generó un nuevo transactionId: {}", transactionId);
            }

            // Obtener el UUID de la sesión asociada al chargePointId
            UUID sessionUUID = webSocketHandler.getSessionIdByChargePointId(chargePointId);
            if (sessionUUID == null) {
                return ResponseEntity.badRequest().body("ChargePointId no encontrado o no está conectado.");
            }

            // Obtener la WebSocketSession asociada
            WebSocketSession webSocketSession = webSocketHandler.getWebSocketSessionById(sessionUUID.toString());
            if (webSocketSession == null || !webSocketSession.isOpen()) {
                return ResponseEntity.badRequest().body("WebSocketSession no está disponible o cerrada.");
            }

            // Obtener la sesión OCPP
            Session session = webSocketHandler.getSessionById(sessionUUID.toString());
            if (session == null) {
                return ResponseEntity.badRequest().body("Sesión OCPP no encontrada.");
            }

            List<CargasOcpp> registrosAnteriores = cargasOcppRepository.findByOcppIdAndNumeroConector(chargePointId, request.getConnectorId());
            if (!registrosAnteriores.isEmpty()) {
                registrosAnteriores.forEach(registro -> {
                    registro.setActivo(false);
                    cargasOcppRepository.save(registro);
                });
                logger.info("Registros anteriores desactivados para ocppId: {}, conector: {}", chargePointId, request.getConnectorId());
            }

            CargasOcpp nuevaCarga = new CargasOcpp();
            nuevaCarga.setOcppId(chargePointId);
            nuevaCarga.setNumeroConector(request.getConnectorId());
            nuevaCarga.setFechaCreacion(LocalDateTime.now());
            nuevaCarga.setActivo(true);

            cargasOcppRepository.save(nuevaCarga);

            // Enviar la solicitud y obtener el CompletableFuture
            CompletableFuture<RemoteStartTransactionConfirmation> future = webSocketHandler.sendRemoteStartTransactionRequestAsync(session, webSocketSession, request);

            // Esperar la confirmación con un timeout de 10 segundos
            RemoteStartTransactionConfirmation confirmation = future.get(10, TimeUnit.SECONDS);

           logger.info("Solicitud RemoteStartTransaction enviada exitosamente. Session: {}, chargePointId: {}", session, chargePointId);
            return ResponseEntity.ok(confirmation);

        } catch (Exception e) {
            logger.error("Error al enviar RemoteStartTransaction", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno: " + e.getMessage());
        }
    }

    // Método para generar un transactionId único
    private Integer generarTransactionId() {
        return Math.abs(UUID.randomUUID().hashCode());  // Genera un ID aleatorio único
    }

    @GetMapping("/obtener-idTag")
    public ResponseEntity<?> obtenerIdTag(@RequestParam String chargePointId) {
        String idTag = webSocketHandler.getAuthorizedIdTag(chargePointId);

        if (idTag == null) {
            idTag = "default";
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró una idTag autorizada para el chargePointId proporcionado.");
        }
        return ResponseEntity.ok(idTag);
    }

    @PostMapping("/detener-carga-remota")
    public ResponseEntity<?> detenerCargaRemotaEnSimulador(@RequestParam String chargePointId,
                                                           @RequestBody RemoteStopTransactionRequest request) {
        try {
            // 1. Validar parámetros
            if (chargePointId == null || chargePointId.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El parámetro 'chargePointId' es requerido.");
            }

            if (request == null || request.getTransactionId() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El campo 'transactionId' en el request es requerido.");
            }

            logger.info("Solicitud para detener carga remota recibida. Carcador: {}, TransactionId: {}", chargePointId, request.getTransactionId());

            // Obtener el UUID de la sesión asociada al chargePointId
            UUID sessionUUID = webSocketHandler.getSessionIdByChargePointId(chargePointId);
            if (sessionUUID == null) {
                return ResponseEntity.badRequest().body("ChargePointId no encontrado o no está conectado.");
            }

            // Obtener la WebSocketSession asociada
            WebSocketSession webSocketSession = webSocketHandler.getWebSocketSessionById(sessionUUID.toString());
            if (webSocketSession == null || !webSocketSession.isOpen()) {
                return ResponseEntity.badRequest().body("WebSocketSession no está disponible o cerrada.");
            }

            // Obtener la sesión OCPP
            Session session = webSocketHandler.getSessionById(sessionUUID.toString());
            if (session == null) {
                return ResponseEntity.badRequest().body("Sesión OCPP no encontrada.");
            }

            // Enviar la solicitud y obtener el CompletableFuture
            CompletableFuture<RemoteStopTransactionConfirmation> future = webSocketHandler.sendRemoteStopTransactionRequestAsync(session, webSocketSession,request);

            // Esperar la confirmación con un timeout de 10 segundos
            RemoteStopTransactionConfirmation confirmation = future.get(10, TimeUnit.SECONDS);

            logger.info("Solicitud RemoteStopTransaction enviada exitosamente. Session: {}, chargePointId: {}", session, chargePointId);
            return ResponseEntity.ok(confirmation);

        } catch (IllegalArgumentException e) {
            logger.error("Error en los parámetros de la solicitud: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Parámetro inválido: " + e.getMessage());
        } catch (Exception e) {
            logger.error("Error al procesar detener carga remota", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno: " + e.getMessage());
        }
    }

    @PostMapping("/change-configuration")
    public ResponseEntity<?> changeConfiguration(@RequestParam String chargePointId,
                                                 @RequestBody ChangeConfigurationRequest request) {

        // Obtener el UUID de la sesión asociada al chargePointId
        UUID sessionUUID = webSocketHandler.getSessionIdByChargePointId(chargePointId);
        if (sessionUUID == null) {
            return ResponseEntity.badRequest().body("ChargePointId no encontrado o no está conectado.");
        }

        // Obtener la WebSocketSession asociada
        WebSocketSession webSocketSession = webSocketHandler.getWebSocketSessionById(sessionUUID.toString());
        if (webSocketSession == null || !webSocketSession.isOpen()) {
            return ResponseEntity.badRequest().body("WebSocketSession no está disponible o cerrada.");
        }

        // Obtener la sesión OCPP
        Session session = webSocketHandler.getSessionById(sessionUUID.toString());
        if (session == null) {
            return ResponseEntity.badRequest().body("Sesión OCPP no encontrada.");
        }

        try {
            // Enviar la solicitud y obtener el CompletableFuture
            CompletableFuture<ChangeConfigurationConfirmation> future = webSocketHandler.sendChangeConfigurationRequestAsync(session, webSocketSession, request);

            // Esperar la confirmación con un timeout de 10 segundos
            ChangeConfigurationConfirmation confirmation = future.get(10, TimeUnit.SECONDS);

            logger.info("ChangeConfigurationRequest enviado a chargePointId: {}", chargePointId);
            return ResponseEntity.ok("ChangeConfigurationRequest enviado con éxito a chargePointId: " + chargePointId);

        } catch (Exception e) {
            logger.error("Error procesando la solicitud de ChangeConfiguration: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno al procesar la solicitud de ChangeConfiguration.");
        }
    }

    /**
     * Endpoint para enviar una solicitud GetConfigurationRequest a un cargador específico.
     *
     * @return La confirmación GetConfigurationConfirmation recibida del cargador.
     */
    @PostMapping("/getConfiguration")
    public ResponseEntity<?> getConfiguration(
            @RequestParam String chargePointId,
            @RequestBody(required = false) List<String> keys) {
        try {
            // Validación adicional de parámetros
            if (keys != null && keys.size() > MAX_KEYS_LIMIT) {
                return ResponseEntity.badRequest().body("Número de claves excede el límite permitido.");
            }

            // Obtener el UUID de la sesión asociada al chargePointId
            UUID sessionUUID = webSocketHandler.getSessionIdByChargePointId(chargePointId);
            if (sessionUUID == null) {
                return ResponseEntity.badRequest().body("ChargePointId no encontrado o no está conectado.");
            }

            // Obtener la WebSocketSession asociada
            WebSocketSession webSocketSession = webSocketHandler.getWebSocketSessionById(sessionUUID.toString());
            if (webSocketSession == null || !webSocketSession.isOpen()) {
                return ResponseEntity.badRequest().body("WebSocketSession no está disponible o cerrada.");
            }

            // Obtener la sesión OCPP
            Session session = webSocketHandler.getSessionById(sessionUUID.toString());
            if (session == null) {
                return ResponseEntity.badRequest().body("Sesión OCPP no encontrada.");
            }

            // Enviar la solicitud y obtener el CompletableFuture
            CompletableFuture<GetConfigurationConfirmation> future = webSocketHandler.sendGetConfigurationRequestAsync(session, webSocketSession, keys);

            // Esperar la confirmación con un timeout de 10 segundos
            GetConfigurationConfirmation confirmation = future.get(10, TimeUnit.SECONDS);

            logger.debug("GetConfigurationConfirmation recibido para messageId: {}", confirmation);
            return ResponseEntity.ok(confirmation);

        } catch (TimeoutException e) {
            return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body("Timeout esperando GetConfigurationConfirmation.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restaurar el estado de interrupción
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("El hilo fue interrumpido.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al procesar la solicitud: " + e.getMessage());
        }
    }

    @PostMapping("/trigger-status")
    public ResponseEntity<?> requestStatus(@RequestParam String chargePointId,
                                           @RequestBody TriggerMessageRequest request) {
        UUID sessionId = webSocketHandler.getSessionIdByChargePointId(chargePointId);
        if (sessionId == null) {
            logger.error("No se encontró una sesión activa para chargePointId: {}", chargePointId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontró una sesión activa para chargePointId: " + chargePointId);
        }

        Session session = WebSocketHandler.sessionStore.get(sessionId);
        WebSocketSession webSocketSession = WebSocketHandler.webSocketSessionStorage.get(sessionId);

        if (session == null || webSocketSession == null || !webSocketSession.isOpen()) {
            logger.error("La sesión para chargePointId {} no está activa.", chargePointId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("La sesión para chargePointId " + chargePointId + " no está activa.");
        }

        try {
            // Asegurar que el request tenga el requestedMessage correcto
            request.setRequestedMessage(TriggerMessageRequestType.StatusNotification);

            // Enviar la solicitud de TriggerMessage y obtener el CompletableFuture
            CompletableFuture<TriggerMessageConfirmation> future =
                    webSocketHandler.handleStatusNotificationTrigger(session, webSocketSession, request);

            // Esperar la confirmación con un timeout (por ejemplo 10 seg)
            TriggerMessageConfirmation confirmation = future.get(10, TimeUnit.SECONDS);

            logger.debug("TriggerMessageConfirmation recibido: {}", confirmation);
            return ResponseEntity.ok(confirmation);

        } catch (TimeoutException e) {
            return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body("Timeout esperando TriggerMessageConfirmation.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("El hilo fue interrumpido.");
        } catch (Exception e) {
            logger.error("Error procesando la solicitud TriggerMessage (StatusNotification): {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al procesar la solicitud: " + e.getMessage());
        }
    }

    @PostMapping("/trigger-heartbeat")
    public ResponseEntity<?> triggerHeartbeat(@RequestParam String chargePointId,
                                              @RequestBody TriggerMessageRequest request) {
        // Obtener el UUID de la sesión asociada al chargePointId
        UUID sessionId = webSocketHandler.getSessionIdByChargePointId(chargePointId);
        if (sessionId == null) {
            logger.error("No se encontró una sesión activa para chargePointId: {}", chargePointId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontró una sesión activa para chargePointId: " + chargePointId);
        }

        Session session = WebSocketHandler.sessionStore.get(sessionId);
        WebSocketSession webSocketSession = WebSocketHandler.webSocketSessionStorage.get(sessionId);

        // Verificar que la sesión y la conexión WebSocket estén activas
        if (session == null || webSocketSession == null || !webSocketSession.isOpen()) {
            logger.error("La sesión para chargePointId {} no está activa.", chargePointId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("La sesión para chargePointId " + chargePointId + " no está activa.");
        }

        try {
            // Asegurar que el request tenga el requestedMessage correcto
            request.setRequestedMessage(TriggerMessageRequestType.Heartbeat);

            // Enviar la solicitud TriggerMessage de manera asíncrona y obtener un CompletableFuture
            CompletableFuture<TriggerMessageConfirmation> future =
                    webSocketHandler.sendTriggerMessageRequestAsync(session, webSocketSession, request);

            // Esperar la confirmación con un timeout (por ejemplo, 10 segundos)
            TriggerMessageConfirmation confirmation = future.get(10, TimeUnit.SECONDS);

            logger.debug("TriggerMessageConfirmation recibido: {}", confirmation);
            actualizarEstadoCargador(chargePointId, ChargerStatus.ACTIVE);
            return ResponseEntity.ok(confirmation);

        } catch (TimeoutException e) {
            actualizarEstadoCargador(chargePointId, ChargerStatus.INACTIVE);
            return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT)
                    .body("Timeout esperando TriggerMessageConfirmation.");
        } catch (InterruptedException e) {
            actualizarEstadoCargador(chargePointId, ChargerStatus.INACTIVE);
            Thread.currentThread().interrupt();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("El hilo fue interrumpido.");
        } catch (Exception e) {
            actualizarEstadoCargador(chargePointId, ChargerStatus.INACTIVE);
            logger.error("Error procesando la solicitud TriggerMessage (Heartbeat): {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al procesar la solicitud: " + e.getMessage());
        }
    }

    private void actualizarEstadoCargador(String chargePointId, ChargerStatus status) {
        Charger charger = chargerService.findByOCPPid(chargePointId);
        if (charger != null) {
            charger.setEstadoCargador(status);
            chargerService.saveCharger(charger);
            logger.info("Estado del cargador {} actualizado a {}", chargePointId, status);
        } else {
            logger.warn("No se encontró el cargador con chargePointId: {}", chargePointId);
        }
    }

    @PostMapping("/trigger-diagnosticsStatusNotification")
    public ResponseEntity<?> triggerDiagnosticsStatusNotification(@RequestParam String chargePointId,
                                                                  @RequestBody TriggerMessageRequest request) {
        try {
            // Verificar que el punto de carga (chargePointId) esté conectado
            UUID sessionId = webSocketHandler.getSessionIdByChargePointId(chargePointId);
            if (sessionId == null) {
                logger.error("No se encontró una sesión activa para chargePointId: {}", chargePointId);
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No se encontró una sesión activa para chargePointId: " + chargePointId);
            }

            // Obtener la sesión OCPP y la sesión WebSocket
            Session session = WebSocketHandler.sessionStore.get(sessionId);
            WebSocketSession webSocketSession = WebSocketHandler.webSocketSessionStorage.get(sessionId);

            if (session == null || webSocketSession == null || !webSocketSession.isOpen()) {
                logger.error("La sesión para chargePointId {} no está activa.", chargePointId);
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("La sesión para chargePointId " + chargePointId + " no está activa.");
            }

            // Asegurar que el request tenga el requestedMessage correcto
            request.setRequestedMessage(TriggerMessageRequestType.DiagnosticsStatusNotification);

            // Enviar la solicitud TriggerMessage de manera asíncrona y obtener el Future de la confirmación
            CompletableFuture<TriggerMessageConfirmation> future = webSocketHandler.handleDiagnosticsStatusNotificationTrigger(session, webSocketSession, request);

            return ResponseEntity.ok("Solicitud de DiagnosticsStatusNotification enviada con éxito.");

        } catch (IOException e) {
            logger.error("Error al enviar TriggerMessageRequest", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error enviando solicitud");
        } catch (Exception e) {
            logger.error("Error al procesar la solicitud de TriggerMessage (DiagnosticsStatusNotification): {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al procesar la solicitud: " + e.getMessage());
        }
    }

    @PostMapping("/trigger-bootNotification")
    public ResponseEntity<?> triggerBootNotification(@RequestParam String chargePointId,
                                                     @RequestBody TriggerMessageRequest request) {
        UUID sessionId = webSocketHandler.getSessionIdByChargePointId(chargePointId);
        if (sessionId == null) {
            logger.error("No se encontró una sesión activa para chargePointId: {}", chargePointId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontró una sesión activa para chargePointId: " + chargePointId);
        }

        Session session = WebSocketHandler.sessionStore.get(sessionId);
        WebSocketSession webSocketSession = WebSocketHandler.webSocketSessionStorage.get(sessionId);

        if (session == null || webSocketSession == null || !webSocketSession.isOpen()) {
            logger.error("La sesión para chargePointId {} no está activa.", chargePointId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("La sesión para chargePointId " + chargePointId + " no está activa.");
        }

        try {
            // Asegurar que el request tenga el requestedMessage correcto
            request.setRequestedMessage(TriggerMessageRequestType.BootNotification);

            CompletableFuture<TriggerMessageConfirmation> future = webSocketHandler.handleBootNotificationTrigger(session, webSocketSession, request);

            // Esperar la confirmación con un timeout de 10 segundos
            TriggerMessageConfirmation confirmation = future.get(10, TimeUnit.SECONDS);

            logger.debug("TriggerMessageConfirmation recibido: {}", confirmation);
            return ResponseEntity.ok(confirmation);

        } catch (TimeoutException e) {
            return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body("Timeout esperando TriggerMessageConfirmation.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("El hilo fue interrumpido.");
        } catch (Exception e) {
            logger.error("Error al procesar la solicitud de TriggerMessage (BootNotification): {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al procesar la solicitud: " + e.getMessage());
        }
    }

    @PostMapping("/trigger-firmwareStatusNotification")
    public ResponseEntity<?> triggerFirmwareStatusNotification(@RequestParam String chargePointId,
                                                               @RequestBody TriggerMessageRequest request) {
        // Obtener el UUID de la sesión asociada al chargePointId
        UUID sessionId = webSocketHandler.getSessionIdByChargePointId(chargePointId);
        if (sessionId == null) {
            logger.error("No se encontró una sesión activa para chargePointId: {}", chargePointId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontró una sesión activa para chargePointId: " + chargePointId);
        }

        Session session = WebSocketHandler.sessionStore.get(sessionId);
        WebSocketSession webSocketSession = WebSocketHandler.webSocketSessionStorage.get(sessionId);

        // Verificar que la sesión y la conexión WebSocket estén activas
        if (session == null || webSocketSession == null || !webSocketSession.isOpen()) {
            logger.error("La sesión para chargePointId {} no está activa.", chargePointId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("La sesión para chargePointId " + chargePointId + " no está activa.");
        }

        try {
            // Asegurar que el request tenga el requestedMessage correcto antes de enviar
            request.setRequestedMessage(TriggerMessageRequestType.FirmwareStatusNotification);

            // Enviar la solicitud TriggerMessage de manera asíncrona y obtener un CompletableFuture
            CompletableFuture<TriggerMessageConfirmation> future =
                    webSocketHandler.sendTriggerMessageRequestAsync(session, webSocketSession, request);

            // Esperar la confirmación con un timeout de 10 segundos
            TriggerMessageConfirmation confirmation = future.get(10, TimeUnit.SECONDS);

            logger.debug("TriggerMessageConfirmation recibido: {}", confirmation);
            return ResponseEntity.ok(confirmation);

        } catch (TimeoutException e) {
            return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT)
                    .body("Timeout esperando TriggerMessageConfirmation.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("El hilo fue interrumpido.");
        } catch (Exception e) {
            logger.error("Error procesando la solicitud TriggerMessage (FirmwareStatusNotification): {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al procesar la solicitud: " + e.getMessage());
        }
    }

    @PostMapping("/trigger-meterValues")
    public ResponseEntity<?> triggerMeterValues(@RequestParam String chargePointId,
                                                @RequestBody TriggerMessageRequest request) {
        // Obtener el UUID de la sesión asociada al chargePointId
        UUID sessionId = webSocketHandler.getSessionIdByChargePointId(chargePointId);
        if (sessionId == null) {
            logger.error("No se encontró una sesión activa para chargePointId: {}", chargePointId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontró una sesión activa para chargePointId: " + chargePointId);
        }

        Session session = WebSocketHandler.sessionStore.get(sessionId);
        WebSocketSession webSocketSession = WebSocketHandler.webSocketSessionStorage.get(sessionId);

        // Verificar que la sesión y la conexión WebSocket estén activas
        if (session == null || webSocketSession == null || !webSocketSession.isOpen()) {
            logger.error("La sesión para chargePointId {} no está activa.", chargePointId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("La sesión para chargePointId " + chargePointId + " no está activa.");
        }

        try {
            // Asegurar que el request tenga el requestedMessage correcto
            request.setRequestedMessage(TriggerMessageRequestType.MeterValues);

            // Enviar la solicitud TriggerMessage de manera asíncrona y obtener un CompletableFuture
            CompletableFuture<TriggerMessageConfirmation> future =
                    webSocketHandler.sendTriggerMessageRequestAsync(session, webSocketSession, request);

            // Esperar la confirmación con un timeout de 10 segundos
            TriggerMessageConfirmation confirmation = future.get(10, TimeUnit.SECONDS);

            logger.debug("TriggerMessageConfirmation recibido: {}", confirmation);
            return ResponseEntity.ok(confirmation);

        } catch (TimeoutException e) {
            return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT)
                    .body("Timeout esperando TriggerMessageConfirmation.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("El hilo fue interrumpido.");
        } catch (Exception e) {
            logger.error("Error procesando la solicitud TriggerMessage (MeterValues): {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al procesar la solicitud: " + e.getMessage());
        }
    }

    @PostMapping("/unlock-connector")
    public ResponseEntity<?> unlockConnector(@RequestParam String chargePointId,
                                             @RequestBody UnlockConnectorRequest request) {
        try {
            // Validar el campo connectorId del request
            if (!request.validate()) {
                return ResponseEntity.badRequest().body("UnlockConnectorRequest inválido: connectorId debe ser mayor que 0.");
            }

            // Obtener el UUID de la sesión asociada al chargePointId
            UUID sessionUUID = webSocketHandler.getSessionIdByChargePointId(chargePointId);
            if (sessionUUID == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No se encontró una sesión activa para chargePointId: " + chargePointId);
            }

            // Obtener la WebSocketSession asociada
            WebSocketSession webSocketSession = webSocketHandler.getWebSocketSessionById(sessionUUID.toString());
            if (webSocketSession == null || !webSocketSession.isOpen()) {
                return ResponseEntity.badRequest().body("WebSocketSession no está disponible o cerrada.");
            }

            // Obtener la sesión OCPP
            Session session = webSocketHandler.getSessionById(sessionUUID.toString());
            if (session == null) {
                return ResponseEntity.badRequest().body("Sesión OCPP no encontrada.");
            }

            // Enviar la solicitud UnlockConnector
            CompletableFuture<UnlockConnectorConfirmation> future = webSocketHandler.sendUnlockConnectorRequestAsync(session,webSocketSession,request);

            // Esperar la confirmación con un timeout de 10 segundos
            Confirmation confirmation = future.get(10, TimeUnit.SECONDS);

            // Verificar que la confirmación sea del tipo UnlockConnectorConfirmation
            if (confirmation instanceof UnlockConnectorConfirmation) {
                UnlockConnectorConfirmation unlockConfirmation = (UnlockConnectorConfirmation) confirmation;
                logger.info("UnlockConnectorConfirmation recibida: {}", unlockConfirmation);

                // Puedes procesar aquí el estado de la confirmación si lo deseas
                return ResponseEntity.ok(unlockConfirmation);
            } else {
                logger.warn("Tipo de confirmación inesperado: {}", confirmation);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Error: Confirmación inesperada para UnlockConnector.");
            }

        } catch (TimeoutException e) {
            return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body("Timeout esperando UnlockConnectorConfirmation.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("El hilo fue interrumpido.");
        } catch (Exception e) {
            logger.error("Error procesando la solicitud de UnlockConnector: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno al procesar la solicitud de UnlockConnector.");
        }
    }

    @PostMapping("/reset-cargador")
    public ResponseEntity<String> resetCargador(@RequestParam String chargePointId,
                                                @RequestBody ResetRequest resetRequest) {
        try {
            // Validar parámetros
            if (resetRequest == null || resetRequest.getType() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El campo 'type' es requerido (Hard o Soft).");
            }

            UUID sessionId = webSocketHandler.getSessionIdByChargePointId(chargePointId);
            if (sessionId == null) {
                logger.error("No se encontró una sesión activa para chargePointId: {}", chargePointId);
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No se encontró una sesión activa para chargePointId: " + chargePointId);
            }

            Session session = WebSocketHandler.sessionStore.get(sessionId);
            WebSocketSession webSocketSession = WebSocketHandler.webSocketSessionStorage.get(sessionId);

            if (session == null || webSocketSession == null || !webSocketSession.isOpen()) {
                logger.error("La sesión para chargePointId {} no está activa.", chargePointId);
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("La sesión para chargePointId " + chargePointId + " no está activa.");
            }
            // Llamar a sendResetRequestAsync
            CompletableFuture<ResetConfirmation> future =
                    webSocketHandler.sendResetRequestAsync(session, webSocketSession, resetRequest);

            ResetConfirmation confirmation = future.get(10, TimeUnit.SECONDS);

            return ResponseEntity.ok("ResetConfirmation recibido: " + confirmation);

        } catch (Exception e) {
            logger.error("Error al enviar la solicitud de Reset", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno: " + e.getMessage());
        }
    }

    @PostMapping("/getDiagnostics")
    public ResponseEntity<?> getDiagnostics(
            @RequestParam String chargePointId,
            @RequestBody GetDiagnosticsRequest request
    ) {
        try {
            // Validar que el chargePointId y la request sean correctos
            if (chargePointId == null || chargePointId.isEmpty()) {
                return ResponseEntity.badRequest().body("El parámetro 'chargePointId' es requerido.");
            }
            if (!request.validate()) {
                return ResponseEntity.badRequest().body("GetDiagnosticsRequest inválido: 'location' es obligatorio.");
            }

            // Obtener la sesión OCPP
            UUID sessionUUID = webSocketHandler.getSessionIdByChargePointId(chargePointId);
            if (sessionUUID == null) {
                logger.error("No se encontró una sesión activa para chargePointId: {}", chargePointId);
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No se encontró una sesión activa para chargePointId: " + chargePointId);
            }

            // Obtener la WebSocketSession asociada
            WebSocketSession webSocketSession = webSocketHandler.getWebSocketSessionById(sessionUUID.toString());
            if (webSocketSession == null || !webSocketSession.isOpen()) {
                return ResponseEntity.badRequest().body("WebSocketSession no está disponible o cerrada.");
            }

            // Obtener la Session OCPP
            Session session = webSocketHandler.getSessionById(sessionUUID.toString());
            if (session == null) {
                return ResponseEntity.badRequest().body("Sesión OCPP no encontrada para chargePointId: " + chargePointId);
            }

            // Llamar a sendGetDiagnosticsRequestAsync
            CompletableFuture<GetDiagnosticsConfirmation> future =
                    webSocketHandler.sendGetDiagnosticsRequestAsync(session, webSocketSession, request);

            // Esperar la confirmación con un timeout (p.e. 10 segundos)
            GetDiagnosticsConfirmation confirmation = future.get(10, TimeUnit.SECONDS);

            logger.info("GetDiagnosticsConfirmation recibido: {}", confirmation);
            return ResponseEntity.ok(confirmation);

        } catch (TimeoutException e) {
            return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body("Timeout esperando GetDiagnosticsConfirmation.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("El hilo fue interrumpido.");
        } catch (Exception e) {
            logger.error("Error procesando la solicitud GetDiagnostics", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno: " + e.getMessage());
        }
    }

    @GetMapping("/get-session-by-idTag")
    public ResponseEntity<String> getSessionByIdTag(@RequestParam String idTag) {
        try {
            // Buscar la sesión correspondiente al idTag
            String sessionId = findSessionIdByIdTag(idTag);

            if (sessionId == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró una sesión para el idTag proporcionado.");
            }

            return ResponseEntity.ok("ID de sesión encontrado: " + sessionId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error buscando la sesión: " + e.getMessage());
        }
    }

    // Método que busca el sessionId basado en el idTag
    private String findSessionIdByIdTag(String idTag) {

        Map<String, String> idTagToSessionMap = getIdTagToSessionMap();  // Debes implementar este método

        return idTagToSessionMap.get(idTag);
    }

    // Método ejemplo para obtener el mapa de idTags a sessionIds
    private Map<String, String> getIdTagToSessionMap() {
        // Aquí deberías devolver el mapa que contiene la relación idTag -> sessionId
        // Este es solo un ejemplo, deberías adaptarlo a tu implementación.
        Map<String, String> idTagToSessionMap = new HashMap<>();
        idTagToSessionMap.put("ABCDE", "07eded6d-3a71-d110-13e0-89dd0e168e8f");
        // Agregar más asociaciones según sea necesario
        return idTagToSessionMap;
    }

    /**
     * Recibe el archivo de diagnóstico en formato binario y lo persiste en la base de datos.
     *
     * @param fileData bytes del archivo subido (por la estación o algún cliente).
     * @return Mensaje indicando el resultado de la operación.
     */
    @PostMapping(consumes = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<String> handleDiagnosticsUpload(@RequestBody byte[] fileData) {
        try {
            if (fileData == null || fileData.length == 0) {
                return ResponseEntity.badRequest().body("No se recibió ningún archivo de diagnóstico.");
            }

            DiagnosticsFile newFile = new DiagnosticsFile();
            newFile.setCreatedAt(LocalDateTime.now());
            newFile.setFileData(fileData);

            // Guardar en la DB
            diagnosticsFileRepository.save(newFile);

            return ResponseEntity.ok("Archivo de diagnóstico recibido y almacenado en la DB con ID: " + newFile.getId());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al guardar el archivo en la base de datos: " + e.getMessage());
        }
    }

    @PostMapping("/clear-cache")
    public ResponseEntity <?> clearCache   (@RequestParam String chargePointId,
                                            @RequestBody ClearCacheRequest request) {
        try {

            if (!request.validate()) {
                return ResponseEntity.badRequest().body("ClearCacheRequest inválido");
            }

            // Obtener el UUID de la sesión asociada al chargePointId
            UUID sessionUUID = webSocketHandler.getSessionIdByChargePointId(chargePointId);
            if (sessionUUID == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No se encontró una sesión activa para chargePointId: " + chargePointId);
            }

            // Obtener la WebSocketSession asociada
            WebSocketSession webSocketSession = webSocketHandler.getWebSocketSessionById(sessionUUID.toString());
            if (webSocketSession == null || !webSocketSession.isOpen()) {
                return ResponseEntity.badRequest().body("WebSocketSession no está disponible o cerrada.");
            }

            // Obtener la sesión OCPP
            Session session = webSocketHandler.getSessionById(sessionUUID.toString());
            if (session == null) {
                return ResponseEntity.badRequest().body("Sesión OCPP no encontrada.");
            }

            // Enviar la solicitud y obtener el CompletableFuture
            CompletableFuture<ClearCacheConfirmation> future =
                    webSocketHandler.sendClearCacheRequestAsync(session, webSocketSession, request);

            ClearCacheConfirmation confirmation = future.get(10, TimeUnit.SECONDS);

            if (confirmation.getStatus() != ClearCacheStatus.Accepted) {
                return ResponseEntity.badRequest().body("ClearCacheRequest no fue aceptado por el cargador.");
            }

            logger.info("ClearCacheConfirmation recibido: {}", confirmation);
            return ResponseEntity.ok(confirmation);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al limpiar el cache: " + e.getMessage());
        }
    }

    @PostMapping("/get-local-list-version")
    public ResponseEntity<?> getLocalListVersion(@RequestParam String chargePointId,
                                                 @RequestBody GetLocalListVersionRequest request) {
        try {
            // Obtener el UUID de la sesión asociada al chargePointId
            UUID sessionId = webSocketHandler.getSessionIdByChargePointId(chargePointId);
            if (sessionId == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No se encontró una sesión activa para chargePointId: " + chargePointId);
            }

            // Obtener la WebSocketSession asociada
            WebSocketSession webSocketSession = webSocketHandler.getWebSocketSessionById(sessionId.toString());
            if (webSocketSession == null || !webSocketSession.isOpen()) {
                return ResponseEntity.badRequest().body("WebSocketSession no está disponible o cerrada.");
            }

            // Obtener la sesión OCPP
            Session session = webSocketHandler.getSessionById(sessionId.toString());
            if (session == null) {
                return ResponseEntity.badRequest().body("Sesión OCPP no encontrada.");
            }

            // Enviar la solicitud y obtener el CompletableFuture
            CompletableFuture<GetLocalListVersionConfirmation> future =
                    webSocketHandler.sendGetLocalListVersionRequestAsync(session, webSocketSession,request);

            // Esperar la confirmación con un timeout de 10 segundos
            GetLocalListVersionConfirmation confirmation = future.get(10, TimeUnit.SECONDS);

            logger.info("GetLocalListVersionConfirmation recibido: {}", confirmation);
            return ResponseEntity.ok(confirmation);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener la versión de la lista local: " + e.getMessage());
        }
    }

    @PostMapping("/change-availability")
    public ResponseEntity<?> changeAvailability(@RequestParam String chargePointId,
                                                @RequestBody ChangeAvailabilityRequest request) {
        try {
            // Validar el request
            if (!request.validate()) {
                return ResponseEntity.badRequest().body("ChangeAvailabilityRequest inválido.");
            }

            // Obtener el UUID de la sesión asociada al chargePointId
            UUID sessionId = webSocketHandler.getSessionIdByChargePointId(chargePointId);
            if (sessionId == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No se encontró una sesión activa para chargePointId: " + chargePointId);
            }

            // Obtener la WebSocketSession asociada
            WebSocketSession webSocketSession = webSocketHandler.getWebSocketSessionById(sessionId.toString());
            if (webSocketSession == null || !webSocketSession.isOpen()) {
                return ResponseEntity.badRequest().body("WebSocketSession no está disponible o cerrada.");
            }

            // Obtener la sesión OCPP
            Session session = webSocketHandler.getSessionById(sessionId.toString());
            if (session == null) {
                return ResponseEntity.badRequest().body("Sesión OCPP no encontrada.");
            }

            // Enviar la solicitud y obtener el CompletableFuture
            CompletableFuture<ChangeAvailabilityConfirmation> future =
                    webSocketHandler.sendChangeAvailabilityRequestAsync(session, webSocketSession, request);

            // Esperar la confirmación con un timeout de 10 segundos
            ChangeAvailabilityConfirmation confirmation = future.get(10, TimeUnit.SECONDS);

            logger.info("ChangeAvailabilityConfirmation recibido: {}", confirmation);
            return ResponseEntity.ok(confirmation);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al cambiar la disponibilidad: " + e.getMessage());
        }
    }

    @PostMapping("/update-firmware")
    public ResponseEntity<?> updateFirmware(@RequestParam String chargePointId,
                                            @RequestBody UpdateFirmwareRequest request) {
        try {
            // Validar el request
            if (!request.validate()) {
                return ResponseEntity.badRequest().body("UpdateFirmwareRequest inválido.");
            }

            // Obtener el UUID de la sesión asociada al chargePointId
            UUID sessionId = webSocketHandler.getSessionIdByChargePointId(chargePointId);
            if (sessionId == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No se encontró una sesión activa para chargePointId: " + chargePointId);
            }

            // Obtener la WebSocketSession asociada
            WebSocketSession webSocketSession = webSocketHandler.getWebSocketSessionById(sessionId.toString());
            if (webSocketSession == null || !webSocketSession.isOpen()) {
                return ResponseEntity.badRequest().body("WebSocketSession no está disponible o cerrada.");
            }

            // Obtener la sesión OCPP
            Session session = webSocketHandler.getSessionById(sessionId.toString());
            if (session == null) {
                return ResponseEntity.badRequest().body("Sesión OCPP no encontrada.");
            }

            // Enviar la solicitud y obtener el CompletableFuture
            CompletableFuture<UpdateFirmwareConfirmation> future =
                    webSocketHandler.sendUpdateFirmwareRequestAsync(session, webSocketSession, request);

            // Esperar la confirmación con un timeout de 10 segundos
            UpdateFirmwareConfirmation confirmation = future.get(10, TimeUnit.SECONDS);

            logger.info("UpdateFirmwareConfirmation recibido: {}", confirmation);
            return ResponseEntity.ok(confirmation);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al actualizar el firmware: " + e.getMessage());
        }
    }

    @PostMapping("/data-transfer")
    public ResponseEntity<?> dataTransfer(@RequestParam String chargePointId,
                                          @RequestBody DataTransferRequest request) {
        try {
            // Validar el request
            if (!request.validate()) {
                return ResponseEntity.badRequest().body("DataTransferRequest inválido.");
            }

            // Obtener el UUID de la sesión asociada al chargePointId
            UUID sessionId = webSocketHandler.getSessionIdByChargePointId(chargePointId);
            if (sessionId == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No se encontró una sesión activa para chargePointId: " + chargePointId);
            }

            // Obtener la WebSocketSession asociada
            WebSocketSession webSocketSession = webSocketHandler.getWebSocketSessionById(sessionId.toString());
            if (webSocketSession == null || !webSocketSession.isOpen()) {
                return ResponseEntity.badRequest().body("WebSocketSession no está disponible o cerrada.");
            }

            // Obtener la sesión OCPP
            Session session = webSocketHandler.getSessionById(sessionId.toString());
            if (session == null) {
                return ResponseEntity.badRequest().body("Sesión OCPP no encontrada.");
            }

            // Enviar la solicitud y obtener el CompletableFuture
            CompletableFuture<DataTransferConfirmation> future =
                    webSocketHandler.sendDataTransferRequestAsync(session, webSocketSession, request);

            // Esperar la confirmación con un timeout de 10 segundos
            DataTransferConfirmation confirmation = future.get(10, TimeUnit.SECONDS);

            logger.info("DataTransferConfirmation recibido: {}", confirmation);
            return ResponseEntity.ok(confirmation);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al transferir datos: " + e.getMessage());
        }
    }

    @PostMapping("/send-local-list")
    public ResponseEntity<?> sendLocalList(@RequestParam String chargePointId,
                                           @RequestBody SendLocalListRequest request) {
        try {
            // Validar el request
            if (!request.validate()) {
                return ResponseEntity.badRequest().body("SendLocalListRequest inválido.");
            }

            // Obtener el UUID de la sesión asociada al chargePointId
            UUID sessionId = webSocketHandler.getSessionIdByChargePointId(chargePointId);
            if (sessionId == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No se encontró una sesión activa para chargePointId: " + chargePointId);
            }

            // Obtener la WebSocketSession asociada
            WebSocketSession webSocketSession = webSocketHandler.getWebSocketSessionById(sessionId.toString());
            if (webSocketSession == null || !webSocketSession.isOpen()) {
                return ResponseEntity.badRequest().body("WebSocketSession no está disponible o cerrada.");
            }

            // Obtener la sesión OCPP
            Session session = webSocketHandler.getSessionById(sessionId.toString());
            if (session == null) {
                return ResponseEntity.badRequest().body("Sesión OCPP no encontrada.");
            }

            // Enviar la solicitud y obtener el CompletableFuture
            CompletableFuture<SendLocalListConfirmation> future =
                    webSocketHandler.sendSendLocalListRequestAsync(session, webSocketSession, request);

            // Esperar la confirmación con un timeout de 10 segundos
            SendLocalListConfirmation confirmation = future.get(10, TimeUnit.SECONDS);

            logger.info("SendLocalListConfirmation recibido: {}", confirmation);
            return ResponseEntity.ok(confirmation);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al enviar la lista local: " + e.getMessage());
        }
    }

    @PostMapping("/get-composite-schedule")
    public ResponseEntity<?> getCompositeSchedule(@RequestParam String chargePointId,
                                                   @RequestBody GetCompositeScheduleRequest request) {
        try {
            // Validar el request
            if (!request.validate()) {
                return ResponseEntity.badRequest().body("GetCompositeScheduleRequest inválido.");
            }

            // Obtener el UUID de la sesión asociada al chargePointId
            UUID sessionId = webSocketHandler.getSessionIdByChargePointId(chargePointId);
            if (sessionId == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No se encontró una sesión activa para chargePointId: " + chargePointId);
            }

            // Obtener la WebSocketSession asociada
            WebSocketSession webSocketSession = webSocketHandler.getWebSocketSessionById(sessionId.toString());
            if (webSocketSession == null || !webSocketSession.isOpen()) {
                return ResponseEntity.badRequest().body("WebSocketSession no está disponible o cerrada.");
            }

            // Obtener la sesión OCPP
            Session session = webSocketHandler.getSessionById(sessionId.toString());
            if (session == null) {
                return ResponseEntity.badRequest().body("Sesión OCPP no encontrada.");
            }

            // Enviar la solicitud y obtener el CompletableFuture
            CompletableFuture<GetCompositeScheduleConfirmation> future =
                    webSocketHandler.sendGetCompositeScheduleRequestAsync(session, webSocketSession, request);

            // Esperar la confirmación con un timeout de 10 segundos
            GetCompositeScheduleConfirmation confirmation = future.get(10, TimeUnit.SECONDS);

            logger.info("GetCompositeScheduleConfirmation recibido: {}", confirmation);
            return ResponseEntity.ok(confirmation);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener el calendario compuesto: " + e.getMessage());
        }
    }

    @PostMapping("/clear-charging-profile")
    public ResponseEntity<?> clearChargingProfile(@RequestParam String chargePointId,
                                                   @RequestBody ClearChargingProfileRequest request) {
        try {
            // Validar el request
            if (!request.validate()) {
                return ResponseEntity.badRequest().body("ClearChargingProfileRequest inválido.");
            }

            // Obtener el UUID de la sesión asociada al chargePointId
            UUID sessionId = webSocketHandler.getSessionIdByChargePointId(chargePointId);
            if (sessionId == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No se encontró una sesión activa para chargePointId: " + chargePointId);
            }

            // Obtener la WebSocketSession asociada
            WebSocketSession webSocketSession = webSocketHandler.getWebSocketSessionById(sessionId.toString());
            if (webSocketSession == null || !webSocketSession.isOpen()) {
                return ResponseEntity.badRequest().body("WebSocketSession no está disponible o cerrada.");
            }

            // Obtener la sesión OCPP
            Session session = webSocketHandler.getSessionById(sessionId.toString());
            if (session == null) {
                return ResponseEntity.badRequest().body("Sesión OCPP no encontrada.");
            }

            // Enviar la solicitud y obtener el CompletableFuture
            CompletableFuture<ClearChargingProfileConfirmation> future =
                    webSocketHandler.sendClearChargingProfileRequestAsync(session, webSocketSession, request);

            // Esperar la confirmación con un timeout de 10 segundos
            ClearChargingProfileConfirmation confirmation = future.get(10, TimeUnit.SECONDS);

            logger.info("ClearChargingProfileConfirmation recibido: {}", confirmation);
            return ResponseEntity.ok(confirmation);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al limpiar el perfil de carga: " + e.getMessage());
        }
    }

    @PostMapping("/set-charging-profile")
    public ResponseEntity<?> setChargingProfile(@RequestParam String chargePointId,
                                                 @RequestBody SetChargingProfileRequest request) {
        try {
            // Validar el request
            if (!request.validate()) {
                return ResponseEntity.badRequest().body("SetChargingProfileRequest inválido.");
            }

            // Obtener el UUID de la sesión asociada al chargePointId
            UUID sessionId = webSocketHandler.getSessionIdByChargePointId(chargePointId);
            if (sessionId == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No se encontró una sesión activa para chargePointId: " + chargePointId);
            }

            // Obtener la WebSocketSession asociada
            WebSocketSession webSocketSession = webSocketHandler.getWebSocketSessionById(sessionId.toString());
            if (webSocketSession == null || !webSocketSession.isOpen()) {
                return ResponseEntity.badRequest().body("WebSocketSession no está disponible o cerrada.");
            }

            // Obtener la sesión OCPP
            Session session = webSocketHandler.getSessionById(sessionId.toString());
            if (session == null) {
                return ResponseEntity.badRequest().body("Sesión OCPP no encontrada.");
            }

            // Enviar la solicitud y obtener el CompletableFuture
            CompletableFuture<SetChargingProfileConfirmation> future =
                    webSocketHandler.sendSetChargingProfileRequestAsync(session, webSocketSession, request);

            // Esperar la confirmación con un timeout de 10 segundos
            SetChargingProfileConfirmation confirmation = future.get(10, TimeUnit.SECONDS);

            logger.info("SetChargingProfileConfirmation recibido: {}", confirmation);
            return ResponseEntity.ok(confirmation);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al establecer el perfil de carga: " + e.getMessage());
        }
    }

    @PostMapping("/cancel-reservation")
    public ResponseEntity<?> cancelReservation(@RequestParam String chargePointId,
                                                @RequestBody CancelReservationRequest request) {
        try {
            // Validar el request
            if (!request.validate()) {
                return ResponseEntity.badRequest().body("CancelReservationRequest inválido.");
            }

            // Obtener el UUID de la sesión asociada al chargePointId
            UUID sessionId = webSocketHandler.getSessionIdByChargePointId(chargePointId);
            if (sessionId == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No se encontró una sesión activa para chargePointId: " + chargePointId);
            }

            // Obtener la WebSocketSession asociada
            WebSocketSession webSocketSession = webSocketHandler.getWebSocketSessionById(sessionId.toString());
            if (webSocketSession == null || !webSocketSession.isOpen()) {
                return ResponseEntity.badRequest().body("WebSocketSession no está disponible o cerrada.");
            }

            // Obtener la sesión OCPP
            Session session = webSocketHandler.getSessionById(sessionId.toString());
            if (session == null) {
                return ResponseEntity.badRequest().body("Sesión OCPP no encontrada.");
            }

            // Enviar la solicitud y obtener el CompletableFuture
            CompletableFuture<CancelReservationConfirmation> future =
                    webSocketHandler.sendCancelReservationRequestAsync(session, webSocketSession, request);

            // Esperar la confirmación con un timeout de 10 segundos
            CancelReservationConfirmation confirmation = future.get(10, TimeUnit.SECONDS);

            logger.info("CancelReservationConfirmation recibido: {}", confirmation);
            return ResponseEntity.ok(confirmation);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al cancelar la reserva: " + e.getMessage());
        }
    }

    @PostMapping("/reserve-now")
    public ResponseEntity<?> reserveNow(@RequestParam String chargePointId,
                                         @RequestBody ReserveNowRequest request) {
        try {
            // Validar el request
            if (!request.validate()) {
                return ResponseEntity.badRequest().body("ReserveNowRequest inválido.");
            }

            // Obtener el UUID de la sesión asociada al chargePointId
            UUID sessionId = webSocketHandler.getSessionIdByChargePointId(chargePointId);
            if (sessionId == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No se encontró una sesión activa para chargePointId: " + chargePointId);
            }

            // Obtener la WebSocketSession asociada
            WebSocketSession webSocketSession = webSocketHandler.getWebSocketSessionById(sessionId.toString());
            if (webSocketSession == null || !webSocketSession.isOpen()) {
                return ResponseEntity.badRequest().body("WebSocketSession no está disponible o cerrada.");
            }

            // Obtener la sesión OCPP
            Session session = webSocketHandler.getSessionById(sessionId.toString());
            if (session == null) {
                return ResponseEntity.badRequest().body("Sesión OCPP no encontrada.");
            }

            // Enviar la solicitud y obtener el CompletableFuture
            CompletableFuture<ReserveNowConfirmation> future =
                    webSocketHandler.sendReserveNowRequestAsync(session, webSocketSession, request);

            // Esperar la confirmación con un timeout de 10 segundos
            ReserveNowConfirmation confirmation = future.get(10, TimeUnit.SECONDS);

            logger.info("ReserveNowConfirmation recibido: {}", confirmation);
            return ResponseEntity.ok(confirmation);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al reservar ahora: " + e.getMessage());
        }
    }

    @GetMapping("/obtener-transaccion")
    public ResponseEntity<?> obtenerTransaccion(
            @RequestParam String ocppId,
            @RequestParam Integer numeroConector) {
        try {
            // Buscar el registro activo
            CargasOcpp cargaActiva = cargasOcppRepository.findByOcppIdAndNumeroConectorAndActivo(ocppId, numeroConector, true);
            if (cargaActiva == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró una transacción activa para el ocppId y número de conector proporcionados.");
            }

            // Devolver el transactionId
            return ResponseEntity.ok(cargaActiva.getTransaccionId());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al obtener la transacción: " + e.getMessage());
        }
    }


    @GetMapping("/device-identifiers")
    public List<DeviceIdentifierDTO> getDeviceIdentifiers() {
        return deviceIdentifierService.getDeviceIdentifiersDTO();
    }

    /**
     * Obtiene el consumo total de energía por empresa y punto de carga.
     *
     * @param empresaId ID de la empresa
     * @return Lista con el consumo total por empresa y punto de carga
     */
    @GetMapping("/transactionInfo/totalEnergy")
    public List<TotalEnergyResponseDTO> getTotalEnergyConsumedByEmpresa(
            @RequestParam(value = "empresaId", required = true) Long empresaId) {

        // Obtener todas las transacciones de la empresa especificada
        List<TransactionInfo> transactions = transactionInfoRepository.findByEmpresaId(empresaId);

        // Agrupar por chargePointId y calcular el consumo total de energía
        Map<String, Integer> totalEnergyByChargePoint = transactions.stream()
                .filter(transaction -> transaction.getEnergyConsumed() != null)
                .collect(Collectors.groupingBy(
                        TransactionInfo::getChargePointId,
                        Collectors.summingInt(TransactionInfo::getEnergyConsumed)
                ));

        // Construir la respuesta con los datos agrupados
        return totalEnergyByChargePoint.entrySet().stream()
                .map(entry -> new TotalEnergyResponseDTO(
                        empresaId,
                        entry.getKey(), // chargePointId
                        entry.getValue() // totalEnergyConsumed
                ))
                .collect(Collectors.toList());
    }

    /**
     * Obtiene la lista de chargePointId asociados a una empresa y el consumo total de energía combinado.
     *
     * @param empresaId ID de la empresa
     * @return Detalles de los puntos de carga y el consumo total combinado
     */
    @GetMapping("/transactionInfo/chargePoints")
    public ChargePointsSummaryResponseDTO getChargePointsAndTotalEnergy(
            @RequestParam(value = "empresaId", required = true) Long empresaId) {

        // Obtener todas las transacciones de la empresa especificada
        List<TransactionInfo> transactions = transactionInfoRepository.findByEmpresaId(empresaId);

        // Obtener la lista de chargePointId únicos
        List<String> chargePointIds = transactions.stream()
                .map(TransactionInfo::getChargePointId)
                .distinct()
                .collect(Collectors.toList());

        // Calcular el consumo total de energía combinado
        int totalEnergyConsumed = transactions.stream()
                .filter(transaction -> transaction.getEnergyConsumed() != null)
                .mapToInt(TransactionInfo::getEnergyConsumed)
                .sum();

        // Construir la respuesta
        return new ChargePointsSummaryResponseDTO(empresaId, chargePointIds, totalEnergyConsumed);
    }


    @Cacheable("energyConsumedByMonth")
    @GetMapping("/transactionInfo/EnergyForMonths")
    public List<ChargePointEnergyForMonthsProjection> getEnergyForMonths(
            @RequestParam(value = "empresaId", required = true) Long empresaId) {
        return transactionInfoRepository.findEnergyConsumedByMonth(empresaId);
    }


    @GetMapping("/energyConsumed")
    public ResponseEntity<Double> getEnergyConsumed() {
        return ResponseEntity.ok(utilService.getTotalEnergy());
    }
}
