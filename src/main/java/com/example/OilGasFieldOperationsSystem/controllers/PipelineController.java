package com.example.OilGasFieldOperationsSystem.controllers;

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
    public Long addPipeline(@RequestBody Pipeline pipeline) {

        return pipelineService.addPipeline(
                pipeline.getPipelineCode(),
                pipeline.getLengthKm(),
                pipeline.getDiameter(),
                pipeline.getStatus(),
                pipeline.getOilField().getId()
        );
    }

    @GetMapping("getAll")
    public List<Pipeline> getAllPipeline() {
        return pipelineService.getAllPipeline();
    }

    @GetMapping("getById")
    public Pipeline getById(@RequestParam Long id) {
        return pipelineService.getById(id);
    }

    @PutMapping("update")
    public Pipeline updatePipeline(
            @RequestBody Pipeline pipeline) throws Exception {

        return pipelineService.updatePipeline(
                pipeline.getId(),
                pipeline.getPipelineCode(),
                pipeline.getLengthKm(),
                pipeline.getDiameter(),
                pipeline.getStatus()
        );
    }

    @DeleteMapping("deleteById")
    public Boolean deletePipeline(@RequestParam Long id) throws Exception {
        return pipelineService.deletePipeline(id);
    }
}
