package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Core.Requests.Enums;

import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Core.Requests.Utils.ChargingProfile;

/**
 * Valores aceptados para definir el tipo de perfil de carga.
 * Estos valores son usados en {@link ChargingProfile}.
 */
public enum ChargingProfileKindType {

    /**
     * Perfil que establece límites absolutos de carga en momentos específicos.
     */
    Absolute,

    /**
     * Perfil que se repite de manera periódica, por ejemplo, todos los días o semanas.
     */
    Recurring,

    /**
     * Perfil que establece límites relativos al inicio de la carga.
     */
    Relative
}
