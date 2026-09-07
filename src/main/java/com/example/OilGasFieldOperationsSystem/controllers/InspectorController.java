package com.example.OilGasFieldOperationsSystem.controllers;

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
    public Long addInspector(@RequestBody Inspector inspector) {

        return inspectorService.addInspector(
                inspector.getName(),
                inspector.getLicenseNumber(),
                inspector.getPhoneNumber()
        );
    }

    @GetMapping("getAll")
    public List<Inspector> getAllInspector() {
        return inspectorService.getAllInspector();
    }

    @GetMapping("getById")
    public Inspector getById(@RequestParam Long id) {
        return inspectorService.getById(id);
    }

    @PutMapping("update")
    public Inspector updateInspector(
            @RequestBody Inspector inspector) throws Exception {

        return inspectorService.updateInspector(
                inspector.getId(),
                inspector.getName(),
                inspector.getLicenseNumber(),
                inspector.getPhoneNumber()
        );
    }

    @DeleteMapping("deleteById")
    public Boolean deleteInspector(
            @RequestParam Long id) throws Exception {

        return inspectorService.deleteInspector(id);
    }
}
