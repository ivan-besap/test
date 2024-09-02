package com.eVolGreen.eVolGreen.Repositories;

import com.eVolGreen.eVolGreen.Models.Account.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
    // No necesitas implementar nada aquí, JpaRepository ya proporciona métodos como save(), findById(), findAll(), etc.
}
