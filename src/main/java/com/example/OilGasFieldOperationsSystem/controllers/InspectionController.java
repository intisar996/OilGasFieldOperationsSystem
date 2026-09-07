package com.example.OilGasFieldOperationsSystem.controllers;

import com.example.OilGasFieldOperationsSystem.dto.InspectionDTO;
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
            @RequestBody InspectionDTO inspection) {

        return inspectionService.addInspection(
                inspection.getInspectionDate(),
                inspection.getResult(),
                inspection.getNotes(),
                inspection.getWellId(),
                inspection.getPipelineId(),
                inspection.getInspectorId()

        );
    }

    @GetMapping("getAll")
    public List<InspectionDTO> getAllInspection() {
        return InspectionDTO.convertToDTO(inspectionService.getAllInspection());
    }

    @GetMapping("getById")
    public InspectionDTO getById(@RequestParam Long id) {
        return InspectionDTO.convertToDTO(inspectionService.getById(id));
    }

    @PutMapping("update")
    public InspectionDTO updateInspection(
            @RequestBody InspectionDTO inspection) throws Exception {

        return InspectionDTO.convertToDTO(inspectionService.updateInspection(
                inspection.getInspectionId(),
                inspection.getInspectionDate(),
                inspection.getResult(),
                inspection.getNotes()
        ));
    }

    @DeleteMapping("deleteById")
    public Boolean deleteInspection(
            @RequestParam Long id) throws Exception {

        return inspectionService.deleteInspection(id);
    }
}
