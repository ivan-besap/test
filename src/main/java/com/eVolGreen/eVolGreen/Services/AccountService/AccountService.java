package com.eVolGreen.eVolGreen.Services.AccountService;


import com.eVolGreen.eVolGreen.DTOS.AccountDTO.AccountDTO;
import com.eVolGreen.eVolGreen.Models.Account.Account;
import com.eVolGreen.eVolGreen.Models.User.subclassUser.CompanyUser;

import java.util.Arrays;
import java.util.List;

public interface AccountService {

    List<AccountDTO> getAccountsDTO();

    AccountDTO getAccountDTOCurrent (long id);

    List<Account> accounts (CompanyUser client);

    void saveAccount(Account account);

    List<Account> findByClientList (CompanyUser client);

    Account findByNumber(String number);

    boolean existsByNumber(String number);

    Account findById(Long id);

    List<AccountDTO> findAll();
}
