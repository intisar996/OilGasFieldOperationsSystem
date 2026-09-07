package com.example.OilGasFieldOperationsSystem.services;

import com.example.OilGasFieldOperationsSystem.entities.Inspection;
import com.example.OilGasFieldOperationsSystem.entities.Inspector;
import com.example.OilGasFieldOperationsSystem.entities.Pipeline;
import com.example.OilGasFieldOperationsSystem.entities.Well;
import com.example.OilGasFieldOperationsSystem.repositories.InspectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class InspectionService {

    InspectionRepository inspectionRepository;
    WellService wellService;
    PipelineService pipelineService;
    InspectorService inspectorService;

    @Autowired
    public InspectionService(InspectionRepository inspectionRepository,
                             WellService wellService,
                             PipelineService pipelineService,
                             InspectorService inspectorService) {

        this.inspectionRepository = inspectionRepository;
        this.wellService = wellService;
        this.pipelineService = pipelineService;
        this.inspectorService = inspectorService;
    }

    public Long addInspection(Date inspectionDate,
                              String result,
                              String notes,
                              Long wellId,
                              Long pipelineId,
                              Long inspectorId) {

        Inspector inspector = inspectorService.getById(inspectorId);

        if (inspector == null || inspector.getId() == null ||
                !inspector.getIsActive()) {
            return -1L;
        }

        Well well = null;
        Pipeline pipeline = null;

        if (wellId != null) {
            well = wellService.getById(wellId);

            if (well == null || well.getId() == null ||
                    !well.getIsActive()) {
                return -1L;
            }
        }

        if (pipelineId != null) {
            pipeline = pipelineService.getById(pipelineId);

            if (pipeline == null || pipeline.getId() == null ||
                    !pipeline.getIsActive()) {
                return -1L;
            }
        }

        if (well == null && pipeline == null) {
            return -1L;
        }

        if (well != null && pipeline != null) {
            return -1L;
        }

        Inspection inspection = new Inspection();

        inspection.setIsActive(true);
        inspection.setCreatedDate(new Date());
        inspection.setInspectionDate(inspectionDate);
        inspection.setResult(result);
        inspection.setNotes(notes);
        inspection.setWell(well);
        inspection.setPipeline(pipeline);
        inspection.setInspector(inspector);

        Inspection saveInspection =
                inspectionRepository.save(inspection);

        return saveInspection.getId();
    }

    public List<Inspection> getAllInspection() {
        return inspectionRepository.getAllInspection();
    }

    public Inspection getById(Long id) {

        Optional<Inspection> inspection =
                inspectionRepository.findById(id);

        if (inspection.isPresent() && inspection.get().getIsActive()) {
            return inspection.get();
        }

        return new Inspection();
    }

    public Inspection updateInspection(Long id,
                                       Date inspectionDate,
                                       String result,
                                       String notes) throws Exception {

        Inspection inspectionToUpdate =
                inspectionRepository.getById(id);

        if (inspectionToUpdate == null) {
            throw new Exception("Inspection is not found by the id");
        }

        inspectionToUpdate.setUpdateDate(new Date());
        inspectionToUpdate.setInspectionDate(inspectionDate);
        inspectionToUpdate.setResult(result);
        inspectionToUpdate.setNotes(notes);

        return inspectionRepository.save(inspectionToUpdate);
    }

    public Boolean deleteInspection(Long id) throws Exception {

        Inspection inspectionToUpdate =
                inspectionRepository.getById(id);

        if (inspectionToUpdate == null) {
            throw new Exception("Inspection is not found by the id");
        }

        inspectionToUpdate.setUpdateDate(new Date());
        inspectionToUpdate.setIsActive(false);

        inspectionRepository.save(inspectionToUpdate);

        return true;
    }
}
