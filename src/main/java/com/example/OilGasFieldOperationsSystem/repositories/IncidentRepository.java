package com.example.OilGasFieldOperationsSystem.repositories;

import com.example.OilGasFieldOperationsSystem.entities.Incident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IncidentRepository extends JpaRepository<Incident, Long> {

    @Query("SELECT i FROM Incident i WHERE i.isActive=true")
    List<Incident> getAllIncident();

    @Query("SELECT i FROM Incident i WHERE i.isActive=true AND i.id=:id")
    Incident getById(@Param("id") Long id);

}
