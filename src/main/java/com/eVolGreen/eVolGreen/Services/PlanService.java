package com.eVolGreen.eVolGreen.Services;

import com.eVolGreen.eVolGreen.DTOS.PlanDTO;
import com.eVolGreen.eVolGreen.Models.Plan;

import java.util.List;

public interface PlanService {

    List<PlanDTO> getPlansDTO();

    void savePlan(Plan plan);

    Plan findById(Long planId);
}
