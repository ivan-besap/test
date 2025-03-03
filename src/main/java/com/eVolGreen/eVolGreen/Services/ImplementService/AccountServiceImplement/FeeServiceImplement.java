package com.eVolGreen.eVolGreen.Services.ImplementService.AccountServiceImplement;

import com.eVolGreen.eVolGreen.DTOS.AccountDTO.FeeDTO.FeeDTO;
import com.eVolGreen.eVolGreen.Models.Account.Account;
import com.eVolGreen.eVolGreen.Models.Account.Empresa;
import com.eVolGreen.eVolGreen.Models.Account.Fee.Fee;
import com.eVolGreen.eVolGreen.Repositories.FeeRepository;
import com.eVolGreen.eVolGreen.Services.AccountService.AccountService;
import com.eVolGreen.eVolGreen.Services.AccountService.FeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FeeServiceImplement implements FeeService {

    @Autowired
    private FeeRepository feeRepository;

    @Autowired
    private AccountService accountService;

    @Override
    @Transactional
    public Fee createFee(Fee fee) {
        return feeRepository.save(fee);
    }

    @Override
    public FeeDTO getFeeDTOById(Long id) {
        Optional<Fee> fee = feeRepository.findById(id);
        return fee.map(FeeDTO::new).orElse(null);
    }

    @Override
    public Fee findById(Long id) {
        return feeRepository.findById(id).orElse(null);
    }

    @Override
    public Optional<Fee> findFeeById(Long id) {
        return feeRepository.findById(id);
    }

    @Override
    public List<FeeDTO> getFeesDTO(String email) {
        Optional<Account> account = accountService.findByEmail(email);
        if (account.isPresent()) {
            Empresa empresa = account.get().getEmpresa();
            if (empresa != null) {
                return feeRepository.findByEmpresa(empresa)
                        .stream()
                        .filter(Fee::getActivo)
                        .map(FeeDTO::new)
                        .collect(Collectors.toList());
            }
        }
        return Collections.emptyList();
    }

    @Override
    @Transactional
    public Fee updateFee(Long id, Fee feeDetails) {
        Optional<Fee> optionalFee = feeRepository.findById(id);
        if (optionalFee.isPresent()) {
            Fee fee = optionalFee.get();
            fee.setNombreTarifa(feeDetails.getNombreTarifa());
            fee.setFechaInicio(feeDetails.getFechaInicio());
            fee.setFechaFin(feeDetails.getFechaFin());
            fee.setDiasDeLaSemana(feeDetails.getDiasDeLaSemana());
            fee.setPrecioTarifa(feeDetails.getPrecioTarifa());
            fee.setEmpresa(feeDetails.getEmpresa());
            return feeRepository.save(fee);
        }
        return null;
    }

    @Override
    @Transactional
    public void deleteFee(Long id) {
        Optional<Fee> optionalFee = feeRepository.findById(id);
        optionalFee.ifPresent(feeRepository::delete);
    }

    @Override
    public Fee convertToEntity(FeeDTO feeDTO) {
        Fee fee = new Fee();
        fee.setNombreTarifa(feeDTO.getNombreTarifa());
        fee.setFechaInicio(ZonedDateTime.from(feeDTO.getFechaInicio()));
        fee.setFechaFin(ZonedDateTime.from(feeDTO.getFechaFin()));
        fee.setDiasDeLaSemana(feeDTO.getDiasDeLaSemana());
        fee.setPrecioTarifa(feeDTO.getPrecioTarifa());
        return fee;
    }

    @Override
    public String validateFeeDTO(FeeDTO feeDTO) {

        String message = " ";
        if (feeDTO.getNombreTarifa().isBlank()) {
            message = "El nombre de la tarifa no puede estar vacío";
            return message;
        }
        if (feeDTO.getFechaInicio().isBefore(LocalDate.now())) {
            message = "La fecha de inicio de la tarifa no puede ser anterior a hoy";
            return message;
        }
        if (feeDTO.getFechaFin().isBefore(feeDTO.getFechaInicio())) {
            message = "La fecha de finalización de la tarifa no puede ser anterior a la fecha de inicio";
            return message;
        }
        if (feeDTO.getHoraInicio().isAfter(feeDTO.getHoraFin())) {
            message = "La hora de inicio de la tarifa no puede ser posterior a la hora de finalización";
            return message;
        }
        if (feeDTO.getDiasDeLaSemana().isEmpty()) {
            message = "Los día de la semana de la tarifa no pueden estar vacíos";
            return message;
        }
        if (feeDTO.getPrecioTarifa().compareTo(BigDecimal.ZERO) <= 0) {
            message = "El valor de la tarifa debe ser mayor que cero";
            return message;
        }
        return null;
    }

    @Transactional
    public Fee saveFee(Fee nuevaTarifa) {
        return feeRepository.save(nuevaTarifa);
    }


}
