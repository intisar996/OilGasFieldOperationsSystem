package com.example.OilGasFieldOperationsSystem.controllers;

import com.example.OilGasFieldOperationsSystem.entities.Inspection;
import com.example.OilGasFieldOperationsSystem.services.InspectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("Inspection")
public class InspectionController {

    InspectionService inspectionService;

    @Autowired
    public InspectionController(InspectionService inspectionService) {
        this.inspectionService = inspectionService;
    }

    @PostMapping("add")
    public Long addInspection(
            @RequestBody Inspection inspection) {

        Long wellId = null;
        Long pipelineId = null;

        if (inspection.getWell() != null) {
            wellId = inspection.getWell().getId();
        }

        if (inspection.getPipeline() != null) {
            pipelineId = inspection.getPipeline().getId();
        }

        return inspectionService.addInspection(
                inspection.getInspectionDate(),
                inspection.getResult(),
                inspection.getNotes(),
                wellId,
                pipelineId,
                inspection.getInspector().getId()
        );
    }

    @GetMapping("getAll")
    public List<Inspection> getAllInspection() {
        return inspectionService.getAllInspection();
    }

    @GetMapping("getById")
    public Inspection getById(@RequestParam Long id) {
        return inspectionService.getById(id);
    }

    @PutMapping("update")
    public Inspection updateInspection(
            @RequestBody Inspection inspection) throws Exception {

        return inspectionService.updateInspection(
                inspection.getId(),
                inspection.getInspectionDate(),
                inspection.getResult(),
                inspection.getNotes()
        );
    }

    @DeleteMapping("deleteById")
    public Boolean deleteInspection(
            @RequestParam Long id) throws Exception {

        return inspectionService.deleteInspection(id);
    }
}
