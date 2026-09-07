package com.example.OilGasFieldOperationsSystem.repositories;

import com.example.OilGasFieldOperationsSystem.entities.ProductionReading;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductionReadingRepository extends JpaRepository<ProductionReading, Long> {

    @Query("SELECT p FROM ProductionReading p WHERE p.isActive=true")
    List<ProductionReading> getAllProductionReading();

    @Query("SELECT p FROM ProductionReading p WHERE p.isActive=true AND p.id=:id")
    ProductionReading getById(@Param("id") Long id);

}
