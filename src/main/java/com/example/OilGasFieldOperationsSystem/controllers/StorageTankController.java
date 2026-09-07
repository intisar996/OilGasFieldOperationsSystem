package com.example.OilGasFieldOperationsSystem.controllers;

import com.example.OilGasFieldOperationsSystem.dto.StorageTankDTO;
import com.example.OilGasFieldOperationsSystem.dto.TransferDTO;
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
    public Long addStorageTank(@RequestBody StorageTankDTO storageTank) {

        return storageTankService.addStorageTank(
                storageTank.getTankCode(),
                storageTank.getCapacity(),
                storageTank.getCurrentLevel(),
                storageTank.getProduct(),
                storageTank.getOilFieldId()
        );
    }

    @GetMapping("getAll")
    public List<StorageTankDTO> getAllStorageTank() {
        return StorageTankDTO.convertToDTO(storageTankService.getAllStorageTank());
    }

    @GetMapping("getById")
    public StorageTankDTO getById(@RequestParam Long id) {
        return StorageTankDTO.convertToDTO(storageTankService.getById(id));
    }

    @PutMapping("update")
    public StorageTankDTO updateStorageTank(
            @RequestBody StorageTankDTO storageTank) throws Exception {

        return StorageTankDTO.convertToDTO(storageTankService.updateStorageTank(
                storageTank.getStorageTankId(),
                storageTank.getTankCode(),
                storageTank.getCapacity(),
                storageTank.getProduct()
        ));
    }

    @DeleteMapping("deleteById")
    public Boolean deleteStorageTank(
            @RequestParam Long id) throws Exception {

        return storageTankService.deleteStorageTank(id);
    }

    @PostMapping("transfer")
    public Long transferToTank(@RequestBody TransferDTO dto) {

        return storageTankService.transferToTank(
                dto.getProductionReadingId(),
                dto.getStorageTankId(),
                dto.getProduct()
        );
    }






}
