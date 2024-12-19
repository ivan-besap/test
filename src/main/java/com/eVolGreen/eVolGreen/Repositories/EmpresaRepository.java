package com.eVolGreen.eVolGreen.Repositories;

import com.eVolGreen.eVolGreen.Models.Account.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
    Optional<Empresa> findByNombre(String nombre);
}
