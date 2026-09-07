package com.example.OilGasFieldOperationsSystem.repositories;

import com.example.OilGasFieldOperationsSystem.entities.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Long> {

    @Query("SELECT e FROM Equipment e WHERE e.isActive=true")
    List<Equipment> getAllEquipment();

    @Query("SELECT e FROM Equipment e WHERE e.isActive=true AND e.id=:id")
    Equipment getById(@Param("id") Long id);

}
