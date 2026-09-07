package com.example.OilGasFieldOperationsSystem.services;

import com.example.OilGasFieldOperationsSystem.entities.Contractor;
import com.example.OilGasFieldOperationsSystem.entities.Rig;
import com.example.OilGasFieldOperationsSystem.exceptions.ResourceNotFoundException;
import com.example.OilGasFieldOperationsSystem.repositories.ContractorRepository;
import com.example.OilGasFieldOperationsSystem.repositories.RigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class RigService {

    RigRepository rigRepository;
    ContractorService contractorService;
    ContractorRepository contractorRepository;

    @Autowired
    public RigService(RigRepository rigRepository,
                      ContractorService contractorService,
                      ContractorRepository contractorRepository) {

        this.rigRepository = rigRepository;
        this.contractorService = contractorService;
        this.contractorRepository = contractorRepository;
    }

    public Long addRig(String name,
                       String model,
                       Double capacity,
                       Long contractorId) {

        Contractor contractor =
                contractorService.getById(contractorId);

        if (contractor == null ||
                contractor.getId() == null ||
                !contractor.getIsActive()) {

            throw new ResourceNotFoundException(
                    "Contractor not found with id: " + contractorId
            );
        }

        Rig rig = new Rig();

        rig.setIsActive(true);
        rig.setCreatedDate(new Date());
        rig.setName(name);
        rig.setModel(model);
        rig.setCapacity(capacity);
        rig.setContractor(contractor);

        Rig saveRig = rigRepository.save(rig);

        return saveRig.getId();
    }

    public List<Rig> getAllRig() {
        return rigRepository.getAllRig();
    }

    public Rig getById(Long id) {

        Optional<Rig> rig =
                rigRepository.findById(id);

        if (rig.isPresent() &&
                rig.get().getIsActive()) {

            return rig.get();
        }

        throw new ResourceNotFoundException(
                "Rig not found with id: " + id
        );
    }

    public Rig updateRig(Long id,
                         String name,
                         String model,
                         Double capacity) {

        Rig rigToUpdate =
                rigRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Rig not found with id: " + id
                                )
                        );

        if (!rigToUpdate.getIsActive()) {
            throw new ResourceNotFoundException(
                    "Rig not found with id: " + id
            );
        }

        rigToUpdate.setUpdateDate(new Date());
        rigToUpdate.setName(name);
        rigToUpdate.setModel(model);
        rigToUpdate.setCapacity(capacity);

        return rigRepository.save(rigToUpdate);
    }

    public Boolean deleteRig(Long id) {

        Rig rigToUpdate =
                rigRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Rig not found with id: " + id
                                )
                        );

        if (!rigToUpdate.getIsActive()) {
            throw new ResourceNotFoundException(
                    "Rig not found with id: " + id
            );
        }

        rigToUpdate.setUpdateDate(new Date());
        rigToUpdate.setIsActive(false);

        rigRepository.save(rigToUpdate);

        return true;
    }
}