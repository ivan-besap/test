package com.eVolGreen.eVolGreen.Services.Implement;

import com.eVolGreen.eVolGreen.DTOS.Client.ClientDTO;
import com.eVolGreen.eVolGreen.DTOS.Client.ClientLoginDTO;
import com.eVolGreen.eVolGreen.Models.Client;
import com.eVolGreen.eVolGreen.Repositories.ClientRepository;
import com.eVolGreen.eVolGreen.Services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientServiceImplement implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public List<ClientDTO> getClientsDTO() {
        return clientRepository.findAll()
                .stream()
                .map(ClientDTO::new)
                .collect(Collectors.toList());
    }
    @Override
    public ClientDTO getClientDTO(Long id) {
        return new ClientDTO(this.findById(id));
    }
    @Override
    public ClientDTO getClientDTOByEmailCurrent(String authentication) {
        return new ClientDTO(clientRepository.findByEmail(authentication));
    }

    @Override
    public List<ClientLoginDTO> getClientsLoginDTO() {
        return clientRepository.findAll()
                .stream()
                .map(ClientLoginDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public Client findById(Long id) {
        return clientRepository.findById(id).orElse(null);
    }
    @Override
    public Client findByEmail(String email) {
        return clientRepository.findByEmail(email);
    }
    @Override
    public void saveClient(Client client) {
        clientRepository.save(client);
    }
}
