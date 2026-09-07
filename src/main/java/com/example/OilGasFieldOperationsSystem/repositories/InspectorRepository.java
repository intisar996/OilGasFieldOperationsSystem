package com.example.OilGasFieldOperationsSystem.repositories;

import com.example.OilGasFieldOperationsSystem.entities.Inspector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InspectorRepository extends JpaRepository<Inspector, Long> {

    @Query("SELECT i FROM Inspector i WHERE i.isActive=true")
    List<Inspector> getAllInspector();

    @Query("SELECT i FROM Inspector i WHERE i.isActive=true AND i.id=:id")
    Inspector getById(@Param("id") Long id);

}
