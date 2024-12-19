package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.ChargingProfile.Confirmation.Enums;

/**
 * Enumera los posibles estados para determinar si la estación de carga puede procesar la solicitud
 * de obtención de perfiles de carga y enviar mensajes ReportChargingProfilesRequest.
 */
public enum GetChargingProfileStatusEnum {

    /**
     * Indica que la estación de carga acepta la solicitud y enviará mensajes
     * ReportChargingProfilesRequest con los perfiles de carga solicitados.
     */
    Accepted,

    /**
     * Indica que la estación de carga no tiene perfiles de carga que coincidan con los criterios
     * solicitados y no enviará mensajes ReportChargingProfilesRequest.
     */
    NoProfiles
}
