package com.eVolGreen.eVolGreen.Services.AccountService;

import com.eVolGreen.eVolGreen.Models.Account.TypeOfAccount.AccountCompany;
import com.eVolGreen.eVolGreen.Models.User.subclassUser.AdminCompanyUser;

import java.util.Optional;

public interface AccountCompanyService {
    Optional<AccountCompany> findById(Long accountCompanyId);

    void saveCuentaCompañia(AccountCompany accountCompany);

}
