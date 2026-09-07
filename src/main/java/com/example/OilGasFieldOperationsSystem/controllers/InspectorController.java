package com.example.OilGasFieldOperationsSystem.controllers;

import com.example.OilGasFieldOperationsSystem.dto.InspectorDTO;
import com.example.OilGasFieldOperationsSystem.entities.Inspector;
import com.example.OilGasFieldOperationsSystem.services.InspectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("Inspector")
public class InspectorController {

    InspectorService inspectorService;

    @Autowired
    public InspectorController(InspectorService inspectorService) {
        this.inspectorService = inspectorService;
    }

    @PostMapping("add")
    public Long addInspector(@RequestBody InspectorDTO inspector) {

        return inspectorService.addInspector(
                inspector.getName(),
                inspector.getLicenseNumber(),
                inspector.getPhoneNumber()
        );
    }

    @GetMapping("getAll")
    public List<InspectorDTO> getAllInspector() {
        return InspectorDTO.convertToDTO(inspectorService.getAllInspector());
    }

    @GetMapping("getById")
    public InspectorDTO getById(@RequestParam Long id) {
        return InspectorDTO.convertToDTO(inspectorService.getById(id));
    }

    @PutMapping("update")
    public InspectorDTO updateInspector(
            @RequestBody InspectorDTO inspector) throws Exception {

        return InspectorDTO.convertToDTO(inspectorService.updateInspector(
                inspector.getInspectorId(),
                inspector.getName(),
                inspector.getLicenseNumber(),
                inspector.getPhoneNumber()
        ));
    }

    @DeleteMapping("deleteById")
    public Boolean deleteInspector(
            @RequestParam Long id) throws Exception {

        return inspectorService.deleteInspector(id);
    }
}
