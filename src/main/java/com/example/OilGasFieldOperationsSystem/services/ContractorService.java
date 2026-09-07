package com.example.OilGasFieldOperationsSystem.services;

import com.example.OilGasFieldOperationsSystem.entities.Contractor;
import com.example.OilGasFieldOperationsSystem.repositories.ContractorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ContractorService {

    ContractorRepository contractorRepository;

    @Autowired
    public ContractorService(ContractorRepository contractorRepository) {
        this.contractorRepository = contractorRepository;
    }

    public Long addContractor(String name, String email,
                              String phoneNumber, String country) {

        Contractor contractor = new Contractor();
        contractor.setIsActive(true);
        contractor.setCreatedDate(new Date());
        contractor.setName(name);
        contractor.setEmail(email);
        contractor.setPhoneNumber(phoneNumber);
        contractor.setCountry(country);

        Contractor saveContractor = contractorRepository.save(contractor);

        return saveContractor.getId();
    }

    public List<Contractor> getAllContractor() {
        return contractorRepository.getAllContractor();
    }

    public Contractor getById(Long id) {

        Optional<Contractor> contractor = contractorRepository.findById(id);

        if (contractor.isPresent() && contractor.get().getIsActive()) {
            return contractor.get();
        }

        return new Contractor();
    }

    public Contractor updateContractor(Long id, String name, String email,
                                       String phoneNumber, String country) throws Exception {

        Contractor contractorToUpdate = contractorRepository.getById(id);

        if (contractorToUpdate == null) {
            throw new Exception("Contractor is not found by the id");
        }

        contractorToUpdate.setUpdateDate(new Date());
        contractorToUpdate.setName(name);
        contractorToUpdate.setEmail(email);
        contractorToUpdate.setPhoneNumber(phoneNumber);
        contractorToUpdate.setCountry(country);

        return contractorRepository.save(contractorToUpdate);
    }

    public Boolean deleteContractor(Long id) throws Exception {

        Contractor contractorToUpdate = contractorRepository.getById(id);

        if (contractorToUpdate == null) {
            throw new Exception("Contractor is not found by the id");
        }

        contractorToUpdate.setUpdateDate(new Date());
        contractorToUpdate.setIsActive(false);

        contractorRepository.save(contractorToUpdate);

        return true;
    }
}
