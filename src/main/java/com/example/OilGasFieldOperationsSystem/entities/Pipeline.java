package com.example.OilGasFieldOperationsSystem.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
public class Pipeline extends BaseClass {

    private String pipelineCode;
    private Double lengthKm;
    private Double diameter;
    private String status;

    @ManyToOne
    private OilField oilField;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    private List<StorageTank> storageTanks;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    private List<Inspection> inspections;
}
