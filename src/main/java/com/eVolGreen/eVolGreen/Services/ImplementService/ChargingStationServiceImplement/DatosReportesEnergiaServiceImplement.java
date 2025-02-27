package com.eVolGreen.eVolGreen.Services.ImplementService.ChargingStationServiceImplement;

import com.eVolGreen.eVolGreen.DTOS.AccountDTO.TransactionDTO.ChargePointsSummaryResponseDTO;
import com.eVolGreen.eVolGreen.Models.ChargingStation.DatosReportesEnergia;
import com.eVolGreen.eVolGreen.Models.ChargingStation.Reporte;
import com.eVolGreen.eVolGreen.Repositories.DatosReportesEnergiaRepository;
import com.eVolGreen.eVolGreen.Repositories.ReporteRepository;
import com.eVolGreen.eVolGreen.Services.ChargingStationService.DatosReportesEnergiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DatosReportesEnergiaServiceImplement implements DatosReportesEnergiaService {

    @Autowired
    private DatosReportesEnergiaRepository datosReportesEnergiaRepository;

    @Autowired
    private ReporteRepository reporteRepository;

    @Override
    @Transactional
    public void generarDatosReportesEnergiaPorEmpresa(Long empresaId) {
        // Obtener los ingresos agrupados por año y mes para la empresa
        List<Object[]> energiaAgrupada = reporteRepository.findEnergiaAgrupadaPorMesYAno(empresaId);

        // Procesar cada grupo y crear un registro en DatosReportesEnergia
        for (Object[] fila : energiaAgrupada) {
            Integer ano = (Integer) fila[0];
            Integer mes = (Integer) fila[1];
            Long energia = (Long) fila[2];
            Long empresa = (Long) fila[3];

            // Verificar si ya existe un registro para ese mes, año y empresa
            Optional<DatosReportesEnergia> existente = datosReportesEnergiaRepository.findByMesAndAnoAndEmpresaId(mes, ano, empresa);

            if (existente.isPresent()) {
                // Si existe, sumar la energía al registro existente
                DatosReportesEnergia datosExistente = existente.get();
                datosExistente.setEnergia(datosExistente.getEnergia() + energia.intValue());
                datosReportesEnergiaRepository.save(datosExistente);
            } else {
                // Si no existe, crear un nuevo registro
                DatosReportesEnergia nuevoRegistro = new DatosReportesEnergia();
                nuevoRegistro.setAno(ano);
                nuevoRegistro.setMes(mes);
                nuevoRegistro.setEnergia(energia.intValue());
                nuevoRegistro.setEmpresaId(empresa);

                // Guardar el registro en la base de datos
                datosReportesEnergiaRepository.save(nuevoRegistro);
            }
        }
    }

    @Override
    public List<DatosReportesEnergia> obtenerDatosReportesEnergiaPorEmpresa(Long empresaId) {
        return datosReportesEnergiaRepository.findByEmpresaId(empresaId);
    }
    @Override
    public ChargePointsSummaryResponseDTO getChargePointsSummary(Long empresaId) {
        // Obtener la fecha actual y los valores de año y mes
        ZonedDateTime now = ZonedDateTime.now();
        int currentYear = now.getYear();
        int currentMonth = now.getMonthValue();

        // 1. Obtener la energía diaria utilizando Reportes
        ZonedDateTime inicioDia = now.toLocalDate().atStartOfDay(ZoneId.systemDefault());
        ZonedDateTime finDia = now.toLocalDate().atTime(23, 59, 59).atZone(ZoneId.systemDefault());

        // 1. Obtener la energía diaria utilizando Reportes
        List<Reporte> reportesHoy = reporteRepository.findByEmpresaIdAndInicioCargaBetween(
                empresaId,
                inicioDia,
                finDia
        );

        Integer dailyEnergyConsumed = reportesHoy.stream()
                .filter(reporte -> reporte.getEnergia() != null)
                .mapToInt(Reporte::getEnergia)
                .sum();

        // 2. Obtener la energía anual y mensual utilizando DatosReportesEnergia
        List<DatosReportesEnergia> energiaAnual = datosReportesEnergiaRepository.findByEmpresaIdAndAno(empresaId, currentYear);

        // Calcular el consumo total de energía anual
        Integer totalEnergyConsumed = energiaAnual.stream()
                .mapToInt(DatosReportesEnergia::getEnergia)
                .sum();

        // Clasificar el consumo por mes
        Map<String, Integer> monthlyEnergyConsumed = energiaAnual.stream()
                .collect(Collectors.groupingBy(
                        datos -> obtenerMesAnio(datos.getMes(), datos.getAno()), // Ej: "Febrero 2025"
                        Collectors.summingInt(DatosReportesEnergia::getEnergia)
                ));

        // 3. Obtener la lista de chargePointId únicos usando Reportes
        List<String> chargePointIds = reportesHoy.stream()
                .map(reporte -> reporte.getCharger().getoCPPid())
                .distinct()
                .collect(Collectors.toList());

        // Construir la respuesta manteniendo el formato anterior
        return new ChargePointsSummaryResponseDTO(
                empresaId,
                chargePointIds,
                totalEnergyConsumed,
                dailyEnergyConsumed,
                monthlyEnergyConsumed
        );
    }

    private String obtenerMesAnio(int mes, int ano) {
        String[] meses = {
                "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
                "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"
        };
        return meses[mes - 1] + " " + ano;
    }
}
