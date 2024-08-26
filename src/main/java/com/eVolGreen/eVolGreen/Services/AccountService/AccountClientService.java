package com.eVolGreen.eVolGreen.Services.AccountService;

import com.eVolGreen.eVolGreen.Models.Account.TypeOfAccount.AccountClient;
import com.eVolGreen.eVolGreen.Models.User.subclassUser.ClientUser;

import java.util.List;


public interface AccountClientService {
    void saveAccountClient(AccountClient account);

    List<AccountClient> findByClientList(ClientUser client);

}
