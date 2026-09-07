package com.example.OilGasFieldOperationsSystem.controllers;

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
    public Long addShipment(@RequestBody Shipment shipment) {

        return shipmentService.addShipment(
                shipment.getShipmentDate(),
                shipment.getVolume(),
                shipment.getDestination(),
                shipment.getStatus(),
                shipment.getStorageTank().getId(),
                shipment.getCustomer().getId()
        );
    }

    @GetMapping("getAll")
    public List<Shipment> getAllShipment() {
        return shipmentService.getAllShipment();
    }

    @GetMapping("getById")
    public Shipment getById(@RequestParam Long id) {
        return shipmentService.getById(id);
    }

    @PutMapping("update")
    public Shipment updateShipment(
            @RequestBody Shipment shipment) throws Exception {

        return shipmentService.updateShipment(
                shipment.getId(),
                shipment.getShipmentDate(),
                shipment.getVolume(),
                shipment.getDestination(),
                shipment.getStatus()
        );
    }

    @DeleteMapping("deleteById")
    public Boolean deleteShipment(
            @RequestParam Long id) throws Exception {

        return shipmentService.deleteShipment(id);
    }
}
