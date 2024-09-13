package com.eVolGreen.eVolGreen.Repositories;

import com.eVolGreen.eVolGreen.Models.Account.Account;
import com.eVolGreen.eVolGreen.Models.Account.Car.Car;
import com.eVolGreen.eVolGreen.Models.Account.Car.DeviceIdentifier;
import com.eVolGreen.eVolGreen.Models.Account.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface DeviceIdentifierRepository extends JpaRepository<DeviceIdentifier, Long> {


    List<DeviceIdentifier> findByEmpresaAndActivo(Empresa empresa, boolean activo);

    @Query("SELECT di FROM DeviceIdentifier di WHERE di.empresa = :empresa AND di.auto IS NULL AND di.activo = true")
    List<DeviceIdentifier> findUnassignedDeviceIdentifiersByEmpresa(@Param("empresa") Empresa empresa);

    List<DeviceIdentifier> findByAuto(Car auto);

}
