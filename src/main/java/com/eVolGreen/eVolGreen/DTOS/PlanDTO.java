package com.eVolGreen.eVolGreen.DTOS;

import com.eVolGreen.eVolGreen.Models.Plan;

import java.math.BigDecimal;

public class PlanDTO {
    private long id;
    private String name;
    private String description;
    private Integer days;
    private BigDecimal cost;

    public PlanDTO(Plan plan) {
        id = plan.getId();
        name = plan.getName();
        description = plan.getDescription();
        days = plan.getDays();
        cost = plan.getCost();
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
}
