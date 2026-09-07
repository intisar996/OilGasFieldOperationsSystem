package com.example.OilGasFieldOperationsSystem.repositories;

import com.example.OilGasFieldOperationsSystem.entities.Well;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WellRepository extends JpaRepository<Well, Long> {

    @Query("SELECT w FROM Well w WHERE w.isActive=true")
    List<Well> getAllWell();

    @Query("SELECT w FROM Well w WHERE w.isActive=true AND w.id=:id")
    Well getById(@Param("id") Long id);

}
