package com.eVolGreen.eVolGreen.Services;

import com.eVolGreen.eVolGreen.DTOS.FeeDTO;
import com.eVolGreen.eVolGreen.Models.Fee;

import java.util.List;
import java.util.Optional;

public interface FeeService {

    Fee createFee(Fee fee);

    FeeDTO getFeeDTOById(Long id);

    Fee findById(Long id);

    Optional<Fee> findFeeById(Long id);

    List<FeeDTO> getFeesDTO();

    Fee updateFee(Long id, Fee feeDetails);

    void deleteFee(Long id);

    Fee convertToEntity(FeeDTO feeDTO);

    String validateFeeDTO(FeeDTO feeDTO);
}
