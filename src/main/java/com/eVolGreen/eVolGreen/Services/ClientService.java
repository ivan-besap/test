package com.eVolGreen.eVolGreen.Services;

import com.eVolGreen.eVolGreen.DTOS.Client.ClientDTO;
import com.eVolGreen.eVolGreen.DTOS.Client.ClientLoginDTO;
import com.eVolGreen.eVolGreen.Models.Client;

import java.util.List;

public interface ClientService {
    Client findByEmail (String email);
    List<ClientDTO> getClientsDTO();
    void saveClient(Client client);

    ClientDTO getClientDTO(Long id);

    Client findById(Long id);

    ClientDTO getClientDTOByEmailCurrent(String email);

}
