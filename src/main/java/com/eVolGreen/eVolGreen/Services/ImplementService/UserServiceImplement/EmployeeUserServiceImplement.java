package com.eVolGreen.eVolGreen.Services.ImplementService.UserServiceImplement;


import com.eVolGreen.eVolGreen.DTOS.UserDTO.EmployeeDTO.EmployeeUserDTO;
import com.eVolGreen.eVolGreen.DTOS.UserDTO.EmployeeDTO.EmployeeUserLoginDTO;
import com.eVolGreen.eVolGreen.Models.User.subclassUser.EmployeeUser;
import com.eVolGreen.eVolGreen.Repositories.EmployeeUserRepository;
import com.eVolGreen.eVolGreen.Services.DUserService.EmployeeUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeUserServiceImplement implements EmployeeUserService {
    @Autowired
    private EmployeeUserRepository employeeUserRepository;


    @Override
    public List<EmployeeUserDTO> getEmployeeUsersDTO() {
        return employeeUserRepository.findAll()
                .stream()
                .map(EmployeeUserDTO::new)
                .collect(Collectors.toList());
    }
    @Override
    public void saveEmployee(EmployeeUser employee) {
        employeeUserRepository.save(employee);
    }

    @Override
    public void deleteEmployee(Long id) {


    }

    @Override
    public EmployeeUser findByEmail(String email) {
        return employeeUserRepository.findByEmail(email);
    }

    @Override
    public List<EmployeeUserLoginDTO> getEmployeeUsersLoginDTO() {
        return employeeUserRepository.findAll()
                .stream()
                .map(EmployeeUserLoginDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeUserDTO getEmployeeUserDTO(Long id) {
        return new EmployeeUserDTO(this.findById(id));
    }

    @Override
    public EmployeeUser findById(Long id) {
        return employeeUserRepository.findById(id).orElse(null);
    }

    @Override
    public EmployeeUserDTO getEmployeeDTOByEmailCurrent(String email) {
        return new EmployeeUserDTO(employeeUserRepository.findByEmail(email));
    }
}
