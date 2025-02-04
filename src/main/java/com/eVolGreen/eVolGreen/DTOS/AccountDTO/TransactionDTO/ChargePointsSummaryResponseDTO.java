package com.eVolGreen.eVolGreen.DTOS.AccountDTO.TransactionDTO;

import com.eVolGreen.eVolGreen.Models.Account.Transaction.TransactionInfo;
import java.util.Collections;
import java.util.List;

public class ChargePointsSummaryResponseDTO {

    private Long empresaId;

    private List<String> chargePointId;

    private Integer totalEnergyConsumed;

    public ChargePointsSummaryResponseDTO() {
    }

    public ChargePointsSummaryResponseDTO(Long empresaId, List<String> chargePointIds, Integer totalEnergyConsumed) {
        this.empresaId = empresaId;
        this.chargePointId = chargePointIds;
        this.totalEnergyConsumed = totalEnergyConsumed;
    }

    public Long getEmpresaId() {
        return empresaId;
    }

    public List<String> getChargePointId() {
        return chargePointId;
    }

    public Integer getTotalEnergyConsumed() {
        return totalEnergyConsumed;
    }
}
