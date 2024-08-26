package com.eVolGreen.eVolGreen.Repositories;

import com.eVolGreen.eVolGreen.Models.Account.TypeOfAccount.AccountClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface AccountClientRepository extends JpaRepository<AccountClient, Long>{
//    List<AccountClient> findByClientUser(ClientUser client);
}
