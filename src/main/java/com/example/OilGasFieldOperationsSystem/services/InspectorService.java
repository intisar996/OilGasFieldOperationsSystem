package com.example.OilGasFieldOperationsSystem.services;

import com.example.OilGasFieldOperationsSystem.entities.Inspector;
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

    public Long addInspector(String name, String licenseNumber,
                             String phoneNumber) {

        Inspector inspector = new Inspector();

        inspector.setIsActive(true);
        inspector.setCreatedDate(new Date());
        inspector.setName(name);
        inspector.setLicenseNumber(licenseNumber);
        inspector.setPhoneNumber(phoneNumber);

        Inspector saveInspector = inspectorRepository.save(inspector);

        return saveInspector.getId();
    }

    public List<Inspector> getAllInspector() {
        return inspectorRepository.getAllInspector();
    }

    public Inspector getById(Long id) {

        Optional<Inspector> inspector =
                inspectorRepository.findById(id);

        if (inspector.isPresent() && inspector.get().getIsActive()) {
            return inspector.get();
        }

        return new Inspector();
    }

    public Inspector updateInspector(Long id, String name,
                                     String licenseNumber,
                                     String phoneNumber) throws Exception {

        Inspector inspectorToUpdate =
                inspectorRepository.getById(id);

        if (inspectorToUpdate == null) {
            throw new Exception("Inspector is not found by the id");
        }

        inspectorToUpdate.setUpdateDate(new Date());
        inspectorToUpdate.setName(name);
        inspectorToUpdate.setLicenseNumber(licenseNumber);
        inspectorToUpdate.setPhoneNumber(phoneNumber);

        return inspectorRepository.save(inspectorToUpdate);
    }

    public Boolean deleteInspector(Long id) throws Exception {

        Inspector inspectorToUpdate =
                inspectorRepository.getById(id);

        if (inspectorToUpdate == null) {
            throw new Exception("Inspector is not found by the id");
        }

        inspectorToUpdate.setUpdateDate(new Date());
        inspectorToUpdate.setIsActive(false);

        inspectorRepository.save(inspectorToUpdate);

        return true;
    }
}
