package com.eVolGreen.eVolGreen.Services.DUserService;




import com.eVolGreen.eVolGreen.DTOS.UserDTO.EmployeeDTO.EmployeeUserDTO;
import com.eVolGreen.eVolGreen.DTOS.UserDTO.EmployeeDTO.EmployeeUserLoginDTO;
import com.eVolGreen.eVolGreen.Models.User.subclassUser.EmployeeUser;

import java.util.List;

public interface EmployeeUserService {

    void saveEmployee(EmployeeUser employee);

    void deleteEmployee(Long id);

    List<EmployeeUserDTO> getEmployeeUsersDTO();

    EmployeeUser findByEmail(String email);

    List<EmployeeUserLoginDTO> getEmployeeUsersLoginDTO();

    EmployeeUserDTO getEmployeeUserDTO(Long id);

    EmployeeUser findById(Long id);

    EmployeeUserDTO getEmployeeDTOByEmailCurrent(String email);
}
