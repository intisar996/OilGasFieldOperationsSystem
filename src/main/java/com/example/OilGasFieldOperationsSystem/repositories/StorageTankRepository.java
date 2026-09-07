package com.example.OilGasFieldOperationsSystem.repositories;

import com.example.OilGasFieldOperationsSystem.entities.StorageTank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StorageTankRepository extends JpaRepository<StorageTank, Long> {

    @Query("SELECT s FROM StorageTank s WHERE s.isActive=true")
    List<StorageTank> getAllStorageTank();

    @Query("SELECT s FROM StorageTank s WHERE s.isActive=true AND s.id=:id")
    StorageTank getById(@Param("id") Long id);

}
