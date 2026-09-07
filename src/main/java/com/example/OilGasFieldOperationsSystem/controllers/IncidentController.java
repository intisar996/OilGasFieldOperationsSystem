package com.example.OilGasFieldOperationsSystem.controllers;

import com.example.OilGasFieldOperationsSystem.dto.IncidentDTO;
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
    public List<IncidentDTO> getAllIncident() {
        return IncidentDTO.convertToDTO(incidentService.getAllIncident());
    }

    @GetMapping("getById")
    public IncidentDTO getById(@RequestParam Long id) {
        return IncidentDTO.convertToDTO(incidentService.getById(id));
    }

    @PutMapping("update")
    public IncidentDTO updateIncident(
            @RequestBody IncidentDTO incident) throws Exception {

        return IncidentDTO.convertToDTO(incidentService.updateIncident(
                incident.getIncidentId(),
                incident.getIncidentDate(),
                incident.getSeverity(),
                incident.getDescription(),
                incident.getStatus()
        ));
    }

    @DeleteMapping("deleteById")
    public Boolean deleteIncident(
            @RequestParam Long id) throws Exception {

        return incidentService.deleteIncident(id);
    }
}
