package com.eVolGreen.eVolGreen.Repositories;

import com.eVolGreen.eVolGreen.DTOS.AccountDTO.TransactionDTO.ChargePointEnergyForMonthsProjection;
import com.eVolGreen.eVolGreen.DTOS.AccountDTO.TransactionDTO.TransactionInfoDTO;
import com.eVolGreen.eVolGreen.Models.Account.Empresa;
import com.eVolGreen.eVolGreen.Models.Account.Transaction.TransactionInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource
public interface TransactionInfoRepository extends JpaRepository<TransactionInfo, Long>{

    List<TransactionInfo> findByEmpresa(Empresa empresa);

    @Query("SELECT t FROM TransactionInfo t WHERE t.empresa.id = :empresaId")
    List<TransactionInfo> findByEmpresaId(@Param("empresaId") Long empresaId);

    @Query("SELECT t.empresa.id AS empresaId, " +
            "FUNCTION('DATE_FORMAT', t.endTime, '%Y-%m') AS dataTimeForMonths, " +
            "SUM(t.energyConsumed) AS totalEnergyConsumed " +
            "FROM TransactionInfo t " +
            "WHERE t.empresa.id = :empresaId AND t.energyConsumed IS NOT NULL AND t.endTime IS NOT NULL " +
            "GROUP BY FUNCTION('DATE_FORMAT', t.endTime, '%Y-%m')")
    List<ChargePointEnergyForMonthsProjection> findEnergyConsumedByMonth(@Param("empresaId") Long empresaId);
}
