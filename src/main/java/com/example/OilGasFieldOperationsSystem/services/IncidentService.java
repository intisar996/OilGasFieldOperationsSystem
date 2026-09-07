package com.example.OilGasFieldOperationsSystem.services;

import com.example.OilGasFieldOperationsSystem.entities.Incident;
import com.example.OilGasFieldOperationsSystem.entities.OilField;
import com.example.OilGasFieldOperationsSystem.entities.Personnel;
import com.example.OilGasFieldOperationsSystem.repositories.IncidentRepository;
import com.example.OilGasFieldOperationsSystem.repositories.OilFieldRepository;
import com.example.OilGasFieldOperationsSystem.repositories.PersonnelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class IncidentService {

    IncidentRepository incidentRepository;
    OilFieldService oilFieldService;
    PersonnelService personnelService;
    OilFieldRepository oilFieldRepository;
    PersonnelRepository personnelRepository;

    @Autowired
    public IncidentService(IncidentRepository incidentRepository,
                           OilFieldService oilFieldService,
                           PersonnelService personnelService,
                           OilFieldRepository oilFieldRepository,
                           PersonnelRepository personnelRepository) {

        this.incidentRepository = incidentRepository;
        this.oilFieldService = oilFieldService;
        this.personnelService = personnelService;
        this.oilFieldRepository = oilFieldRepository;
        this.personnelRepository = personnelRepository;
    }

    public Long addIncident(Date incidentDate,
                            String severity,
                            String description,
                            String status,
                            Long oilFieldId,
                            Long personnelId) {

        OilField oilField = oilFieldService.getById(oilFieldId);
        Personnel personnel = personnelService.getById(personnelId);

        if (oilField == null || oilField.getId() == null ||
                !oilField.getIsActive()) {
            return -1L;
        }

        if (personnel == null || personnel.getId() == null ||
                !personnel.getIsActive()) {
            return -1L;
        }

        Incident incident = new Incident();

        incident.setIsActive(true);
        incident.setCreatedDate(new Date());
        incident.setIncidentDate(incidentDate);
        incident.setSeverity(severity);
        incident.setDescription(description);
        incident.setStatus(status);
        incident.setOilField(oilField);
        incident.setReportedBy(personnel);

        Incident saveIncident = incidentRepository.save(incident);

        return saveIncident.getId();
    }

    public List<Incident> getAllIncident() {
        return incidentRepository.getAllIncident();
    }

    public Incident getById(Long id) {

        Optional<Incident> incident =
                incidentRepository.findById(id);

        if (incident.isPresent() && incident.get().getIsActive()) {
            return incident.get();
        }

        return new Incident();
    }

    public Incident updateIncident(Long id,
                                   Date incidentDate,
                                   String severity,
                                   String description,
                                   String status) throws Exception {

        Incident incidentToUpdate =
                incidentRepository.getById(id);

        if (incidentToUpdate == null) {
            throw new Exception("Incident is not found by the id");
        }

        incidentToUpdate.setUpdateDate(new Date());
        incidentToUpdate.setIncidentDate(incidentDate);
        incidentToUpdate.setSeverity(severity);
        incidentToUpdate.setDescription(description);
        incidentToUpdate.setStatus(status);

        return incidentRepository.save(incidentToUpdate);
    }

    public Boolean deleteIncident(Long id) throws Exception {

        Incident incidentToUpdate =
                incidentRepository.getById(id);

        if (incidentToUpdate == null) {
            throw new Exception("Incident is not found by the id");
        }

        incidentToUpdate.setUpdateDate(new Date());
        incidentToUpdate.setIsActive(false);

        incidentRepository.save(incidentToUpdate);

        return true;
    }
}
