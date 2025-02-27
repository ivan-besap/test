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
import com.eVolGreen.eVolGreen.Models.ChargingStation.DatosReportes;
import com.eVolGreen.eVolGreen.Models.ChargingStation.DatosReportesTiempo;
import com.eVolGreen.eVolGreen.Models.ChargingStation.Reporte;
import com.eVolGreen.eVolGreen.Repositories.DatosReportesRepository;
import com.eVolGreen.eVolGreen.Repositories.DatosReportesTiempoRepository;
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
import java.time.YearMonth;
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

    @Autowired
    private DatosReportesRepository datosReportesRepository;

    @Autowired
    private DatosReportesTiempoRepository datosReportesTiempoRepository;


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

            Long empresaId = reporte.getEmpresa().getId();
            Long estacionId = reporte.getChargingStation().getId();

            if (reporte.getCosto() > 0) {
                // Obtenemos el año y mes del inicio de carga
                ZonedDateTime inicioCarga = reporte.getInicioCarga();
                int ano = inicioCarga.getYear();
                int mes = inicioCarga.getMonthValue();

                // Verificamos si ya existe un registro en DatosReportes para el mismo año, mes y empresa
                Optional<DatosReportes> datosReportesOpt = datosReportesRepository.findByMesAndAnoAndEmpresaIdAndEstacionId(mes, ano, empresaId, estacionId);

                if (datosReportesOpt.isPresent()) {
                    // Si ya existe, sumamos el costo al ingreso existente
                    DatosReportes datosReportes = datosReportesOpt.get();
                    datosReportes.setIngreso(datosReportes.getIngreso() + reporte.getCosto());
                    datosReportesRepository.save(datosReportes);
                } else {
                    // Si no existe, creamos un nuevo registro en DatosReportes
                    DatosReportes nuevoDatosReportes = new DatosReportes();
                    nuevoDatosReportes.setMes(mes);
                    nuevoDatosReportes.setAno(ano);
                    nuevoDatosReportes.setIngreso(reporte.getCosto());
                    nuevoDatosReportes.setEmpresaId(empresaId);
                    nuevoDatosReportes.setEstacionId(reporte.getChargingStation().getId());

                    datosReportesRepository.save(nuevoDatosReportes);
                }
            }
            return true;
        }
        return false;
    }

    public List<ReporteResponseDTO> getReportesByEmpresa(Long empresaId) {
        List<Reporte> reportes = reporteRepository.findByEmpresaIdAndDeviceIdentifierIsNull(empresaId);

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

    public List<ReporteResponseDTO> getReportesByEmpresaAndByRfid(Long empresaId) {
        List<Reporte> reportes = reporteRepository.findByEmpresaIdAndDeviceIdentifierIsNotNull(empresaId);

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

                    dto.setRfid(reporte.getDeviceIdentifier().getRFID());
                    if (reporte.getPatenteAuto() != null) {
                        dto.setAuto(reporte.getPatenteAuto());
                    } else {
                        dto.setAuto("la RFID no estaba asignada a un auto");
                    }

                    return dto;
                })
                .collect(Collectors.toList());
    }

    public ReporteResumenDTO obtenerReporteResumen(Long empresaId) {
        // **Fechas relevantes**
        LocalDate today = LocalDate.now();
        LocalDate yesterday = today.minusDays(1);
        LocalDate firstDayOfMonth = today.withDayOfMonth(1);
        LocalDate firstDayOfLastMonth = firstDayOfMonth.minusMonths(1);
        LocalDate firstDayOfYear = today.withDayOfYear(1);
        LocalDate firstDayOfLastYear = firstDayOfYear.minusYears(1);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM yyyy", new Locale("es", "ES"));
        DateTimeFormatter formatterDay = DateTimeFormatter.ofPattern("dd MMM yyyy", new Locale("es", "ES"));

        // **Mapas para acumular datos**
        Map<String, Integer> costosMensuales = new HashMap<>();
        Map<String, Integer> ingresosDiarios = new HashMap<>();
        Map<String, List<Integer>> tiemposDiarios = new HashMap<>();

        int costoTotalAnual = 0;

        int ingresoHoy = 0, ingresoAyer = 0;
        int ingresoMesActual = 0, ingresoMesPasado = 0;
        int ingresoAnioPasado = 0;

        // **Obtener datos diarios de Reporte**
        List<Reporte> reportes = reporteRepository.findByEmpresaId(empresaId);

        for (Reporte reporte : reportes) {
            String diaMesAnio = reporte.getFechaCreacion().format(formatterDay);
            int costo = reporte.getCosto();
            int tiempoSegundos = convertirTiempoASegundos(reporte.getTiempo());

            ingresosDiarios.put(diaMesAnio, ingresosDiarios.getOrDefault(diaMesAnio, 0) + costo);

            tiemposDiarios.computeIfAbsent(diaMesAnio, k -> new ArrayList<>()).add(tiempoSegundos);
        }

        // **Ingresos Diarios**
        ingresoHoy = ingresosDiarios.getOrDefault(today.format(formatterDay), 0);
        ingresoAyer = ingresosDiarios.getOrDefault(yesterday.format(formatterDay), 0);

        // **Ingresos Mensuales y Anuales de DatosReportes**
        List<DatosReportes> datosMensuales = datosReportesRepository.findByEmpresaIdAndAnoAndMes(empresaId, today.getYear(), today.getMonthValue());
        ingresoMesActual = datosMensuales.stream().mapToInt(DatosReportes::getIngreso).sum();

        List<DatosReportes> datosMesPasado = datosReportesRepository.findByEmpresaIdAndAnoAndMes(empresaId, firstDayOfLastMonth.getYear(), firstDayOfLastMonth.getMonthValue());
        ingresoMesPasado = datosMesPasado.stream().mapToInt(DatosReportes::getIngreso).sum();

        List<DatosReportes> datosAnual = datosReportesRepository.findByEmpresaIdAndAno(empresaId, today.getYear());
        costoTotalAnual = datosAnual.stream().mapToInt(DatosReportes::getIngreso).sum();

        List<DatosReportes> datosAnioPasado = datosReportesRepository.findByEmpresaIdAndAno(empresaId, firstDayOfLastYear.getYear());
        ingresoAnioPasado = datosAnioPasado.stream().mapToInt(DatosReportes::getIngreso).sum();

        // **Costos Mensuales para el Gráfico**
        costosMensuales = datosAnual.stream()
                .collect(Collectors.groupingBy(
                        dr -> YearMonth.of(dr.getAno(), dr.getMes()).format(formatter),
                        LinkedHashMap::new,
                        Collectors.summingInt(DatosReportes::getIngreso)
                ));

        // **Obtener y Agrupar Datos de Tiempos Mensuales**
        List<DatosReportesTiempo> tiemposMensualesDatos = datosReportesTiempoRepository.findByEmpresaIdAndAno(empresaId, today.getYear());
        List<DatosReportesTiempo> tiemposMesPasadoDatos = datosReportesTiempoRepository.findByEmpresaIdAndAnoAndMes(
                empresaId, firstDayOfLastMonth.getYear(), firstDayOfLastMonth.getMonthValue());

        Map<String, String> tiemposPromediosMesNuevo = tiemposMensualesDatos.stream()
                .collect(Collectors.toMap(
                        drt -> YearMonth.of(drt.getAno(), drt.getMes()).format(formatter), // Llave: Mes y Año formateado
                        DatosReportesTiempo::getTiempo, // Valor: Tiempo en formato HH:mm:ss
                        this::sumarTiempos, // Utiliza el método sumarTiempos para manejar duplicados
                        LinkedHashMap::new
                ));

// **Formatear Tiempos a h m s**
        tiemposPromediosMesNuevo.replaceAll((key, value) -> convertirSegundosAFormatoTiempo(convertirTiempoASegundos(value)));

        List<DatosReportesTiempo> tiemposAnualesDatos = datosReportesTiempoRepository.findByEmpresaIdAndAno(empresaId, today.getYear());
        List<DatosReportesTiempo> tiemposAnioPasadoDatos = datosReportesTiempoRepository.findByEmpresaIdAndAno(empresaId, firstDayOfLastYear.getYear());

// **Sumar todos los tiempos anuales y calcular el promedio**
        int totalSegundosAnuales = tiemposAnualesDatos.stream()
                .mapToInt(drt -> convertirTiempoASegundos(drt.getTiempo()))
                .sum();

        int cantidadMesesConDatos = tiemposAnualesDatos.size();
        int promedioAnualSegundosNuevo = (cantidadMesesConDatos > 0) ? (totalSegundosAnuales / cantidadMesesConDatos) : 0;

// **Convertir el promedio a formato legible**
        String promedioAnualNuevo = convertirSegundosAFormatoTiempo(promedioAnualSegundosNuevo);

        int totalSegundosAnual = tiemposAnualesDatos.stream()
                .mapToInt(drt -> convertirTiempoASegundos(drt.getTiempo()))
                .sum();

        int totalSegundosAnioPasado = tiemposAnioPasadoDatos.stream()
                .mapToInt(drt -> convertirTiempoASegundos(drt.getTiempo()))
                .sum();

// **Sumar tiempos mensuales**
        int totalSegundosMensual = tiemposMensualesDatos.stream()
                .mapToInt(drt -> convertirTiempoASegundos(drt.getTiempo()))
                .sum();

        int totalSegundosMesPasado = tiemposMesPasadoDatos.stream()
                .mapToInt(drt -> convertirTiempoASegundos(drt.getTiempo()))
                .sum();

        double porcentajeTiempoAnualNuevo = calcularPorcentajeCambio(totalSegundosAnioPasado, totalSegundosAnual);

// **Calcular Porcentaje Mensual**
        double porcentajeTiempoMensualNuevo = calcularPorcentajeCambio(totalSegundosMesPasado, totalSegundosMensual);


        // **Porcentajes de Ingresos**
        double porcentajeIngresoDiario = calcularPorcentajeCambio(ingresoAyer, ingresoHoy);
        double porcentajeIngresoMensual = calcularPorcentajeCambio(ingresoMesPasado, ingresoMesActual);
        double porcentajeIngresoAnual = calcularPorcentajeCambio(ingresoAnioPasado, costoTotalAnual);

        // **Calcular promedios de tiempos**
        int promedioDiarioSegundos = calcularPromedioSegundos(tiemposDiarios.getOrDefault(today.format(formatterDay), new ArrayList<>()));
        int promedioAyerSegundos = calcularPromedioSegundos(tiemposDiarios.getOrDefault(yesterday.format(formatterDay), new ArrayList<>()));

        // **Porcentajes de Cambio en Tiempos**
        double porcentajeTiempoDiario = calcularPorcentajeCambio(promedioAyerSegundos, promedioDiarioSegundos);
        // **Construir y Retornar DTO**
        return new ReporteResumenDTO(
                empresaId, costosMensuales, costoTotalAnual, ingresoHoy,
                porcentajeIngresoDiario, porcentajeIngresoMensual, porcentajeIngresoAnual,
                calcularPromedioTiempo(List.of(promedioDiarioSegundos)),
                porcentajeTiempoDiario, porcentajeTiempoMensualNuevo, porcentajeTiempoAnualNuevo,
                tiemposPromediosMesNuevo, promedioAnualNuevo
        );
    }

    // Convierte segundos a formato h m s
    private String convertirSegundosAFormatoTiempo(int totalSegundos) {
        int horas = totalSegundos / 3600;
        int minutos = (totalSegundos % 3600) / 60;
        int segundos = totalSegundos % 60;

        StringBuilder resultado = new StringBuilder();
        if (horas > 0) resultado.append(horas).append("h ");
        if (minutos > 0) resultado.append(minutos).append("m ");
        if (segundos > 0) resultado.append(segundos).append("s");

        return resultado.toString().trim();
    }

    // Suma dos tiempos en formato HH:mm:ss
    private String sumarTiempos(String tiempo1, String tiempo2) {
        int totalSegundos = convertirTiempoASegundos(tiempo1) + convertirTiempoASegundos(tiempo2);
        return convertirSegundosAFormatoTiempo(totalSegundos);
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

