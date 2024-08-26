package com.eVolGreen.eVolGreen.DTOS.AccountDTO.TypeOfAccountDTO;

import com.eVolGreen.eVolGreen.DTOS.AccountDTO.CarDTO.CarCompanyDTO;
import com.eVolGreen.eVolGreen.DTOS.AccountDTO.FeeDTO.FeeDTO;
import com.eVolGreen.eVolGreen.DTOS.AccountDTO.PermissionCredentialDTO.CredentialDTO;
import com.eVolGreen.eVolGreen.DTOS.AccountDTO.PermissionCredentialDTO.PermissionDTO;
import com.eVolGreen.eVolGreen.DTOS.AccountDTO.TransactionDTO;
import com.eVolGreen.eVolGreen.DTOS.ChargingStationDTO.ChargingStationsDTO;
import com.eVolGreen.eVolGreen.DTOS.UserDTO.ClientDTO.ClientUserDTO;
import com.eVolGreen.eVolGreen.DTOS.UserDTO.EmployeeDTO.EmployeeUserDTO;
import com.eVolGreen.eVolGreen.Models.Account.Car.Car;
import com.eVolGreen.eVolGreen.Models.Account.Fee.Fee;
import com.eVolGreen.eVolGreen.Models.Account.Location;
import com.eVolGreen.eVolGreen.Models.Account.PermissionCredential.Credential;
import com.eVolGreen.eVolGreen.Models.Account.PermissionCredential.Permission;
import com.eVolGreen.eVolGreen.Models.Account.Transaction.Transaction;
import com.eVolGreen.eVolGreen.Models.Account.TypeOfAccount.AccountClient;
import com.eVolGreen.eVolGreen.Models.Account.TypeOfAccount.AccountCompany;
import com.eVolGreen.eVolGreen.Models.Account.TypeOfAccount.AccountEmployee;
import com.eVolGreen.eVolGreen.Models.Account.TypeOfAccount.TypeAccounts;
import com.eVolGreen.eVolGreen.Models.ChargingStation.ChargingStation;
import com.eVolGreen.eVolGreen.Models.User.Role;
import com.eVolGreen.eVolGreen.Models.User.subclassUser.ClientUser;

import com.eVolGreen.eVolGreen.Models.User.subclassUser.EmployeeUser;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class AccountCompanyDTO {

    private long id;

    @NotNull(message = "El número de cuenta no puede ser nulo.")
    private String NumeroDeCuenta;

    @NotBlank(message = "El nombre no puede estar vacío")
    private String NombreCompañia;

    @NotBlank(message = "El RUT no puede estar vacío")
    private String Rut;

    @NotNull(message = "El Email es obligatorio")
    private String Email;

    @NotNull(message = "La Contraseña es obligatoria")
    private String Password;

    private long Compañia;

    private long CuentaPrincipal;

    private List<ClientUserDTO> Clientes;

    private List<EmployeeUserDTO> Trabajador;

    private List<FeeDTO> Tarifas;

    private List<CarCompanyDTO> Autos;

    private List<ChargingStationsDTO> Terminales;

    private List<PermissionDTO> Permisos;

    private List<CredentialDTO> Credenciales;

    private List<AccountEmployeeDTO> CuentaEmpleado;

    private List<AccountClientDTO> CuentaCliente;

    private List<TransactionDTO> Transacciones;

    @Enumerated(EnumType.STRING)
    private Role Rol = Role.COMPANY;

    @NotNull(message = "El tipo de cuenta no puede ser vacío")
    @Enumerated(EnumType.STRING)
    private TypeAccounts TipoCuenta = TypeAccounts.Company;

    @NotNull(message = "La ubicación de la compañia no puede ser nula.")
    private Location UbicacionCuentaCompañia;

    private Boolean Activo = false;

    public AccountCompanyDTO(AccountCompany CuentaCompañia) {

        id = CuentaCompañia.getId();
        NumeroDeCuenta = CuentaCompañia.getNumeroDeCuenta();
        NombreCompañia = CuentaCompañia.getNombreCompañia();
        Rut = CuentaCompañia.getRut();
        Email = CuentaCompañia.getEmail();
        Password = CuentaCompañia.getPassword();
        Compañia = CuentaCompañia.getCompañia().getId();
        CuentaPrincipal = CuentaCompañia.getCuentaPrincipal().getId();
        Clientes = CuentaCompañia.getClientes().stream().map(ClientUserDTO::new).collect(Collectors.toList());
        Trabajador = CuentaCompañia.getTrabajador().stream().map(EmployeeUserDTO::new).collect(Collectors.toList());
        Tarifas = CuentaCompañia.getTarifas().stream().map(FeeDTO::new).collect(Collectors.toList());
        Autos = CuentaCompañia.getAutos().stream().map(CarCompanyDTO::new).collect(Collectors.toList());
        Terminales = CuentaCompañia.getTerminales().stream().map(ChargingStationsDTO::new).collect(Collectors.toList());
        Permisos = CuentaCompañia.getPermisos().stream().map(PermissionDTO::new).collect(Collectors.toList());
        Credenciales = CuentaCompañia.getCredenciales().stream().map(CredentialDTO::new).collect(Collectors.toList());
        CuentaEmpleado = CuentaCompañia.getCuentaEmpleado().stream().map(AccountEmployeeDTO::new).collect(Collectors.toList());
        CuentaCliente = CuentaCompañia.getCuentaCliente().stream().map(AccountClientDTO::new).collect(Collectors.toList());
        Transacciones = CuentaCompañia.getTransacciones().stream().map(TransactionDTO::new).collect(Collectors.toList());
        Rol = CuentaCompañia.getRol();
        TipoCuenta = CuentaCompañia.getTipoCuenta();
        UbicacionCuentaCompañia = CuentaCompañia.getUbicacionCuentaCompañia();
        Activo = CuentaCompañia.getActivo();

    }

    public long getId() {
        return id;
    }

    public @NotNull(message = "El número de cuenta no puede ser nulo.") String getNumeroDeCuenta() {
        return NumeroDeCuenta;
    }

    public @NotBlank(message = "El nombre no puede estar vacío") String getNombreCompañia() {
        return NombreCompañia;
    }

    public @NotBlank(message = "El RUT no puede estar vacío") String getRut() {
        return Rut;
    }

    public @NotNull(message = "El Email es obligatorio") String getEmail() {
        return Email;
    }

    public @NotNull(message = "La Contraseña es obligatoria") String getPassword() {
        return Password;
    }

    public long getCompañia() {
        return Compañia;
    }

    public long getCuentaPrincipal() {
        return CuentaPrincipal;
    }

    public List<ClientUserDTO> getClientes() {
        return Clientes;
    }

    public List<EmployeeUserDTO> getTrabajador() {
        return Trabajador;
    }

    public List<FeeDTO> getTarifas() {
        return Tarifas;
    }

    public List<CarCompanyDTO> getAutos() {
        return Autos;
    }

    public List<ChargingStationsDTO> getTerminales() {
        return Terminales;
    }

    public List<PermissionDTO> getPermisos() {
        return Permisos;
    }

    public List<CredentialDTO> getCredenciales() {
        return Credenciales;
    }

    public List<AccountEmployeeDTO> getCuentaEmpleado() {
        return CuentaEmpleado;
    }

    public List<AccountClientDTO> getCuentaCliente() {
        return CuentaCliente;
    }

    public List<TransactionDTO> getTransacciones() {
        return Transacciones;
    }

    public Role getRol() {
        return Rol;
    }

    public @NotNull(message = "El tipo de cuenta no puede ser vacío") TypeAccounts getTipoCuenta() {
        return TipoCuenta;
    }

    public @NotNull(message = "La ubicación de la compañia no puede ser nula.") Location getUbicacionCuentaCompañia() {
        return UbicacionCuentaCompañia;
    }

    public Boolean getActivo() {
        return Activo;
    }

    @Override
    public String toString() {
        return "AccountCompanyDTO{" +
                "id=" + id +
                ", NumeroDeCuenta='" + NumeroDeCuenta + '\'' +
                ", NombreCompañia='" + NombreCompañia + '\'' +
                ", Rut='" + Rut + '\'' +
                ", Email='" + Email + '\'' +
                ", Password='" + Password + '\'' +
                ", Compañia=" + Compañia +
                ", CuentaPrincipal=" + CuentaPrincipal +
                ", Clientes=" + Clientes +
                ", Trabajador=" + Trabajador +
                ", Tarifas=" + Tarifas +
                ", Autos=" + Autos +
                ", Terminales=" + Terminales +
                ", Permisos=" + Permisos +
                ", Credenciales=" + Credenciales +
                ", CuentaEmpleado=" + CuentaEmpleado +
                ", CuentaCliente=" + CuentaCliente +
                ", Transacciones=" + Transacciones +
                ", Rol=" + Rol +
                ", TipoCuenta=" + TipoCuenta +
                ", UbicacionCuentaCompañia=" + UbicacionCuentaCompañia +
                ", Activo=" + Activo +
                '}';
    }
}
