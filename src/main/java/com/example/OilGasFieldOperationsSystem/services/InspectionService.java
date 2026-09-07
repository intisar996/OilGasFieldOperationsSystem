package com.example.OilGasFieldOperationsSystem.services;

import com.example.OilGasFieldOperationsSystem.entities.Inspection;
import com.example.OilGasFieldOperationsSystem.entities.Inspector;
import com.example.OilGasFieldOperationsSystem.entities.Pipeline;
import com.example.OilGasFieldOperationsSystem.entities.Well;
import com.example.OilGasFieldOperationsSystem.exceptions.ResourceNotFoundException;
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
    public InspectionService(
            InspectionRepository inspectionRepository,
            WellService wellService,
            PipelineService pipelineService,
            InspectorService inspectorService) {

        this.inspectionRepository = inspectionRepository;
        this.wellService = wellService;
        this.pipelineService = pipelineService;
        this.inspectorService = inspectorService;
    }

    public Long addInspection(
            Date inspectionDate,
            String result,
            String notes,
            Long wellId,
            Long pipelineId,
            Long inspectorId) {

        // Check Inspector
        Inspector inspector = inspectorService.getById(inspectorId);

        if (inspector == null ||
                inspector.getId() == null ||
                !inspector.getIsActive()) {

            throw new ResourceNotFoundException(
                    "Inspector not found with id: " + inspectorId
            );
        }

        Well well = null;
        Pipeline pipeline = null;

        // Check Well
        if (wellId != null) {

            well = wellService.getById(wellId);

            if (well == null ||
                    well.getId() == null ||
                    !well.getIsActive()) {

                throw new ResourceNotFoundException(
                        "Well not found with id: " + wellId
                );
            }
        }

        // Check Pipeline
        if (pipelineId != null) {

            pipeline = pipelineService.getById(pipelineId);

            if (pipeline == null ||
                    pipeline.getId() == null ||
                    !pipeline.getIsActive()) {

                throw new ResourceNotFoundException(
                        "Pipeline not found with id: " + pipelineId
                );
            }
        }

        // Must have either Well or Pipeline
        if (well == null && pipeline == null) {

            throw new IllegalArgumentException(
                    "Either wellId or pipelineId must be provided"
            );
        }

        // Cannot have both
        if (well != null && pipeline != null) {

            throw new IllegalArgumentException(
                    "Inspection must belong to either a well or a pipeline, not both"
            );
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

        if (inspection.isPresent() &&
                inspection.get().getIsActive()) {

            return inspection.get();
        }

        throw new ResourceNotFoundException(
                "Inspection not found with id: " + id
        );
    }


    public Inspection updateInspection(
            Long id,
            Date inspectionDate,
            String result,
            String notes) throws Exception {

        Inspection inspectionToUpdate =
                inspectionRepository.getById(id);

        if (inspectionToUpdate == null ||
                !inspectionToUpdate.getIsActive()) {

            throw new ResourceNotFoundException(
                    "Inspection not found with id: " + id
            );
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

        if (inspectionToUpdate == null ||
                !inspectionToUpdate.getIsActive()) {

            throw new ResourceNotFoundException(
                    "Inspection not found with id: " + id
            );
        }

        inspectionToUpdate.setUpdateDate(new Date());
        inspectionToUpdate.setIsActive(false);

        inspectionRepository.save(inspectionToUpdate);

        return true;
    }
}
