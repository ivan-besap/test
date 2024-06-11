package com.eVolGreen.eVolGreen.Services;

import com.eVolGreen.eVolGreen.DTOS.Employee.EmployeeDTO;
import com.eVolGreen.eVolGreen.DTOS.Employee.EmployeeLoginDTO;
import com.eVolGreen.eVolGreen.Models.Employee;

import java.util.List;

public interface EmployeeService {

    List<EmployeeDTO> getEmployeesDTO();
    void saveEmployee(Employee employee);
    Employee findByEmail(String email);

    List<EmployeeLoginDTO> getEmployeesLoginDTO();

    EmployeeDTO getEmployeeDTO(Long id);

    Employee findById(Long id);

    EmployeeDTO getEmployeeDTOByEmailCurrent(String email);
}
