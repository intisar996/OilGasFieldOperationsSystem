package com.example.OilGasFieldOperationsSystem.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
public class StorageTank extends BaseClass {

    private String tankCode;
    private Double capacity;
    private Double currentLevel;
    private String product;

    @ManyToOne
    private OilField oilField;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    private List<Shipment> shipments;
}
