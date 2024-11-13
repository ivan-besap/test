package com.eVolGreen.eVolGreen.DTOS.AccountDTO.CarDTO;

import com.eVolGreen.eVolGreen.Models.Account.Car.Flota;

import java.util.Set;
import java.util.stream.Collectors;

public class FlotaDTO {

    private Long id;
    private String nombreFlota;
    private Boolean activo;
    private Long empresaId;
    private Set<AutoDTO> autos;

    // Constructor vacío
    public FlotaDTO() {}

    // Constructor que acepta un objeto Flota y mapea sus propiedades
    public FlotaDTO(Flota flota) {
        this.id = flota.getId();
        this.nombreFlota = flota.getNombreFlota();
        this.activo = flota.getActivo();
        this.empresaId = flota.getEmpresa() != null ? flota.getEmpresa().getId() : null;
        this.autos = flota.getAutos().stream()
                .map(auto -> new AutoDTO(auto.getId(), auto.getPatente()))
                .collect(Collectors.toSet());
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreFlota() {
        return nombreFlota;
    }

    public void setNombreFlota(String nombreFlota) {
        this.nombreFlota = nombreFlota;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public Long getEmpresaId() {
        return empresaId;
    }

    public void setEmpresaId(Long empresaId) {
        this.empresaId = empresaId;
    }

    public Set<AutoDTO> getAutos() {
        return autos;
    }

    public void setAutos(Set<AutoDTO> autos) {
        this.autos = autos;
    }

    // Clase interna AutoDTO para representar los autos asociados a la flota
    public static class AutoDTO {
        private Long id;
        private String patente;

        public AutoDTO(Long id, String patente) {
            this.id = id;
            this.patente = patente;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getPatente() {
            return patente;
        }

        public void setPatente(String patente) {
            this.patente = patente;
        }
    }
}