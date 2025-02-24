package com.eVolGreen.eVolGreen.Services.ImplementService.ChargingStationServiceImplement;

import com.eVolGreen.eVolGreen.DTOS.ChargingStationDTO.ChargerDTO.ReporteListDTO;
import com.eVolGreen.eVolGreen.DTOS.ChargingStationDTO.ReporteDTO;
import com.eVolGreen.eVolGreen.DTOS.ChargingStationDTO.ReporteResponseDTO;
import com.eVolGreen.eVolGreen.DTOS.ChargingStationDTO.ReporteResumenDTO;
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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
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

    public ReporteResumenDTO obtenerReporteResumen(Long empresaId) {
        List<Reporte> reportes = reporteRepository.findByEmpresaId(empresaId);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM yyyy", new Locale("es", "ES"));
        DateTimeFormatter formatterDay = DateTimeFormatter.ofPattern("dd MMM yyyy", new Locale("es", "ES"));

        Map<String, Integer> costosMensuales = new HashMap<>();
        Map<String, Integer> ingresosDiarios = new HashMap<>();
        Map<String, List<Integer>> tiemposDiarios = new HashMap<>();
        Map<String, List<Integer>> tiemposMensuales = new HashMap<>();

        int costoTotalAnual = 0;
        int tiempoTotalAnual = 0;
        int cantidadCargasAnual = 0;

        LocalDate today = LocalDate.now();
        LocalDate yesterday = today.minusDays(1);
        LocalDate lastMonth = today.minusMonths(1);
        LocalDate lastYear = today.minusYears(1);

        int ingresoHoy = 0, ingresoAyer = 0;
        int ingresoMesActual = 0, ingresoMesPasado = 0;
        int ingresoAnioPasado = 0;

        for (Reporte reporte : reportes) {
            String mesAnio = reporte.getFechaCreacion().format(formatter);
            String diaMesAnio = reporte.getFechaCreacion().format(formatterDay);
            int costo = reporte.getCosto();
            int tiempoSegundos = convertirTiempoASegundos(reporte.getTiempo());

            costosMensuales.put(mesAnio, costosMensuales.getOrDefault(mesAnio, 0) + costo);
            ingresosDiarios.put(diaMesAnio, ingresosDiarios.getOrDefault(diaMesAnio, 0) + costo);

            tiemposDiarios.computeIfAbsent(diaMesAnio, k -> new ArrayList<>()).add(tiempoSegundos);
            tiemposMensuales.computeIfAbsent(mesAnio, k -> new ArrayList<>()).add(tiempoSegundos);

            if (reporte.getFechaCreacion().getYear() == today.getYear()) {
                costoTotalAnual += costo;
                tiempoTotalAnual += tiempoSegundos;
                cantidadCargasAnual++;
            }
        }

        // **Obtener valores de ingresos**
        ingresoHoy = ingresosDiarios.getOrDefault(today.format(formatterDay), 0);
        ingresoAyer = ingresosDiarios.getOrDefault(yesterday.format(formatterDay), 0);
        ingresoMesActual = costosMensuales.getOrDefault(today.format(formatter), 0);
        ingresoMesPasado = costosMensuales.getOrDefault(lastMonth.format(formatter), 0);
        ingresoAnioPasado = costosMensuales.getOrDefault(lastYear.format(formatter), 0);

        // **Calcular porcentajes de ingresos**
        double porcentajeIngresoDiario = calcularPorcentajeCambio(ingresoAyer, ingresoHoy);
        double porcentajeIngresoMensual = calcularPorcentajeCambio(ingresoMesPasado, ingresoMesActual);
        double porcentajeIngresoAnual = calcularPorcentajeCambio(ingresoAnioPasado, costoTotalAnual);

        // **Calcular promedios de tiempos**
        int promedioDiarioSegundos = calcularPromedioSegundos(tiemposDiarios.getOrDefault(today.format(formatterDay), new ArrayList<>()));
        int promedioAyerSegundos = calcularPromedioSegundos(tiemposDiarios.getOrDefault(yesterday.format(formatterDay), new ArrayList<>()));

        Map<String, String> promedioMensualPorMes = tiemposMensuales.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> calcularPromedioTiempo(entry.getValue())
                ));

        int promedioMensualSegundos = calcularPromedioSegundos(tiemposMensuales.getOrDefault(today.format(formatter), new ArrayList<>()));
        int promedioMensualPasadoSegundos = calcularPromedioSegundos(tiemposMensuales.getOrDefault(lastMonth.format(formatter), new ArrayList<>()));

        int promedioAnualSegundos = (cantidadCargasAnual > 0) ? (tiempoTotalAnual / cantidadCargasAnual) : 0;
        int promedioAnualPasadoSegundos = calcularPromedioSegundos(tiemposMensuales.getOrDefault(lastYear.format(formatter), new ArrayList<>()));

        // **Calcular porcentajes de cambio en tiempos**
        double porcentajeTiempoDiario = calcularPorcentajeCambio(promedioAyerSegundos, promedioDiarioSegundos);
        double porcentajeTiempoMensual = calcularPorcentajeCambio(promedioMensualPasadoSegundos, promedioMensualSegundos);
        double porcentajeTiempoAnual = calcularPorcentajeCambio(promedioAnualPasadoSegundos, promedioAnualSegundos);

        return new ReporteResumenDTO(
                empresaId, costosMensuales, costoTotalAnual, ingresoHoy,
                porcentajeIngresoDiario, porcentajeIngresoMensual, porcentajeIngresoAnual,
                calcularPromedioTiempo(List.of(promedioDiarioSegundos)),
                promedioMensualPorMes,
                calcularPromedioTiempo(List.of(promedioAnualSegundos)),
                porcentajeTiempoDiario, porcentajeTiempoMensual, porcentajeTiempoAnual
        );
    }

    private int calcularPromedioSegundos(List<Integer> tiempos) {
        if (tiempos == null || tiempos.isEmpty()) return 0;
        int totalSegundos = tiempos.stream().mapToInt(Integer::intValue).sum();
        return totalSegundos / tiempos.size();
    }


    private int convertirTiempoASegundos(String tiempoStr) {
        if (tiempoStr == null || tiempoStr.isEmpty()) return 0;

        String[] partes = tiempoStr.split(":"); // Separar por ":"
        int horas = Integer.parseInt(partes[0]);
        int minutos = Integer.parseInt(partes[1]);
        int segundos = Integer.parseInt(partes[2]);

        return (horas * 3600) + (minutos * 60) + segundos;
    }

    private double calcularPorcentajeCambio(int valorAnterior, int valorActual) {
        if (valorAnterior == 0) return valorActual > 0 ? 100 : 0;
        return ((double) (valorActual - valorAnterior) / valorAnterior) * 100;
    }


    private String calcularPromedioTiempo(List<Integer> tiempos) {
        if (tiempos == null || tiempos.isEmpty()) return "0s";

        int totalSegundos = tiempos.stream().mapToInt(Integer::intValue).sum();
        int promedioSegundos = totalSegundos / tiempos.size();

        int horas = promedioSegundos / 3600;
        int minutos = (promedioSegundos % 3600) / 60;
        int segundos = promedioSegundos % 60;

        StringBuilder resultado = new StringBuilder();
        if (horas > 0) resultado.append(horas).append("h ");
        if (minutos > 0 || horas > 0) resultado.append(minutos).append("m ");
        resultado.append(segundos).append("s");

        return resultado.toString().trim();
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

