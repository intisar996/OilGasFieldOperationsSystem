package com.example.OilGasFieldOperationsSystem.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
public class Well extends BaseClass {

    private String wellCode;
    private Double depth;
    private String type;
    private String status;

    @ManyToOne
    private OilField oilField;

    @ManyToOne
    private Rig rig;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    private List<ProductionReading> productionReadings;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    private List<Inspection> inspections;
}
