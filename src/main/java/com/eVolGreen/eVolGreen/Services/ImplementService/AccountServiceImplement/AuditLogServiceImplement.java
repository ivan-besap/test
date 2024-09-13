package com.eVolGreen.eVolGreen.Services.ImplementService.AccountServiceImplement;

import com.eVolGreen.eVolGreen.Models.Account.Account;
import com.eVolGreen.eVolGreen.Models.Account.AuditLog;
import com.eVolGreen.eVolGreen.Repositories.AuditLogRepository;
import com.eVolGreen.eVolGreen.Services.AccountService.AuditLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuditLogServiceImplement implements AuditLogService {

    @Autowired
    private AuditLogRepository auditLogRepository;

    @Override
    public void recordAction(String description, Account account) {
        AuditLog auditLog = new AuditLog(account, description, LocalDateTime.now());
        auditLogRepository.save(auditLog);
    }
}
