package com.eVolGreen.eVolGreen.DTOS.AccountDTO.TransactionDTO;

import com.eVolGreen.eVolGreen.Models.Account.Transaction.TransactionInfo;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class ChargePointsSummaryResponseDTO {

    private Long empresaId;
    private List<String> chargePointIds;
    private Integer totalEnergyConsumed;
    private Integer dailyEnergyConsumed;
    private Map<String, Integer> monthlyEnergyConsumed;
    public ChargePointsSummaryResponseDTO() {
    }

    public ChargePointsSummaryResponseDTO(Long empresaId, List<String> chargePointIds,
                                          Integer totalEnergyConsumed, Integer dailyEnergyConsumed,
                                          Map<String, Integer> monthlyEnergyConsumed) {
        this.empresaId = empresaId;
        this.chargePointIds = chargePointIds;
        this.totalEnergyConsumed = totalEnergyConsumed;
        this.dailyEnergyConsumed = dailyEnergyConsumed;
        this.monthlyEnergyConsumed = monthlyEnergyConsumed;
    }

    public Long getEmpresaId() {
        return empresaId;
    }

    public List<String> getChargePointIds() {  // 👈 Se corrigió el nombre (antes era getChargePointId)
        return chargePointIds;
    }

    public Integer getTotalEnergyConsumed() {
        return totalEnergyConsumed;
    }

    public Integer getDailyEnergyConsumed() {
        return dailyEnergyConsumed;
    }

    public Map<String, Integer> getMonthlyEnergyConsumed() {
        return monthlyEnergyConsumed;
    }

    // ✅ SETTERS
    public void setEmpresaId(Long empresaId) {
        this.empresaId = empresaId;
    }

    public void setChargePointIds(List<String> chargePointIds) {
        this.chargePointIds = chargePointIds;
    }

    public void setTotalEnergyConsumed(Integer totalEnergyConsumed) {
        this.totalEnergyConsumed = totalEnergyConsumed;
    }

    public void setDailyEnergyConsumed(Integer dailyEnergyConsumed) {
        this.dailyEnergyConsumed = dailyEnergyConsumed;
    }

    public void setMonthlyEnergyConsumed(Map<String, Integer> monthlyEnergyConsumed) {
        this.monthlyEnergyConsumed = monthlyEnergyConsumed;
    }

    // ✅ Método toString() para depuración
    @Override
    public String toString() {
        return "ChargePointsSummaryResponseDTO{" +
                "empresaId=" + empresaId +
                ", chargePointIds=" + chargePointIds +
                ", totalEnergyConsumed=" + totalEnergyConsumed +
                ", dailyEnergyConsumed=" + dailyEnergyConsumed +
                ", monthlyEnergyConsumed=" + monthlyEnergyConsumed +
                '}';
    }
}
