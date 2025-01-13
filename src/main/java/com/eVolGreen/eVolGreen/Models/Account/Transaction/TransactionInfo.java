package com.eVolGreen.eVolGreen.Models.Account.Transaction;

import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Core.Requests.Utils.MeterValue;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Core.Requests.Utils.SampledValue;
import jakarta.persistence.*;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

@Entity
public class TransactionInfo {

    @Id
    @GenericGenerator(name = "native", strategy = "native")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
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

    public TransactionInfo() {
    }

    public TransactionInfo(String chargePointId, int transactionId, ZonedDateTime startTime, ZonedDateTime endTime, Integer meterStart, Integer meterStop, Integer energyConsumed, SampledValue[] transactionData) {
        this.chargePointId = chargePointId;
        this.transactionId = transactionId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.meterStart = meterStart;
        this.meterStop = meterStop;
        this.energyConsumed = energyConsumed;
        this.transactionData = transactionData;
    }

    public SampledValue[] getTransactionData() {
        return transactionData;
    }

    public void setTransactionData(SampledValue[] transactionData) {
        this.transactionData = transactionData;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChargePointId() {
        return chargePointId;
    }

    public void setChargePointId(String chargePointId) {
        this.chargePointId = chargePointId;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public ZonedDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(ZonedDateTime startTime) {
        this.startTime = startTime;
    }

    public ZonedDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(ZonedDateTime endTime) {
        this.endTime = endTime;
    }

    public Integer getMeterStart() {
        return meterStart;
    }

    public void setMeterStart(Integer meterStart) {
        this.meterStart = meterStart;
    }

    public Integer getMeterStop() {
        return meterStop;
    }

    public void setMeterStop(Integer meterStop) {
        this.meterStop = meterStop;
    }

    public Integer getEnergyConsumed() {
        return energyConsumed;
    }

    public void setEnergyConsumed(Integer energyConsumed) {
        this.energyConsumed = energyConsumed;
    }

    @Override
    public String toString() {
        return "TransactionInfo{" +
                "id=" + id +
                ", chargePointId='" + chargePointId + '\'' +
                ", transactionId=" + transactionId +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", meterStart=" + meterStart +
                ", meterStop=" + meterStop +
                ", energyConsumed=" + energyConsumed +
                ", transactionData=" + transactionData +
                '}';
    }
}
