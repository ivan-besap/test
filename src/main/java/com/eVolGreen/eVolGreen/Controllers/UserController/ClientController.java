package com.eVolGreen.eVolGreen.Controllers.UserController;

import com.eVolGreen.eVolGreen.DTOS.UserDTO.ClientDTO.ClientUserDTO;
import com.eVolGreen.eVolGreen.Models.Account.TypeOfAccount.AccountClient;
import com.eVolGreen.eVolGreen.Models.Email;
import com.eVolGreen.eVolGreen.Models.User.Role;
import com.eVolGreen.eVolGreen.Models.User.subclassUser.ClientUser;
import com.eVolGreen.eVolGreen.Repositories.ClientUserRepository;
import com.eVolGreen.eVolGreen.Services.AccountService.AccountClientService;
import com.eVolGreen.eVolGreen.Services.DUserService.ClientUserService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/api")
public class ClientController {

    @Autowired
    private ClientUserService clientUserService;

    @Autowired
    private AccountClientService accountService;

    @Autowired
    private PasswordEncoder passwordEncoder;

//    @Autowired
//    private EmailServiceImplement emailService;

    @Autowired
    private ClientUserRepository clientUserRepository;


    @GetMapping("/clients")
    public List<ClientUserDTO> getClientsUser() {
        return clientUserService.getClientUsersDTO();
    }

    @GetMapping("/clients/{id}")
    public ClientUserDTO getClientUser(@PathVariable Long id){
        return clientUserService.getClientUserDTO(id);
    }
    @GetMapping("/clients/current")
    public ClientUserDTO getCurrentClientUser(Authentication authentication){

        return clientUserService.getClientDTOByEmailCurrent(authentication.getName());
    }

//    @PostMapping("/clients")
//    public ResponseEntity<Object> registerClientUser(
//            @RequestBody ClientUser client) {
//
//        String mensaje = " ";
//        if (client.getNombre().isBlank()) {
//            mensaje = "Missing first name";
//            return new ResponseEntity<>(mensaje, HttpStatus.FORBIDDEN);
//        }
//        if (client.getApellidoPaterno().isBlank()) {
//            mensaje = "Missing last name";
//            return new ResponseEntity<>(mensaje, HttpStatus.FORBIDDEN);
//        }
//        if (client.getApellidoMaterno().isBlank()) {
//            mensaje = "Missing last name";
//            return new ResponseEntity<>(mensaje, HttpStatus.FORBIDDEN);
//        }
//        if (client.getRut() == null) {
//            mensaje = "Missing rut";
//            return new ResponseEntity<>(mensaje, HttpStatus.FORBIDDEN);
//        }
//        if (client.getEmail().isBlank()) {
//            mensaje = "Missing email";
//            return new ResponseEntity<>(mensaje, HttpStatus.FORBIDDEN);
//        }
//        if (client.getPassword().isBlank()) {
//            mensaje = "Missing password";
//            return new ResponseEntity<>(mensaje, HttpStatus.FORBIDDEN);
//        }
//        if (clientUserService.findByEmail(client.getEmail()) != null) {
//            mensaje = "Email already in use";
//            return new ResponseEntity<>(mensaje, HttpStatus.FORBIDDEN);
//        }
//
//        // Generar el checkDigit
//        String checkDigit = generateCheckDigit();
//
//        Role clientRole = Role.CLIENT;
//
//
//        // Crear el cliente
//        ClientUser newClient = new ClientUser(
//                client.getNombre(),
//                client.getApellidoPaterno(),
//                client.getApellidoMaterno(),
//                client.getRut(),
//                client.getEmail(),
//                passwordEncoder.encode(client.getPassword()),
//                clientRole
//                );
//        clientUserService.saveClientUser(newClient);
//
//        // Verificar si el cliente no es administrador
//        if (!newClient.getEmail().contains("@admin.com")) {
//            String accountNumber = "VIN" + getStringRandomClient();
//            AccountClient newAccount = new AccountClient(accountNumber, LocalDate.now(), TypeAccounts.Client);
//            newClient.addAccountClient(newAccount);
//            accountService.saveAccountClient(newAccount);
//        }
//
//        // Enviar el correo electrónico con el checkDigit
//        try {
//            Email emailMessage = new Email( client.getEmail(),
//                    "CheckDigit",
//                    checkDigit);
//            emailService.sendEmail(emailMessage);
//            mensaje = "Created Client and CheckDigit email sent";
//        } catch (MessagingException e) {
//            mensaje = "Error sending email: " + e.getMessage();
//            return new ResponseEntity<>(mensaje, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//
//        return new ResponseEntity<>(mensaje, HttpStatus.CREATED);
//    }

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int LENGTH = 6; // Longitud del checkDigit
    private String generateCheckDigit() {
        StringBuilder sb = new StringBuilder(LENGTH);
        Random random = new Random();
        for (int i = 0; i < LENGTH; i++) {
            int index = random.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(index));
        }
        return sb.toString();
    }

    int min = 00000000;
    int max = 99999999;

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public String getStringRandomClient() {
        int randomNumber = getRandomNumber(min, max);
        return String.valueOf(randomNumber);
    }

    // Cambia isActiveStatus del cliente actualmente autenticada (Test)
    @PutMapping("/clients/change-active-status")
    public ResponseEntity<Object> changeActiveStatus(Authentication authentication,
                                                     @RequestParam boolean activeStatus) {

        ClientUser client = clientUserService.findByEmail(authentication.getName());
        String message = "";

        if (client == null) {
            message = "Client not found.";
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }

        client.setActivo(activeStatus);
        clientUserService.saveClientUser(client);

        return ResponseEntity.ok("Active status updated to: " + activeStatus);
    }




}
