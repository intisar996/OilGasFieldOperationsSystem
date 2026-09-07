package com.example.OilGasFieldOperationsSystem.controllers;

import com.example.OilGasFieldOperationsSystem.dto.TechnicianDTO;
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
    public Long addTechnician(@RequestBody TechnicianDTO technician) {

        return technicianService.addTechnician(
                technician.getName(),
                technician.getSpecialization(),
                technician.getPhoneNumber()
        );
    }

    @GetMapping("getAll")
    public List<TechnicianDTO> getAllTechnician() {
        return TechnicianDTO.convertToDTO(technicianService.getAllTechnician());
    }

    @GetMapping("getById")
    public TechnicianDTO getById(@RequestParam Long id) {
        return TechnicianDTO.convertToDTO(technicianService.getById(id));
    }

    @PutMapping("update")
    public TechnicianDTO updateTechnician(
            @RequestBody TechnicianDTO technician) throws Exception {

        return TechnicianDTO.convertToDTO(technicianService.updateTechnician(
                technician.getTechnicianId(),
                technician.getName(),
                technician.getSpecialization(),
                technician.getPhoneNumber()
        ));
    }

    @DeleteMapping("deleteById")
    public Boolean deleteTechnician(
            @RequestParam Long id) throws Exception {

        return technicianService.deleteTechnician(id);
    }
}
