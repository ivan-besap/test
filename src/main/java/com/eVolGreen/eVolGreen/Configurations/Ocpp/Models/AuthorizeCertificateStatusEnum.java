package com.eVolGreen.eVolGreen.Configurations.Ocpp.Models;

public enum AuthorizeCertificateStatusEnum {
    Accepted,
    SignatureError,
    CertificateExpired,
    CertificateRevoked,
    NoCertificateAvailable,
    CertChainError,
    ContractCancelled
}