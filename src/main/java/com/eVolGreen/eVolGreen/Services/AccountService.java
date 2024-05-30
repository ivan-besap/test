package com.eVolGreen.eVolGreen.Services;

import com.eVolGreen.eVolGreen.DTOS.AccountDTO;
import com.eVolGreen.eVolGreen.Models.Account;
import com.eVolGreen.eVolGreen.Models.Client;

import java.util.List;

public interface AccountService {
    List<AccountDTO> getAccountsDTO();
    AccountDTO getAccountDTOCurrent (long id);
    List<Account> accounts (Client client);
    void saveAccount(Account account);
    List<Account> findByClientList (Client client);
    Account findByNumber(String number);
    boolean existsByNumber(String number);

    Account findById(Long id);
}
