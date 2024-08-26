package com.eVolGreen.eVolGreen.DTOS.AccountDTO;

import com.eVolGreen.eVolGreen.Models.Account.Transaction.Transaction;
import com.eVolGreen.eVolGreen.Models.Account.Transaction.TransactionType;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionDTO {

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
    private long CuentaCompañia;

    private long CuentaCliente;

    private long CuentaTrabajador;

    @NotNull(message = "El Terminal es obligatorio")
    private long Terminal;

    public TransactionDTO(Transaction Transaccion) {

        id = Transaccion.getId();
        TipoTransaction = Transaccion.getTipoTransaction();
        Descripcion = Transaccion.getDescripcion();
        FechaCreacion = Transaccion.getFechaCreacion();
        HoraInicio = Transaccion.getHoraInicio();
        HoraFin = Transaccion.getHoraFin();
        EnergiaEntregada = Transaccion.getEnergiaEntregada();
        Costo = Transaccion.getCosto();
        CuentaCompañia = Transaccion.getCuentaCompañia().getId();
        CuentaCliente = Transaccion.getCuentaCliente().getId();
        CuentaTrabajador = Transaccion.getCuentaTrabajador().getId();
        Terminal = Transaccion.getTerminal().getId();
    }

    public long getId() {
        return id;
    }

    public @NotNull(message = "El tipo de transacción es obligatorio") TransactionType getTipoTransaction() {
        return TipoTransaction;
    }

    public @NotNull(message = "La descripción es obligatoria") String getDescripcion() {
        return Descripcion;
    }

    public @NotNull(message = "La fecha de creación es obligatoria") LocalDateTime getFechaCreacion() {
        return FechaCreacion;
    }

    public @NotNull(message = "La hora de inicio es obligatoria") LocalDateTime getHoraInicio() {
        return HoraInicio;
    }

    public @NotNull(message = "La hora de fin es obligatoria") LocalDateTime getHoraFin() {
        return HoraFin;
    }

    public @NotNull(message = "La energia entregada es obligatoria") BigDecimal getEnergiaEntregada() {
        return EnergiaEntregada;
    }

    public @NotNull(message = "El costo es obligatorio") Integer getCosto() {
        return Costo;
    }

    @NotNull(message = "La Cuenta de la Compañia es obligatoria")
    public long getCuentaCompañia() {
        return CuentaCompañia;
    }

    public long getCuentaCliente() {
        return CuentaCliente;
    }

    public long getCuentaTrabajador() {
        return CuentaTrabajador;
    }

    @NotNull(message = "El Terminal es obligatorio")
    public long getTerminal() {
        return Terminal;
    }

    @Override
    public String toString() {
        return "TransactionDTO{" +
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
