package com.eVolGreen.eVolGreen.Services.AccountService;


import com.eVolGreen.eVolGreen.DTOS.AccountDTO.AccountDTO;
import com.eVolGreen.eVolGreen.Models.Account.Account;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public interface AccountService {

    List<AccountDTO> getAccountsDTO(); // Retorna todas las cuentas como DTO

    AccountDTO getAccountDTOCurrent(long id); // Retorna una cuenta específica como DTO

    void saveAccount(Account account); // Guarda o actualiza una cuenta

    Account findByNumber(String number); // Busca una cuenta por su número

    boolean existsByNumber(String number); // Verifica si existe una cuenta por su número

    Account findById(Long id); // Encuentra una cuenta por su ID

    List<AccountDTO> findAll(); // Retorna todas las cuentas como DTO

    Optional<Account> findByEmail(String email); // Busca una cuenta por su email
}