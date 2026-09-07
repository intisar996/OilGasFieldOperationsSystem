package com.example.OilGasFieldOperationsSystem.controllers;

import com.example.OilGasFieldOperationsSystem.dto.PipelineDTO;
import com.example.OilGasFieldOperationsSystem.entities.Pipeline;
import com.example.OilGasFieldOperationsSystem.services.PipelineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("Pipeline")
public class PipelineController {

    PipelineService pipelineService;

    @Autowired
    public PipelineController(PipelineService pipelineService) {
        this.pipelineService = pipelineService;
    }

    @PostMapping("add")
    public Long addPipeline(@RequestBody PipelineDTO pipeline) {

        return pipelineService.addPipeline(
                pipeline.getPipelineCode(),
                pipeline.getLengthKm(),
                pipeline.getDiameter(),
                pipeline.getStatus(),
                pipeline.getOilFieldId()
        );
    }

    @GetMapping("getAll")
    public List<PipelineDTO> getAllPipeline() {
        return PipelineDTO.convertToDTO(pipelineService.getAllPipeline());
    }

    @GetMapping("getById")
    public PipelineDTO getById(@RequestParam Long id) {
        return PipelineDTO.convertToDTO(pipelineService.getById(id));
    }

    @PutMapping("update")
    public PipelineDTO updatePipeline(
            @RequestBody PipelineDTO pipeline) throws Exception {

        return PipelineDTO.convertToDTO(pipelineService.updatePipeline(
                pipeline.getPipelineId(),
                pipeline.getPipelineCode(),
                pipeline.getLengthKm(),
                pipeline.getDiameter(),
                pipeline.getStatus()
        ));
    }

    @DeleteMapping("deleteById")
    public Boolean deletePipeline(@RequestParam Long id) throws Exception {
        return pipelineService.deletePipeline(id);
    }
}
