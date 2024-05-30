package com.eVolGreen.eVolGreen.Controllers;

import com.eVolGreen.eVolGreen.DTOS.AccountDTO;
import com.eVolGreen.eVolGreen.Models.Account;
import com.eVolGreen.eVolGreen.Models.Client;
import com.eVolGreen.eVolGreen.Models.Email;
import com.eVolGreen.eVolGreen.Models.TypeAccounts;
import com.eVolGreen.eVolGreen.Repositories.AccountRepository;
import com.eVolGreen.eVolGreen.Services.AccountService;
import com.eVolGreen.eVolGreen.Services.ClientService;
import com.eVolGreen.eVolGreen.Services.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class AccountController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("/accounts")
    public List<AccountDTO> getAccounts() {
        return accountService.getAccountsDTO();
    }
    @GetMapping("/accounts/{id}")
    public AccountDTO getAccount(@PathVariable Long id){
        return accountService.getAccountDTOCurrent(id);
    }
    @GetMapping("/clients/current/accounts/true")
    public List<AccountDTO> getAccountDtoTrue(){
        List<AccountDTO> accountDTOList= accountRepository.findAll().stream().map(AccountDTO::new).collect(Collectors.toList());
        List<AccountDTO> accountDTOListTrue=accountDTOList.stream().filter(AccountDTO::getActive).collect(Collectors.toList());
        return  accountDTOListTrue;
    }
//    @PostMapping("/activate-accounts")
//    public ResponseEntity<String> activateAccount(@RequestParam String email, @RequestParam String checkDigit) {
//
//        String  message = "";
//
//        if (email.isBlank()) {
//            message = "Missing email";
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
//        }
//        if (checkDigit.isBlank()) {
//            message = "Missing check digit";
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
//        }
//
//        Client client = clientService.findByEmail(email);
//
//        if (client != null && client.getCheckDigit().equals(checkDigit)) {
//            Account account = client.getAccounts().stream().findFirst().orElse(null);
//            if (account != null) {
//                account.setActive(true);
//                accountService.saveAccount(account);
//
//                // Enviar correo electrónico de activación de cuenta
//                String body = "Your account has been activated successfully. You can now log in.";
//                Email emailMessage = new Email( email,
//                        "Your Account Has Been Successfully Activated!",
//                        body);
//                emailService.sendAccountActivationEmail(emailMessage);
//                return ResponseEntity.ok("Account activated successfully. Activation email sent.");
//            } else {
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Account not found.");
//            }
//        } else {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid email or check digit.");
//        }
//    }
    @PatchMapping("/clients/current/accounts/delete/{id}")
    public ResponseEntity<Object> deleteAccount (Authentication authentication,
                                                 @PathVariable Long id){
        Client client = clientService.findByEmail(authentication.getName());
        Account account = accountService.findById(id);
        Boolean exists = client.getAccounts().contains(account);
        String message = " ";

        if (account == null) {
            message = "Account not found";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
        if (!exists) {
            message = "Account not found";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
        if(!account.getActive()){
            message = "Account already deleted";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }

        account.setActive(false);
        accountService.saveAccount(account);

        message = "Account deleted successfully";
        return ResponseEntity.ok(message);
    }

    @PostMapping("/clients/current/accounts")
    public ResponseEntity<Object> registerAccount(Authentication authentication,
                                                  @RequestParam String typeAccounts) {
        Client client = clientService.findByEmail(authentication.getName());
        String mensaje = "";

        if (client == null) {
            mensaje = "Client not found";
            return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
        }

        List<Account> accounts = accountService.findByClientList(client);
        if (accounts.size() >=3) {
            mensaje = "Can't create more accounts";
            return new ResponseEntity<>(mensaje, HttpStatus.FORBIDDEN);
        }

        String number = "VIN" + getStringRandomNumber();
        Account account = new Account( number, LocalDate.now(), TypeAccounts.Client);
        client.addAccount(account);
        accountService.saveAccount(account);

        mensaje= "New account created";
        return new ResponseEntity<>(mensaje, HttpStatus.CREATED);
    }

    int min = 00000000;
    int max = 99999999;

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public String getStringRandomNumber() {
        int randomNumber = getRandomNumber(min, max);
        return String.valueOf(randomNumber);
    }

}
