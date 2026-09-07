package com.example.OilGasFieldOperationsSystem.controllers;

import com.example.OilGasFieldOperationsSystem.entities.Contractor;
import com.example.OilGasFieldOperationsSystem.services.ContractorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("Contractor")
public class ContractorController {

    ContractorService contractorService;

    @Autowired
    public ContractorController(ContractorService contractorService) {
        this.contractorService = contractorService;
    }

    @PostMapping("add")
    public Long addContractor(@RequestBody Contractor contractor) {

        return contractorService.addContractor(
                contractor.getName(),
                contractor.getEmail(),
                contractor.getPhoneNumber(),
                contractor.getCountry()
        );
    }

    @GetMapping("getAll")
    public List<Contractor> getAllContractor() {
        return contractorService.getAllContractor();
    }

    @GetMapping("getById")
    public Contractor getById(@RequestParam Long id) {
        return contractorService.getById(id);
    }

    @PutMapping("update")
    public Contractor updateContractor(
            @RequestBody Contractor contractor) throws Exception {

        return contractorService.updateContractor(
                contractor.getId(),
                contractor.getName(),
                contractor.getEmail(),
                contractor.getPhoneNumber(),
                contractor.getCountry()
        );
    }

    @DeleteMapping("deleteById")
    public Boolean deleteContractor(@RequestParam Long id) throws Exception {
        return contractorService.deleteContractor(id);
    }
}
