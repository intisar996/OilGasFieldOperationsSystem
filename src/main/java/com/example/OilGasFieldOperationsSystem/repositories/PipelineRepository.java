package com.example.OilGasFieldOperationsSystem.repositories;

import com.example.OilGasFieldOperationsSystem.entities.Pipeline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PipelineRepository extends JpaRepository<Pipeline, Long> {

    @Query("SELECT p FROM Pipeline p WHERE p.isActive=true")
    List<Pipeline> getAllPipeline();

    @Query("SELECT p FROM Pipeline p WHERE p.isActive=true AND p.id=:id")
    Pipeline getById(@Param("id") Long id);

}
