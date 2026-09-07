package com.example.OilGasFieldOperationsSystem.controllers;

import com.example.OilGasFieldOperationsSystem.dto.PersonnelDTO;
import com.example.OilGasFieldOperationsSystem.entities.Personnel;
import com.example.OilGasFieldOperationsSystem.services.PersonnelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("Personnel")
public class PersonnelController {

    PersonnelService personnelService;

    @Autowired
    public PersonnelController(PersonnelService personnelService) {
        this.personnelService = personnelService;
    }

    @PostMapping("add")
    public Long addPersonnel(@RequestBody PersonnelDTO personnel) {

        return personnelService.addPersonnel(
                personnel.getName(),
                personnel.getRole(),
                personnel.getPhoneNumber(),
                personnel.getCertification(),
                personnel.getContractorId(),
                personnel.getOilFieldId()
        );
    }

    @GetMapping("getAll")
    public List<PersonnelDTO> getAllPersonnel() {
        return PersonnelDTO.convertToDTO(personnelService.getAllPersonnel());
    }

    @GetMapping("getById")
    public PersonnelDTO getById(@RequestParam Long id) {
        return PersonnelDTO.convertToDTO(personnelService.getById(id));
    }

    @PutMapping("update")
    public PersonnelDTO updatePersonnel(
            @RequestBody PersonnelDTO personnel) throws Exception {

        return PersonnelDTO.convertToDTO(personnelService.updatePersonnel (
                personnel.getPersonnelId(),
                personnel.getName(),
                personnel.getRole(),
                personnel.getPhoneNumber(),
                personnel.getCertification()
        ));
    }

    @DeleteMapping("deleteById")
    public Boolean deletePersonnel(
            @RequestParam Long id) throws Exception {

        return personnelService.deletePersonnel(id);
    }
}
