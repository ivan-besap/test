package com.eVolGreen.eVolGreen.DTOS;

import com.eVolGreen.eVolGreen.Models.Company;
import com.eVolGreen.eVolGreen.Models.Connector;
import com.eVolGreen.eVolGreen.Models.Fee;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

public class FeeDTO {
    private long id;
    private String name;
    private LocalDate startingDate;
    private LocalDate endDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private Set<String> daysOfTheWeek;
    private BigDecimal feeValue;

    public FeeDTO (){ }

    public FeeDTO(Fee fee) {
        this.id = fee.getId();
        this.name = fee.getName();
        this.startingDate = fee.getStartingDate();
        this.endDate = fee.getEndDate();
        this.startTime = fee.getStartTime();
        this.endTime = fee.getEndTime();
        this.daysOfTheWeek = fee.getDaysOfTheWeek();
        this.feeValue = fee.getFeeValue();
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getStartingDate() {
        return startingDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public Set<String> getDaysOfTheWeek() {
        return daysOfTheWeek;
    }

    public BigDecimal getFeeValue() {
        return feeValue;
    }
}
