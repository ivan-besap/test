package com.eVolGreen.eVolGreen.Repositories;

import com.eVolGreen.eVolGreen.DTOS.AccountDTO.TransactionDTO.TransactionDTO;
import com.eVolGreen.eVolGreen.Models.Account.Transaction.Transaction;
import com.eVolGreen.eVolGreen.Models.ChargingStation.Reporte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("SELECT t FROM Reporte t WHERE t.transactionId = :transactionId ")
    Optional<Transaction> findByTransactionId(Integer transactionId);

}
