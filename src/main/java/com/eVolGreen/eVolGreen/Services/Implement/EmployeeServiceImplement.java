package com.eVolGreen.eVolGreen.Services.Implement;

import com.eVolGreen.eVolGreen.DTOS.Employee.EmployeeDTO;
import com.eVolGreen.eVolGreen.DTOS.Employee.EmployeeLoginDTO;
import com.eVolGreen.eVolGreen.Models.Employee;
import com.eVolGreen.eVolGreen.Repositories.EmployeeRepository;
import com.eVolGreen.eVolGreen.Services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImplement implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;


    @Override
    public List<EmployeeDTO> getEmployeesDTO() {
        return employeeRepository.findAll()
                .stream()
                .map(EmployeeDTO::new)
                .collect(Collectors.toList());
    }
    @Override
    public void saveEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public Employee findByEmail(String email) {
        return employeeRepository.findByEmail(email);
    }

    @Override
    public List<EmployeeLoginDTO> getEmployeesLoginDTO() {
        return employeeRepository.findAll()
                .stream()
                .map(EmployeeLoginDTO::new)
                .collect(Collectors.toList());
    }
}
