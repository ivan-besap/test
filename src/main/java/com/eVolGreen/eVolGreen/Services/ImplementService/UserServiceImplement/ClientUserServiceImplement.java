package com.eVolGreen.eVolGreen.Services.ImplementService.UserServiceImplement;

import com.eVolGreen.eVolGreen.DTOS.UserDTO.ClientDTO.ClientUserDTO;
import com.eVolGreen.eVolGreen.Models.User.subclassUser.ClientUser;
import com.eVolGreen.eVolGreen.Repositories.ClientUserRepository;
import com.eVolGreen.eVolGreen.Services.DUserService.ClientUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientUserServiceImplement implements ClientUserService {

    @Autowired
    private ClientUserRepository clientUserRepository;

    @Override
    public List<ClientUserDTO> getClientUsersDTO() {
        return clientUserRepository.findAll()
                .stream()
                .map(ClientUserDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public ClientUserDTO getClientUserDTO(Long id) {
        return new ClientUserDTO(this.findById(id));
    }

    @Override
    public ClientUserDTO getClientDTOByEmailCurrent(String authentication) {
        ClientUser client = clientUserRepository.findByEmail(authentication);
        if (client != null) {
            return new ClientUserDTO(client);
        } else {
            return null;
        }
    }

    @Override
    public ClientUser findById(Long id) {
        return clientUserRepository.findById(id).orElse(null);
    }

    @Override
    public ClientUser findByEmail(String email) {
        return clientUserRepository.findByEmail(email);
    }

    @Override
    public void saveClientUser(ClientUser client) {
        clientUserRepository.save(client);
    }


}
