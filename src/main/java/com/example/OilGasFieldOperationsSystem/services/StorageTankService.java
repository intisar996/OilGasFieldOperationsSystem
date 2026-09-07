package com.example.OilGasFieldOperationsSystem.services;

import com.example.OilGasFieldOperationsSystem.entities.OilField;
import com.example.OilGasFieldOperationsSystem.entities.ProductionReading;
import com.example.OilGasFieldOperationsSystem.entities.StorageTank;
import com.example.OilGasFieldOperationsSystem.exceptions.ResourceNotFoundException;
import com.example.OilGasFieldOperationsSystem.repositories.OilFieldRepository;
import com.example.OilGasFieldOperationsSystem.repositories.ProductionReadingRepository;
import com.example.OilGasFieldOperationsSystem.repositories.StorageTankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class StorageTankService {

    StorageTankRepository storageTankRepository;
    OilFieldService oilFieldService;
    OilFieldRepository oilFieldRepository;
    ProductionReadingRepository productionReadingRepository;

    @Autowired
    public StorageTankService(
            OilFieldRepository oilFieldRepository,
            OilFieldService oilFieldService,
            ProductionReadingRepository productionReadingRepository,
            StorageTankRepository storageTankRepository) {

        this.oilFieldRepository = oilFieldRepository;
        this.oilFieldService = oilFieldService;
        this.productionReadingRepository = productionReadingRepository;
        this.storageTankRepository = storageTankRepository;
    }

    public Long addStorageTank(String tankCode, Double capacity,
                               Double currentLevel, String product,
                               Long oilFieldId) {

        OilField oilField = oilFieldService.getById(oilFieldId);

        if (oilField == null ||
                oilField.getId() == null ||
                !oilField.getIsActive()) {

            throw new ResourceNotFoundException(
                    "Oil field not found with id: " + oilFieldId);
        }

        StorageTank storageTank = new StorageTank();

        storageTank.setIsActive(true);
        storageTank.setCreatedDate(new Date());
        storageTank.setTankCode(tankCode);
        storageTank.setCapacity(capacity);
        storageTank.setCurrentLevel(currentLevel);
        storageTank.setProduct(product);
        storageTank.setOilField(oilField);

        StorageTank saveStorageTank =
                storageTankRepository.save(storageTank);

        return saveStorageTank.getId();
    }

    public List<StorageTank> getAllStorageTank() {
        return storageTankRepository.getAllStorageTank();
    }

    public StorageTank getById(Long id) {

        Optional<StorageTank> storageTank =
                storageTankRepository.findById(id);

        if (storageTank.isPresent() &&
                storageTank.get().getIsActive()) {

            return storageTank.get();
        }

        throw new ResourceNotFoundException(
                "Storage tank not found with id: " + id);
    }

    public StorageTank updateStorageTank(Long id,
                                         String tankCode,
                                         Double capacity,
                                         String product)
            throws Exception {

        StorageTank storageTankToUpdate =
                storageTankRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Storage tank not found with id: "
                                                + id));

        if (!storageTankToUpdate.getIsActive()) {
            throw new ResourceNotFoundException(
                    "Storage tank not found with id: " + id);
        }

        storageTankToUpdate.setUpdateDate(new Date());
        storageTankToUpdate.setTankCode(tankCode);
        storageTankToUpdate.setCapacity(capacity);
        storageTankToUpdate.setProduct(product);

        return storageTankRepository.save(storageTankToUpdate);
    }

    public Boolean deleteStorageTank(Long id) throws Exception {

        StorageTank storageTankToUpdate =
                storageTankRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Storage tank not found with id: "
                                                + id));

        if (!storageTankToUpdate.getIsActive()) {
            throw new ResourceNotFoundException(
                    "Storage tank not found with id: " + id);
        }

        storageTankToUpdate.setUpdateDate(new Date());
        storageTankToUpdate.setIsActive(false);

        storageTankRepository.save(storageTankToUpdate);

        return true;
    }

    public Long transferToTank(
            Long productionReadingId,
            Long storageTankId,
            String product) {

        ProductionReading reading =
                productionReadingRepository
                        .findById(productionReadingId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Production reading not found with id: "
                                                + productionReadingId));

        StorageTank tank =
                storageTankRepository
                        .findById(storageTankId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Storage tank not found with id: "
                                                + storageTankId));

        if (!reading.getIsActive()) {
            throw new ResourceNotFoundException(
                    "Production reading not found with id: "
                            + productionReadingId);
        }

        if (!tank.getIsActive()) {
            throw new ResourceNotFoundException(
                    "Storage tank not found with id: "
                            + storageTankId);
        }

        if (product == null || product.isBlank()) {
            throw new IllegalArgumentException(
                    "Product cannot be empty");
        }

        Double volume;

        switch (product.toUpperCase()) {

            case "OIL":
                volume = reading.getOilVolume();

                if (volume == null || volume <= 0) {
                    throw new IllegalArgumentException(
                            "Oil volume must be greater than zero");
                }

                break;

            case "GAS":
                volume = reading.getGasVolume();

                if (volume == null || volume <= 0) {
                    throw new IllegalArgumentException(
                            "Gas volume must be greater than zero");
                }

                break;

            case "WATER":
                volume = reading.getWaterVolume();

                if (volume == null || volume <= 0) {
                    throw new IllegalArgumentException(
                            "Water volume must be greater than zero");
                }

                break;

            default:
                throw new IllegalArgumentException(
                        "Invalid product: " + product);
        }

        if (tank.getCurrentLevel() == null ||
                tank.getCapacity() == null) {

            throw new IllegalArgumentException(
                    "Tank capacity or current level cannot be null");
        }

        Double newLevel =
                tank.getCurrentLevel() + volume;

        if (newLevel > tank.getCapacity()) {
            throw new IllegalArgumentException(
                    "Transfer rejected: tank capacity exceeded");
        }

        tank.setCurrentLevel(newLevel);

        switch (product.toUpperCase()) {

            case "OIL":
                reading.setOilVolume(0.0);
                break;

            case "GAS":
                reading.setGasVolume(0.0);
                break;

            case "WATER":
                reading.setWaterVolume(0.0);
                break;
        }

        storageTankRepository.save(tank);
        productionReadingRepository.save(reading);

        return tank.getId();
    }
}
