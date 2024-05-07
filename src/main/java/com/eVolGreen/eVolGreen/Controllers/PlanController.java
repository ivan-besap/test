package com.eVolGreen.eVolGreen.Controllers;

import com.eVolGreen.eVolGreen.DTOS.PlanDTO;
import com.eVolGreen.eVolGreen.Repositories.PlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class PlanController {
    @Autowired
    private PlanRepository planRepository;

    @GetMapping("/plans")
    public List<PlanDTO> getPlans() {
        return planRepository.findAll()
                .stream()
                .map(PlanDTO::new)
                .collect(Collectors.toList());
    }
}
