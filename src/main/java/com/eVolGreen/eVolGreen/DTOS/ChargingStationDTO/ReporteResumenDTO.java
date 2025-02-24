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
    private Map<String, String> promedioMensualPorMes;
    private String promedioAnual;

    private Double porcentajeTiempoDiario;
    private Double porcentajeTiempoMensual;
    private Double porcentajeTiempoAnual;

    public ReporteResumenDTO(Long empresaId, Map<String, Integer> costosMensuales, int costoTotalAcumulado,
                             int ingresoDiario, double porcentajeIngresoDiario, double porcentajeIngresoMensual,
                             double porcentajeIngresoAcumulado, String promedioDiario, Map<String, String> promedioMensualPorMes,
                                String promedioAnual, double porcentajeTiempoDiario, double porcentajeTiempoMensual,
                             double porcentajeTiempoAnual) {
        this.empresaId = empresaId;
        this.costosMensuales = costosMensuales;
        this.costoTotalAcumulado = costoTotalAcumulado;
        this.ingresoDiario = ingresoDiario;
        this.porcentajeIngresoDiario = porcentajeIngresoDiario;
        this.porcentajeIngresoMensual = porcentajeIngresoMensual;
        this.porcentajeIngresoAcumulado = porcentajeIngresoAcumulado;
        this.promedioDiario = promedioDiario;
        this.promedioMensualPorMes = promedioMensualPorMes;
        this.promedioAnual = promedioAnual;
        this.porcentajeTiempoDiario = porcentajeTiempoDiario;
        this.porcentajeTiempoMensual = porcentajeTiempoMensual;
        this.porcentajeTiempoAnual = porcentajeTiempoAnual;
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

    public Map<String, String> getPromedioMensualPorMes() {
        return promedioMensualPorMes;
    }

    public String getPromedioAnual() {
        return promedioAnual;
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
}
