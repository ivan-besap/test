package com.eVolGreen.eVolGreen.Models.ChargingStation;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class DatosReportesTiempo {
    @Id
    @GenericGenerator(name = "native", strategy = "native")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
    private Long id;

    private int mes;  // Mes en formato numérico (1-12)
    private int ano;  // Año
    private String tiempo;  // Ingresos acumulados del mes
    private Long empresaId;

    // Constructor vacío
    public DatosReportesTiempo
    () {}

    // Constructor con parámetros
    public DatosReportesTiempo(int mes, int ano, String tiempo, Long empresaId) {
        this.mes = mes;
        this.ano = ano;
        this.tiempo = tiempo;
        this.empresaId = empresaId;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public int getMes() { return mes; }
    public void setMes(int mes) { this.mes = mes; }

    public int getAno() { return ano; }
    public void setAno(int ano) { this.ano = ano; }

    public String getTiempo() { return tiempo; }
    public void setTiempo(String tiempo) { this.tiempo = tiempo; }

    public Long getEmpresaId() { return empresaId; }
    public void setEmpresaId(Long empresaId) { this.empresaId = empresaId; }
}

