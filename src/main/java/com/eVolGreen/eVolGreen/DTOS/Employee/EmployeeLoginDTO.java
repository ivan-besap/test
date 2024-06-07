package com.eVolGreen.eVolGreen.DTOS.Employee;

import com.eVolGreen.eVolGreen.Models.Employee;

public class EmployeeLoginDTO {
    private Long id;
    private String name;
    private String firstSurname;
    private String lastSurname;
    private String email;


    public EmployeeLoginDTO(Employee employee) {
        id = employee.getId();
        name = employee.getName();
        firstSurname = employee.getFirstSurname();
        lastSurname = employee.getLastSurname();
        email = employee.getEmail();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFirstSurname() {
        return firstSurname;
    }

    public String getLastSurname() {
        return lastSurname;
    }

    public String getEmail() {
        return email;
    }
}
