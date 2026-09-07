package com.example.OilGasFieldOperationsSystem.controllers;

import com.example.OilGasFieldOperationsSystem.dto.ShipmentDTO;
import com.example.OilGasFieldOperationsSystem.entities.Shipment;
import com.example.OilGasFieldOperationsSystem.services.ShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("Shipment")
public class ShipmentController {

    ShipmentService shipmentService;

    @Autowired
    public ShipmentController(ShipmentService shipmentService) {
        this.shipmentService = shipmentService;
    }

    @PostMapping("add")
    public Long addShipment(@RequestBody ShipmentDTO shipment) {

        return shipmentService.addShipment(
                shipment.getShipmentDate(),
                shipment.getVolume(),
                shipment.getDestination(),
                shipment.getStatus(),
                shipment.getStorageTankId(),
                shipment.getCustomerId()
        );
    }

    @GetMapping("getAll")
    public List<ShipmentDTO> getAllShipment() {
        return ShipmentDTO.convertToDTO(shipmentService.getAllShipment());
    }

    @GetMapping("getById")
    public ShipmentDTO getById(@RequestParam Long id) {
        return ShipmentDTO.convertToDTO(shipmentService.getById(id));
    }

    @PutMapping("update")
    public ShipmentDTO updateShipment(
            @RequestBody ShipmentDTO shipment) throws Exception {

        return ShipmentDTO.convertToDTO(shipmentService.updateShipment(
                shipment.getShipmentId(),
                shipment.getShipmentDate(),
                shipment.getVolume(),
                shipment.getDestination(),
                shipment.getStatus()
        ));
    }

    @DeleteMapping("deleteById")
    public Boolean deleteShipment(
            @RequestParam Long id) throws Exception {

        return shipmentService.deleteShipment(id);
    }
}
