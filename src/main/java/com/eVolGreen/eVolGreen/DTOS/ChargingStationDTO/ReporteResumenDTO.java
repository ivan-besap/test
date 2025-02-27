package com.eVolGreen.eVolGreen.DTOS.ChargingStationDTO;

import java.util.Map;

public class ReporteResumenDTO {
    private Long empresaId;
    private Map<String, Integer> costosMensuales;
    private Integer costoTotalAcumulado;
    private Integer ingresoDiario;
    private Double porcentajeIngresoDiario;
    private Double porcentajeIngresoMensual;
    private Double porcentajeIngresoAcumulado;
    private String promedioDiario;

    private Double porcentajeTiempoDiario;
    private Double porcentajeTiempoMensual;
    private Double porcentajeTiempoAnual;

    private String promedioAnualNuevo;

    private Map<String, String> tiemposPromediosMesNuevo;

    public ReporteResumenDTO(Long empresaId, Map<String, Integer> costosMensuales, int costoTotalAcumulado,
                             int ingresoDiario, double porcentajeIngresoDiario, double porcentajeIngresoMensual,
                             double porcentajeIngresoAcumulado, String promedioDiario
                                , double porcentajeTiempoDiario, double porcentajeTiempoMensual,
                             double porcentajeTiempoAnual, Map<String, String> tiemposPromediosMesNuevo, String promedioAnualNuevo) {
        this.empresaId = empresaId;
        this.costosMensuales = costosMensuales;
        this.costoTotalAcumulado = costoTotalAcumulado;
        this.ingresoDiario = ingresoDiario;
        this.porcentajeIngresoDiario = porcentajeIngresoDiario;
        this.porcentajeIngresoMensual = porcentajeIngresoMensual;
        this.porcentajeIngresoAcumulado = porcentajeIngresoAcumulado;
        this.promedioDiario = promedioDiario;
        this.porcentajeTiempoDiario = porcentajeTiempoDiario;
        this.porcentajeTiempoMensual = porcentajeTiempoMensual;
        this.porcentajeTiempoAnual = porcentajeTiempoAnual;
        this.tiemposPromediosMesNuevo = tiemposPromediosMesNuevo;
        this.promedioAnualNuevo = promedioAnualNuevo;
    }

    public Long getEmpresaId() {
        return empresaId;
    }

    public Map<String, Integer> getCostosMensuales() {
        return costosMensuales;
    }

    public Integer getCostoTotalAcumulado() {
        return costoTotalAcumulado;
    }

    public Integer getIngresoDiario() {
        return ingresoDiario;
    }

    public Double getPorcentajeIngresoDiario() {
        return porcentajeIngresoDiario;
    }

    public Double getPorcentajeIngresoMensual() {
        return porcentajeIngresoMensual;
    }

    public Double getPorcentajeIngresoAcumulado() {
        return porcentajeIngresoAcumulado;
    }

    public String getPromedioDiario() {
        return promedioDiario;
    }

    public Double getPorcentajeTiempoDiario() {
        return porcentajeTiempoDiario;
    }

    public Double getPorcentajeTiempoMensual() {
        return porcentajeTiempoMensual;
    }

    public Double getPorcentajeTiempoAnual() {
        return porcentajeTiempoAnual;
    }

    public Map<String, String> getTiemposPromediosMesNuevo() {
        return tiemposPromediosMesNuevo;
    }

    public void setTiemposPromediosMesNuevo(Map<String, String> tiemposPromediosMesNuevo) {
        this.tiemposPromediosMesNuevo = tiemposPromediosMesNuevo;
    }

    public String getPromedioAnualNuevo() {
        return promedioAnualNuevo;
    }

    public void setPromedioAnualNuevo(String promedioAnualNuevo) {
        this.promedioAnualNuevo = promedioAnualNuevo;
    }
}
