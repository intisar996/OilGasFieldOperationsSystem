package com.example.OilGasFieldOperationsSystem.services;

import com.example.OilGasFieldOperationsSystem.entities.Equipment;
import com.example.OilGasFieldOperationsSystem.entities.MaintenanceRecord;
import com.example.OilGasFieldOperationsSystem.entities.Technician;
import com.example.OilGasFieldOperationsSystem.exceptions.ResourceNotFoundException;
import com.example.OilGasFieldOperationsSystem.repositories.EquipmentRepository;
import com.example.OilGasFieldOperationsSystem.repositories.MaintenanceRecordRepository;
import com.example.OilGasFieldOperationsSystem.repositories.TechnicianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MaintenanceRecordService {

    MaintenanceRecordRepository maintenanceRecordRepository;
    EquipmentService equipmentService;
    TechnicianService technicianService;
    EquipmentRepository equipmentRepository;
    TechnicianRepository technicianRepository;

    @Autowired
    public MaintenanceRecordService(
            MaintenanceRecordRepository maintenanceRecordRepository,
            EquipmentService equipmentService,
            TechnicianService technicianService,
            EquipmentRepository equipmentRepository,
            TechnicianRepository technicianRepository) {

        this.maintenanceRecordRepository = maintenanceRecordRepository;
        this.equipmentService = equipmentService;
        this.technicianService = technicianService;
        this.equipmentRepository = equipmentRepository;
        this.technicianRepository = technicianRepository;
    }

    public Long addMaintenanceRecord(
            Date maintenanceDate,
            String description,
            Double cost,
            Long equipmentId,
            Long technicianId) {

        Equipment equipment = equipmentService.getById(equipmentId);
        Technician technician = technicianService.getById(technicianId);

        if (equipment == null ||
                equipment.getId() == null ||
                !equipment.getIsActive()) {

            throw new ResourceNotFoundException(
                    "Equipment not found with id: " + equipmentId
            );
        }

        if (technician == null ||
                technician.getId() == null ||
                !technician.getIsActive()) {

            throw new ResourceNotFoundException(
                    "Technician not found with id: " + technicianId
            );
        }

        MaintenanceRecord maintenanceRecord = new MaintenanceRecord();

        maintenanceRecord.setIsActive(true);
        maintenanceRecord.setCreatedDate(new Date());
        maintenanceRecord.setMaintenanceDate(maintenanceDate);
        maintenanceRecord.setDescription(description);
        maintenanceRecord.setCost(cost);
        maintenanceRecord.setEquipment(equipment);
        maintenanceRecord.setTechnician(technician);

        // Set equipment status
        equipment.setStatus("MAINTENANCE");
        equipmentRepository.save(equipment);

        MaintenanceRecord saveMaintenanceRecord =
                maintenanceRecordRepository.save(maintenanceRecord);

        return saveMaintenanceRecord.getId();
    }


    public List<MaintenanceRecord> getMaintenanceByEquipment(
            Long equipmentId) {

        Equipment equipment = equipmentService.getById(equipmentId);

        if (equipment == null ||
                equipment.getId() == null ||
                !equipment.getIsActive()) {

            throw new ResourceNotFoundException(
                    "Equipment not found with id: " + equipmentId
            );
        }

        return maintenanceRecordRepository
                .getMaintenanceByEquipment(equipmentId);
    }


    public List<MaintenanceRecord> getAllMaintenanceRecord() {

        return maintenanceRecordRepository.getAllMaintenanceRecord();
    }


    public MaintenanceRecord getById(Long id) {

        Optional<MaintenanceRecord> maintenanceRecord =
                maintenanceRecordRepository.findById(id);

        if (maintenanceRecord.isPresent() &&
                maintenanceRecord.get().getIsActive()) {

            return maintenanceRecord.get();
        }

        throw new ResourceNotFoundException(
                "MaintenanceRecord not found with id: " + id
        );
    }


    public MaintenanceRecord updateMaintenanceRecord(
            Long id,
            Date maintenanceDate,
            String description,
            Double cost) {

        MaintenanceRecord maintenanceRecordToUpdate =
                maintenanceRecordRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "MaintenanceRecord not found with id: " + id
                                )
                        );

        if (!maintenanceRecordToUpdate.getIsActive()) {

            throw new ResourceNotFoundException(
                    "MaintenanceRecord not found with id: " + id
            );
        }

        maintenanceRecordToUpdate.setUpdateDate(new Date());
        maintenanceRecordToUpdate.setMaintenanceDate(maintenanceDate);
        maintenanceRecordToUpdate.setDescription(description);
        maintenanceRecordToUpdate.setCost(cost);

        return maintenanceRecordRepository.save(
                maintenanceRecordToUpdate
        );
    }


    public Boolean deleteMaintenanceRecord(Long id) {

        MaintenanceRecord maintenanceRecordToUpdate =
                maintenanceRecordRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "MaintenanceRecord not found with id: " + id
                                )
                        );

        if (!maintenanceRecordToUpdate.getIsActive()) {

            throw new ResourceNotFoundException(
                    "MaintenanceRecord not found with id: " + id
            );
        }

        maintenanceRecordToUpdate.setUpdateDate(new Date());
        maintenanceRecordToUpdate.setIsActive(false);

        maintenanceRecordRepository.save(
                maintenanceRecordToUpdate
        );

        return true;
    }
}
