package com.example.OilGasFieldOperationsSystem.repositories;

import com.example.OilGasFieldOperationsSystem.entities.Personnel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonnelRepository extends JpaRepository<Personnel, Long> {

    @Query("SELECT p FROM Personnel p WHERE p.isActive=true")
    List<Personnel> getAllPersonnel();

    @Query("SELECT p FROM Personnel p WHERE p.isActive=true AND p.id=:id")
    Personnel getById(@Param("id") Long id);

}
