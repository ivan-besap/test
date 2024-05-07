package com.eVolGreen.eVolGreen.DTOS;

import com.eVolGreen.eVolGreen.Models.Transaction;
import com.eVolGreen.eVolGreen.Models.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionDTO {
    private long id;
    private TransactionType type;
    private String description;
    private LocalDateTime date;
    private LocalDateTime loadingStartDate;
    private LocalDateTime loadingEndDate;
    private BigDecimal suppliedEnergy;
    private Integer cost;

    public TransactionDTO(Transaction transaction) {
        id = transaction.getId();
        type = transaction.getType();
        description = transaction.getDescription();
        date = transaction.getDate();
        loadingStartDate = transaction.getLoadingStartDate();
        loadingEndDate = transaction.getLoadingEndDate();
        suppliedEnergy = transaction.getSuppliedEnergy();
        cost = transaction.getCost();
    }

    public long getId() {
        return id;
    }

    public TransactionType getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public LocalDateTime getLoadingStartDate() {
        return loadingStartDate;
    }

    public LocalDateTime getLoadingEndDate() {
        return loadingEndDate;
    }

    public BigDecimal getSuppliedEnergy() {
        return suppliedEnergy;
    }

    public Integer getCost() {
        return cost;
    }
}
