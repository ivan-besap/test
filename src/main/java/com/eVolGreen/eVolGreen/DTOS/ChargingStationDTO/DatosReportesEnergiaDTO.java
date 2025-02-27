package com.eVolGreen.eVolGreen.DTOS.ChargingStationDTO;

public class DatosReportesEnergiaDTO {
    private int mes;  // Mes en formato numérico (1-12)
    private int ano;  // Año
    private int energia;  // Ingresos acumulados del mes

    public DatosReportesEnergiaDTO() {
    }

    // Constructor con parámetros
    public DatosReportesEnergiaDTO(int mes, int ano, int energia) {
        this.mes = mes;
        this.ano = ano;
        this.energia = energia;

    }

    // Getters y Setters

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {this.mes = mes;}

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {this.ano = ano;}

    public int getEnergia() {
        return energia;
    }

    public void setEnergia(int energia) {
        this.energia = energia;
    }
}
