package com.example.OilGasFieldOperationsSystem.controllers;

import com.example.OilGasFieldOperationsSystem.entities.Technician;
import com.example.OilGasFieldOperationsSystem.services.TechnicianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("Technician")
public class TechnicianController {

    TechnicianService technicianService;

    @Autowired
    public TechnicianController(TechnicianService technicianService) {
        this.technicianService = technicianService;
    }

    @PostMapping("add")
    public Long addTechnician(@RequestBody Technician technician) {

        return technicianService.addTechnician(
                technician.getName(),
                technician.getSpecialization(),
                technician.getPhoneNumber()
        );
    }

    @GetMapping("getAll")
    public List<Technician> getAllTechnician() {
        return technicianService.getAllTechnician();
    }

    @GetMapping("getById")
    public Technician getById(@RequestParam Long id) {
        return technicianService.getById(id);
    }

    @PutMapping("update")
    public Technician updateTechnician(
            @RequestBody Technician technician) throws Exception {

        return technicianService.updateTechnician(
                technician.getId(),
                technician.getName(),
                technician.getSpecialization(),
                technician.getPhoneNumber()
        );
    }

    @DeleteMapping("deleteById")
    public Boolean deleteTechnician(
            @RequestParam Long id) throws Exception {

        return technicianService.deleteTechnician(id);
    }
}
