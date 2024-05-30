package com.eVolGreen.eVolGreen.Services.Implement;


import com.eVolGreen.eVolGreen.DTOS.PlanDTO;
import com.eVolGreen.eVolGreen.Models.Plan;
import com.eVolGreen.eVolGreen.Repositories.PlanRepository;
import com.eVolGreen.eVolGreen.Services.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;



@Service
public class PlanServiceImplement implements PlanService {
    @Autowired
    private PlanRepository planRepository;

    @Override
    public List<PlanDTO> getPlansDTO() {
        return planRepository.findAll()
                .stream()
                .map(PlanDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public void savePlan(Plan plan) {
        planRepository.save(plan);
    }

    @Override
    public Plan findById(Long planId) {
        return planRepository.findById(planId).orElse(null);
    }


}
