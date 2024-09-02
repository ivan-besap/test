package com.eVolGreen.eVolGreen.Services.ImplementService.AccountServiceImplement;

import com.eVolGreen.eVolGreen.Models.Account.Empresa;
import com.eVolGreen.eVolGreen.Repositories.EmpresaRepository;
import com.eVolGreen.eVolGreen.Services.AccountService.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpresaServiceImplement implements EmpresaService {

    @Autowired
    private EmpresaRepository empresaRepository;

    @Override
    public void saveEmpresa(Empresa empresa) {
        empresaRepository.save(empresa);
    }
}