package com.eVolGreen.eVolGreen.DTOS.ChargingStationDTO;

import java.time.ZonedDateTime;

public class DatosReportesDTO {

    private int mes;  // Mes en formato numérico (1-12)
    private int ano;  // Año
    private int ingreso;  // Ingresos acumulados del mes

    private String estacion;

    public DatosReportesDTO() {
    }

    // Constructor con parámetros
    public DatosReportesDTO(String estacion, int mes, int ano, int ingreso) {
        this.estacion = estacion;
        this.mes = mes;
        this.ano = ano;
        this.ingreso = ingreso;

    }

    // Getters y Setters

    public String getEstacion() {
        return estacion;
    }

    public void setEstacion(String estacion) {
        this.estacion = estacion;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {this.mes = mes;}

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {this.ano = ano;}

    public int getIngreso() {
        return ingreso;
    }

    public void setIngreso(int ingreso) {
        this.ingreso = ingreso;
    }

}
