package com.example.OilGasFieldOperationsSystem.services;

import com.example.OilGasFieldOperationsSystem.entities.OilField;
import com.example.OilGasFieldOperationsSystem.entities.Pipeline;
import com.example.OilGasFieldOperationsSystem.repositories.OilFieldRepository;
import com.example.OilGasFieldOperationsSystem.repositories.PipelineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PipelineService {

    PipelineRepository pipelineRepository;
    OilFieldService oilFieldService;
    OilFieldRepository oilFieldRepository;

    @Autowired
    public PipelineService(PipelineRepository pipelineRepository,
                           OilFieldService oilFieldService,
                           OilFieldRepository oilFieldRepository) {

        this.pipelineRepository = pipelineRepository;
        this.oilFieldService = oilFieldService;
        this.oilFieldRepository = oilFieldRepository;
    }

    public Long addPipeline(String pipelineCode, Double lengthKm,
                            Double diameter, String status,
                            Long oilFieldId) {

        OilField oilField = oilFieldService.getById(oilFieldId);

        if (oilField == null || oilField.getId() == null || !oilField.getIsActive()) {
            return -1L;
        }

        Pipeline pipeline = new Pipeline();

        pipeline.setIsActive(true);
        pipeline.setCreatedDate(new Date());
        pipeline.setPipelineCode(pipelineCode);
        pipeline.setLengthKm(lengthKm);
        pipeline.setDiameter(diameter);
        pipeline.setStatus(status);
        pipeline.setOilField(oilField);

        Pipeline savePipeline = pipelineRepository.save(pipeline);

        return savePipeline.getId();
    }

    public List<Pipeline> getAllPipeline() {
        return pipelineRepository.getAllPipeline();
    }

    public Pipeline getById(Long id) {

        Optional<Pipeline> pipeline = pipelineRepository.findById(id);

        if (pipeline.isPresent() && pipeline.get().getIsActive()) {
            return pipeline.get();
        }

        return new Pipeline();
    }

    public Pipeline updatePipeline(Long id, String pipelineCode,
                                   Double lengthKm, Double diameter,
                                   String status) throws Exception {

        Pipeline pipelineToUpdate = pipelineRepository.getById(id);

        if (pipelineToUpdate == null) {
            throw new Exception("Pipeline is not found by the id");
        }

        pipelineToUpdate.setUpdateDate(new Date());
        pipelineToUpdate.setPipelineCode(pipelineCode);
        pipelineToUpdate.setLengthKm(lengthKm);
        pipelineToUpdate.setDiameter(diameter);
        pipelineToUpdate.setStatus(status);

        return pipelineRepository.save(pipelineToUpdate);
    }

    public Boolean deletePipeline(Long id) throws Exception {

        Pipeline pipelineToUpdate = pipelineRepository.getById(id);

        if (pipelineToUpdate == null) {
            throw new Exception("Pipeline is not found by the id");
        }

        pipelineToUpdate.setUpdateDate(new Date());
        pipelineToUpdate.setIsActive(false);

        pipelineRepository.save(pipelineToUpdate);

        return true;
    }
}
