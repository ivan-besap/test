package com.eVolGreen.eVolGreen.Services;

import com.eVolGreen.eVolGreen.DTOS.EmployeeDTO;
import com.eVolGreen.eVolGreen.Models.Employee;

import java.util.List;

public interface EmployeeService {

    List<EmployeeDTO> getEmployeesDTO();
    void saveEmployee(Employee employee);
    Employee findByEmail(String email);
}
