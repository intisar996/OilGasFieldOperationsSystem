package com.example.OilGasFieldOperationsSystem.services;

import com.example.OilGasFieldOperationsSystem.entities.Customer;
import com.example.OilGasFieldOperationsSystem.entities.Shipment;
import com.example.OilGasFieldOperationsSystem.entities.StorageTank;
import com.example.OilGasFieldOperationsSystem.exceptions.ResourceNotFoundException;
import com.example.OilGasFieldOperationsSystem.repositories.CustomerRepository;
import com.example.OilGasFieldOperationsSystem.repositories.ShipmentRepository;
import com.example.OilGasFieldOperationsSystem.repositories.StorageTankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ShipmentService {

    ShipmentRepository shipmentRepository;
    StorageTankService storageTankService;
    CustomerService customerService;
    StorageTankRepository storageTankRepository;
    CustomerRepository customerRepository;

    @Autowired
    public ShipmentService(ShipmentRepository shipmentRepository,
                           StorageTankService storageTankService,
                           CustomerService customerService,
                           StorageTankRepository storageTankRepository,
                           CustomerRepository customerRepository) {

        this.shipmentRepository = shipmentRepository;
        this.storageTankService = storageTankService;
        this.customerService = customerService;
        this.storageTankRepository = storageTankRepository;
        this.customerRepository = customerRepository;
    }

    public Long addShipment(Date shipmentDate,
                            Double volume,
                            String destination,
                            String status,
                            Long storageTankId,
                            Long customerId) {

        StorageTank storageTank =
                storageTankService.getById(storageTankId);

        Customer customer =
                customerService.getById(customerId);

        if (volume == null || volume <= 0) {
            throw new IllegalArgumentException(
                    "Shipment volume must be greater than 0"
            );
        }

        if (storageTank.getCurrentLevel() == null) {
            throw new IllegalArgumentException(
                    "Storage tank current level cannot be null"
            );
        }

        // Reject if shipment volume exceeds current tank level
        if (volume > storageTank.getCurrentLevel()) {
            throw new IllegalArgumentException(
                    "Shipment volume exceeds the current tank level"
            );
        }

        // Decrease tank level
        storageTank.setCurrentLevel(
                storageTank.getCurrentLevel() - volume
        );

        storageTankRepository.save(storageTank);

        Shipment shipment = new Shipment();

        shipment.setIsActive(true);
        shipment.setCreatedDate(new Date());
        shipment.setShipmentDate(shipmentDate);
        shipment.setVolume(volume);
        shipment.setDestination(destination);
        shipment.setStatus(status);
        shipment.setStorageTank(storageTank);
        shipment.setCustomer(customer);

        Shipment saveShipment =
                shipmentRepository.save(shipment);

        return saveShipment.getId();
    }

    public List<Shipment> getAllShipment() {
        return shipmentRepository.getAllShipment();
    }

    public Shipment getById(Long id) {

        Optional<Shipment> shipment =
                shipmentRepository.findById(id);

        if (shipment.isPresent() &&
                shipment.get().getIsActive()) {

            return shipment.get();
        }

        throw new ResourceNotFoundException(
                "Shipment not found with id: " + id
        );
    }

    public Shipment updateShipment(Long id,
                                   Date shipmentDate,
                                   Double volume,
                                   String destination,
                                   String status) {

        Shipment shipmentToUpdate =
                shipmentRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Shipment not found with id: " + id
                                )
                        );

        if (!shipmentToUpdate.getIsActive()) {
            throw new ResourceNotFoundException(
                    "Shipment not found with id: " + id
            );
        }

        shipmentToUpdate.setUpdateDate(new Date());
        shipmentToUpdate.setShipmentDate(shipmentDate);
        shipmentToUpdate.setVolume(volume);
        shipmentToUpdate.setDestination(destination);
        shipmentToUpdate.setStatus(status);

        return shipmentRepository.save(shipmentToUpdate);
    }

    public Boolean deleteShipment(Long id) {

        Shipment shipmentToUpdate =
                shipmentRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Shipment not found with id: " + id
                                )
                        );

        if (!shipmentToUpdate.getIsActive()) {
            throw new ResourceNotFoundException(
                    "Shipment not found with id: " + id
            );
        }

        shipmentToUpdate.setUpdateDate(new Date());
        shipmentToUpdate.setIsActive(false);

        shipmentRepository.save(shipmentToUpdate);

        return true;
    }
}