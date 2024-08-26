package com.eVolGreen.eVolGreen.Services.ImplementService.AccountServiceImplement;

import com.eVolGreen.eVolGreen.DTOS.AccountDTO.AccountDTO;
import com.eVolGreen.eVolGreen.Models.Account.Account;
import com.eVolGreen.eVolGreen.Models.User.subclassUser.CompanyUser;
import com.eVolGreen.eVolGreen.Repositories.AccountRepository;
import com.eVolGreen.eVolGreen.Services.AccountService.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImplement implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public List<AccountDTO> getAccountsDTO() {
        return accountRepository.findAll()
                .stream()
                .map(AccountDTO::new)
                .collect(Collectors.toList());
    }
    @Override
    public AccountDTO getAccountDTOCurrent(long id) {
        return new AccountDTO(accountRepository.findById(id).orElse(null));
    }
    @Override
    public List<Account> accounts(CompanyUser company) {
        return accountRepository.findByCuentaCompañia(company);
    }
    @Override
    public void saveAccount(Account account) {
        accountRepository.save(account);
    }

    @Override
    public List<Account> findByClientList(CompanyUser client) {
        return accountRepository.findByCuentaCompañia(client);
    }
    @Override
    public Account findByNumber(String number) {
        return accountRepository.findByNumeroDeCuenta(number );
    }
    @Override
    public boolean existsByNumber(String number) {
        return accountRepository.existsByNumeroDeCuenta(number);
    }
    @Override
    public Account findById(Long id) {
        return accountRepository.findById(id).orElse(null);
    }

    @Override
    public List<AccountDTO> findAll() {
        return accountRepository.findAll()
                .stream()
                .map(AccountDTO::new)
                .collect(Collectors.toList());
    }
}
