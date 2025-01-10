//package com.eVolGreen.eVolGreen.Models.Account.Transaction;
//
//import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Core.Requests.Utils.MeterValue;
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import lombok.Setter;
//
//import java.math.BigDecimal;
//import java.time.LocalDateTime;
//import java.util.List;
//
//@Entity
//public class TransactionInfo {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private String id;
//    private String chargePointId;
//    private int transactionId;
//    private LocalDateTime startTime;
//
//    private BigDecimal lastMeterValue;
//    @Setter
//    private MeterValue[] meterValues;
//
//    public TransactionInfo() {
//    }
//
//    public TransactionInfo(String chargePointId, int transactionId, LocalDateTime startTime, MeterValue[] meterValues) {
//        this.chargePointId = chargePointId;
//        this.transactionId = transactionId;
//        this.startTime = startTime;
//        this.meterValues = meterValues;
//    }
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public BigDecimal getLastMeterValue() {
//        return lastMeterValue;
//    }
//
//    public void setLastMeterValue(BigDecimal lastMeterValue) {
//        this.lastMeterValue = lastMeterValue;
//    }
//
//    public void setMeterValues(MeterValue[] meterValues) {
//        this.meterValues = meterValues;
//    }
//
//    public String getChargePointId() {
//        return chargePointId;
//    }
//
//    public void setChargePointId(String chargePointId) {
//        this.chargePointId = chargePointId;
//    }
//
//    public int getTransactionId() {
//        return transactionId;
//    }
//
//    public void setTransactionId(int transactionId) {
//        this.transactionId = transactionId;
//    }
//
//    public LocalDateTime getStartTime() {
//        return startTime;
//    }
//
//    public void setStartTime(LocalDateTime startTime) {
//        this.startTime = startTime;
//    }
//
//    public MeterValue [] getMeterValues() {
//        return meterValues;
//    }
//
//    @Override
//    public String toString() {
//        return "TransactionInfo{" +
//                "chargePointId='" + chargePointId + '\'' +
//                ", transactionId=" + transactionId +
//                ", startTime=" + startTime +
//                ", meterValues=" + meterValues +
//                '}';
//    }
//}
