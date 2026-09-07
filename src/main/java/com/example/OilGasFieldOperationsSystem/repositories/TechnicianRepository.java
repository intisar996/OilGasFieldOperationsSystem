package com.example.OilGasFieldOperationsSystem.repositories;

import com.example.OilGasFieldOperationsSystem.entities.Technician;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TechnicianRepository extends JpaRepository<Technician, Long> {

    @Query("SELECT t FROM Technician t WHERE t.isActive=true")
    List<Technician> getAllTechnician();

    @Query("SELECT t FROM Technician t WHERE t.isActive=true AND t.id=:id")
    Technician getById(@Param("id") Long id);

}
