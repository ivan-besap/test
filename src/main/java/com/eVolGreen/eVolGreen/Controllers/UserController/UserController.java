package com.eVolGreen.eVolGreen.Controllers.UserController;

import com.eVolGreen.eVolGreen.DTOS.AccountDTO.AccountDTO;
import com.eVolGreen.eVolGreen.Models.Account.Account;
import com.eVolGreen.eVolGreen.Services.AccountService.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/user/current")
    public ResponseEntity<UserResponse> getCurrentUser(Authentication authentication) {
        return accountService.findByEmail(authentication.getName())
                .map(account -> {
                    AccountDTO accountDTO = new AccountDTO(account); // Adaptar a tu DTO si es necesario
                    return ResponseEntity.ok(new UserResponse(account.getTipoCuenta().name(), accountDTO));
                })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new UserResponse("NOT_FOUND", null)));
    }

    // Clase para la respuesta
    public static class UserResponse {
        private String userType;
        private Object userData;

        public UserResponse(String userType, Object userData) {
            this.userType = userType;
            this.userData = userData;
        }

        // Getters y setters
        public String getUserType() { return userType; }
        public void setUserType(String userType) { this.userType = userType; }
        public Object getUserData() { return userData; }
        public void setUserData(Object userData) { this.userData = userData; }
    }
}