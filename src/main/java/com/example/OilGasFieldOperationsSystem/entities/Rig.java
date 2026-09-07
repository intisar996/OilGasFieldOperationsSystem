package com.example.OilGasFieldOperationsSystem.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
public class Rig extends BaseClass {

    private String name;
    private String model;
    private Double capacity;

    @ManyToOne
    private Contractor contractor;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    private List<Well> wells;
}
