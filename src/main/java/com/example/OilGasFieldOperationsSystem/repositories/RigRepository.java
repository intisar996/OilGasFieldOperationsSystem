package com.example.OilGasFieldOperationsSystem.repositories;

import com.example.OilGasFieldOperationsSystem.entities.Rig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RigRepository extends JpaRepository<Rig, Long> {

    @Query("SELECT r FROM Rig r WHERE r.isActive=true")
    List<Rig> getAllRig();

    @Query("SELECT r FROM Rig r WHERE r.isActive=true AND r.id=:id")
    Rig getById(@Param("id") Long id);

}
