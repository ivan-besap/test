package com.eVolGreen.eVolGreen.Services.ImplementService.ChargingStationServiceImplement;

import com.eVolGreen.eVolGreen.DTOS.ChargingStationDTO.ChargerDTO.ReporteListDTO;
import com.eVolGreen.eVolGreen.DTOS.ChargingStationDTO.ReporteDTO;
import com.eVolGreen.eVolGreen.DTOS.ChargingStationDTO.ReporteResponseDTO;
import com.eVolGreen.eVolGreen.Models.Account.Account;
import com.eVolGreen.eVolGreen.Models.Account.Empresa;
import com.eVolGreen.eVolGreen.Models.ChargingStation.Charger.Charger;
import com.eVolGreen.eVolGreen.Models.ChargingStation.ChargingStation;
import com.eVolGreen.eVolGreen.Models.ChargingStation.Connector.Connector;
import com.eVolGreen.eVolGreen.Models.ChargingStation.Reporte;
import com.eVolGreen.eVolGreen.Repositories.ReporteRepository;
import com.eVolGreen.eVolGreen.Services.AccountService.AccountService;
import com.eVolGreen.eVolGreen.Services.AccountService.EmpresaService;
import com.eVolGreen.eVolGreen.Services.ChargingStationService.ChargerService;
import com.eVolGreen.eVolGreen.Services.ChargingStationService.ChargingStationsService;
import com.eVolGreen.eVolGreen.Services.ChargingStationService.ConnectorService;
import com.eVolGreen.eVolGreen.Services.ChargingStationService.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReporteServiceImplement implements ReporteService {

    @Autowired
    private ReporteRepository reporteRepository;

    @Autowired
    private ChargingStationsService chargingStationsService;

    @Autowired
    private ChargerService chargerService;

    @Autowired
    private ConnectorService connectorService;

    @Autowired
    private EmpresaService empresaService;

    @Autowired
    private AccountService accountService;


    @Override
    public List<ReporteDTO> getAllReportes() {
        return reporteRepository.findAll().stream()
                .map(reporte -> new ReporteDTO(
                        reporte.getId(),
                        reporte.getChargingStation().getId(),
                        reporte.getCharger().getoCPPid(),
                        reporte.getConnector().getId(),
                        reporte.getEmpresa().getId(),
                        reporte.getEnergia(),
                        reporte.getTiempo(),
                        reporte.getFechaCreacion(),
                        reporte.getInicioCarga(),
                        reporte.getFinCarga(),
                        reporte.getCosto(),
                        reporte.getDescripcion(),
                        reporte.getAccount().getId(),
                        reporte.getDeviceIdentifier().getId()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public ReporteDTO getReporteById(Long id) {
        Reporte reporte = reporteRepository.findById(id).orElse(null);
        if (reporte != null) {
            return new ReporteDTO(
                    reporte.getId(),
                    reporte.getChargingStation().getId(),
                    reporte.getCharger().getoCPPid(),
                    reporte.getConnector().getId(),
                    reporte.getEmpresa().getId(),
                    reporte.getEnergia(),
                    reporte.getTiempo(),
                    reporte.getFechaCreacion(),
                    reporte.getInicioCarga(),
                    reporte.getFinCarga(),
                    reporte.getCosto(),
                    reporte.getDescripcion(),
                    reporte.getAccount().getId(),
                    reporte.getDeviceIdentifier().getId()
            );
        }
        return null;
    }

    public Long createReporte(ReporteDTO reporteDTO) {
        Reporte reporte = new Reporte();
        ChargingStation chargingStation = chargingStationsService.findById(reporteDTO.getChargingStationId());
        Charger charger = chargerService.findByOCPPid(reporteDTO.getChargerId());
        Connector connector = connectorService.findById(reporteDTO.getConnectorId());
        Empresa empresa = empresaService.findById(reporteDTO.getEmpresaId());

        reporte.setChargingStation(chargingStation);
        reporte.setCharger(charger);
        reporte.setConnector(connector);
        reporte.setEmpresa(empresa);

        if (reporteDTO.getAccountId() != null) {
            Account account = accountService.findById(reporteDTO.getAccountId());
            reporte.setAccount(account);
        }

        reporte.setFechaCreacion(LocalDateTime.now());
        reporte.setInicioCarga(ZonedDateTime.now());
        // Otros campos iniciales si es necesario

        reporte = reporteRepository.save(reporte);
        return reporte.getId();
    }

    public boolean updateReporte(Long id, ReporteDTO reporteDTO) {
        Optional<Reporte> optionalReporte = reporteRepository.findById(id);
        if (optionalReporte.isPresent()) {
            Reporte reporte = optionalReporte.get();
            reporte.setFinCarga(ZonedDateTime.now());
            reporte.setTiempo(reporteDTO.getTiempo());
            reporte.setCosto(reporteDTO.getCosto());
            reporte.setEnergia(reporteDTO.getEnergia());
            reporteRepository.save(reporte);
            return true;
        }
        return false;
    }

    public List<ReporteResponseDTO> getReportesByEmpresa(Long empresaId) {
        List<Reporte> reportes = reporteRepository.findByEmpresaId(empresaId);

        return reportes.stream()
                .sorted((r1, r2) -> r2.getInicioCarga().compareTo(r1.getInicioCarga())) // Ordenar por inicioCarga (descendente)
                .map(reporte -> {
                    ReporteResponseDTO dto = new ReporteResponseDTO();
                    dto.setEstacionDeCarga(reporte.getChargingStation().getNombreTerminal());
                    dto.setIdCargador(reporte.getCharger().getoCPPid());
                    dto.setConector(reporte.getConnector().getNConector().toString());
                    dto.setInicioCarga(reporte.getInicioCarga());
                    dto.setFinCarga(reporte.getFinCarga());
                    dto.setUsuario(reporte.getAccount().getNombre() + " " + reporte.getAccount().getApellidoPaterno());
                    dto.setEnergia(reporte.getEnergia().toString());
                    dto.setTiempo(reporte.getTiempo());
                    dto.setCosto(reporte.getCosto());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public void saveReporte(Reporte reporte) {
        reporteRepository.save(reporte);
    }

    @Override
    public void deleteReporte(Long id) {
        reporteRepository.deleteById(id);
    }

    @Override
    public List<ReporteListDTO> getAllReportesGroupedByCharger() {
        List<Reporte> reportes = reporteRepository.findAllGroupedByChargerAndSorted();

        return reportes.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private ReporteListDTO convertToDTO(Reporte reporte) {
        ReporteListDTO dto = new ReporteListDTO();
        dto.setId(reporte.getId());
        dto.setFechaCreacion(reporte.getFechaCreacion());
        dto.setEstacionDeCarga(reporte.getChargingStation().getNombreTerminal()); // Ajusta según tu entidad ChargingStation
        dto.setCargador(reporte.getCharger().getNombre()); // Ajusta según tu entidad Charger
        dto.setConector(reporte.getConnector().getTipoConector().getNombre()); // Ajusta según tu entidad Connector
        dto.setChargePointId(reporte.getCharger().getoCPPid()); // Ajusta según tu entidad Charger
        dto.setModelo(reporte.getCharger().getModel().getName()); // Ajusta según tu entidad Charger
        dto.setTipo(reporte.getConnector().getTipoConector().getNombre()); // Ajusta según tu entidad Connector
        dto.setEstado(reporte.getActivo() ? "Activo" : "Inactivo");

        return dto;
    }
}

