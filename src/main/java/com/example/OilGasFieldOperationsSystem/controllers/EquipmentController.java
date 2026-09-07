package com.example.OilGasFieldOperationsSystem.controllers;

import com.example.OilGasFieldOperationsSystem.entities.Equipment;
import com.example.OilGasFieldOperationsSystem.services.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("Equipment")
public class EquipmentController {

    EquipmentService equipmentService;

    @Autowired
    public EquipmentController(EquipmentService equipmentService) {
        this.equipmentService = equipmentService;
    }

    @PostMapping("add")
    public Long addEquipment(@RequestBody Equipment equipment) {

        return equipmentService.addEquipment(
                equipment.getName(),
                equipment.getSerialNumber(),
                equipment.getType(),
                equipment.getStatus(),
                equipment.getOilField().getId()
        );
    }

    @GetMapping("getAll")
    public List<Equipment> getAllEquipment() {
        return equipmentService.getAllEquipment();
    }

    @GetMapping("getById")
    public Equipment getById(@RequestParam Long id) {
        return equipmentService.getById(id);
    }

    @PutMapping("update")
    public Equipment updateEquipment(
            @RequestBody Equipment equipment) throws Exception {

        return equipmentService.updateEquipment(
                equipment.getId(),
                equipment.getName(),
                equipment.getSerialNumber(),
                equipment.getType(),
                equipment.getStatus()
        );
    }

    @DeleteMapping("deleteById")
    public Boolean deleteEquipment(
            @RequestParam Long id) throws Exception {

        return equipmentService.deleteEquipment(id);
    }
}
