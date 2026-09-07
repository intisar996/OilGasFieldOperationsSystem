package com.example.OilGasFieldOperationsSystem.controllers;

import com.example.OilGasFieldOperationsSystem.dto.WellDTO;
import com.example.OilGasFieldOperationsSystem.entities.Well;
import com.example.OilGasFieldOperationsSystem.services.WellService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("Well")
public class WellController {

    WellService wellService;

    @Autowired
    public WellController(WellService wellService) {
        this.wellService = wellService;
    }

    @PostMapping("add")
    public Long addWell(@RequestBody WellDTO well) {

        return wellService.addWell(
                well.getWellCode(),
                well.getDepth(),
                well.getType(),
                well.getStatus(),
                well.getOilFieldId(),
                well.getRigId()
        );
    }

    @GetMapping("getAll")
    public List<WellDTO> getAllWell() {
        return WellDTO.convertToDTO(wellService.getAllWell());
    }

    @GetMapping("getById")
    public WellDTO getById(@RequestParam Long id) {
        return  WellDTO.convertToDTO(wellService.getById(id));
    }

    @PutMapping("update")
    public WellDTO updateWell(@RequestBody WellDTO well) throws Exception {

        return WellDTO.convertToDTO(wellService.updateWell(
                well.getWellId(),
                well.getWellCode(),
                well.getDepth(),
                well.getType(),
                well.getStatus()
        ));
    }

    @DeleteMapping("deleteById")
    public Boolean deleteWell(@RequestParam Long id) throws Exception {
        return wellService.deleteWell(id);
    }
}
