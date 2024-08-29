package com.eVolGreen.eVolGreen.Models.Account;

import com.eVolGreen.eVolGreen.Models.ChargingStation.ChargingStation;
import com.eVolGreen.eVolGreen.Models.Account.TypeOfAccount.AccountClient;
import com.eVolGreen.eVolGreen.Models.Account.TypeOfAccount.AccountCompany;
import com.eVolGreen.eVolGreen.Models.Account.TypeOfAccount.AccountEmployee;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.GenericGenerator;


@Entity
public class Location {

    @Id
    @GenericGenerator(name= "native", strategy = "native")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    private long id;

    @NotNull(message = "La Direccion es obligatoria")
    private String direccion;

    @OneToOne(mappedBy = "UbicacionCuentaTrabajador")
    @JsonBackReference
    private AccountEmployee CuentaEmpleado;

    @OneToOne(mappedBy = "UbicacionCuentaCliente")
    @JsonBackReference
    private AccountClient CuentaCliente;

    @OneToOne(mappedBy = "UbicacionCuentaCompañia")
    @JsonBackReference
    private AccountCompany CuentaCompania;

    @OneToOne(mappedBy = "UbicacionTerminal")
    @JsonBackReference
    private ChargingStation Terminal;

    public Location() { }

    public Location(String Direccion) {
//        this.latitude = latitude;
//        this.longitude = longitude;
        this.direccion = Direccion;
//        this.city = city;
//        this.region = region;
//        this.country = country;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public @NotNull(message = "La Direccion es obligatoria") String getDireccion() {
        return direccion;
    }

    public void setDireccion(@NotNull(message = "La Direccion es obligatoria") String direccion) {
        this.direccion = direccion;
    }

    public AccountEmployee getCuentaEmpleado() {
        return CuentaEmpleado;
    }

    public void setCuentaEmpleado(AccountEmployee cuentaEmpleado) {
        CuentaEmpleado = cuentaEmpleado;
    }

    public AccountClient getCuentaCliente() {
        return CuentaCliente;
    }

    public void setCuentaCliente(AccountClient cuentaCliente) {
        CuentaCliente = cuentaCliente;
    }

    public AccountCompany getCuentaCompania() {
        return CuentaCompania;
    }

    public void setCuentaCompania(AccountCompany cuentaCompania) {
        CuentaCompania = cuentaCompania;
    }

    public ChargingStation getTerminal() {
        return Terminal;
    }

    public void setTerminal(ChargingStation terminal) {
        Terminal = terminal;
    }

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", direccion='" + direccion + '\'' +
                '}';
    }
}
