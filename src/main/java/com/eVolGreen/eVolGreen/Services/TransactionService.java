package com.eVolGreen.eVolGreen.Services;

import com.eVolGreen.eVolGreen.DTOS.TransactionDTO;
import com.eVolGreen.eVolGreen.Models.Account;
import com.eVolGreen.eVolGreen.Models.Transaction;

import java.util.List;

public interface TransactionService {

    List<TransactionDTO> getTransactionsDTO();

    void saveTransaction(Transaction transaction);

    Transaction startCharging(Account account, Long chargingStationId);

    Transaction stopCharging(Long transactionId);
}
