package com.example.OilGasFieldOperationsSystem.repositories;

import com.example.OilGasFieldOperationsSystem.entities.Inspection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InspectionRepository extends JpaRepository<Inspection, Long> {

    @Query("SELECT i FROM Inspection i WHERE i.isActive=true")
    List<Inspection> getAllInspection();

    @Query("SELECT i FROM Inspection i WHERE i.isActive=true AND i.id=:id")
    Inspection getById(@Param("id") Long id);

}
