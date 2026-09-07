package com.example.OilGasFieldOperationsSystem.controllers;

import com.example.OilGasFieldOperationsSystem.dto.MaintenanceRecordDTO;
import com.example.OilGasFieldOperationsSystem.entities.MaintenanceRecord;
import com.example.OilGasFieldOperationsSystem.services.MaintenanceRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("MaintenanceRecord")
public class MaintenanceRecordController {

    MaintenanceRecordService maintenanceRecordService;

    @Autowired
    public MaintenanceRecordController(
            MaintenanceRecordService maintenanceRecordService) {

        this.maintenanceRecordService = maintenanceRecordService;
    }

    @PostMapping("add")
    public Long addMaintenanceRecord(
            @RequestBody MaintenanceRecordDTO maintenanceRecord) {

        return maintenanceRecordService.addMaintenanceRecord(
                maintenanceRecord.getMaintenanceDate(),
                maintenanceRecord.getDescription(),
                maintenanceRecord.getCost(),
                maintenanceRecord.getEquipmentId(),
                maintenanceRecord.getTechnicianId()
        );
    }


    @GetMapping("getMaintenanceByEquipment")
    public List<MaintenanceRecordDTO> getMaintenanceByEquipment(Long equpmentId) {
        return MaintenanceRecordDTO.convertToDTO(maintenanceRecordService.getMaintenanceByEquipment(equpmentId));
    }

    @GetMapping("getAll")
    public List<MaintenanceRecordDTO> getAllMaintenanceRecord() {
        return MaintenanceRecordDTO.convertToDTO(maintenanceRecordService.getAllMaintenanceRecord());
    }

    @GetMapping("getById")
    public MaintenanceRecordDTO getById(@RequestParam Long id) {
        return MaintenanceRecordDTO.convertToDTO(maintenanceRecordService.getById(id));
    }

    @PutMapping("update")
    public MaintenanceRecordDTO updateMaintenanceRecord(
            @RequestBody MaintenanceRecordDTO maintenanceRecord)
            throws Exception {

        return MaintenanceRecordDTO.convertToDTO(maintenanceRecordService.updateMaintenanceRecord(
                maintenanceRecord.getEquipmentId(),
                maintenanceRecord.getMaintenanceDate(),
                maintenanceRecord.getDescription(),
                maintenanceRecord.getCost()
        ));
    }

    @DeleteMapping("deleteById")
    public Boolean deleteMaintenanceRecord(
            @RequestParam Long id) throws Exception {

        return maintenanceRecordService.deleteMaintenanceRecord(id);
    }
}
