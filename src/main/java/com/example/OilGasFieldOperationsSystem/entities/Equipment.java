package com.example.OilGasFieldOperationsSystem.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
public class Equipment extends BaseClass {

    private String name;
    private String serialNumber;
    private String type;
    private String status;

    @ManyToOne
    private OilField oilField;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    private List<MaintenanceRecord> maintenanceRecords;
}
