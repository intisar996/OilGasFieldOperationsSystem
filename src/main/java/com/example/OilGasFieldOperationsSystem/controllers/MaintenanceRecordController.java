package com.example.OilGasFieldOperationsSystem.controllers;

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
            @RequestBody MaintenanceRecord maintenanceRecord) {

        return maintenanceRecordService.addMaintenanceRecord(
                maintenanceRecord.getMaintenanceDate(),
                maintenanceRecord.getDescription(),
                maintenanceRecord.getCost(),
                maintenanceRecord.getEquipment().getId(),
                maintenanceRecord.getTechnician().getId()
        );
    }

    @GetMapping("getAll")
    public List<MaintenanceRecord> getAllMaintenanceRecord() {
        return maintenanceRecordService.getAllMaintenanceRecord();
    }

    @GetMapping("getById")
    public MaintenanceRecord getById(@RequestParam Long id) {
        return maintenanceRecordService.getById(id);
    }

    @PutMapping("update")
    public MaintenanceRecord updateMaintenanceRecord(
            @RequestBody MaintenanceRecord maintenanceRecord)
            throws Exception {

        return maintenanceRecordService.updateMaintenanceRecord(
                maintenanceRecord.getId(),
                maintenanceRecord.getMaintenanceDate(),
                maintenanceRecord.getDescription(),
                maintenanceRecord.getCost()
        );
    }

    @DeleteMapping("deleteById")
    public Boolean deleteMaintenanceRecord(
            @RequestParam Long id) throws Exception {

        return maintenanceRecordService.deleteMaintenanceRecord(id);
    }
}
