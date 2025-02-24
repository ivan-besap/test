package com.eVolGreen.eVolGreen.Models.ChargingStation;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class DatosReportes {

    @Id
    @GenericGenerator(name = "native", strategy = "native")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
    private Long id;

    private int mes;  // Mes en formato numérico (1-12)
    private int ano;  // Año
    private int ingreso;  // Ingresos acumulados del mes

    // Constructor vacío
    public DatosReportes() {}

    // Constructor con parámetros
    public DatosReportes(int mes, int ano, int ingreso) {
        this.mes = mes;
        this.ano = ano;
        this.ingreso = ingreso;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public int getMes() { return mes; }
    public void setMes(int mes) { this.mes = mes; }

    public int getAno() { return ano; }
    public void setAno(int ano) { this.ano = ano; }

    public int getIngreso() { return ingreso; }
    public void setIngreso(int ingreso) { this.ingreso = ingreso; }
}
