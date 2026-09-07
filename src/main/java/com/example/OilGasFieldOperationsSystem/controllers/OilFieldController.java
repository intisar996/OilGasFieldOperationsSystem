package com.example.OilGasFieldOperationsSystem.controllers;

import com.example.OilGasFieldOperationsSystem.dto.OilFieldDTO;
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
    public Long addOilField(@RequestBody OilFieldDTO oilField) {

        return oilFieldService.addOilField(
                oilField.getName(),
                oilField.getLocation(),
                oilField.getRegion()
        );
    }

    @GetMapping("getAll")
    public List<OilFieldDTO> getAllOilField() {
        return OilFieldDTO.convertToDTO(oilFieldService.getAllOilField());
    }

    @GetMapping("getById")
    public OilFieldDTO getById(@RequestParam Long id) {
        return OilFieldDTO.convertToDTO(oilFieldService.getById(id));
    }

    @PutMapping("update")
    public OilFieldDTO updateOilField(
            @RequestBody OilFieldDTO oilField) throws Exception {

        return OilFieldDTO.convertToDTO(oilFieldService.updateOilField(
                oilField.getOilFieldId(),
                oilField.getName(),
                oilField.getLocation(),
                oilField.getRegion()
        ));
    }

    @DeleteMapping("deleteById")
    public Boolean deleteOilField(@RequestParam Long id) throws Exception {
        return oilFieldService.deleteOilField(id);
    }
}
