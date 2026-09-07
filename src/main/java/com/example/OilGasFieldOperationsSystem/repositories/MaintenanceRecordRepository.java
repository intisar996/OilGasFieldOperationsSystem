package com.example.OilGasFieldOperationsSystem.repositories;

import com.example.OilGasFieldOperationsSystem.entities.MaintenanceRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaintenanceRecordRepository extends JpaRepository<MaintenanceRecord, Long> {

    @Query("SELECT m FROM MaintenanceRecord m WHERE m.isActive=true")
    List<MaintenanceRecord> getAllMaintenanceRecord();

    @Query("SELECT m FROM MaintenanceRecord m WHERE m.isActive=true AND m.id=:id")
    MaintenanceRecord getById(@Param("id") Long id);

    @Query("""
        SELECT m
        FROM MaintenanceRecord m
        WHERE m.equipment.id = :equipmentId
          AND m.isActive = true
    """)
    List<MaintenanceRecord> getMaintenanceByEquipment(
            @Param("equipmentId") Long equipmentId);

}
