package com.example.OilGasFieldOperationsSystem.controllers;

import com.example.OilGasFieldOperationsSystem.dto.EquipmentDTO;
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
    public Long addEquipment(@RequestBody EquipmentDTO equipment) {

        return equipmentService.addEquipment(
                equipment.getName(),
                equipment.getSerialNumber(),
                equipment.getType(),
                equipment.getStatus(),
                equipment.getOilFieldId()
        );
    }

    @GetMapping("getAll")
    public List<EquipmentDTO> getAllEquipment() {
        return EquipmentDTO.convertToDTO(equipmentService.getAllEquipment());
    }

    @GetMapping("getById")
    public EquipmentDTO getById(@RequestParam Long id) {
        return EquipmentDTO.convertToDTO(equipmentService.getById(id));
    }

    @PutMapping("update")
    public EquipmentDTO updateEquipment(
            @RequestBody EquipmentDTO equipment) throws Exception {

        return EquipmentDTO.convertToDTO(equipmentService.updateEquipment(
                equipment.getEquipmentId(),
                equipment.getName(),
                equipment.getSerialNumber(),
                equipment.getType(),
                equipment.getStatus()
        ));
    }

    @DeleteMapping("deleteById")
    public Boolean deleteEquipment(
            @RequestParam Long id) throws Exception {

        return equipmentService.deleteEquipment(id);
    }
}
