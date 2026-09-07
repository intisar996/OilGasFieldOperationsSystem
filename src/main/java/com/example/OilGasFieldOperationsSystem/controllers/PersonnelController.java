package com.example.OilGasFieldOperationsSystem.controllers;

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
    public Long addPersonnel(@RequestBody Personnel personnel) {

        return personnelService.addPersonnel(
                personnel.getName(),
                personnel.getRole(),
                personnel.getPhoneNumber(),
                personnel.getCertification(),
                personnel.getContractor().getId(),
                personnel.getOilField().getId()
        );
    }

    @GetMapping("getAll")
    public List<Personnel> getAllPersonnel() {
        return personnelService.getAllPersonnel();
    }

    @GetMapping("getById")
    public Personnel getById(@RequestParam Long id) {
        return personnelService.getById(id);
    }

    @PutMapping("update")
    public Personnel updatePersonnel(
            @RequestBody Personnel personnel) throws Exception {

        return personnelService.updatePersonnel(
                personnel.getId(),
                personnel.getName(),
                personnel.getRole(),
                personnel.getPhoneNumber(),
                personnel.getCertification()
        );
    }

    @DeleteMapping("deleteById")
    public Boolean deletePersonnel(
            @RequestParam Long id) throws Exception {

        return personnelService.deletePersonnel(id);
    }
}
