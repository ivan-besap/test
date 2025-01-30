package com.eVolGreen.eVolGreen.DTOS.AccountDTO.TransactionDTO;

public interface ChargePointEnergyForMonthsProjection {
    Long getEmpresaId();
    String getDataTimeForMonths();
    Integer getTotalEnergyConsumed();
}
