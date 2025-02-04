package com.eVolGreen.eVolGreen.DTOS.AccountDTO.TransactionDTO;

public interface ActiveTransactionProjection {

    String getChargePointId();
    int getConnectorId();
    int getTransactionId();
    Integer getEnergyConsumed();

}
