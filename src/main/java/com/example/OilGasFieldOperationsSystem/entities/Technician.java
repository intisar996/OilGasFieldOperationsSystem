package com.example.OilGasFieldOperationsSystem.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
public class Technician extends BaseClass {

    private String name;
    private String specialization;
    private String phoneNumber;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    private List<MaintenanceRecord> maintenanceRecords;
}
