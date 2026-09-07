package com.example.OilGasFieldOperationsSystem.services;

import com.example.OilGasFieldOperationsSystem.entities.Inspector;
import com.example.OilGasFieldOperationsSystem.exceptions.ResourceNotFoundException;
import com.example.OilGasFieldOperationsSystem.repositories.InspectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class InspectorService {

    InspectorRepository inspectorRepository;

    @Autowired
    public InspectorService(InspectorRepository inspectorRepository) {
        this.inspectorRepository = inspectorRepository;
    }

    public Long addInspector(String name,
                             String licenseNumber,
                             String phoneNumber) {

        Inspector inspector = new Inspector();

        inspector.setIsActive(true);
        inspector.setCreatedDate(new Date());
        inspector.setName(name);
        inspector.setLicenseNumber(licenseNumber);
        inspector.setPhoneNumber(phoneNumber);

        Inspector saveInspector =
                inspectorRepository.save(inspector);

        return saveInspector.getId();
    }


    public List<Inspector> getAllInspector() {

        return inspectorRepository.getAllInspector();
    }


    public Inspector getById(Long id) {

        Optional<Inspector> inspector =
                inspectorRepository.findById(id);

        if (inspector.isPresent() &&
                inspector.get().getIsActive()) {

            return inspector.get();
        }

        throw new ResourceNotFoundException(
                "Inspector not found with id: " + id
        );
    }


    public Inspector updateInspector(
            Long id,
            String name,
            String licenseNumber,
            String phoneNumber) {

        Inspector inspectorToUpdate =
                inspectorRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Inspector not found with id: " + id
                                )
                        );

        if (!inspectorToUpdate.getIsActive()) {

            throw new ResourceNotFoundException(
                    "Inspector not found with id: " + id
            );
        }

        inspectorToUpdate.setUpdateDate(new Date());
        inspectorToUpdate.setName(name);
        inspectorToUpdate.setLicenseNumber(licenseNumber);
        inspectorToUpdate.setPhoneNumber(phoneNumber);

        return inspectorRepository.save(inspectorToUpdate);
    }


    public Boolean deleteInspector(Long id) {

        Inspector inspectorToUpdate =
                inspectorRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Inspector not found with id: " + id
                                )
                        );

        if (!inspectorToUpdate.getIsActive()) {

            throw new ResourceNotFoundException(
                    "Inspector not found with id: " + id
            );
        }

        inspectorToUpdate.setUpdateDate(new Date());
        inspectorToUpdate.setIsActive(false);

        inspectorRepository.save(inspectorToUpdate);

        return true;
    }
}
