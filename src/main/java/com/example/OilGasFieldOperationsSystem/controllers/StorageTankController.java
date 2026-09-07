package com.example.OilGasFieldOperationsSystem.controllers;

import com.example.OilGasFieldOperationsSystem.entities.StorageTank;
import com.example.OilGasFieldOperationsSystem.services.StorageTankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("StorageTank")
public class StorageTankController {

    StorageTankService storageTankService;

    @Autowired
    public StorageTankController(StorageTankService storageTankService) {
        this.storageTankService = storageTankService;
    }

    @PostMapping("add")
    public Long addStorageTank(@RequestBody StorageTank storageTank) {

        return storageTankService.addStorageTank(
                storageTank.getTankCode(),
                storageTank.getCapacity(),
                storageTank.getCurrentLevel(),
                storageTank.getProduct(),
                storageTank.getOilField().getId()
        );
    }

    @GetMapping("getAll")
    public List<StorageTank> getAllStorageTank() {
        return storageTankService.getAllStorageTank();
    }

    @GetMapping("getById")
    public StorageTank getById(@RequestParam Long id) {
        return storageTankService.getById(id);
    }

    @PutMapping("update")
    public StorageTank updateStorageTank(
            @RequestBody StorageTank storageTank) throws Exception {

        return storageTankService.updateStorageTank(
                storageTank.getId(),
                storageTank.getTankCode(),
                storageTank.getCapacity(),
                storageTank.getCurrentLevel(),
                storageTank.getProduct()
        );
    }

    @DeleteMapping("deleteById")
    public Boolean deleteStorageTank(
            @RequestParam Long id) throws Exception {

        return storageTankService.deleteStorageTank(id);
    }
}
