package com.example.OilGasFieldOperationsSystem.controllers;

import com.example.OilGasFieldOperationsSystem.entities.ProductionReading;
import com.example.OilGasFieldOperationsSystem.services.ProductionReadingService;
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
    public Long addProductionReading(
            @RequestBody ProductionReading productionReading) {

        return productionReadingService.addProductionReading(
                productionReading.getReadingDate(),
                productionReading.getOilVolume(),
                productionReading.getGasVolume(),
                productionReading.getWaterVolume(),
                productionReading.getWell().getId()
        );
    }

    @GetMapping("getAll")
    public List<ProductionReading> getAllProductionReading() {
        return productionReadingService.getAllProductionReading();
    }

    @GetMapping("getById")
    public ProductionReading getById(@RequestParam Long id) {
        return productionReadingService.getById(id);
    }

    @PutMapping("update")
    public ProductionReading updateProductionReading(
            @RequestBody ProductionReading productionReading)
            throws Exception {

        return productionReadingService.updateProductionReading(
                productionReading.getId(),
                productionReading.getReadingDate(),
                productionReading.getOilVolume(),
                productionReading.getGasVolume(),
                productionReading.getWaterVolume()
        );
    }

    @DeleteMapping("deleteById")
    public Boolean deleteProductionReading(
            @RequestParam Long id) throws Exception {

        return productionReadingService.deleteProductionReading(id);
    }
}
