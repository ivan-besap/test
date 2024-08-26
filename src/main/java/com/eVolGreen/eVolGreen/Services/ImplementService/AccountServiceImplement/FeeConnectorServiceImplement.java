package com.eVolGreen.eVolGreen.Services.ImplementService.AccountServiceImplement;

import com.eVolGreen.eVolGreen.Models.Account.Fee.FeeConnector;
import com.eVolGreen.eVolGreen.Repositories.FeeConnectorRepository;
import com.eVolGreen.eVolGreen.Services.AccountService.FeeConnectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeeConnectorServiceImplement implements FeeConnectorService {

    @Autowired
    private FeeConnectorRepository feeConnectorRepository;


    @Override
    public void save(FeeConnector feeConnector) {
        feeConnectorRepository.save(feeConnector);
    }

    @Override
    public FeeConnector findById(Long id) {
        return feeConnectorRepository.findById(id).orElse(null);
    }


}
