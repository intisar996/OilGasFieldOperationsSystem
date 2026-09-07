package com.example.OilGasFieldOperationsSystem.controllers;

import com.example.OilGasFieldOperationsSystem.dto.RigDTO;
import com.example.OilGasFieldOperationsSystem.entities.Rig;
import com.example.OilGasFieldOperationsSystem.services.RigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("Rig")
public class RigController {

    RigService rigService;

    @Autowired
    public RigController(RigService rigService) {
        this.rigService = rigService;
    }

    @PostMapping("add")
    public Long addRig(@RequestBody RigDTO rig) {

        return rigService.addRig(
                rig.getName(),
                rig.getModel(),
                rig.getCapacity(),
                rig.getContractorId()
        );
    }

    @GetMapping("getAll")
    public List<RigDTO> getAllRig() {
        return RigDTO.convertToDTO(rigService.getAllRig());
    }

    @GetMapping("getById")
    public RigDTO getById(@RequestParam Long id) {
        return RigDTO.convertToDTO(rigService.getById(id));
    }

    @PutMapping("update")
    public RigDTO updateRig(@RequestBody RigDTO rig) throws Exception {

        return RigDTO.convertToDTO(rigService.updateRig(
                rig.getRigId(),
                rig.getName(),
                rig.getModel(),
                rig.getCapacity()
        ));
    }

    @DeleteMapping("deleteById")
    public Boolean deleteRig(@RequestParam Long id) throws Exception {
        return rigService.deleteRig(id);
    }
}
