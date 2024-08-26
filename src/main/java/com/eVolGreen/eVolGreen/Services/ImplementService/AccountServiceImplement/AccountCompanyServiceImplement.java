package com.eVolGreen.eVolGreen.Services.ImplementService.AccountServiceImplement;

import com.eVolGreen.eVolGreen.Models.Account.TypeOfAccount.AccountCompany;
import com.eVolGreen.eVolGreen.Models.User.subclassUser.AdminCompanyUser;
import com.eVolGreen.eVolGreen.Repositories.AccountCompanyRepository;
import com.eVolGreen.eVolGreen.Services.AccountService.AccountCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountCompanyServiceImplement implements AccountCompanyService {
    @Autowired
    private AccountCompanyRepository accountCompanyRepository;

    @Override
    public Optional<AccountCompany> findById(Long accountCompanyId) {
        return Optional.empty();
    }

    @Override
    public void saveCuentaCompañia(AccountCompany accountCompany) {
        accountCompanyRepository.save(accountCompany);
    }

}
