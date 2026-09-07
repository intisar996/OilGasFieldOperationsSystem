package com.example.OilGasFieldOperationsSystem.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@Entity
public class MaintenanceRecord extends BaseClass {

    private Date maintenanceDate;
    private String description;
    private Double cost;

    @ManyToOne
    private Equipment equipment;

    @ManyToOne
    private Technician technician;
}
