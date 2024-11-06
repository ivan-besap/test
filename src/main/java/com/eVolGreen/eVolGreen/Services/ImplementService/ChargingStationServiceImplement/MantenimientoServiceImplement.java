package com.eVolGreen.eVolGreen.Services.ImplementService.ChargingStationServiceImplement;

import com.eVolGreen.eVolGreen.DTOS.ChargingStationDTO.ChargerDTO.MantenimientoDTO;
import com.eVolGreen.eVolGreen.Models.ChargingStation.Charger.Charger;
import com.eVolGreen.eVolGreen.Models.ChargingStation.Charger.Mantenimiento;
import com.eVolGreen.eVolGreen.Repositories.ChargerRepository;
import com.eVolGreen.eVolGreen.Repositories.MantenimientoRepository;
import com.eVolGreen.eVolGreen.Services.ChargingStationService.MantenimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MantenimientoServiceImplement implements MantenimientoService {

    @Autowired
    private MantenimientoRepository mantenimientoRepository;

    @Autowired
    private ChargerRepository chargerRepository;

    @Override
    public Mantenimiento findById(Long id) {
        return mantenimientoRepository.findById(id).orElse(null);
    }

    @Override
    public void saveMantenimiento(Mantenimiento mantenimiento) {
        mantenimientoRepository.save(mantenimiento);
    }

    @Override
    public void updateMantenimiento(Long id, MantenimientoDTO mantenimientoDTO) {
        Mantenimiento mantenimiento = findById(id);
        if (mantenimiento != null) {
            mantenimiento.setDescripcion(mantenimientoDTO.getDescripcion());
            mantenimiento.setFechaInicial(mantenimientoDTO.getFechaInicial());
            mantenimiento.setFechaFinal(mantenimientoDTO.getFechaFinal());
            mantenimiento.setHorarioInicio(mantenimientoDTO.getHorarioInicio());
            mantenimiento.setHorarioFin(mantenimientoDTO.getHorarioFin());
            mantenimiento.setDiasDeLaSemana(mantenimientoDTO.getDiasDeLaSemana());
            // Asocia cargador si es necesario
            mantenimientoRepository.save(mantenimiento);
        }
    }

    @Override
    public void deleteMantenimiento(Long id) {
        mantenimientoRepository.deleteById(id);
    }

    @Override
    public List<MantenimientoDTO> getMantenimientosByCargador(Long cargadorId) {
        return mantenimientoRepository.findByCargadorId(cargadorId).stream()
                .map(MantenimientoDTO::new) // Utiliza el constructor de MantenimientoDTO que acepta un Mantenimiento
                .collect(Collectors.toList());
    }

    @Override
    public List<Mantenimiento> findAllMantenimientos() {
        return mantenimientoRepository.findAll();
    }

    @Override
    public boolean asignarMantenimientoACargador(Long cargadorId, Long mantenimientoId) {
        Optional<Charger> cargadorOpt = chargerRepository.findById(cargadorId);
        Optional<Mantenimiento> mantenimientoOpt = mantenimientoRepository.findById(mantenimientoId);

        if (cargadorOpt.isPresent() && mantenimientoOpt.isPresent()) {
            Charger cargador = cargadorOpt.get();
            Mantenimiento nuevoMantenimiento = mantenimientoOpt.get();

            // Verificar si el cargador ya tiene un mantenimiento asignado
            if (!cargador.getMantenimientos().isEmpty()) {
                // Desvincular el mantenimiento anterior
                for (Mantenimiento mantenimiento : cargador.getMantenimientos()) {
                    mantenimiento.setCargador(null);
                    mantenimientoRepository.save(mantenimiento); // Actualizamos el mantenimiento anterior para desvincularlo
                }
//                cargador.getMantenimientos().clear(); // Limpiar la lista de mantenimientos
            }

            // Asignar el nuevo mantenimiento
            nuevoMantenimiento.setCargador(cargador);
            cargador.getMantenimientos().add(nuevoMantenimiento);

            // Guardar los cambios
            chargerRepository.save(cargador);
            mantenimientoRepository.save(nuevoMantenimiento);

            return true;
        }

        return false;
    }

    public List<Mantenimiento> findByEmpresa(Long empresaId) {
        return mantenimientoRepository.findByEmpresaId(empresaId);
    }
}

