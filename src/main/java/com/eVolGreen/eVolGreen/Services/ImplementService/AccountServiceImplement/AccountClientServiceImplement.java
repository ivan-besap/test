package com.eVolGreen.eVolGreen.Services.ImplementService.AccountServiceImplement;

import com.eVolGreen.eVolGreen.Models.Account.TypeOfAccount.AccountClient;
import com.eVolGreen.eVolGreen.Models.User.subclassUser.ClientUser;
import com.eVolGreen.eVolGreen.Services.AccountService.AccountClientService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountClientServiceImplement implements AccountClientService {
    @Override
    public void saveAccountClient(AccountClient account) {

    }

    @Override
    public List<AccountClient> findByClientList(ClientUser client) {
        return List.of();
    }
}
