package com.eVolGreen.eVolGreen.DTOS;

import com.eVolGreen.eVolGreen.Models.Plan;

import java.math.BigDecimal;

public class PlanDTO {
    private long id;
    private String name;
    private String description;
    private Integer days;
    private BigDecimal cost;
    private BigDecimal totalKWh;
    private BigDecimal totalCost;

    public PlanDTO(Plan plan) {
        id = plan.getId();
        name = plan.getName();
        description = plan.getDescription();
        days = plan.getDays();
        cost = plan.getCost();
        totalKWh = plan.getTotalKWh();
        totalCost = plan.getTotalKWh()  ;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Integer getDays() {
        return days;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public BigDecimal getTotalKWh() {
        return totalKWh;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }
}
