package com.eVolGreen.eVolGreen.Services.AccountService;

import com.eVolGreen.eVolGreen.Models.Account.Fee.FeeConnector;

public interface FeeConnectorService {
    void save(FeeConnector feeConnector);

    FeeConnector findById(Long id);
}
