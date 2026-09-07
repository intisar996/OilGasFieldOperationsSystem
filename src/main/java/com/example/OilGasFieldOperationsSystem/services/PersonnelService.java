package com.example.OilGasFieldOperationsSystem.services;

import com.example.OilGasFieldOperationsSystem.entities.Contractor;
import com.example.OilGasFieldOperationsSystem.entities.OilField;
import com.example.OilGasFieldOperationsSystem.entities.Personnel;
import com.example.OilGasFieldOperationsSystem.exceptions.ResourceNotFoundException;
import com.example.OilGasFieldOperationsSystem.repositories.ContractorRepository;
import com.example.OilGasFieldOperationsSystem.repositories.OilFieldRepository;
import com.example.OilGasFieldOperationsSystem.repositories.PersonnelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PersonnelService {

    PersonnelRepository personnelRepository;
    ContractorService contractorService;
    OilFieldService oilFieldService;
    ContractorRepository contractorRepository;
    OilFieldRepository oilFieldRepository;

    @Autowired
    public PersonnelService(PersonnelRepository personnelRepository,
                            ContractorService contractorService,
                            OilFieldService oilFieldService,
                            ContractorRepository contractorRepository,
                            OilFieldRepository oilFieldRepository) {

        this.personnelRepository = personnelRepository;
        this.contractorService = contractorService;
        this.oilFieldService = oilFieldService;
        this.contractorRepository = contractorRepository;
        this.oilFieldRepository = oilFieldRepository;
    }

    public Long addPersonnel(String name, String role,
                             String phoneNumber, String certification,
                             Long contractorId, Long oilFieldId) {

        Contractor contractor = contractorService.getById(contractorId);
        OilField oilField = oilFieldService.getById(oilFieldId);

        if (contractor == null || contractor.getId() == null ||
                !contractor.getIsActive()) {

            throw new ResourceNotFoundException(
                    "Contractor not found with id: " + contractorId
            );
        }

        if (oilField == null || oilField.getId() == null ||
                !oilField.getIsActive()) {

            throw new ResourceNotFoundException(
                    "OilField not found with id: " + oilFieldId
            );
        }

        Personnel personnel = new Personnel();

        personnel.setIsActive(true);
        personnel.setCreatedDate(new Date());
        personnel.setName(name);
        personnel.setRole(role);
        personnel.setPhoneNumber(phoneNumber);
        personnel.setCertification(certification);
        personnel.setContractor(contractor);
        personnel.setOilField(oilField);

        Personnel savePersonnel = personnelRepository.save(personnel);

        return savePersonnel.getId();
    }

    public List<Personnel> getAllPersonnel() {
        return personnelRepository.getAllPersonnel();
    }

    public Personnel getById(Long id) {

        Optional<Personnel> personnel =
                personnelRepository.findById(id);

        if (personnel.isPresent() &&
                personnel.get().getIsActive()) {

            return personnel.get();
        }

        throw new ResourceNotFoundException(
                "Personnel not found with id: " + id
        );
    }

    public Personnel updatePersonnel(Long id,
                                     String name,
                                     String role,
                                     String phoneNumber,
                                     String certification) {

        Personnel personnelToUpdate =
                personnelRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Personnel not found with id: " + id
                                )
                        );

        if (!personnelToUpdate.getIsActive()) {
            throw new ResourceNotFoundException(
                    "Personnel not found with id: " + id
            );
        }

        personnelToUpdate.setUpdateDate(new Date());
        personnelToUpdate.setName(name);
        personnelToUpdate.setRole(role);
        personnelToUpdate.setPhoneNumber(phoneNumber);
        personnelToUpdate.setCertification(certification);

        return personnelRepository.save(personnelToUpdate);
    }

    public Boolean deletePersonnel(Long id) {

        Personnel personnelToUpdate =
                personnelRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Personnel not found with id: " + id
                                )
                        );

        if (!personnelToUpdate.getIsActive()) {
            throw new ResourceNotFoundException(
                    "Personnel not found with id: " + id
            );
        }

        personnelToUpdate.setUpdateDate(new Date());
        personnelToUpdate.setIsActive(false);

        personnelRepository.save(personnelToUpdate);

        return true;
    }
}