package com.eVolGreen.eVolGreen.DTOS.UserDTO.EmployeeDTO;

import com.eVolGreen.eVolGreen.DTOS.AccountDTO.RelationTypeCompanyDTO.CompanyEmployeeRelationDTO;
import com.eVolGreen.eVolGreen.DTOS.AccountDTO.TypeOfAccountDTO.AccountEmployeeDTO;
import com.eVolGreen.eVolGreen.Models.Account.RelationTypeCompany.CompanyEmployeeRelation;
import com.eVolGreen.eVolGreen.Models.Account.TypeOfAccount.AccountCompany;
import com.eVolGreen.eVolGreen.Models.Account.TypeOfAccount.AccountEmployee;
import com.eVolGreen.eVolGreen.Models.User.Role;
import com.eVolGreen.eVolGreen.Models.User.subclassUser.EmployeeUser;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.GenericGenerator;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class EmployeeUserDTO {

    private Long id;

    private String email;

    private String password;

    private Integer telefono;

    private String nombre;

    private String apellidoPaterno;

    private String apellidoMaterno;

    private List<Long> RelacionCompañiaEmpleado;

    private List<Long> CuentaTrabajador;

    private Long CuentaCompañia;

    private Role rol = Role.EMPLOYEE;

    private Boolean activo = false;

    public EmployeeUserDTO(EmployeeUser Trabajado) {

        id = Trabajado.getId();
        nombre = Trabajado.getNombre();
        apellidoPaterno = Trabajado.getApellidoPaterno();
        apellidoMaterno = Trabajado.getApellidoMaterno();
        RelacionCompañiaEmpleado = Trabajado.getRelacionCompañiaEmpleado().stream().map(CompanyEmployeeRelationDTO -> new CompanyEmployeeRelationDTO(CompanyEmployeeRelationDTO).getId()).collect(Collectors.toList());
        CuentaTrabajador = Trabajado.getCuentaTrabajador().stream().map(AccountEmployeeDTO -> new AccountEmployeeDTO(AccountEmployeeDTO).getId()).collect(Collectors.toList());
        CuentaCompañia = Trabajado.getCuentaCompañia().getId();
        rol = Trabajado.getRol();
        activo = Trabajado.getActivo();
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public List<Long> getRelacionCompañiaEmpleado() {
        return RelacionCompañiaEmpleado;
    }

    public List<Long> getCuentaTrabajador() {
        return CuentaTrabajador;
    }

    public Long getCuentaCompañia() {
        return CuentaCompañia;
    }

    public Role getRol() {
        return rol;
    }

    public Boolean getActivo() {
        return activo;
    }

    @Override
    public String toString() {
        return "EmployeeUserDTO{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", telefono=" + telefono +
                ", nombre='" + nombre + '\'' +
                ", apellidoPaterno='" + apellidoPaterno + '\'' +
                ", apellidoMaterno='" + apellidoMaterno + '\'' +
                ", RelacionCompañiaEmpleado=" + RelacionCompañiaEmpleado +
                ", CuentaTrabajador=" + CuentaTrabajador +
                ", CuentaCompañia=" + CuentaCompañia +
                ", rol=" + rol +
                ", activo=" + activo +
                '}';
    }
}
