package com.eVolGreen.eVolGreen.Models.ChargingStation;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class DatosReportesEnergia {
    @Id
    @GenericGenerator(name = "native", strategy = "native")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
    private Long id;

    private int mes;  // Mes en formato numérico (1-12)
    private int ano;  // Año
    private int energia;  // Ingresos acumulados del mes
    private Long empresaId;

    // Constructor vacío
    public DatosReportesEnergia() {}

    // Constructor con parámetros
    public DatosReportesEnergia(int mes, int ano, int energia, Long empresaId) {
        this.mes = mes;
        this.ano = ano;
        this.energia = energia;
        this.empresaId = empresaId;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public int getMes() { return mes; }
    public void setMes(int mes) { this.mes = mes; }

    public int getAno() { return ano; }
    public void setAno(int ano) { this.ano = ano; }

    public int getEnergia() { return energia; }
    public void setEnergia(int energia) { this.energia = energia; }

    public Long getEmpresaId() { return empresaId; }
    public void setEmpresaId(Long empresaId) { this.empresaId = empresaId; }
}

