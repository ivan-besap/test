package com.eVolGreen.eVolGreen.Services.Implement;

import com.eVolGreen.eVolGreen.DTOS.TransactionDTO;
import com.eVolGreen.eVolGreen.Models.Transaction;
import com.eVolGreen.eVolGreen.Repositories.TransactionRepository;
import com.eVolGreen.eVolGreen.Services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImplement implements TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public List<TransactionDTO> getTransactionsDTO() {
        return transactionRepository.findAll()
                .stream()
                .map(TransactionDTO::new)
                .collect(Collectors.toList());
    }
    @Override
    public void saveTransaction(Transaction transaction) {
        transactionRepository.save(transaction);
    }
}
