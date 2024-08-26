package com.eVolGreen.eVolGreen.Controllers.UserController;

import com.eVolGreen.eVolGreen.DTOS.UserDTO.ClientDTO.ClientUserDTO;
import com.eVolGreen.eVolGreen.DTOS.UserDTO.CompanyDTO.CompanyUserDTO;
import com.eVolGreen.eVolGreen.DTOS.UserDTO.EmployeeDTO.EmployeeUserDTO;
import com.eVolGreen.eVolGreen.Services.DUserService.ClientUserService;
import com.eVolGreen.eVolGreen.Services.DUserService.CompanyUserService;
import com.eVolGreen.eVolGreen.Services.DUserService.EmployeeUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private ClientUserService clientUserService;

    @Autowired
    private CompanyUserService companyUserService;

    @Autowired
    private EmployeeUserService employeeUserService;


    @GetMapping("/user/current")
    public ResponseEntity<?> getCurrentUser(Authentication authentication) {
        // Primero intentamos obtener el usuario como cliente
        ClientUserDTO client = clientUserService.getClientDTOByEmailCurrent(authentication.getName());
        if (client != null) {
            return ResponseEntity.ok(new UserResponse("client", client));
        }

        // Si no es cliente, intentamos como compañía
        CompanyUserDTO company = companyUserService.getCompanyDTOByEmailCurrent(authentication.getName());
        if (company != null) {
            return ResponseEntity.ok(new UserResponse("company", company));
        }

        // Finalmente intentamos como empleado
        EmployeeUserDTO employee = employeeUserService.getEmployeeDTOByEmailCurrent(authentication.getName());
        if (employee != null) {
            return ResponseEntity.ok(new UserResponse("employee", employee));
        }

        // Si no se encuentra el usuario, devolvemos un error
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
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
