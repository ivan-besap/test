package com.eVolGreen.eVolGreen.Services.AccountService;

import com.eVolGreen.eVolGreen.Models.Account.Empresa;
import com.eVolGreen.eVolGreen.Models.ChargingStation.Connector.Connector;

public interface EmpresaService {
    void saveEmpresa(Empresa empresa);
    Empresa findById(Long empresaId);
}
