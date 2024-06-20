package com.eVolGreen.eVolGreen.Controllers;

import com.eVolGreen.eVolGreen.DTOS.ChargerDTO;
import com.eVolGreen.eVolGreen.Models.Company;
import com.eVolGreen.eVolGreen.Repositories.ChargerRepository;
import com.eVolGreen.eVolGreen.Services.ChargerService;
import com.eVolGreen.eVolGreen.Services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ChargerController {
    @Autowired
    private ChargerService chargerService;
    @Autowired
    private ChargerRepository chargerRepository;
    @Autowired
    private CompanyService companyService;

    @GetMapping("/chargers")
    public List<ChargerDTO> getChargers() {
        return chargerService.getChargersDTO();
    }

//    @GetMapping("/chargers/{id}")
//    public ChargerDTO getCharger(@PathVariable Long id) {
//        return chargerService.getChargerDTO(id);
//    }
//
//    @GetMapping("/chargers")
//    public List<ChargerDTO> getListChargersByStation(Authentication authentication
//                                                 ) {
//        Company company = companyService.findByEmailCompany(authentication.getName());
//        if (company == null) {
//            return null;
//        }
//        return chargerRepository.findByCompany(company).stream().map(ChargerDTO::new).collect(Collectors.toList());
//    }

}
