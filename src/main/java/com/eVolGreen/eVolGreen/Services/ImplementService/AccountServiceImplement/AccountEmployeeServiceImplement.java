package com.eVolGreen.eVolGreen.Services.ImplementService.AccountServiceImplement;

import com.eVolGreen.eVolGreen.Models.Account.TypeOfAccount.AccountEmployee;
import com.eVolGreen.eVolGreen.Repositories.AccountEmployeeRepository;
import com.eVolGreen.eVolGreen.Services.AccountService.AccountEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountEmployeeServiceImplement implements AccountEmployeeService {

    @Autowired
    private AccountEmployeeRepository accountEmployeeRepository;

    @Override
    public void saveCuentaTrabajador(AccountEmployee newAccount) {
        accountEmployeeRepository.save(newAccount);

    }
}
