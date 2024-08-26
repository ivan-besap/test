package com.eVolGreen.eVolGreen.Models.Ocpp.Models;

public enum AuthorizeCertificateStatusEnum {
    Accepted,
    SignatureError,
    CertificateExpired,
    CertificateRevoked,
    NoCertificateAvailable,
    CertChainError,
    ContractCancelled
}