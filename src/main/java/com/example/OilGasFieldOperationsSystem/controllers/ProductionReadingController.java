package com.example.OilGasFieldOperationsSystem.controllers;

import com.example.OilGasFieldOperationsSystem.dto.ProductionReadingDTO;
import com.example.OilGasFieldOperationsSystem.entities.ProductionReading;
import com.example.OilGasFieldOperationsSystem.services.ProductionReadingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("ProductionReading")
public class ProductionReadingController {

    ProductionReadingService productionReadingService;

    @Autowired
    public ProductionReadingController(
            ProductionReadingService productionReadingService) {

        this.productionReadingService = productionReadingService;
    }

    @PostMapping("add")
    public Long addProductionReading(@Valid
            @RequestBody ProductionReadingDTO productionReading) {

        return productionReadingService.addProductionReading(
                productionReading.getReadingDate(),
                productionReading.getOilVolume(),
                productionReading.getGasVolume(),
                productionReading.getWaterVolume(),
                productionReading.getWellId()
        );
    }

    @GetMapping("getAll")
    public List<ProductionReadingDTO> getAllProductionReading() {
        return ProductionReadingDTO.convertToDTO(productionReadingService.getAllProductionReading());
    }

    @GetMapping("getById")
    public ProductionReadingDTO getById(@RequestParam Long id) {
        return ProductionReadingDTO.convertToDTO(productionReadingService.getById(id));
    }

    @PutMapping("update")
    public ProductionReadingDTO updateProductionReading(
            @RequestBody ProductionReadingDTO productionReading)
            throws Exception {

        return ProductionReadingDTO.convertToDTO(productionReadingService.updateProductionReading(
                productionReading.getProductionReadingId(),
                productionReading.getReadingDate(),
                productionReading.getOilVolume(),
                productionReading.getGasVolume(),
                productionReading.getWaterVolume()
        ));
    }

    @DeleteMapping("deleteById")
    public Boolean deleteProductionReading(
            @RequestParam Long id) throws Exception {

        return productionReadingService.deleteProductionReading(id);
    }
}
