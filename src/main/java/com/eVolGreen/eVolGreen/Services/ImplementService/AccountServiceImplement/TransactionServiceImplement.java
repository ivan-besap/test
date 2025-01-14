package com.eVolGreen.eVolGreen.Services.ImplementService.AccountServiceImplement;

import com.eVolGreen.eVolGreen.DTOS.AccountDTO.TransactionDTO.TransactionInfoDTO;
import com.eVolGreen.eVolGreen.Models.Account.Account;
import com.eVolGreen.eVolGreen.Models.Account.Empresa;
import com.eVolGreen.eVolGreen.Models.Account.Transaction.TransactionInfo;
import com.eVolGreen.eVolGreen.Repositories.ChargingStationRepository;
import com.eVolGreen.eVolGreen.Repositories.TransactionInfoRepository;
import com.eVolGreen.eVolGreen.Repositories.TransactionRepository;
import com.eVolGreen.eVolGreen.Services.AccountService.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImplement implements TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private ChargingStationRepository chargingStationRepository;
    @Autowired
    private TransactionInfoRepository transactionInfoRepository;
    @Autowired
    private AccountServiceImplement accountService;

    @GetMapping("/transactionsInfo")
    public List<TransactionInfoDTO> getTransactionsInfoDTO(String email) {
        Optional<Account> account = accountService.findByEmail(email);
        if (account.isPresent()) {
            Empresa empresa = account.get().getEmpresa();
            if (empresa != null) {
                // Mapea directamente utilizando el constructor del DTO
                return transactionInfoRepository.findByEmpresa(empresa)
                        .stream()
                        .map(TransactionInfoDTO::new) // Usa el constructor directamente
                        .collect(Collectors.toList());
            }
        }
        return Collections.emptyList();
    }

//
//    @Autowired
//    private CompanyUserService companyUserService;

//    @Override
//    public List<TransactionDTO> getTransactionsDTO() {
//        return transactionRepository.findAll()
//                .stream()
//                .map(TransactionDTO::new)
//                .collect(Collectors.toList());
//    }
//    @Override
//    public void saveTransaction(Transaction transaction) {
//        transactionRepository.save(transaction);
//    }
//
//    @Override
//    public Transaction startCharging(Account account, Long chargingStationId) {
//        ChargingStation chargingStation = chargingStationRepository.findById(chargingStationId).orElse(null);
//        if (chargingStation == null) {
//            throw new RuntimeException("Charging station not found");
//        }
//
//        Transaction transaction = new Transaction();
//        transaction.setType(TransactionType.CREDIT);
//        transaction.setDescription("Charging started");
//        transaction.setDate(LocalDateTime.now());
//        transaction.setLoadingStartDate(LocalDateTime.now());
//        transaction.setAccount(account);
//        transaction.setChargingStation(chargingStation);
//        transactionRepository.save(transaction);
//        return transaction;
//    }
//
//    @Override
//    public Transaction stopCharging(Long transactionId) {
//        Transaction transaction = transactionRepository.findById(transactionId).orElse(null);
//        if (transaction == null) {
//            throw new RuntimeException("Transaction not found");
//        }
//
//        transaction.setLoadingEndDate(LocalDateTime.now());
//        transaction.setDescription("Charging stopped");
//
//        // Calculate the charging duration and the supplied energy
//        long duration = java.time.Duration.between(transaction.getLoadingStartDate(), transaction.getLoadingEndDate()).toMinutes();
//        BigDecimal suppliedEnergy = calculateSuppliedEnergy(duration); // This method should be implemented
//
//        transaction.setSuppliedEnergy(suppliedEnergy);
//        updateAccountPlan(transaction.getAccount(), suppliedEnergy);
//        transactionRepository.save(transaction);
//        return transaction;
//    }
//
//    @Override
//    public Transaction findById(Long id) {
//        return transactionRepository.findById(id).orElse(null);
//    }
//
//    private BigDecimal calculateSuppliedEnergy(long duration) {
//        // Implement the logic to calculate supplied energy based on the duration
//        // For example, let's assume 1 kWh per minute
//        return BigDecimal.valueOf(duration);
//    }
//
//    private void updateAccountPlan(Account account, BigDecimal suppliedEnergy) {
//        Company company = account.getCompany();
//        if (company != null) {
//            List<Fee> fees = companyService.getCompanyFeesById(company.getId());
//            Fee fee = fees.stream().findFirst().orElse(null);
//            if (fee != null) {
//                BigDecimal remainingEnergy = fee.getAvailableKWh().subtract(suppliedEnergy);
//                fee.setAvailableKWh(remainingEnergy);
//                accountService.saveAccount(account);
//            }
//        }
//    }


}
