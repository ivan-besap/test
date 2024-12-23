package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6;


import jakarta.persistence.*;

import java.time.LocalDateTime;

/**
 * Entidad que representa la tabla `diagnostics_files`.
 */
@Entity
@Table(name = "diagnostics_files")
public class DiagnosticsFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    // LONGBLOB en MySQL puede mapearse a un array de bytes
    @Lob
    @Column(name = "file_data", nullable = false)
    private byte[] fileData;

    public DiagnosticsFile() {
    }

    public DiagnosticsFile(LocalDateTime createdAt, byte[] fileData) {
        this.createdAt = createdAt;
        this.fileData = fileData;
    }

    // Getters y Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public byte[] getFileData() {
        return fileData;
    }

    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }
}
