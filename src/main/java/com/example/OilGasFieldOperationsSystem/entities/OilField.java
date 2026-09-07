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
public class OilField extends BaseClass {

    private String name;
    private String location;
    private String region;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    private List<Well> wells;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    private List<Pipeline> pipelines;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    private List<StorageTank> storageTanks;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    private List<Equipment> equipment;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    private List<Personnel> personnel;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    private List<Incident> incidents;
}
