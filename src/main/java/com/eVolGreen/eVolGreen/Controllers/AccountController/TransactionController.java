package com.eVolGreen.eVolGreen.Controllers.AccountController;

import com.eVolGreen.eVolGreen.Repositories.TransactionRepository;
import com.eVolGreen.eVolGreen.Services.AccountService.AccountService;
import com.eVolGreen.eVolGreen.Services.AccountService.TransactionService;
import com.eVolGreen.eVolGreen.Services.DUserService.ClientUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private ClientUserService clientUserService;
    @Autowired
    private AccountService accountService;

//    @GetMapping("/transactions")
//    public List<TransactionDTO> getTransactions() {
//        return transactionService.getTransactionsDTO();
//    }
//
//    @GetMapping("/transactions/{id}")
//    public TransactionDTO getTransaction(@PathVariable Long id) {
//        return new TransactionDTO(transactionService.findById(id));
//    }
//
//    @PostMapping("/clients/current/transactions/start")
//    public ResponseEntity<Object> startCharging(Authentication authentication,
//                                                @RequestParam Long accountId,
//                                                @RequestParam Long chargingStationId) {
//        Client client = clientService.findByEmail(authentication.getName());
//        if (client == null) {
//            return ResponseEntity.status(404).body("Client not found");
//        }
//
//        Account account = accountService.findById(accountId);
//        if (account == null || !client.getAccounts().contains(account)) {
//            return ResponseEntity.status(403).body("Account does not belong to the client");
//        }
//
//        Transaction transaction = transactionService.startCharging(account, chargingStationId);
//        return ResponseEntity.status(201).body(transaction);
//    }
//
//    @PostMapping("/clients/current/transactions/stop")
//    public ResponseEntity<Object> stopCharging(Authentication authentication,
//                                               @RequestParam Long transactionId) {
//        Client client = clientService.findByEmail(authentication.getName());
//        if (client == null) {
//            return ResponseEntity.status(404).body("Client not found");
//        }
//
//        Transaction transaction = transactionService.stopCharging(transactionId);
//        if (transaction == null) {
//            return ResponseEntity.status(404).body("Transaction not found");
//        }
//
//        return ResponseEntity.status(200).body(transaction);
//    }
}
