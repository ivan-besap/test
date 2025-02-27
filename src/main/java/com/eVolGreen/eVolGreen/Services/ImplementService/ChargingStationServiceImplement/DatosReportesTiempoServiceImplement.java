package com.eVolGreen.eVolGreen.Services.ImplementService.ChargingStationServiceImplement;

import com.eVolGreen.eVolGreen.Models.ChargingStation.DatosReportesTiempo;
import com.eVolGreen.eVolGreen.Repositories.DatosReportesTiempoRepository;
import com.eVolGreen.eVolGreen.Repositories.ReporteRepository;
import com.eVolGreen.eVolGreen.Services.ChargingStationService.DatosReportesTiempoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class DatosReportesTiempoServiceImplement implements DatosReportesTiempoService {
    @Autowired
    private DatosReportesTiempoRepository datosReportesTiempoRepository;

    @Autowired
    private ReporteRepository reporteRepository;

    @Override
    @Transactional
    public void generarDatosReportesTiempoPorEmpresa(Long empresaId) {
        // Obtener los tiempos agrupados por año y mes para la empresa
        List<Object[]> tiemposAgrupados = reporteRepository.findTiemposAgrupadosPorMesYAno(empresaId);

        // Mapear tiempos por año y mes
        Map<String, List<String>> tiemposPorMesYAno = new HashMap<>();
        for (Object[] fila : tiemposAgrupados) {
            Integer ano = (Integer) fila[0];
            Integer mes = (Integer) fila[1];
            String tiempo = (String) fila[2];
            Long empresa = (Long) fila[3];

            // Agrupar tiempos por año y mes
            String key = ano + "-" + mes;
            tiemposPorMesYAno.computeIfAbsent(key, k -> new ArrayList<>()).add(tiempo);
        }

        // Procesar cada grupo para calcular el tiempo promedio
        for (Map.Entry<String, List<String>> entry : tiemposPorMesYAno.entrySet()) {
            String[] anoMes = entry.getKey().split("-");
            Integer ano = Integer.parseInt(anoMes[0]);
            Integer mes = Integer.parseInt(anoMes[1]);
            List<String> tiempos = entry.getValue();

            // Calcular el promedio de tiempos
            LocalTime promedio = calcularPromedioTiempos(tiempos);

            // Convertir a String para guardar en la base de datos
            String tiempoPromedio = promedio.format(DateTimeFormatter.ofPattern("HH:mm:ss"));

            // Verificar si ya existe un registro para ese mes, año y empresa
            Optional<DatosReportesTiempo> existente = datosReportesTiempoRepository.findByMesAndAnoAndEmpresaId(mes, ano, empresaId);

            if (existente.isPresent()) {
                // Si existe, actualizar el tiempo promedio
                DatosReportesTiempo datosExistente = existente.get();
                datosExistente.setTiempo(tiempoPromedio);
                datosReportesTiempoRepository.save(datosExistente);
            } else {
                // Si no existe, crear un nuevo registro
                DatosReportesTiempo nuevoRegistro = new DatosReportesTiempo();
                nuevoRegistro.setAno(ano);
                nuevoRegistro.setMes(mes);
                nuevoRegistro.setTiempo(tiempoPromedio);
                nuevoRegistro.setEmpresaId(empresaId);

                // Guardar el registro en la base de datos
                datosReportesTiempoRepository.save(nuevoRegistro);
            }
        }
    }

    private LocalTime convertirStringATiempo(String tiempoString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return LocalTime.parse(tiempoString, formatter);
    }

    // Método para sumar tiempos en formato HH:mm:ss
    private LocalTime sumarTiempos(List<String> tiempos) {
        LocalTime total = LocalTime.of(0, 0, 0);
        for (String tiempo : tiempos) {
            LocalTime t = convertirStringATiempo(tiempo);
            total = total.plusHours(t.getHour())
                    .plusMinutes(t.getMinute())
                    .plusSeconds(t.getSecond());
        }
        return total;
    }

    // Método para calcular el promedio de tiempos
    private LocalTime calcularPromedioTiempos(List<String> tiempos) {
        LocalTime total = sumarTiempos(tiempos);
        int totalSegundos = total.toSecondOfDay();
        int promedioSegundos = totalSegundos / tiempos.size();
        return LocalTime.ofSecondOfDay(promedioSegundos);
    }
}
