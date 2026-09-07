package com.example.OilGasFieldOperationsSystem.controllers;

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
    public Long addWell(@RequestBody Well well) {

        return wellService.addWell(
                well.getWellCode(),
                well.getDepth(),
                well.getType(),
                well.getStatus(),
                well.getOilField().getId(),
                well.getRig().getId()
        );
    }

    @GetMapping("getAll")
    public List<Well> getAllWell() {
        return wellService.getAllWell();
    }

    @GetMapping("getById")
    public Well getById(@RequestParam Long id) {
        return wellService.getById(id);
    }

    @PutMapping("update")
    public Well updateWell(@RequestBody Well well) throws Exception {

        return wellService.updateWell(
                well.getId(),
                well.getWellCode(),
                well.getDepth(),
                well.getType(),
                well.getStatus()
        );
    }

    @DeleteMapping("deleteById")
    public Boolean deleteWell(@RequestParam Long id) throws Exception {
        return wellService.deleteWell(id);
    }
}
