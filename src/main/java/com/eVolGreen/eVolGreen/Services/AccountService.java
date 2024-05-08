package com.eVolGreen.eVolGreen.Services;

import com.eVolGreen.eVolGreen.DTOS.AccountDTO;
import com.eVolGreen.eVolGreen.Models.Account;

import java.util.List;

public interface AccountService {
    List<AccountDTO> getAccountsDTO();
    void saveAccount(Account account);
}
