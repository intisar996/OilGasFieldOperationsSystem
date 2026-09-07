package com.example.OilGasFieldOperationsSystem.services;

import com.example.OilGasFieldOperationsSystem.entities.OilField;
import com.example.OilGasFieldOperationsSystem.entities.StorageTank;
import com.example.OilGasFieldOperationsSystem.repositories.OilFieldRepository;
import com.example.OilGasFieldOperationsSystem.repositories.StorageTankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class StorageTankService {

    StorageTankRepository storageTankRepository;
    OilFieldService oilFieldService;
    OilFieldRepository oilFieldRepository;

    @Autowired
    public StorageTankService(StorageTankRepository storageTankRepository,
                              OilFieldService oilFieldService,
                              OilFieldRepository oilFieldRepository) {

        this.storageTankRepository = storageTankRepository;
        this.oilFieldService = oilFieldService;
        this.oilFieldRepository = oilFieldRepository;
    }

    public Long addStorageTank(String tankCode, Double capacity,
                               Double currentLevel, String product,
                               Long oilFieldId) {

        OilField oilField = oilFieldService.getById(oilFieldId);

        if (oilField == null || oilField.getId() == null || !oilField.getIsActive()) {
            return -1L;
        }

        StorageTank storageTank = new StorageTank();

        storageTank.setIsActive(true);
        storageTank.setCreatedDate(new Date());
        storageTank.setTankCode(tankCode);
        storageTank.setCapacity(capacity);
        storageTank.setCurrentLevel(currentLevel);
        storageTank.setProduct(product);
        storageTank.setOilField(oilField);

        StorageTank saveStorageTank = storageTankRepository.save(storageTank);

        return saveStorageTank.getId();
    }

    public List<StorageTank> getAllStorageTank() {
        return storageTankRepository.getAllStorageTank();
    }

    public StorageTank getById(Long id) {

        Optional<StorageTank> storageTank =
                storageTankRepository.findById(id);

        if (storageTank.isPresent() && storageTank.get().getIsActive()) {
            return storageTank.get();
        }

        return new StorageTank();
    }

    public StorageTank updateStorageTank(Long id, String tankCode,
                                         Double capacity, Double currentLevel,
                                         String product) throws Exception {

        StorageTank storageTankToUpdate =
                storageTankRepository.getById(id);

        if (storageTankToUpdate == null) {
            throw new Exception("StorageTank is not found by the id");
        }

        storageTankToUpdate.setUpdateDate(new Date());
        storageTankToUpdate.setTankCode(tankCode);
        storageTankToUpdate.setCapacity(capacity);
        storageTankToUpdate.setCurrentLevel(currentLevel);
        storageTankToUpdate.setProduct(product);

        return storageTankRepository.save(storageTankToUpdate);
    }

    public Boolean deleteStorageTank(Long id) throws Exception {

        StorageTank storageTankToUpdate =
                storageTankRepository.getById(id);

        if (storageTankToUpdate == null) {
            throw new Exception("StorageTank is not found by the id");
        }

        storageTankToUpdate.setUpdateDate(new Date());
        storageTankToUpdate.setIsActive(false);

        storageTankRepository.save(storageTankToUpdate);

        return true;
    }
}
