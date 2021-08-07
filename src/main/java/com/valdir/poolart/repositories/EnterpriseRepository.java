package com.valdir.poolart.repositories;

import com.valdir.poolart.domain.Enterprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EnterpriseRepository extends JpaRepository<Enterprise, Integer> {
    Optional<Enterprise> findByCnpj(String cnpj);
}
