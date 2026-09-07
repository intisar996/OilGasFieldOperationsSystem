package com.example.OilGasFieldOperationsSystem.services;

import com.example.OilGasFieldOperationsSystem.entities.OilField;
import com.example.OilGasFieldOperationsSystem.entities.Rig;
import com.example.OilGasFieldOperationsSystem.entities.Well;
import com.example.OilGasFieldOperationsSystem.exceptions.ResourceNotFoundException;
import com.example.OilGasFieldOperationsSystem.repositories.OilFieldRepository;
import com.example.OilGasFieldOperationsSystem.repositories.RigRepository;
import com.example.OilGasFieldOperationsSystem.repositories.WellRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class WellService {

    WellRepository wellRepository;
    OilFieldService oilFieldService;
    RigService rigService;
    OilFieldRepository oilFieldRepository;
    RigRepository rigRepository;

    @Autowired
    public WellService(
            WellRepository wellRepository,
            OilFieldService oilFieldService,
            RigService rigService,
            OilFieldRepository oilFieldRepository,
            RigRepository rigRepository) {

        this.wellRepository = wellRepository;
        this.oilFieldService = oilFieldService;
        this.rigService = rigService;
        this.oilFieldRepository = oilFieldRepository;
        this.rigRepository = rigRepository;
    }

    public Long addWell(String wellCode, Double depth, String type,
                        String status, Long oilFieldId, Long rigId) {

        OilField oilField = oilFieldService.getById(oilFieldId);

        if (oilField == null ||
                oilField.getId() == null ||
                !oilField.getIsActive()) {

            throw new ResourceNotFoundException(
                    "Oil field not found with id: " + oilFieldId);
        }

        Rig rig = rigService.getById(rigId);

        if (rig == null ||
                rig.getId() == null ||
                !rig.getIsActive()) {

            throw new ResourceNotFoundException(
                    "Rig not found with id: " + rigId);
        }

        if (wellRepository.isRigAssign(rigId)) {

            throw new IllegalArgumentException(
                    "Rig is already assigned to another well");
        }

        Well well = new Well();

        well.setIsActive(true);
        well.setCreatedDate(new Date());
        well.setWellCode(wellCode);
        well.setDepth(depth);
        well.setType(type);
        well.setStatus(status);
        well.setOilField(oilField);
        well.setRig(rig);

        Well saveWell = wellRepository.save(well);

        return saveWell.getId();
    }

    public List<Well> getAllWell() {
        return wellRepository.getAllWell();
    }

    public Well getById(Long id) {

        Optional<Well> well =
                wellRepository.findById(id);

        if (well.isPresent() &&
                well.get().getIsActive()) {

            return well.get();
        }

        throw new ResourceNotFoundException(
                "Well not found with id: " + id);
    }

    public Well updateWell(Long id,
                           String wellCode,
                           Double depth,
                           String type,
                           String status) {

        Well wellToUpdate =
                wellRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Well not found with id: " + id));

        if (!wellToUpdate.getIsActive()) {
            throw new ResourceNotFoundException(
                    "Well not found with id: " + id);
        }

        wellToUpdate.setUpdateDate(new Date());
        wellToUpdate.setWellCode(wellCode);
        wellToUpdate.setDepth(depth);
        wellToUpdate.setType(type);
        wellToUpdate.setStatus(status);

        return wellRepository.save(wellToUpdate);
    }

    public Boolean deleteWell(Long id) {

        Well wellToUpdate =
                wellRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Well not found with id: " + id));

        if (!wellToUpdate.getIsActive()) {
            throw new ResourceNotFoundException(
                    "Well not found with id: " + id);
        }

        wellToUpdate.setUpdateDate(new Date());
        wellToUpdate.setIsActive(false);

        wellRepository.save(wellToUpdate);

        return true;
    }
}
