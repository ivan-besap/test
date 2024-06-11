package com.eVolGreen.eVolGreen.Controllers;

import com.eVolGreen.eVolGreen.DTOS.Employee.EmployeeDTO;
import com.eVolGreen.eVolGreen.DTOS.Employee.EmployeeLoginDTO;
import com.eVolGreen.eVolGreen.Repositories.EmployeeRepository;
import com.eVolGreen.eVolGreen.Services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
