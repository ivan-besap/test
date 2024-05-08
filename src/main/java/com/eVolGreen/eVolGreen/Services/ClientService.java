package com.eVolGreen.eVolGreen.Services;

import com.eVolGreen.eVolGreen.DTOS.ClientDTO;
import com.eVolGreen.eVolGreen.Models.Client;

import java.util.List;

public interface ClientService {
    Client findByEmail (String email);
    List<ClientDTO> getClientsDTO();
    void saveClient(Client client);
}
