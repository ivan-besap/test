package com.eVolGreen.eVolGreen.Models.Account.Fee;

import com.eVolGreen.eVolGreen.Models.Account.TypeOfAccount.AccountClient;
import com.eVolGreen.eVolGreen.Models.Account.TypeOfAccount.AccountCompany;
import com.eVolGreen.eVolGreen.Models.Account.TypeOfAccount.AccountEmployee;
import com.eVolGreen.eVolGreen.Models.User.subclassUser.CompanyUser;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Fee {

    @Id
    @GenericGenerator(name= "native", strategy = "native")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    private long id;

    @NotNull(message = "El nombre de la tarifa es obligatorio")
    private String NombreTarifa;

    @NotNull(message = "La fecha de inicio es obligatoria")
    private LocalDate FechaInicio;

    @NotNull(message = "La fecha de fin es obligatoria")
    private LocalDate FechaFin;

    @NotNull(message = "La hora de inicio es obligatoria")
    private LocalTime HoraInicio;

    @NotNull(message = "La hora de fin es obligatoria")
    private LocalTime HoraFin;

    @NotNull(message = "Los dias de la semana son obligatorios")
    @ElementCollection
    @CollectionTable(name = "Tarifa_Dias", joinColumns = @JoinColumn(name = "Tarifa_id"))
    @Column(name = "DiasDeLaSemana")
    private Set<String> DiasDeLaSemana = new HashSet<>();

    @NotNull(message = "El precio de la tarifa es obligatorio")
    private BigDecimal PrecioTarifa;

//    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinTable(name = "Tarifa_Conector",
//            joinColumns = @JoinColumn(name = "Tarifa_id"),
//            inverseJoinColumns = @JoinColumn(name = "Conector_id"))
//    @JsonBackReference("Conectores-Tarifas")
//    private Set<Connector> Conectores = new HashSet<>();

    @OneToMany(mappedBy = "Tarifa", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("Tarifa-TarifaConector")
    private Set<FeeConnector> TarifaConector = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Cliente_id")
    @JsonBackReference("CuentaCliente-Tarifa")
    private AccountClient CuentaCliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CuentaCompañia_id")
    @JsonBackReference("Tarifa-CuentaCompañia")
    private AccountCompany CuentaCompañia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CuentaTrabajador_id")
    @JsonBackReference("CuentaTrabajador-Tarifas")
    private AccountEmployee CuentaTrabajador;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Compañia_id")
    @JsonBackReference("Compañia-Tarifa")
    private CompanyUser Compañia;

    private Boolean activo = false;

    public Fee() {}

    public Fee(String NombreTarifa, LocalDate FechaInicio, LocalDate FechaFin, LocalTime HoraInicio, LocalTime HoraFin, Set<String> DiasDeLaSemana, BigDecimal PrecioTarifa, Boolean activo) {
        this.NombreTarifa = NombreTarifa;
        this.FechaInicio = FechaInicio;
        this.FechaFin = FechaFin;
        this.HoraInicio = HoraInicio;
        this.HoraFin = HoraFin;
        this.DiasDeLaSemana = DiasDeLaSemana;
        this.PrecioTarifa = PrecioTarifa;
        this.activo = activo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public @NotNull(message = "El nombre de la tarifa es obligatorio") String getNombreTarifa() {
        return NombreTarifa;
    }

    public void setNombreTarifa(@NotNull(message = "El nombre de la tarifa es obligatorio") String nombreTarifa) {
        NombreTarifa = nombreTarifa;
    }

    public @NotNull(message = "La fecha de inicio es obligatoria") LocalDate getFechaInicio() {
        return FechaInicio;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public void setFechaInicio(@NotNull(message = "La fecha de inicio es obligatoria") LocalDate fechaInicio) {
        FechaInicio = fechaInicio;
    }

    public @NotNull(message = "La fecha de fin es obligatoria") LocalDate getFechaFin() {
        return FechaFin;
    }

    public void setFechaFin(@NotNull(message = "La fecha de fin es obligatoria") LocalDate fechaFin) {
        FechaFin = fechaFin;
    }

    public @NotNull(message = "La hora de inicio es obligatoria") LocalTime getHoraInicio() {
        return HoraInicio;
    }

    public void setHoraInicio(@NotNull(message = "La hora de inicio es obligatoria") LocalTime horaInicio) {
        HoraInicio = horaInicio;
    }

    public @NotNull(message = "La hora de fin es obligatoria") LocalTime getHoraFin() {
        return HoraFin;
    }

    public void setHoraFin(@NotNull(message = "La hora de fin es obligatoria") LocalTime horaFin) {
        HoraFin = horaFin;
    }

    public @NotNull(message = "Los dias de la semana son obligatorios") Set<String> getDiasDeLaSemana() {
        return DiasDeLaSemana;
    }

    public void setDiasDeLaSemana(@NotNull(message = "Los dias de la semana son obligatorios") Set<String> diasDeLaSemana) {
        DiasDeLaSemana = diasDeLaSemana;
    }

    public @NotNull(message = "El precio de la tarifa es obligatorio") BigDecimal getPrecioTarifa() {
        return PrecioTarifa;
    }

    public void setPrecioTarifa(@NotNull(message = "El precio de la tarifa es obligatorio") BigDecimal precioTarifa) {
        PrecioTarifa = precioTarifa;
    }

    public Set<FeeConnector> getTarifaConector() {
        return TarifaConector;
    }

    public void setTarifaConector(Set<FeeConnector> tarifaConector) {
        TarifaConector = tarifaConector;
    }

    public AccountClient getCuentaCliente() {
        return CuentaCliente;
    }

    public void setCuentaCliente(AccountClient cuentaCliente) {
        CuentaCliente = cuentaCliente;
    }

    public AccountCompany getCuentaCompañia() {
        return CuentaCompañia;
    }

    public void setCuentaCompañia(AccountCompany cuentaCompañia) {
        CuentaCompañia = cuentaCompañia;
    }

    public AccountEmployee getCuentaTrabajador() {
        return CuentaTrabajador;
    }

    public void setCuentaTrabajador(AccountEmployee cuentaTrabajador) {
        CuentaTrabajador = cuentaTrabajador;
    }

    public CompanyUser getCompañia() {
        return Compañia;
    }

    public void setCompañia(CompanyUser compañia) {
        Compañia = compañia;
    }

    @Override
    public String toString() {
        return "Fee{" +
                "id=" + id +
                ", NombreTarifa='" + NombreTarifa + '\'' +
                ", FechaInicio=" + FechaInicio +
                ", FechaFin=" + FechaFin +
                ", HoraInicio=" + HoraInicio +
                ", HoraFin=" + HoraFin +
                ", DiasDeLaSemana=" + DiasDeLaSemana +
                ", PrecioTarifa=" + PrecioTarifa +
                ", TarifaConector=" + TarifaConector +
                ", CuentaCliente=" + CuentaCliente +
                ", CuentaCompañia=" + CuentaCompañia +
                ", CuentaTrabajador=" + CuentaTrabajador +
                ", Compañia=" + Compañia +
                '}';
    }
}
