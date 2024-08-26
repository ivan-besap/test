package com.eVolGreen.eVolGreen.Models.Account.Transaction;

import com.eVolGreen.eVolGreen.Models.ChargingStation.ChargingStation;
import com.eVolGreen.eVolGreen.Models.Account.TypeOfAccount.AccountClient;
import com.eVolGreen.eVolGreen.Models.Account.TypeOfAccount.AccountCompany;
import com.eVolGreen.eVolGreen.Models.Account.TypeOfAccount.AccountEmployee;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Transaction {

    @Id
    @GenericGenerator(name= "native", strategy = "native")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    private long id;

    @NotNull(message = "El tipo de transacción es obligatorio")
    private TransactionType TipoTransaction;

    @NotNull(message = "La descripción es obligatoria")
    private String Descripcion;

    @NotNull(message = "La fecha de creación es obligatoria")
    private LocalDateTime FechaCreacion;

    @NotNull(message = "La hora de inicio es obligatoria")
    private LocalDateTime HoraInicio;

    @NotNull(message = "La hora de fin es obligatoria")
    private LocalDateTime HoraFin;

    @NotNull(message = "La energia entregada es obligatoria")
    private BigDecimal EnergiaEntregada;

    @NotNull(message = "El costo es obligatorio")
    private Integer Costo;

    @NotNull(message = "La Cuenta de la Compañia es obligatoria")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CuentaCompañia_id")
    @JsonBackReference ("Transacciones-CuentaCompañia")
    private AccountCompany CuentaCompañia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CuentaCliente_id")
    @JsonBackReference ("CuentaCliente-Transaccion")
    private AccountClient CuentaCliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CuentaTrabajador_id")
    @JsonBackReference ("Transacciones-CuentaTrabajador")
    private AccountEmployee CuentaTrabajador;

    @NotNull(message = "El Terminal es obligatorio")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Terminal_id")
    @JsonBackReference ("Terminal-Transaction")
    private ChargingStation Terminal;

    public Transaction() {
    }

    public Transaction(long id, TransactionType tipoTransaction, String descripcion, LocalDateTime fechaCreacion, LocalDateTime horaInicio, LocalDateTime horaFin, BigDecimal energiaEntregada, Integer costo, AccountCompany cuentaCompañia, ChargingStation terminal) {
        this.id = id;
        TipoTransaction = tipoTransaction;
        Descripcion = descripcion;
        FechaCreacion = fechaCreacion;
        HoraInicio = horaInicio;
        HoraFin = horaFin;
        EnergiaEntregada = energiaEntregada;
        Costo = costo;
        CuentaCompañia = cuentaCompañia;
        Terminal = terminal;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public @NotNull(message = "El tipo de transacción es obligatorio") TransactionType getTipoTransaction() {
        return TipoTransaction;
    }

    public void setTipoTransaction(@NotNull(message = "El tipo de transacción es obligatorio") TransactionType tipoTransaction) {
        TipoTransaction = tipoTransaction;
    }

    public @NotNull(message = "La descripción es obligatoria") String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(@NotNull(message = "La descripción es obligatoria") String descripcion) {
        Descripcion = descripcion;
    }

    public @NotNull(message = "La fecha de creación es obligatoria") LocalDateTime getFechaCreacion() {
        return FechaCreacion;
    }

    public void setFechaCreacion(@NotNull(message = "La fecha de creación es obligatoria") LocalDateTime fechaCreacion) {
        FechaCreacion = fechaCreacion;
    }

    public @NotNull(message = "La hora de inicio es obligatoria") LocalDateTime getHoraInicio() {
        return HoraInicio;
    }

    public void setHoraInicio(@NotNull(message = "La hora de inicio es obligatoria") LocalDateTime horaInicio) {
        HoraInicio = horaInicio;
    }

    public @NotNull(message = "La hora de fin es obligatoria") LocalDateTime getHoraFin() {
        return HoraFin;
    }

    public void setHoraFin(@NotNull(message = "La hora de fin es obligatoria") LocalDateTime horaFin) {
        HoraFin = horaFin;
    }

    public @NotNull(message = "La energia entregada es obligatoria") BigDecimal getEnergiaEntregada() {
        return EnergiaEntregada;
    }

    public void setEnergiaEntregada(@NotNull(message = "La energia entregada es obligatoria") BigDecimal energiaEntregada) {
        EnergiaEntregada = energiaEntregada;
    }

    public @NotNull(message = "El costo es obligatorio") Integer getCosto() {
        return Costo;
    }

    public void setCosto(@NotNull(message = "El costo es obligatorio") Integer costo) {
        Costo = costo;
    }

    public @NotNull(message = "La Cuenta de la Compañia es obligatoria") AccountCompany getCuentaCompañia() {
        return CuentaCompañia;
    }

    public void setCuentaCompañia(@NotNull(message = "La Cuenta de la Compañia es obligatoria") AccountCompany cuentaCompañia) {
        CuentaCompañia = cuentaCompañia;
    }

    public AccountClient getCuentaCliente() {
        return CuentaCliente;
    }

    public void setCuentaCliente(AccountClient cuentaCliente) {
        CuentaCliente = cuentaCliente;
    }

    public AccountEmployee getCuentaTrabajador() {
        return CuentaTrabajador;
    }

    public void setCuentaTrabajador(AccountEmployee cuentaTrabajador) {
        CuentaTrabajador = cuentaTrabajador;
    }

    public @NotNull(message = "El Terminal es obligatorio") ChargingStation getTerminal() {
        return Terminal;
    }

    public void setTerminal(@NotNull(message = "El Terminal es obligatorio") ChargingStation terminal) {
        Terminal = terminal;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", TipoTransaction=" + TipoTransaction +
                ", Descripcion='" + Descripcion + '\'' +
                ", FechaCreacion=" + FechaCreacion +
                ", HoraInicio=" + HoraInicio +
                ", HoraFin=" + HoraFin +
                ", EnergiaEntregada=" + EnergiaEntregada +
                ", Costo=" + Costo +
                ", CuentaCompañia=" + CuentaCompañia +
                ", CuentaCliente=" + CuentaCliente +
                ", CuentaTrabajador=" + CuentaTrabajador +
                ", Terminal=" + Terminal +
                '}';
    }
}
