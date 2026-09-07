package com.example.OilGasFieldOperationsSystem.services;

import com.example.OilGasFieldOperationsSystem.entities.OilField;
import com.example.OilGasFieldOperationsSystem.repositories.OilFieldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OilFieldService {

    OilFieldRepository oilFieldRepository;

    @Autowired
    public OilFieldService(OilFieldRepository oilFieldRepository) {
        this.oilFieldRepository = oilFieldRepository;
    }

    public Long addOilField(String name, String location, String region) {

        OilField oilField = new OilField();

        oilField.setIsActive(true);
        oilField.setCreatedDate(new Date());
        oilField.setName(name);
        oilField.setLocation(location);
        oilField.setRegion(region);

        OilField saveOilField = oilFieldRepository.save(oilField);

        return saveOilField.getId();
    }

    public List<OilField> getAllOilField() {
        return oilFieldRepository.getAllOilField();
    }

    public OilField getById(Long id) {

        Optional<OilField> oilField = oilFieldRepository.findById(id);

        if (oilField.isPresent() && oilField.get().getIsActive()) {
            return oilField.get();
        }

        return new OilField();
    }

    public OilField updateOilField(Long id, String name, String location, String region) throws Exception {

        OilField oilFieldToUpdate = oilFieldRepository.getById(id);

        if (oilFieldToUpdate == null) {
            throw new Exception("OilField is not found by the id");
        }

        oilFieldToUpdate.setUpdateDate(new Date());
        oilFieldToUpdate.setName(name);
        oilFieldToUpdate.setLocation(location);
        oilFieldToUpdate.setRegion(region);

        oilFieldToUpdate = oilFieldRepository.save(oilFieldToUpdate);

        return oilFieldToUpdate;
    }

    public Boolean deleteOilField(Long id) throws Exception {

        OilField oilFieldToUpdate = oilFieldRepository.getById(id);

        if (oilFieldToUpdate == null) {
            throw new Exception("OilField is not found by the id");
        }

        oilFieldToUpdate.setUpdateDate(new Date());
        oilFieldToUpdate.setIsActive(false);

        oilFieldRepository.save(oilFieldToUpdate);

        return true;
    }
}
