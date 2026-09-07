package com.example.OilGasFieldOperationsSystem.repositories;

import com.example.OilGasFieldOperationsSystem.entities.OilField;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OilFieldRepository extends JpaRepository<OilField, Long> {

    @Query("SELECT o FROM OilField o WHERE o.isActive=true")
    List<OilField> getAllOilField();

    @Query("SELECT o FROM OilField o WHERE o.isActive=true AND o.id=:id")
    OilField getById(@Param("id") Long id);

}
