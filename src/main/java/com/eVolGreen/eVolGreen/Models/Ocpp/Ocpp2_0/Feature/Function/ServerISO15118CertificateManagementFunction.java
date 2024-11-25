package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.*;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.EnergyAndCostManagement.Requests.Utils.CertificateHashData;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Security.Requests.DeleteCertificateRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Security.Requests.Get15118EVCertificateRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Security.Requests.GetCertificateStatusRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Security.Requests.GetInstalledCertificateIdsRequest;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Clase que proporciona creadores de solicitudes del servidor y manejadores para el bloque funcional
 * de gestión de certificados ISO 15118.
 */
public class ServerISO15118CertificateManagementFunction implements Function {

    private final ServerISO15118CertificateManagementEventHandler eventHandler;
    private final ArrayList<FunctionFeature> features;

    /**
     * Constructor que inicializa el manejador de eventos y las características del bloque funcional.
     *
     * @param eventHandler Implementación de {@link ServerISO15118CertificateManagementEventHandler}
     *                     para manejar eventos relacionados con ISO 15118.
     */
    public ServerISO15118CertificateManagementFunction(
            ServerISO15118CertificateManagementEventHandler eventHandler) {
        this.eventHandler = eventHandler;
        features = new ArrayList<>();
        features.add(new DeleteCertificateFeature(null));
        features.add(new Get15118EVCertificateFeature(this));
        features.add(new GetCertificateStatusFeature(this));
        features.add(new GetInstalledCertificateIdsFeature(null));
    }

    @Override
    public FunctionFeature[] getFeatureList() {
        return features.toArray(new FunctionFeature[0]);
    }

    @Override
    public Confirmation handleRequest(UUID sessionIndex, Request request) {
        if (request instanceof Get15118EVCertificateRequest) {
            return eventHandler.handleGet15118EVCertificateRequest(
                    sessionIndex, (Get15118EVCertificateRequest) request);
        } else if (request instanceof GetCertificateStatusRequest) {
            return eventHandler.handleGetCertificateStatusRequest(
                    sessionIndex, (GetCertificateStatusRequest) request);
        }
        return null;
    }

    /**
     * Crea una solicitud de tipo {@link DeleteCertificateRequest}.
     *
     * @param certificateHashData Datos hash del certificado que debe eliminarse.
     * @return Una instancia de {@link DeleteCertificateRequest}.
     */
    public DeleteCertificateRequest createDeleteCertificateRequest(
            CertificateHashData certificateHashData) {
        return new DeleteCertificateRequest(certificateHashData);
    }

    /**
     * Crea una solicitud de tipo {@link GetInstalledCertificateIdsRequest}.
     *
     * @return Una instancia de {@link GetInstalledCertificateIdsRequest}.
     */
    public GetInstalledCertificateIdsRequest createGetInstalledCertificateIdsRequest() {
        return new GetInstalledCertificateIdsRequest();
    }
}
