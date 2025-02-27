package com.eVolGreen.eVolGreen.Services.ImplementService.ChargingStationServiceImplement;

import com.eVolGreen.eVolGreen.DTOS.ChargingStationDTO.DatosReportesDTO;
import com.eVolGreen.eVolGreen.Models.ChargingStation.DatosReportes;
import com.eVolGreen.eVolGreen.Repositories.ChargerManufacturerRepository;
import com.eVolGreen.eVolGreen.Repositories.DatosReportesRepository;
import com.eVolGreen.eVolGreen.Repositories.ReporteRepository;
import com.eVolGreen.eVolGreen.Services.ChargingStationService.DatosReportesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DatosReportesServiceImplement implements DatosReportesService {

    @Autowired
    private DatosReportesRepository datosReportesRepository;

    @Autowired
    private ReporteRepository reporteRepository;

    @Override
    public List<DatosReportesDTO> getDatosReportesByEmpresaId(Long empresaId) {
        return datosReportesRepository.findAllByEmpresaId(empresaId);
    }

    @Override
    @Transactional
    public void generarDatosReportesPorEmpresa(Long empresaId) {
        // Obtener los ingresos agrupados por año y mes para la empresa
        List<Object[]> ingresosAgrupados = reporteRepository.findIngresosAgrupadosPorMesYAno(empresaId);

        // Procesar cada grupo y crear un registro en DatosReportes
        for (Object[] fila : ingresosAgrupados) {
            Integer ano = (Integer) fila[0];
            Integer mes = (Integer) fila[1];
            Long ingreso = (Long) fila[2];
            Long empresa = (Long) fila[3];
            Long estacionId = (Long) fila[4];

            // Crear el objeto DatosReportes
            DatosReportes datosReportes = new DatosReportes();
            datosReportes.setAno(ano);
            datosReportes.setMes(mes);
            datosReportes.setIngreso(ingreso.intValue());
            datosReportes.setEmpresaId(empresa);
            datosReportes.setEstacionId(estacionId);

            // Guardar el registro en la base de datos
            datosReportesRepository.save(datosReportes);
        }
    }
}
