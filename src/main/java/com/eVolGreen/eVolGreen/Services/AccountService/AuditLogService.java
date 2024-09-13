package com.eVolGreen.eVolGreen.Services.AccountService;

import com.eVolGreen.eVolGreen.Models.Account.Account;

public interface AuditLogService {
    void recordAction(String description, Account account);
}