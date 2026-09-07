package com.example.OilGasFieldOperationsSystem.controllers;

import com.example.OilGasFieldOperationsSystem.entities.OilField;
import com.example.OilGasFieldOperationsSystem.services.OilFieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("OilField")
public class OilFieldController {

    OilFieldService oilFieldService;

    @Autowired
    public OilFieldController(OilFieldService oilFieldService) {
        this.oilFieldService = oilFieldService;
    }

    @PostMapping("add")
    public Long addOilField(@RequestBody OilField oilField) {

        return oilFieldService.addOilField(
                oilField.getName(),
                oilField.getLocation(),
                oilField.getRegion()
        );
    }

    @GetMapping("getAll")
    public List<OilField> getAllOilField() {
        return oilFieldService.getAllOilField();
    }

    @GetMapping("getById")
    public OilField getById(@RequestParam Long id) {
        return oilFieldService.getById(id);
    }

    @PutMapping("update")
    public OilField updateOilField(
            @RequestBody OilField oilField) throws Exception {

        return oilFieldService.updateOilField(
                oilField.getId(),
                oilField.getName(),
                oilField.getLocation(),
                oilField.getRegion()
        );
    }

    @DeleteMapping("deleteById")
    public Boolean deleteOilField(@RequestParam Long id) throws Exception {
        return oilFieldService.deleteOilField(id);
    }
}
