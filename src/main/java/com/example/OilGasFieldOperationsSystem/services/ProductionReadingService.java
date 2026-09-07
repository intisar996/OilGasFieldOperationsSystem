package com.example.OilGasFieldOperationsSystem.services;

import com.example.OilGasFieldOperationsSystem.entities.ProductionReading;
import com.example.OilGasFieldOperationsSystem.entities.Well;
import com.example.OilGasFieldOperationsSystem.exceptions.ResourceNotFoundException;
import com.example.OilGasFieldOperationsSystem.repositories.ProductionReadingRepository;
import com.example.OilGasFieldOperationsSystem.repositories.WellRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProductionReadingService {

    ProductionReadingRepository productionReadingRepository;
    WellService wellService;
    WellRepository wellRepository;

    @Autowired
    public ProductionReadingService(
            ProductionReadingRepository productionReadingRepository,
            WellService wellService,
            WellRepository wellRepository) {

        this.productionReadingRepository = productionReadingRepository;
        this.wellService = wellService;
        this.wellRepository = wellRepository;
    }

    public Long addProductionReading(Date readingDate,
                                     Double oilVolume,
                                     Double gasVolume,
                                     Double waterVolume,
                                     Long wellId) {

        Well well = wellService.getById(wellId);

        if (well == null ||
                well.getId() == null ||
                !well.getIsActive()) {

            throw new ResourceNotFoundException(
                    "Well not found with id: " + wellId
            );
        }

        // Production reading is allowed only for producing wells
        if (!"producing".equalsIgnoreCase(well.getStatus())) {
            throw new IllegalArgumentException(
                    "Production reading can only be added for a producing well"
            );
        }

        ProductionReading productionReading =
                new ProductionReading();

        productionReading.setIsActive(true);
        productionReading.setCreatedDate(new Date());
        productionReading.setReadingDate(readingDate);
        productionReading.setOilVolume(oilVolume);
        productionReading.setGasVolume(gasVolume);
        productionReading.setWaterVolume(waterVolume);
        productionReading.setWell(well);

        ProductionReading saveProductionReading =
                productionReadingRepository.save(productionReading);

        return saveProductionReading.getId();
    }

    public List<ProductionReading> getAllProductionReading() {
        return productionReadingRepository.getAllProductionReading();
    }

    public ProductionReading getById(Long id) {

        Optional<ProductionReading> productionReading =
                productionReadingRepository.findById(id);

        if (productionReading.isPresent() &&
                productionReading.get().getIsActive()) {

            return productionReading.get();
        }

        throw new ResourceNotFoundException(
                "ProductionReading not found with id: " + id
        );
    }

    public ProductionReading updateProductionReading(
            Long id,
            Date readingDate,
            Double oilVolume,
            Double gasVolume,
            Double waterVolume) {

        ProductionReading productionReadingToUpdate =
                productionReadingRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "ProductionReading not found with id: " + id
                                )
                        );

        if (!productionReadingToUpdate.getIsActive()) {
            throw new ResourceNotFoundException(
                    "ProductionReading not found with id: " + id
            );
        }

        productionReadingToUpdate.setUpdateDate(new Date());
        productionReadingToUpdate.setReadingDate(readingDate);
        productionReadingToUpdate.setOilVolume(oilVolume);
        productionReadingToUpdate.setGasVolume(gasVolume);
        productionReadingToUpdate.setWaterVolume(waterVolume);

        return productionReadingRepository.save(
                productionReadingToUpdate
        );
    }

    public Boolean deleteProductionReading(Long id) {

        ProductionReading productionReadingToUpdate =
                productionReadingRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "ProductionReading not found with id: " + id
                                )
                        );

        if (!productionReadingToUpdate.getIsActive()) {
            throw new ResourceNotFoundException(
                    "ProductionReading not found with id: " + id
            );
        }

        productionReadingToUpdate.setUpdateDate(new Date());
        productionReadingToUpdate.setIsActive(false);

        productionReadingRepository.save(
                productionReadingToUpdate
        );

        return true;
    }
}