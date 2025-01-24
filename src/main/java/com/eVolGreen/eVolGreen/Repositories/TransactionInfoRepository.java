package com.eVolGreen.eVolGreen.Repositories;

import com.eVolGreen.eVolGreen.DTOS.AccountDTO.TransactionDTO.TransactionInfoDTO;
import com.eVolGreen.eVolGreen.Models.Account.Empresa;
import com.eVolGreen.eVolGreen.Models.Account.Transaction.TransactionInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource
public interface TransactionInfoRepository extends JpaRepository<TransactionInfo, Long>{

    List<TransactionInfo> findByEmpresa(Empresa empresa);

    List<TransactionInfo> findByEmpresaId(Long empresaId);

}
