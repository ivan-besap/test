package com.eVolGreen.eVolGreen.DTOS.AccountDTO.TransactionDTO;

import com.eVolGreen.eVolGreen.Models.Account.Transaction.TransactionInfo;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Core.Requests.Utils.SampledValue;
import jakarta.persistence.*;

import java.time.ZonedDateTime;
import java.util.Arrays;

public class TransactionInfoDTO {

    private Long id;

    private String chargePointId;

    private int transactionId;

    private ZonedDateTime startTime;

    private ZonedDateTime endTime;

    private Integer meterStart;

    private Integer meterStop;

    private Integer energyConsumed;

    @ElementCollection
    @CollectionTable(name = "transaction_data", joinColumns = @JoinColumn(name = "transaction_id"))
    @Column(name = "sample_value")
    private SampledValue[] transactionData;

    public TransactionInfoDTO() {
    }

    public TransactionInfoDTO(TransactionInfo transactionInfo) {
        this.id = transactionInfo.getId();
        this.chargePointId = transactionInfo.getChargePointId();
        this.transactionId = transactionInfo.getTransactionId();
        this.startTime = transactionInfo.getStartTime();
        this.endTime = transactionInfo.getEndTime();
        this.meterStart = transactionInfo.getMeterStart();
        this.meterStop = transactionInfo.getMeterStop();
        this.energyConsumed = transactionInfo.getEnergyConsumed();
        this.transactionData = transactionInfo.getTransactionData();
    }


    public Long getId() {
        return id;
    }

    public String getChargePointId() {
        return chargePointId;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public ZonedDateTime getStartTime() {
        return startTime;
    }

    public ZonedDateTime getEndTime() {
        return endTime;
    }

    public Integer getMeterStart() {
        return meterStart;
    }

    public Integer getMeterStop() {
        return meterStop;
    }

    public Integer getEnergyConsumed() {
        return energyConsumed;
    }

    public SampledValue[] getTransactionData() {
        return transactionData;
    }

    @Override
    public String toString() {
        return "TransactionInfoDTO{" +
                "id=" + id +
                ", chargePointId='" + chargePointId + '\'' +
                ", transactionId=" + transactionId +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", meterStart=" + meterStart +
                ", meterStop=" + meterStop +
                ", energyConsumed=" + energyConsumed +
                ", transactionData=" + Arrays.toString(transactionData) +
                '}';
    }
}
