package com.example.OilGasFieldOperationsSystem.controllers;

import com.example.OilGasFieldOperationsSystem.entities.Incident;
import com.example.OilGasFieldOperationsSystem.services.IncidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("Incident")
public class IncidentController {

    IncidentService incidentService;

    @Autowired
    public IncidentController(IncidentService incidentService) {
        this.incidentService = incidentService;
    }

    @PostMapping("add")
    public Long addIncident(@RequestBody Incident incident) {

        return incidentService.addIncident(
                incident.getIncidentDate(),
                incident.getSeverity(),
                incident.getDescription(),
                incident.getStatus(),
                incident.getOilField().getId(),
                incident.getReportedBy().getId()
        );
    }

    @GetMapping("getAll")
    public List<Incident> getAllIncident() {
        return incidentService.getAllIncident();
    }

    @GetMapping("getById")
    public Incident getById(@RequestParam Long id) {
        return incidentService.getById(id);
    }

    @PutMapping("update")
    public Incident updateIncident(
            @RequestBody Incident incident) throws Exception {

        return incidentService.updateIncident(
                incident.getId(),
                incident.getIncidentDate(),
                incident.getSeverity(),
                incident.getDescription(),
                incident.getStatus()
        );
    }

    @DeleteMapping("deleteById")
    public Boolean deleteIncident(
            @RequestParam Long id) throws Exception {

        return incidentService.deleteIncident(id);
    }
}
