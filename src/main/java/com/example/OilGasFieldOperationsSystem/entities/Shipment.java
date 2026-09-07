package com.example.OilGasFieldOperationsSystem.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@Entity
public class Shipment extends BaseClass {

    private Date shipmentDate;
    private Double volume;
    private String destination;
    private String status;

    @ManyToOne
    private StorageTank storageTank;

    @ManyToOne
    private Customer customer;
}
