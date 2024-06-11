package com.eVolGreen.eVolGreen.Auth;

import com.eVolGreen.eVolGreen.DTOS.Client.ClientDTO;
import com.eVolGreen.eVolGreen.DTOS.Company.CompanyDTO;
import com.eVolGreen.eVolGreen.DTOS.Employee.EmployeeDTO;
import com.eVolGreen.eVolGreen.Services.ClientService;
import com.eVolGreen.eVolGreen.Services.CompanyService;
import com.eVolGreen.eVolGreen.Services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/user/current")
    public ResponseEntity<?> getCurrentUser(Authentication authentication) {
        // Primero intentamos obtener el usuario como cliente
        ClientDTO client = clientService.getClientDTOByEmailCurrent(authentication.getName());
        if (client != null) {
            return ResponseEntity.ok(new UserResponse("client", client));
        }

        // Si no es cliente, intentamos como compañía
        CompanyDTO company = companyService.getCompanyDTOByEmailCurrent(authentication.getName());
        if (company != null) {
            return ResponseEntity.ok(new UserResponse("company", company));
        }

        // Finalmente intentamos como empleado
        EmployeeDTO employee = employeeService.getEmployeeDTOByEmailCurrent(authentication.getName());
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
