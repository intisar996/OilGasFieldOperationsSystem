package com.example.OilGasFieldOperationsSystem.services;

import com.example.OilGasFieldOperationsSystem.entities.Equipment;
import com.example.OilGasFieldOperationsSystem.entities.OilField;
import com.example.OilGasFieldOperationsSystem.exceptions.ResourceNotFoundException;
import com.example.OilGasFieldOperationsSystem.repositories.EquipmentRepository;
import com.example.OilGasFieldOperationsSystem.repositories.OilFieldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EquipmentService {

    EquipmentRepository equipmentRepository;
    OilFieldService oilFieldService;
    OilFieldRepository oilFieldRepository;

    @Autowired
    public EquipmentService(EquipmentRepository equipmentRepository,
                            OilFieldService oilFieldService,
                            OilFieldRepository oilFieldRepository) {

        this.equipmentRepository = equipmentRepository;
        this.oilFieldService = oilFieldService;
        this.oilFieldRepository = oilFieldRepository;
    }

    public Long addEquipment(String name, String serialNumber,
                             String type, String status,
                             Long oilFieldId) {

        OilField oilField = oilFieldService.getById(oilFieldId);

        if (oilField == null || oilField.getId() == null ||
                !oilField.getIsActive()) {
            throw  new ResourceNotFoundException("oilField is not found by the id");

        }

        Equipment equipment = new Equipment();

        equipment.setIsActive(true);
        equipment.setCreatedDate(new Date());
        equipment.setName(name);
        equipment.setSerialNumber(serialNumber);
        equipment.setType(type);
        equipment.setStatus(status);
        equipment.setOilField(oilField);

        Equipment saveEquipment = equipmentRepository.save(equipment);

        return saveEquipment.getId();
    }

    public List<Equipment> getAllEquipment() {
        return equipmentRepository.getAllEquipment();
    }

    public Equipment getById(Long id) {

        Optional<Equipment> equipment = equipmentRepository.findById(id);

        if (equipment.isPresent() && equipment.get().getIsActive()) {
            return equipment.get();
        }

        return new Equipment();
    }

    public Equipment updateEquipment(Long id, String name,
                                     String serialNumber, String type,
                                     String status) throws Exception {

        Equipment equipmentToUpdate = equipmentRepository.getById(id);

        if (equipmentToUpdate == null) {
            throw  new ResourceNotFoundException("Equipment is not found by the id");
        }

        equipmentToUpdate.setUpdateDate(new Date());
        equipmentToUpdate.setName(name);
        equipmentToUpdate.setSerialNumber(serialNumber);
        equipmentToUpdate.setType(type);
        equipmentToUpdate.setStatus(status);

        return equipmentRepository.save(equipmentToUpdate);
    }

    public Boolean deleteEquipment(Long id) throws Exception {

        Equipment equipmentToUpdate = equipmentRepository.getById(id);

        if (equipmentToUpdate == null) {
            throw  new ResourceNotFoundException("Equipment is not found by the id");

        }

        equipmentToUpdate.setUpdateDate(new Date());
        equipmentToUpdate.setIsActive(false);

        equipmentRepository.save(equipmentToUpdate);

        return true;
    }
}
