package com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Confirmations;

/**
 * Enum para representar el estado de la autorización del idTag.
 */
public enum AuthorizationStatus {

    Accepted,
    Blocked,
    Expired,
    Invalid,
    ConcurrentTx

}