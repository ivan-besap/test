package com.eVolGreen.eVolGreen.Repositories;


import com.eVolGreen.eVolGreen.Models.User.subclassUser.ClientUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ClientUserRepository extends JpaRepository<ClientUser, Long> {

    ClientUser findByEmail(String username);

}
