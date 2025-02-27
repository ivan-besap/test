package com.eVolGreen.eVolGreen.Services.ImplementService.ChargingStationServiceImplement;

import com.eVolGreen.eVolGreen.DTOS.ChargingStationDTO.ChargerDTO.MantenimientoDTO;
import com.eVolGreen.eVolGreen.Models.Account.Account;
import com.eVolGreen.eVolGreen.Models.Account.Empresa;
import com.eVolGreen.eVolGreen.Models.ChargingStation.Charger.Charger;
import com.eVolGreen.eVolGreen.Models.ChargingStation.Charger.Mantenimiento;
import com.eVolGreen.eVolGreen.Models.ChargingStation.ChargerStatus;
import com.eVolGreen.eVolGreen.Models.ChargingStation.Connector.ConnectorStatus;
import com.eVolGreen.eVolGreen.Repositories.ChargerRepository;
import com.eVolGreen.eVolGreen.Repositories.ConnectorRepository;
import com.eVolGreen.eVolGreen.Repositories.MantenimientoRepository;
import com.eVolGreen.eVolGreen.Services.AccountService.AccountService;
import com.eVolGreen.eVolGreen.Services.ChargingStationService.MantenimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MantenimientoServiceImplement implements MantenimientoService {

    @Autowired
    private MantenimientoRepository mantenimientoRepository;

    @Autowired
    private ChargerRepository chargerRepository;

    @Autowired
    private ConnectorRepository connectorRepository;

    @Autowired
    private AccountService accountService;


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

    @Scheduled(cron = "0 */3 * * * *")
    public void verificarMantenimientos() {
        LocalDate hoy = LocalDate.now();
        LocalTime horaActual = LocalTime.now();

        // Aquí debes obtener la empresa del usuario actual o configurarla de alguna forma

        // Filtrar solo los mantenimientos de la empresa que tengan cargador asignado
        List<Mantenimiento> mantenimientos = mantenimientoRepository.findWithCargadorAndConectores();

        for (Mantenimiento mantenimiento : mantenimientos) {
            Charger cargador = mantenimiento.getCargador();

            if (cargador == null) {
                continue;
            }

            if ((hoy.isAfter(mantenimiento.getFechaInicial()) || hoy.isEqual(mantenimiento.getFechaInicial())) &&
                    (hoy.isBefore(mantenimiento.getFechaFinal()) || hoy.isEqual(mantenimiento.getFechaFinal())) &&
                    (horaActual.isAfter(mantenimiento.getHorarioInicio()) || horaActual.equals(mantenimiento.getHorarioInicio())) &&
                    (horaActual.isBefore(mantenimiento.getHorarioFin()) || horaActual.equals(mantenimiento.getHorarioFin()))
            ) {
                // Si está en mantenimiento, cambiar el estado a EN_MANTENIMIENTO
                cargador.setEstadoCargador(ChargerStatus.CONSTRUCTION);
                cargador.getConectores().forEach(conector -> {
                    conector.setEstadoConector(ConnectorStatus.DISCONNECTED);
                    connectorRepository.save(conector);
                });
                chargerRepository.save(cargador);
            } else {
                cargador.setEstadoCargador(ChargerStatus.ACTIVE);
                cargador.getConectores().forEach(conector -> {
                    conector.setEstadoConector(ConnectorStatus.CONNECTED);
                    connectorRepository.save(conector);
                });
                // Desvincular el mantenimiento del cargador
                mantenimiento.setCargador(null);
                mantenimientoRepository.save(mantenimiento);
            }
            chargerRepository.save(cargador);
        }
    }
}

