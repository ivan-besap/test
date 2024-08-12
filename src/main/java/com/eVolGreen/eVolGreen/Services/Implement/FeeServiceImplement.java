package com.eVolGreen.eVolGreen.Services.Implement;

import com.eVolGreen.eVolGreen.DTOS.FeeDTO;
import com.eVolGreen.eVolGreen.Models.Fee;
import com.eVolGreen.eVolGreen.Repositories.FeeRepository;
import com.eVolGreen.eVolGreen.Services.FeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FeeServiceImplement implements FeeService {

    @Autowired
    private FeeRepository feeRepository;

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
    public List<FeeDTO> getFeesDTO() {
        return feeRepository.findAll()
                .stream()
                .map(FeeDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Fee updateFee(Long id, Fee feeDetails) {
        Optional<Fee> optionalFee = feeRepository.findById(id);
        if (optionalFee.isPresent()) {
            Fee fee = optionalFee.get();
            fee.setName(feeDetails.getName());
            fee.setStartingDate(feeDetails.getStartingDate());
            fee.setEndDate(feeDetails.getEndDate());
            fee.setStartTime(feeDetails.getStartTime());
            fee.setEndTime(feeDetails.getEndTime());
            fee.setDaysOfTheWeek(feeDetails.getDaysOfTheWeek());
            fee.setFeeValue(feeDetails.getFeeValue());
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
        fee.setName(feeDTO.getName());
        fee.setStartingDate(feeDTO.getStartingDate());
        fee.setEndDate(feeDTO.getEndDate());
        fee.setStartTime(feeDTO.getStartTime());
        fee.setEndTime(feeDTO.getEndTime());
        fee.setDaysOfTheWeek(feeDTO.getDaysOfTheWeek());
        fee.setFeeValue(feeDTO.getFeeValue());
        return fee;
    }

    @Override
    public String validateFeeDTO(FeeDTO feeDTO) {

        String message = " ";
        if (feeDTO.getName().isBlank()) {
            message = "El nombre de la tarifa no puede estar vacío";
            return message;
        }
        if (feeDTO.getStartingDate().isBefore(LocalDate.now())) {
            message = "La fecha de inicio de la tarifa no puede ser anterior a hoy";
            return message;
        }
        if (feeDTO.getEndDate().isBefore(feeDTO.getStartingDate())) {
            message = "La fecha de finalización de la tarifa no puede ser anterior a la fecha de inicio";
            return message;
        }
        if (feeDTO.getStartTime().isAfter(feeDTO.getEndTime())) {
            message = "La hora de inicio de la tarifa no puede ser posterior a la hora de finalización";
            return message;
        }
        if (feeDTO.getDaysOfTheWeek().isEmpty()) {
            message = "Los día de la semana de la tarifa no pueden estar vacíos";
            return message;
        }
        if (feeDTO.getFeeValue().compareTo(BigDecimal.ZERO) <= 0) {
            message = "El valor de la tarifa debe ser mayor que cero";
            return message;
        }
        return null;
    }
}
