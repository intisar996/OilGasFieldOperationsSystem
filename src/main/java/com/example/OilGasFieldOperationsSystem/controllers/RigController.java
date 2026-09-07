package com.example.OilGasFieldOperationsSystem.controllers;

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
    public Long addRig(@RequestBody Rig rig) {

        return rigService.addRig(
                rig.getName(),
                rig.getModel(),
                rig.getCapacity(),
                rig.getContractor().getId()
        );
    }

    @GetMapping("getAll")
    public List<Rig> getAllRig() {
        return rigService.getAllRig();
    }

    @GetMapping("getById")
    public Rig getById(@RequestParam Long id) {
        return rigService.getById(id);
    }

    @PutMapping("update")
    public Rig updateRig(@RequestBody Rig rig) throws Exception {

        return rigService.updateRig(
                rig.getId(),
                rig.getName(),
                rig.getModel(),
                rig.getCapacity()
        );
    }

    @DeleteMapping("deleteById")
    public Boolean deleteRig(@RequestParam Long id) throws Exception {
        return rigService.deleteRig(id);
    }
}
