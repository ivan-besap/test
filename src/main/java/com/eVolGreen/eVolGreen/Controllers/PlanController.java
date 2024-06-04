package com.eVolGreen.eVolGreen.Controllers;

import com.eVolGreen.eVolGreen.DTOS.AccountDTO;
import com.eVolGreen.eVolGreen.DTOS.PlanDTO;
import com.eVolGreen.eVolGreen.Models.Account;
import com.eVolGreen.eVolGreen.Models.Client;
import com.eVolGreen.eVolGreen.Models.Company;
import com.eVolGreen.eVolGreen.Models.Plan;
import com.eVolGreen.eVolGreen.Repositories.AccountRepository;
import com.eVolGreen.eVolGreen.Repositories.PlanRepository;
import com.eVolGreen.eVolGreen.Services.AccountService;
import com.eVolGreen.eVolGreen.Services.ClientService;
import com.eVolGreen.eVolGreen.Services.CompanyService;
import com.eVolGreen.eVolGreen.Services.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class PlanController {
    @Autowired
    private PlanService planService;
    @Autowired
    private PlanRepository planRepository;
    @Autowired
    private ClientService clientService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private CompanyService  companyService;

    @GetMapping("/plans")
    public List<PlanDTO> getPlans() {
        return planService.getPlansDTO();
    }


    @GetMapping("/plans/{id}")
    public PlanDTO getPlan(@PathVariable Long id) {

        return new PlanDTO(planService.findById(id));
    }

    @PostMapping("/clients/current/plans")
    public ResponseEntity<Object> registerPlan(Authentication authentication,
                                               @RequestParam Long planId,
                                               @RequestParam Long accountId) {

        Client client = clientService.findByEmail(authentication.getName());
        if (client == null) {
            return ResponseEntity.status(404).body("Client not found");
        }

        Plan plan = planService.findById(planId);
        if (plan == null) {
            return ResponseEntity.status(404).body("Plan not found");
        }

        Account account = accountService.findById(accountId);
        if (account == null) {
            return ResponseEntity.status(404).body("Account not found");
        }

        if (!client.getAccounts().contains(account)) {
            return ResponseEntity.status(403).body("Account does not belong to the client");
        }

        account.addPlan(plan);
        accountService.saveAccount(account);

        return ResponseEntity.status(201).body("Plan registered successfully");
    }

    @PostMapping ("/company/current/plans")
    public ResponseEntity<Object> newPlan (Authentication authentication,
                                               @RequestBody Plan newPlan) {
        Company company = companyService.findByEmailCompany(authentication.getName());
        String message = "";

        if (company == null) {
            message = "Company not found";
            return ResponseEntity.status(404).body(message);
        }

        if (newPlan.getName().isBlank()) {
            message = "Plan name cannot be empty";
            return ResponseEntity.status(400).body(message);
        }
        if(newPlan.getDescription().isBlank()) {
            message = "Plan description cannot be empty";
            return ResponseEntity.status(400).body(message);
        }
        if (newPlan.getDays() == null) {
            message = "Plan days cannot be empty";
            return ResponseEntity.status(400).body(message);
        }
        if (newPlan.getCost() == null) {
            message = "Plan cost cannot be empty";
            return ResponseEntity.status(400).body(message);
        }
        if (newPlan.getTotalKWh() == null) {
            message = "Plan total kWh cannot be empty";
            return ResponseEntity.status(400).body(message);
        }
        if (newPlan.getAvailableKWh() == null) {
            message = "Plan available kWh cannot be empty";
            return ResponseEntity.status(400).body(message);
        }

        Plan plan = new Plan(
                newPlan.getName(),
                newPlan.getDescription(),
                newPlan.getDays(),
                newPlan.getCost(),
                newPlan.getTotalKWh(),
                newPlan.getAvailableKWh()
        );

        planService.savePlan(plan);
        company.getAccounts().forEach(account -> account.addPlan(plan));
        companyService.saveCompany(company);

        message = "Plan created successfully";
        return ResponseEntity.status(201).body(message);
    }
}
