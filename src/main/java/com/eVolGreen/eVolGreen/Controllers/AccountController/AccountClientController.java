package com.eVolGreen.eVolGreen.Controllers.AccountController;

import com.eVolGreen.eVolGreen.Models.Account.Account;
import com.eVolGreen.eVolGreen.Models.Account.TypeOfAccount.AccountClient;
import com.eVolGreen.eVolGreen.Models.Account.TypeOfAccount.TypeAccounts;
import com.eVolGreen.eVolGreen.Models.User.subclassUser.ClientUser;
import com.eVolGreen.eVolGreen.Services.AccountService.AccountClientService;
import com.eVolGreen.eVolGreen.Services.DUserService.ClientUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

public class AccountClientController {

    @Autowired
    private ClientUserService clientUserService;

    @Autowired
    private AccountClientService accountClientService;

    @PostMapping("/clients/current/accounts")
    public ResponseEntity<Object> registerAccountClient(Authentication authentication,
                                                        @RequestParam String typeAccounts) {

        ClientUser client = clientUserService.findByEmail(authentication.getName());
        String mensaje = "";

        if (client == null) {
            mensaje = "Client not found";
            return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
        }

        List<AccountClient> accounts = accountClientService.findByClientList(client);
        if (accounts.size() >=3) {
            mensaje = "Can't create more accounts";
            return new ResponseEntity<>(mensaje, HttpStatus.FORBIDDEN);
        }

        String number = "VIN" + getStringRandomNumber();
//        AccountClient account = new Account( number, LocalDate.now(), TypeAccounts.Client);
//        client.addAccountClient(account);
//        accountClientService.saveAccountClient(account);

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
