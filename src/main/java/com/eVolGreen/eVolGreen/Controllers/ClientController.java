package com.eVolGreen.eVolGreen.Controllers;

import com.eVolGreen.eVolGreen.Auth.Role;
import com.eVolGreen.eVolGreen.DTOS.ClientDTO;
import com.eVolGreen.eVolGreen.Models.Account;
import com.eVolGreen.eVolGreen.Models.Client;
import com.eVolGreen.eVolGreen.Models.Email;
import com.eVolGreen.eVolGreen.Models.TypeAccounts;
import com.eVolGreen.eVolGreen.Repositories.ClientRepository;
import com.eVolGreen.eVolGreen.Services.AccountService;
import com.eVolGreen.eVolGreen.Services.ClientService;
import com.eVolGreen.eVolGreen.Services.Implement.EmailServiceImplement;
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
    private ClientService clientService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private EmailServiceImplement emailService;
    @Autowired
    private ClientRepository clientRepository;


    @GetMapping("/clients")
    public List<ClientDTO> getClients() {
        return clientService.getClientsDTO();
    }

    @GetMapping("/clients/{id}")
    public ClientDTO getClient(@PathVariable Long id){
        return clientService.getClientDTO(id);
    }
    @GetMapping("/clients/current")
    public ClientDTO getCurrentClient(Authentication authentication){
        return clientService.getClientDTOByEmailCurrent(authentication.getName());
    }

    @PostMapping("/clients")
    public ResponseEntity<Object> register(
            @RequestBody Client client) {

        String mensaje = " ";
        if (client.getFirstName().isBlank()) {
            mensaje = "Missing first name";
            return new ResponseEntity<>(mensaje, HttpStatus.FORBIDDEN);
        }
        if (client.getLastName().isBlank()) {
            mensaje = "Missing last name";
            return new ResponseEntity<>(mensaje, HttpStatus.FORBIDDEN);
        }
        if (client.getRut() == null) {
            mensaje = "Missing rut";
            return new ResponseEntity<>(mensaje, HttpStatus.FORBIDDEN);
        }
        if (client.getEmail().isBlank()) {
            mensaje = "Missing email";
            return new ResponseEntity<>(mensaje, HttpStatus.FORBIDDEN);
        }
        if (client.getPhone() == null) {
            mensaje = "Missing phone";
            return new ResponseEntity<>(mensaje, HttpStatus.FORBIDDEN);
        }
        if (client.getPassword().isBlank()) {
            mensaje = "Missing password";
            return new ResponseEntity<>(mensaje, HttpStatus.FORBIDDEN);
        }
        if (clientService.findByEmail(client.getEmail()) != null) {
            mensaje = "Email already in use";
            return new ResponseEntity<>(mensaje, HttpStatus.FORBIDDEN);
        }

        // Generar el checkDigit
        String checkDigit = generateCheckDigit();

        Role clientRole = Role.CLIENT;

        // Crear el cliente
        Client newClient = new Client(
                client.getFirstName(),
                client.getLastName(),
                client.getRut(),
                client.getEmail(),
                client.getPhone(),
                passwordEncoder.encode(client.getPassword()),
                clientRole
                );
        clientService.saveClient(newClient);

        // Verificar si el cliente no es administrador
        if (!newClient.getEmail().contains("@admin.com")) {
            String accountNumber = "VIN" + getStringRandomClient();
            Account newAccount = new Account(accountNumber, LocalDate.now(), TypeAccounts.Client);
            newClient.addAccount(newAccount);
            accountService.saveAccount(newAccount);
        }

        // Enviar el correo electrónico con el checkDigit
        try {
            Email emailMessage = new Email( client.getEmail(),
                    "CheckDigit",
                    checkDigit);
            emailService.sendEmail(emailMessage);
            mensaje = "Created Client and CheckDigit email sent";
        } catch (MessagingException e) {
            mensaje = "Error sending email: " + e.getMessage();
            return new ResponseEntity<>(mensaje, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(mensaje, HttpStatus.CREATED);
    }

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




}
