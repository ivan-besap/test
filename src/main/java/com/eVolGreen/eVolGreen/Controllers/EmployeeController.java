package com.eVolGreen.eVolGreen.Controllers;

import com.eVolGreen.eVolGreen.DTOS.Employee.EmployeeDTO;
import com.eVolGreen.eVolGreen.DTOS.Employee.EmployeeLoginDTO;
import com.eVolGreen.eVolGreen.Models.Client;
import com.eVolGreen.eVolGreen.Models.Employee;
import com.eVolGreen.eVolGreen.Repositories.EmployeeRepository;
import com.eVolGreen.eVolGreen.Services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/employees")
    public List<EmployeeDTO> getEmployees() {
        return employeeService.getEmployeesDTO();
    }

    @GetMapping("/employees/{id}")
    public EmployeeDTO getEmployee(@PathVariable Long id){
        return employeeService.getEmployeeDTO(id);
    }

    @GetMapping("/employees/logins")
    public List<EmployeeLoginDTO> getEmployeesLogin() {
        return employeeService.getEmployeesLoginDTO();
    }

    // Cambia isActiveStatus del cliente actualmente autenticada (Test)
    @PutMapping("/employees/change-active-status")
    public ResponseEntity<Object> changeActiveStatus(Authentication authentication,
                                                     @RequestParam boolean activeStatus) {

        Employee employee = employeeService.findByEmail(authentication.getName());
        String message = "";

        if (employee == null) {
            message = "Employee not found.";
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }

        employee.setActive(activeStatus);
        employeeService.saveEmployee(employee);

        return ResponseEntity.ok("Active status updated to: " + activeStatus);
    }

}
