package com.eVolGreen.eVolGreen.DTOS.AccountDTO.TransactionDTO;

import com.eVolGreen.eVolGreen.Models.Account.Transaction.TransactionInfo;

public class TotalEnergyResponseDTO {

    private Long empresaId;

    private String chargePointId;

    private int totalEnergyConsumed;

    public TotalEnergyResponseDTO() {
    }

    public TotalEnergyResponseDTO(TransactionInfo transactionInfo) {
        empresaId = transactionInfo.getEmpresa().getId();
        chargePointId = transactionInfo.getChargePointId();
        totalEnergyConsumed = transactionInfo.getEnergyConsumed();
    }

    public TotalEnergyResponseDTO(Long empresaId, String key, Integer value) {
        this.empresaId = empresaId;
        this.chargePointId = key;
        this.totalEnergyConsumed = value;
    }

    public Long getEmpresaId() {
        return empresaId;
    }

    public String getChargePointId() {
        return chargePointId;
    }

    public int getTotalEnergyConsumed() {
        return totalEnergyConsumed;
    }
}
