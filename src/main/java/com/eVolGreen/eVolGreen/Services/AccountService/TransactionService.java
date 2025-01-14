package com.eVolGreen.eVolGreen.Services.AccountService;

import com.eVolGreen.eVolGreen.DTOS.AccountDTO.TransactionDTO.TransactionInfoDTO;

import java.util.List;

public interface TransactionService {

    List<TransactionInfoDTO> getTransactionsInfoDTO(String email);

//    List<TransactionDTO> getTransactionsDTO();
//
//    void saveTransaction(Transaction transaction);
//
//    Transaction startCharging(Account account, Long chargingStationId);
//
//    Transaction stopCharging(Long transactionId);
//
//    Transaction findById(Long id);
}
