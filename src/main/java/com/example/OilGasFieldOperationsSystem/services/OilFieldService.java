package com.example.OilGasFieldOperationsSystem.services;

import com.example.OilGasFieldOperationsSystem.entities.OilField;
import com.example.OilGasFieldOperationsSystem.exceptions.ResourceNotFoundException;
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

    public Long addOilField(
            String name,
            String location,
            String region) {

        OilField oilField = new OilField();

        oilField.setIsActive(true);
        oilField.setCreatedDate(new Date());
        oilField.setName(name);
        oilField.setLocation(location);
        oilField.setRegion(region);

        OilField saveOilField =
                oilFieldRepository.save(oilField);

        return saveOilField.getId();
    }


    public List<OilField> getAllOilField() {

        return oilFieldRepository.getAllOilField();
    }


    public OilField getById(Long id) {

        Optional<OilField> oilField =
                oilFieldRepository.findById(id);

        if (oilField.isPresent() &&
                oilField.get().getIsActive()) {

            return oilField.get();
        }

        throw new ResourceNotFoundException(
                "OilField not found with id: " + id
        );
    }


    public OilField updateOilField(
            Long id,
            String name,
            String location,
            String region) {

        OilField oilFieldToUpdate =
                oilFieldRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "OilField not found with id: " + id
                                )
                        );

        if (!oilFieldToUpdate.getIsActive()) {

            throw new ResourceNotFoundException(
                    "OilField not found with id: " + id
            );
        }

        oilFieldToUpdate.setUpdateDate(new Date());
        oilFieldToUpdate.setName(name);
        oilFieldToUpdate.setLocation(location);
        oilFieldToUpdate.setRegion(region);

        return oilFieldRepository.save(oilFieldToUpdate);
    }


    public Boolean deleteOilField(Long id) {

        OilField oilFieldToUpdate =
                oilFieldRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "OilField not found with id: " + id
                                )
                        );

        if (!oilFieldToUpdate.getIsActive()) {

            throw new ResourceNotFoundException(
                    "OilField not found with id: " + id
            );
        }

        oilFieldToUpdate.setUpdateDate(new Date());
        oilFieldToUpdate.setIsActive(false);

        oilFieldRepository.save(oilFieldToUpdate);

        return true;
    }
}
