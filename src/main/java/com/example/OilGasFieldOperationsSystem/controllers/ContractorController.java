package com.example.OilGasFieldOperationsSystem.controllers;

import com.example.OilGasFieldOperationsSystem.dto.ContractorDTO;
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
    public Long addContractor(@RequestBody ContractorDTO contractor) {

        return contractorService.addContractor(
                contractor.getName(),
                contractor.getEmail(),
                contractor.getPhoneNumber(),
                contractor.getCountry()
        );
    }

    @GetMapping("getAll")
    public List<ContractorDTO> getAllContractor() {
        return  ContractorDTO.convertToDTO(contractorService.getAllContractor());
    }

    @GetMapping("getById")
    public ContractorDTO getById(@RequestParam Long id) {
        return ContractorDTO.convertToDTO(contractorService.getById(id));
    }

    @PutMapping("update")
    public ContractorDTO updateContractor(
            @RequestBody ContractorDTO contractor) throws Exception {

        return ContractorDTO.convertToDTO(contractorService.updateContractor(
                contractor.getContractorId(),
                contractor.getName(),
                contractor.getEmail(),
                contractor.getPhoneNumber(),
                contractor.getCountry()
        ));
    }

    @DeleteMapping("deleteById")
    public Boolean deleteContractor(@RequestParam Long id) throws Exception {
        return contractorService.deleteContractor(id);
    }
}
