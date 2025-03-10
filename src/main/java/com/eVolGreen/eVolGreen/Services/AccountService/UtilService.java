package com.eVolGreen.eVolGreen.Services.AccountService;

import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Core.Requests.Enums.ResetType;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Core.Requests.RemoteStartTransactionRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Core.Requests.Utils.ChargingProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

import static org.apache.activemq.plugin.ForcePersistencyModeBroker.log;

@Service
public class UtilService {

    private String meterValuesFront;

    private double totalEnergy = 0.0;

    private static final Logger logger = LoggerFactory.getLogger(UtilService.class);

    private final RestTemplate restTemplate;  // Ahora inyectará el bean de RestTemplate automáticamente

    public UtilService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void enviarHeartbeatASimulador(String ocppid, boolean enableAutomatic, String vendorId) {
        String simuladorUrl = "http://localhost:8081/api/charge-point/send-heartbeat?ocppid={ocppid}&enableAutomatic={enableAutomatic}&vendorId={vendorId}";

        Map<String, Object> uriVariables = new HashMap<>();
        uriVariables.put("ocppid", ocppid);
        uriVariables.put("enableAutomatic", enableAutomatic);
        uriVariables.put("vendorId", vendorId);

        try {
            restTemplate.postForObject(simuladorUrl, null, String.class, uriVariables);
            log.info("Heartbeat enviado al simulador con éxito");
        } catch (Exception e) {
            log.error("Error enviando Heartbeat al simulador: " + e.getMessage());
        }
    }

    public void iniciarCargaEnSimulador(int connectorId) {
        String simuladorUrlPlugIn = "http://localhost:8081/api/charge-point/plug-in?connectorId={connectorId}";
        String simuladorUrlRfid = "http://localhost:8081/api/charge-point/rfid?idTag={idTag}";

        // Crear un mapa de parámetros para enviar al simulador para el endpoint plug-in
        Map<String, Object> uriVariablesPlugIn = new HashMap<>();
        uriVariablesPlugIn.put("connectorId", connectorId);

        // Crear un mapa de parámetros para enviar al simulador para el endpoint rfid
        Map<String, Object> uriVariablesRfid = new HashMap<>();
        String idTag = "123"; // idTag estático para pruebas
        uriVariablesRfid.put("idTag", idTag);

        try {
            // Realizar la solicitud al simulador para iniciar carga (plug-in)
            restTemplate.postForObject(simuladorUrlPlugIn, null, String.class, uriVariablesPlugIn);
            log.info("Solicitud de iniciar carga enviada al simulador con éxito");

            // Si la solicitud fue exitosa, llamar al endpoint de RFID
            restTemplate.postForObject(simuladorUrlRfid, null, String.class, uriVariablesRfid);
            log.info("Solicitud de autorización RFID enviada al simulador con éxito");
        } catch (Exception e) {
            log.error("Error enviando solicitud de iniciar carga o autorización RFID al simulador: " + e.getMessage());
        }
    }

    public void iniciarCargaRemotaEnSimulador(int connectorId, String idTag, ChargingProfile chargingProfile) throws Exception {
        String url = "http://localhost:8081/api/charge-point/remote-start";

        // Crear el payload con el perfil de carga
        RemoteStartTransactionRequest requestPayload = new RemoteStartTransactionRequest(idTag);
        requestPayload.setConnectorId(connectorId);
        requestPayload.setChargingProfile(chargingProfile);

        // Configurar los encabezados de la solicitud si son necesarios
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        // Crear la solicitud HTTP con el payload y los encabezados
        HttpEntity<RemoteStartTransactionRequest> requestEntity = new HttpEntity<>(requestPayload, headers);

        // Enviar la solicitud POST al simulador
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);

        // Verificar si la respuesta es exitosa
        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new Exception("Error al iniciar la carga remota en el simulador: " + response.getBody());
        }

        log.info("Carga remota en simulador iniciada con éxito para connectorId: {} y idTag: {}", connectorId, idTag);
    }

    public void detenerCargaRemotaEnSimulador(int transactionId) throws Exception {
        String url = "http://localhost:8081/api/charge-point/remote-stop?transactionId=" + transactionId;

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new Exception("Error al detener la carga remota en el simulador: " + response.getBody());
        }

        log.info("Carga remota detenida con éxito en el simulador para transactionId: {}", transactionId);
    }

    public synchronized void updateMeterValuesFront(String meterValuesJson) {
        this.meterValuesFront = meterValuesJson;
    }

    public synchronized void updateEnergySuppliedFront(Double energySupplied) {
        totalEnergy += energySupplied;
    }

    public synchronized String getMeterValuesFront() {
        return this.meterValuesFront;
    }

    public void detenerCargaEnSimulador() {
        String simuladorUrlPlugOut = "http://localhost:8081/api/charge-point/plug-out";

        try {
            // Realizar la solicitud al simulador para detener la carga (plug-out)
            restTemplate.postForObject(simuladorUrlPlugOut, null, String.class);
            log.info("Solicitud de detener carga enviada al simulador con éxito");
        } catch (Exception e) {
            log.error("Error enviando solicitud de detener carga al simulador: " + e.getMessage());
        }
    }

    public void desbloquearConectorSimulador(int connectorId) throws Exception {
        String url = "http://localhost:8081/api/charge-point/unlock-connector?connectorId=" + connectorId;

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new Exception("Error al desbloquear el conector en el simulador: " + response.getBody());
        }

        log.info("Éxito al desbloquear el conectorId: {}", connectorId);
    }

    public void resetCargadorSimulador(ResetType resetType, String ocppid) throws Exception {
        String url = "http://localhost:8081/api/charge-point/reset-cargador?resetType=" + resetType;

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new Exception("Error al resetear el cargador en el simulador: " + response.getBody());
        }

        log.info("Éxito al resetear el cargador: {}", ocppid);
    }

    public double getTotalEnergy() {
        return totalEnergy;
    }

    public String calculateTimeDifference(ZonedDateTime inicioCarga, ZonedDateTime timestamp) {
        // Calcular la diferencia en segundos
        long seconds = ChronoUnit.SECONDS.between(inicioCarga, timestamp);

        // Calcular horas, minutos y segundos
        long hours = seconds / 3600;
        long minutes = (seconds % 3600) / 60;
        long remainingSeconds = seconds % 60;

        // Formatear la duración como "HH:mm:ss"
        return String.format("%02d:%02d:%02d", hours, minutes, remainingSeconds);
    }
}
