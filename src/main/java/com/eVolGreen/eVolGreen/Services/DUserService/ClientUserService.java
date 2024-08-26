package com.eVolGreen.eVolGreen.Services.DUserService;

import com.eVolGreen.eVolGreen.DTOS.UserDTO.ClientDTO.ClientUserDTO;
import com.eVolGreen.eVolGreen.Models.User.subclassUser.ClientUser;

import java.util.List;

public interface ClientUserService {

    ClientUser findByEmail (String email);

    List<ClientUserDTO> getClientUsersDTO();

    void saveClientUser(ClientUser client);

    ClientUserDTO getClientUserDTO(Long id);

    ClientUser findById(Long id);

    ClientUserDTO getClientDTOByEmailCurrent(String email);

}
