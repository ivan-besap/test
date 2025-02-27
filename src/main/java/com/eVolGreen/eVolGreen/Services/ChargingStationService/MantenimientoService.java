package com.eVolGreen.eVolGreen.Services.ChargingStationService;

import com.eVolGreen.eVolGreen.DTOS.ChargingStationDTO.ChargerDTO.MantenimientoDTO;
import com.eVolGreen.eVolGreen.Models.Account.Empresa;
import com.eVolGreen.eVolGreen.Models.ChargingStation.Charger.Mantenimiento;

import java.util.List;

public interface MantenimientoService {
    Mantenimiento findById(Long id);
    void saveMantenimiento(Mantenimiento mantenimiento);
    void updateMantenimiento(Long id, MantenimientoDTO mantenimientoDTO);
    void deleteMantenimiento(Long id);
    List<MantenimientoDTO> getMantenimientosByCargador(Long cargadorId);

    List<Mantenimiento> findAllMantenimientos();

    boolean asignarMantenimientoACargador(Long cargadorId, Long mantenimientoId);

    List<Mantenimiento> findByEmpresa(Long empresaId);
    void verificarMantenimientos();


}
