package com.example.OilGasFieldOperationsSystem.repositories;

import com.example.OilGasFieldOperationsSystem.entities.Contractor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContractorRepository extends JpaRepository<Contractor, Long> {

    @Query("SELECT c FROM Contractor c WHERE c.isActive=true")
    List<Contractor> getAllContractor();

    @Query("SELECT c FROM Contractor c WHERE c.isActive=true AND c.id=:id")
    Contractor getById(@Param("id") Long id);

}
